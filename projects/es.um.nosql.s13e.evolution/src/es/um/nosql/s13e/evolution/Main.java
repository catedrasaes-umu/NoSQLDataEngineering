package es.um.nosql.s13e.evolution;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.interfaces.Webclick2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.NoSQLSchemaPrettyPrinter;

public class Main
{
  public static void main(String[] args)
  {
    String inputRoute = "testSources/MapReduceTimestamp.json";
    String dbName = "DEBUG_MapReduceTimestamp";
    Webclick2Db controller = new Webclick2Db(DbType.MONGODB, "localhost");
    controller.run(inputRoute, dbName);

    MongoDBImport inferrer = new MongoDBImport("localhost", dbName);
    JsonArray jArray = inferrer.mapRed2Array("mapreduce/mongodb/v2/");

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema nosqlschema = builder.buildFromGsonArray(dbName, jArray);

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(nosqlschema));

    controller.getClient().cleanDb(dbName);
    controller.shutdown();

    /* Modificar el MapRedSources para:
      a) Cargar el mapReduce normal (MapRedSources)
      b) Analizar el timestamp escogido
      c) Cargarlo del fichero correspondiente
      d) Sustituir el fragmento del map por el del timestamp escogido
      e) Tests
    */
  }
}
