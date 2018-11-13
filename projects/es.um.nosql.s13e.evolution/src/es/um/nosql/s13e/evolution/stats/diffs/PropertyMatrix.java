package es.um.nosql.s13e.evolution.stats.diffs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.Serializer;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class PropertyMatrix
{
  private CompareProperty pComparer;
  private Classifier classifier;
  private Map<Property, List<Integer>> propMatrix;

  public PropertyMatrix(Classifier classifier)
  {
    propMatrix = new HashMap<Property, List<Integer>>();
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
          propMatrix.get(propKey.get()).add(var.getVariationId());
        else
        {
          List<Integer> varList = new ArrayList<Integer>();
          varList.add(var.getVariationId());
          propMatrix.put(prop1, varList);
        }
      });
    });

//    ECollections.sort(classifier.getVariations(), (var1, var2) -> var1.getCount() > var2.getCount() ? -1 : 1);
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
        int varId = classifier.getVariations().get(i).getVariationId();
        if (propMatrix.get(prop).contains(varId))
          result.append("X");
        else
          result.append("-");
        result.append("   ");
        if (varId >= 10)
          result.append(" ");
        if (varId >= 100)
          result.append(" ");
      }
      result.append(endLine);
    }

    return result.toString();
  }
}
