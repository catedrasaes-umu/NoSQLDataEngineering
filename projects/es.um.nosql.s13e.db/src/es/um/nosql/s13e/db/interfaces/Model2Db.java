package es.um.nosql.s13e.db.interfaces;

import java.io.File;
import java.nio.file.Paths;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.db.util.generator.JsonGenerator;
import es.um.nosql.s13e.util.ModelLoader;

public class Model2Db extends Source2Db
{
  public Model2Db(DbType db, String ip)
  {
    super(db, ip);
  }

  public void run(String modelRoute, String dbName)
  {
    int minInstances = 1000;
    int maxInstances = 1000;

    this.run(modelRoute, dbName, minInstances, maxInstances);
  }

  public void run(String modelRoute, String dbName, int minInstances, int maxInstances)
  {
    long startTime = System.currentTimeMillis();

    System.out.println("Reading input model " + modelRoute + "...");
    storeJSONContent(modelRoute, dbName, minInstances, maxInstances);
    System.out.println(Paths.get(modelRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
  }

  private void storeJSONContent(String modelRoute, String dbName, int minInstances, int maxInstances)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    JsonGenerator generator = new JsonGenerator();

    NoSQLSchema schema = loader.load(new File(modelRoute), NoSQLSchema.class);
    String jsonContent = "";

    try
    {
      jsonContent = generator.generate(schema, minInstances, maxInstances);
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    getClient().cleanDb(schema.getName().toLowerCase());
    getClient().insert(dbName, jsonContent);
  }
}
