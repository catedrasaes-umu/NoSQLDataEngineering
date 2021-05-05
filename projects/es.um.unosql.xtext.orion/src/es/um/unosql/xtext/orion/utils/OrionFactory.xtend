package es.um.unosql.xtext.orion.utils

import es.um.unosql.xtext.orion.orion.FeatureSelector
import es.um.unosql.xtext.orion.orion.ConditionDecl
import es.um.unosql.xtext.orion.orion.SplitFeatures
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.orion.orion.SimpleDataFeature

class OrionFactory
{
  val orionFactory = es.um.unosql.xtext.orion.orion.OrionFactory.eINSTANCE
  val athenaFactory = new AthenaFactory()

  def createOrionOperations(String name)
  {
    if (name === null)
      throw new IllegalArgumentException("OrionOperations: Name can't be null")

    val result = orionFactory.createOrionOperations
    result.name = name

    return result
  }

  def createEvolBlock(int index)
  {
    if (index < 1)
      throw new IllegalArgumentException("EvolBlock: Index has to be greater than 0")

    val result = orionFactory.createEvolBlock
    result.name = index

    return result
  }

  def createEntityAddOp(String name)
  {
    if (name === null)
      throw new IllegalArgumentException("EntityAddOp: EntityName can't be null")

    val result = orionFactory.createEntityAddOp
    result.spec = orionFactory.createEntityAdd
    result.spec.name = name

    return result
  }

  def createEntityAddOp(String name, java.util.List<SimpleDataFeature> features)
  {
    if (name === null)
      throw new IllegalArgumentException("EntityAddOp: EntityName can't be null")

    if (features === null)
      throw new IllegalArgumentException("EntityAddOp: Features can't be null")

    val result = createEntityAddOp(name)
    result.spec.features.addAll(features)

    return result
  }

  def createEntityDeleteOp(String ref)
  {
    if (ref === null)
      throw new IllegalArgumentException("EntityDeleteOp: EntityRef can't be null")

    val result = orionFactory.createEntityDeleteOp
    result.spec = orionFactory.createEntityDel
    result.spec.ref = ref

    return result
  }

  def createEntityRenameOp(String ref, String name)
  {
    if (ref === null)
      throw new IllegalArgumentException("EntityDeleteOp: EntityRef can't be null")

    if (name === null)
      throw new IllegalArgumentException("EntityDeleteOp: String can't be null")

    val result = orionFactory.createEntityRenameOp
    result.spec = orionFactory.createEntityRename
    result.spec.ref = ref
    result.spec.name = name

    return result
  }

  def createEntitySplitOp(String ref, String name1, SplitFeatures feats1, String name2, SplitFeatures feats2)
  {
    if (ref === null)
      throw new IllegalArgumentException("EntitySplitOp: EntityRef can't be null")

    if (name1 === null)
      throw new IllegalArgumentException("EntitySplitOp: EntityName name1 can't be null")

    if (feats1 === null)
      throw new IllegalArgumentException("EntitySplitOp: SplitFeatures feats1 can't be null")

    if (name2 === null)
      throw new IllegalArgumentException("EntitySplitOp: EntityName name2 can't be null")

    if (feats2 === null)
      throw new IllegalArgumentException("EntitySplitOp: SplitFeatures feats2 can't be null")

    val result = orionFactory.createEntitySplitOp
    result.spec = orionFactory.createEntitySplit
    result.spec.ref = ref
    result.spec.name1 = name1
    result.spec.features1 = feats1
    result.spec.name2 = name2
    result.spec.features2 = feats2

    return result
  }

  def createEntitySplitOp(String ref, String name1, java.util.List<String> feats1, String name2, java.util.List<String> feats2)
  {
    if (feats1 === null)
      throw new IllegalArgumentException("EntitySplitOp: List<FeatureDecl> feats1 can't be null")

    if (feats2 === null)
      throw new IllegalArgumentException("EntitySplitOp: List<FeatureDecl> feats2 can't be null")

    return createEntitySplitOp(ref, name1, createSplitFeatures(feats1), name2, createSplitFeatures(feats2))
  }

