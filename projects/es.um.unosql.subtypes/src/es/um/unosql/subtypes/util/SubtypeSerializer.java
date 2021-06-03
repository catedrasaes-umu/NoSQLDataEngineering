package es.um.unosql.subtypes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.unosql.subtypes.discovery.DependencyAnalyzer;
import es.um.unosql.subtypes.discovery.FeatureMatrix;
import es.um.unosql.subtypes.discovery.dependencies.DependencyDetector;
import es.um.unosql.subtypes.discovery.discriminator.DBDiscriminatorSeeker;
import es.um.unosql.subtypes.outliers.OutlierTransformer;
import es.um.unosql.subtypes.outliers.impl.CoverageOutlierDetector;
import es.um.unosql.subtypes.outliers.impl.EpsilonOutlierDetector;
import es.um.unosql.subtypes.outliers.OutlierDetector;
import es.um.unosql.subtypes.util.types.EntitySubtype;
import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.utils.Serializer;
import es.um.unosql.utils.compare.CompareFeature;

public class SubtypeSerializer
{
    private CompareFeature comparer;

    public SubtypeSerializer()
    {
        comparer = new CompareFeature();
    }

    public String printPretty(DependencyAnalyzer detector)
    {
        StringBuilder result = new StringBuilder();

        result.append("> " + detector.getSchemaType().getName() + "\n");
        result.append(printPretty(detector.getFeatureMatrix()));
        result.append(printPretty(detector.getDependentFeats()));

        return result.toString();
    }

    public String printPretty(DependencyDetector dFeatures)
    {
        StringBuilder result = new StringBuilder();

        if (!dFeatures.getStrongDependencies().isEmpty())
        {
            result.append("Strong dependencies:\n");
            for (var strDependencies : dFeatures.getStrongDependencies())
                result.append(
                        "  (" + strDependencies.stream().map(feat -> feat.getName()).collect(Collectors.joining(", "))
                                + ")\n");
            result.append("\n");
        }

        if (!dFeatures.getWeakDependencies().isEmpty())
        {
            result.append("Weak dependencies:\n");
            for (Feature weakDependency : dFeatures.getWeakDependencies().keySet())
                result.append(
                        "  (" + weakDependency.getName() + ": " + dFeatures.getWeakDependencies().get(weakDependency)
                                .stream().map(feat -> feat.getName()).collect(Collectors.joining(", ")) + ")\n");
            result.append("\n");
        }

        if (!dFeatures.getExcludingFeatures().isEmpty())
        {
            result.append("Exclusive features:\n");
            for (Feature exclusionFeat : dFeatures.getExcludingFeatures().keySet())
                result.append(
                        "  (" + exclusionFeat.getName() + ": " + dFeatures.getExcludingFeatures().get(exclusionFeat)
                                .stream().map(feat -> feat.getName()).collect(Collectors.joining(", ")) + ")\n");
            result.append("\n");
        }

        return result.toString();
    }

    public String printPretty(FeatureMatrix matrix)
    {
        StringBuilder result = new StringBuilder();

        int spacing = matrix.getFeatures().stream().mapToInt(feat -> {
            return Serializer.serialize(feat).length();
        }).max().getAsInt() + 2;

        result.append(createStr(spacing));

        for (StructuralVariation var : matrix.getSchemaType().getVariations())
            result.append(var.getVariationId() + " ");
        result.append("\n");

        List<Feature> requiredFeatures = matrix.getFeatures().stream().filter(feat -> !matrix.isFeatOptional(feat))
                .sorted((f1, f2) -> f1.getName().compareTo(f2.getName())).collect(Collectors.toList());
        List<Feature> optionalFeatures = matrix.getFeatures().stream().filter(feat -> matrix.isFeatOptional(feat))
                .sorted((f1, f2) -> matrix.getVarsFromFeature(f2).size() - matrix.getVarsFromFeature(f1).size())
                .collect(Collectors.toList());

        if (!requiredFeatures.isEmpty())
        {
            result.append("Required:\n");
            for (Feature feat : requiredFeatures)
                result.append(serializeMatrixFeature(matrix, feat, spacing));
            result.append("\n");
        }

        if (!optionalFeatures.isEmpty())
        {
            result.append("Optional:\n");
            for (Feature feat : optionalFeatures)
                result.append(serializeMatrixFeature(matrix, feat, spacing));
        }

        result.append("\n");

        return result.toString();
    }

    public String printPretty(OutlierDetector detector)
    {
        StringBuilder result = new StringBuilder();
        String endLine = "\n";
        Map<SchemaType, List<StructuralVariation>> outliers = detector.getOutliers();

        for (SchemaType schemaType : outliers.keySet())
        {
            long numObjects = Stream.concat(schemaType.getVariations().stream(), outliers.get(schemaType).stream())
                    .mapToLong(var -> var.getCount()).sum();
            int numVariations = schemaType.getVariations().size() + outliers.get(schemaType).size();
            double factor = detector.getFactor();

            result.append(schemaType.getName() + ": " + numObjects + " items" + endLine);

            if (detector instanceof CoverageOutlierDetector)
            {
                long countCoverage = Math.round((numObjects * factor) / 100);
                result.append("Coverage factor: " + factor + "% (" + countCoverage + " items)" + endLine);
            } else if (detector instanceof EpsilonOutlierDetector)
            {
                long countThreshold = Math.round(numObjects * factor);
                result.append("Epsilon factor: " + String.format("%.4f", factor) + " (" + countThreshold + " items)"
                        + endLine);
            }

            result.append("Variations before/after: " + numVariations + " => " + schemaType.getVariations().size()
                    + endLine + endLine);
        }

        return result.toString();
    }

