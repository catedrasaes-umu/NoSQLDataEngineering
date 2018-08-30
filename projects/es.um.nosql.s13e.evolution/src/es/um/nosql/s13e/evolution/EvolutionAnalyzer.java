package es.um.nosql.s13e.evolution;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.evolution.timestamp.gen.BasicTimestampAnalyzer;
import es.um.nosql.s13e.evolution.util.MapReduceTimestampSources;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.NoSQLSchemaPrettyPrinter;

public class EvolutionAnalyzer
{

  public void runWebclickExample(String dbName, String mapReduceDir)
  {
    MongoDBImport inferrer = new MongoDBImport("localhost", dbName);
    MapReduceTimestampSources mrtSources = new MapReduceTimestampSources(mapReduceDir, new BasicTimestampAnalyzer("timestamp"));

    System.out.println(mrtSources.getMapJSCode());
    // Call TimestampInferrer
    // Generate conclusions
/*    JsonArray jArray = inferrer.mapRed2Array(mrtSources);

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema nosqlschema = builder.buildFromGsonArray(dbName, jArray);

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(nosqlschema));*/
  }
}