  def createEntityMergeOp(String ref1, String ref2, String name, ConditionDecl condition)
  {
    if (ref1 === null)
      throw new IllegalArgumentException("EntityMergeOp: EntityRef ref1 can't be null")

    if (ref2 === null)
      throw new IllegalArgumentException("EntityMergeOp: EntityRef ref2 can't be null")

    if (name === null)
      throw new IllegalArgumentException("EntityMergeOp: String can't be null")

    if (condition === null)
      throw new IllegalArgumentException("EntityMergeOp: ConditionDecl can't be null")

    val result = orionFactory.createEntityMergeOp
    result.spec = orionFactory.createEntityMerge
    result.spec.ref1 = ref1
    result.spec.ref2 = ref2
    result.spec.name = name
    result.spec.condition = condition

    return result
  }

  def createFeatureDeleteOp(FeatureSelector selector)
  {
    if (selector === null)
      throw new IllegalArgumentException("FeatureDeleteOp: FeatureSelector can't be null")

    val result = orionFactory.createFeatureDeleteOp
    result.spec = orionFactory.createFeatureDel
    result.spec.selector = selector

    return result
  }

  def createFeatureRenameOp(FeatureSelector selector, String name)
  {
    if (selector === null)
      throw new IllegalArgumentException("FeatureRenameOp: FeatureSelector can't be null")

    if (name === null)
      throw new IllegalArgumentException("FeatureRenameOp: String can't be null")

    val result = orionFactory.createFeatureRenameOp
    result.spec = orionFactory.createFeatureRename
    result.spec.selector = selector
    result.spec.name = name

    return result
  }

  def createFeatureCopyOp(FeatureSelector sSelector, FeatureSelector tSelector, ConditionDecl cond)
  {
    val result = orionFactory.createFeatureCopyOp
    result.spec = createFeatureAllocate(sSelector, tSelector, cond)

    return result
  }

  def createFeatureMoveOp(FeatureSelector sSelector, FeatureSelector tSelector, ConditionDecl cond)
  {
    val result = orionFactory.createFeatureMoveOp
    result.spec = createFeatureAllocate(sSelector, tSelector, cond)

    return result
  }

  def createAttributeAddOp(FeatureSelector selector)
  {
    if (selector === null)
      throw new IllegalArgumentException("AttributeAddOp: FeatureSelector can't be null")

    val result = orionFactory.createAttributeAddOp
    result.spec = orionFactory.createAttrAdd
    result.spec.selector = selector

    return result
  }

  def createAttributeAddOp(FeatureSelector selector, DataType type)
  {
    val result = createAttributeAddOp(selector)

    if (type !== null)
      result.spec.type = type

    return result
  }

  def createAttributeAddOp(FeatureSelector selector, DataType type, String value)
  {
    if (value === null)
      throw new IllegalArgumentException("AttributeAddOp: Default value can't be null")

    val result = createAttributeAddOp(selector, type)
    result.spec.defaultValue = value

    return result
  }

  def createAttributeAddOp(FeatureSelector selector, DataType type, String value, boolean optional)
  {
    val result = createAttributeAddOp(selector, type, value)
    result.spec.optional = optional

    return result
  }

  def createAttributeCastOp(FeatureSelector selector, PrimitiveType type)
  {
    val result = orionFactory.createAttributeCastOp
    result.spec = createAttrOrRefCast(selector, type)

    return result
  }

  def createReferenceCastOp(FeatureSelector selector, PrimitiveType type)
  {
    val result = orionFactory.createReferenceCastOp
    result.spec = createAttrOrRefCast(selector, type)

    return result
  }

