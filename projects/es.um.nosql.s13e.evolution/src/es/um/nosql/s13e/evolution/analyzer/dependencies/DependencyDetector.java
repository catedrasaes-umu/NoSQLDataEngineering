package es.um.nosql.s13e.evolution.analyzer.dependencies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;

public class DependencyDetector
{
  private Classifier classifier;
  private PropertyMatrix matrixMap;
  private List<List<Property>> dependentProps;
  private Map<Property, List<Property>> exclusionProps;
  private List<Property> schemaAddProps;
  private List<Property> schemaRemoveProps;
  private Map<Property, Property> schemaRenameProps;
  private List<Property> optionalProps;

  public DependencyDetector(Classifier classifier)
  {
    this.classifier = classifier;
    this.matrixMap = new PropertyMatrix(classifier);

    this.dependentProps = detectDependentProps();
    this.exclusionProps = detectExclusionProps();
    this.schemaAddProps = detectSchemaAddProps();
    this.schemaRemoveProps = detectSchemaRemoveProps();
    this.schemaRenameProps = detectSchemaRenameProps();
    this.optionalProps = detectOptionalProps();
  }

  public List<List<Property>> getDependentProps()
  {
    return dependentProps;
  }

  public Map<Property, List<Property>> exclusionProps()
  {
    return exclusionProps;
  }

  public List<Property> getSchemaAddProps()
  {
    return schemaAddProps;
  }

  public List<Property> getSchemaRemoveProps()
  {
    return schemaRemoveProps;
  }

  public Map<Property, Property> getSchemaRenameProps()
  {
    return schemaRenameProps;
  }

  public List<Property> getOptionalProps()
  {
    return optionalProps;
  }

  private List<List<Property>> detectDependentProps()
  {
    List<List<Property>> depProps = new ArrayList<List<Property>>();
    List<Property> optionalProps = matrixMap.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    // For each property, if the variations containing it are the same that to any grouped properties, then that property is added to the grouped properties
    // If there are not coincidences, then the property will create a new group
    for (Property prop : optionalProps)
    {
      Optional<List<Property>> optList = depProps.stream().filter(list -> matrixMap.getVarsFromProp(prop).equals(matrixMap.getVarsFromProp(list.get(0)))).findFirst();
      if (optList.isPresent())
        optList.get().add(prop);
      else
        depProps.add(new ArrayList<>(Arrays.asList(prop)));
    }

    depProps = depProps.stream().filter(innerList -> innerList.size() > 1).collect(Collectors.toList());
    // Two properties are dependent if, and only if, when one of them occurs, then the other occurs.

    return depProps;
  }

