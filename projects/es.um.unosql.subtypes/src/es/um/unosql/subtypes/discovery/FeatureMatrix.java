package es.um.unosql.subtypes.discovery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.Key;
import es.um.unosql.uNoSQLSchema.Reference;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralFeature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.utils.compare.CompareFeature;

public class FeatureMatrix
{
    private CompareFeature comparer;
    private SchemaType sType;
    private Map<Feature, List<StructuralVariation>> featMatrix;
    private Set<Feature> optionalFeats;

    public FeatureMatrix(SchemaType sType)
    {
        this.featMatrix = new HashMap<Feature, List<StructuralVariation>>();
        this.comparer = new CompareFeature();
        this.sType = sType;
        this.optionalFeats = new HashSet<Feature>();

        sType.getVariations().forEach(var -> {
            var.getFeatures().forEach(feature -> {
                // For each feature we keep track of it.
                // If the feature is Key we do not consider it because we are taking into
                // account its binded Attribute.
                // If the feature is a Reference without a name we do not consider it for the
                // same reason.
                // If the feature is an Attribute, an Aggregate or a named Reference (Neo4J) we
                // will keep track of it.
                if (feature instanceof Key || (feature instanceof Reference && ((Reference) feature).getName() == null))
                    return;

                // We have to keep track of the optional features.
                // If the feature is a StructuralFeature, we check its attribute.
                // If the feature is a named reference, we check its lowerbound. TODO: This
                // might be not correct.
                if ((feature instanceof StructuralFeature && ((StructuralFeature) feature).isOptional()) ||
                        (feature instanceof Reference && ((Reference) feature).getLowerBound() == 0))
                    if (this.optionalFeats.stream().noneMatch(f -> this.comparer.compare(f, feature)))
                        this.optionalFeats.add(feature);

                // For each considered feature, we create an entry in the matrix with the
                // variations in which it exists.
                Optional<Feature> featKey = featMatrix.keySet().stream()
                        .filter(feat2 -> comparer.compare(feature, feat2)).findFirst();
                if (featKey.isPresent())
                    featMatrix.get(featKey.get()).add(var);
                else
                {
                    List<StructuralVariation> varList = new ArrayList<StructuralVariation>(1);
                    varList.add(var);
                    featMatrix.put(feature, varList);
                }
            });
        });
    }

    public SchemaType getSchemaType()
    {
        return sType;
    }

    public Set<Feature> getFeatures()
    {
        return featMatrix.keySet();
    }

    public List<StructuralVariation> getVariations()
    {
        return sType.getVariations();
    }

    public List<StructuralVariation> getVarsFromFeature(Feature feature)
    {
        return featMatrix.get(feature);
    }

    public Boolean isFeatOptional(Feature feat)
    {
        return optionalFeats.stream().anyMatch(f -> this.comparer.compare(f, feat));
    }
}
