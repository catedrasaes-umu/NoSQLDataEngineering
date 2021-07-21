package es.um.unosql.xtext.orion.utils.updater

import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.orion.orion.EntityDeleteOp
import es.um.unosql.xtext.orion.orion.EntityRenameOp
import es.um.unosql.xtext.orion.orion.EntitySplitOp
import es.um.unosql.xtext.athena.athena.SimpleFeature
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.orion.orion.EntityExtractOp
import es.um.unosql.xtext.orion.orion.EntityMergeOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.orion.orion.SimpleDataFeature
import es.um.unosql.xtext.orion.validation.m2m.AthenaSchemaUpdaterValidator
import es.um.unosql.xtext.orion.orion.EntityDelVarOp
import es.um.unosql.xtext.orion.orion.EntityUnionOp
import es.um.unosql.xtext.orion.orion.EntityAdaptOp
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.orion.orion.AttributePromoteOp
import es.um.unosql.xtext.orion.orion.AttributeDemoteOp
import es.um.unosql.xtext.orion.utils.OrionFactory
import es.um.unosql.xtext.orion.utils.OrionUtils
import es.um.unosql.xtext.orion.orion.MultipleFeatureSelector
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceMultiplicityOp
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.AggregateMultiplicityOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp

class AthenaSchemaUpdater
{
  AthenaSchema schema
  AthenaSchemaUpdaterValidator validator
  val aHandler = new AthenaHandler
  val athenaFactory = new AthenaFactory
  val orionFactory = new OrionFactory

  new(AthenaSchema schema, boolean enable_validation)
  {
    this.schema = schema
    this.validator = new AthenaSchemaUpdaterValidator(enable_validation)
  }

  def AthenaSchema getSchema()
  {
    return this.schema
  }

  def dispatch void processOperation(EntityAddOp op)
  {
    validator.validateOperation(this.schema, op)

    // Operation is only performed if there is no entity with such a name.
    if (aHandler.getEntityDecl(schema, op.spec.name) !== null)
      return

    val result = athenaFactory.createShortEntityDecl(op.spec.name, true)
    schema.entities.add(result)

    for (feat : op.spec.features)
      aHandler.addFeatureToSchemaType(result, processFeature(EcoreUtil2.copy(feat)))
  }

  def dispatch void processOperation(EntityDeleteOp op)
  {
    validator.validateOperation(this.schema, op)
    val entity = aHandler.getEntityDecl(schema, op.spec.ref)

    // Operation is only performed if there is an entity with such a name.
    if (entity !== null)
      schema.entities.remove(entity)
  }

  def dispatch void processOperation(EntityRenameOp op)
  {
    validator.validateOperation(this.schema, op)
    val entity = aHandler.getEntityDecl(schema, op.spec.ref)

    // Operation is only performed if there is an entity with such a name and there is no entity with the new name.
    if (entity !== null && aHandler.getEntityDecl(schema, op.spec.name) === null)
      entity.name = op.spec.name
  }

  def dispatch void processOperation(EntitySplitOp op)
  {
    validator.validateOperation(this.schema, op)

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)

    if (sourceEntity === null)
      return

    val featsToSplit = aHandler.getReducedFeaturesInSchemaTypeAndVariations(sourceEntity).filter(SimpleFeature)

    // For each of the two new entities: create a new short entity and clone each of the features to be moved to the splitted entity
    // TODO: What to do with variations?
    val newEntity1 = athenaFactory.createShortEntityDecl(op.spec.name1, sourceEntity.root)
    for (featName : op.spec.features1.features)
    {
      val featToSplit = featsToSplit.findFirst[f | f.name.equals(featName)]
      if (featToSplit !== null)
        aHandler.addFeatureToSchemaType(newEntity1, EcoreUtil2.copy(featToSplit))
    }

    val newEntity2 = athenaFactory.createShortEntityDecl(op.spec.name2, sourceEntity.root)
    for (featName : op.spec.features2.features)
    {
      val featToSplit = featsToSplit.findFirst[f | f.name.equals(featName)]
      if (featToSplit !== null)
        aHandler.addFeatureToSchemaType(newEntity2, EcoreUtil2.copy(featToSplit))
    }

