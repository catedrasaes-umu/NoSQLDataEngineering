package es.um.unosql.xtext.orion.m2m

import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.orion.utils.AthenaSchemaUpdater
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.AthenaSchema

class Orion2Athena
{
  val schemaUpdater = new AthenaSchemaUpdater()

  def AthenaSchema m2m(OrionOperations orion)
  {
    if (orion.imports !== null)
      schemaUpdater.schema = orion.imports.importedNamespace
    else
      schemaUpdater.schema = new AthenaFactory().createAthenaSchema(orion.name)

    for (op : orion.operations)
      schemaUpdater.processOperation(op)

    for (eBlock : orion.evolBlocks)
      for (op : eBlock.operations)
        schemaUpdater.processOperation(op)

    return schemaUpdater.schema
  }
}
