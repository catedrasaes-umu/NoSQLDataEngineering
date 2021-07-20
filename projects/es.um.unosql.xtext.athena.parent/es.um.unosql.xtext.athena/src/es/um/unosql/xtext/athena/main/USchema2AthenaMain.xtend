package es.um.unosql.xtext.athena.main

import es.um.unosql.uNoSQLSchema.UNoSQLSchemaPackage
import es.um.unosql.uNoSQLSchema.uNoSQLSchema
import es.um.unosql.utils.ModelLoader
import es.um.unosql.xtext.athena.m2m.USchema2Athena
import es.um.unosql.xtext.athena.utils.config.USchema2AthenaConfig
import es.um.unosql.xtext.athena.utils.io.AthenaIO

class USchema2AthenaMain
{
  def static void main(String[] args)
  {
    // val custom_args = newArrayList("-f", "models/subtypes_output/", "-o", "models/subtypes/")

    val config = new USchema2AthenaConfig(args)

    runUSchema2Athena(config)
  }

  private static def runUSchema2Athena(USchema2AthenaConfig config)
  {
    val inputModel = config.inputModel
    val inputPath = config.inputPath
    val outputPath = config.outputPath

    val loader = new ModelLoader(UNoSQLSchemaPackage.eINSTANCE)
    val transformer = new USchema2Athena()
    val athenaIO = new AthenaIO()

    println("ATHENA> Generating Athena models from UNoSQL...")

    outputPath.toFile.mkdirs

    val iterateModels = inputModel === null ? inputPath.toFile.listFiles[f | f.name.endsWith(".xmi")].toSet : #{ inputModel.toFile }

    for (model : iterateModels)
    {
      println("ATHENA>> Model: " + model.name)
      val schema = transformer.m2m(loader.load(model, uNoSQLSchema))
      athenaIO.write(schema, outputPath.resolve(schema.id.name + "_" + schema.id.version + ".athena"))
    }

    println("ATHENA> Athena generation finished!")
  }
}
