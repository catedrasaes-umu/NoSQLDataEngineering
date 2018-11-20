package es.um.nosql.s13e.evolution.stats.diffs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
    propMatrix = new HashMap<Property, List<StructuralVariation>>();
    pComparer = new CompareProperty();
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

  public List<StructuralVariation> getVarsFromProp(Property property)
  {
    return propMatrix.get(property);
  }

  public String getMatrixSummary()
  {
    StringBuilder result = new StringBuilder();
    String endLine = "\n";
    String tab = "\t";

    result.append(tab + tab + tab + tab + tab + tab);

    for (StructuralVariation var: classifier.getVariations())
      result.append("_" + var.getVariationId() + "  ");
    result.append(endLine);

    for (Property prop : propMatrix.keySet())
    {
      result.append(Serializer.serialize(prop) + tab + tab);

      for (int i = 0; i < classifier.getVariations().size(); i++)
      {
        StructuralVariation var = classifier.getVariations().get(i);
        if (propMatrix.get(prop).contains(var))
          result.append("X");
        else
          result.append("-");
        result.append("   ");
        if (var.getVariationId() >= 10)
          result.append(" ");
        if (var.getVariationId() >= 100)
          result.append(" ");
      }
      result.append(endLine);
    }

    return result.toString();
  }
}
