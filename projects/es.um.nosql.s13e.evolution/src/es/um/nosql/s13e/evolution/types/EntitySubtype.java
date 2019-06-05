package es.um.nosql.s13e.evolution.types;

import java.util.ArrayList;
import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.types.changes.SchemaAdd;
import es.um.nosql.s13e.evolution.types.changes.SchemaChange;
import es.um.nosql.s13e.evolution.types.changes.SchemaRemove;

public class EntitySubtype
{
  List<StructuralVariation> variations;
  private List<Property> subtypeRequiredProps;
  private List<Property> subtypeOptionalProps;
  private List<SchemaAdd> schemaAdds;
  private List<SchemaRemove> schemaRemoves;
  private List<SchemaChange> schemaChanges;

  public EntitySubtype(List<StructuralVariation> variations, List<Property> subtypeRequiredProps, List<Property> subtypeOptionalProps)
  {
    this.variations = variations;
    this.subtypeRequiredProps = subtypeRequiredProps;
    this.subtypeOptionalProps = subtypeOptionalProps;
    this.schemaAdds = new ArrayList<SchemaAdd>();
    this.schemaRemoves = new ArrayList<SchemaRemove>();
    this.schemaChanges = new ArrayList<SchemaChange>();
  }

  public List<StructuralVariation> getVariations()
  {
    return variations;
  }

  public List<Property> getSubtypeRequiredProps()
  {
    return subtypeRequiredProps;
  }

  public List<Property> getSubtypeOptionalProps()
  {
    return subtypeOptionalProps;
  }

  public List<SchemaAdd> getSchemaAdds()
  {
    return schemaAdds;
  }

  public List<SchemaRemove> getSchemaRemoves()
  {
    return schemaRemoves;
  }

  public List<SchemaChange> getSchemaChanges()
  {
    return schemaChanges;
  }

  public void addSchemaAdd(SchemaAdd schemaAdd)
  {
    schemaAdds.add(schemaAdd);
  }

  public void addSchemaRemove(SchemaRemove schemaRemove)
  {
    schemaRemoves.add(schemaRemove);
  }

  public void addSchemaChange(SchemaChange schemaChange)
  {
    schemaChanges.add(schemaChange);
  }
}
