package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.m2t.Orion2Cassandra
import es.um.unosql.xtext.orion.utils.config.Orion2CassandraConfig
import es.um.unosql.xtext.athena.utils.io.CodeWriter
import es.um.unosql.xtext.athena.utils.io.AthenaIO
import es.um.unosql.xtext.orion.utils.io.OrionIO

class Orion2CassandraMain
{
  def static void main(String[] args)
  {
    // val customArgs = newArrayList("-i", "../../es.um.unosql.xtext.athena.parent/es.um.unosql.xtext.athena/models/orion/mongosongs_phased.orion", "-o", "models/")

    val config = new Orion2CassandraConfig(args)

    runOrion2Cassandra(config)
  }

  private static def void runOrion2Cassandra(Orion2CassandraConfig config)
  {
    val inputModel = config.inputModel
    val inputPath = config.inputPath
    val outputPath = config.outputPath

    val orionIO = new OrionIO()
    val transformer = new Orion2Cassandra()
    val athenaIO = new AthenaIO()
    val codeWriter = new CodeWriter()

    println("ORION> Generating Cassandra code...")

    outputPath.toFile.mkdirs

    val iterateModels = inputModel === null ? inputPath.toFile.listFiles[f | f.name.endsWith(".orion")].toSet : #{ inputModel.toFile }

    for (model : iterateModels)
    {
      println("ORION>> Model: " + model.name)
      try
      {
        // These two lists do have the same number of elements
        val scripts = transformer.m2t(orionIO.load(model.toPath))
        val schemas = transformer.schemas


        for (index : 0..schemas.length - 1)
        {
          athenaIO.write(schemas.get(index), outputPath.resolve(schemas.get(index).id.name + "_" + schemas.get(index).id.version + ".athena"))
          codeWriter.write(scripts.get(index), outputPath.resolve(schemas.get(index).id.name + "_" + schemas.get(index).id.version + ".cql"))
        }
      } catch (IllegalArgumentException e)
      {
        System.err.println("ORION>> Generation error for: " + model.name + " - " + e.message)
      }
    }

    println("ORION> Cassandra generation finished!")
  }
}
