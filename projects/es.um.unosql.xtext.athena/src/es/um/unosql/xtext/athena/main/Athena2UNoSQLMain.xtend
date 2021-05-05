package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.utils.Constants
import java.io.File
import es.um.unosql.xtext.athena.m2m.Athena2UNoSQL
import es.um.unosql.utils.UNoSQLSchemaWriter
import es.um.unosql.xtext.athena.utils.io.ModelLoader

class Athena2UNoSQLMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader()
    val transformer = new Athena2UNoSQL()
    val writer = new UNoSQLSchemaWriter()

    println("ATHENA> Generating UNoSQL models from Athena...")

    new File(Constants.ATHENA2UNOSQL_FOLDER).mkdirs

    for (model : new File(Constants.UNOSQL2ATHENA_FOLDER).listFiles.filter[f | f.name.endsWith(".athena")])
    {
      println("ATHENA>> Model: " + model.name)
      val schema = transformer.m2m(loader.load(model))
      writer.write(schema, new File(Constants.ATHENA2UNOSQL_FOLDER + schema.name + ".xmi"))
    }

    println("ATHENA> UNoSQL generation finished!")
  }
}
