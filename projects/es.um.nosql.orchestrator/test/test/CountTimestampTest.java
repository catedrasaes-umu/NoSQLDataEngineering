package test;

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

    Assert.assertEquals(1, nosqlschema.getEntities().size());
    Entity entity = nosqlschema.getEntities().get(0);
    Assert.assertEquals(2, entity.getEntityVariations().size());
    Assert.assertEquals(1, entity.getEntityVariations().get(0).getVariationId());
    Assert.assertEquals(8, entity.getEntityVariations().get(0).getCount());
    Assert.assertNotEquals(0, entity.getEntityVariations().get(0).getTimestamp());
    Assert.assertEquals(3, entity.getEntityVariations().get(1).getCount());
    Assert.assertNotEquals(0, entity.getEntityVariations().get(1).getTimestamp());
  }
}
