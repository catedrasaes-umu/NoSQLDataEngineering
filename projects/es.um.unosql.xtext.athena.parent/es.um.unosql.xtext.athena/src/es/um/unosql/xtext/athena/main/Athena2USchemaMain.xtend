package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.m2m.Athena2USchema
import es.um.unosql.xtext.athena.utils.config.Athena2USchemaConfig
import es.um.unosql.utils.UNoSQLSchemaWriter
import es.um.unosql.xtext.athena.utils.io.AthenaIO

class Athena2USchemaMain
{
  def static void main(String[] args)
  {
    // val custom_args = newArrayList("-f", "models/uschema2athena/", "-o", "models/athena2uschema/")

    val config = new Athena2USchemaConfig(args)

    runAthena2USchema(config)
  }

  private static def runAthena2USchema(Athena2USchemaConfig config)
  {
    val inputModel = config.inputModel
    val inputPath = config.inputPath
    val outputPath = config.outputPath

    val athenaIO = new AthenaIO()
    val transformer = new Athena2USchema()
    val writer = new UNoSQLSchemaWriter()

    println("ATHENA> Generating UNoSQL models from Athena...")

    outputPath.toFile.mkdirs

    val iterateModels = inputModel === null ? inputPath.toFile.listFiles[f | f.name.endsWith(".athena")].toSet : #{ inputModel.toFile }

    for (model : iterateModels)
    {
      println("ATHENA>> Model: " + model.name)
      val schema = transformer.m2m(athenaIO.load(model.toPath))
      writer.write(schema, outputPath.resolve(model.name.replace(".athena", ".xmi")).toFile)
    }

    println("ATHENA> UNoSQL generation finished!")
  }
}
