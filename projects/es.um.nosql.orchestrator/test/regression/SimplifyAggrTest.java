package regression;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.db.interfaces.EveryPolitician2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

/**
 * Validation test: The inference process should be able to simplify Aggr{V1, V2, V2,...V2} to Aggr{V1, V2}.
 * If this fails, the inferrer is aggregating variations which already are added to the list.
 * @fail: Several variations are stored as different when in fact they are equivalent.
 */
public class SimplifyAggrTest
{
  private String inputRoute = "testSources/SimplifyAggr.json";
  private String dbName = "DEBUG_SimplifyAggr";
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

    Assert.assertEquals(2, nosqlschema.getEntities().size());
    Assert.assertEquals(2, nosqlschema.getEntities().get(0).getVariations().size());
    Assert.assertEquals(2, nosqlschema.getEntities().get(1).getVariations().size());
  }
}