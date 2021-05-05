package es.um.unosql.xtext.orion.utils

import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.orion.utils.io.ModelSerializer
import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.orion.orion.EntityDeleteOp
import es.um.unosql.xtext.orion.orion.EntityRenameOp
import es.um.unosql.xtext.orion.orion.EntitySplitOp
import es.um.unosql.xtext.athena.athena.SimpleFeature
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregationTarget
import es.um.unosql.xtext.orion.orion.EntityExtractOp
import es.um.unosql.xtext.orion.orion.EntityMergeOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import java.util.ArrayList
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceCardinalityOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.AggregateCardinalityOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.orion.orion.SimpleDataFeature
import es.um.unosql.xtext.athena.athena.PrimitiveType
import org.eclipse.emf.ecore.util.EcoreUtil

class AthenaSchemaUpdater
{
  AthenaSchema schema
  val serializer = new ModelSerializer
  val handler = new AthenaHandler
  val athenaFactory = new AthenaFactory
  val orionFactory = new OrionFactory

  new() //TODO: Maybe create a boolean flag indicating security or not: If TRUE, an exception is thrown if, for example, the user deletes a non-existing entity.
  {
  }

  new(AthenaSchema schema)
  {
    this.schema = schema
  }

  def void setSchema(AthenaSchema schema)
  {
    this.schema = schema
  }

  def AthenaSchema getSchema()
  {
    return this.schema
  }

  def dispatch void processOperation(EntityAddOp op)
  {
    if (schema.entities.exists[e | e.name.equals(op.spec.name)])
      throw new IllegalArgumentException("EntityAddOp - Cannot create an Entity with the same name as an existing entity: " + serializer.serialize(op))

    val result = athenaFactory.createShortEntityDecl(op.spec.name, true)
    schema.entities.add(result)

    for (feat : op.spec.features)
      handler.addFeatureToSchemaType(result, processFeature(feat), false)
  }

  def dispatch void processOperation(EntityDeleteOp op)
  {
    val entity = handler.getEntityDecl(schema, op.spec.ref)
    if (entity === null)
      throw new IllegalArgumentException("EntityDeleteOp - Cannot delete an Entity that does not exist: " + serializer.serialize(op))

    schema.entities.remove(entity)
  }

  def dispatch void processOperation(EntityRenameOp op)
  {
    val entity = handler.getEntityDecl(schema, op.spec.ref)
    if (entity === null)
      throw new IllegalArgumentException("EntityRenameOp - Cannot rename an Entity which does not exist: " + serializer.serialize(op))

    entity.name = op.spec.name
  }

  def dispatch void processOperation(EntitySplitOp op)
  {
    // Exception if the entity does not exist.
    val sEntity = handler.getEntityDecl(schema, op.spec.ref)
    if (sEntity === null)
      throw new IllegalArgumentException("EntitySplitOp - Cannot split an Entity that does not exist: " + serializer.serialize(op))

    // Exception if one of the splitted entities already exist.
    if (schema.entities.exists[e | e.name.equals(op.spec.name1)] || schema.entities.exists[e | e.name.equals(op.spec.name2)])
      throw new IllegalArgumentException("EntitySplitOp - Cannot split to an Entity that already exists: " + serializer.serialize(op))

    val featsToSplit = handler.getReducedFeaturesFromSchemaTypeAndVariations(sEntity).filter(SimpleFeature)

    // For each of the two new entities: create a new short entity and clone each of the features to be moved to the splitted entity
    val newEntity1 = athenaFactory.createShortEntityDecl(op.spec.name1, sEntity.root)
    for (featName : op.spec.features1.features)
    {
      val featToSplit = featsToSplit.findFirst[f | f.name.equals(featName)]
      if (featToSplit === null)
        throw new IllegalArgumentException("EntitySplitOp - Cannot split Feature \"" + featName + "\" because it does not exist: " + serializer.serialize(op))

      handler.addFeatureToSchemaType(newEntity1, featToSplit, true)
    }

    val newEntity2 = athenaFactory.createShortEntityDecl(op.spec.name2, sEntity.root)
    for (featName : op.spec.features2.features)
    {
      val featToSplit = featsToSplit.findFirst[f | f.name.equals(featName)]
      if (featToSplit === null)
        throw new IllegalArgumentException("EntitySplitOp - Cannot split Feature \"" + featName + "\" because it does not exist: " + serializer.serialize(op))

      handler.addFeatureToSchemaType(newEntity2, featToSplit, true)
    }

    EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget).filter[r | r.ref === sEntity].forEach[r | r.ref = newEntity1]
    // The reference type should still point to the key feature type, so no need to change it.

