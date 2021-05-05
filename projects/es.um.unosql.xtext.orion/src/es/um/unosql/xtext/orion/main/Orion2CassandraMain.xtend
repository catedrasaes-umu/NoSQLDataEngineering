package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.m2t.Orion2Cassandra
import es.um.unosql.xtext.orion.utils.Constants
import java.io.File
import es.um.unosql.xtext.orion.utils.io.ModelLoader

class Orion2CassandraMain
{
  def static void main(String[] args)
  {
    val loader = new ModelLoader()
    val transformer = new Orion2Cassandra()

    println("ORION> Generating Cassandra code...")

    new File(Constants.CASSANDRA_OUTPUT_FOLDER).mkdirs

    for (model : new File(Constants.CASSANDRA_INPUT_FOLDER).listFiles.filter[f | f.name.endsWith(".orion")])
    {
      println("ORION>> Model: " + model.name)
      try
      {
        transformer.m2t(loader.load(model), new File(Constants.CASSANDRA_OUTPUT_FOLDER))
      } catch (IllegalArgumentException e)
      {
        System.err.println("ORION>> Generation error for: " + model.name + " - " + e.message)
      }
    }

    println("ORION> Cassandra generation finished!")
  }
}
