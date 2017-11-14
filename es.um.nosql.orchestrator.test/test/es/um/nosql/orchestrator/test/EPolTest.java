package es.um.nosql.orchestrator.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.db.interfaces.EPol2Db;
import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.db.mongodb.MongoDBImport;

public class EPolTest
{
  private static final String DATABASE_IP = "localhost";
  private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";
  private static String INPUT_FILE = "testSources/ERROR_Sweden.json";
  private static String DBNAME = "DEBUG_everypolitician";
  private static String OUTPUT_MODEL = "testOutput/" + DBNAME + ".xmi";

  private EPol2Db controller;

  @Before
  public void setUp() throws Exception
  {
    controller = new EPol2Db(DbType.MONGODB, DATABASE_IP);
  }

  @After
  public void tearDown() throws Exception
  {
    controller.getClient().cleanDb(DBNAME);
    controller.shutdown();
  }

  @Test
  public void test()
  {
    controller.run(INPUT_FILE, DBNAME);

    System.out.println("Starting inference...");
    MongoDBImport inferrer = new MongoDBImport();
    JsonArray jArray = inferrer.mapRed2Array(DATABASE_IP, DBNAME, MONGODB_MAPREDUCE_FOLDER);
    System.out.println("Inference finished.");

    System.out.println("Starting BuildNoSQLSchema...");
    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromGsonArray(DBNAME, jArray, OUTPUT_MODEL);

    System.out.println("BuildNoSQLSchema created: " + OUTPUT_MODEL);
    //TODO: Actually fail on exception...
    //TODO: Check model integrity
  }
}
