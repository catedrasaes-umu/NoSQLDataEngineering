package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.m2t.Athena2MySQL
import es.um.unosql.xtext.athena.utils.io.CodeWriter
import es.um.unosql.xtext.athena.utils.config.Athena2MySQLConfig
import es.um.unosql.xtext.athena.utils.io.AthenaIO

class Athena2MySQLMain
{
  def static void main(String[] args)
  {
    // val custom_args = newArrayList("-f", "models/normalization", "-o", "models/mysql", "-nr")

    val config = new Athena2MySQLConfig(args)

    runAthena2MySQL(config)
  }

  private static def runAthena2MySQL(Athena2MySQLConfig config)
  {
    val inputModel = config.inputModel
    val inputPath = config.inputPath
    val outputPath = config.outputPath
    val normalizeRelations = config.normalizeRelations
    val normalizeCollections = config.normalizeRelations

    val athenaIO = new AthenaIO()
    val transformer = new Athena2MySQL()
    val codeWriter = new CodeWriter()

    println("ATHENA> Generating MySQL code...")

    outputPath.toFile.mkdirs

    val iterateModels = inputModel === null ? inputPath.toFile.listFiles[f | f.name.endsWith(".athena")].toSet : #{ inputModel.toFile }

    for (model : iterateModels)
    {
      println("ATHENA>> Model: " + model.name)
      val result = transformer.m2t(athenaIO.load(model.toPath), normalizeRelations, normalizeCollections)
      codeWriter.write(result, outputPath.resolve(model.name.replace(".athena", ".sql")))
    }

    println("ATHENA> MySQL generation finished!")
  }
}
