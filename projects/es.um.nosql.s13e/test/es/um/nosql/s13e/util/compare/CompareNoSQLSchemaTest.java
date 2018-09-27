package es.um.nosql.s13e.util.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class CompareNoSQLSchemaTest
{
  private CompareNoSQLSchema cSchema;
  private CompareClassifier cClassifier;
  private CompareReferenceClass cRef;
  private CompareEntityClass cEntity;
  private CompareStructuralVariation cVariation;

  @Before
  public void setUp()
  {
    cSchema = new CompareNoSQLSchema();
    cClassifier = new CompareClassifier();
    cRef = new CompareReferenceClass();
    cEntity = new CompareEntityClass();
    cVariation = new CompareStructuralVariation();
  }

  /**
   * TestNoSQLSchema SHOULD check: Name and ReferenceClass/EntityClass comparison with Classifier.
   */
  @Test
  public void testNoSQLSchema()
  {
    assertFalse(cSchema.compare(null, null));

    assertTrue(cSchema.compare(createNoSQLSchema("name", null, null), createNoSQLSchema("name", null, null)));
    assertFalse(cSchema.compare(createNoSQLSchema("name1", null, null), createNoSQLSchema("name2", null, null)));

    assertFalse(cSchema.compare(createNoSQLSchema("name", new String[] {"entityName"}, null),
        createNoSQLSchema("name", null, null)));
    assertTrue(cSchema.compare(createNoSQLSchema("name", new String[] {"entityName1", "entityName2"}, null),
        createNoSQLSchema("name", new String[] {"entityName1", "entityName2"}, null)));

    assertFalse(cSchema.compare(createNoSQLSchema("name", null, new String[] {"refName"}),
        createNoSQLSchema("name", null, null)));
    assertFalse(cSchema.compare(createNoSQLSchema("name", null, new String[] {"refName1"}),
        createNoSQLSchema("name", null, new String[] {"refName2"})));
  }

  /**
   * TestClassifier SHOULD check: Name, Parents, StructuralVariations and ReferenceClass/EntityClass comparison.
   * TestClassifier SHOULD NOT check: IsRoot
   */
  @Test
  public void testClassifier()
  {
    assertFalse(cClassifier.compare(null, null));

    assertTrue(cClassifier.compare(createRef("name", null), createRef("name", null)));
    assertFalse(cClassifier.compare(createRef("name1", null), createRef("name2", null)));

    assertTrue(cClassifier.compare(createEntity("name", false, null), createEntity("name", false, null)));
    assertFalse(cClassifier.compare(createEntity("name1", false, null), createEntity("name2", false, null)));

    assertTrue(cClassifier.compare(createEntity("name", false, new String[] {"parent1", "parent2"}), createEntity("name", false, new String[] {"parent1", "parent2"})));
    assertFalse(cClassifier.compare(createEntity("name", false, new String[] {"parent1", "parent2"}), createEntity("name", false, new String[] {"parent1"})));

    assertFalse(cClassifier.compare(createRef("name", null, 1, 2, 3), createRef("name", null, 1)));
    assertTrue(cClassifier.compare(createRef("name", null, 1, 2, 3), createRef("name", null, 3, 2, 1)));
    assertFalse(cClassifier.compare(createRef("name", null, 1, 2, 3), createRef("name", null, 1, 2, 3, 4)));
  }

  /**
   * TestReferenceClass SHOULD NOT check: Name and StructuralVariation
   */
  @Test
  public void testReferenceClass()
  {
    assertFalse(cRef.compare(null, null));

    assertFalse(cRef.compare(createRef("name", null), null));
    assertFalse(cRef.compare(null, createRef("name", null)));

    assertTrue(cRef.compare(createRef("name", null, 1, 2, 3), createRef("name", null, 1, 2, 3)));
    assertTrue(cRef.compare(createRef("name", null, 1, 2, 3), createRef("name", null, 1)));

    assertTrue(cRef.compare(createRef(null, null), createRef("name", null)));
    assertTrue(cRef.compare(createRef("aaa", null), createRef("bbb", null)));
  }

  /**
   * TestEntityClass SHOULD check: IsRoot
   * TestEntityClass SHOULD NOT check: Name and StructuralVariation
   */
  @Test
  public void testEntityClass()
  {
    assertFalse(cEntity.compare(null, null));

    assertFalse(cEntity.compare(createEntity("name", false, null), null));
    assertFalse(cEntity.compare(null, createEntity("name", false, null)));

    assertTrue(cEntity.compare(createEntity("name1", false, null), createEntity("name2", false, null)));
    assertFalse(cEntity.compare(createEntity("name1", false, null), createEntity("name2", true, null)));

    assertTrue(cEntity.compare(createEntity("name", false, null, 1, 2, 3), createEntity("name", false, null, 1, 2, 3)));
    assertTrue(cEntity.compare(createEntity("name", false, null, 1, 2, 3), createEntity("name", false, null, 1)));

    assertTrue(cEntity.compare(createEntity(null, false, null), createEntity("name", false, null)));
    assertTrue(cEntity.compare(createEntity("aaa", false, null), createEntity("bbb", false, null)));
  }

  /**
   * TestEntityClass SHOULD check: Properties
   * TestEntityClass SHOULD NOT check: VariationId, Count and Timestamp
   */
  @Test
  public void testStructuralVariation()
  {
    assertFalse(cVariation.compare(null, null));

    assertTrue(cVariation.compare(createVar(0, 0, 0), createVar(0, 0, 0)));
    assertTrue(cVariation.compare(createVar(0, 0, 0), createVar(1, 0, 0)));
    assertTrue(cVariation.compare(createVar(0, 0, 0), createVar(0, 1, 0)));
    assertTrue(cVariation.compare(createVar(0, 0, 0), createVar(0, 0, 1)));

    assertFalse(cVariation.compare(createVar(0, 0, 0), createVar(0, 0, 0, "prop1")));
    assertTrue(cVariation.compare(createVar(0, 0, 0, "prop1"), createVar(0, 0, 0, "prop1")));
    assertTrue(cVariation.compare(createVar(0, 0, 0, "prop1", "prop2"), createVar(0, 0, 0, "prop2", "prop1")));
  }

  private NoSQLSchema createNoSQLSchema(String name, String[] entities, String[] refs)
  {
    NoSQLSchema schema = NoSQLSchemaFactory.eINSTANCE.createNoSQLSchema();
    schema.setName(name);

    if (entities != null)
      for (String e : entities)
      {
        EntityClass entity = NoSQLSchemaFactory.eINSTANCE.createEntityClass();
        entity.setName(e);
        schema.getEntities().add(entity);
      }

    if (refs != null)
      for (String ref : refs)
      {
        ReferenceClass reference = NoSQLSchemaFactory.eINSTANCE.createReferenceClass();
        reference.setName(ref);
        schema.getRefClasses().add(reference);
      }

    return schema;
  }

  private EntityClass createEntity(String name, boolean root, String[] parents, Integer... varIds)
  {
    EntityClass entity = NoSQLSchemaFactory.eINSTANCE.createEntityClass();
    entity.setName(name);
    entity.setRoot(root);

    if (parents != null)
      for (String parentName : parents)
      {
        EntityClass parent = NoSQLSchemaFactory.eINSTANCE.createEntityClass();
        parent.setName(parentName);
        entity.getParents().add(parent);
      }

    for (int varId : varIds)
    {
      StructuralVariation var = NoSQLSchemaFactory.eINSTANCE.createStructuralVariation();
      var.setVariationId(varId);
      entity.getVariations().add(var);
    }

    return entity;
  }

  private ReferenceClass createRef(String name, String[] parents, Integer... varIds)
  {
    ReferenceClass ref = NoSQLSchemaFactory.eINSTANCE.createReferenceClass();
    ref.setName(name);

    if (parents != null)
      for (String parentName : parents)
      {
        EntityClass parent = NoSQLSchemaFactory.eINSTANCE.createEntityClass();
        parent.setName(parentName);
        ref.getParents().add(parent);
      }

    for (int varId : varIds)
    {
      StructuralVariation var = NoSQLSchemaFactory.eINSTANCE.createStructuralVariation();
      var.setVariationId(varId);
      ref.getVariations().add(var);
    }

    return ref;
  }

  private StructuralVariation createVar(int varId, long count, long ts, String... props)
  {
    StructuralVariation variation = NoSQLSchemaFactory.eINSTANCE.createStructuralVariation();
    variation.setVariationId(varId);
    variation.setCount(count);
    variation.setTimestamp(ts);

    for (String prop : props)
    {
      Null nullProp = NoSQLSchemaFactory.eINSTANCE.createNull();
      nullProp.setName(prop);
      variation.getProperties().add(nullProp);
    }

    return variation;
  }
}
