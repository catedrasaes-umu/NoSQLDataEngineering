package entitydifferentiation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;

public class Test2
{
  NoSQLSchema schema;

  @Before
  public void setUp() throws Exception
  {
    NoSQLSchemaFactory factory = NoSQLSchemaFactory.eINSTANCE;

    EntityClass persons = factory.createEntityClass(); persons.setName("Persons"); persons.setRoot(true);
    StructuralVariation ev1 = factory.createStructuralVariation(); ev1.setVariationId(1);
    Attribute attrP1 = factory.createAttribute(); attrP1.setName("_id");
    PrimitiveType pTypeP1 = factory.createPrimitiveType(); pTypeP1.setName("ObjectId"); attrP1.setType(pTypeP1);

    Attribute attrP2 = factory.createAttribute(); attrP2.setName("name");
    PrimitiveType pTypeP2 = factory.createPrimitiveType(); pTypeP2.setName("String"); attrP2.setType(pTypeP2);
    Attribute attrP3 = factory.createAttribute(); attrP3.setName("surname");
    PrimitiveType pTypeP3 = factory.createPrimitiveType(); pTypeP3.setName("String"); attrP3.setType(pTypeP3);
    Attribute attrP4 = factory.createAttribute(); attrP4.setName("age");
    PrimitiveType pTypeP4 = factory.createPrimitiveType(); pTypeP4.setName("Number"); attrP4.setType(pTypeP4);
    Attribute attrP5 = factory.createAttribute(); attrP5.setName("isEmployed");
    PrimitiveType pTypeP5 = factory.createPrimitiveType(); pTypeP5.setName("Boolean"); attrP5.setType(pTypeP5);
    Attribute attrP6 = factory.createAttribute(); attrP6.setName("isMarried");
    PrimitiveType pTypeP6 = factory.createPrimitiveType(); pTypeP6.setName("Boolean"); attrP6.setType(pTypeP6);
    Attribute attrP7 = factory.createAttribute(); attrP7.setName("status");
    PrimitiveType pTypeP7 = factory.createPrimitiveType(); pTypeP7.setName("String"); attrP7.setType(pTypeP7);

    ev1.getProperties().add(attrP1); ev1.getProperties().add(attrP2); ev1.getProperties().add(attrP3); ev1.getProperties().add(attrP4);
    ev1.getProperties().add(attrP5); ev1.getProperties().add(attrP6); ev1.getProperties().add(attrP7);

    persons.getVariations().add(ev1);

    schema = factory.createNoSQLSchema(); schema.setName("test2");
    schema.getEntities().add(persons);
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void test()
  {
    fail("Not yet implemented");
  }
}
