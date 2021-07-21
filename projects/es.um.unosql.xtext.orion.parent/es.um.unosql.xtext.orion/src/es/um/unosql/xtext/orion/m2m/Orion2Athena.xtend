package es.um.unosql.xtext.orion.m2m

import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.athena.utils.AthenaFactory
import es.um.unosql.xtext.athena.athena.AthenaSchema
import java.util.List
import java.util.ArrayList
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.orion.utils.updater.AthenaSchemaUpdater

class Orion2Athena
{
  AthenaSchemaUpdater schemaUpdater
  List<AthenaSchema> schemas

  new()
  {
    this.schemaUpdater = null
    this.schemas = new ArrayList<AthenaSchema>()
  }

  def List<AthenaSchema> m2m(OrionOperations orion)
  {
    return m2m(orion, orion.imports !== null)
  }

  def List<AthenaSchema> m2m(OrionOperations orion, boolean enableValidation)
  {
    // If Orion imports a schema, we use it.
    val schema = orion.imports !== null ?
      new AthenaNormalizer().m2m(orion.imports.importedNamespace)
      :
    // If not, we create a new brand schema but with VersionId = 0
      new AthenaFactory().createAthenaSchema(orion.name, 0)

    // If the schema was created from scratch, disable validation
    // Else, if a schema was provided, activate validation
    schemaUpdater = new AthenaSchemaUpdater(schema, enableValidation)

    // Sequence of operations
    if (!orion.operations.empty)
    {
      for (op : orion.operations)
        schemaUpdater.processOperation(op)

      // Now we increment the schema version: 0 to 1 if no schema was provided
      schema.id.version = schema.id.version + 1
      schemas.add(schemaUpdater.schema)
    }
    // Sequence of evolution blocks
    else
    {
      for (eBlock : orion.evolBlocks)
      {
        for (op : eBlock.operations)
          schemaUpdater.processOperation(op)

        // Now we increment the schema version: 0 to 1 if no schema was provided
        schema.id.version = schema.id.version + 1
        schemas.add(EcoreUtil2.copy(schemaUpdater.schema))
      }
    }

    return this.schemas
  }
}
