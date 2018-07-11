package entitydifferentiation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;

public class Test1
{
  NoSQLSchema schema;

  @Before
  public void setUp() throws Exception
  {
    NoSQLSchemaFactory factory = NoSQLSchemaFactory.eINSTANCE;

    Entity cdate1 = factory.createEntity(); cdate1.setName("CustomDate1"); cdate1.setRoot(false);
    EntityVariation ev10 = factory.createEntityVariation(); ev10.setVariationId(10);
    Attribute attr1101 = factory.createAttribute(); attr1101.setName("date1");
    PrimitiveType pType1101 = factory.createPrimitiveType(); pType1101.setName("number"); attr1101.setType(pType1101);
    cdate1.getEntityVariations().add(ev10); ev10.getProperties().add(attr1101);

    Entity cdate2 = factory.createEntity(); cdate2.setName("CustomDate2"); cdate2.setRoot(true);
    EntityVariation ev20 = factory.createEntityVariation(); ev20.setVariationId(20);
    Attribute attr2201 = factory.createAttribute(); attr2201.setName("_id");
    PrimitiveType pType2201 = factory.createPrimitiveType(); pType2201.setName("String"); attr2201.setType(pType2201);
    Attribute attr2202 = factory.createAttribute(); attr2202.setName("date2");
    PrimitiveType pType2202 = factory.createPrimitiveType(); pType2202.setName("String"); attr2202.setType(pType2202);
    cdate2.getEntityVariations().add(ev20); ev20.getProperties().add(attr2201); ev20.getProperties().add(attr2202);

    Entity cdate3 = factory.createEntity(); cdate3.setName("CustomDate3"); cdate3.setRoot(true);
    EntityVariation ev30 = factory.createEntityVariation(); ev30.setVariationId(30);
    Attribute attr3301 = factory.createAttribute(); attr3301.setName("_id");
    PrimitiveType pType3301 = factory.createPrimitiveType(); pType3301.setName("ObjectId"); attr3301.setType(pType3301);
    Attribute attr3302 = factory.createAttribute(); attr3302.setName("date3");
    PrimitiveType pType3302 = factory.createPrimitiveType(); pType3302.setName("Number"); attr3302.setType(pType3302);
    cdate3.getEntityVariations().add(ev30); ev30.getProperties().add(attr3301); ev30.getProperties().add(attr3302);

    Entity persons = factory.createEntity(); persons.setName("Persons"); persons.setRoot(true);
    EntityVariation ev1 = factory.createEntityVariation(); ev1.setVariationId(1);
    Attribute attrP1 = factory.createAttribute(); attrP1.setName("_id");
    PrimitiveType pTypeP1 = factory.createPrimitiveType(); pTypeP1.setName("String"); attrP1.setType(pTypeP1);
    Reference ref1 = factory.createReference(); ref1.setName("date"); ref1.setLowerBound(1); ref1.setUpperBound(1);
    ref1.setOriginalType("String"); ref1.setRefTo(cdate3);
    ev1.getProperties().add(attrP1); ev1.getProperties().add(ref1);

    EntityVariation ev2 = factory.createEntityVariation(); ev2.setVariationId(2);
    Attribute attrP2 = factory.createAttribute(); attrP2.setName("_id");
    PrimitiveType pTypeP2 = factory.createPrimitiveType(); pTypeP2.setName("String"); attrP2.setType(pTypeP2);
    Aggregate aggr2 = factory.createAggregate(); aggr2.setName("date"); aggr2.setLowerBound(1); aggr2.setUpperBound(1); aggr2.getRefTo().add(ev10);
    ev2.getProperties().add(attrP2); ev2.getProperties().add(aggr2);

    EntityVariation ev3 = factory.createEntityVariation(); ev3.setVariationId(3);
    Attribute attrP3 = factory.createAttribute(); attrP3.setName("_id");
    PrimitiveType pTypeP3 = factory.createPrimitiveType(); pTypeP3.setName("String"); attrP3.setType(pTypeP3);
    Reference ref3 = factory.createReference(); ref3.setName("date"); ref3.setLowerBound(1); ref3.setUpperBound(-1);
    ref3.setOriginalType("ObjectId"); ref3.setRefTo(cdate3);
    ev3.getProperties().add(attrP3); ev3.getProperties().add(ref3);

    EntityVariation ev4 = factory.createEntityVariation(); ev4.setVariationId(4);
    Attribute attrP41 = factory.createAttribute(); attrP41.setName("_id");
    PrimitiveType pTypeP41 = factory.createPrimitiveType(); pTypeP41.setName("String"); attrP41.setType(pTypeP41);
    Attribute attrP42 = factory.createAttribute(); attrP42.setName("date");
    PrimitiveType pTypeP42 = factory.createPrimitiveType(); pTypeP42.setName("String");
    Tuple tupleP42 = factory.createTuple(); tupleP42.getElements().add(pTypeP42); attrP42.setType(tupleP42);
    ev4.getProperties().add(attrP41); ev4.getProperties().add(attrP42);

    persons.getEntityVariations().add(ev1); persons.getEntityVariations().add(ev2); persons.getEntityVariations().add(ev3); persons.getEntityVariations().add(ev4);

    schema = factory.createNoSQLSchema(); schema.setName("test1");
    schema.getEntities().add(persons); schema.getEntities().add(cdate1); schema.getEntities().add(cdate2); schema.getEntities().add(cdate3);
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