  def createReferenceAddOp(FeatureSelector selector, String cardinality, String ref)
  {
    if (selector === null)
      throw new IllegalArgumentException("ReferenceAddOp: FeatureSelector can't be null")

    if (cardinality === null)
      throw new IllegalArgumentException("ReferenceAddOp: Cardinality can't be null")

    if (ref === null)
      throw new IllegalArgumentException("ReferenceAddOp: EntityRef can't be null")

    val result = orionFactory.createReferenceAddOp
    result.spec = orionFactory.createRefAdd
    result.spec.selector = selector
    result.spec.cardinality = cardinality
    result.spec.ref = ref

    return result
  }

  def createReferenceAddOp(FeatureSelector selector, PrimitiveType type, String cardinality, String ref)
  {
    if (type === null)
      throw new IllegalArgumentException("ReferenceAddOp: PrimitiveType can't be null")

    val result = createReferenceAddOp(selector, cardinality, ref)
    result.spec.type = type

    return result
  }

  def createReferenceAddOp(FeatureSelector selector, PrimitiveType type, String cardinality, Boolean optional, String ref)
  {
    val result = createReferenceAddOp(selector, type, cardinality, ref)
    result.spec.optional = optional

    return result
  }

  def createReferenceCardinalityOp(FeatureSelector selector, String cardinality)
  {
    val result = orionFactory.createReferenceCardinalityOp
    result.spec = createRefOrAggrCard(selector, cardinality)

    return result
  }

  def createReferenceMorphOp(FeatureSelector selector, String name, Boolean delId, Boolean delEntity)
  {
    if (selector === null)
      throw new IllegalArgumentException("ReferenceMorphOp: FeatureSelector can't be null")

    if (delId === null)
      throw new IllegalArgumentException("ReferenceMorphOp: DeleteId can't be null")

    if (delEntity === null)
      throw new IllegalArgumentException("ReferenceMorphOp: DeleteEntity can't be null")

    val result = orionFactory.createReferenceMorphOp
    result.spec = orionFactory.createRefMorph
    result.spec.selector = selector
    result.spec.name = name
    result.spec.deleteId = delId
    result.spec.deleteEntity = delEntity

    return result
  }

  def createAggregateAddOp(FeatureSelector selector, String cardinality)
  {
    if (selector === null)
      throw new IllegalArgumentException("AggregateAddOp: FeatureSelector can't be null")

    if (cardinality === null)
      throw new IllegalArgumentException("AggregateAddOp: Cardinality can't be null")

    val result = orionFactory.createAggregateAddOp
    result.spec = orionFactory.createAggrAdd
    result.spec.selector = selector
    result.spec.cardinality = cardinality

    return result
  }

  def createAggregateAddOp(FeatureSelector selector, java.util.List<SimpleDataFeature> feats, String cardinality)
  {
    if (feats === null || feats.isEmpty)
      throw new IllegalArgumentException("AggregateAddOp: Features can't be null")

    val result = createAggregateAddOp(selector, cardinality)
    result.spec.features.addAll(feats)

    return result
  }

  def createAggregateAddOp(FeatureSelector selector, java.util.List<SimpleDataFeature> feats, String cardinality, Boolean optional)
  {
    val result = createAggregateAddOp(selector, feats, cardinality)
    result.spec.optional = optional

    return result
  }

  def createAggregateCardinalityOp(FeatureSelector selector, String cardinality)
  {
    val result = orionFactory.createAggregateCardinalityOp
    result.spec = createRefOrAggrCard(selector, cardinality)

    return result
  }

  def createAggregateMorphOp(FeatureSelector selector, String name)
  {
    if (selector === null)
      throw new IllegalArgumentException("AggregateMorphOp: FeatureSelector can't be null")

    val result = orionFactory.createAggregateMorphOp
    result.spec = orionFactory.createAggrMorph
    result.spec.selector = selector
    result.spec.name = name

    return result
  }

