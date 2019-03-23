package es.um.nosql.s13e.util.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class CompareNoSQLSchemaTest
{
  private CompareNoSQLSchema cSchema;
  private CompareSchemaType cClassifier;
  private CompareRelationshipType cRef;
  private CompareEntityType cEntity;
  private CompareStructuralVariation cVariation;

  @Before
  public void setUp()
  {
    cSchema = new CompareNoSQLSchema();
    cClassifier = new CompareSchemaType();
    cRef = new CompareRelationshipType();
    cEntity = new CompareEntityType();
    cVariation = new CompareStructuralVariation();
  }

  /**
   * TestNoSQLSchema SHOULD check: Name and RelationshipType/EntityType comparison with SchemaType.
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
   * TestClassifier SHOULD check: Name, Parents, StructuralVariations and RelationshipType/EntityType comparison.
   * TestClassifier SHOULD NOT check: IsRoot
   */
  @Test
  public void testClassifier()
  {
    assertFalse(cClassifier.compare(null, null));

    assertTrue(cClassifier.compare(createRel("name", null), createRel("name", null)));
    assertFalse(cClassifier.compare(createRel("name1", null), createRel("name2", null)));

    assertTrue(cClassifier.compare(createEntity("name", false, null), createEntity("name", false, null)));
    assertFalse(cClassifier.compare(createEntity("name1", false, null), createEntity("name2", false, null)));

    assertTrue(cClassifier.compare(createEntity("name", false, new String[] {"parent1", "parent2"}), createEntity("name", false, new String[] {"parent1", "parent2"})));
    assertFalse(cClassifier.compare(createEntity("name", false, new String[] {"parent1", "parent2"}), createEntity("name", false, new String[] {"parent1"})));

    assertFalse(cClassifier.compare(createRel("name", null, 1, 2, 3), createRel("name", null, 1)));
    assertTrue(cClassifier.compare(createRel("name", null, 1, 2, 3), createRel("name", null, 3, 2, 1)));
    assertFalse(cClassifier.compare(createRel("name", null, 1, 2, 3), createRel("name", null, 1, 2, 3, 4)));
  }

  /**
   * TestRelationshipType SHOULD NOT check: Name and StructuralVariation
   */
  @Test
  public void testRelationshipType()
  {
    assertFalse(cRef.compare(null, null));

    assertFalse(cRef.compare(createRel("name", null), null));
    assertFalse(cRef.compare(null, createRel("name", null)));

    assertTrue(cRef.compare(createRel("name", null, 1, 2, 3), createRel("name", null, 1, 2, 3)));
    assertTrue(cRef.compare(createRel("name", null, 1, 2, 3), createRel("name", null, 1)));

    assertTrue(cRef.compare(createRel(null, null), createRel("name", null)));
    assertTrue(cRef.compare(createRel("aaa", null), createRel("bbb", null)));
  }

  /**
   * TestEntityType SHOULD check: IsRoot
   * TestEntityType SHOULD NOT check: Name and StructuralVariation
   */
  @Test
  public void testEntityType()
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
   * TestStructuralVariation SHOULD check: Properties
   * TestStructuralVariation SHOULD NOT check: VariationId, Count and Timestamp
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
        EntityType entity = NoSQLSchemaFactory.eINSTANCE.createEntityType();
        entity.setName(e);
        schema.getEntities().add(entity);
      }

    if (refs != null)
      for (String ref : refs)
      {
        RelationshipType reference = NoSQLSchemaFactory.eINSTANCE.createRelationshipType();
        reference.setName(ref);
        schema.getRelationships().add(reference);
      }

    return schema;
  }

  private EntityType createEntity(String name, boolean root, String[] parents, Integer... varIds)
  {
    EntityType entity = NoSQLSchemaFactory.eINSTANCE.createEntityType();
    entity.setName(name);
    entity.setRoot(root);

    if (parents != null)
      for (String parentName : parents)
      {
        EntityType parent = NoSQLSchemaFactory.eINSTANCE.createEntityType();
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

  private RelationshipType createRel(String name, String[] parents, Integer... varIds)
  {
    RelationshipType ref = NoSQLSchemaFactory.eINSTANCE.createRelationshipType();
    ref.setName(name);

    if (parents != null)
      for (String parentName : parents)
      {
        EntityType parent = NoSQLSchemaFactory.eINSTANCE.createEntityType();
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
    variation.setFirstTimestamp(ts);

    for (String prop : props)
    {
      Null nullProp = NoSQLSchemaFactory.eINSTANCE.createNull();
      nullProp.setName(prop);
      variation.getProperties().add(nullProp);
    }

    return variation;
  }
}
