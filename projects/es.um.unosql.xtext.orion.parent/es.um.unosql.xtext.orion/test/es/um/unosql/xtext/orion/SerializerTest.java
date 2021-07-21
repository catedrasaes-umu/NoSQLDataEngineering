package es.um.unosql.xtext.orion;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.unosql.xtext.orion.orion.OrionOperations;
import es.um.unosql.xtext.orion.utils.OrionFactory;
import es.um.unosql.xtext.orion.utils.io.OrionIO;

public class SerializerTest
{
  private OrionFactory factory;

  @BeforeEach
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
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrSinTipo")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrStr"), factory.createPrimitiveType("String")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrNum"), factory.createPrimitiveType("Number")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrDouble"), factory.createPrimitiveType("Double")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrBool"), factory.createPrimitiveType("Boolean")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrSet"), factory.createSet()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrList"), factory.createList()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrTuple"), factory.createTuple()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrMap"), factory.createMap()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("Null"), factory.createNull()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrStrVal"), factory.createPrimitiveType("String"), "\"initValue\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrNumVal"), factory.createPrimitiveType("Number"), Integer.toString(33)));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrDoubleVal"), factory.createPrimitiveType("Double"), Double.toString(33.1)));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("addAttrBoolVal"), factory.createPrimitiveType("Boolean"), Boolean.toString(false)));

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

    orion.getOperations().add(factory.createFeatureDeleteOp(factory.createMultipleFeatureSelector("EntityToRemove", Arrays.asList("attrToDel", "attrToDelStr", "attrToDelNum", "attrToDelDouble", "attrToDelBool"))));

    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRen"), "attrFalse1"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenStr"), "attrFalse2"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenNum"), "attrFalse3"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenDouble"), "attrFalse4"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenBool"), "attrFalse5"));

    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRen"), "attrTrue1"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenStr"), "attrTrue2"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenNum"), "attrTrue3"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenDouble"), "attrTrue4"));
    orion.getOperations().add(factory.createFeatureRenameOp(factory.createSingleFeatureSelector("EntityToRemove", "attrToRenBool"), "attrTrue5"));

    orion.getOperations().add(factory.createEntityAddOp("EntityToCast", Arrays.asList(
        factory.createSimpleDataFeature("attrToCast"),
        factory.createSimpleDataFeature("attrToNull", factory.createNull()),
        factory.createSimpleDataFeature("attrToCastStr", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attrToCastNum", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attrToCastDouble", factory.createPrimitiveType("Double")),
        factory.createSimpleDataFeature("attrToCastBool", factory.createPrimitiveType("Boolean"))
        )));

    orion.getOperations().add(factory.createAttributeCastOp(factory.createMultipleFeatureSelector("EntityToCast", Arrays.asList("attrToCast", "attrToCastStr", "attrToCastDouble")), factory.createPrimitiveType("Bool")));
    orion.getOperations().add(factory.createAttributeCastOp(factory.createMultipleFeatureSelector("EntityToCast", Arrays.asList("attrToCastNull")), factory.createPrimitiveType("Number")));
    orion.getOperations().add(factory.createAttributeCastOp(factory.createMultipleFeatureSelector("EntityToCast", Arrays.asList("attrToCastNum", "attrToCastBool")), factory.createPrimitiveType("String")));

    orion.getOperations().add(factory.createEntityAddOp("EntityRefs"));

    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "nullRefSimple"), "&", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "nullRefArray"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "strRefSimple"), factory.createPrimitiveType("String"), "&", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "strRefArray"), factory.createPrimitiveType("String"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "numRefSimple"), factory.createPrimitiveType("Number"), "&", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "numRefArray"), factory.createPrimitiveType("Number"), "+", "addedEntity1"));

    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "nullRefSimpleChange"), "&", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "nullRefArrayChange"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "strRefSimpleChange"), factory.createPrimitiveType("String"), "&", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "strRefArrayChange"), factory.createPrimitiveType("String"), "+", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "numRefSimpleChange"), factory.createPrimitiveType("Number"), "&", "addedEntity1"));
    orion.getOperations().add(factory.createReferenceAddOp(factory.createSingleFeatureSelector("EntityRefs", "numRefArrayChange"), factory.createPrimitiveType("Number"), "+", "addedEntity1"));

    orion.getOperations().add(factory.createReferenceMultiplicityOp(factory.createMultipleFeatureSelector("EntityRefs", Arrays.asList("nullRefSimpleChange", "strRefSimpleChange", "numRefSimpleChange")), "+"));
    orion.getOperations().add(factory.createReferenceMultiplicityOp(factory.createMultipleFeatureSelector("EntityRefs", Arrays.asList("nullRefArrayChange", "strRefArrayChange", "numRefArrayChange")), "&"));

    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityRefs", "nullAttrSimpleChange")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityRefs", "nullAttrArrayChange"), factory.createList()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityRefs", "strAttrSimpleChange"), factory.createPrimitiveType("String"), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityRefs", "strAttrArrayChange"), factory.createList(factory.createPrimitiveType("String")), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityRefs", "numAttrSimpleChange"), factory.createPrimitiveType("Number"), "33"));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityRefs", "numAttrArrayChange"), factory.createList(factory.createPrimitiveType("Number")), "44"));

    orion.getOperations().add(factory.createReferenceMultiplicityOp(factory.createMultipleFeatureSelector("EntityRefs", Arrays.asList("nullAttrSimpleChange", "strAttrSimpleChange", "numAttrSimpleChange")), "+"));
    orion.getOperations().add(factory.createReferenceMultiplicityOp(factory.createMultipleFeatureSelector("EntityRefs", Arrays.asList("nullAttrArrayChange", "strAttrArrayChange", "numAttrArrayChange")), "&"));

    orion.getOperations().add(factory.createEntityAddOp("EntityToAggr"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrSimple"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr3", factory.createMap()),
        factory.createSimpleDataFeature("attr4", factory.createList())
        ), "&"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrList"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr3", factory.createMap()),
        factory.createSimpleDataFeature("attr4", factory.createList())
        ), "+"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrSimple.innerAggrSimple"), Arrays.asList(
        factory.createSimpleDataFeature("innerAttr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("innerAttr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("innerAttr3", factory.createMap()),
        factory.createSimpleDataFeature("innerAttr4", factory.createList())
        ), "&"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrSimple.innerAggrList"), Arrays.asList(
        factory.createSimpleDataFeature("innerAttr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("innerAttr2", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("innerAttr3", factory.createMap()),
        factory.createSimpleDataFeature("innerAttr4", factory.createList())
        ), "+"));

    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "strAggrSimpleChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2")
        ), "&"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "strAggrArrayChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("attr2")
        ), "+"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "numAggrSimpleChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr2")
        ), "&"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityToAggr", "numAggrArrayChange"), Arrays.asList(
        factory.createSimpleDataFeature("attr1", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("attr2")
        ), "+"));

    orion.getOperations().add(factory.createAggregateMultiplicityOp(factory.createMultipleFeatureSelector("EntityToAggr", Arrays.asList("strAggrSimpleChange")), "+"));
    orion.getOperations().add(factory.createAggregateMultiplicityOp(factory.createMultipleFeatureSelector("EntityToAggr", Arrays.asList("strAggrArrayChange")), "&"));
    orion.getOperations().add(factory.createAggregateMultiplicityOp(factory.createMultipleFeatureSelector("EntityToAggr", Arrays.asList("numAggrSimpleChange")), "+"));
    orion.getOperations().add(factory.createAggregateMultiplicityOp(factory.createMultipleFeatureSelector("EntityToAggr", Arrays.asList("numAggrArrayChange")), "&"));

    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrByHand1.nullAttrSimpleChange")));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrByHand1.nullAttrArrayChange"), factory.createList()));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrByHand1.strAttrSimpleChange"), factory.createPrimitiveType("String"), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrByHand1.strAttrArrayChange"), factory.createList(factory.createPrimitiveType("String")), "\"ref1\""));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrByHand2.numAttrSimpleChange"), factory.createPrimitiveType("Number"), "33"));
    orion.getOperations().add(factory.createAttributeAddOp(factory.createSingleFeatureSelector("EntityToAggr", "aggrByHand2.numAttrArrayChange"), factory.createList(factory.createPrimitiveType("Number")), "44"));

    orion.getOperations().add(factory.createAggregateMultiplicityOp(factory.createMultipleFeatureSelector("EntityToAggr", Arrays.asList("aggrByHand1")), "+"));

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

    orion.getOperations().add(factory.createReferenceMorphOp(factory.createSingleFeatureSelector("EntityMorphRef", "refToEntity1"), "EntityRef1", false, false));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createSingleFeatureSelector("EntityMorphRef", "refToEntity2"), "EntityRef2", true, false));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createSingleFeatureSelector("EntityMorphRef", "refToEntity3"), "EntityRef3", false, true));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createSingleFeatureSelector("EntityMorphRef", "refToEntity4"), "EntityRef4", false, false));
    orion.getOperations().add(factory.createReferenceMorphOp(factory.createSingleFeatureSelector("EntityMorphRef", "refToEntity5"), "EntityRef5", true, true));

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
        factory.createSingleFeatureSelector("EntityToCopySource1", "valueToCopy1"),
        factory.createSingleFeatureSelector("EntityToCopyTarget", "copy1"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureCopyOp(
        factory.createSingleFeatureSelector("EntityToCopySource1", "valueToCopy2"),
        factory.createSingleFeatureSelector("EntityToCopyTarget", "copy2"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureCopyOp(
        factory.createSingleFeatureSelector("EntityToCopySource1", "valueToCopy3"),
        factory.createSingleFeatureSelector("EntityToCopyTarget", "copy3"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureCopyOp(
        factory.createSingleFeatureSelector("EntityToCopySource2", "valueToCopy4"),
        factory.createSingleFeatureSelector("EntityToCopyTarget", "valueToCopy4"),
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
        factory.createSingleFeatureSelector("EntityToMoveSource1", "valueToMove1"),
        factory.createSingleFeatureSelector("EntityToMoveTarget", "move1"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureMoveOp(
        factory.createSingleFeatureSelector("EntityToMoveSource1", "valueToMove2"),
        factory.createSingleFeatureSelector("EntityToMoveTarget", "move2"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureMoveOp(
        factory.createSingleFeatureSelector("EntityToMoveSource1", "valueToMove3"),
        factory.createSingleFeatureSelector("EntityToMoveTarget", "move3"),
        factory.createCondition("_id", "thisRef")));
    orion.getOperations().add(factory.createFeatureMoveOp(
        factory.createSingleFeatureSelector("EntityToMoveSource2", "valueToMove4"),
        factory.createSingleFeatureSelector("EntityToMoveTarget", "move4"),
        factory.createCondition("_id", "thatRef")));

    orion.getOperations().add(factory.createEntityAddOp("EntityMorphAggr", Arrays.asList(factory.createSimpleDataFeature("_id"))));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr1"), Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("string1", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("string2", factory.createPrimitiveType("String"))
        ), "&"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr2"), Arrays.asList(
        factory.createSimpleDataFeature("aList", factory.createList()),
        factory.createSimpleDataFeature("aMap", factory.createMap())
        ), "&"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr3"), Arrays.asList(
        factory.createSimpleDataFeature("_id", factory.createPrimitiveType("Number")),
        factory.createSimpleDataFeature("string5", factory.createPrimitiveType("String")),
        factory.createSimpleDataFeature("string6", factory.createPrimitiveType("String"))
        ), "+"));
    orion.getOperations().add(factory.createAggregateAddOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr4"), Arrays.asList(
        factory.createSimpleDataFeature("aSet", factory.createSet()),
        factory.createSimpleDataFeature("aTuple", factory.createTuple())
        ), "+"));

    orion.getOperations().add(factory.createAggregateMorphOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr1"), "MorphedAggregates"));
    orion.getOperations().add(factory.createAggregateMorphOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr2"), "MorphedAggregates"));
    orion.getOperations().add(factory.createAggregateMorphOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr3"), "MorphedAggregates"));
    orion.getOperations().add(factory.createAggregateMorphOp(factory.createSingleFeatureSelector("EntityMorphAggr", "aggr4"), "MorphedAggregates"));

    System.out.println(new OrionIO().serialize(orion));
  }
}
