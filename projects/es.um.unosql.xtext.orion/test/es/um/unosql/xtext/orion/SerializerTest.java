package es.um.unosql.xtext.orion;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import es.um.unosql.xtext.orion.orion.OrionOperations;
import es.um.unosql.xtext.orion.utils.OrionFactory;
import es.um.unosql.xtext.orion.utils.io.ModelSerializer;

public class SerializerTest
{
  private OrionFactory factory;

  @Before
  public void setUp()
  {
    factory = new OrionFactory();
  }

  @Test
  public void testXtextModelLoader()
  {
    OrionOperations orion = factory.createOrionOperations("handmade_oneofeach");
    orion.getOperations().add(factory.createEntityAddOp("addedEntity1"));
    orion.getOperations().add(factory.createEntityAddOp("addedEntity2"));
    orion.getOperations().add(factory.createEntityAddOp("addedEntity3"));
    orion.getOperations().add(factory.createEntityDeleteOp("addedEntity2"));
    orion.getOperations().add(factory.createEntityRenameOp("addedEntity3", "renamedEntity"));

    orion.getOperations().add(factory.createEntityAddOp("EntityToAdd"));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrSinTipo")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrStr"), factory.createPrimitiveType("String")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrNum"), factory.createPrimitiveType("Number")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrDouble"), factory.createPrimitiveType("Double")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrBool"), factory.createPrimitiveType("Boolean")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrSet"), factory.createSet()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrList"), factory.createList()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrTuple"), factory.createTuple()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrMap"), factory.createMap()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("Null"), factory.createNull()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrStrVal"), factory.createPrimitiveType("String"), "\"initValue\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrNumVal"), factory.createPrimitiveType("Number"), Integer.toString(33)));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrDoubleVal"), factory.createPrimitiveType("Double"), Double.toString(33.1)));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("addAttrBoolVal"), factory.createPrimitiveType("Boolean"), Boolean.toString(false)));

    orion.getOperations().add(factory.createEntityAddOp("EntityToRemove", Arrays.asList(
        factory.createSimpleDataFeature("attrToDel"),
        factory.createSimpleDataFeature("attrToDelStr", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attrToDelNum", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attrToDelDouble", factory.createPrimitiveType("Double")),
        factory.createSimpleDataFeature("attrToDelBool", factory.createPrimitiveType("Boolean")),
        factory.createSimpleDataFeature("attrToRen"),
        factory.createSimpleDataFeature("attrToRenStr", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attrToRenNum", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attrToRenDouble", factory.createPrimitiveType("Double")),
        factory.createSimpleDataFeature("attrToRenBool", factory.createPrimitiveType("Boolean"))
        )));

    orion.getOperations().add(factory.createFeatureDeleteOp(factory.createFeatureSelector("EntityToRemove", "attrToDel")));
    orion.getOperations().add(factory.createFeatureDeleteOp(factory.createFeatureSelector("EntityToRemove", "attrToDelStr")));
    orion.getOperations().add(factory.createFeatureDeleteOp(factory.createFeatureSelector("EntityToRemove", "attrToDelNum")));
    orion.getOperations().add(factory.createFeatureDeleteOp(factory.createFeatureSelector("EntityToRemove", "attrToDelDouble")));
    orion.getOperations().add(factory.createFeatureDeleteOp(factory.createFeatureSelector("EntityToRemove", "attrToDelBool")));

    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRen"), "attrFalse1"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenStr"), "attrFalse2"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenNum"), "attrFalse3"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenDouble"), "attrFalse4"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenBool"), "attrFalse5"));

    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRen"), "attrTrue1"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenStr"), "attrTrue2"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenNum"), "attrTrue3"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenDouble"), "attrTrue4"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createFeatureSelector("EntityToRemove", "attrToRenBool"), "attrTrue5"));

    orion.getOperations().add(factory.createEntityAddOp("EntityToCast", Arrays.asList(
        factory.createSimpleDataFeature("attrToCast"),
        factory.createSimpleDataFeature("attrToNull", factory.createNull()),
        factory.createSimpleDataFeature("attrToCastStr", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attrToCastNum", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attrToCastDouble", factory.createPrimitiveType("Double")),
        factory.createSimpleDataFeature("attrToCastBool", factory.createPrimitiveType("Boolean"))
        )));

    orion.getOperations().add(factory.createAttributeCastOp(factory.createFeatureSelector("EntityToCast", "attrToCast"), factory.createPrimitiveType("Bool")));
    orion.getOperations().add(factory.createAttributeCastOp(factory.createFeatureSelector("EntityToCast", "attrToCastNull"), factory.createPrimitiveType("Number")));
    orion.getOperations().add(factory.createAttributeCastOp(factory.createFeatureSelector("EntityToCast", "attrToCastStr"), factory.createPrimitiveType("Bool")));
    orion.getOperations().add(factory.createAttributeCastOp(factory.createFeatureSelector("EntityToCast", "attrToCastNum"), factory.createPrimitiveType("String")));
    orion.getOperations().add(factory.createAttributeCastOp(factory.createFeatureSelector("EntityToCast", "attrToCastDouble"), factory.createPrimitiveType("Bool")));
    orion.getOperations().add(factory.createAttributeCastOp(factory.createFeatureSelector("EntityToCast", "attrToCastBool"), factory.createPrimitiveType("String")));

    orion.getOperations().add(factory.createEntityAddOp("EntityRefs"));

    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "nullRefSimple"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "nullRefArray"), "*", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "strRefSimple"), factory.createPrimitiveType("String"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "strRefArray"), factory.createPrimitiveType("String"), "*", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "numRefSimple"), factory.createPrimitiveType("Number"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "numRefArray"), factory.createPrimitiveType("Number"), "*", "addedEntity1"));

    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "nullRefSimpleChange"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "nullRefArrayChange"), "*", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "strRefSimpleChange"), factory.createPrimitiveType("String"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "strRefArrayChange"), factory.createPrimitiveType("String"), "*", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "numRefSimpleChange"), factory.createPrimitiveType("Number"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createFeatureSelector("EntityRefs", "numRefArrayChange"), factory.createPrimitiveType("Number"), "*", "addedEntity1"));

    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "nullRefSimpleChange"), "*"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "nullRefArrayChange"), "+"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "strRefSimpleChange"), "*"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "strRefArrayChange"), "+"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "numRefSimpleChange"), "*"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "numRefArrayChange"), "+"));

    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityRefs", "nullAttrSimpleChange")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityRefs", "nullAttrArrayChange"), factory.createList()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityRefs", "strAttrSimpleChange"), factory.createPrimitiveType("String"), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityRefs", "strAttrArrayChange"), factory.createList(factory.createPrimitiveType("String")), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityRefs", "numAttrSimpleChange"), factory.createPrimitiveType("Number"), "33"));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityRefs", "numAttrArrayChange"), factory.createList(factory.createPrimitiveType("Number")), "44"));

    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "nullAttrSimpleChange"), "*"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "nullAttrArrayChange"), "+"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "strAttrSimpleChange"), "*"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "strAttrArrayChange"), "+"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "numAttrSimpleChange"), "*"));
    orion.getOperations().add(factory.createReferenceCardinalityOp(factory.createFeatureSelector("EntityRefs", "numAttrArrayChange"), "+"));

    orion.getOperations().add(factory.createEntityAddOp("EntityToAggr"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "aggrSimple"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr3", factory.createMap()),
        factory.createSimpleDataFeature("attr4", factory.createList())
        ), "+"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "aggrList"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr3", factory.createMap()),
        factory.createSimpleDataFeature("attr4", factory.createList())
        ), "*"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "aggrSimple.innerAggrSimple"), Arrays.asList(
        factory.createSimpleDataFeature("innerAttr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("innerAttr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("innerAttr3", factory.createMap()),
        factory.createSimpleDataFeature("innerAttr4", factory.createList())
        ), "+"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "aggrSimple.innerAggrList"), Arrays.asList(
        factory.createSimpleDataFeature("innerAttr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("innerAttr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("innerAttr3", factory.createMap()),
        factory.createSimpleDataFeature("innerAttr4", factory.createList())
        ), "*"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "strAggrSimpleChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2")
        ), "+"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "strAggrArrayChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2")
        ), "*"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "numAggrSimpleChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr2")
        ), "+"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityToAggr", "numAggrArrayChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr2")
        ), "*"));

    orion.getOperations().add(factory.createAggregateCardinalityOp(factory.createFeatureSelector("EntityToAggr", "strAggrSimpleChange"), "*"));
    orion.getOperations().add(factory.createAggregateCardinalityOp(factory.createFeatureSelector("EntityToAggr", "strAggrArrayChange"), "+"));
    orion.getOperations().add(factory.createAggregateCardinalityOp(factory.createFeatureSelector("EntityToAggr", "numAggrSimpleChange"), "*"));
    orion.getOperations().add(factory.createAggregateCardinalityOp(factory.createFeatureSelector("EntityToAggr", "numAggrArrayChange"), "+"));

    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityToAggr", "aggrByHand1.nullAttrSimpleChange")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityToAggr", "aggrByHand1.nullAttrArrayChange"), factory.createList()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityToAggr", "aggrByHand1.strAttrSimpleChange"), factory.createPrimitiveType("String"), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityToAggr", "aggrByHand1.strAttrArrayChange"), factory.createList(factory.createPrimitiveType("String")), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityToAggr", "aggrByHand2.numAttrSimpleChange"), factory.createPrimitiveType("Number"), "33"));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createFeatureSelector("EntityToAggr", "aggrByHand2.numAttrArrayChange"), factory.createList(factory.createPrimitiveType("Number")), "44"));

    orion.getOperations().add(factory.createAggregateCardinalityOp(factory.createFeatureSelector("EntityToAggr", "aggrByHand1"), "*"));

    orion.getOperations().add(factory.createEntityAddOp("EntityRef1", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"refId1\""),
        factory.createSimpleDataFeature("value1", factory.createPrimitiveType("String"), "\"value1\""),
        factory.createSimpleDataFeature("value2", factory.createPrimitiveType("String"), "\"value2\"")
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityRef2", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"refId2\""),
        factory.createSimpleDataFeature("value3", factory.createPrimitiveType("String"), "\"value3\""),
        factory.createSimpleDataFeature("value4", factory.createPrimitiveType("String"), "\"value4\"")
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityRef3", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"refId3\""),
        factory.createSimpleDataFeature("value5", factory.createPrimitiveType("String"), "\"value5\""),
        factory.createSimpleDataFeature("value6", factory.createPrimitiveType("String"), "\"value6\"")
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityRef4", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"refId4\""),
        factory.createSimpleDataFeature("value7", factory.createPrimitiveType("String"), "\"value7\""),
        factory.createSimpleDataFeature("value8", factory.createPrimitiveType("String"), "\"value8\"")
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityRef5", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"refId5\""),
        factory.createSimpleDataFeature("value9", factory.createPrimitiveType("String"), "\"value9\""),
        factory.createSimpleDataFeature("value10", factory.createPrimitiveType("String"), "\"value10\"")
        )));

    orion.getOperations().add(factory.createEntityAddOp("EntityMorphRef", Arrays.asList(
        factory.createSimpleDataFeature("_id"),
        factory.createSimpleDataFeature("refToEntity1", factory.createPrimitiveType("String"), "\"refId1\""),
        factory.createSimpleDataFeature("refToEntity2", factory.createPrimitiveType("String"), "\"refId2\""),
        factory.createSimpleDataFeature("refToEntity3", factory.createPrimitiveType("String"), "\"refId3\""),
        factory.createSimpleDataFeature("refToEntity4", factory.createList(factory.createPrimitiveType("String")), "\"refId4\""),
        factory.createSimpleDataFeature("refToEntity5", factory.createList(factory.createPrimitiveType("String")), "\"refId5\"")
        )));

    orion.getOperations().add(factory.createReferenceMorphOp(factory.createFeatureSelector("EntityMorphRef", "refToEntity1"), "EntityRef1", false, false));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createFeatureSelector("EntityMorphRef", "refToEntity2"), "EntityRef2", true, false));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createFeatureSelector("EntityMorphRef", "refToEntity3"), "EntityRef3", false, true));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createFeatureSelector("EntityMorphRef", "refToEntity4"), "EntityRef4", false, false));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createFeatureSelector("EntityMorphRef", "refToEntity5"), "EntityRef5", true, true));

    orion.getOperations().add(factory.createEntityAddOp("EntityToCopySource1", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"id1\""),
        factory.createSimpleDataFeature("valueToCopy1", factory.createPrimitiveType("String"), "\"COPY_THIS\""),
        factory.createSimpleDataFeature("valueToCopy2", factory.createList()),
        factory.createSimpleDataFeature("valueToCopy3", factory.createMap())
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityToCopySource2", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"id2\""),
        factory.createSimpleDataFeature("valueToCopy4", factory.createPrimitiveType("String"), "\"COPY_THAT\"")
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityToCopyTarget", Arrays.asList(
        factory.createSimpleDataFeature("thisRef", factory.createPrimitiveType("String"), "\"id1\""),
        factory.createSimpleDataFeature("thatRef", factory.createPrimitiveType("String"), "\"id2\"")
        )));

    orion.getOperations().add(factory.createFeatureCopyOp(
        factory.createFeatureSelector("EntityToCopySource1", "valueToCopy1"),
        factory.createFeatureSelector("EntityToCopyTarget", "copy1"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureCopyOp(
        factory.createFeatureSelector("EntityToCopySource1", "valueToCopy2"),
        factory.createFeatureSelector("EntityToCopyTarget", "copy2"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureCopyOp(
        factory.createFeatureSelector("EntityToCopySource1", "valueToCopy3"),
        factory.createFeatureSelector("EntityToCopyTarget", "copy3"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureCopyOp(
        factory.createFeatureSelector("EntityToCopySource2", "valueToCopy4"),
        factory.createFeatureSelector("EntityToCopyTarget", "valueToCopy4"),
        factory.createCondition("_id", "thatRef")));

    orion.getOperations().add(factory.createEntityAddOp("EntityToMoveSource1", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"id1\""),
        factory.createSimpleDataFeature("valueToMove1", factory.createPrimitiveType("String"), "\"MOVE_THIS\""),
        factory.createSimpleDataFeature("valueToMove2", factory.createList()),
        factory.createSimpleDataFeature("valueToMove3", factory.createMap())
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityToMoveSource2", Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("String"), "\"id2\""),
        factory.createSimpleDataFeature("valueToMove4", factory.createPrimitiveType("String"), "\"MOVE_THAT\"")
        )));
    orion.getOperations().add(factory.createEntityAddOp("EntityToMoveTarget", Arrays.asList(
        factory.createSimpleDataFeature("thisRef", factory.createPrimitiveType("String"), "\"id1\""),
        factory.createSimpleDataFeature("thatRef", factory.createPrimitiveType("String"), "\"id2\"")
        )));

    orion.getOperations().add(factory.createFeatureMoveOp(
        factory.createFeatureSelector("EntityToMoveSource1", "valueToMove1"),
        factory.createFeatureSelector("EntityToMoveTarget", "move1"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureMoveOp(
        factory.createFeatureSelector("EntityToMoveSource1", "valueToMove2"),
        factory.createFeatureSelector("EntityToMoveTarget", "move2"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureMoveOp(
        factory.createFeatureSelector("EntityToMoveSource1", "valueToMove3"),
        factory.createFeatureSelector("EntityToMoveTarget", "move3"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureMoveOp(
        factory.createFeatureSelector("EntityToMoveSource2", "valueToMove4"),
        factory.createFeatureSelector("EntityToMoveTarget", "move4"),
        factory.createCondition("_id", "thatRef")));

    orion.getOperations().add(factory.createEntityAddOp("EntityMorphAggr", Arrays.asList(factory.createSimpleDataFeature("_id"))));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityMorphAggr", "aggr1"), Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("string1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("string2", factory.createPrimitiveType("String"))
        ), "+"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityMorphAggr", "aggr2"), Arrays.asList(
        factory.createSimpleDataFeature("aList", factory.createList()),
        factory.createSimpleDataFeature("aMap", factory.createMap())
        ), "+"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityMorphAggr", "aggr3"), Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("string5", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("string6", factory.createPrimitiveType("String"))
        ), "*"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createFeatureSelector("EntityMorphAggr", "aggr4"), Arrays.asList(
        factory.createSimpleDataFeature("aSet", factory.createSet()),
        factory.createSimpleDataFeature("aTuple", factory.createTuple())
        ), "*"));

    orion.getOperations().add(factory.createAggregateMorphOp(factory.createFeatureSelector("EntityMorphAggr", "aggr1"), "MorphedAggregates"));
    orion.getOperations().add(factory.createAggregateMorphOp(factory.createFeatureSelector("EntityMorphAggr", "aggr2"), "MorphedAggregates"));
    orion.getOperations().add(factory.createAggregateMorphOp(factory.createFeatureSelector("EntityMorphAggr", "aggr3"), "MorphedAggregates"));
    orion.getOperations().add(factory.createAggregateMorphOp(factory.createFeatureSelector("EntityMorphAggr", "aggr4"), "MorphedAggregates"));

    System.out.println(new ModelSerializer().serialize(orion));
  }
}
