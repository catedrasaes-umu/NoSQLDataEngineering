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
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.EntityDecl
import es.um.unosql.xtext.athena.utils.AthenaHandler
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.athena.RelationshipDecl
import es.um.unosql.xtext.athena.utils.io.AthenaIO

class MySQLValidator
{
  val errorLog = new ArrayList
  val warningLog = new ArrayList
  val factory = new AthenaFactory
  val handler = new AthenaHandler
  val athenaIO = new AthenaIO

  def validate(AthenaSchema schema)
  {
    if (!EcoreUtil2.getAllContentsOfType(schema, RelationshipDecl).empty)
      warningLog.add("RelationshipType - A RelationshipType is not allowed in MySQL. It will be ignored.")

    if (EcoreUtil2.getAllContentsOfType(schema, SimpleReferenceTarget).exists[r | r.multiplicity.equals("*") || r.multiplicity.equals("+")])
      errorLog.add("SimpleReferenceTarget - A SimpleReferenceTarget[..*] is not allowed in MySQL. Please allow the MYSQL_NORMALIZE option.")

    if (EcoreUtil2.getAllContentsOfType(schema, SimpleAggregateTarget).exists[a | a.multiplicity.equals("*") || a.multiplicity.equals("+")])
      errorLog.add("SimpleAggregateTarget - A SimpleAggregateTarget[..*] is not allowed in MySQL. Please allow the MYSQL_NORMALIZE option.")

    if (!EcoreUtil2.getAllContentsOfType(schema, List).empty)
      warningLog.add("List - A List is not allowed in MySQL. It will be generated as JSON unless the MYSQL_NORMALIZE option is set to True.")

    if (!EcoreUtil2.getAllContentsOfType(schema, Set).empty)
      warningLog.add("Set - A Set is not allowed in MySQL. It will be generated as JSON unless the MYSQL_NORMALIZE option is set to True.")

    if (!EcoreUtil2.getAllContentsOfType(schema, Map).empty)
      warningLog.add("Map - A Map is not allowed in MySQL. It will be generated as JSON unless the MYSQL_NORMALIZE option is set to True.")

    if (!EcoreUtil2.getAllContentsOfType(schema, Tuple).empty)
      warningLog.add("Tuple - A Tuple is not allowed in MySQL. It will be generated as JSON unless the MYSQL_NORMALIZE option is set to True.")

    if (!EcoreUtil2.getAllContentsOfType(schema, OptionPrimitiveType).empty)
      warningLog.add("OptionPrimitiveType - An OptionPrimitiveType is not allowed in MySQL. Taking the most open type of the Option.")

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
      warningLog.add("SimpleFeature - In MySQL a field cannot start with \"_\". Removing that character: " + athenaIO.serialize(feat) + "\n")
      feat.name = feat.name.replaceFirst("^_*", "")
    }

    feat.type = checkDataType(feat.type)
  }

  private def dispatch checkDataType(SimpleReferenceTarget ref)
  {
    if (ref.multiplicity === null)
    {
      warningLog.add("SimpleFeature - In MySQL a reference must have a multiplicity. Setting it to 1..1: " + athenaIO.serialize(ref.eContainer) + "\n")
      ref.multiplicity = "&"
    }

    return ref
  }

  private def dispatch checkDataType(SimpleAggregateTarget aggr)
  {
    if (aggr.multiplicity === null)
    {
      warningLog.add("SimpleFeature - In MySQL a reference must have a multiplicity. Setting it to 1..1: " + athenaIO.serialize(aggr.eContainer) + "\n")
      aggr.multiplicity = "&"
    }

    return aggr
  }

  private def dispatch checkDataType(DataType theType)
  {
    if (theType === null)
    {
      warningLog.add("SimpleFeature - In MySQL any column need to have a non-null type. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
      return factory.createUnrestrictedPrimitiveType("String")
    }
    else
      switch (theType)
      {
        case theType instanceof Null:
        {
          warningLog.add("SimpleFeature - In MySQL a column of type Null can't be created. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
          return factory.createUnrestrictedPrimitiveType("String")
        }
        case theType instanceof Map:
        {
          val type = theType as Map
          if (type.keyType === null)
          {
            warningLog.add("SimpleFeature - In MySQL a Map has to define a primitive Key type. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.keyType = factory.createUnrestrictedPrimitiveType("String")
          }
          if (type.valueType === null || (type.valueType instanceof Null))
          {
            warningLog.add("SimpleFeature - In MySQL a Map can't be of Null values. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.valueType = factory.createUnrestrictedPrimitiveType("String")
          }

          return type
        }
        case theType instanceof List:
        {
          val type = theType as List
          if (type.elementType === null || !(type.elementType instanceof SinglePrimitiveType))
          {
            warningLog.add("SimpleFeature - In MySQL a List can only be of Primitive types. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.elementType = factory.createUnrestrictedPrimitiveType("String")
          }

          return type
        }
        case theType instanceof Set:
        {
          val type = theType as Set
          if (type.elementType === null || !(type.elementType instanceof SinglePrimitiveType))
          {
            warningLog.add("SimpleFeature - In MySQL a Set can only be of Primitive types. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.elementType = factory.createUnrestrictedPrimitiveType("String")
          }

          return type
        }
        case theType instanceof Tuple:
        {
          val type = theType as Tuple
          if (type.elements.empty || type.elements.exists[e | !(e instanceof SinglePrimitiveType)])
          {
            warningLog.add("SimpleFeature - In MySQL a Tuple can only be of Primitive types. Replacing by String: " + athenaIO.serialize(theType.eContainer) + "\n")
            type.elements.clear
            type.elements.add(factory.createUnrestrictedPrimitiveType("String"))
          }

          return type
        }
      }

    return theType
  }
}
