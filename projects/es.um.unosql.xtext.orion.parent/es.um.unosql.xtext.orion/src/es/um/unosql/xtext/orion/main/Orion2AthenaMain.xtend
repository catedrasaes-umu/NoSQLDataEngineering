package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.m2m.Orion2Athena
import es.um.unosql.xtext.orion.utils.config.Orion2AthenaConfig
import es.um.unosql.xtext.athena.utils.io.AthenaIO
import es.um.unosql.xtext.orion.utils.io.OrionIO

class Orion2AthenaMain
{
  def static void main(String[] args)
  {
    // val customArgs = newArrayList("-i", "../../es.um.unosql.xtext.athena.parent/es.um.unosql.xtext.athena/models/orion/mongosongs_phased.orion", "-o", "models/")

    val config = new Orion2AthenaConfig(args)

    runOrion2Athena(config)
  }

  private static def void runOrion2Athena(Orion2AthenaConfig config)
  {
    val inputModel = config.inputModel
    val inputPath = config.inputPath
    val outputPath = config.outputPath

    val orionIO = new OrionIO()
    val transformer = new Orion2Athena()
    val athenaIO = new AthenaIO()

    println("ORION> Generating Athena schemas...")

    outputPath.toFile.mkdirs

    val iterateModels = inputModel === null ? inputPath.toFile.listFiles[f | f.name.endsWith(".orion")].toSet : #{ inputModel.toFile }

    for (model : iterateModels)
    {
      println("ORION>> Model: " + model.name)

      for (schema : transformer.m2m(orionIO.load(model.toPath)))
        athenaIO.write(schema, outputPath.resolve(schema.id.name + "_" + schema.id.version + ".athena"))
    }

    println("ORION> Athena generation finished!")
  }
}
