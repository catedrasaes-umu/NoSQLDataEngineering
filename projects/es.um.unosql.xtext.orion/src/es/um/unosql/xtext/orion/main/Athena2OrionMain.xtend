package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.m2m.Athena2Orion
import java.io.File
import es.um.unosql.xtext.orion.utils.Constants
import es.um.unosql.xtext.orion.utils.io.ModelWriter
import es.um.unosql.xtext.athena.utils.io.ModelLoader

class Athena2OrionMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader()
    val transformer = new Athena2Orion()
    val writer = new ModelWriter()

    println("ORION> Generating Orion operations...")

    new File(Constants.MAIN_FOLDER).mkdirs

    for (model : new File(Constants.ATHENA_INPUT_FOLDER).listFiles.filter[f | f.name.endsWith(".athena")])
    {
      println("ORION>> Model: " + model.name)
      val operations = transformer.m2m(loader.load(model))
      writer.write(operations, Constants.MAIN_FOLDER + operations.name + ".orion")
    }

    println("ORION> Orion generation finished!")
  }
}
