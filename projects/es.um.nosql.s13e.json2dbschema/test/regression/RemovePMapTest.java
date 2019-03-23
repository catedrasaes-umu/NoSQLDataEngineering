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
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.DataType;
import es.um.nosql.s13e.json2dbschema.m2m.NoSQLSchemaToDocumentDb;

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
    Attribute attrMap = createPMap("attr2map", "string", pTypeValue);

    StructuralVariation var = factory.createStructuralVariation();
    var.setVariationId(1); var.getProperties().add(createPrimitiveType("attr1Str", "string")); var.getProperties().add(attrMap);

    EntityType entity = factory.createEntityType();
    entity.setName("entityName");
    entity.getVariations().add(var);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("testRemovePMap");
    schema.getEntities().add(entity);

    schema2DDb.adaptToDocumentDb(schema);

    assertEquals(2, schema.getEntities().size());
    assertEquals("entityName", schema.getEntities().get(0).getName());
    assertEquals(1, schema.getEntities().get(0).getVariations().size());
    assertEquals(2, schema.getEntities().get(0).getVariations().get(0).getProperties().size());
    Property prop1 = schema.getEntities().get(0).getVariations().get(0).getProperties().get(0);
    Property prop2 = schema.getEntities().get(0).getVariations().get(0).getProperties().get(1);
    assertTrue(prop1 instanceof Attribute && prop1.getName().equals("attr1Str"));
    assertTrue(prop2 instanceof Aggregate && prop2.getName().equals("attr2map"));

    assertEquals("Map_Attr2map", schema.getEntities().get(1).getName());
    assertEquals(1, schema.getEntities().get(1).getVariations().size());
    assertEquals(2, schema.getEntities().get(1).getVariations().get(0).getProperties().size());
    prop1 = schema.getEntities().get(1).getVariations().get(0).getProperties().get(0);
    prop2 = schema.getEntities().get(1).getVariations().get(0).getProperties().get(1);
    assertTrue(prop1 instanceof Attribute && prop1.getName().equals("key"));
    assertTrue(prop2 instanceof Attribute && prop2.getName().equals("value"));
  }

  @Test
  public void testRemovePMapFromTwoVariations()
  {
    PrimitiveType pTypeValue = factory.createPrimitiveType(); pTypeValue.setName("string");
    PrimitiveType pTypeValue2 = factory.createPrimitiveType(); pTypeValue2.setName("string");
    Attribute attrMap = createPMap("attr2map", "string", pTypeValue);
    Attribute attrMap2 = createPMap("attr2map", "string", pTypeValue2);

    StructuralVariation var = factory.createStructuralVariation();
    var.setVariationId(1); var.getProperties().add(createPrimitiveType("attr1Str", "string")); var.getProperties().add(attrMap);

    StructuralVariation var2 = factory.createStructuralVariation();
    var2.setVariationId(2); var2.getProperties().add(attrMap2);

    EntityType entity = factory.createEntityType();
    entity.setName("entityName");
    entity.getVariations().add(var);
    entity.getVariations().add(var2);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("testRemovePMapFromTwoVariations");
    schema.getEntities().add(entity);

    schema2DDb.adaptToDocumentDb(schema);

    assertEquals(2, schema.getEntities().size());
    assertEquals("entityName", schema.getEntities().get(0).getName());
    assertEquals(2, schema.getEntities().get(0).getVariations().size());
    assertEquals(2, schema.getEntities().get(0).getVariations().get(0).getProperties().size());
    assertEquals(1, schema.getEntities().get(0).getVariations().get(1).getProperties().size());
    Property prop1 = schema.getEntities().get(0).getVariations().get(0).getProperties().get(0);
    Property prop2 = schema.getEntities().get(0).getVariations().get(0).getProperties().get(1);
    assertTrue(prop1 instanceof Attribute && prop1.getName().equals("attr1Str"));
    assertTrue(prop2 instanceof Aggregate && prop2.getName().equals("attr2map"));
    prop1 = schema.getEntities().get(0).getVariations().get(1).getProperties().get(0);
    assertTrue(prop2 instanceof Aggregate && prop2.getName().equals("attr2map"));

    assertEquals("Map_Attr2map", schema.getEntities().get(1).getName());
    assertEquals(1, schema.getEntities().get(1).getVariations().size());
    assertEquals(2, schema.getEntities().get(1).getVariations().get(0).getProperties().size());
    prop1 = schema.getEntities().get(1).getVariations().get(0).getProperties().get(0);
    prop2 = schema.getEntities().get(1).getVariations().get(0).getProperties().get(1);
    assertTrue(prop1 instanceof Attribute && prop1.getName().equals("key"));
    assertTrue(prop2 instanceof Attribute && prop2.getName().equals("value"));
  }

  @Test
  public void testRecursiveRemovePMap()
  {
    PrimitiveType pTypeValue = factory.createPrimitiveType(); pTypeValue.setName("string");
    PrimitiveType pTypeValue2 = factory.createPrimitiveType(); pTypeValue2.setName("int");
    PrimitiveType pTypeValue3 = factory.createPrimitiveType(); pTypeValue3.setName("int");
    PMap pTypeMap = factory.createPMap(); pTypeMap.setKeyType(pTypeValue2); pTypeMap.setValueType(pTypeValue3);
    Attribute attrMap = createPMap("attr2map", "string", pTypeMap);

    PrimitiveType pTypeValue4 = factory.createPrimitiveType(); pTypeValue4.setName("int");
    Attribute attrMap2 = createPMap("attr2map", "string", pTypeValue4);

    StructuralVariation var1 = factory.createStructuralVariation();
    var1.setVariationId(1); var1.getProperties().add(createPrimitiveType("attr1Str", "string")); var1.getProperties().add(attrMap);

    StructuralVariation var2 = factory.createStructuralVariation();
    var2.setVariationId(2); var2.getProperties().add(attrMap2);

    EntityType entity = factory.createEntityType();
    entity.setName("entityName");
    entity.getVariations().add(var1); entity.getVariations().add(var2);

    NoSQLSchema schema = factory.createNoSQLSchema();
    schema.setName("testRecursiveRemovePMap");
    schema.getEntities().add(entity);

    schema2DDb.adaptToDocumentDb(schema);
    assertTrue(var1.getProperties().stream().noneMatch(prop -> {return prop instanceof Attribute && ((Attribute)prop).getType() instanceof PMap;}));
    assertTrue(var2.getProperties().stream().noneMatch(prop -> {return prop instanceof Attribute && ((Attribute)prop).getType() instanceof PMap;}));
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

  private Attribute createPMap(String name, String key, DataType value)
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