  def createRefOrAggrCard(FeatureSelector selector, String cardinality)
  {
    if (selector === null)
      throw new IllegalArgumentException("RefOrAggrCard: FeatureSelector can't be null")

    if (cardinality === null)
      throw new IllegalArgumentException("RefOrAggrCard: Cardinality can't be null")

    val result = orionFactory.createRefOrAggrCard
    result.selector = selector
    result.cardinality = cardinality

    return result
  }

  def createAttrOrRefCast(FeatureSelector selector, PrimitiveType type)
  {
    if (selector === null)
      throw new IllegalArgumentException("AttrOrRefCastOp: FeatureSelector can't be null")

    if (type === null)
      throw new IllegalArgumentException("AttrOrRefCastOp: PrimitiveType can't be null")

    val result = orionFactory.createAttrOrRefCast
    result.selector = selector
    result.type = type

    return result
  }

  def createFeatureAllocate(FeatureSelector sSelector, FeatureSelector tSelector, ConditionDecl cond)
  {
    if (sSelector === null)
      throw new IllegalArgumentException("FeatureAllocateOp: FeatureSelector sSelector can't be null")

    if (tSelector === null)
      throw new IllegalArgumentException("FeatureAllocateOp: FeatureSelector tSelector can't be null")

    if (cond === null)
      throw new IllegalArgumentException("FeatureAllocateOp: ConditionDecl can't be null")

    val result = orionFactory.createFeatureAllocate
    result.sourceSelector = sSelector
    result.targetSelector = tSelector
    result.condition = cond

    return result
  }

  def createSplitFeatures(java.util.List<String> feats)
  {
    val result = orionFactory.createSplitFeatures
    result.features.addAll(feats)

    return result
  }

  def createFeatureSelector(String ref, String decl)
  {
    val result = orionFactory.createFeatureSelector
    result.ref = ref
    result.target = decl

    return result
  }

  def createFeatureSelector(String decl)
  {
    val result = orionFactory.createFeatureSelector
    result.forAll = true
    result.target = decl

    return result
  }

  def createCondition(String c1, String c2)
  {
    if (c1 === null)
      throw new IllegalArgumentException("ConditionDecl: String c1 can't be null")

    if (c2 === null)
      throw new IllegalArgumentException("ConditionDecl: String c2 can't be null")

    val result = orionFactory.createConditionDecl
    result.c1 = c1
    result.c2 = c2

    return result
  }

  def SimpleDataFeature createSimpleDataFeature(String name)
  {
    if (name === null)
      throw new IllegalArgumentException("Create SimpleDataFeature: name can't be null")

    val result = orionFactory.createSimpleDataFeature
    result.name = name

    return result
  }

  def SimpleDataFeature createSimpleDataFeature(String name, DataType type)
  {
    val result = createSimpleDataFeature(name)

    if (type !== null)
      result.type = type

    return result
  }

  def SimpleDataFeature createSimpleDataFeature(String name, DataType type, String defaultValue)
  {
    val result = createSimpleDataFeature(name, type)

    if (defaultValue !== null)
      result.defaultValue = defaultValue

    return result
  }

  def createPrimitiveType(String type)
  {
    return athenaFactory.createUnrestrictedPrimitiveType(type)
  }

  def Null createNull()
  {
    return athenaFactory.createNull
  }

  def List createList()
  {
    return athenaFactory.createList
  }

  def List createList(DataType dType)
  {
    return athenaFactory.createList(dType)
  }

  def Set createSet()
  {
    return athenaFactory.createSet
  }

  def Set createSet(DataType dType)
  {
    return athenaFactory.createSet(dType)
  }

  def Map createMap()
  {
    return athenaFactory.createMap
  }

  def Map createMap(PrimitiveType key, DataType value)
  {
    return athenaFactory.createMap(key, value)
  }

  def Tuple createTuple()
  {
    return athenaFactory.createTuple
  }

  def Tuple createTuple(DataType... datatypes)
  {
    return athenaFactory.createTuple(datatypes)
  }
}
