package es.um.nosql.s13e.evolution.analyzer.dependencies.detectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;

public class DependentPropsDetector
{
  private List<List<Property>> dependentProps;
  private Map<Property, List<Property>> exclusionProps;

  public DependentPropsDetector(PropertyMatrix matrix)
  {
    this.dependentProps = detectDependentProps(matrix);
    this.exclusionProps = detectExclusionProps(matrix);
  }

  public List<List<Property>> getDependentProps()
  {
    return dependentProps;
  }

  public Map<Property, List<Property>> getExclusionProps()
  {
    return exclusionProps;
  }

  private List<List<Property>> detectDependentProps(PropertyMatrix matrix)
  {
    List<List<Property>> depProps = new ArrayList<List<Property>>();
    List<Property> optionalProps = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    // For each property, if the variations containing it are the same that to any grouped properties, then that property is added to the grouped properties
    // If there are not coincidences, then the property will create a new group
    for (Property prop : optionalProps)
    {
      Optional<List<Property>> optList = depProps.stream().filter(list -> matrix.getVarsFromProperty(prop).equals(matrix.getVarsFromProperty(list.get(0)))).findFirst();
      if (optList.isPresent())
        optList.get().add(prop);
      else
        depProps.add(new ArrayList<>(Arrays.asList(prop)));
    }

    depProps = depProps.stream().filter(innerList -> innerList.size() > 1).collect(Collectors.toList());
    // Two properties are dependent if, and only if, when one of them occurs, then the other occurs.

    return depProps;
  }

  private Map<Property, List<Property>> detectExclusionProps(PropertyMatrix matrix)
  {
    Map<Property, List<Property>> exclProps = new HashMap<Property, List<Property>>();
    List<Property> optionalProps = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop1 : optionalProps)
    {
      List<Property> exclusions = new ArrayList<Property>();
      List<StructuralVariation> prop1List = matrix.getVarsFromProperty(prop1);

      for (Property prop2 : optionalProps)
      {
        if (prop1 == prop2)
          continue;

        List<StructuralVariation> prop2List = matrix.getVarsFromProperty(prop2);
        if (prop1List.stream().filter(prop -> prop2List.contains(prop)).count() == 0)
          exclusions.add(prop2);
      }
      if (!exclusions.isEmpty())
        exclProps.put(prop1, exclusions);
    }
    // Two properties are exclusive if, and only if, when one of them occurs, then the other do not occur.
    return exclProps;
  }
}
