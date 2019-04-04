package es.um.nosql.s13e.evolution.types.changes;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class SchemaAdd extends AbstractSchemaChange
{
  private Property propertyAdd;

  private StructuralVariation variation;

  private long timestamp;

  public SchemaAdd(Property propertyAdd, StructuralVariation variation)
  {
    this.propertyAdd = propertyAdd;
    this.variation = variation;
    timestamp = variation.getFirstTimestamp();
  }

  public Property getAddedProperty()
  {
    return propertyAdd;
  }

  public StructuralVariation getFirstVariation()
  {
    return variation;
  }

  public long getFirstTimestamp()
  {
    return timestamp;
  }
}
