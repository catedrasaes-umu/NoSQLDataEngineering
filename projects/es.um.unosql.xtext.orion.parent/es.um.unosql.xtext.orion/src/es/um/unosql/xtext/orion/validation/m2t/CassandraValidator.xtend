package es.um.unosql.xtext.orion.validation.m2t

import java.util.ArrayList
import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.orion.utils.OrionFactory
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.orion.orion.SimpleDataFeature
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.orion.utils.io.OrionIO
import es.um.unosql.xtext.orion.orion.AggregateAddOp

class CassandraValidator
{
  val errorLog = new ArrayList()
  val warningLog = new ArrayList()
  val orionIO = new OrionIO()
  val factory = new OrionFactory()
  val aHandler = new AthenaHandler()

  def getSummary()
  {
    if (!warningLog.empty)
      System.err.println("Some warnings were detected on the Orion file:\n" + warningLog.join())

    if (!errorLog.empty)
      throw new IllegalArgumentException("Some errors were detected on the Orion file:\n" + errorLog.join())
  }

  def dispatch checkBasicOperation(AthenaSchema schema, BasicOperation op)
  {
    // This method is used for operations where a validation does not apply...
  }

  def dispatch checkBasicOperation(AthenaSchema schema, EntityAddOp op)
  {
    if (op.spec.features.empty)
      errorLog.add("EntityAddOp - A new Entity should define fields: " + orionIO.serialize(op))
    else
    {
      if (!op.spec.features.exists[f | f.isKey])
        errorLog.add("EntityAddOp - A new Entity should have at least one key field: " + orionIO.serialize(op))

      for(feat : op.spec.features)
        checkSimpleDataFeature(feat)

      if (op.spec.features.exists[f | f.defaultValue !== null] && op.spec.features.exists[f | f.key && f.defaultValue === null])
        errorLog.add("EntityAddOp - When defining an initial value to features, key fields need to be given an initial value too: " + orionIO.serialize(op))
    }
  }

