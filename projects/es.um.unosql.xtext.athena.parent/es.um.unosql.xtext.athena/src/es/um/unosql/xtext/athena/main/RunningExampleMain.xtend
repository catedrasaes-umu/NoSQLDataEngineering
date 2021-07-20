package es.um.unosql.xtext.athena.main

import es.um.unosql.xtext.athena.m2t.Athena2Cassandra
import es.um.unosql.xtext.athena.m2t.Athena2MongoDBSchemaValidator
import es.um.unosql.xtext.athena.utils.io.CodeWriter
import java.nio.file.Path
import es.um.unosql.xtext.athena.utils.io.AthenaIO
import es.um.unosql.xtext.athena.m2t.Athena2MySQL

class RunningExampleMain
{
  public static val RUNNING_EXAMPLE_PATH = Path.of("models/running_examples/comonos2021/")
  public static val ATHENA_MODEL         = "Social_Network.athena"

  def static void main(String[] args)
  {
    val athenaIO = new AthenaIO()
    val codeWriter = new CodeWriter()

    println("ATHENA>> Model: " + ATHENA_MODEL)
    println("ATHENA> Generating MySQL/Cassandra/MongoDB code...")

    RUNNING_EXAMPLE_PATH.toFile.mkdirs

    try
    {
      val athenaModel = athenaIO.load(RUNNING_EXAMPLE_PATH.resolve(ATHENA_MODEL))

      codeWriter.write(
        new Athena2Cassandra().m2t(athenaModel),
        RUNNING_EXAMPLE_PATH.resolve(ATHENA_MODEL.replace(".athena", ".cql")))

      codeWriter.write(
        new Athena2MongoDBSchemaValidator().m2t(athenaModel),
        RUNNING_EXAMPLE_PATH.resolve(ATHENA_MODEL.replace(".athena", ".js")))

      codeWriter.write(
        new Athena2MySQL().m2t(athenaModel, true, true),
        RUNNING_EXAMPLE_PATH.resolve(ATHENA_MODEL.replace(".athena", ".sql")))

    } catch (IllegalArgumentException e)
    {e.printStackTrace
      System.err.println("ATHENA>> Generation error for: " + ATHENA_MODEL + " - " + e.message)
    }

    println("ATHENA> MySQL/Cassandra/MongoDB generation finished!")
  }
}