  private Map<Property, List<Property>> detectExclusionProps()
  {
    Map<Property, List<Property>> exclProps = new HashMap<Property, List<Property>>();
    List<Property> optionalProps = matrixMap.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop1 : optionalProps)
    {
      List<Property> exclusions = new ArrayList<Property>();
      List<StructuralVariation> prop1List = matrixMap.getVarsFromProp(prop1);

      for (Property prop2 : optionalProps)
      {
        if (prop1 == prop2)
          continue;

        List<StructuralVariation> prop2List = matrixMap.getVarsFromProp(prop2);
        if (prop1List.stream().filter(prop -> prop2List.contains(prop)).count() == 0)
          exclusions.add(prop2);
      }
      if (!exclusions.isEmpty())
        exclProps.put(prop1, exclusions);
    }
    // Two properties are exclusive if, and only if, when one of them occurs, then the other do not occur.
    return exclProps;
  }

  private List<Property> detectSchemaAddProps()
  {
    List<Property> schemaAddProps = new ArrayList<Property>();
    List<Property> optionalProps = matrixMap.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop : optionalProps)
    {
      // Check if the ids are continuous. If they are not, this is not a schema change.
      Integer[] ids = matrixMap.getVarsFromProp(prop).stream().map(var -> var.getVariationId()).toArray(Integer[]::new);
      boolean continuous = true;
      for (int i = 0; i < ids.length - 1 && continuous; i++)
        if (ids[i] + 1 != ids[i + 1])
          continuous = false;
      if (!continuous)
        continue;

      int lastPropVarId = matrixMap.getVarsFromProp(prop).get(matrixMap.getVarsFromProp(prop).size() - 1).getVariationId();
      int lastVarId = matrixMap.getVars().get(matrixMap.getVars().size() - 1).getVariationId();

      // If last propVarId is the same as lastVarId, then this property appears on the last variation.
      if (lastPropVarId == lastVarId)
        schemaAddProps.add(prop);
    }
    // Once a property appears, it should ALWAYS appear yo be a schema change.
    // Thing is, a property might have been optional at the beginning and then being changed to a schema changing add....
    // TODO: Need to test.

    return schemaAddProps;
  }

  private List<Property> detectSchemaRemoveProps()
  {
    List<Property> schemaAddProps = new ArrayList<Property>();
    List<Property> optionalProps = matrixMap.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop : optionalProps)
    {
      // Check if the ids are continuous. If they are not, this is not a schema change.
      Integer[] ids = matrixMap.getVarsFromProp(prop).stream().map(var -> var.getVariationId()).toArray(Integer[]::new);
      boolean continuous = true;
      for (int i = 0; i < ids.length - 1 && continuous; i++)
        if (ids[i] + 1 != ids[i + 1])
          continuous = false;
      if (!continuous)
        continue;

      int lastPropVarId = matrixMap.getVarsFromProp(prop).get(matrixMap.getVarsFromProp(prop).size() - 1).getVariationId();
      int lastVarId = matrixMap.getVars().get(matrixMap.getVars().size() - 1).getVariationId();

      // If last propVarId is not equal as lastVarId, then this property does not appear on the last variation.
      if (lastPropVarId != lastVarId)
        schemaAddProps.add(prop);
    }
    // Once a property disappears, it should NEVER show again.
    // Thing is, a property might have been optional at the beginning and then being changed to a schema changing remove....
    // TODO: Need to test.

    return schemaAddProps;
  }

  private Map<Property, Property> detectSchemaRenameProps()
  {
    Map<Property, Property> schemaRenameProps = new HashMap<Property, Property>();

    // For each schema removal property, check if another property was added the next variation.
    for (Property removedProp : schemaRemoveProps)
    {
      List<StructuralVariation> removedPropVars = matrixMap.getVarsFromProp(removedProp);
      int varId = removedPropVars.get(removedPropVars.size() - 1).getVariationId() + 1;
      Optional<Property> optionalRename = schemaAddProps.stream().filter(prop ->
      {
        List<StructuralVariation> addedPropVars = matrixMap.getVarsFromProp(prop);
        return addedPropVars.get(0).getVariationId() == varId;
      }).findAny();

      if (optionalRename.isPresent())
        schemaRenameProps.put(removedProp, optionalRename.get());
    }
    //TODO: Need to test

    return schemaRenameProps;
  }

  private List<Property> detectOptionalProps()
  {
    List<Property> filteredOptionalProps = new ArrayList<Property>();
    List<Property> optionalProps = matrixMap.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    filteredOptionalProps.addAll(optionalProps.stream().filter(prop ->
      !schemaAddProps.contains(prop) && !schemaRemoveProps.contains(prop)).collect(Collectors.toList()));

    return filteredOptionalProps;
  }

  public String getSummary()
  {
    StringBuilder result = new StringBuilder();

    result.append(">" + classifier.getName() + "\n");
    result.append(matrixMap.getMatrixSummary() + "\n");

    if (!dependentProps.isEmpty())
    {
      result.append("Dependent properties:\n");
      for (List<Property> groupedProps : dependentProps)
        result.append("\t(" + groupedProps.stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }
    if (!exclusionProps.isEmpty())
    {
      result.append("Exclusion properties:\n");
      for (Property exclusionProp : exclusionProps.keySet())
        result.append("\t(" + exclusionProp.getName() + ": " + exclusionProps.get(exclusionProp).stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }
    if (!schemaAddProps.isEmpty())
    {
      result.append("Schema add properties:\n");
      result.append("\t(" + schemaAddProps.stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }
    if (!schemaRemoveProps.isEmpty())
    {
      result.append("Schema remove properties:\n");
      result.append("\t(" + schemaRemoveProps.stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }
    if (!schemaRenameProps.isEmpty())
    {
      result.append("Schema rename properties:\n");
      for (Entry<Property, Property> entry : schemaRenameProps.entrySet())
        result.append("\t(" + entry.getKey().getName() + ": " + entry.getValue().getName() + ")\n");
      result.append("\n");
    }
    if (!optionalProps.isEmpty())
    {
      result.append("Optional properties:\n");
      result.append("\t(" + optionalProps.stream().map(prop -> prop.getName()).collect(Collectors.joining(", ")) + ")\n");
      result.append("\n");
    }
    result.append("\n");

    return result.toString();
  }
}
