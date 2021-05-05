package es.um.unosql.xtext.orion.validation.m2t

import java.util.ArrayList

import es.um.unosql.xtext.orion.orion.BasicOperation

import es.um.unosql.xtext.athena.athena.AthenaSchema

class MongoDBValidator
{
  val errorLog = new ArrayList
  val warningLog = new ArrayList
  
  new() {}

  def getSummary()
  {
    if (!warningLog.empty)
      System.err.println("Some warnings were detected on the Orion file:\n" + warningLog.join())

    if (!errorLog.empty)
      throw new IllegalArgumentException("Some errors were detected on the Orion file:\n" + errorLog.join())
  }

  def checkBasicOperation(AthenaSchema schema, BasicOperation op)
  {
    // This method is used for operations where a validation does not apply...
  }
}
