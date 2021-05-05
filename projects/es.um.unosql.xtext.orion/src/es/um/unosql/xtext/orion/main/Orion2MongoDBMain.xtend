package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.utils.Constants
import java.io.File
import es.um.unosql.xtext.orion.m2t.Orion2MongoDB
import es.um.unosql.xtext.orion.utils.io.ModelLoader

class Orion2MongoDBMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader()
    val transformer = new Orion2MongoDB()

    println("ORION> Generating MongoDB code...")

    new File(Constants.MONGODB_OUTPUT_FOLDER).mkdirs

    for (model : new File(Constants.MONGODB_INPUT_FOLDER).listFiles.filter[f | f.name.endsWith(".orion")])
    {
      println("ORION>> Model: " + model.name)
      try
      {
        transformer.m2t(loader.load(model), new File(Constants.MONGODB_OUTPUT_FOLDER))
      } catch (IllegalArgumentException e)
      {
        System.err.println("ORION>> Generation error for: " + model.name + " - " + e.message)
      }
    }

    println("ORION> MongoDB generation finished!")
  }
}
