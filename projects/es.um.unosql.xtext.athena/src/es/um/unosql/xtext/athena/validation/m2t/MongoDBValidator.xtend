package es.um.unosql.xtext.athena.validation.m2t

import java.util.ArrayList
import es.um.unosql.xtext.athena.utils.io.ModelSerializer
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.SimpleFeature
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregationTarget
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.RelationshipDecl

class MongoDBValidator
{
  val errorLog = new ArrayList
  val warningLog = new ArrayList
  val serializer = new ModelSerializer

  def validate(AthenaSchema schema)
  {
    if (!EcoreUtil2.getAllContentsOfType(schema, RelationshipDecl).empty)
      warningLog.add("RelationshipType - A RelationshipType is not allowed in MongoDB. It will be ignored.")

    if (!EcoreUtil2.getAllContentsOfType(schema, ComposedReference).empty)
      warningLog.add("ComposedReference - A ComposedReference is not allowed in MongoDB. It will be ignored.")

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
    feat.type = checkDataType(feat.type)
  }

  private def dispatch checkDataType(SimpleReferenceTarget ref)
  {
    if (ref.multiplicity === null)
    {
      warningLog.add("SimpleFeature - In MongoDB a reference must have a cardinality. Setting it to 0..1: " + serializer.serialize(ref.eContainer) + "\n")
      ref.multiplicity = "+"
    }

    return ref
  }

  private def dispatch checkDataType(SimpleAggregationTarget aggr)
  {
    if (aggr.multiplicity === null)
    {
      warningLog.add("SimpleFeature - In MongoDB an aggregate must have a cardinality. Setting it to 0..1: " + serializer.serialize(aggr.eContainer) + "\n")
      aggr.multiplicity = "+"
    }

    return aggr
  }

  private def dispatch checkDataType(DataType theType)
  {
    return theType
  }
}
