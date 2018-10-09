package regression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.Type;
import es.um.nosql.s13e.json2dbschema.m2m.NoSQLSchemaToDocumentDb;
import es.um.nosql.s13e.util.NoSQLSchemaPrettyPrinter;

public class ReferenceClassToEntityClassTest
{
  private NoSQLSchemaFactory factory;
  private NoSQLSchemaToDocumentDb schema2DDb;

  @Before
  public void setUp()
  {
    factory = NoSQLSchemaFactory.eINSTANCE;
    schema2DDb = new NoSQLSchemaToDocumentDb();
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
    ReferenceClass refClass = createRefClass("class1", 3);
    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("schema");
    schema.getRefClasses().add(refClass);


//    schema2DDb.adaptToDocumentDb(schema);
  }

  @Test
  public void testFixReferences()
  {
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

  private EntityClass createEntity(String name, int variations)
  {
    EntityClass entity = factory.createEntityClass();
    entity.setName(name);

    for (int i = 1; i <= variations; i++)
    {
      StructuralVariation var = factory.createStructuralVariation();
      entity.getVariations().add(var);

      PrimitiveType pType = factory.createPrimitiveType(); pType.setName("string");
      Attribute attr = factory.createAttribute(); attr.setName("attr" + i); attr.setType(pType);
      var.getProperties().add(attr);
    }

    return entity;
  }
}
