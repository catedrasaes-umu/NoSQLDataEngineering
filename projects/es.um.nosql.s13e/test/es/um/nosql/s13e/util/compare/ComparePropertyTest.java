package es.um.nosql.s13e.util.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class ComparePropertyTest
{
  private CompareProperty cProperty;
  private CompareAssociation cAssociation;
  private CompareAttribute cAttribute;
  private CompareReference cReference;
  private CompareNull cNull;
  private CompareAggregate cAggregate;

  @Before
  public void setUp()
  {
    cProperty = new CompareProperty();
    cAssociation = new CompareAssociation();
    cAggregate = new CompareAggregate();
    cReference = new CompareReference();
    cNull = new CompareNull();
    cAttribute = new CompareAttribute();
  }

  /**
   * TestProperty SHOULD check: Name and Association/Null/Attribute comparison.
   */
  @Test
  public void testProperty()
  {
    assertFalse(cProperty.compare(null, null));

    assertTrue(cProperty.compare(createNull("name"), createNull("name")));
    assertFalse(cProperty.compare(createNull("name1"), createNull("name2")));

    assertFalse(cProperty.compare(createAttr("name", "type1"), createAttr("name", "type2")));
    assertFalse(cProperty.compare(createAttr("name", null), createAggr("name", 0, 0)));
  }

  /**
   * TestAssociation SHOULD check: UpperBound, LowerBound and Reference/Aggregate comparison
   * TestAssociation SHOULD NOT check: Name
   */
  @Test
  public void testAssociation()
  {
    assertFalse(cAssociation.compare(null, null));

    assertTrue(cAssociation.compare(createAggr("name", 0, 0), createAggr("name", 0, 0)));
    assertTrue(cAssociation.compare(createAggr("name1", 0, 0), createAggr("name2", 0, 0)));
    assertFalse(cAssociation.compare(createAggr("name", 1, 0), createAggr("name", 0, 0)));
    assertFalse(cAssociation.compare(createAggr("name", 0, 1), createAggr("name", 0, 0)));

    assertTrue(cAssociation.compare(createRef("name", 0, 0, null, null, null, null), createRef("name", 0, 0, null, null, null, null)));
    assertTrue(cAssociation.compare(createRef("name1", 0, 0, null, null, null, null), createRef("name2", 0, 0, null, null, null, null)));
    assertFalse(cAssociation.compare(createRef("name", 1, 0, null, null, null, null), createRef("name", 0, 0, null, null, null, null)));
    assertFalse(cAssociation.compare(createRef("name", 0, 1, null, null, null, null), createRef("name", 0, 0, null, null, null, null)));
  }

  /**
   * TestReference SHOULD check: OriginalType, Opposite, RefsTo, Features
   * TestReference SHOULD NOT check: Name, UpperBound, LowerBound
   */
  @Test
  public void testReference()
  {
    assertFalse(cReference.compare(null, null));

    assertTrue(cReference.compare(createRef("name1", 1, 1, null, null, null, null), createRef("name2", 0, 0, null, null, null, null)));

    assertTrue(cReference.compare(createRef(null, 0, 0, "origType", null, null, null), createRef(null, 0, 0, "origType", null, null, null)));
    assertFalse(cReference.compare(createRef(null, 0, 0, "origType1", null, null, null), createRef(null, 0, 0, "origType2", null, null, null)));

    assertTrue(cReference.compare(createRef(null, 0, 0, null, "entity", null, null), createRef(null, 0, 0, null, "entity", null, null)));
    assertFalse(cReference.compare(createRef(null, 0, 0, null, "entity1", null, null), createRef(null, 0, 0, null, "entity2", null, null)));

    assertTrue(cReference.compare(createRef(null, 0, 0, null, null, createRef("name", 0, 0, null, null, null, null), null),
        createRef(null, 0, 0, null, null, createRef("name", 0, 0, null, null, null, null), null)));
    assertFalse(cReference.compare(createRef(null, 0, 0, null, null, createRef("name1", 0, 0, null, null, null, null), null),
        createRef(null, 0, 0, null, null, createRef("name2", 0, 0, null, null, null, null), null)));

    assertTrue(cReference.compare(createRef(null, 0, 0, null, null, null, 1), createRef(null, 0, 0, null, null, null, 1)));
    assertFalse(cReference.compare(createRef(null, 0, 0, null, null, null, null), createRef(null, 0, 0, null, null, null, 1)));
  }

  /**
   * TestAggregate SHOULD check: Aggregates
   * TestAggregate SHOULD NOT check: Name, UpperBound, LowerBound
   */
  @Test
  public void testAggregate()
  {
    assertFalse(cAggregate.compare(null, null));

    assertTrue(cAggregate.compare(createAggr("name1", 1, 1), createAggr("name2", 0, 0)));

    assertTrue(cAggregate.compare(createAggr(null, 0, 0, 2, 1, 3), createAggr(null, 0, 0, 1, 1, 1)));
    assertFalse(cAggregate.compare(createAggr(null, 0, 0, 1, 2), createAggr(null, 0, 0, 3)));
  }

  /**
   * TestNull SHOULD NOT check: Name
   */
  @Test
  public void testNull()
  {
    assertFalse(cNull.compare(null, null));

    assertFalse(cNull.compare(createNull("name"), null));
    assertFalse(cNull.compare(null, createNull("name")));

    assertTrue(cNull.compare(createNull("name"), createNull("name")));
    assertTrue(cNull.compare(createNull(null), createNull("name")));
    assertTrue(cNull.compare(createNull("aaa"), createNull("bbb")));
  }

  /**
   * TestAttribute SHOULD check: Type
   * TestAggregate SHOULD NOT check: Name
   */
  @Test
  public void testAttribute()
  {
    assertFalse(cAttribute.compare(null, null));

    assertFalse(cAttribute.compare(createAttr(null, "string"), null));
    assertFalse(cAttribute.compare(null, createAttr(null, "string")));

    assertFalse(cAttribute.compare(createAttr("attr1", "string"), createAttr("attr2", "int")));
    assertTrue(cAttribute.compare(createAttr("attr1", "string"), createAttr("attr2", "string")));
  }

  private Reference createRef(String name, int lBound, int uBound, String oType, String entityName, Reference opp, Integer varId)
  {
    Reference theRef = NoSQLSchemaFactory.eINSTANCE.createReference();
    theRef.setName(name);
    theRef.setLowerBound(lBound);
    theRef.setUpperBound(uBound);
    theRef.setOriginalType(oType);
    theRef.setOpposite(opp);

    if (entityName != null)
    {
      EntityClass entity = NoSQLSchemaFactory.eINSTANCE.createEntityClass();
      entity.setName(entityName);
      theRef.setRefsTo(entity);
    }

    if (varId != null)
    {
      StructuralVariation var = NoSQLSchemaFactory.eINSTANCE.createStructuralVariation();
      var.setVariationId(varId);
      theRef.setFeatures(var);
    }

    return theRef;
  }

  private Aggregate createAggr(String name, int lBound, int uBound, Integer... varIds)
  {
    Aggregate theAggr = NoSQLSchemaFactory.eINSTANCE.createAggregate();
    theAggr.setName(name);
    theAggr.setLowerBound(lBound);
    theAggr.setUpperBound(uBound);

    for (int varId : varIds)
    {
      StructuralVariation var = NoSQLSchemaFactory.eINSTANCE.createStructuralVariation();
      var.setVariationId(varId);
      theAggr.getAggregates().add(var);
    }

    return theAggr;
  }

  private Null createNull(String name)
  {
    Null theNull = NoSQLSchemaFactory.eINSTANCE.createNull();
    theNull.setName(name);

    return theNull;
  }

  private Attribute createAttr(String name, String pType)
  {
    Attribute theAttribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
    theAttribute.setName(name);

    PrimitiveType type = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
    type.setName(pType);

    theAttribute.setType(type);

    return theAttribute;
  }
}