    EcoreUtil2.getAllContentsOfType(schema, SimpleAggregationTarget).filter[r | r.aggr.contains(sEntity)].forEach[r | r.aggr.clear; r.aggr.add(newEntity1)]

    schema.entities.remove(sEntity)
    schema.entities.add(newEntity1)
    schema.entities.add(newEntity2)
  }

  def dispatch void processOperation(EntityExtractOp op)
  {
    // Exception if the entity does not exist.
    val sEntity = handler.getEntityDecl(schema, op.spec.ref)
    if (sEntity === null)
      throw new IllegalArgumentException("EntityExtractOp - Cannot extract from an Entity that does not exist: " + serializer.serialize(op))

    // Exception if one of the splitted entities already exist.
    if (schema.entities.exists[e | e.name.equals(op.spec.name)])
      throw new IllegalArgumentException("EntityExtractOp - Cannot extract to an Entity that already exists: " + serializer.serialize(op))

    val featsToSplit = handler.getReducedFeaturesFromSchemaTypeAndVariations(sEntity).filter(SimpleFeature)

    // - Create a new short entity and clone each of the features to be moved to the extracted entity
    val newEntity = athenaFactory.createShortEntityDecl(op.spec.name, sEntity.root)
    for (featName : op.spec.features.features)
    {
      val featToExtract = featsToSplit.findFirst[f | f.name.equals(featName)]
      if (featToExtract === null)
        throw new IllegalArgumentException("EntityExtractOp - Cannot extract Feature \"" + featName + "\" because it does not exist: " + serializer.serialize(op))

      handler.addFeatureToSchemaType(newEntity, featToExtract, true)
    } // We do not remove any features, so Extraction does not remove anything.

    schema.entities.add(newEntity)
  }

  def dispatch void processOperation(EntityMergeOp op)
  {
    val sEntity1 = handler.getEntityDecl(schema, op.spec.ref1)
    val sEntity2 = handler.getEntityDecl(schema, op.spec.ref2)

    // Exception if one of the source entities do not exist, or the merged entity does already exist.
    if (sEntity1 === null || sEntity2 === null)
      throw new IllegalArgumentException("EntityMergeOp - Cannot merge an Entity because it does not exist: " + serializer.serialize(op))

    if (schema.entities.exists[e | e.name.equals(op.spec.name)])
      throw new IllegalArgumentException("EntityMergeOp - Cannot merge to an Entity that already exists: " + serializer.serialize(op))

    val newEntity = athenaFactory.createShortEntityDecl(op.spec.name, sEntity1.root || sEntity2.root)
    schema.entities.add(newEntity)

    // For each feature of the merged entities, move the first occurrence to the new merged Entity
    for (feat : (handler.getFeaturesFromSchemaTypeAndVariations(sEntity1) + handler.getFeaturesFromSchemaTypeAndVariations(sEntity2)).filter(SimpleFeature))
      if (handler.getSimpleFeatureFromSchemaType(newEntity, feat.name).empty)
        handler.addFeatureToSchemaType(newEntity, feat, true)

    // For each Aggregate and Reference, move them to point to the new merged Entity
    EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget).filter[r | r.ref === sEntity1 || r.ref === sEntity2].forEach[r | r.ref = newEntity]

    EcoreUtil2.getAllContentsOfType(schema, SimpleAggregationTarget).filter[r | r.aggr.contains(sEntity1) || r.aggr.contains(sEntity2)].forEach[r | r.aggr.clear; r.aggr.add(newEntity)]

    // Remove merging entities
    schema.entities.remove(sEntity1)
    schema.entities.remove(sEntity2)
  }

  def dispatch void processOperation(FeatureDeleteOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("FeatureDeleteOp - Cannot delete features of an Entity that does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("FeatureDeleteOp - Cannot find a Feature to delete: " + serializer.serialize(op))

    for (entity : entities)
      handler.removeSimpleFeatureFromSchemaType(entity, op.spec.selector.target)
  }

  def dispatch void processOperation(FeatureRenameOp op)//TODO: A problem with rename is that if after renaming an aggregate you try to modify that aggregate, it is not found anymore.
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("FeatureRenameOp - Cannot rename features of an Entity that does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("FeatureRenameOp - Cannot find a Feature to rename: " + serializer.serialize(op))

    for (entity : entities)
    {
      handler.getFeaturesFromSchemaType(entity).filter(SimpleFeature).filter(f | f.name.equals(op.spec.selector.target)).forEach[f | f.name = op.spec.name]

      if (entity instanceof RegularEntityDecl)
        for (variation : (entity as RegularEntityDecl).variations)
          handler.getFeaturesFromVariation(variation).filter(SimpleFeature).filter(f | f.name.equals(op.spec.selector.target)).forEach[f | f.name = op.spec.name]
    }
  }

  def dispatch void processOperation(FeatureCopyOp op)
  {
    val sEntities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.sourceSelector)
    if (sEntities.size !== 1)
      throw new IllegalArgumentException("FeatureCopyOp - ForAll is not allowed here: " + serializer.serialize(op))
    if (sEntities.head === null)
      throw new IllegalArgumentException("FeatureCopyOp - Cannot find source Entity: " + serializer.serialize(op))

    val sEntity = sEntities.head

    if (handler.getSimpleFeatureFromSchemaType(sEntity, op.spec.sourceSelector.target).empty)
      throw new IllegalArgumentException("FeatureCopyOp - Cannot find source Feature: " + serializer.serialize(op))

    val tEntities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.targetSelector)
    if (tEntities.size !== 1)
      throw new IllegalArgumentException("FeatureCopyOp - ForAll is not allowed here: " + serializer.serialize(op))
    if (tEntities.head === null)
      throw new IllegalArgumentException("FeatureCopyOp - Cannot find target Entity: " + serializer.serialize(op))

    val tEntity = tEntities.head

    val featToCopy = handler.getSimpleFeatureFromSchemaType(sEntity, op.spec.sourceSelector.target).head
    handler.addFeatureToSchemaType(tEntity, featToCopy, true)
    handler.getSimpleFeatureFromSchemaType(tEntity, op.spec.sourceSelector.target).head.name = op.spec.targetSelector.target
  }

  def dispatch void processOperation(FeatureMoveOp op)
  {
    val sEntities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.sourceSelector)
    if (sEntities.size !== 1)
      throw new IllegalArgumentException("FeatureMoveOp - ForAll is not allowed here: " + serializer.serialize(op))
    if (sEntities.head === null)
      throw new IllegalArgumentException("FeatureMoveOp - Cannot find source Entity: " + serializer.serialize(op))

    val sEntity = sEntities.head

    if (handler.getSimpleFeatureFromSchemaType(sEntity, op.spec.sourceSelector.target).empty)
      throw new IllegalArgumentException("FeatureMoveOp - Cannot find source Feature: " + serializer.serialize(op))

    val tEntities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.targetSelector)
    if (tEntities.size !== 1)
      throw new IllegalArgumentException("FeatureMoveOp - ForAll is not allowed here: " + serializer.serialize(op))
    if (tEntities.head === null)
      throw new IllegalArgumentException("FeatureMoveOp - Cannot find target Entity: " + serializer.serialize(op))

    val tEntity = tEntities.head

    val featToMove = handler.getSimpleFeatureFromSchemaType(sEntity, op.spec.sourceSelector.target).head
    handler.addFeatureToSchemaType(tEntity, featToMove, false)
    handler.getSimpleFeatureFromSchemaType(tEntity, op.spec.sourceSelector.target).head.name = op.spec.targetSelector.target
  }

  def dispatch void processOperation(FeatureNestOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.size !== 1)
      throw new IllegalArgumentException("FeatureNestOp - ForAll is not allowed here: " + serializer.serialize(op))
    if (entities.head === null)
      throw new IllegalArgumentException("FeatureNestOp - Cannot nest Features of an Entity that does not exist: " + serializer.serialize(op))

    val sEntity = entities.head
    val featsToNest = new ArrayList()
    featsToNest.addAll(op.spec.features)
    featsToNest.add(op.spec.selector.target)

    if (featsToNest.exists[f | handler.getSimpleFeatureFromSchemaType(sEntity, f).empty])
        throw new IllegalArgumentException("FeatureNestOp - Cannot find all Features to nest: " + serializer.serialize(op))

    // TODO: Maybe if they try to nest into a structure that already exists, we should go with an error.
    val entityName = op.spec.name.toFirstUpper
    var entity = handler.getEntityDecl(schema, entityName)
    if (entity === null)
    {
      entity = athenaFactory.createShortEntityDecl(entityName, false)
      schema.entities.add(entity)
    }

    // First move features to the nest
    for (feat : featsToNest)
    {
      var schemaFeat = handler.getSimpleFeatureFromSchemaType(sEntity, feat).head
      handler.addFeatureToSchemaType(entity, schemaFeat, false)
    }

    // Now create an aggregation pointing to the nest
    if (handler.getSimpleFeatureFromSchemaType(sEntity, op.spec.name).empty)
      handler.addFeatureToSchemaType(sEntity, athenaFactory.createSimpleFeature(op.spec.name.toFirstLower, athenaFactory.createSimpleAggr(entity, "+")), false)
  }

  def dispatch void processOperation(FeatureUnnestOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.size !== 1)
      throw new IllegalArgumentException("FeatureUnnestOp - ForAll is not allowed here: " + serializer.serialize(op))
    if (entities.head === null)
      throw new IllegalArgumentException("FeatureUnnestOp - Cannot unnest Features from an Entity that does not exist: " + serializer.serialize(op))

    val sEntity = entities.head
    val featsToUnnest = new ArrayList()
    featsToUnnest.addAll(op.spec.features)
    featsToUnnest.add(op.spec.selector.target)

    if (featsToUnnest.exists[f | !f.contains(".")])
      throw new IllegalArgumentException("FeatureUnnestOp - Features to unnest must use \"dot\" notation: " + serializer.serialize(op))

    for (String featToUnnest : featsToUnnest)
    {
      val aggrToUnnest = featToUnnest.substring(0, featToUnnest.indexOf("."))
      val nameToUnnest = featToUnnest.substring(featToUnnest.indexOf(".") + 1)
      val featAggr = handler.getSimpleFeatureFromSchemaType(sEntity, aggrToUnnest).head

      if (featAggr === null || !(featAggr.type instanceof SimpleAggregationTarget))
        throw new IllegalArgumentException("FeatureUnnestOp - Cannot find Aggregate " + aggrToUnnest + ": " + serializer.serialize(op))

      if (!(featAggr.type as SimpleAggregationTarget).multiplicity.equals("+"))
        throw new IllegalArgumentException("FeatureUnnestOp - Can't unnest a 1..* Aggregate: " + serializer.serialize(op))

      val aggrTarget = (featAggr.type as SimpleAggregationTarget).aggr.head
      // Get the feature to unnest
      val featInAggr = aggrTarget instanceof EntityDecl ? handler.getSimpleFeatureFromSchemaType(aggrTarget, nameToUnnest).head : handler.getFeaturesFromVariation(aggrTarget as VariationDecl).filter(SimpleFeature).findFirst[f | f.name.equals(nameToUnnest)]

      // Move it to its new location
      handler.addFeatureToSchemaType(sEntity, featInAggr, false)
    }
  }

  def dispatch void processOperation(AttributeAddOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("AttributeAddOp - Cannot create Attributes on an Entity that does not exist: " + serializer.serialize(op))

    // If someone tries to do ADD AGGR Entity::propAggr.innerAggr {...} => We actually recursively shift it to ADD AGGR PropAggr::innerAggr {...}
    if (op.spec.selector.target.contains("."))
    {
      processOperation(orionFactory.createAttributeAddOp(
        orionFactory.createFeatureSelector(
          op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper,
          op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)
        ), op.spec.type))
    }
    else
      for (entity : entities)
        handler.addFeatureToSchemaType(entity, processFeature(op.spec.selector.target, op.spec.type, op.spec.isKey, op.spec.isOptional), true)
  }

  def dispatch void processOperation(AttributeCastOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("AttributeCastOp - Cannot cast Attributes on an Entity that does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("AttributeCastOp - Cannot find an Attribute to cast: " + serializer.serialize(op))

    for (entity : entities)
      handler.getSimpleFeatureFromSchemaType(entity, op.spec.selector.target).forEach[f | f.type = EcoreUtil.copy(op.spec.type)]
    //TODO: What happens if we had a reference pointing this casted attribute?
  }
  //TODO: Dar un repaso y sustituir asignaciones con EcoreUtil.copy.

  def dispatch void processOperation(ReferenceCastOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("ReferenceCastOp - Cannot cast References on an Entity which does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("ReferenceCastOp - Cannot find a Reference to cast: " + serializer.serialize(op))

    for (entity : entities)
      handler.getSimpleFeatureFromSchemaType(entity, op.spec.selector.target).forEach[f | (f.type as SimpleReferenceTarget).type = EcoreUtil.copy(op.spec.type)]
  }

  def dispatch void processOperation(ReferenceAddOp op)
  {
    // TODO: If someone tries to do ADD REF Entity::propAggr.innerAggr {...} => We actually recursively shift it to ADD AGGR PropAggr::innerAggr {...}
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("ReferenceAddOp - Cannot add References on an Entity that does not exist: " + serializer.serialize(op))

    val target = handler.getEntityDecl(schema, op.spec.ref)
    if (target === null)
      throw new IllegalArgumentException("ReferenceAddOp - Cannot create a Reference targeting an Entity that does not exist: " + serializer.serialize(op))

    if (op.spec.selector.target.contains(".")) //TODO: Maybe do this for each operation in which dot notation is allowed...
    {
      processOperation(orionFactory.createReferenceAddOp(
        orionFactory.createFeatureSelector(
          op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper,
          op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)
        ), op.spec.type, op.spec.cardinality, op.spec.optional, op.spec.ref))
    }
    else
      for (entity : entities)
        handler.addFeatureToSchemaType(entity, athenaFactory.createSimpleFeature(op.spec.selector.target, athenaFactory.createSimpleRef(target, op.spec.cardinality, op.spec.type), op.spec.optional), true)
  }

  def dispatch void processOperation(ReferenceCardinalityOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("ReferenceCardinalityOp - Cannot change References on an Entity that does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("ReferenceCardinalityOp - Cannot find a Reference to change card: " + serializer.serialize(op))

    for (entity : entities)
      handler.getSimpleFeatureFromSchemaType(entity, op.spec.selector.target).forEach[f | (f.type as SimpleReferenceTarget).multiplicity = op.spec.cardinality]
  }

  def dispatch void processOperation(ReferenceMorphOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("ReferenceMorphOp - Cannot morph References on an Entity that does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("ReferenceMorphOp - Cannot find a Reference to morph: " + serializer.serialize(op))

    val referencedEntity = (handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).head.type as SimpleReferenceTarget).ref
    referencedEntity.root = false

    if (op.spec.deleteId)
      handler.getFeaturesFromSchemaTypeAndVariations(referencedEntity).removeIf(f | f instanceof SimpleFeature && (f as SimpleFeature).key)

    for (entity : entities)
      handler.getFeaturesFromSchemaTypeAndVariations(entity).filter(SimpleFeature).filter[f | f.type instanceof SimpleReferenceTarget && (f.type as SimpleReferenceTarget).ref === referencedEntity]
      .forEach[f | f.type = athenaFactory.createSimpleAggr(referencedEntity, (f.type as SimpleReferenceTarget).multiplicity)]
  }

  def dispatch void processOperation(AggregateAddOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("AggregateAddOp - Cannot create Aggregates on an Entity that does not exist: " + serializer.serialize(op))

    // If someone tries to do ADD AGGR Entity::propAggr.innerAggr {...} => We actually recursively shift it to ADD AGGR PropAggr::innerAggr {...}
    if (op.spec.selector.target.contains("."))
    {
      processOperation(orionFactory.createAggregateAddOp(
        orionFactory.createFeatureSelector(
          op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper,
          op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)
        ), op.spec.features, op.spec.cardinality, op.spec.optional))
    }
    else
    {
      val entityName = op.spec.selector.target.toFirstUpper
      var entity = handler.getEntityDecl(schema, entityName)
      if (entity === null)
      {
        entity = athenaFactory.createShortEntityDecl(entityName, false)
        schema.entities.add(entity)
      }
      for (f : op.spec.features)
        handler.addFeatureToSchemaType(entity, processFeature(f.name, f.type, f.key, f.optional), true)
  
      for (e : entities)
        handler.addFeatureToSchemaType(e, athenaFactory.createSimpleFeature(op.spec.selector.target, athenaFactory.createSimpleAggr(entity, op.spec.cardinality), op.spec.optional), false)
    }
  }

  def dispatch void processOperation(AggregateCardinalityOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("AggregateCardinalityOp - Cannot change Aggregates on an Entity that does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("AggregateCardinalityOp - Cannot find an Aggregate to change card: " + serializer.serialize(op))

    for (entity : entities)
      handler.getSimpleFeatureFromSchemaType(entity, op.spec.selector.target).forEach[f |
        (f.type as SimpleAggregationTarget).multiplicity = op.spec.cardinality;
        if (op.spec.cardinality.equals("+")) (f.type as SimpleAggregationTarget).aggr.removeIf(i | i !== (f.type as SimpleAggregationTarget).aggr.head)
      ]
  }

  def dispatch void processOperation(AggregateMorphOp op)
  {
    val entities = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
    if (entities.empty)
      throw new IllegalArgumentException("AggregateMorphOp - Cannot morph Aggregates on an Entity that does not exist: " + serializer.serialize(op))

    if (entities.size == 1 && handler.getSimpleFeatureFromSchemaType(entities.head, op.spec.selector.target).empty)
      throw new IllegalArgumentException("AggregateMorphOp - Cannot find an Aggregate to morph: " + serializer.serialize(op))

    val aggregatedEntity = handler.getEntityDecl(schema, op.spec.selector.target.toFirstUpper)
    aggregatedEntity.root = true

    if (handler.getKeyFromSchemaType(aggregatedEntity) === null)
    {
      val key = athenaFactory.createSimpleFeature("id", athenaFactory.createUnrestrictedPrimitiveType("Identifier"))
      key.key = true
      handler.addFeatureToSchemaType(aggregatedEntity, key, false)
    }
    //TODO: Update schema to change feature name.
    for (e : entities)
      handler.getFeaturesFromSchemaTypeAndVariations(e).filter(SimpleFeature).filter[f | f.type instanceof SimpleAggregationTarget && (f.type as SimpleAggregationTarget).aggr.contains(aggregatedEntity)]
      .forEach[f | f.type = athenaFactory.createSimpleRef(aggregatedEntity, (f.type as SimpleAggregationTarget).multiplicity, handler.getKeyFromSchemaType(aggregatedEntity).type as PrimitiveType)]
  }

  private def SimpleFeature processFeature(SimpleDataFeature feature)
  {
    return processFeature(feature.name, feature.type, feature.isKey, feature.optional)
  }

  private def SimpleFeature processFeature(String name, DataType type, boolean isKey, boolean isOptional)
  {
    if (name.contains(".")) // It is an aggregation.
    {
      val entityName = name.substring(0, name.indexOf(".")).toFirstUpper
      val featName = name.substring(name.indexOf(".") + 1)
      var entity = handler.getEntityDecl(schema, entityName)

      if (entity === null)
      {
        entity = athenaFactory.createShortEntityDecl(entityName, false)
        schema.entities.add(entity)
      }

      handler.addFeatureToSchemaType(entity, processFeature(featName, type, isKey, isOptional), false)

      return athenaFactory.createSimpleFeature(name.substring(0, name.indexOf(".")), athenaFactory.createSimpleAggr(entity, "+"), isOptional)
    }
    else
    {
      val result = type === null ? athenaFactory.createSimpleFeature(name) : athenaFactory.createSimpleFeature(name, type, isOptional)
      result.key = isKey

      return result
    }
  }
}
