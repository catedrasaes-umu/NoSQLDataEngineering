package es.um.unosql.xtext.athena.validation.m2m

import java.util.ArrayList
import es.um.unosql.xtext.athena.utils.io.ModelSerializer
import es.um.unosql.xtext.athena.athena.AthenaSchema
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.SQLStructure
import es.um.unosql.xtext.athena.athena.InnerStructureLiteral
import es.um.unosql.xtext.athena.athena.InnerStructureLiteralArray
import es.um.unosql.xtext.athena.athena.TopLevelStructureDefiningElementDeclRef
import es.um.unosql.xtext.athena.athena.VariationDeclRef
import es.um.unosql.xtext.athena.athena.StructureTerm

class NormalizerValidator
{
  val errorLog = new ArrayList
  val warningLog = new ArrayList
  val serializer = new ModelSerializer

  def validate(AthenaSchema schema)
  {
    val sqlStructs = EcoreUtil2.getAllContentsOfType(schema, SQLStructure)
    if (!sqlStructs.empty)
      errorLog.add("SQLStructure - There are still SQLStructures that could not be normalized: " + sqlStructs.map[e | serializer.serialize(e)].join(", "))

    val innerStructArrays = EcoreUtil2.getAllContentsOfType(schema, InnerStructureLiteralArray)
    if (!innerStructArrays.empty)
      errorLog.add("InnerStructureLiteralArray - There are still InnerStructureLiteralArrays that could not be normalized: " + innerStructArrays.map[e | serializer.serialize(e)].join(", "))

    val innerStructs = EcoreUtil2.getAllContentsOfType(schema, InnerStructureLiteral)
    if (!innerStructs.empty)
      errorLog.add("InnerStructureLiteral - There are still InnerStructureLiterals that could not be normalized: " + innerStructs.map[e | serializer.serialize(e)].join(", "))

    val topLevelRefs = EcoreUtil2.getAllContentsOfType(schema, TopLevelStructureDefiningElementDeclRef)
    if (!topLevelRefs.empty)
      errorLog.add("TopLevelStructureDefiningElementDeclRef - There are still TopLevelStructureDefiningElementDeclRefs that could not be normalized: " + topLevelRefs.map[e | serializer.serialize(e)].join(", "))

    val variationRefs = EcoreUtil2.getAllContentsOfType(schema, VariationDeclRef)
    if (!variationRefs.empty)
      errorLog.add("VariationDeclRef - There are still VariationDeclRefs that could not be normalized: " + variationRefs.map[e | serializer.serialize(e)].join(", "))

    val structTerms = EcoreUtil2.getAllContentsOfType(schema, StructureTerm)
    if (!structTerms.empty)
      errorLog.add("StructureTerm - There are still StructureTerms that could not be normalized: " + structTerms.map[e | serializer.serialize(e)].join(", "))

    if (!warningLog.empty)
      System.err.println("Some warnings were detected on the Athena file:\n" + warningLog.join())

    if (!errorLog.empty)
      throw new IllegalArgumentException("Some errors were detected on the Athena file:\n" + errorLog.join())
  }
}
