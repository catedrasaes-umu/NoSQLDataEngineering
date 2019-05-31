package es.um.nosql.s13e.evolution.types.changes;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class SchemaChange extends AbstractSchemaChange
{
  private Property propertyRemove;

  private Property propertyAdd;

  private StructuralVariation variation1;

  private StructuralVariation variation2;

  private long timestamp1;

  private long timestamp2;

  private CompareProperty comparer;

  public SchemaChange(SchemaRemove schemaRemove, SchemaAdd schemaAdd)
  {
    this(schemaRemove.getRemovedProperty(), schemaRemove.getLastVariation(), schemaAdd.getAddedProperty(), schemaAdd.getFirstVariation());
  }

  public SchemaChange(Property propertyRemove, StructuralVariation variation1, Property propertyAdd, StructuralVariation variation2)
  {
    this.propertyRemove = propertyRemove;
    this.variation1 = variation1;
    this.propertyAdd = propertyAdd;
    this.variation2 = variation2;
    this.timestamp1 = variation1.getFirstTimestamp();
    this.timestamp2 = variation2.getFirstTimestamp();
    this.comparer = new CompareProperty();
  }

  public Property getRemovedProperty()
  {
    return propertyRemove;
  }

  public Property getAddedProperty()
  {
    return propertyAdd;
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
    return !comparer.compareName(propertyAdd, propertyRemove)
      && comparer.compareType(propertyAdd, propertyRemove);
  }

  public boolean isTypeChange()
  {
    return comparer.compareName(propertyAdd, propertyRemove)
      && !comparer.compareType(propertyAdd, propertyRemove);
  }

  public boolean isIndependent()
  {
    return !isRename() && !isTypeChange();
  }
}
