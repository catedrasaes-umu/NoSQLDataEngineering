package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.m2t.Athena2MongoDBSchemaValidator
import es.um.unosql.xtext.athena.utils.io.CodeWriter
import es.um.unosql.xtext.athena.utils.config.Athena2MongoDBSchemaValidatorConfig
import es.um.unosql.xtext.athena.utils.io.AthenaIO

class Athena2MongoDBSchemaValidatorMain
{
  def static void main(String[] args)
  {
    // val custom_args = newArrayList("-f", "models/uschema2athena", "-o", "models/mongodb/")

    val config = new Athena2MongoDBSchemaValidatorConfig(args)

    runAthena2MongoDBSchemaValidator(config)
  }

  private static def runAthena2MongoDBSchemaValidator(Athena2MongoDBSchemaValidatorConfig config)
  {
    val inputModel = config.inputModel
    val inputPath = config.inputPath
    val outputPath = config.outputPath

    val athenaIO = new AthenaIO()
    val transformer = new Athena2MongoDBSchemaValidator()
    val codeWriter = new CodeWriter()

    println("ATHENA> Generating MongoDB code...")

    outputPath.toFile.mkdirs

    val iterateModels = inputModel === null ? inputPath.toFile.listFiles[f | f.name.endsWith(".athena")].toSet : #{ inputModel.toFile }

    for (model : iterateModels)
    {
      println("ATHENA>> Model: " + model.name)
      val result = transformer.m2t(athenaIO.load(model.toPath))
      codeWriter.write(result, outputPath.resolve(model.name.replace(".athena", ".js")))
    }

    println("ATHENA> MongoDB generation finished!")
  }
}
