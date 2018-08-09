package es.um.nosql.s13e;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.evolution.util.MapReduceTimestampSources;
import es.um.nosql.s13e.evolution.util.TSAnalyzerType;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.NoSQLSchemaPrettyPrinter;

public class Main
{
  public static void main(String[] args)
  {
    String dbName = "DEBUG_MapReduceTimestamp";
    String mapReduceDir = "../es.um.nosql.orchestrator/mapreduce/mongodb/v2/";
    TSAnalyzerType tsType = TSAnalyzerType.NONE;

    MongoDBImport inferrer = new MongoDBImport("localhost", dbName);

    MapReduceTimestampSources mrtSources = new MapReduceTimestampSources(mapReduceDir, ConfigConstants.GET_TIMESTAMP_FILE(tsType));

    JsonArray jArray = inferrer.mapRed2Array(mrtSources);

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema nosqlschema = builder.buildFromGsonArray(dbName, jArray);

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(nosqlschema));

    /* Pendiente: Hacer el timestamp como una plantilla y que el usuario pueda meter un format, una condición, un atributo como timestamp...
     * Quizá es buena idea hacer un POJO-like...
     */
  }
}
