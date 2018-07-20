package regression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.db.interfaces.EveryPolitician2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

/**
 * Validation test: The inference process should be able to differentiate between strings and ObjectIds
 * in attributes as well as in the original types of references. If this fails, please check the inference
 * process and also the mapReduce files.
 * @fail: An ObjectId is inferred as a String or as an Aggregated entity.
 */
public class ObjectIdTest
{
  private String inputRoute = "testSources/ERROR_ObjectIds.json";
  private String dbName = "DEBUG_ObjectIds";
  private EveryPolitician2Db controller;

  @Before
  public void setUp() throws Exception
  {
    controller = new EveryPolitician2Db(DbType.MONGODB, "localhost");
  }

  @After
  public void tearDown() throws Exception
  {
    controller.getClient().cleanDb(dbName);
    controller.shutdown();
  }

  @Test
  public void test()
  {
    controller.run(inputRoute, dbName);

    MongoDBImport inferrer = new MongoDBImport();
    JsonArray jArray = inferrer.mapRed2Array("localhost", dbName, "mapreduce/mongodb/v1/");

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    NoSQLSchema nosqlschema = builder.buildFromGsonArray(dbName, jArray);

    assertNotNull("Schema can't be null", nosqlschema);
    assertNotNull("Schema should have entities", nosqlschema.getEntities());
    assertEquals("Schema should have one entity", 1, nosqlschema.getEntities().size());

    Property property = nosqlschema.getEntities().get(0).getEntityVariations().get(0).getProperties().stream().filter(p -> p.getName().equals("_id")).findFirst().get();

    assertTrue(property instanceof Attribute);
    assertEquals("ObjectId", ((PrimitiveType)((Attribute)property).getType()).getName());
  }
}
