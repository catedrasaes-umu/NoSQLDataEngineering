package es.um.unosql.xtext.orion.m2m

import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.orion.utils.OrionFactory
import es.um.unosql.xtext.orion.utils.io.ModelSerializer

class Athena2Orion
{
  val factory = new OrionFactory()

  def OrionOperations m2m(AthenaSchema schema)
  {
    val simplifiedSchema = new AthenaNormalizer().m2m(schema)
    val operations = factory.createOrionOperations(simplifiedSchema.name)

    for (entity : schema.entities.filter[e | e.isRoot])
      operations.operations.add(factory.createEntityAddOp(entity.name))

    println(new ModelSerializer().serialize(operations))

    return operations
  }
  //TODO: Not implemented yet
}