    // The reference type should still point to the key feature type, so no need to change it.
    EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget).filter[r | r.ref === sourceEntity].forEach[r | r.ref = newEntity1]

    // For each Aggregate, if it aggregates the Entity or one of its variations, redirect that aggregate to the new splitted entity
    EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[r | r.aggr.contains(sourceEntity)].forEach[r | r.aggr.clear; r.aggr.add(newEntity1)]

    if (sourceEntity instanceof RegularEntityDecl)
      EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[r | r.aggr.exists[aggrO | (sourceEntity as RegularEntityDecl).variations.contains(aggrO)]]
        .forEach[r | r.aggr.clear; r.aggr.add(newEntity1)]

    schema.entities.remove(sourceEntity)
    schema.entities.add(newEntity1)
    schema.entities.add(newEntity2)
  }


  def dispatch void processOperation(EntityExtractOp op)
  {
    validator.validateOperation(this.schema, op)

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)

    if (sourceEntity === null)
      return

    val featsToExtract = aHandler.getReducedFeaturesInSchemaTypeAndVariations(sourceEntity).filter(SimpleFeature)

    // Create a new short entity and clone each of the features to be moved to the extracted entity
    val newEntity = athenaFactory.createShortEntityDecl(op.spec.name, sourceEntity.root)
    for (featName : op.spec.features.features)
    {
      val featToExtract = featsToExtract.findFirst[f | f.name.equals(featName)]
      if (featToExtract !== null)
        aHandler.addFeatureToSchemaType(newEntity, EcoreUtil2.copy(featToExtract))
    }

    schema.entities.add(newEntity)
  }

  def dispatch void processOperation(EntityMergeOp op)
  {
    validator.validateOperation(this.schema, op)
    val sourceEntity1 = aHandler.getEntityDecl(schema, op.spec.ref1)
    val sourceEntity2 = aHandler.getEntityDecl(schema, op.spec.ref2)

    if (sourceEntity1 === null || sourceEntity2 === null)
      return

    val newEntity = athenaFactory.createShortEntityDecl(op.spec.name, sourceEntity1.root || sourceEntity2.root)
    schema.entities.add(newEntity)

    // For each feature of the merged entities, move the first occurrence to the new merged Entity
    for (feat : (aHandler.getFeaturesInSchemaTypeAndVariations(sourceEntity1) + aHandler.getFeaturesInSchemaTypeAndVariations(sourceEntity2)).filter(SimpleFeature))
      if (aHandler.getSimpleFeatureInSchemaType(newEntity, feat.name) === null)
        aHandler.addFeatureToSchemaType(newEntity, EcoreUtil2.copy(feat))

    // For each Aggregate and Reference, move them to point to the new merged Entity
    EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget).filter[r | r.ref === sourceEntity1 || r.ref === sourceEntity1].forEach[r | r.ref = newEntity]
    // For each Aggregate, if it aggregates the Entity or one of its variations, redirect that aggregate to the new splitted entity
    EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[r | r.aggr.contains(sourceEntity1) || r.aggr.contains(sourceEntity1)].forEach[r | r.aggr.clear; r.aggr.add(newEntity)]

    if (sourceEntity1 instanceof RegularEntityDecl)
      EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[r | r.aggr.exists[aggrO | (sourceEntity1 as RegularEntityDecl).variations.contains(aggrO)]].forEach[r | r.aggr.clear; r.aggr.add(newEntity)]

    if (sourceEntity2 instanceof RegularEntityDecl)
      EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[r | r.aggr.exists[aggrO | (sourceEntity2 as RegularEntityDecl).variations.contains(aggrO)]].forEach[r | r.aggr.clear; r.aggr.add(newEntity)]

    // Remove merging entities
    schema.entities.remove(sourceEntity1)
    schema.entities.remove(sourceEntity2)
  }

  def dispatch void processOperation(EntityDelVarOp op)
  {
    validator.validateOperation(schema, op)
    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)

    if (sourceEntity === null || !(sourceEntity instanceof RegularEntityDecl))
      return;

    (sourceEntity as RegularEntityDecl).variations.removeIf([v | v.name.equals(op.spec.target)])

    try
    {
      val varId = Integer.parseInt(op.spec.target)
      (sourceEntity as RegularEntityDecl).variations.filter[v | Integer.parseInt(v.name) > varId].forEach[v | v.name = String.valueOf(Integer.parseInt(v.name) - 1)]
    } catch (NumberFormatException e)
    {
      e.printStackTrace()
    }
    // TODO: Should we recalculate common features ?
  }

  // Adapt operation at schema level holds the same semantics as DelVar 
  def dispatch void processOperation(EntityAdaptOp op)
  {
    validator.validateOperation(schema, op)
    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)

    if (sourceEntity === null || !(sourceEntity instanceof RegularEntityDecl))
      return;

    if (!(sourceEntity as RegularEntityDecl).variations.exists[v | v.name.equals(op.spec.target)])
      return;

    (sourceEntity as RegularEntityDecl).variations.removeIf([v | v.name.equals(op.spec.source)]) // <= Slightly different to DelVar

    try
    {
      val varId = Integer.parseInt(op.spec.source)
      (sourceEntity as RegularEntityDecl).variations.filter[v | Integer.parseInt(v.name) > varId].forEach[v | v.name = String.valueOf(Integer.parseInt(v.name) - 1)]
    } catch (NumberFormatException e)
    {
      e.printStackTrace()
    }
    // TODO: Should we recalculate common features ?
  }

  def dispatch void processOperation(EntityUnionOp op)
  {
    validator.validateOperation(schema, op)
    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)

    if (sourceEntity === null)
      return;

    if (sourceEntity instanceof ShortEntityDecl)
      aHandler.getFeaturesInSchemaType(sourceEntity).filter(SimpleFeature).forEach[f | f.optional = false]

    if (sourceEntity instanceof RegularEntityDecl)
    {
      val newEntity = athenaFactory.createRegularEntityDecl(sourceEntity.name, sourceEntity.root)
      newEntity.variations.add(athenaFactory.createVariationDecl(1))
      aHandler.getReducedFeaturesInSchemaTypeAndVariations(sourceEntity).forEach[f | f instanceof SimpleFeature ? (f as SimpleFeature).optional = false; aHandler.addFeatureToSchemaType(newEntity, EcoreUtil2.copy(f))]

      // The reference type should still point to the key feature type, so no need to change it.
      EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget).filter[r | r.ref === sourceEntity].forEach[r | r.ref = newEntity]

      // For each Aggregate, if it aggregates the Entity or one of its variations, redirect that aggregate to the newly entity
      EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[r | r.aggr.contains(sourceEntity)].forEach[r | r.aggr.clear; r.aggr.add(newEntity)]
      EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[r | r.aggr.exists[aggrO | (sourceEntity as RegularEntityDecl).variations.contains(aggrO)]]
        .forEach[r | r.aggr.clear; r.aggr.add(newEntity)]

      schema.entities.remove(sourceEntity)
      schema.entities.add(newEntity)
    }
  }

  def dispatch void processOperation(FeatureDeleteOp op)
  {
    validator.validateOperation(schema, op)

    for (entity : OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector))
      if (entity instanceof ShortEntityDecl || op.spec.selector.variations.empty)
        op.spec.selector.targets.forEach[target | aHandler.removeSimpleFeatureInSchemaType(entity, target)]
      else // Entity has variations AND variations were specified.
        for (variation : (entity as RegularEntityDecl).variations.filter[v | op.spec.selector.variations.contains(v.name)])
          op.spec.selector.targets.forEach[target | aHandler.removeSimpleFeatureInVariation(variation, target)]
    // TODO: Should we recalculate common features ?
  }

  def dispatch void processOperation(FeatureRenameOp op)
  {
    validator.validateOperation(schema, op)

    for (entity : OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector))
    {
      if (entity instanceof RegularEntityDecl) // If it is Regular, perform the operation over the variations first.
      {
        val variationsToApply = (entity as RegularEntityDecl).variations.filter[v | op.spec.selector.variations.empty || op.spec.selector.variations.contains(v.name)]

        for (variation : variationsToApply)
          aHandler.setNameOfSimpleFeatureInVariation(variation, op.spec.selector.target, op.spec.name)
      }

      // Now if it is Short OR there are no variations (thus the operation has to be performed over the Common body of a Regular)
      if (entity instanceof ShortEntityDecl || op.spec.selector.variations.empty)
        aHandler.setNameOfSimpleFeatureInSchemaType(entity, op.spec.selector.target, op.spec.name)
    }
  }

  def dispatch void processOperation(FeatureCopyOp op)
  {
    validator.validateOperation(schema, op)

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.sourceSelector.ref)
    val targetEntity = aHandler.getEntityDecl(schema, op.spec.targetSelector.ref)
    val featToCopy = aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.sourceSelector.target)

    if (featToCopy === null)
      return;

    if (targetEntity instanceof ShortEntityDecl || op.spec.targetSelector.variations.empty) // Just copy the feature to each Entity
    {
      val newFeatToCopy = EcoreUtil2.copy(featToCopy)
      newFeatToCopy.name = op.spec.targetSelector.target

      aHandler.addFeatureToSchemaType(targetEntity, newFeatToCopy)
    }
    else
    {
      for (v : (targetEntity as RegularEntityDecl).variations.filter[v | op.spec.targetSelector.variations.contains(v.name)])
      {
        val newFeatToCopy = EcoreUtil2.copy(featToCopy)
        newFeatToCopy.name = op.spec.targetSelector.target

        aHandler.getFeaturesInVariation(v).add(newFeatToCopy)
      }
    }
  }

  def dispatch void processOperation(FeatureMoveOp op)
  {
    validator.validateOperation(schema, op)

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.sourceSelector.ref)
    val targetEntity = aHandler.getEntityDecl(schema, op.spec.targetSelector.ref)
    val featToMove = aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.sourceSelector.target)

    if (featToMove === null)
      return;

    if (targetEntity instanceof ShortEntityDecl || op.spec.targetSelector.variations.empty) // Just copy the feature to each Entity
    {
      val newFeatToMove = EcoreUtil2.copy(featToMove)
      newFeatToMove.name = op.spec.targetSelector.target

      aHandler.addFeatureToSchemaType(targetEntity, newFeatToMove)
    }
    else
    {
      for (v : (targetEntity as RegularEntityDecl).variations.filter[v | op.spec.targetSelector.variations.contains(v.name)])
      {
        val newFeatToMove = EcoreUtil2.copy(featToMove)
        newFeatToMove.name = op.spec.targetSelector.target

        aHandler.getFeaturesInVariation(v).add(newFeatToMove)
      }
    }

    aHandler.removeSimpleFeatureInSchemaType(sourceEntity, featToMove.name)
  }

  def dispatch void processOperation(FeatureNestOp op)
  {
    validator.validateOperation(schema, op)

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    // TODO: We might consider that op.spec.name is actually the field where the nest has to be created, instead of the Aggregate entity.
    // Look for the target Aggregated entity. If it does not exist, create a new one.
    val nestEntityName = op.spec.name.toFirstUpper
    var nestEntity = aHandler.getEntityDecl(schema, nestEntityName)
    if (nestEntity === null)
    {
      nestEntity = athenaFactory.createShortEntityDecl(nestEntityName, false)
      schema.entities.add(nestEntity)
    }

    // First move features to the nest
    for (featName : op.spec.selector.targets)
    {
      val feat = aHandler.getSimpleFeatureInSchemaType(sourceEntity, featName)
      if (feat !== null)
      {
        aHandler.addFeatureToSchemaType(nestEntity, feat)
        aHandler.removeSimpleFeatureInSchemaType(sourceEntity, featName)
      }
    }

    // Now create an aggregate pointing to the nest
    if (aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.name) === null)
      aHandler.addFeatureToSchemaType(sourceEntity, athenaFactory.createSimpleFeature(op.spec.name.toFirstLower, athenaFactory.createSimpleAggr(nestEntity, "&")))
  }

  def dispatch void processOperation(FeatureUnnestOp op)
  {
    validator.validateOperation(schema, op)

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    for (String featToUnnest : op.spec.selector.targets)
    {
      val aggrToUnnest = featToUnnest.substring(0, featToUnnest.indexOf("."))
      val nameToUnnest = featToUnnest.substring(featToUnnest.indexOf(".") + 1)
      val featAggr = aHandler.getSimpleFeatureInSchemaType(sourceEntity, aggrToUnnest)

      if (featAggr !== null)
      {
        val aggrTarget = (featAggr.type as SimpleAggregateTarget).aggr.head

        // Get the feature to unnest, move it to its new location and remove it from its previous location
        if (aggrTarget instanceof EntityDecl)
        {
          val featInAggr = aHandler.getSimpleFeatureInSchemaType(aggrTarget, nameToUnnest)
          if (featInAggr !== null)
          {
            aHandler.removeSimpleFeatureInSchemaType(aggrTarget, nameToUnnest)
            aHandler.addFeatureToSchemaType(sourceEntity, featInAggr)
          }
        }
        else // Aggregate points to a Variation
        {
          val featInAggr = aHandler.getFeaturesInVariation(aggrTarget as VariationDecl).filter(SimpleFeature).findFirst[f | f.name.equals(nameToUnnest)]
          if (featInAggr !== null)
          {
            aHandler.removeSimpleFeatureInVariation(aggrTarget as VariationDecl, nameToUnnest)
            aHandler.addFeatureToSchemaType(sourceEntity, featInAggr)
          }
        }
      }
    }
  }

  def dispatch void processOperation(AttributeAddOp op)
  {
    validator.validateOperation(schema, op)

    // If someone tries to do ADD ATTRIBUTE Entity::propAggr.innerAggr {...} => We actually recursively shift it to ADD ATTRIBUTE PropAggr::innerAggr {...}
    if (op.spec.selector.target.contains("."))
      processOperation(orionFactory.createAttributeAddOp(
        orionFactory.createSingleFeatureSelector(
          op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper,
          op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)
        ), op.spec.type, op.spec.defaultValue, op.spec.key, op.spec.optional, op.spec.unique))
    else
    {
      val featureModel = processFeature(op.spec.selector.target, op.spec.type, op.spec.defaultValue, op.spec.isKey, op.spec.isOptional, op.spec.unique)
      for (entity : OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector))
      {
        if (entity instanceof ShortEntityDecl && aHandler.getSimpleFeatureInSchemaType(entity, op.spec.selector.target) === null)
          aHandler.addFeatureToSchemaType(entity, EcoreUtil2.copy(featureModel))
        else // RegularEntity
        {
          if (op.spec.selector.variations.empty && aHandler.getSimpleFeatureInSchemaType(entity, op.spec.selector.target) === null)
          {
            aHandler.addFeatureToSchemaType(entity, EcoreUtil2.copy(featureModel))

            for (variation : (entity as RegularEntityDecl).variations)
              aHandler.removeSimpleFeatureInVariation(variation, op.spec.selector.target)
          }
          else // Variations were specified
            for (variation : (entity as RegularEntityDecl).variations.filter[v | op.spec.selector.variations.contains(v.name) && aHandler.getSimpleFeatureInVariation(v, op.spec.selector.target) === null])
              aHandler.addFeatureToVariation(variation, EcoreUtil2.copy(featureModel))
        }
      }
    }
  }

  def dispatch void processOperation(AttributeCastOp op)
  {
    validator.validateOperation(schema, op)
    processTypeCast(op.spec.selector, op.spec.type)
  }

  def dispatch void processOperation(AttributePromoteOp op)
  {
    validator.validateOperation(schema, op)
    setKeyOperation(op.spec.selector, true)
  }

  def dispatch void processOperation(AttributeDemoteOp op)
  {
    validator.validateOperation(schema, op)
    setKeyOperation(op.spec.selector, false)
  }

  private def void setKeyOperation(MultipleFeatureSelector selector, boolean isKey)
  {
    for (entity : OrionUtils.getEntityTypesFromSelector(schema, selector))
    {
      if (entity instanceof RegularEntityDecl) // If it is Regular, perform the operation over the variations first.
      {
        val variationsToApply = (entity as RegularEntityDecl).variations.filter[v | selector.variations.empty || selector.variations.contains(v.name)]

        for (variation : variationsToApply)
          selector.targets.forEach[t | aHandler.setKeyOfSimpleFeatureInVariation(variation, t, isKey)]
      }

      // Now if it is Short OR there are no variations (thus the operation has to be performed over the Common body of a Regular)
      if (entity instanceof ShortEntityDecl || selector.variations.empty)
        selector.targets.forEach[t | aHandler.setKeyOfSimpleFeatureInSchemaType(entity, t, isKey)]
    }
  }

  def dispatch void processOperation(ReferenceAddOp op)
  {
    validator.validateOperation(schema, op)

    // If someone tries to do ADD REF Entity::propAggr.innerAggr {...} => We actually recursively shift it to ADD REF PropAggr::innerAggr {...}
    if (op.spec.selector.target.contains("."))
      processOperation(orionFactory.createReferenceAddOp(
        orionFactory.createSingleFeatureSelector(
          op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper,
          op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)
        ), op.spec.type, op.spec.multiplicity, op.spec.optional, op.spec.ref))
    else
    {
      val referenceModel = athenaFactory.createSimpleFeature(op.spec.selector.target, athenaFactory.createSimpleRef(aHandler.getEntityDecl(schema, op.spec.ref), op.spec.multiplicity, op.spec.type), op.spec.optional)
      for (entity : OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector))
      {
        if (entity instanceof ShortEntityDecl && aHandler.getSimpleFeatureInSchemaType(entity, op.spec.selector.target) === null)
          aHandler.addFeatureToSchemaType(entity, EcoreUtil2.copy(referenceModel))
        else // RegularEntity
        {
          if (op.spec.selector.variations.empty && aHandler.getSimpleFeatureInSchemaType(entity, op.spec.selector.target) === null)
          {
            aHandler.addFeatureToSchemaType(entity, EcoreUtil2.copy(referenceModel))

            for (variation : (entity as RegularEntityDecl).variations)
              aHandler.removeSimpleFeatureInVariation(variation, op.spec.selector.target)
          }
          else // Variations were specified
            for (variation : (entity as RegularEntityDecl).variations.filter[v | op.spec.selector.variations.contains(v.name) && aHandler.getSimpleFeatureInVariation(v, op.spec.selector.target) === null])
              aHandler.addFeatureToVariation(variation, EcoreUtil2.copy(referenceModel))
        }
      }
    }
  }

  def dispatch void processOperation(ReferenceCastOp op)
  {
    validator.validateOperation(schema, op)
    processTypeCast(op.spec.selector, op.spec.type)
  }

  def dispatch void processOperation(ReferenceMultiplicityOp op)
  {
    validator.validateOperation(schema, op)
    processMultiplicityChange(op.spec.selector, op.spec.multiplicity)
  }

  def dispatch void processOperation(ReferenceMorphOp op)
  {
    validator.validateOperation(schema, op)

    val feat = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
      .flatMap[e | aHandler.getFeaturesInSchemaTypeAndVariations(e)]
      .filter(SimpleFeature)
      .findFirst[f | f.name.equals(op.spec.selector.target) && f.type instanceof SimpleReferenceTarget]

    if (feat !== null)
    {
      val reference = feat.type as SimpleReferenceTarget
      val newAggregate = athenaFactory.createSimpleAggr(reference.ref, reference.multiplicity)
      reference.ref.root = false

      if (op.spec.deleteId) // Remove ids
      {
        while (aHandler.getKeyInSchemaType(reference.ref) !== null)
          aHandler.setKeyOfSimpleFeatureInSchemaType(reference.ref, aHandler.getKeyInSchemaType(reference.ref).name, false)
      }

      for (entity : OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector))
      {
        if (entity instanceof RegularEntityDecl) // If it is Regular, perform the operation over the variations first.
        {
          val variationsToApply = (entity as RegularEntityDecl).variations.filter[v | op.spec.selector.variations.empty || op.spec.selector.variations.contains(v.name)]

          for (variation : variationsToApply)
          {
            aHandler.setTypeOfSimpleFeatureInVariation(variation, op.spec.selector.target, EcoreUtil2.copy(newAggregate))
            aHandler.setNameOfSimpleFeatureInVariation(variation, op.spec.selector.target, op.spec.name)
          }
        }

        // Now if it is Short OR there are no variations (thus the operation has to be performed over the Common body of a Regular)
        if (entity instanceof ShortEntityDecl || op.spec.selector.variations.empty)
        {
          aHandler.setTypeOfSimpleFeatureInSchemaType(entity, op.spec.selector.target, EcoreUtil2.copy(newAggregate))
          aHandler.setNameOfSimpleFeatureInSchemaType(entity, op.spec.selector.target, op.spec.name)
        }
      }
    }
  }

  def dispatch void processOperation(AggregateAddOp op)
  {
    validator.validateOperation(schema, op)

    // If someone tries to do ADD AGGR Entity::propAggr.innerAggr {...} => We actually recursively shift it to ADD AGGR PropAggr::innerAggr {...}
    if (op.spec.selector.target.contains("."))
    {
      processOperation(orionFactory.createAggregateAddOp(
        orionFactory.createSingleFeatureSelector(
          op.spec.selector.target.substring(0, op.spec.selector.target.indexOf(".")).toFirstUpper,
          op.spec.selector.target.substring(op.spec.selector.target.indexOf(".") + 1)
        ), op.spec.features, op.spec.multiplicity, op.spec.optional, op.spec.name))
    }
    else
    {
      val entityName = op.spec.name !== null ? op.spec.name : op.spec.selector.target.toFirstUpper
      var aggregateEntity = aHandler.getEntityDecl(schema, entityName)
      if (aggregateEntity === null)
      {
        aggregateEntity = athenaFactory.createShortEntityDecl(entityName, false)
        schema.entities.add(aggregateEntity)
      }
      val aggregateModel = athenaFactory.createSimpleFeature(op.spec.selector.target, athenaFactory.createSimpleAggr(aggregateEntity, op.spec.multiplicity), op.spec.optional)

      for (f : op.spec.features)
        aHandler.addFeatureToSchemaType(aggregateEntity, processFeature(f.name, f.type, f.defaultValue, f.key, f.optional, f.unique))
  
      for (entity : OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector))
      {
        if (entity instanceof ShortEntityDecl && aHandler.getSimpleFeatureInSchemaType(entity, op.spec.selector.target) === null)
          aHandler.addFeatureToSchemaType(entity, EcoreUtil2.copy(aggregateModel))
        else // RegularEntity
        {
          if (op.spec.selector.variations.empty && aHandler.getSimpleFeatureInSchemaType(entity, op.spec.selector.target) === null)
          {
            aHandler.addFeatureToSchemaType(entity, EcoreUtil2.copy(aggregateModel))

            for (variation : (entity as RegularEntityDecl).variations)
              aHandler.removeSimpleFeatureInVariation(variation, op.spec.selector.target)
          }
          else // Variations were specified
            for (variation : (entity as RegularEntityDecl).variations.filter[v | op.spec.selector.variations.contains(v.name) && aHandler.getSimpleFeatureInVariation(v, op.spec.selector.target) === null])
              aHandler.addFeatureToVariation(variation, EcoreUtil2.copy(aggregateModel))
        }
      }
    }
  }

  def dispatch void processOperation(AggregateMultiplicityOp op)
  {
    validator.validateOperation(schema, op)
    processMultiplicityChange(op.spec.selector, op.spec.multiplicity)
  }

  def dispatch void processOperation(AggregateMorphOp op)
  {
    validator.validateOperation(schema, op)

    val feat = OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector)
      .flatMap[e | aHandler.getFeaturesInSchemaTypeAndVariations(e)]
      .filter(SimpleFeature)
      .findFirst[f | f.name.equals(op.spec.selector.target) && f.type instanceof SimpleAggregateTarget]

    if (feat !== null)
    {
      val aggregate = feat.type as SimpleAggregateTarget
      val aggregatedEntity = aHandler.getEntityDecl(schema, aHandler.getSimpleAggregateTargetName(aggregate))
      aggregatedEntity.root = true

      if (aHandler.getKeyInSchemaType(aggregatedEntity) === null)
      {
        val key = athenaFactory.createSimpleFeature("id", athenaFactory.createUnrestrictedPrimitiveType("Identifier"))
        key.key = true
        aHandler.addFeatureToSchemaType(aggregatedEntity, key)
      }

      val newReference = athenaFactory.createSimpleRef(aggregatedEntity, aggregate.multiplicity, aHandler.getKeyInSchemaType(aggregatedEntity).type as PrimitiveType)

      for (entity : OrionUtils.getEntityTypesFromSelector(schema, op.spec.selector))
      {
        if (entity instanceof RegularEntityDecl) // If it is Regular, perform the operation over the variations first.
        {
          val variationsToApply = (entity as RegularEntityDecl).variations.filter[v | op.spec.selector.variations.empty || op.spec.selector.variations.contains(v.name)]

          for (variation : variationsToApply)
          {
            aHandler.setTypeOfSimpleFeatureInVariation(variation, op.spec.selector.target, EcoreUtil2.copy(newReference))
            aHandler.setNameOfSimpleFeatureInVariation(variation, op.spec.selector.target, op.spec.name)
          }
        }

        // Now if it is Short OR there are no variations (thus the operation has to be performed over the Common body of a Regular)
        if (entity instanceof ShortEntityDecl || op.spec.selector.variations.empty)
        {
          aHandler.setTypeOfSimpleFeatureInSchemaType(entity, op.spec.selector.target, EcoreUtil2.copy(newReference))
          aHandler.setNameOfSimpleFeatureInSchemaType(entity, op.spec.selector.target, op.spec.name)
        }
      }
    }
  }

  private def SimpleFeature processFeature(SimpleDataFeature feature)
  {
    return processFeature(feature.name, feature.type, feature.defaultValue, feature.isKey, feature.optional, feature.unique)
  }

  private def SimpleFeature processFeature(String name, DataType type, String value, boolean isKey, boolean isOptional, boolean isUnique)
  {
    if (name.contains(".")) // It is an aggregate.
    {
      val entityName = name.substring(0, name.indexOf(".")).toFirstUpper
      val featName = name.substring(name.indexOf(".") + 1)
      var entity = aHandler.getEntityDecl(schema, entityName)

      if (entity === null)
      {
        entity = athenaFactory.createShortEntityDecl(entityName, false)
        schema.entities.add(entity)
      }

      aHandler.addFeatureToSchemaType(entity, processFeature(featName, type, value, isKey, isOptional, isUnique))

      return athenaFactory.createSimpleFeature(name.substring(0, name.indexOf(".")), athenaFactory.createSimpleAggr(entity, "&"), isOptional)
    }
    else
    {
      val result = type === null ? athenaFactory.createSimpleFeature(name) : athenaFactory.createSimpleFeature(name, type, isOptional)
      result.key = isKey
      result.unique = isUnique
      result.optional = isOptional
      // Athena does not hold default values

      return result
    }
  }

  private def void processTypeCast(MultipleFeatureSelector selector, PrimitiveType type)
  {
    for (entity : OrionUtils.getEntityTypesFromSelector(schema, selector))
    {
      if (entity instanceof RegularEntityDecl) // If it is Regular, perform the operation over the variations first.
      {
        val variationsToApply = (entity as RegularEntityDecl).variations.filter[v | selector.variations.empty || selector.variations.contains(v.name)]
      
        for (variation : variationsToApply)
          selector.targets.forEach[t | aHandler.setTypeOfSimpleFeatureInVariation(variation, t, type)]
      }

      // Now if it is Short OR there are no variations (thus the operation has to be performed over the Common body of a Regular)
      if (entity instanceof ShortEntityDecl || selector.variations.empty)
        selector.targets.forEach[t | aHandler.setTypeOfSimpleFeatureInSchemaType(entity, t, type)]
    }
  }

  def void processMultiplicityChange(MultipleFeatureSelector selector, String mutiplicity)
  {
    for (entity : OrionUtils.getEntityTypesFromSelector(schema, selector))
    {
      if (entity instanceof RegularEntityDecl) // If it is Regular, perform the operation over the variations first.
      {
        val variationsToApply = (entity as RegularEntityDecl).variations.filter[v | selector.variations.empty || selector.variations.contains(v.name)]

        for (variation : variationsToApply)
          selector.targets.forEach[t | aHandler.setMultiplicityOfSimpleFeatureInVariation(variation, t, mutiplicity)]
      }

      // Now if it is Short OR there are no variations (thus the operation has to be performed over the Common body of a Regular)
      if (entity instanceof ShortEntityDecl || selector.variations.empty)
        selector.targets.forEach[t | aHandler.setMultiplicityOfSimpleFeatureInSchemaType(entity, t, mutiplicity)]
    }
  }
}
