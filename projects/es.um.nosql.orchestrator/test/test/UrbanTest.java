package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.db.interfaces.UrbanDictionary2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

public class UrbanTest
{
  private static final String DATABASE_IP = "localhost";
  private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";
  private static String INPUT_FILE = "testSources/ERROR_words.json";
  private static String DBNAME = "DEBUG_urban";
  private static String OUTPUT_MODEL = "testOutput/" + DBNAME + ".xmi";

  private UrbanDictionary2Db controller;

  @Before
  public void setUp() throws Exception
  {
    controller = new UrbanDictionary2Db(DbType.MONGODB, DATABASE_IP);
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
    MongoDBImport inferrer = new MongoDBImport(DATABASE_IP, DBNAME);
    JsonArray jArray = inferrer.mapRed2Array(MONGODB_MAPREDUCE_FOLDER);
    System.out.println("Inference finished.");

    System.out.println(jArray);
    System.out.println("Starting BuildNoSQLSchema...");
    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromGsonArray(DBNAME, jArray, OUTPUT_MODEL);

    System.out.println("BuildNoSQLSchema created: " + OUTPUT_MODEL);

    //TODO: Actually fail on exception...
    // It fails if an attribute has a NULL value...
    //TODO: Check model integrity
    // There should be an entity Id of some kind with a string attribute
    // Also some words..
  }
}
