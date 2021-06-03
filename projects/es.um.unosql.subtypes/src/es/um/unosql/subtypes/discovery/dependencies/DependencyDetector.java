package es.um.unosql.subtypes.discovery.dependencies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import es.um.unosql.subtypes.discovery.FeatureMatrix;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;

public class DependencyDetector
{
    // <Feature A>: List<Features that appear when A does>
    private Map<Feature, Set<Feature>> dependencyMap;
    private Set<Set<Feature>> strongDependencies;
    private Map<Feature, Set<Feature>> weakDependencies;
    private Map<Feature, Set<Feature>> excludingFeatures;

    public DependencyDetector(FeatureMatrix matrix)
    {
        this.dependencyMap = checkDependencies(matrix);
        this.strongDependencies = detectStrongDependencies();
        this.weakDependencies = detectWeakDependencies();
        this.excludingFeatures = detectExcludingFeatures(matrix);
    }

    public Set<Set<Feature>> getStrongDependencies()
    {
        return strongDependencies;
    }

    public Map<Feature, Set<Feature>> getWeakDependencies()
    {
        return weakDependencies;
    }

    public Map<Feature, Set<Feature>> getExcludingFeatures()
    {
        return excludingFeatures;
    }

    private Map<Feature, Set<Feature>> checkDependencies(FeatureMatrix matrix)
    {
        Map<Feature, Set<Feature>> dependencyMap = new HashMap<Feature, Set<Feature>>();
        Set<Feature> optionalFeatures = matrix.getFeatures().stream()
                .filter(feat -> matrix.isFeatOptional(feat)).collect(Collectors.toSet());

        for (Feature feat1 : optionalFeatures)
        {
            Set<Feature> dependencies = new HashSet<Feature>();
            dependencyMap.put(feat1, dependencies);
            List<StructuralVariation> feat1Vars = matrix.getVarsFromFeature(feat1);

            dependencies.addAll(optionalFeatures.stream().filter(feat2 -> {
                return feat1 != feat2 && matrix.getVarsFromFeature(feat2).containsAll(feat1Vars);
            }).collect(Collectors.toList()));
        }

        return dependencyMap;
    }

    // Strong dependencies do exist if for all and each time a Feature A appears,
    // another Feature B appears,
    // and also for all and each time Feature B appears, Feature A also appears.
    private HashSet<Set<Feature>> detectStrongDependencies()
    {
        var strongDependencies = new HashSet<Set<Feature>>();

        // Initial structure is like: Feature A: <List of attributes that, when they
        // appear, Feature A also appears>
        for (Feature feat1 : dependencyMap.keySet())
        {
            for (Feature feat2 : dependencyMap.get(feat1))
            {
                // If we already detected a strong dependency between these features, go on.
                // If dependency list is of different sizes, there is no strong dependency.
                if (strongDependencies.stream().anyMatch(list -> list.contains(feat1) && list.contains(feat2))
                        || dependencyMap.get(feat1).size() != dependencyMap.get(feat2).size())
                    continue;

                // Feature A: <FeatB, FeatC, FeatD>
                // Feature B: <FeatA, FeatC, FeatD>
                // From List A, we extract FeatB, then add FeatA, then compare.
                Set<Feature> auxSet = new HashSet<Feature>();
                auxSet.addAll(dependencyMap.get(feat1));
                auxSet.remove(feat2);
                auxSet.add(feat1);

                // Once we know Feats A and B are strongly dependent, we try to insert them were
                // they belong.
                if (auxSet.containsAll(dependencyMap.get(feat2)))
                {
                    Optional<Set<Feature>> optList = strongDependencies.stream().filter(list -> list.contains(feat1))
                            .findFirst();
                    if (optList.isPresent())
                        optList.get().add(feat2);
                    else
                        strongDependencies.add(new HashSet<>(Arrays.asList(feat1, feat2)));
                }
            }
        }

        return strongDependencies;
    }

    private Map<Feature, Set<Feature>> detectWeakDependencies()
    {
        Map<Feature, Set<Feature>> weakDependencies = new HashMap<Feature, Set<Feature>>();

        for (Feature feat : dependencyMap.keySet())
        {
            // First of all, for a feature, get all its dependencies.
            Set<Feature> dependencies = new HashSet<Feature>(dependencyMap.get(feat));

            // Try to get its strong dependencies.
            Optional<Set<Feature>> optList = strongDependencies.stream().filter(list -> list.contains(feat))
                    .findFirst();
            if (optList.isPresent())
                // If it has strong dependencies, we remove those from the dependency list...
                dependencies.removeAll(optList.get());

            // The remaining dependencies are weak.
            if (!dependencies.isEmpty())
                weakDependencies.put(feat, dependencies);
        }

        return weakDependencies;
    }

    private Map<Feature, Set<Feature>> detectExcludingFeatures(FeatureMatrix matrix)
    {
        Map<Feature, Set<Feature>> excludingFeatures = new HashMap<Feature, Set<Feature>>();
        Set<Feature> optionalFeatures = matrix.getFeatures().stream()
                .filter(feat -> matrix.isFeatOptional(feat)).collect(Collectors.toSet());

        for (Feature feat1 : optionalFeatures)
        {
            Set<Feature> exclusions = new HashSet<Feature>();
            Set<StructuralVariation> feat1Set = new HashSet<StructuralVariation>(matrix.getVarsFromFeature(feat1));

            for (Feature feat2 : optionalFeatures)
            {
                if (feat1 == feat2)
                    continue;

                Set<StructuralVariation> feat2Set = new HashSet<>(matrix.getVarsFromFeature(feat2));
                feat2Set.retainAll(feat1Set);
                if (feat2Set.isEmpty())
                    exclusions.add(feat2);
                // if (feat1Set.stream().filter(feat -> feat2Set.contains(feat)).count() == 0)
            }
            if (!exclusions.isEmpty())
                excludingFeatures.put(feat1, exclusions);
        }
        // Two features are exclusive if, and only if, when one of them occurs, then the
        // other do not occur.
        return excludingFeatures;
    }
}
