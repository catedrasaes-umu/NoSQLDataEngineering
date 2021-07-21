package es.um.unosql.xtext.orion.m2m

import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.orion.utils.OrionFactory
import es.um.unosql.xtext.athena.athena.ShortEntityDecl
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.athena.ShortRelationshipDecl
import es.um.unosql.xtext.athena.athena.RegularRelationshipDecl
import es.um.unosql.xtext.orion.orion.SimpleDataFeature
import java.util.ArrayList
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import java.util.List
import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.AggregateAddOp

class Athena2Orion
{
  OrionOperations orionSpec
  AthenaHandler athenaHandler
  OrionFactory factory

  new()
  {
    this.athenaHandler = new AthenaHandler()
    this.factory = new OrionFactory()
  }

  def OrionOperations m2m(AthenaSchema schema)
  {
    val simplifiedSchema = new AthenaNormalizer().m2m(schema)
    orionSpec = factory.createOrionOperations(simplifiedSchema.id.name)

    for (entity : schema.entities.filter[e | e.isRoot])
      processSchemaType(entity)

    for (relationship : schema.relationships)
      processSchemaType(relationship)

    return orionSpec
  }

  private def dispatch processSchemaType(ShortEntityDecl entity)
  {
    val featuresInEntity = athenaHandler.getFeaturesInSchemaType(entity)

    // Generate the EntityAddOp with base features, and then ReferenceAddOps and AggregateAddOps (recursively if needed)
    orionSpec.operations.add(generateEntityAddOp(entity.name, featuresInEntity.filter(SimpleFeature).filter[f | f.type instanceof DataType].toList))
    orionSpec.operations.addAll(generateRefAddOps(entity.name, featuresInEntity.filter(SimpleFeature).filter[f | f.type instanceof SimpleReferenceTarget].toList))
    orionSpec.operations.addAll(generateAggrAddOps(entity.name, featuresInEntity.filter(SimpleFeature).filter[f | f.type instanceof SimpleAggregateTarget].toList))
  }

  private def dispatch processSchemaType(RegularEntityDecl entity)
  {
    val featuresInEntity = athenaHandler.getFeaturesInSchemaType(entity)

    // Generate the EntityAddOp with base features, and then ReferenceAddOps and AggregateAddOps (recursively if needed)
    orionSpec.operations.add(generateEntityAddOp(entity.name, featuresInEntity.filter(SimpleFeature).filter[f | f.type instanceof DataType].toList))
    orionSpec.operations.addAll(generateRefAddOps(entity.name, featuresInEntity.filter(SimpleFeature).filter[f | f.type instanceof SimpleReferenceTarget].toList))
    orionSpec.operations.addAll(generateAggrAddOps(entity.name, featuresInEntity.filter(SimpleFeature).filter[f | f.type instanceof SimpleAggregateTarget].toList))

    // Now for each variation generate specific operations for features. Selectors will be adapted to include only over the specific variation.
    // TODO: We could optimize this process by creating a Map<Feature, List<Variations>> and packing a single operation with all the variations in which it appears.
    for (variation : entity.variations)
    {
      val featuresInVariation = athenaHandler.getFeaturesInVariation(variation)
      val attrAddOps = generateAttributeAddOps(entity.name, featuresInVariation.filter(SimpleFeature).filter[f | f.type instanceof DataType].toList)
      attrAddOps.forEach[op | op.spec.selector.variations.add(variation.name)]
      orionSpec.operations.addAll(attrAddOps)

      val refAddOps = generateRefAddOps(entity.name, featuresInVariation.filter(SimpleFeature).filter[f | f.type instanceof SimpleReferenceTarget].toList)
      refAddOps.forEach[op | op.spec.selector.variations.add(variation.name)]
      orionSpec.operations.addAll(refAddOps)

      val aggrAddOps = generateAggrAddOps(entity.name, featuresInVariation.filter(SimpleFeature).filter[f | f.type instanceof SimpleAggregateTarget].toList)
      aggrAddOps.forEach[op | op instanceof ReferenceAddOp ? op.spec.selector.variations.add(variation.name); op instanceof AggregateAddOp ? op.spec.selector.variations.add(variation.name)]
      orionSpec.operations.addAll(aggrAddOps)
    }
  }

  private def dispatch processSchemaType(ShortRelationshipDecl entity)
  {
    //TODO: As soon as RelationshipOps are available.
  }

  private def dispatch processSchemaType(RegularRelationshipDecl entity)
  {
    //TODO: As soon as RelationshipOps is available.
  }

  private def EntityAddOp generateEntityAddOp(String name, List<SimpleFeature> sFeatures)
  {
    val simpleFeatures = new ArrayList<SimpleDataFeature>()

    for (feat : sFeatures)
      simpleFeatures.add(processFeature(feat.name, feat.type as DataType, feat.key, feat.unique, feat.optional))

    return factory.createEntityAddOp(name, simpleFeatures)
  }

  private def List<AttributeAddOp> generateAttributeAddOps(String name, List<SimpleFeature> attributes)
  {
    val result = new ArrayList<AttributeAddOp>()

    for (attr : attributes)
      result.add(factory.createAttributeAddOp(factory.createSingleFeatureSelector(name, attr.name), attr.type as DataType))

    return result
  }

  private def List<ReferenceAddOp> generateRefAddOps(String name, List<SimpleFeature> references)
  {
    val result = new ArrayList<ReferenceAddOp>()

    for (ref : references)
      result.add(factory.createReferenceAddOp(
        factory.createSingleFeatureSelector(name, ref.name),
        (ref.type as SimpleReferenceTarget).type,
        (ref.type as SimpleReferenceTarget).multiplicity,
        ref.optional,
        (ref.type as SimpleReferenceTarget).ref.name
      ))

    return result
  }

  private def List<BasicOperation> generateAggrAddOps(String name, List<SimpleFeature> aggregates)
  {
    val result = new ArrayList<BasicOperation>()

    for (aggrFeat : aggregates)
    {
      val aggrType = aggrFeat.type as SimpleAggregateTarget
      val featuresInAggr = athenaHandler.getFeaturesInAggregate(aggrType.aggr)
      val simpleFeatures = new ArrayList<SimpleDataFeature>()
  
      for (feat : featuresInAggr.filter(SimpleFeature).filter[f | f.type instanceof DataType])
        simpleFeatures.add(processFeature(feat.name, feat.type as DataType, feat.key, feat.unique, feat.optional))

      result.add(factory.createAggregateAddOp(
        factory.createSingleFeatureSelector(name, aggrFeat.name),
        simpleFeatures,
        aggrType.multiplicity,
        aggrFeat.isOptional,
        athenaHandler.getSimpleAggregateTargetName(aggrType)
      ))

      result.addAll(generateRefAddOps(athenaHandler.getSimpleAggregateTargetName(aggrType), featuresInAggr.filter(SimpleFeature).filter[f | f.type instanceof SimpleReferenceTarget].toList))
      result.addAll(generateAggrAddOps(athenaHandler.getSimpleAggregateTargetName(aggrType), featuresInAggr.filter(SimpleFeature).filter[f | f.type instanceof SimpleAggregateTarget].toList))
    }

    return result
  }

  private def SimpleDataFeature processFeature(String name, DataType type, Boolean key, Boolean unique, Boolean optional)
  {
    val result = factory.createSimpleDataFeature(name, type)
    result.key = key
    result.unique = unique
    result.optional = optional

    return result
  }
}
