package es.um.unosql.subtypes.util.types.changes;

import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;

public class SchemaAdd extends AbstractSchemaChange
{
  private Feature featAdd;

  private StructuralVariation variation;

  private long timestamp;

  public SchemaAdd(Feature featAdd, StructuralVariation variation)
  {
    this.featAdd = featAdd;
    this.variation = variation;
    this.timestamp = variation.getFirstTimestamp();
  }

  public Feature getAddedFeature()
  {
    return featAdd;
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
