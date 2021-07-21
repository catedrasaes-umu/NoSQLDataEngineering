package es.um.unosql.xtext.orion.utils

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
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.orion.orion.SingleFeatureSelector
import es.um.unosql.xtext.orion.orion.MultipleFeatureSelector

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
    result.scriptMode = true

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
    result.spec = orionFactory.createEntityAddSpec
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
    result.spec = orionFactory.createEntityDeleteSpec
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
    result.spec = orionFactory.createEntityRenameSpec
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
    result.spec = orionFactory.createEntitySplitSpec
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
    result.spec = orionFactory.createEntityMergeSpec
    result.spec.ref1 = ref1
    result.spec.ref2 = ref2
    result.spec.name = name
    result.spec.condition = EcoreUtil2.copy(condition)

    return result
  }

  def createFeatureDeleteOp(MultipleFeatureSelector selector)
  {
    if (selector === null)
      throw new IllegalArgumentException("FeatureDeleteOp: FeatureSelector can't be null")

    val result = orionFactory.createFeatureDeleteOp
    result.spec = orionFactory.createFeatureDeleteSpec
    result.spec.selector = EcoreUtil2.copy(selector)

    return result
  }

  def createFeatureRenameOp(SingleFeatureSelector selector, String name)
  {
    if (selector === null)
      throw new IllegalArgumentException("FeatureRenameOp: FeatureSelector can't be null")

    if (name === null)
      throw new IllegalArgumentException("FeatureRenameOp: String can't be null")

    val result = orionFactory.createFeatureRenameOp
    result.spec = orionFactory.createFeatureRenameSpec
    result.spec.selector = EcoreUtil2.copy(selector)
    result.spec.name = name

    return result
  }

  def createFeatureCopyOp(SingleFeatureSelector sSelector, SingleFeatureSelector tSelector, ConditionDecl cond)
  {
    val result = orionFactory.createFeatureCopyOp
    result.spec = createFeatureAllocateSpec(sSelector, tSelector, cond)

    return result
  }

  def createFeatureMoveOp(SingleFeatureSelector sSelector, SingleFeatureSelector tSelector, ConditionDecl cond)
  {
    val result = orionFactory.createFeatureMoveOp
    result.spec = createFeatureAllocateSpec(sSelector, tSelector, cond)

    return result
  }

  def createAttributeAddOp(SingleFeatureSelector selector)
  {
    if (selector === null)
      throw new IllegalArgumentException("AttributeAddOp: FeatureSelector cannot be null")

    val result = orionFactory.createAttributeAddOp
    result.spec = orionFactory.createAttributeAddSpec
    result.spec.selector = EcoreUtil2.copy(selector)

    return result
  }

  def createAttributeAddOp(SingleFeatureSelector selector, DataType type)
  {
    val result = createAttributeAddOp(selector)

    if (type !== null)
      result.spec.type = EcoreUtil2.copy(type)

    return result
  }

  def createAttributeAddOp(SingleFeatureSelector selector, DataType type, String value)
  {
    val result = createAttributeAddOp(selector, type)
    result.spec.defaultValue = value

    return result
  }

  def createAttributeAddOp(SingleFeatureSelector selector, DataType type, String value, boolean key, boolean optional, boolean unique)
  {
    val result = createAttributeAddOp(selector, type, value)
    result.spec.key = key
    result.spec.optional = optional
    result.spec.unique = unique

    return result
  }

  def createAttributeCastOp(MultipleFeatureSelector selector, PrimitiveType type)
  {
    val result = orionFactory.createAttributeCastOp
    result.spec = createAttrOrRefCast(selector, type)

    return result
  }

  def createReferenceCastOp(MultipleFeatureSelector selector, PrimitiveType type)
  {
    val result = orionFactory.createReferenceCastOp
    result.spec = createAttrOrRefCast(selector, type)

    return result
  }

  def createReferenceAddOp(SingleFeatureSelector selector, String multiplicity, String ref)
  {
    if (selector === null)
      throw new IllegalArgumentException("ReferenceAddOp: FeatureSelector can't be null")

    if (multiplicity === null)
      throw new IllegalArgumentException("ReferenceAddOp: Multiplicity can't be null")

    if (ref === null)
      throw new IllegalArgumentException("ReferenceAddOp: EntityRef can't be null")

    val result = orionFactory.createReferenceAddOp
    result.spec = orionFactory.createReferenceAddSpec
    result.spec.selector = EcoreUtil2.copy(selector)
    result.spec.multiplicity = multiplicity
    result.spec.ref = ref

    return result
  }

  def createReferenceAddOp(SingleFeatureSelector selector, PrimitiveType type, String multiplicity, String ref)
  {
    if (type === null)
      throw new IllegalArgumentException("ReferenceAddOp: PrimitiveType can't be null")

    val result = createReferenceAddOp(selector, multiplicity, ref)
    result.spec.type = type

    return result
  }

  def createReferenceAddOp(SingleFeatureSelector selector, PrimitiveType type, String multiplicity, Boolean optional, String ref)
  {
    val result = createReferenceAddOp(selector, type, multiplicity, ref)
    result.spec.optional = optional

    return result
  }

  def createReferenceMultiplicityOp(MultipleFeatureSelector selector, String multiplicity)
  {
    val result = orionFactory.createReferenceMultiplicityOp
    result.spec = createRefOrAggrMultiplicity(selector, multiplicity)

    return result
  }

  def createReferenceMorphOp(SingleFeatureSelector selector, String name, Boolean delId, Boolean delEntity)
  {
    if (selector === null)
      throw new IllegalArgumentException("ReferenceMorphOp: FeatureSelector can't be null")

    if (delId === null)
      throw new IllegalArgumentException("ReferenceMorphOp: DeleteId can't be null")

    if (delEntity === null)
      throw new IllegalArgumentException("ReferenceMorphOp: DeleteEntity can't be null")

    val result = orionFactory.createReferenceMorphOp
    result.spec = orionFactory.createReferenceMorphSpec
    result.spec.selector = EcoreUtil2.copy(selector)
    result.spec.name = name
    result.spec.deleteId = delId
    result.spec.deleteEntity = delEntity

    return result
  }

  def createAggregateAddOp(SingleFeatureSelector selector, String multiplicity)
  {
    if (selector === null)
      throw new IllegalArgumentException("AggregateAddOp: FeatureSelector can't be null")

    if (multiplicity === null)
      throw new IllegalArgumentException("AggregateAddOp: Multiplicity can't be null")

    val result = orionFactory.createAggregateAddOp
    result.spec = orionFactory.createAggregateAddSpec
    result.spec.selector = EcoreUtil2.copy(selector)
    result.spec.multiplicity = multiplicity

    return result
  }

  def createAggregateAddOp(SingleFeatureSelector selector, java.util.List<SimpleDataFeature> feats, String multiplicity)
  {
    if (feats === null || feats.isEmpty)
      throw new IllegalArgumentException("AggregateAddOp: Features can't be null")

    val result = createAggregateAddOp(selector, multiplicity)
    result.spec.features.addAll(feats)

    return result
  }

  def createAggregateAddOp(SingleFeatureSelector selector, java.util.List<SimpleDataFeature> feats, String multiplicity, Boolean optional, String aggrTargetName)
  {
    val result = createAggregateAddOp(selector, feats, multiplicity)
    result.spec.optional = optional

    if (aggrTargetName !== null)
      result.spec.name = aggrTargetName

    return result
  }

  def createAggregateMultiplicityOp(MultipleFeatureSelector selector, String multiplicity)
  {
    val result = orionFactory.createAggregateMultiplicityOp
    result.spec = createRefOrAggrMultiplicity(selector, multiplicity)

    return result
  }

  def createAggregateMorphOp(SingleFeatureSelector selector, String name)
  {
    if (selector === null)
      throw new IllegalArgumentException("AggregateMorphOp: FeatureSelector can't be null")

    val result = orionFactory.createAggregateMorphOp
    result.spec = orionFactory.createAggregateMorphSpec
    result.spec.selector = EcoreUtil2.copy(selector)
    result.spec.name = name

    return result
  }

  def createRefOrAggrMultiplicity(MultipleFeatureSelector selector, String multiplicity)
  {
    if (selector === null)
      throw new IllegalArgumentException("RefOrAggrCard: FeatureSelector can't be null")

    if (multiplicity === null)
      throw new IllegalArgumentException("RefOrAggrCard: Multiplicity can't be null")

    val result = orionFactory.createReferenceOrAggregateMultiplicitySpec
    result.selector = EcoreUtil2.copy(selector)
    result.multiplicity = multiplicity

    return result
  }

  def createAttrOrRefCast(MultipleFeatureSelector selector, PrimitiveType type)
  {
    if (selector === null)
      throw new IllegalArgumentException("AttrOrRefCastOp: FeatureSelector can't be null")

    if (type === null)
      throw new IllegalArgumentException("AttrOrRefCastOp: PrimitiveType can't be null")

    val result = orionFactory.createAttributeOrReferenceCastSpec
    result.selector = EcoreUtil2.copy(selector)
    result.type = EcoreUtil2.copy(type)

    return result
  }

  def createFeatureAllocateSpec(SingleFeatureSelector sSelector, SingleFeatureSelector tSelector, ConditionDecl cond)
  {
    if (sSelector === null)
      throw new IllegalArgumentException("FeatureAllocateOp: FeatureSelector sSelector can't be null")

    if (tSelector === null)
      throw new IllegalArgumentException("FeatureAllocateOp: FeatureSelector tSelector can't be null")

    if (cond === null)
      throw new IllegalArgumentException("FeatureAllocateOp: ConditionDecl can't be null")

    val result = orionFactory.createFeatureAllocateSpec
    result.sourceSelector = EcoreUtil2.copy(sSelector)
    result.targetSelector = EcoreUtil2.copy(tSelector)
    result.condition = EcoreUtil2.copy(cond)

    return result
  }

  def createSplitFeatures(java.util.List<String> feats)
  {
    val result = orionFactory.createSplitFeatures
    result.features.addAll(feats)

    return result
  }

  def createSingleFeatureSelector(String ref, java.util.List<String> variationList, String decl)
  {
    val result = orionFactory.createSingleFeatureSelector
    result.ref = ref
    result.variations.addAll(variationList)
    result.target = decl

    return result
  }

  def createSingleFeatureSelector(String ref, String decl)
  {
    val result = orionFactory.createSingleFeatureSelector
    result.ref = ref
    result.target = decl

    return result
  }

  def createSingleFeatureSelector(String decl)
  {
    val result = orionFactory.createSingleFeatureSelector
    result.forAll = true
    result.target = decl

    return result
  }

  def createMultipleFeatureSelector(String ref, java.util.List<String> variationList, java.util.List<String> targets)
  {
    val result = orionFactory.createMultipleFeatureSelector
    result.ref = ref
    result.variations.addAll(variationList)
    result.targets.addAll(targets)

    return result
  }

  def createMultipleFeatureSelector(String ref, java.util.List<String> targets)
  {
    val result = orionFactory.createMultipleFeatureSelector
    result.ref = ref
    result.targets.addAll(targets)

    return result
  }

  def createMultipleFeatureSelector(java.util.List<String> targets)
  {
    val result = orionFactory.createMultipleFeatureSelector
    result.forAll = true
    result.targets.addAll(targets)

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
      result.type = EcoreUtil2.copy(type)

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
