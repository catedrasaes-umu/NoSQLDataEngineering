package es.um.unosql.subtypes.discovery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import es.um.unosql.subtypes.discovery.dependencies.DependencyDetector;
import es.um.unosql.subtypes.discovery.dependencies.SchemaChangeDetector;
import es.um.unosql.subtypes.discovery.discriminator.DBDiscriminatorSeeker;
import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralVariation;

public class DependencyAnalyzer
{
    private SchemaType sType;
    private FeatureMatrix matrix;
    private DependencyDetector dFeatsDetector;
    private SchemaChangeDetector sChangeDetector;
    private List<EntitySubtype> subtypes;
    private DBDiscriminatorSeeker discrSeeker;

    public DependencyAnalyzer(SchemaType sType)
    {
        this(sType, null);
    }

    public DependencyAnalyzer(SchemaType sType, DBDiscriminatorSeeker seeker)
    {
        this.sType = sType;
        this.discrSeeker = seeker;
    }

    public void doDependencyAnalysis()
    {
        matrix = new FeatureMatrix(sType);
        dFeatsDetector = new DependencyDetector(matrix);
        subtypes = detectSubtypes();
        sChangeDetector = new SchemaChangeDetector(subtypes);

        if (discrSeeker != null)
        {
            discrSeeker.doSeek(
                    sType.getName().toLowerCase(),
                    subtypes.stream().filter(subtype -> !subtype.getSubtypeRequiredFeatures().isEmpty())
                            .collect(Collectors.toList()),
                    matrix.getFeatures().stream().filter(feat -> !matrix.isFeatOptional(feat))
                            .collect(Collectors.toList()));
        }
    }

    public SchemaType getSchemaType()
    {
        return sType;
    }

    public FeatureMatrix getFeatureMatrix()
    {
        return matrix;
    }

    public SchemaType getsType()
    {
        return sType;
    }

    public DependencyDetector getDependentFeats()
    {
        return dFeatsDetector;
    }

    public SchemaChangeDetector getSchemaChanges()
    {
        return sChangeDetector;
    }

    public List<EntitySubtype> getSubtypes()
    {
        return subtypes;
    }

    public DBDiscriminatorSeeker getDiscriminatorSeeker()
    {
        return discrSeeker;
    }

    private List<EntitySubtype> detectSubtypes()
    {
        List<EntitySubtype> subtypes = new ArrayList<EntitySubtype>();
        Set<Feature> allSubtypeFeats = new HashSet<Feature>();
        Set<StructuralVariation> remainingVars = new HashSet<StructuralVariation>(sType.getVariations());

        // About to detect subtypes...
        for (Set<Feature> subtypeRequiredFeats : dFeatsDetector.getStrongDependencies())
        {
            Set<Feature> subtypeOptionalFeats = dFeatsDetector.getWeakDependencies().keySet().stream()
                    .filter(weakFeat -> dFeatsDetector.getWeakDependencies()
                            .getOrDefault(weakFeat, Collections.emptySet()).containsAll(subtypeRequiredFeats))
                    .collect(Collectors.toSet());
            allSubtypeFeats.addAll(subtypeRequiredFeats);
            allSubtypeFeats.addAll(subtypeOptionalFeats);
            // Variations belonging to a subtype are variations that have all of the
            // required features for that subtype.
            // Because of this we take the first of the subtype features and then get
            // variations with that first feature.
            EntitySubtype subtype = new EntitySubtype(
                    matrix.getVarsFromFeature(subtypeRequiredFeats.stream().findFirst().get()),
                    subtypeRequiredFeats, subtypeOptionalFeats);
            subtypes.add(subtype);
            remainingVars.removeAll(subtype.getVariations());
        }

        // Now get all features independent of particularWeakDeps and subtypeFeats...
        // and filter those independent features with weak dependencies with another
        // independent features.
        // The remaining features are the identifying ones.
        List<Feature> tempList = dFeatsDetector.getExcludingFeatures().keySet().stream()
                .filter(exclFeat -> dFeatsDetector.getExcludingFeatures().get(exclFeat).containsAll(allSubtypeFeats))
                .collect(Collectors.toList());

        List<Feature> remainingFeats = tempList.stream().filter(remFeat -> {
            for (Feature remFeat2 : tempList)
                if (dFeatsDetector.getWeakDependencies().getOrDefault(remFeat, Collections.emptySet())
                        .contains(remFeat2))
                    return false;
            return true;
        }).collect(Collectors.toList());

        // TODO: So there is a small mistake here: Once a remainingFeat is identified as
        // exclusive for a subtype, the rest of remainingFeats must be recalculated.
        // Products2 model
        for (Feature remFeat : remainingFeats)
        {
            Set<Feature> subtypeOptionalFeats = dFeatsDetector.getWeakDependencies().keySet().stream()
                    .filter(weakFeat -> dFeatsDetector.getWeakDependencies()
                            .getOrDefault(weakFeat, Collections.emptySet())
                            .contains(remFeat))
                    .collect(Collectors.toSet());

            EntitySubtype subtype = new EntitySubtype(matrix.getVarsFromFeature(remFeat),
                    Collections.unmodifiableSet(new HashSet<Feature>(Arrays.asList(remFeat))),
                    subtypeOptionalFeats);
            subtypes.add(subtype);
            remainingVars.removeAll(subtype.getVariations());
        }

        // Finally, create a new subtype grouping all variations not belonging to any
        // subtype.
        // Note that this subtype wont be used to detect the discriminator field.
        // if (!remainingVars.isEmpty())
        // subtypes.add(new EntitySubtype(remainingVars, new ArrayList<Feature>(), new
        // ArrayList<Feature>()));

        return subtypes;
    }
}
