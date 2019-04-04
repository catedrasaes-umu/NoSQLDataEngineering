package es.um.nosql.s13e.evolution.util;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.DependencyAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.detectors.DependentPropsDetector;
import es.um.nosql.s13e.evolution.analyzer.detectors.SchemaChangeDetector;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;
import es.um.nosql.s13e.util.Serializer;

public class EvolutionPrinter
{
  public String printPretty(DependencyAnalyzer detector)
  {
    StringBuilder result = new StringBuilder();

    result.append("> " + detector.getSchemaType().getName() + "\n");
    result.append(printPretty(detector.getPropertyMatrix()));
    result.append(printPretty(detector.getDependentProps()));
//    result.append(printPretty(detector.getSchemaChanges()));
  
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

  public String printPretty(SchemaChangeDetector sChanges)
  {
    StringBuilder result = new StringBuilder();

    if (!sChanges.getSchemaAddProps().isEmpty())
      result.append("Schema add properties:\n  (" + sChanges.getSchemaAddProps().stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n\n");

    if (!sChanges.getSchemaRemoveProps().isEmpty())
      result.append("Schema remove properties:\n  (" + sChanges.getSchemaRemoveProps().stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n\n");

    if (!sChanges.getSchemaRenameProps().isEmpty())
    {
      result.append("Schema rename properties:\n");
      for (Entry<Property, Property> entry : sChanges.getSchemaRenameProps().entrySet())
        result.append("  (" + entry.getKey().getName() + ": " + entry.getValue().getName() + ")\n");
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
