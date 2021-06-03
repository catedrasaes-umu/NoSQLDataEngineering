package es.um.unosql.subtypes.main;

import java.nio.file.Path;

import es.um.unosql.subtypes.timestamp.TimestampInferrer;
import es.um.unosql.subtypes.timestamp.templates.TimestampAnalyzer;
import es.um.unosql.subtypes.util.configuration.TimestampInferenceConfig;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;
import es.um.unosql.utils.UNoSQLSchemaWriter;

public class TimestampInference
{
  /**
   * This main function is used to infer/analyze a schema stored in a MongoDB taking into account the Timestamps.
   * Think of it as an "enhanced" inference process.
   * usage: <Timestamp inference>
     -h,--help                                        Prints help
     -ip                                              The ip
     -mr,--map_reduce <folder>                        The path to the map
                                                      reduce folder
     -n,--db_name <db_name>                           The database name
     -o,--output <folder>                             The path to an output
                                                      folder
     -ts,--timestamp <BASIC|DATE|DEFAULT|OID field>   The timestamp type
   */
  public static void main(String[] args)
  {
    /*
    String[] args_harvard       = { "-ip", "localhost", "-n", "harvard", "-ts", "date", "start_time_DI", "-o", "output" };
    String[] args_links         = { "-ip", "localhost", "-n", "links", "-ts", "basic", "timestamp", "-o", "output" };
    String[] args_webclick      = { "-ip", "localhost", "-n", "webclick", "-ts", "basic", "timestamp", "-o", "output" };
    String[] args_publications  = { "-ip", "localhost", "-n", "publications", "-ts", "default", "-o", "output" };
    String[] args_facebook      = { "-ip", "localhost", "-n", "facebook", "-ts", "date", "created_time", "-o", "output" };
    String[] args_sanctions     = { "-ip", "localhost", "-n", "opensanctions", "-ts", "date", "timestamp", "-o", "output" };
    String[] args_stackoverflow = { "-ip", "localhost", "-n", "stackoverflow", "-ts", "date", "CreationDate", "-o", "output" };
    String[] args_reddit        = { "-ip", "localhost", "-n", "reddit", "-ts", "basic", "created_utc", "-o", "output" };
     */

    TimestampInferenceConfig config = new TimestampInferenceConfig(args);

    runTimestampInference(config);
  }

  public static void runTimestampInference(TimestampInferenceConfig config)
  {
    String ip = config.getIP();
    String dbName = config.getDBName();
    TimestampAnalyzer analyzer = config.getTimestampAnalyzer();
    Path outputPath = config.getOutputPath();
    Path mrPath = config.getMapReducePath();

    uNoSQLSchema schema = new TimestampInferrer().infer(ip, dbName, analyzer, mrPath);

    outputPath.toFile().mkdirs();
    new UNoSQLSchemaWriter().write(schema, outputPath.resolve(schema.getName() + ".xmi").toFile());
  }
}
