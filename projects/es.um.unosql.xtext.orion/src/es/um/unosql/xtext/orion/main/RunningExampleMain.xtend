package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.m2t.Orion2Cassandra
import es.um.unosql.xtext.orion.m2t.Orion2MongoDB
import java.io.File
import es.um.unosql.xtext.athena.m2t.Athena2Cassandra
import es.um.unosql.xtext.athena.m2t.Athena2MongoDBSchemaValidator
import es.um.unosql.xtext.orion.utils.io.ModelLoader

class RunningExampleMain
{
  public static val RUNNING_EXAMPLE_FOLDER   = "../es.um.unosql.xtext.athena/models/running_example/"
  public static val ATHENA_MODEL             = "Gamification_Athena.athena"
  public static val ORION_MODEL              = "Gamification_ops.orion"
  public static val ORION_VALIDATION         = "Gamification_Orion.orion"
  public static val ORION_SOF                = "StackOverflow_ops.orion"

  def static void main(String[] args)
  {
    val orionLoader = new ModelLoader()

    println("ATHENA> Generating Cassandra/MongoDB code...")

    new File(RUNNING_EXAMPLE_FOLDER + "athena/").mkdirs

    println("ATHENA>> Model: " + ATHENA_MODEL)
    try
    {
      val athenaModel = new es.um.unosql.xtext.athena.utils.io.ModelLoader().load(RUNNING_EXAMPLE_FOLDER + ATHENA_MODEL)

      new Athena2Cassandra().m2t(athenaModel, new File(RUNNING_EXAMPLE_FOLDER + "athena/"))
      new Athena2MongoDBSchemaValidator().m2t(athenaModel, new File(RUNNING_EXAMPLE_FOLDER + "athena/"))
    } catch (IllegalArgumentException e)
    {
      System.err.println("ATHENA>> Generation error for: " + ATHENA_MODEL + " - " + e.message)
    }

    println("ATHENA> Cassandra/MongoDB generation finished!")
    println("ORION> Generating Cassandra/MongoDB code...")

    new File(RUNNING_EXAMPLE_FOLDER + "orion/").mkdirs

    try
    {
      new Orion2MongoDB().m2t(orionLoader.load(RUNNING_EXAMPLE_FOLDER + ORION_MODEL), new File(RUNNING_EXAMPLE_FOLDER + "orion/"))
      new Orion2Cassandra().m2t(orionLoader.load(RUNNING_EXAMPLE_FOLDER + ORION_VALIDATION), new File(RUNNING_EXAMPLE_FOLDER + "orion/"))
      new Orion2MongoDB().m2t(orionLoader.load(RUNNING_EXAMPLE_FOLDER + ORION_SOF), new File(RUNNING_EXAMPLE_FOLDER + "orion/"))
    } catch (IllegalArgumentException e)
    {
      e.printStackTrace
      System.err.println("ORION>> Generation error for: Orion model / validation - " + e.message)
    }

    println("ORION> Cassandra/MongoDB generation finished!")
  }
}