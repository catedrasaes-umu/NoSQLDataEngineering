package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.athena.utils.config.AthenaNormalizerConfig
import es.um.unosql.xtext.athena.utils.io.AthenaIO

class AthenaNormalizerMain
{
  def static void main(String[] args)
  {
    // val custom_args = newArrayList("-f", "models/normalization", "-o", "models/output/")

    val config = new AthenaNormalizerConfig(args)

    runAthenaNormalizer(config)
  }

  private static def runAthenaNormalizer(AthenaNormalizerConfig config)
  {
    val inputModel = config.inputModel
    val inputPath = config.inputPath
    val outputPath = config.outputPath

    val athenaIO = new AthenaIO()
    val normalizer = new AthenaNormalizer()

    println("ATHENA> Normalizing Athena schemas...")

    outputPath.toFile.mkdirs

    val iterateModels = inputModel === null ? inputPath.toFile.listFiles[f | f.name.endsWith(".athena")].toSet : #{ inputModel.toFile }

    for (model : iterateModels)
    {
      println("ATHENA>> Model: " + model.name)
      val schema = normalizer.m2m(athenaIO.load(model.toPath))
      athenaIO.write(schema, outputPath.resolve(schema.id.name + "_" + schema.id.version + ".athena"))
    }

    println("ATHENA> Athena generation finished!")
  }
}
