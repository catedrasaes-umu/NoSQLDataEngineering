package es.um.unosql.subtypes.util.types.changes;

import es.um.unosql.uNoSQLSchema.Feature;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.utils.compare.CompareFeature;

public class SchemaChange extends AbstractSchemaChange
{
  private Feature featRemove;

  private Feature featAdd;

  private StructuralVariation variation1;

  private StructuralVariation variation2;

  private long timestamp1;

  private long timestamp2;

  private CompareFeature comparer;

  public SchemaChange(SchemaRemove schemaRemove, SchemaAdd schemaAdd)
  {
    this(schemaRemove.getRemovedFeature(), schemaRemove.getLastVariation(), schemaAdd.getAddedFeature(), schemaAdd.getFirstVariation());
  }

  public SchemaChange(Feature featRemove, StructuralVariation variation1, Feature featAdd, StructuralVariation variation2)
  {
    this.featRemove = featRemove;
    this.variation1 = variation1;
    this.featAdd = featAdd;
    this.variation2 = variation2;
    this.timestamp1 = variation1.getFirstTimestamp();
    this.timestamp2 = variation2.getFirstTimestamp();
    this.comparer = new CompareFeature();
  }

  public Feature getRemovedFeature()
  {
    return featRemove;
  }

  public Feature getAddedFeature()
  {
    return featAdd;
  }

  public StructuralVariation getFirstVariation()
  {
    return variation1;
  }

  public StructuralVariation getSecondVariation()
  {
    return variation2;
  }

  public long getFirstTimestamp()
  {
    return timestamp1;
  }

  public long getSecondTimestamp()
  {
    return timestamp2;
  }

  public boolean isRename()
  {
    return !comparer.compareName(featAdd, featRemove)
      && comparer.compareType(featAdd, featRemove);
  }

  public boolean isTypeChange()
  {
    return comparer.compareName(featAdd, featRemove)
      && !comparer.compareType(featAdd, featRemove);
  }

  public boolean isIndependent()
  {
    return !isRename() && !isTypeChange();
  }
}
