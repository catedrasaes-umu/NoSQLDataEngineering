package es.um.unosql.xtext.orion.main

import es.um.unosql.xtext.orion.m2t.Orion2Cassandra
import es.um.unosql.xtext.orion.m2t.Orion2MongoDB
import es.um.unosql.xtext.athena.m2t.Athena2Cassandra
import es.um.unosql.xtext.athena.m2t.Athena2MongoDBSchemaValidator
import es.um.unosql.xtext.athena.utils.io.CodeWriter
import java.nio.file.Path
import es.um.unosql.xtext.athena.utils.io.AthenaIO
import es.um.unosql.xtext.orion.utils.io.OrionIO

class RunningExampleMain
{
  public static val RUNNING_EXAMPLE_PATH = Path.of("../../es.um.unosql.xtext.athena.parent/es.um.unosql.xtext.athena/models/running_example/")
  public static val ATHENA_MODEL             = "Gamification_Athena.athena"
  public static val ORION_MODEL              = "Gamification_ops.orion"
  public static val ORION_VALIDATION         = "Gamification_Orion.orion"
  public static val ORION_SOF                = "StackOverflow_ops.orion"

  def static void main(String[] args)
  {
    val orionIO = new OrionIO()
    val athenaIO = new AthenaIO()
    val codeWriter = new CodeWriter()

    println("ATHENA> Generating Cassandra/MongoDB code...")

    RUNNING_EXAMPLE_PATH.toFile.mkdirs
    RUNNING_EXAMPLE_PATH.resolve("athena").toFile.mkdirs


    println("ATHENA>> Model: " + ATHENA_MODEL)
    try
    {
      val athenaModel = athenaIO.load(RUNNING_EXAMPLE_PATH.resolve(ATHENA_MODEL))

      codeWriter.write(
        new Athena2Cassandra().m2t(athenaModel),
        RUNNING_EXAMPLE_PATH.resolve("athena").resolve(ATHENA_MODEL.replace(".athena", ".cql")))

      codeWriter.write(
        new Athena2MongoDBSchemaValidator().m2t(athenaModel),
        RUNNING_EXAMPLE_PATH.resolve("athena").resolve(ATHENA_MODEL.replace(".athena", ".js")))

    } catch (IllegalArgumentException e)
    {
      System.err.println("ATHENA>> Generation error for: " + ATHENA_MODEL + " - " + e.message)
    }

    println("ATHENA> Cassandra/MongoDB generation finished!")
    println("ORION> Generating Cassandra/MongoDB code...")

    RUNNING_EXAMPLE_PATH.resolve("orion").toFile.mkdirs

    try
    {
      val orionModel = orionIO.load(RUNNING_EXAMPLE_PATH.resolve(ORION_MODEL))
      val orionValidation = orionIO.load(RUNNING_EXAMPLE_PATH.resolve(ORION_VALIDATION))
      val orionSof = orionIO.load(RUNNING_EXAMPLE_PATH.resolve(ORION_SOF))

      codeWriter.write(
        new Orion2MongoDB(false).m2t(orionModel).head,
        RUNNING_EXAMPLE_PATH.resolve("orion").resolve(ORION_MODEL.replace(".orion", ".js"))
      )

      codeWriter.write(
        new Orion2Cassandra().m2t(orionValidation).head,
        RUNNING_EXAMPLE_PATH.resolve("orion").resolve(ORION_VALIDATION.replace(".orion", ".cql"))
      )

      codeWriter.write(
        new Orion2MongoDB(false).m2t(orionSof).head,
        RUNNING_EXAMPLE_PATH.resolve("orion").resolve(ORION_SOF.replace(".orion", ".js"))
      )

    } catch (IllegalArgumentException e)
    {
      e.printStackTrace
      System.err.println("ORION>> Generation error for: Orion model / validation - " + e.message)
    }

    println("ORION> Cassandra/MongoDB generation finished!")
  }
}