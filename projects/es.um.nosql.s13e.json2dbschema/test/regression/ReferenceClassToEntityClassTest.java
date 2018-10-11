package regression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.json2dbschema.m2m.NoSQLSchemaToDocumentDb;
import es.um.nosql.s13e.util.NoSQLSchemaPrinter;

public class ReferenceClassToEntityClassTest
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
  public void testReferenceClass()
  {
    ReferenceClass refClass = createRefClass("class1", 3);
    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRefClasses().add(refClass);

    schema2DDb.adaptToDocumentDb(schema);

    assertTrue(schema.getRefClasses().isEmpty());
    assertEquals(1, schema.getEntities().size());
    assertEquals(3, schema.getEntities().get(0).getVariations().size());
  }

  @Test
  public void testReferenceClassCollision()
  {
    ReferenceClass refClass = createRefClass("class1", 15);
    EntityClass entityClass = createEntityClass("Ref_Class1", 4);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRefClasses().add(refClass);
    schema.getEntities().add(entityClass);

    schema2DDb.adaptToDocumentDb(schema);

    assertTrue(schema.getRefClasses().isEmpty());
    assertEquals(1, schema.getEntities().size());
    assertEquals(15, schema.getEntities().get(0).getVariations().size());
  }

  @Test
  public void testFixReferences()
  {
    ReferenceClass refClass = createRefClass("refClass", 1);
    EntityClass entityClass1 = createEntityClass("entityClass1", 1);
    EntityClass entityClass2 = createEntityClass("entityClass2", 1);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRefClasses().add(refClass);
    schema.getEntities().add(entityClass1); schema.getEntities().add(entityClass2);

    Reference ref = factory.createReference();
    ref.setName("theReference");
    ref.setLowerBound(1);
    ref.setUpperBound(2);
    ref.setRefsTo(entityClass2);
    ref.setFeatures(refClass.getVariations().get(0));
    entityClass1.getVariations().get(0).getProperties().add(ref);

    schema2DDb.adaptToDocumentDb(schema);

    assertTrue(schema.getRefClasses().isEmpty());
    assertEquals(3, schema.getEntities().size());
    assertTrue(schema.getEntities().get(0).getVariations().get(0).getProperties().stream().noneMatch(prop -> {return prop instanceof Reference;}));
  }

  @Test
  public void testFixReferencesAndCollision()
  {
    ReferenceClass refClass = createRefClass("refClass", 1);
    EntityClass entityClass1 = createEntityClass("entityClass1", 1);
    EntityClass entityClass2 = createEntityClass("entityClass2", 1);
    EntityClass entityClass3 = createEntityClass("Ref_Refclass", 3);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRefClasses().add(refClass);
    schema.getEntities().add(entityClass1); schema.getEntities().add(entityClass2); schema.getEntities().add(entityClass3);

    Reference ref = factory.createReference();
    ref.setName("theReference");
    ref.setLowerBound(1);
    ref.setUpperBound(2);
    ref.setRefsTo(entityClass2);
    ref.setFeatures(refClass.getVariations().get(0));
    entityClass1.getVariations().get(0).getProperties().add(ref);

    System.out.println(printer.printPretty(schema));
    schema2DDb.adaptToDocumentDb(schema);
    System.out.println(printer.printPretty(schema));
  }

  private ReferenceClass createRefClass(String name, int variations)
  {
    ReferenceClass refClass = factory.createReferenceClass();
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

  private EntityClass createEntityClass(String name, int variations)
  {
    EntityClass entity = factory.createEntityClass();
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
