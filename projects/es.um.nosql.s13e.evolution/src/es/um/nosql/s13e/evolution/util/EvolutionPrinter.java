package es.um.nosql.s13e.evolution.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.DependencyAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.detectors.DependentPropsDetector;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierTransformer;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.CoverageOutlierDetector;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.EpsilonOutlierDetector;
import es.um.nosql.s13e.evolution.types.EntitySubtype;
import es.um.nosql.s13e.util.Serializer;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class EvolutionPrinter
{
  private CompareProperty propComparer;

  public EvolutionPrinter()
  {
    propComparer = new CompareProperty();
  }

  public String printPretty(DependencyAnalyzer detector)
  {
    StringBuilder result = new StringBuilder();

    result.append("> " + detector.getSchemaType().getName() + "\n");
    result.append(printPretty(detector.getPropertyMatrix()));
    result.append(printPretty(detector.getDependentProps()));

    return result.toString();
  }

  public String printPretty(DependentPropsDetector dProps)
  {
    StringBuilder result = new StringBuilder();

    if (!dProps.getStrongDependencies().isEmpty())
    {
      result.append("Strong dependencies:\n");
      for (List<Property> strDependencies : dProps.getStrongDependencies())
        result.append("  (" + strDependencies.stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }

    if (!dProps.getWeakDependencies().isEmpty())
    {
      result.append("Weak dependencies:\n");
      for (Property weakDependency : dProps.getWeakDependencies().keySet())
        result.append("  (" + weakDependency.getName() + ": " + dProps.getWeakDependencies().get(weakDependency).stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }

    if (!dProps.getExcludingProps().isEmpty())
    {
      result.append("Exclusive properties:\n");
      for (Property exclusionProp : dProps.getExcludingProps().keySet())
        result.append("  (" + exclusionProp.getName() + ": " + dProps.getExcludingProps().get(exclusionProp).stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }

    return result.toString();
  }

  public String printPretty(PropertyMatrix matrix)
  {
    StringBuilder result = new StringBuilder();

    int spacing = matrix.getProperties().stream().mapToInt(prop ->
    {
      return serializeShort(prop).length();
    }).max().getAsInt() + 2;

    result.append(createStr(spacing));

    for (StructuralVariation var: matrix.getSchemaType().getVariations())
      result.append(var.getVariationId() + " ");
    result.append("\n");

    List<Property> requiredProperties = matrix.getProperties().stream().filter(prop -> !prop.isOptional()).collect(Collectors.toList());
    List<Property> optionalProperties = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    if (!requiredProperties.isEmpty())
    {
      result.append("Required:\n");
      for (Property prop : requiredProperties)
        result.append(serializeMatrixProperty(matrix, prop, spacing));
      result.append("\n");
    }

    if (!optionalProperties.isEmpty())
    {
      result.append("Optional:\n");
      for (Property prop : optionalProperties)
        result.append(serializeMatrixProperty(matrix, prop, spacing));
    }

    result.append("\n");

    return result.toString();
  }

  public String printPretty(OutlierAnalyzer analyzer)
  {
    StringBuilder result = new StringBuilder();
    String endLine = "\n";
    Map<SchemaType, List<StructuralVariation>> outliers = analyzer.getOutliers();

    for (SchemaType schemaType : outliers.keySet())
    {
      long numObjects = Stream.concat(schemaType.getVariations().stream(), outliers.get(schemaType).stream()).mapToLong(var -> var.getCount()).sum();
      int numVariations = schemaType.getVariations().size() + outliers.get(schemaType).size();
      double factor = analyzer.getOutlierDetector().getFactor();

      result.append(schemaType.getName() + ": " + numObjects + " items" + endLine);

      if (analyzer.getOutlierDetector() instanceof CoverageOutlierDetector)
      {
        long countCoverage = Math.round((numObjects * factor) / 100);
        result.append("Coverage factor: " + factor + "% (" + countCoverage + " items)" + endLine);
      }
      else if (analyzer.getOutlierDetector() instanceof EpsilonOutlierDetector)
      {
        long countThreshold = Math.round(numObjects * factor);
        result.append("Epsilon factor: " + String.format("%.4f", factor) + " (" + countThreshold + " items)" + endLine);
      }

      result.append("Variations before/after: " + numVariations + " => " + schemaType.getVariations().size() + endLine + endLine);
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
        result.append("  [" + outlier.getVariationId() + " -> " + transformer.getOutliersAlternatives().get(key).get(outlier).stream()
            .map(alt -> Integer.toString(alt.getVariationId()))
            .collect(Collectors.joining(", ")) + "]:\n");
        result.append(getDifferences(outlier, transformer.getOutliersAlternatives().get(key).get(outlier).get(0), 4));
      }
    }

    return result.toString();
  }

  public String printPretty(List<EntitySubtype> subtypes)
  {
    StringBuilder result = new StringBuilder();

    for (EntitySubtype subtype : subtypes)
      result.append(printPretty(subtype));

    return result.toString();    
  }

  public String printPretty(EntitySubtype subtype)
  {
    StringBuilder result = new StringBuilder();
    result.append("Subtype:\n");
    result.append("  Variations: " + subtype.getVariations().stream().map(var -> Integer.toString(var.getVariationId())).collect(Collectors.joining(", ")) + "\n");

    if (!subtype.getIdentifiers().isEmpty())
      result.append("  Req Props : (" + subtype.getIdentifiers().stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");

    if (!subtype.getOptionals().isEmpty())
      result.append("  Opt Props : (" + subtype.getOptionals().stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");

    if (!subtype.getSchemaAdds().isEmpty())
      result.append("  Schema add: (" + subtype.getSchemaAdds().stream()
          .map(schemaAdd -> schemaAdd.getAddedProperty().getName() + " in " + schemaAdd.getFirstVariation().getVariationId())
          .collect(Collectors.joining(", ")) + ")\n");

    if (!subtype.getSchemaRemoves().isEmpty())
      result.append("  Schema rem: (" + subtype.getSchemaRemoves().stream()
          .map(schemaRem -> schemaRem.getRemovedProperty().getName() + " in " + schemaRem.getLastVariation().getVariationId())
          .collect(Collectors.joining(", ")) + ")\n");

    if (!subtype.getSchemaChanges().isEmpty())
      result.append("  Schema chg: (" + subtype.getSchemaChanges().stream()
          .map(schemaChg -> schemaChg.getRemovedProperty().getName() + " in " + schemaChg.getFirstVariation().getVariationId() + " for " +
          schemaChg.getAddedProperty().getName() + " in " + schemaChg.getSecondVariation().getVariationId())
          .collect(Collectors.joining(", ")) + ")\n");

    return result.toString();
  }

  public String getDifferences(StructuralVariation var1, StructuralVariation var2)
  {
    return getDifferences(var1, var2, 0);
  }

  public String getDifferences(StructuralVariation var1, StructuralVariation var2, int numSpaces)
  {
    StringBuilder result = new StringBuilder();
    List<Property> var1OnlyProps = new ArrayList<Property>();
    List<Property> var2OnlyProps = new ArrayList<Property>();

    var1OnlyProps = var1.getProperties().stream().filter(prop -> var2.getProperties().stream().noneMatch(prop2 -> propComparer.compare(prop, prop2))).collect(Collectors.toList());
    var2OnlyProps = var2.getProperties().stream().filter(prop -> var1.getProperties().stream().noneMatch(prop2 -> propComparer.compare(prop, prop2))).collect(Collectors.toList());

    var1OnlyProps.forEach(prop -> result.append(createStr(numSpaces) + "-   " + serializeShort(prop) + "\n"));
    var2OnlyProps.forEach(prop -> result.append(createStr(numSpaces) + "+   " + serializeShort(prop) + "\n"));

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

  private String serializeMatrixProperty(PropertyMatrix matrix, Property property, int spacing)
  {
    StringBuilder result = new StringBuilder();

    result.append(createStr(serializeShort(property), spacing));

    for (int i = 0; i < matrix.getSchemaType().getVariations().size(); i++)
    {
      StructuralVariation var = matrix.getSchemaType().getVariations().get(i);
      if (matrix.getVarsFromProperty(property).contains(var))
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

  private String serializeShort(Property property)
  {
    if (property == null)
      return null;

    String serializedProperty = Serializer.serialize(property);
    serializedProperty = serializedProperty.substring(serializedProperty.indexOf(")") + 2);

    if (property instanceof Association)
      serializedProperty = serializedProperty.substring(0, serializedProperty.indexOf("]") + 1);

    return serializedProperty;
  }
}
