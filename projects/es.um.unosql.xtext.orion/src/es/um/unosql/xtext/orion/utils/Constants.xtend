package es.um.unosql.xtext.orion.utils

import java.util.stream.Stream
import java.util.AbstractMap.SimpleEntry
import java.util.stream.Collectors

class Constants
{
  public static val MAIN_FOLDER              = "../es.um.unosql.xtext.athena/models/orion/"
  public static val CASSANDRA_INPUT_FOLDER   = "../es.um.unosql.xtext.athena/models/orion/cassandra/"
  public static val CASSANDRA_OUTPUT_FOLDER  = "../es.um.unosql.xtext.athena/models/orion/output/cassandra/"
  public static val MONGODB_INPUT_FOLDER     = "../es.um.unosql.xtext.athena/models/orion/mongodb/"
  public static val MONGODB_OUTPUT_FOLDER    = "../es.um.unosql.xtext.athena/models/orion/output/mongodb/"
  public static val ATHENA_INPUT_FOLDER      = "../es.um.unosql.xtext.athena/models/unosql2athena/"
  public static val TEST_FOLDER              = "../es.um.unosql.xtext.athena/models/tests/"

  public static val TEST_INPUT_FOLDER        = "../es.um.unosql.xtext.athena/models/orion/test/"
  public static val TEST_OUTPUT_FOLDER       = "../es.um.unosql.xtext.athena/models/orion/output/test/"

  public static val MONGODB_CREATE_VALIDATOR = true
  public static val MONGODB_CREATE_DOCUMENTS = true

  //TODO: Until I learn how to load a model and its referenced models (eProxyURI problem), I am going to need this map.
  // https://www.eclipse.org/forums/index.php/m/1745239/?srch=ParseHelper+resourceset+createResource#msg_1745239
  // https://www.eclipse.org/forums/index.php/t/1098791/
  // https://www.eclipse.org/forums/index.php/t/200271/
  public static val ORION_REFERENCE_MAP = Stream.of(
    new SimpleEntry<String, String>("Gamification_ops", "../es.um.unosql.xtext.athena/models/running_example/Gamification_Athena.athena"),
    new SimpleEntry<String, String>("StackOverflow", "../es.um.unosql.xtext.athena/models/unosql2athena/stackoverflow.athena"),
    new SimpleEntry<String, String>("LoaderTest", "../es.um.unosql.xtext.athena/models/tests/LoaderTest.athena")
  ).collect(Collectors.toMap( [ e | e.key], [e | e.value]))
}
