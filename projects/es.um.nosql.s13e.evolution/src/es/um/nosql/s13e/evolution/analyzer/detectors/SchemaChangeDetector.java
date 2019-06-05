package es.um.nosql.s13e.evolution.analyzer.detectors;

import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.types.EntitySubtype;
import es.um.nosql.s13e.evolution.types.changes.SchemaAdd;
import es.um.nosql.s13e.evolution.types.changes.SchemaChange;
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
      detectSchemaAdds(subtype);
      detectSchemaRemoves(subtype);
      detectSchemaChanges(subtype);
    }
  }

  private void detectSchemaAdds(EntitySubtype subtype)
  {
    boolean itAppears = false;

    for (Property optional : subtype.getSubtypeOptionalProps())
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
        subtype.addSchemaAdd(new SchemaAdd(optional, subtype.getVariations().stream()
            .filter(var -> var.getProperties().stream().anyMatch(prop -> pComparer.compare(prop, optional))).findFirst().get()));
    }
  }

  private void detectSchemaRemoves(EntitySubtype subtype)
  {
    boolean itDissapears = false;

    for (Property optional : subtype.getSubtypeOptionalProps())
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
      {
        subtype.addSchemaRemove(new SchemaRemove(optional, subtype.getVariations().stream()
            .filter(var -> var.getProperties().stream().anyMatch(prop -> pComparer.compare(prop,  optional)))
            .reduce((var1, var2) -> var1.getVariationId() > var2.getVariationId() ? var1 : var2).get()));
      }
    }
  }

  private void detectSchemaChanges(EntitySubtype subtype)
  {
    for (SchemaRemove schemaRem : subtype.getSchemaRemoves())
    {
      int index = subtype.getVariations().indexOf(schemaRem.getLastVariation());
      if (subtype.getVariations().size() > index)
        for (SchemaAdd schemaAdd : subtype.getSchemaAdds().stream().filter(schAdd -> schAdd.getFirstVariation() == subtype.getVariations().get(index + 1)).collect(Collectors.toList()))
          subtype.addSchemaChange(new SchemaChange(schemaRem, schemaAdd));
    }
  }
}
