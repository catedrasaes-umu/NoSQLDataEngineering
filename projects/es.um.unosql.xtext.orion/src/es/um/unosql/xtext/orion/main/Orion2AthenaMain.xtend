package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.m2m.Orion2Athena
import es.um.unosql.xtext.orion.utils.Constants
import java.io.File
import es.um.unosql.xtext.orion.utils.io.ModelLoader
import es.um.unosql.xtext.athena.utils.io.ModelWriter

class Orion2AthenaMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader()
    val transformer = new Orion2Athena()
    val writer = new ModelWriter()

    println("ORION> Generating Athena schemas...")

    new File(Constants.MONGODB_OUTPUT_FOLDER).mkdirs

    for (model : new File(Constants.MONGODB_INPUT_FOLDER).listFiles.filter[f | f.name.endsWith(".orion")])
    {
      println("ORION>> Model: " + model.name)
      val schema = transformer.m2m(loader.load(model))
      writer.write(schema, Constants.MONGODB_OUTPUT_FOLDER + schema.name + ".athena")
    }

    println("ORION> Athena generation finished!")
  }
}
