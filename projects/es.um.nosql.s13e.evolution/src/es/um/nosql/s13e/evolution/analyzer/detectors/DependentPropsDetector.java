package es.um.nosql.s13e.evolution.analyzer.detectors;

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
  // <Property A>: List<Properties that appear when A does>
  private Map<Property, List<Property>> dependencyMap;
  private List<List<Property>> strongDependencies;
  private Map<Property, List<Property>> weakDependencies;
  private Map<Property, List<Property>> excludingProps;

  public DependentPropsDetector(PropertyMatrix matrix)
  {
    this.dependencyMap = checkDependencies(matrix);
    this.strongDependencies = detectStrongDependencies();
    this.weakDependencies = detectWeakDependencies();
    this.excludingProps = detectExcludingProps(matrix);
  }

  public List<List<Property>> getStrongDependencies()
  {
    return strongDependencies;
  }

  public Map<Property, List<Property>> getWeakDependencies()
  {
    return weakDependencies;
  }

  public Map<Property, List<Property>> getExcludingProps()
  {
    return excludingProps;
  }

  private Map<Property, List<Property>> checkDependencies(PropertyMatrix matrix)
  {
    Map<Property, List<Property>> dependencyMap = new HashMap<Property, List<Property>>();
    List<Property> optionalProps = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop1 : optionalProps)
    {
      List<Property> dependencies = new ArrayList<Property>();
      dependencyMap.put(prop1, dependencies);
      List<StructuralVariation> prop1Vars = matrix.getVarsFromProperty(prop1);

      dependencies.addAll(optionalProps.stream().filter(prop2 ->
      {
        return prop1 != prop2 && matrix.getVarsFromProperty(prop2).containsAll(prop1Vars);
      }).collect(Collectors.toList()));
    }

    return dependencyMap;
  }

  // Strong dependencies do exist if for all and each time a Property A appears, another Property B appears,
  // and also for all and each time Property B appears, Property A also appears.
  private List<List<Property>> detectStrongDependencies()
  {
    List<List<Property>> strongDependencies = new ArrayList<List<Property>>();

    // Initial structure is like: Property A: <List of attributes that, when they appear, Property A also appears>
    for (Property prop1 : dependencyMap.keySet())
    {
      for (Property prop2 : dependencyMap.get(prop1))
      {
        // If we already detected a strong dependency between these properties, go on.
        // If dependency list is of different sizes, there is no strong dependency.
        if (strongDependencies.stream().anyMatch(list -> list.contains(prop1) && list.contains(prop2))
            || dependencyMap.get(prop1).size() != dependencyMap.get(prop2).size())
          continue;

        // Property A: <PropB, PropC, PropD>
        // Property B: <PropA, PropC, PropD>
        // From List A, we extract PropB, then add PropA, then compare.
        List<Property> auxList = new ArrayList<Property>();
        auxList.addAll(dependencyMap.get(prop1));
        auxList.remove(prop2);
        auxList.add(prop1);

        // Once we know Props A and B are strongly dependent, we try to insert them were they belong.
        if (auxList.containsAll(dependencyMap.get(prop2)))
        {
          Optional<List<Property>> optList = strongDependencies.stream().filter(list -> list.contains(prop1)).findFirst();
          if (optList.isPresent())
            optList.get().add(prop2);
          else
            strongDependencies.add(new ArrayList<>(Arrays.asList(prop1, prop2)));
        }
      }
    }

    return strongDependencies;
  }

  private Map<Property, List<Property>> detectWeakDependencies()
  {
    Map<Property, List<Property>> weakDependencies = new HashMap<Property, List<Property>>();

    for (Property prop : dependencyMap.keySet())
    {
      // First of all, for a property, get all its dependencies.
      List<Property> dependencies = new ArrayList<Property>();
      dependencies.addAll(dependencyMap.get(prop));

      // Try to get its strong dependencies.
      Optional<List<Property>> optList = strongDependencies.stream().filter(list -> list.contains(prop)).findFirst();
      if (optList.isPresent())
        // If it has strong dependencies, we remove those from the dependency list...
        dependencies.removeAll(optList.get());

      // The remaining dependencies are weak.
      if (!dependencies.isEmpty())
        weakDependencies.put(prop, dependencies);
    }

    return weakDependencies;
  }

  private Map<Property, List<Property>> detectExcludingProps(PropertyMatrix matrix)
  {
    Map<Property, List<Property>> excludingProps = new HashMap<Property, List<Property>>();
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
        excludingProps.put(prop1, exclusions);
    }
    // Two properties are exclusive if, and only if, when one of them occurs, then the other do not occur.
    return excludingProps;
  }
}
