package es.um.nosql.s13e.evolution.stats.dependencies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.stats.diffs.PropertyMatrix;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class DependencyDetector
{
  private Map<Classifier, PropertyMatrix> matrixMap;
  private Map<Classifier, List<List<Property>>> dependentProps;
  private Map<Classifier, Map<Property, List<Property>>> exclusionProps;
  private CompareProperty propComparer;

  public DependencyDetector()
  {
    matrixMap = new HashMap<Classifier, PropertyMatrix>();
    dependentProps = new HashMap<Classifier, List<List<Property>>>();
    exclusionProps = new HashMap<Classifier, Map<Property, List<Property>>>();
    propComparer = new CompareProperty();
  }

  public void analyzeSchema(NoSQLSchema schema)
  {
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      matrixMap.put(classifier, new PropertyMatrix(classifier));

    for (Classifier classifier : matrixMap.keySet())
    {
      dependentProps.put(classifier, detectDependentProps(matrixMap.get(classifier)));
      exclusionProps.put(classifier, detectExclusionProps(matrixMap.get(classifier)));
    }
    // TODO: Call each detector.
  }

  public List<List<Property>> detectDependentProps(PropertyMatrix matrix)
  {
    List<List<Property>> depProps = new ArrayList<List<Property>>();
    List<Property> optionalProperties = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    // For each property, if the variations containing it are the same that to any grouped properties, then that property is added to the grouped properties
    // If there are not coincidences, then the property will create a new group
    for (Property prop : optionalProperties)
    {
      Optional<List<Property>> optList = depProps.stream().filter(list -> matrix.getVarsFromProp(prop).equals(matrix.getVarsFromProp(list.get(0)))).findFirst();
      if (optList.isPresent())
        optList.get().add(prop);
      else
        depProps.add(new ArrayList<>(Arrays.asList(prop)));
    }

    depProps = depProps.stream().filter(innerList -> innerList.size() > 1).collect(Collectors.toList());
    // Two properties are dependent if, and only if, when one of them occurs, then the other occurs.

    return depProps;
  }

  public Map<Property, List<Property>> detectExclusionProps(PropertyMatrix matrix)
  {
    Map<Property, List<Property>> exclProps = new HashMap<Property, List<Property>>();
    List<Property> optionalProperties = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop1 : optionalProperties)
    {
      List<Property> exclusions = new ArrayList<Property>();
      List<StructuralVariation> prop1List = matrix.getVarsFromProp(prop1);

      for (Property prop2 : optionalProperties)
      {
        if (prop1 == prop2)
          continue;

        List<StructuralVariation> prop2List = matrix.getVarsFromProp(prop2);
        if (prop1List.stream().filter(prop -> prop2List.contains(prop)).count() == 0)
          exclusions.add(prop2);
      }
      if (!exclusions.isEmpty())
        exclProps.put(prop1, exclusions);
    }
    // Two properties are exclusive if, and only if, when one of them occurs, then the other do not occur.
    return exclProps;
  }

  public void detectSchemaProps()
  {
    // A property is schema changing if:
    // - When added, once it appears, then it appears on the remaining variations
    // - When deleted, once it disappears, it does not show again on any remaining variation
    // - When renamed, once it disappears, it does not show again but another property appears 
  }

  public void detectOptionalProps()
  {
    // A property is optional if it appears and dissapears without any patron.
    // Call detectSchemaProps. Get the others props.
  }

  public String getSummary()
  {
    StringBuilder result = new StringBuilder();

    for (Classifier classifier : matrixMap.keySet())
    {
      result.append(">" + classifier.getName() + "\n");
      result.append(matrixMap.get(classifier).getMatrixSummary() + "\n");

      if (!dependentProps.get(classifier).isEmpty())
      {
        result.append("Dependent properties:\n");
        for (List<Property> groupedProps : dependentProps.get(classifier))
          result.append("\t(" + groupedProps.stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");

        result.append("\n");
      }
      if (!exclusionProps.get(classifier).isEmpty())
      {
        result.append("Exclusion properties:\n");
        for (Property exclusionProp : exclusionProps.get(classifier).keySet())
          result.append("\t(" + exclusionProp.getName() + ": " + exclusionProps.get(classifier).get(exclusionProp).stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");

        result.append("\n");
      }
      result.append("\n");
    }

    return result.toString();
  }
}
