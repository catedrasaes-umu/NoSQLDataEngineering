package es.um.nosql.s13e.evolution.analyzer.diffs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.Serializer;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class PropertyMatrix
{
  private CompareProperty pComparer;
  private Classifier classifier;
  private Map<Property, List<StructuralVariation>> propMatrix;

  public PropertyMatrix(Classifier classifier)
  {
    this.propMatrix = new HashMap<Property, List<StructuralVariation>>();
    this.pComparer = new CompareProperty();
    this.classifier = classifier;

    createPropMatrix();
  }

  private void createPropMatrix()
  {
    classifier.getVariations().forEach(var ->
    {
      var.getProperties().forEach(prop1 ->
      {
        Optional<Property> propKey = propMatrix.keySet().stream().filter(prop2 -> pComparer.compare(prop1, prop2)).findFirst();
        if (propKey.isPresent())
          propMatrix.get(propKey.get()).add(var);
        else
        {
          List<StructuralVariation> varList = new ArrayList<StructuralVariation>();
          varList.add(var);
          propMatrix.put(prop1, varList);
        }
      });
    });
  }

  public Set<Property> getProperties()
  {
    return propMatrix.keySet();
  }

  public List<StructuralVariation> getVars()
  {
    return classifier.getVariations();
  }

  public Classifier getClassifier()
  {
    return classifier;
  }

  public List<StructuralVariation> getVarsFromProp(Property property)
  {
    return propMatrix.get(property);
  }

  public String getMatrixSummary()
  {
    StringBuilder result = new StringBuilder();

    int spacing = propMatrix.keySet().stream().mapToInt(prop ->
    {
      return serializeShort(prop).length();
    }).max().getAsInt() + 2;

    result.append(createStr(spacing));

    for (StructuralVariation var: classifier.getVariations())
      result.append(var.getVariationId() + " ");
    result.append("\n");

    result.append("Required:\n");
    for (Property prop : propMatrix.keySet().stream().filter(prop -> !prop.isOptional()).collect(Collectors.toList()))
      result.append(serializeMatrixProp(prop, spacing));

    result.append("\nOptional:\n");
    for (Property prop : propMatrix.keySet().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList()))
      result.append(serializeMatrixProp(prop, spacing));

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

  private String serializeMatrixProp(Property property, int spacing)
  {
    StringBuilder result = new StringBuilder();

    result.append(createStr(serializeShort(property), spacing));

    for (int i = 0; i < classifier.getVariations().size(); i++)
    {
      StructuralVariation var = classifier.getVariations().get(i);
      if (propMatrix.get(property).contains(var))
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