  def dispatch checkBasicOperation(AthenaSchema schema, FeatureDeleteOp op)
  {
    if (op.spec.selector.targets.map[t | aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schema, op.spec.selector.ref), t)].exists[f | f.isKey])
      errorLog.add("FeatureDeleteOp - A key attribute can't be deleted: " + orionIO.serialize(op))
  }

  def dispatch checkBasicOperation(AthenaSchema schema, FeatureRenameOp op)
  {
    if (op.spec.selector.ref.contains("."))
      errorLog.add("FeatureRenameOp - Cannot rename features with dot notation: " + orionIO.serialize(op))
  }

  def dispatch checkBasicOperation(AthenaSchema schema, FeatureCopyOp op)
  {
    val feature = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schema, op.spec.targetSelector.ref), op.spec.condition.c2)
    if (feature === null || !feature.key)
      errorLog.add("FeatureCopyOp - Cannot copy without a key on the target condition: " + orionIO.serialize(op))
  }

  def dispatch checkBasicOperation(AthenaSchema schema, FeatureMoveOp op)
  {
    val feature = aHandler.getSimpleFeatureInSchemaType(aHandler.getEntityDecl(schema, op.spec.targetSelector.ref), op.spec.condition.c2)
    if (feature === null || !feature.key)
      errorLog.add("FeatureMoveOp - Cannot Move without a key on the target condition: " + orionIO.serialize(op))
  }

  def dispatch checkBasicOperation(AthenaSchema schema, FeatureNestOp op)
  {
    warningLog.add("FeatureNestOp - Fields will be removed and nested but cannot be populated: " + orionIO.serialize(op))
  }

  def dispatch checkBasicOperation(AthenaSchema schema, FeatureUnnestOp op)
  {
    warningLog.add("FeatureUnnestOp - Fields will be unnested but will not be removed from the nest nor populated: " + orionIO.serialize(op))
  }

  def dispatch checkBasicOperation(AthenaSchema schema, AttributeAddOp op)
  {
    if (op.spec.isKey)
      warningLog.add("AttributeAddOp - A new key column can't be added. Ignoring key: " + orionIO.serialize(op))

    if (op.spec.selector.target.startsWith("_"))
    {
      warningLog.add("AttributeAddOp - In Cassandra a field cannot start with \"_\". Removing that character: " + orionIO.serialize(op) + "\n")
      op.spec.selector.target = op.spec.selector.target.substring(1)
    }

    op.spec.type = checkDataType(op.spec.type)

    if (op.spec.defaultValue !== null)
      warningLog.add("AttributeAddOp - When adding a new column the default value is ignored: " + orionIO.serialize(op) + "\n")
  }

  def dispatch checkBasicOperation(AthenaSchema schema, ReferenceAddOp op)
  {
    checkDataType(op.spec.type)
  }

  def dispatch checkBasicOperation(AthenaSchema schema, ReferenceMorphOp op)
  {
    if (op.spec.deleteId)
      warningLog.add("ReferenceMorphOp - Can't delete Id from a user-defined type. Ignoring option: " + orionIO.serialize(op))
  }

  def dispatch checkBasicOperation(AthenaSchema schema, AggregateAddOp op)
  {
    if (op.spec.features.empty)
      errorLog.add("AggregateAddOp - In Cassandra an User-defined type should have fields: " + orionIO.serialize(op))
    else
      for(feat : op.spec.features)
        checkSimpleDataFeature(feat)

    if (op.spec.features.exists[f | f.defaultValue !== null])
      warningLog.add("AggregateAddOp - When adding a new column the default value is ignored: " + orionIO.serialize(op) + "\n")
  }

  private def checkSimpleDataFeature(SimpleDataFeature feat)
  {
    if (feat.name.startsWith("_"))
    {
      warningLog.add("SimpleDataFeature - In Cassandra a field cannot start with \"_\". Removing that character: " + orionIO.serialize(feat) + "\n")
      feat.name = feat.name.substring(1)
    }

    feat.type = checkDataType(feat.type)
  }

  private def checkDataType(DataType theType)
  {
    if (theType === null)
    {
      warningLog.add("SimpleDataFeature - In Cassandra any column need to have a non-null type. Replacing by String\n")
      return factory.createPrimitiveType("String")
    }
    else
      switch (theType)
      {
        case theType instanceof Null:
        {
          warningLog.add("SimpleDataFeature - In Cassandra a column of type Null can't be created. Replacing by String: " + orionIO.serialize(theType.eContainer) + "\n")
          return factory.createPrimitiveType("String")
        }
        case theType instanceof Map:
        {
          val type = theType as Map
          if (type.keyType === null)
          {
            warningLog.add("SimpleDataFeature - In Cassandra a Map has to define a primitive Key type. Replacing by String: " + orionIO.serialize(theType.eContainer) + "\n")
            type.keyType = factory.createPrimitiveType("String")
          }
          if (type.valueType === null || (type.valueType instanceof Null))
          {
            warningLog.add("SimpleDataFeature - In Cassandra a Map can't be of Null values. Replacing by String: " + orionIO.serialize(theType.eContainer) + "\n")
            type.valueType = factory.createPrimitiveType("String")
          }

          return type
        }
        case theType instanceof List:
        {
          val type = theType as List
          if (type.elementType === null || (type.elementType instanceof Null))
          {
            warningLog.add("SimpleDataFeature - In Cassandra a List can't be of Nulls. Replacing by String: " + orionIO.serialize(theType.eContainer) + "\n")
            type.elementType = factory.createPrimitiveType("String")
          }

          return type
        }
        case theType instanceof Set:
        {
          val type = theType as Set
          if (type.elementType === null || (type.elementType instanceof Null))
          {
            warningLog.add("SimpleDataFeature - In Cassandra a Set can't be of Nulls. Replacing by String: " + orionIO.serialize(theType.eContainer) + "\n")
            type.elementType = factory.createPrimitiveType("String")
          }

          return type
        }
        case theType instanceof Tuple:
        {
          val type = theType as Tuple
          if (type.elements.empty || type.elements.exists[e | (e instanceof Null)])
          {
            warningLog.add("SimpleDataFeature - In Cassandra a Tuple can't be of Nulls. Replacing by String: " + orionIO.serialize(theType.eContainer) + "\n")
            type.elements.clear
            type.elements.add(factory.createPrimitiveType("String"))
          }

          return type
        }
      }

    return theType
  }
}
