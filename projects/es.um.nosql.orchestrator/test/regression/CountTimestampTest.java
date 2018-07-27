package regression;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.interfaces.EveryPolitician2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

/**
 * Validation test: The inference process should be able to infer correctly the count and timestamp of root entities variations.
 * In non root entity variations, the count and timestamp is copied from the parents, for now.
 * @fail: Count and timestamp are not correctly calculated.
 */
public class CountTimestampTest
{
  private String inputRoute = "testSources/ERROR_CountTimestamp.json";
  private String dbName = "DEBUG_CountTimestamp";
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

    Assert.assertEquals(3, nosqlschema.getEntities().size());
    Entity entity = nosqlschema.getEntities().get(0);
    Assert.assertEquals(2, entity.getVariations().size());
    Assert.assertEquals(1, entity.getVariations().get(0).getVariationId());
    Assert.assertEquals(1, entity.getVariations().get(0).getCount());
    Assert.assertNotEquals(0, entity.getVariations().get(0).getTimestamp());
    Assert.assertEquals(2, entity.getVariations().get(1).getVariationId());
    Assert.assertEquals(1, entity.getVariations().get(1).getCount());
    Assert.assertNotEquals(0, entity.getVariations().get(1).getTimestamp());

    entity = nosqlschema.getEntities().get(1);
    Assert.assertEquals(1, entity.getVariations().size());
    Assert.assertEquals(1, entity.getVariations().get(0).getVariationId());
    Assert.assertEquals(2, entity.getVariations().get(0).getCount());
    Assert.assertNotEquals(0, entity.getVariations().get(0).getTimestamp());

    entity = nosqlschema.getEntities().get(2);
    Assert.assertEquals(2, entity.getVariations().size());
    Assert.assertEquals(1, entity.getVariations().get(0).getVariationId());
    Assert.assertEquals(8, entity.getVariations().get(0).getCount());
    Assert.assertNotEquals(0, entity.getVariations().get(0).getTimestamp());
    Assert.assertEquals(2, entity.getVariations().get(1).getVariationId());
    Assert.assertEquals(3, entity.getVariations().get(1).getCount());
    Assert.assertNotEquals(0, entity.getVariations().get(1).getTimestamp());
  }
}
