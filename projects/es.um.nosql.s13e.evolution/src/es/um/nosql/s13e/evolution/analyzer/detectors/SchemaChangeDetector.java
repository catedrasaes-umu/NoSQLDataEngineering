package es.um.nosql.s13e.evolution.analyzer.detectors;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.types.EntitySubtype;
import es.um.nosql.s13e.evolution.types.changes.SchemaAdd;
import es.um.nosql.s13e.evolution.types.changes.SchemaRemove;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class SchemaChangeDetector
{
  private CompareProperty pComparer;

  public SchemaChangeDetector(List<EntitySubtype> subtypes)
  {
    pComparer = new CompareProperty();

    for (EntitySubtype subtype : subtypes)
    {
      detectSchemaAddProps(subtype);
      detectSchemaRemoveProps(subtype);
      detectSchemaRenameProps(subtype);
    }
  }

  private void detectSchemaAddProps(EntitySubtype subtype)
  {
    boolean itAppears = false;

    for (Property optional : subtype.getOptionals())
    {
      for (StructuralVariation variation : subtype.getVariations())
        if (variation.getProperties().stream().anyMatch(prop -> pComparer.compare(prop, optional)))
          itAppears = true;
        else if (itAppears)
        {
          itAppears = false;
          break;
        }
      if (itAppears)
        subtype.addSchemaAdd(new SchemaAdd(optional, subtype.getVariations().stream().filter(var -> var.getProperties().contains(optional)).findFirst().get()));
    }
  }

  private void detectSchemaRemoveProps(EntitySubtype subtype)
  {
    boolean itDissapears = false;

    for (Property optional : subtype.getOptionals())
    {
      for (StructuralVariation variation : subtype.getVariations())
        if (variation.getProperties().stream().noneMatch(prop -> pComparer.compare(prop, optional)))
          itDissapears = true;
        else if (itDissapears)
        {
          itDissapears = false;
          break;
        }
      if (itDissapears)
        subtype.addSchemaRemove(new SchemaRemove(optional, subtype.getVariations().stream().filter(var -> var.getProperties().contains(optional)).reduce((var1, var2) -> var2).get()));
    }
  }

  private void detectSchemaRenameProps(EntitySubtype subtype)
  {
    //TODO: Schema rename. No lo tengo muy claro...
  }

  /*
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
  }*/
}
