package es.um.nosql.s13e.evolution.types.changes;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class SchemaRemove extends AbstractSchemaChange
{
  private Property propertyRemove;

  private StructuralVariation variation;

  private long timestamp;

  public SchemaRemove(Property propertyRemove, StructuralVariation variation)
  {
    this.propertyRemove = propertyRemove;
    this.variation = variation;
    timestamp = variation.getFirstTimestamp();
  }

  public Property getRemovedProperty()
  {
    return propertyRemove;
  }

  public StructuralVariation getLastVariation()
  {
    return variation;
  }

  public long getLastTimestamp()
  {
    return timestamp;
  }
}
