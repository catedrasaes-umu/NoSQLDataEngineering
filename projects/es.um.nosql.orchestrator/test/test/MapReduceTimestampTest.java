package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.interfaces.Webclick2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.NoSQLSchemaPrettyPrinter;

public class MapReduceTimestampTest
{
  private String inputRoute = "testSources/MapReduceTimestamp.json";
  private String dbName = "DEBUG_MapReduceTimestamp";
  private Webclick2Db controller;

  @Before
  public void setUp() throws Exception
  {
    controller = new Webclick2Db(DbType.MONGODB, "localhost");
  }

  @After
  public void tearDown() throws Exception
  {
//    controller.getClient().cleanDb(dbName);
    controller.shutdown();
  }

  @Test
  public void test()
  {
//    controller.run(inputRoute, dbName);

    MongoDBImport inferrer = new MongoDBImport();
    JsonArray jArray = inferrer.mapRed2Array("localhost", dbName, "mapreduce/mongodb/v2/");

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema nosqlschema = builder.buildFromGsonArray(dbName, jArray);

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(nosqlschema));
  }
}
