package es.um.unosql.xtext.orion.validation.m2m

import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.orion.orion.EntityDeleteOp
import es.um.unosql.xtext.orion.utils.io.OrionIO
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.orion.orion.EntityRenameOp
import es.um.unosql.xtext.orion.orion.EntitySplitOp
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.orion.orion.EntityExtractOp
import es.um.unosql.xtext.orion.orion.EntityMergeOp
import es.um.unosql.xtext.orion.orion.EntityDelVarOp
import es.um.unosql.xtext.orion.orion.EntityAdaptOp
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.orion.orion.EntityUnionOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.AttributePromoteOp
import es.um.unosql.xtext.orion.orion.AttributeDemoteOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceMultiplicityOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.AggregateMultiplicityOp
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp

class AthenaSchemaUpdaterValidator
{
  Boolean enable_validation
  OrionIO orionIO
  AthenaHandler aHandler

  new(Boolean enable_validation)
  {
    this.enable_validation = enable_validation
    this.orionIO = new OrionIO()
    this.aHandler = new AthenaHandler()
  }

  def void validateOperation(AthenaSchema schema, BasicOperation operation)
  {
    if (enable_validation)
      validateOp(schema, operation)
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityAddOp op)
  {
    if (schema.entities.exists[e | e.name.equals(op.spec.name)])
      throw new IllegalArgumentException("EntityAddOp> Cannot create an Entity with the same name as an existing entity: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityDeleteOp op)
  {
    if (aHandler.getEntityDecl(schema, op.spec.ref) === null)
      throw new IllegalArgumentException("EntityDeleteOp> Cannot delete an Entity that does not exist: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityRenameOp op)
  {
    if (aHandler.getEntityDecl(schema, op.spec.ref) === null)
          throw new IllegalArgumentException("EntityRenameOp> Cannot rename an Entity that does not exist: " + orionIO.serialize(op))

    if (aHandler.getEntityDecl(schema, op.spec.name) !== null)
          throw new IllegalArgumentException("EntityRenameOp> Cannot rename to a name that already exists: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntitySplitOp op)
  {
    // Check Entity to split exists
    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)
    if (sourceEntity === null)
      throw new IllegalArgumentException("EntitySplitOp - Cannot split an Entity that does not exist: " + orionIO.serialize(op))

    val featsToSplit = aHandler.getReducedFeaturesInSchemaTypeAndVariations(sourceEntity).filter(SimpleFeature)

    // Check features from the first group exist
    for (featName : op.spec.features1.features)
      if (!featsToSplit.exists[f | f.name.equals(featName)])
        throw new IllegalArgumentException("EntitySplitOp - Cannot split Feature \"" + featName + "\" because it does not exist: " + orionIO.serialize(op))

    // Check features from the second group exist
    for (featName : op.spec.features2.features)
      if (!featsToSplit.exists[f | f.name.equals(featName)])
        throw new IllegalArgumentException("EntitySplitOp - Cannot split Feature \"" + featName + "\" because it does not exist: " + orionIO.serialize(op))

    // Check new entities do not collide with existing entities
    if (aHandler.getEntityDecl(schema, op.spec.name1) !== null || aHandler.getEntityDecl(schema, op.spec.name2) !== null)
      throw new IllegalArgumentException("EntitySplitOp - Cannot split to an Entity that already exists: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityExtractOp op)
  {
    // Check Entity to extract from
    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)
    if (sourceEntity === null)
      throw new IllegalArgumentException("EntityExtractOp - Cannot extract from an Entity that does not exist: " + orionIO.serialize(op))

    val featsToExtract = aHandler.getReducedFeaturesInSchemaTypeAndVariations(sourceEntity).filter(SimpleFeature)

    // Check Features to extract do exist
    for (featName : op.spec.features.features)
      if (!featsToExtract.exists[f | f.name.equals(featName)])
        throw new IllegalArgumentException("EntityExtractOp - Cannot extract Feature \"" + featName + "\" because it does not exist: " + orionIO.serialize(op))

    // Exception if the extracted entity name already exist.
    if (aHandler.getEntityDecl(schema, op.spec.name) !== null)
      throw new IllegalArgumentException("EntityExtractOp - Cannot extract to an Entity that already exists: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityMergeOp op)
  {
    val sourceEntity1 = aHandler.getEntityDecl(schema, op.spec.ref1)
    val sourceEntity2 = aHandler.getEntityDecl(schema, op.spec.ref2)

    // Exception if one of the source entities do not exist, or the merged entity does already exist.
    if (sourceEntity1 === null || sourceEntity2 === null)
      throw new IllegalArgumentException("EntityMergeOp - Cannot merge an Entity because it does not exist: " + orionIO.serialize(op))

    if (aHandler.getEntityDecl(schema, op.spec.name) !== null)
      throw new IllegalArgumentException("EntityMergeOp - Cannot merge to an Entity that already exists: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityDelVarOp op)
  {
    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)

    if (sourceEntity === null || !(sourceEntity instanceof RegularEntityDecl))
      throw new IllegalArgumentException("EntityDelVarOp - Cannot delete a variation from an Entity that does not exist or does not have variations: " + orionIO.serialize(op))

    if(!(sourceEntity as RegularEntityDecl).variations.exists[v | v.name.equals(op.spec.target)])
      throw new IllegalArgumentException("EntityDelVarOp - Cannot delete a variation that does not exist on the given Entity: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityAdaptOp op)
  {
    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.ref)

    if (sourceEntity === null || !(sourceEntity instanceof RegularEntityDecl))
      throw new IllegalArgumentException("EntityAdaptOp - Cannot adapt a variation from an Entity that does not exist or does not have variations: " + orionIO.serialize(op))

    if(!(sourceEntity as RegularEntityDecl).variations.exists[v | v.name.equals(op.spec.source)])
      throw new IllegalArgumentException("EntityDelVarOp - Cannot adapt a variation that does not exist on the given Entity: " + orionIO.serialize(op))

    if(!(sourceEntity as RegularEntityDecl).variations.exists[v | v.name.equals(op.spec.target)])
      throw new IllegalArgumentException("EntityDelVarOp - Cannot adapt to a variation that does not exist on the given Entity: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, EntityUnionOp op)
  {
    if (aHandler.getEntityDecl(schema, op.spec.ref) === null)
      throw new IllegalArgumentException("EntityExtractOp - Cannot union an Entity that does not exist: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, FeatureDeleteOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("FeatureDeleteOp - Cannot delete features of an Entity that does not exist: " + orionIO.serialize(op))

    // If it is a short entity we just check the feature exists
    if ((sourceEntity instanceof ShortEntityDecl || (sourceEntity instanceof RegularEntityDecl && op.spec.selector.variations.empty))
      && op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("FeatureDeleteOp - Cannot delete features of an Entity that does not exist: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, FeatureRenameOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("FeatureRenameOp - Cannot rename features of an Entity that does not exist: " + orionIO.serialize(op))

    // If no variations were given we just check the feature exists
    if (op.spec.selector.variations.empty && aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.selector.target) === null)
      throw new IllegalArgumentException("FeatureRenameOp - Cannot rename features of an Entity that does not exist: " + orionIO.serialize(op))

    if (aHandler.getFeaturesInSchemaTypeAndVariations(sourceEntity).exists[f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(op.spec.name)])
      throw new IllegalArgumentException("FeatureRenameOp - Cannot rename features to an already existing name: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, FeatureCopyOp op)
  {
    if (op.spec.sourceSelector.forAll || op.spec.targetSelector.forAll)
      throw new IllegalArgumentException("FeatureCopyOp - ForAll is not allowed here: " + orionIO.serialize(op))

    if (!op.spec.sourceSelector.variations.empty)
      throw new IllegalArgumentException("FeatureCopyOp - Variations cannot be selected on source Entity: " + orionIO.serialize(op))

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.sourceSelector.ref)
    if (sourceEntity === null)
      throw new IllegalArgumentException("FeatureCopyOp - Cannot find source Entity: " + orionIO.serialize(op))

    if (op.spec.sourceSelector.variations.empty && aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.sourceSelector.target) === null)
      throw new IllegalArgumentException("FeatureCopyOp - Cannot find source Feature: " + orionIO.serialize(op))

    val targetEntity = aHandler.getEntityDecl(schema, op.spec.targetSelector.ref)
    if (targetEntity === null)
      throw new IllegalArgumentException("FeatureCopyOp - Cannot find target Entity: " + orionIO.serialize(op))

    if (aHandler.getFeaturesInSchemaTypeAndVariations(targetEntity).exists[f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(op.spec.targetSelector.target)])
      throw new IllegalArgumentException("FeatureCopyOp - Cannot copy features when they already exist on target: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, FeatureMoveOp op)
  {
    if (op.spec.sourceSelector.forAll || op.spec.targetSelector.forAll)
      throw new IllegalArgumentException("FeatureMoveOp - ForAll is not allowed here: " + orionIO.serialize(op))

    if (!op.spec.sourceSelector.variations.empty)
      throw new IllegalArgumentException("FeatureMoveOp - Variations cannot be selected on source Entity: " + orionIO.serialize(op))

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.sourceSelector.ref)
    if (sourceEntity === null)
      throw new IllegalArgumentException("FeatureMoveOp - Cannot find source Entity: " + orionIO.serialize(op))

    if (op.spec.sourceSelector.variations.empty && aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.sourceSelector.target) === null)
      throw new IllegalArgumentException("FeatureMoveOp - Cannot find source Feature: " + orionIO.serialize(op))

    val targetEntity = aHandler.getEntityDecl(schema, op.spec.targetSelector.ref)
    if (targetEntity === null)
      throw new IllegalArgumentException("FeatureMoveOp - Cannot find target Entity: " + orionIO.serialize(op))

    if (aHandler.getFeaturesInSchemaTypeAndVariations(targetEntity).exists[f | f instanceof SimpleFeature && (f as SimpleFeature).name.equals(op.spec.targetSelector.target)])
      throw new IllegalArgumentException("FeatureMoveOp - Cannot move features when they already exist on target: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, FeatureNestOp op)
  {
    if (op.spec.selector.forAll)
      throw new IllegalArgumentException("FeatureNestOp - ForAll is not allowed here: " + orionIO.serialize(op))

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("FeatureNestOp - Cannot nest Features of an Entity that does not exist: " + orionIO.serialize(op))

    if (op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("FeatureNestOp - Cannot find all Features to nest: " + orionIO.serialize(op))

    // TODO: We do not allow variations on this operation because it shows some problems at schema level.
    if (!op.spec.selector.variations.empty)
      throw new IllegalArgumentException("FeatureNestOp - Variations are not allowed here: " + orionIO.serialize(op))
    //if (!aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.name).empty)
    //  throw new IllegalArgumentException("FeatureNestOp - Cannot nest Features into an existing field: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, FeatureUnnestOp op)
  {
    if (op.spec.selector.forAll)
      throw new IllegalArgumentException("FeatureUnnestOp - ForAll is not allowed here: " + orionIO.serialize(op))

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("FeatureUnnestOp - Cannot nest Features of an Entity that does not exist: " + orionIO.serialize(op))

    if (op.spec.selector.targets.exists[t | !t.contains(".")])
      throw new IllegalArgumentException("FeatureUnnestOp - Features to unnest must use \"dot\" notation: " + orionIO.serialize(op))

    if (!op.spec.selector.variations.empty)
      throw new IllegalArgumentException("FeatureUnnestOp - Variations are not allowed here: " + orionIO.serialize(op))

    // We do not check if there is a field on the Entity with that name.
    // TODO: We do not allow variations on this operation because it shows some problems at schema level.
    for (String featToUnnest : op.spec.selector.targets)
    {
      val aggrToUnnest = featToUnnest.substring(0, featToUnnest.indexOf("."))
      val featAggr = aHandler.getSimpleFeatureInSchemaType(sourceEntity, aggrToUnnest)

      if (featAggr === null || !(featAggr.type instanceof SimpleAggregateTarget))
        throw new IllegalArgumentException("FeatureUnnestOp - Cannot find Aggregate " + aggrToUnnest + ": " + orionIO.serialize(op))

      if (!(featAggr.type as SimpleAggregateTarget).multiplicity.equals("?") && !(featAggr.type as SimpleAggregateTarget).multiplicity.equals("&"))
        throw new IllegalArgumentException("FeatureUnnestOp - Cannot unnest a ..* Aggregate: " + orionIO.serialize(op))
    }
  }

  private def dispatch void validateOp(AthenaSchema schema, AttributeAddOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("AttributeAddOp - Cannot create features of an Entity that does not exist: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, AttributeCastOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("AttributeCastOp - Cannot cast Attributes on an Entity that does not exist: " + orionIO.serialize(op))

    // If it is a short entity we just check the feature exists
    if ((sourceEntity instanceof ShortEntityDecl || (sourceEntity instanceof RegularEntityDecl && op.spec.selector.variations.empty))
      && op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("AttributeCastOp - Cannot find Attributes to cast: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, AttributePromoteOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("AttributePromoteOp - Cannot promote Attributes on an Entity that does not exist: " + orionIO.serialize(op))

    // If it is a short entity we just check the feature exists
    if ((sourceEntity instanceof ShortEntityDecl || (sourceEntity instanceof RegularEntityDecl && op.spec.selector.variations.empty))
      && op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("AttributePromoteOp - Cannot find Attributes to promote: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, AttributeDemoteOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("AttributeDemoteOp - Cannot demote Attributes on an Entity that does not exist: " + orionIO.serialize(op))

    // If it is a short entity we just check the feature exists
    if ((sourceEntity instanceof ShortEntityDecl || (sourceEntity instanceof RegularEntityDecl && op.spec.selector.variations.empty))
      && op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("AttributeDemoteOp - Cannot find Attributes to demote: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, ReferenceAddOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("ReferenceAddOp - Cannot create features of an Entity that does not exist: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, ReferenceCastOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("ReferenceCastOp - Cannot cast References on an Entity that does not exist: " + orionIO.serialize(op))

    // If it is a short entity we just check the feature exists
    if ((sourceEntity instanceof ShortEntityDecl || (sourceEntity instanceof RegularEntityDecl && op.spec.selector.variations.empty))
      && op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("ReferenceCastOp - Cannot find References to cast: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, ReferenceMultiplicityOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("ReferenceMultiplicityOp - Cannot change multiplicity on References on an Entity that does not exist: " + orionIO.serialize(op))

    // If it is a short entity we just check the feature exists
    if ((sourceEntity instanceof ShortEntityDecl || (sourceEntity instanceof RegularEntityDecl && op.spec.selector.variations.empty))
      && op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("ReferenceCastOp - Cannot find References to change multiplicity: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, ReferenceMorphOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("ReferenceMorphOp - Cannot morph References on an Entity that does not exist: " + orionIO.serialize(op))

    // If no variations were given we just check the feature exists
    if (op.spec.selector.variations.empty && aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.selector.target) === null)
      throw new IllegalArgumentException("ReferenceMorphOp - Cannot find a Reference to morph: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, AggregateAddOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("AggregateAddOp - Cannot create features of an Entity that does not exist: " + orionIO.serialize(op))
  }

  private def dispatch void validateOp(AthenaSchema schema, AggregateMultiplicityOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("AggregateMultiplicityOp - Cannot change multiplicity on Aggregates on an Entity that does not exist: " + orionIO.serialize(op))

    // If it is a short entity we just check the feature exists
    if ((sourceEntity instanceof ShortEntityDecl || (sourceEntity instanceof RegularEntityDecl && op.spec.selector.variations.empty))
      && op.spec.selector.targets.exists[t | aHandler.getSimpleFeatureInSchemaType(sourceEntity, t) === null])
      throw new IllegalArgumentException("ReferenceCastOp - Cannot find Aggregate to change multiplicity: " + orionIO.serialize(op))

    // If variations were defined we will not check if the feature does exist for all variations.
  }

  private def dispatch void validateOp(AthenaSchema schema, AggregateMorphOp op)
  {
    if (op.spec.selector.forAll)
      return

    val sourceEntity = aHandler.getEntityDecl(schema, op.spec.selector.ref)

    if (sourceEntity === null)
      throw new IllegalArgumentException("AggregateMorphOp - Cannot morph Aggregates on an Entity that does not exist: " + orionIO.serialize(op))

    // If no variations were given we just check the feature exists
    if (op.spec.selector.variations.empty && aHandler.getSimpleFeatureInSchemaType(sourceEntity, op.spec.selector.target) === null)
      throw new IllegalArgumentException("AggregateMorphOp - Cannot find an Aggregate to morph: " + orionIO.serialize(op))
  }
}
