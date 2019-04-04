package es.um.nosql.s13e.evolution.analyzer.detectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;

public class SchemaChangeDetector
{
  private List<Property> schemaAddProps;
  private List<Property> schemaRemoveProps;
  private Map<Property, Property> schemaRenameProps;

  public SchemaChangeDetector(PropertyMatrix matrix)
  {
    this.schemaAddProps = detectSchemaAddProps(matrix);
    this.schemaRemoveProps = detectSchemaRemoveProps(matrix);
    this.schemaRenameProps = detectSchemaRenameProps(matrix);
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

  private List<Property> detectSchemaAddProps(PropertyMatrix matrix)
  {
    List<Property> schemaAddProps = new ArrayList<Property>();
    List<Property> optionalProps = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop : optionalProps)
    {
      // Check if the ids are continuous. If they are not, this is not a schema change.
      Integer[] ids = matrix.getVarsFromProperty(prop).stream().map(var -> var.getVariationId()).toArray(Integer[]::new);
      boolean continuous = true;
      for (int i = 0; i < ids.length - 1 && continuous; i++)
        if (ids[i] + 1 != ids[i + 1])
          continuous = false;
      if (!continuous)
        continue;

      int lastPropVarId = matrix.getVarsFromProperty(prop).get(matrix.getVarsFromProperty(prop).size() - 1).getVariationId();
      int lastVarId = matrix.getVars().get(matrix.getVars().size() - 1).getVariationId();

      // If last propVarId is the same as lastVarId, then this property appears on the last variation.
      if (lastPropVarId == lastVarId)
        schemaAddProps.add(prop);
    }
    // Once a property appears, it should ALWAYS appear to be a schema change.
    // Thing is, a property might have been optional at the beginning and then being changed to a schema changing add....
    // TODO: Need to test.

    return schemaAddProps;
  }

  private List<Property> detectSchemaRemoveProps(PropertyMatrix matrix)
  {
    List<Property> schemaAddProps = new ArrayList<Property>();
    List<Property> optionalProps = matrix.getProperties().stream().filter(prop -> prop.isOptional()).collect(Collectors.toList());

    for (Property prop : optionalProps)
    {
      // Check if the ids are continuous. If they are not, this is not a schema change.
      Integer[] ids = matrix.getVarsFromProperty(prop).stream().map(var -> var.getVariationId()).toArray(Integer[]::new);
      boolean continuous = true;
      for (int i = 0; i < ids.length - 1 && continuous; i++)
        if (ids[i] + 1 != ids[i + 1])
          continuous = false;
      if (!continuous)
        continue;

      int lastPropVarId = matrix.getVarsFromProperty(prop).get(matrix.getVarsFromProperty(prop).size() - 1).getVariationId();
      int lastVarId = matrix.getVars().get(matrix.getVars().size() - 1).getVariationId();

      // If last propVarId is not equal as lastVarId, then this property does not appear on the last variation.
      if (lastPropVarId != lastVarId)
        schemaAddProps.add(prop);
    }
    // Once a property disappears, it should NEVER show again.
    // Thing is, a property might have been optional at the beginning and then being changed to a schema changing remove....
    // TODO: Need to test.

    return schemaAddProps;
  }

  private Map<Property, Property> detectSchemaRenameProps(PropertyMatrix matrix)
  {
    Map<Property, Property> schemaRenameProps = new HashMap<Property, Property>();

    // For each schema removal property, check if another property was added the next variation.
    for (Property removedProp : schemaRemoveProps)
    {
      List<StructuralVariation> removedPropVars = matrix.getVarsFromProperty(removedProp);
      int varId = removedPropVars.get(removedPropVars.size() - 1).getVariationId() + 1;
      Optional<Property> optionalRename = schemaAddProps.stream().filter(prop ->
      {
        List<StructuralVariation> addedPropVars = matrix.getVarsFromProperty(prop);
        return addedPropVars.get(0).getVariationId() == varId;
      }).findAny();

      if (optionalRename.isPresent())
        schemaRenameProps.put(removedProp, optionalRename.get());
    }
    //TODO: Need to test

    return schemaRenameProps;
  }
}
