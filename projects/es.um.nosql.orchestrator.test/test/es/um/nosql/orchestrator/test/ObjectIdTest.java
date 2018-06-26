package es.um.nosql.orchestrator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.db.interfaces.EPol2Db;
import es.um.nosql.s13e.db.utils.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;
import es.um.nosql.s13e.util.emf.ModelLoader;

public class ObjectIdTest
{
  private static final String DATABASE_IP = "localhost";
  private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";
  private static String INPUT_FILE = "testSources/ERROR_InferenceObjectIds.json";
  private static String DBNAME = "DEBUG_InferenceTest";
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
//    controller.getClient().cleanDb(DBNAME);
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

    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(OUTPUT_MODEL), NoSQLSchema.class);

    assertNotNull("Schema can't be null", schema);
    assertNotNull("Schema should have entities", schema.getEntities());
    assertEquals("Schema should have one entity", 1, schema.getEntities().size());

    Entity memberships = null;

    for (Entity e : schema.getEntities())
    {
      assertFalse(e.getName().equals("_id"));
      if (e.getName().equals("memberships"))
        memberships = e;
    }

    memberships.getEntityversions().forEach(ev ->
    {
      assertTrue(ev.getProperties().stream().noneMatch(p -> p.getName().equals("_id") && p instanceof Association));
    });
    // TODO: The inference process fails when the _id identifier is an ObjectId: ObjectId("code")
    // It is inferred it as an Aggregate of an _id Entity with no attributes.
    // It should be inferred as a PrimitiveType with ObjectId type.
  }
}
