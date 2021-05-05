package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.utils.Constants
import java.io.File
import es.um.unosql.xtext.athena.m2t.Athena2MongoDBSchemaValidator
import es.um.unosql.xtext.athena.utils.io.ModelLoader

class Athena2MongoDBSchemaValidatorMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader()
    val transformer = new Athena2MongoDBSchemaValidator()

    println("ATHENA> Generating MongoDB code...")

    new File(Constants.ATHENA2MONGODB_FOLDER).mkdirs

    for (model : new File(Constants.UNOSQL2ATHENA_FOLDER).listFiles.filter[f | f.name.endsWith(".athena")])
    {
      println("ATHENA>> Model: " + model.name)
      try
      {
        transformer.m2t(loader.load(model), new File(Constants.ATHENA2MONGODB_FOLDER))
      } catch (IllegalArgumentException e)
      {
        System.err.println("ATHENA>> Generation error for: " + model.name + " - " + e.message)
      }
    }

    println("ATHENA> MongoDB generation finished!")
  }
}