    public String printPretty(OutlierTransformer transformer)
    {
        StringBuilder result = new StringBuilder();

        for (SchemaType key : transformer.getOutliersAlternatives().keySet())
        {
            result.append("\n" + key.getName() + ":\n");
            for (StructuralVariation outlier : transformer.getOutliersAlternatives().get(key).keySet())
            {
                result.append("  [" + outlier.getVariationId() + " -> "
                        + transformer.getOutliersAlternatives().get(key).get(outlier).stream()
                                .map(alt -> Integer.toString(alt.getVariationId()))
                                .collect(Collectors.joining(", "))
                        + "]:\n");
                result.append(
                        getDifferences(outlier, transformer.getOutliersAlternatives().get(key).get(outlier).get(0), 4));
            }
        }

        return result.toString();
    }

    public String printPretty(List<EntitySubtype> subtypes)
    {
        StringBuilder result = new StringBuilder();

        for (EntitySubtype subtype : subtypes)
            result.append(printPretty(subtype));

        result.append("\n");

        return result.toString();
    }

    public String printPretty(EntitySubtype subtype)
    {
        StringBuilder result = new StringBuilder();
        result.append("Subtype:\n");
        result.append("  Variations: " + subtype.getVariations().stream()
                .map(var -> Integer.toString(var.getVariationId())).collect(Collectors.joining(", ")) + "\n");

        if (!subtype.getSubtypeRequiredFeatures().isEmpty())
            result.append("  Req Feats : (" + subtype.getSubtypeRequiredFeatures().stream().map(feat -> feat.getName())
                    .collect(Collectors.joining(", ")) + ")\n");

        if (!subtype.getSubtypeOptionalFeatures().isEmpty())
            result.append("  Opt Feats : (" + subtype.getSubtypeOptionalFeatures().stream().map(feat -> feat.getName())
                    .collect(Collectors.joining(", ")) + ")\n");

        if (!subtype.getSchemaAdds().isEmpty())
            result.append("  Schema add: (" + subtype.getSchemaAdds().stream()
                    .map(schemaAdd -> schemaAdd.getAddedFeature().getName() + " in "
                            + schemaAdd.getFirstVariation().getVariationId())
                    .collect(Collectors.joining(", ")) + ")\n");

        if (!subtype.getSchemaRemoves().isEmpty())
            result.append("  Schema rem: (" + subtype.getSchemaRemoves().stream()
                    .map(schemaRem -> schemaRem.getRemovedFeature().getName() + " in "
                            + schemaRem.getLastVariation().getVariationId())
                    .collect(Collectors.joining(", ")) + ")\n");

        if (!subtype.getSchemaChanges().isEmpty())
            result.append("  Schema chg: (" + subtype.getSchemaChanges().stream()
                    .map(schemaChg -> schemaChg.getRemovedFeature().getName() + " in "
                            + schemaChg.getFirstVariation().getVariationId() + " for " +
                            schemaChg.getAddedFeature().getName() + " in "
                            + schemaChg.getSecondVariation().getVariationId())
                    .collect(Collectors.joining(", ")) + ")\n");

        return result.toString();
    }

    public String printPretty(DBDiscriminatorSeeker dSeeker)
    {
        StringBuilder result = new StringBuilder();

        if (dSeeker != null)
        {
            result.append("Discriminator field detected: " + Serializer.serialize(dSeeker.getDiscriminator()) + "\n");
            result.append("Subtype values: " + dSeeker.getDiscriminatorValues().values());
        } else
            result.append("No Discriminator seeker was configured!");

        return result.toString();
    }

    public String getDifferences(StructuralVariation var1, StructuralVariation var2)
    {
        return getDifferences(var1, var2, 0);
    }

    public String getDifferences(StructuralVariation var1, StructuralVariation var2, int numSpaces)
    {
        StringBuilder result = new StringBuilder();
        List<Feature> var1OnlyFeatures = new ArrayList<Feature>();
        List<Feature> var2OnlyFeatures = new ArrayList<Feature>();

        var1OnlyFeatures = var1.getFeatures().stream()
                .filter(feat -> var2.getFeatures().stream().noneMatch(feat2 -> comparer.compare(feat, feat2)))
                .collect(Collectors.toList());
        var2OnlyFeatures = var2.getFeatures().stream()
                .filter(feat -> var1.getFeatures().stream().noneMatch(feat2 -> comparer.compare(feat, feat2)))
                .collect(Collectors.toList());

        var1OnlyFeatures
                .forEach(feat -> result.append(createStr(numSpaces) + "-   " + Serializer.serialize(feat) + "\n"));
        var2OnlyFeatures
                .forEach(feat -> result.append(createStr(numSpaces) + "+   " + Serializer.serialize(feat) + "\n"));

        return result.toString();
    }

    private String createStr(int numSpaces)
    {
        return new String(new char[numSpaces]).replace('\0', ' ');
    }

    private String createStr(String str, int numSpaces)
    {
        return new String(str).concat(createStr(numSpaces - str.length()));
    }

    private String serializeMatrixFeature(FeatureMatrix matrix, Feature feature, int spacing)
    {
        StringBuilder result = new StringBuilder();

        result.append(createStr(Serializer.serialize(feature), spacing));

        for (int i = 0; i < matrix.getSchemaType().getVariations().size(); i++)
        {
            StructuralVariation var = matrix.getSchemaType().getVariations().get(i);
            if (matrix.getVarsFromFeature(feature).contains(var))
                result.append("X");
            else
                result.append("-");
            result.append(" ");
            if (var.getVariationId() >= 9)
                result.append(" ");
            if (var.getVariationId() >= 99)
                result.append(" ");
        }
        result.append("\n");

        return result.toString();
    }
}
