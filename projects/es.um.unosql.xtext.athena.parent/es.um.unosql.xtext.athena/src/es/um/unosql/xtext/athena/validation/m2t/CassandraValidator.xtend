package es.um.unosql.xtext.athena.validation.m2t

import java.util.ArrayList
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.SimpleFeature
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.OptionPrimitiveType
import es.um.unosql.xtext.athena.athena.RestrictedPrimitiveType
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.utils.io.AthenaIO
import es.um.unosql.xtext.athena.athena.RelationshipDecl

class CassandraValidator
{
  val errorLog = new ArrayList
  val warningLog = new ArrayList
  val factory = new AthenaFactory
  val handler = new AthenaHandler
  val athenaIO = new AthenaIO

  def validate(AthenaSchema schema)
  {
    if (!EcoreUtil2.getAllContentsOfType(schema, RelationshipDecl).empty)
      warningLog.add("RelationshipDecl - RelationshipDecls cannot be translated to Cassandra. They will be ignored.")

    val optionals = EcoreUtil2.getAllContentsOfType(schema, OptionPrimitiveType)
    if (!optionals.empty)
      warningLog.add("OptionPrimitiveType - An OptionPrimitiveType is not allowed in Cassandra. Taking the most open type of the Option: " + optionals.map[e | athenaIO.serialize(e)].join(", "))

    val restricteds = EcoreUtil2.getAllContentsOfType(schema, RestrictedPrimitiveType)
    if (!restricteds.empty)
      warningLog.add("RestrictedPrimitiveType - A RestrictedPrimitiveType is not allowed in Cassandra: " + restricteds.map[e | athenaIO.serialize(e)].join(", "))

    val composedRefs = EcoreUtil2.getAllContentsOfType(schema, ComposedReference)
    if (!composedRefs.empty)
      warningLog.add("ComposedReference - A ComposedReference is not allowed in Cassandra: " + composedRefs.map[e | athenaIO.serialize(e)].join(", "))

    for (EntityDecl entity : EcoreUtil2.getAllContentsOfType(schema, EntityDecl).filter[e | e.isRoot])
      if (!handler.getFeaturesInSchemaType(entity).exists[f | f instanceof SimpleFeature && (f as SimpleFeature).key])
        errorLog.add("EntityDecl - A root Entity was found without a key. Please add a key: " + athenaIO.serialize(entity) + "\n")

    EcoreUtil2.getAllContentsOfType(schema, SimpleFeature).forEach[f | checkSimpleFeature(f)]
  }

  def getSummary()
  {
    if (!warningLog.empty)
      System.err.println("Some warnings were detected on the Athena file:\n" + warningLog.join("\n"))

    if (!errorLog.empty)
      throw new IllegalArgumentException("Some errors were detected on the Athena file:\n" + errorLog.join("\n"))
  }

  private def checkSimpleFeature(SimpleFeature feat)
  {
    if (feat.name.startsWith("_"))
    {
      warningLog.add("SimpleFeature - In Cassandra a field cannot start with \"_\". Removing that character: " + athenaIO.serialize(feat) + "\n")
      feat.name = feat.name.replaceFirst("^_*", "")
    }

    feat.type = checkDataType(feat.type)
  }

  private def dispatch checkDataType(SimpleReferenceTarget ref)
  {
    if (ref.multiplicity === null)
    {
      warningLog.add("SimpleFeature - In Cassandra a reference must have a multiplicity. Setting it to 0..1: " + athenaIO.serialize(ref.eContainer) + "\n")
      ref.multiplicity = "+"
    }

    return ref
  }

  private def dispatch checkDataType(SimpleAggregateTarget aggr)
  {
    if (aggr.multiplicity === null)
    {
      warningLog.add("SimpleFeature - In Cassandra a reference must have a multiplicity. Setting it to 0..1: " + athenaIO.serialize(aggr.eContainer) + "\n")
      aggr.multiplicity = "+"
    }

    return aggr
  }

  private def dispatch checkDataType(DataType theType)
  {
    if (theType === null)
    {
      warningLog.add("SimpleFeature - In Cassandra any column need to have a non-null type. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
      return factory.createUnrestrictedPrimitiveType("String")
    }
    else
      switch (theType)
      {
        case theType instanceof Null:
        {
          warningLog.add("SimpleFeature - In Cassandra a column of type Null can't be created. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
          return factory.createUnrestrictedPrimitiveType("String")
        }
        case theType instanceof Map:
        {
          val type = theType as Map
          if (type.keyType === null)
          {
            warningLog.add("SimpleFeature - In Cassandra a Map has to define a primitive Key type. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.keyType = factory.createUnrestrictedPrimitiveType("String")
          }
          if (type.valueType === null || (type.valueType instanceof Null))
          {
            warningLog.add("SimpleFeature - In Cassandra a Map can't be of Null values. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.valueType = factory.createUnrestrictedPrimitiveType("String")
          }

          return type
        }
        case theType instanceof List:
        {
          val type = theType as List
          if (type.elementType === null || (type.elementType instanceof Null))
          {
            warningLog.add("SimpleFeature - In Cassandra a List can't be of Nulls. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.elementType = factory.createUnrestrictedPrimitiveType("String")
          }

          return type
        }
        case theType instanceof Set:
        {
          val type = theType as Set
          if (type.elementType === null || (type.elementType instanceof Null))
          {
            warningLog.add("SimpleFeature - In Cassandra a Set can't be of Nulls. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.elementType = factory.createUnrestrictedPrimitiveType("String")
          }

          return type
        }
        case theType instanceof Tuple:
        {
          val type = theType as Tuple
          if (type.elements.empty || type.elements.exists[e | (e instanceof Null)])
          {
            warningLog.add("SimpleFeature - In Cassandra a Tuple can't be of Nulls. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.elements.clear
            type.elements.add(factory.createUnrestrictedPrimitiveType("String"))
          }

          return type
        }
      }

    return theType
  }
}
