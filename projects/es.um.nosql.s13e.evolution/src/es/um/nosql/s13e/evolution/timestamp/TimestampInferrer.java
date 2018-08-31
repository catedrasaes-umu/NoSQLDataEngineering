package es.um.nosql.s13e.evolution.timestamp;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.evolution.timestamp.gen.TimestampAnalyzer;
import es.um.nosql.s13e.evolution.util.MapReduceTimestampSources;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

public class TimestampInferrer
{
  public NoSQLSchema infer(String dbName, TimestampAnalyzer analyzer)
  {
    long startTime = System.currentTimeMillis();

    if (ConfigConstants.DEBUG)
      System.out.println(dbName + " > Connecting to the database...");

    MongoDBImport inferrer = new MongoDBImport(ConfigConstants.DATABASE_IP, dbName);
    MapReduceTimestampSources mrtSources = new MapReduceTimestampSources(ConfigConstants.MONGODB_MAPREDUCE_FOLDER, analyzer);

    if (ConfigConstants.DEBUG)
      System.out.println(dbName + " > Starting inference...");

    JsonArray jArray = inferrer.mapRed2Array(mrtSources);

    if (ConfigConstants.DEBUG)
    {
      System.out.println(dbName + " > Inference finished.");
      System.out.println(dbName + " > Starting BuildNoSQLSchema...");      
    }

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema result = builder.buildFromGsonArray(dbName, jArray);

    if (ConfigConstants.DEBUG)
      System.out.println(dbName + " > BuildNoSQLSchema created in " + (System.currentTimeMillis() - startTime) + " ms");

    return result;
  }
}
