package es.um.nosql.s13e.evolution.analyzer.diffs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
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

  public Classifier getClassifier()
  {
    return classifier;
  }

  public Set<Property> getProperties()
  {
    return propMatrix.keySet();
  }

  public List<StructuralVariation> getVars()
  {
    return classifier.getVariations();
  }

  public List<StructuralVariation> getVarsFromProperty(Property property)
  {
    return propMatrix.get(property);
  }
}
