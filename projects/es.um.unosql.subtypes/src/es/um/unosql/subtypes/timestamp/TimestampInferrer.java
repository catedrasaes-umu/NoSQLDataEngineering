package es.um.unosql.subtypes.timestamp;

import java.nio.file.Path;

import com.google.gson.JsonArray;

import es.um.unosql.doc2unosql.main.BuildUNoSQLSchema;
import es.um.unosql.doc2unosql.main.DefaultBuildUNoSQLSchema;
import es.um.unosql.documents.extractors.db.mongodb.MongoDBImport;
import es.um.unosql.subtypes.timestamp.templates.TimestampAnalyzer;
import es.um.unosql.subtypes.util.MapReduceTimestampSources;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;

public class TimestampInferrer
{
  public uNoSQLSchema infer(String ip, String dbName, TimestampAnalyzer analyzer, Path mapReduceFolder)
  {
    long startTime = System.currentTimeMillis();

    System.out.println(dbName + " > Connecting to the database...");

    MongoDBImport inferrer = new MongoDBImport(ip, dbName);
    MapReduceTimestampSources mrtSources = new MapReduceTimestampSources(mapReduceFolder.toString(), analyzer);

    System.out.println(dbName + " > Starting inference...");

    JsonArray jArray = inferrer.mapRed2Array(mrtSources);

    System.out.println(dbName + " > Inference finished.");
    System.out.println(dbName + " > Starting BuildNoSQLSchema...");

    BuildUNoSQLSchema builder = DefaultBuildUNoSQLSchema.getInjectedInstance();

    builder.buildFromGsonArray(dbName, jArray);
    uNoSQLSchema result = builder.getUNoSQLSchema();

    System.out.println(dbName + " > BuildNoSQLSchema created in " + (System.currentTimeMillis() - startTime) + " ms");

    return result;
  }
}
