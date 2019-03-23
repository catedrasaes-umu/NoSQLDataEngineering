package regression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.json2dbschema.m2m.NoSQLSchemaToDocumentDb;
import es.um.nosql.s13e.util.NoSQLSchemaPrinter;

public class RelationshipTypeToEntityTypeTest
{
  private NoSQLSchemaFactory factory;
  private NoSQLSchemaToDocumentDb schema2DDb;
  private NoSQLSchemaPrinter printer;

  @Before
  public void setUp()
  {
    factory = NoSQLSchemaFactory.eINSTANCE;
    schema2DDb = new NoSQLSchemaToDocumentDb();
    printer = new NoSQLSchemaPrinter();
  }

  @Test
  public void testRelationshipType()
  {
    RelationshipType refClass = createRelType("class1", 3);
    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRelationships().add(refClass);

    schema2DDb.adaptToDocumentDb(schema);

    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(1, schema.getEntities().size());
    assertEquals(3, schema.getEntities().get(0).getVariations().size());
  }

  @Test
  public void testRelationshipTypeCollision()
  {
    RelationshipType refClass = createRelType("class1", 15);
    EntityType entityType = createEntityType("Ref_Class1", 4);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRelationships().add(refClass);
    schema.getEntities().add(entityType);

    schema2DDb.adaptToDocumentDb(schema);

    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(1, schema.getEntities().size());
    assertEquals(15, schema.getEntities().get(0).getVariations().size());
  }

  @Test
  public void testFixReferences()
  {
    RelationshipType refClass = createRelType("relClass", 1);
    EntityType entity1 = createEntityType("entity1", 1);
    EntityType entity2 = createEntityType("entity2", 1);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRelationships().add(refClass);
    schema.getEntities().add(entity1); schema.getEntities().add(entity2);

    Reference ref = factory.createReference();
    ref.setName("theReference");
    ref.setLowerBound(1);
    ref.setUpperBound(2);
    ref.setRefsTo(entity2);
    ref.getFeatures().add(refClass.getVariations().get(0));
    entity1.getVariations().get(0).getProperties().add(ref);

    schema2DDb.adaptToDocumentDb(schema);

    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(3, schema.getEntities().size());
    assertTrue(schema.getEntities().get(0).getVariations().get(0).getProperties().stream().noneMatch(prop -> {return prop instanceof Aggregate;}));
  }

  @Test
  public void testFixReferencesAndCollision()
  {
    RelationshipType refClass = createRelType("relClass", 1);
    EntityType entity1 = createEntityType("entity1", 1);
    EntityType entity2 = createEntityType("entity2", 1);
    EntityType entity3 = createEntityType("Ref_Relclass", 3);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRelationships().add(refClass);
    schema.getEntities().add(entity1); schema.getEntities().add(entity2); schema.getEntities().add(entity3);

    Reference ref = factory.createReference();
    ref.setName("theReference");
    ref.setLowerBound(1);
    ref.setUpperBound(2);
    ref.setRefsTo(entity2);
    ref.getFeatures().add(refClass.getVariations().get(0));
    entity1.getVariations().get(0).getProperties().add(ref);

    System.out.println(printer.printPretty(schema));
    schema2DDb.adaptToDocumentDb(schema);
    System.out.println(printer.printPretty(schema));
  }

  private RelationshipType createRelType(String name, int variations)
  {
    RelationshipType refClass = factory.createRelationshipType();
    refClass.setName(name);

    for (int i = 1; i <= variations; i++)
    {
      StructuralVariation var = factory.createStructuralVariation();
      var.setVariationId(i);
      refClass.getVariations().add(var);

      PrimitiveType pType = factory.createPrimitiveType(); pType.setName("string");
      Attribute attr = factory.createAttribute(); attr.setName("attr" + i); attr.setType(pType);
      var.getProperties().add(attr);
    }

    return refClass;
  }

  private EntityType createEntityType(String name, int variations)
  {
    EntityType entity = factory.createEntityType();
    entity.setName(name);

    for (int i = 1; i <= variations; i++)
    {
      StructuralVariation var = factory.createStructuralVariation();
      var.setVariationId(i);
      entity.getVariations().add(var);

      PrimitiveType pType = factory.createPrimitiveType(); pType.setName("string");
      Attribute attr = factory.createAttribute(); attr.setName("attr" + i); attr.setType(pType);
      var.getProperties().add(attr);
    }

    return entity;
  }
}
