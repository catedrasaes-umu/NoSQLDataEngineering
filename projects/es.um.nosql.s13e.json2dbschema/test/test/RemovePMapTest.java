package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.Type;
import es.um.nosql.s13e.json2dbschema.m2m.NoSQLSchemaToDocumentDb;
import es.um.nosql.s13e.util.NoSQLSchemaPrettyPrinter;

public class RemovePMapTest
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
  public void testRemovePMap()
  {
    PrimitiveType pTypeValue = factory.createPrimitiveType(); pTypeValue.setName("string");
    Attribute attrMap = createPMap("attr2Map", "string", pTypeValue);

    StructuralVariation var = factory.createStructuralVariation();
    var.setVariationId(1); var.getProperties().add(createPrimitiveType("attr1Str", "string")); var.getProperties().add(attrMap);

    EntityClass entity = factory.createEntityClass();
    entity.setName("entityName");
    entity.getVariations().add(var);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.getEntities().add(entity);

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
    schema2DDb.adaptToDocumentDb(schema);

    assertEquals(2, schema.getEntities().size());
    assertEquals("entityName", schema.getEntities().get(0).getName());
    assertEquals(1, schema.getEntities().get(0).getVariations().size());
    assertEquals(2, schema.getEntities().get(0).getVariations().get(0).getProperties().size());
    Property prop1 = schema.getEntities().get(0).getVariations().get(0).getProperties().get(0);
    Property prop2 = schema.getEntities().get(0).getVariations().get(0).getProperties().get(1);
//    assertTrue(prop1 instanceof Attribute && prop1.getName().equals("attr1Str"));
    assertEquals("MapAttr2Map", schema.getEntities().get(1).getName());
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }
/*
  @Test
  public void testRemovePMap()
  {
    // Test if two variations with the same map aggregate the same variation after transformation.
    Attribute attr1 = factory.createAttribute();
    attr1.setName("attr1Str");
    PrimitiveType pType = factory.createPrimitiveType();
    pType.setName("string");
    attr1.setType(pType);

    Attribute attr2 = factory.createAttribute();
    attr2.setName("attr2Map");
    PMap theMap2 = factory.createPMap();
    PrimitiveType pTypeKey2 = factory.createPrimitiveType(); pTypeKey2.setName("string");
    PrimitiveType pTypeValue2 = factory.createPrimitiveType(); pTypeValue2.setName("string");
    theMap2.setKeyType(pTypeKey2); theMap2.setValueType(pTypeValue2);
    attr2.setType(theMap2);

    StructuralVariation var = factory.createStructuralVariation();
    var.setVariationId(1); var.getProperties().add(attr1); var.getProperties().add(attr2); var.getProperties().add(attr3);

    EntityClass entity = factory.createEntityClass();
    entity.setName("entityName");
    entity.getVariations().add(var);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.getEntities().add(entity);

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
    schema2DDb.adaptToDocumentDb(schema);
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }
*/
  @Test
  public void testRecursiveRemovePMap()
  {
  }

  private Attribute createPrimitiveType(String name, String type)
  {
    Attribute attr = factory.createAttribute();
    attr.setName(name);
    PrimitiveType pType = factory.createPrimitiveType();
    pType.setName(type);
    attr.setType(pType);

    return attr;
  }

  private Attribute createPMap(String name, String key, Type value)
  {
    Attribute attr = factory.createAttribute();
    attr.setName(name);
    PMap pMap = factory.createPMap();
    PrimitiveType pType = factory.createPrimitiveType();
    pType.setName(key);

    pMap.setKeyType(pType);
    pMap.setValueType(value);
    attr.setType(pMap);

    return attr;
  }
}
