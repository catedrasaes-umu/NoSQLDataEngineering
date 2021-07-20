package es.um.unosql.xtext.athena.m2m

import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.athena.DataType
import org.eclipse.emf.common.util.BasicEList
import java.util.Arrays

class Athena2MySQLAthena
{
  val handler = new AthenaHandler()
  val factory = new AthenaFactory()

  def AthenaSchema m2m(AthenaSchema schema, boolean mysqlNormalizeRelations, boolean mysqlNormalizeCollections)
  {
    if (mysqlNormalizeRelations)
    {
      // Transform each Reference 1..* by moving it to target Entity and letting it reference the original Entity container.
      for (ref : EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget).filter[r | r.multiplicity.equals("*")])
      {
        val oldContainer = EcoreUtil2.getContainerOfType(ref, EntityDecl)
  
        handler.addFeatureToSchemaType(ref.ref, ref.eContainer as SimpleFeature)
        ref.ref = oldContainer
        ref.multiplicity = "+"
        val keyFeat = handler.getKeyInSchemaType(oldContainer)
  
        if (keyFeat !== null && keyFeat.type !== null && keyFeat.type instanceof PrimitiveType)
          ref.type = EcoreUtil2.copy(keyFeat.type) as PrimitiveType
      }
  
      // Transform each Aggregate 1..* by moving it to target Entity and letting it reference the original Entity container.
      for (aggr : EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).filter[a | a.multiplicity.equals("*")])
      {
        val oldContainer = EcoreUtil2.getContainerOfType(aggr, EntityDecl)
  
        handler.addFeatureToSchemaType(aggr.aggr.head instanceof EntityDecl ? aggr.aggr.head as EntityDecl : aggr.aggr.head.eContainer as EntityDecl, aggr.eContainer as SimpleFeature)
        aggr.aggr.clear
        aggr.aggr.add(oldContainer)
        aggr.multiplicity = "+"
      }
    }

    if (mysqlNormalizeCollections)
    {
      for (list : EcoreUtil2.getAllContentsOfType(schema, List))
        transformList(schema, list)
  
      for (set : EcoreUtil2.getAllContentsOfType(schema, Set))
        transformSet(schema, set)
  
      for (map : EcoreUtil2.getAllContentsOfType(schema, Map))
        transformMap(schema, map)
  
      for (tuple : EcoreUtil2.getAllContentsOfType(schema, Tuple))
        transformTuple(schema, tuple)
    }

    return schema
  }

  private def transformList(AthenaSchema schema, List list)
  {
    createManyToManyEntities(schema, list, false)
  }

  private def transformSet(AthenaSchema schema, Set set)
  {
    createManyToManyEntities(schema, set, true)
  }

  private def transformMap(AthenaSchema schema, Map map)
  {
    createManyToManyEntities(schema, map, false)
  }

  private def transformTuple(AthenaSchema schema, Tuple tuple)
  {
    createManyToManyEntities(schema, tuple, false)
  }

  private def createManyToManyEntities(AthenaSchema schema, DataType dType, boolean uniques)
  {
    val feat = dType.eContainer as SimpleFeature
    val oldContainer = EcoreUtil2.getContainerOfType(dType, EntityDecl)
    handler.removeSimpleFeatureInSchemaType(oldContainer, feat.name)

    var EntityDecl finalEntity = null
    if (schema.entities.exists[e | e.name.equals(feat.name.toFirstUpper)])
      finalEntity = handler.getEntityDecl(schema, feat.name.toFirstUpper)
    else
    {
      finalEntity = factory.createShortEntityDecl(feat.name.toFirstUpper, true)
      schema.entities.add(finalEntity)
    }

    if (handler.getKeyInSchemaType(finalEntity) === null)
    {
      val key = factory.createSimpleFeature("id", factory.createUnrestrictedPrimitiveType("int"))
      key.key = true
      handler.addFeatureToSchemaType(finalEntity, key)

      if (dType instanceof Map)
      {
        val key2 = factory.createSimpleFeature("key", factory.createUnrestrictedPrimitiveType(((dType as Map).keyType as SinglePrimitiveType).typename))
        key2.key = true
        handler.addFeatureToSchemaType(finalEntity, key2)
      }
    }
    if (handler.getSimpleFeatureInSchemaType(finalEntity, feat.name) === null)
    {
      if (dType instanceof Tuple)
        for (e : (dType as Tuple).elements)
          handler.addFeatureToSchemaType(finalEntity, factory.createSimpleFeature(feat.name + "_" + (dType as Tuple).elements.indexOf(e), factory.createUnrestrictedPrimitiveType((e as SinglePrimitiveType).typename)))
      else if (dType instanceof List || dType instanceof Set)
      {
        val listOrSetType = dType instanceof List ? ((dType as List).elementType as SinglePrimitiveType).typename : ((dType as Set).elementType as SinglePrimitiveType).typename
        val listOrSetField = factory.createSimpleFeature(feat.name, factory.createUnrestrictedPrimitiveType(listOrSetType))
        listOrSetField.unique = uniques
  
        handler.addFeatureToSchemaType(finalEntity, listOrSetField)
      }
      else // Map
      {
        handler.addFeatureToSchemaType(finalEntity, factory.createSimpleFeature("value", factory.createUnrestrictedPrimitiveType(((dType as Map).valueType as SinglePrimitiveType).typename)))
      }
    }

    createBridgeEntity(schema, oldContainer, finalEntity)
  }

  private def createBridgeEntity(AthenaSchema schema, EntityDecl entity1, EntityDecl entity2)
  {
    val unionName = entity1.name + "_" + entity2.name
    var EntityDecl unionEntity = null
    if (schema.entities.exists[e | e.name.equals(unionName)])
      unionEntity = handler.getEntityDecl(schema, unionName)
    else
    {
      unionEntity = factory.createShortEntityDecl(unionName, true)
      schema.entities.add(unionEntity)
    }

    if (handler.getKeyInSchemaType(unionEntity) === null)
    {
      val key1 = factory.createSimpleFeature("id1", factory.createSimpleRef(entity1, "+", factory.createUnrestrictedPrimitiveType((handler.getKeyInSchemaType(entity1).type as SinglePrimitiveType).typename)))
      key1.key = true
      handler.addFeatureToSchemaType(unionEntity, key1)

      if (handler.getFeaturesInSchemaType(entity2).filter(SimpleFeature).filter[f | f.isKey].size == 1)
      {
        val key2 = factory.createSimpleFeature("id2", factory.createSimpleRef(entity2, "+", factory.createUnrestrictedPrimitiveType((handler.getKeyInSchemaType(entity2).type as SinglePrimitiveType).typename)))
        key2.key = true
        handler.addFeatureToSchemaType(unionEntity, key2)
      }
      else // There were two keys => We are modeling a Map
      {
        val key2 = factory.createSimpleFeature("id2", factory.createUnrestrictedPrimitiveType((handler.getKeyInSchemaType(entity2).type as SinglePrimitiveType).typename))
        key2.key = true
        handler.addFeatureToSchemaType(unionEntity, key2)

        val key3 = factory.createSimpleFeature("key", factory.createUnrestrictedPrimitiveType((handler.getFeaturesInSchemaType(entity2).filter(SimpleFeature).filter[f | f.isKey].last.type as SinglePrimitiveType).typename))
        key3.key = true
        handler.addFeatureToSchemaType(unionEntity, key3)
        handler.addFeatureToSchemaType(unionEntity, factory.createComposedReference(new BasicEList(Arrays.asList("id2", "key")), entity2))
      }
    }

    schema.entities.add(unionEntity)
  }
}
