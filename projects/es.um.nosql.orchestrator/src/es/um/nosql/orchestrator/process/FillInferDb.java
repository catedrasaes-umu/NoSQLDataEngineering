package es.um.nosql.orchestrator.process;

import java.io.File;

import com.google.gson.JsonArray;

import es.um.nosql.orchestrator.util.constants.ConfigConstants;
import es.um.nosql.s13e.db.interfaces.Source2Db;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.couchdb.CouchDBImport;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

public class FillInferDb
{
  private DbType dbType;

  public FillInferDb(DbType dbType)
  {
    this.dbType = dbType;
  }

  public void fillDbFromFile(Source2Db controller, String dbName, String sourceFile)
  {
    long startTime = System.currentTimeMillis();

    if (ConfigConstants.DEBUG)
      System.out.println("Filling the " + dbType.toString() + " database...");

    controller.run(sourceFile, dbName);
    controller.shutdown();

    if (ConfigConstants.DEBUG)
      System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");
  }

  public void fillDbFromFolder(Source2Db controller, String dbName, String sourceFolder)
  {
    long startTime = System.currentTimeMillis();

    if (ConfigConstants.DEBUG)
      System.out.println("Filling the " + dbType.toString() + " database...");

    for (String fileName : new File(sourceFolder).list())
        controller.run(sourceFolder + fileName, dbName);

    controller.shutdown();

    if (ConfigConstants.DEBUG)
      System.out.println("Database " + dbName + " filled in " + (System.currentTimeMillis() - startTime) + " ms");
  }

  public void inferFromDb(String dbName, String outputModel)
  {
    long startTime = System.currentTimeMillis();

    if (ConfigConstants.DEBUG)
      System.out.println("Starting inference...");

    JsonArray jArray = null;

    switch (dbType)
    {
      case MONGODB:
      {
        MongoDBImport inferrer = new MongoDBImport();
        jArray = inferrer.mapRed2Array(ConfigConstants.DATABASE_IP, dbName, ConfigConstants.MONGODB_MAPREDUCE_FOLDER);
        break;
      }
      case COUCHDB:
      {
        CouchDBImport inferrer = new CouchDBImport();
        jArray = inferrer.mapRed2Array(ConfigConstants.DATABASE_IP, dbName, ConfigConstants.COUCHDB_MAPREDUCE_FOLDER);
        break;
      }
    }

    if (ConfigConstants.DEBUG)
    {
      System.out.println("Inference finished.");
      System.out.println("Starting BuildNoSQLSchema...");      
    }

    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromGsonArray(dbName, jArray, outputModel);

    if (ConfigConstants.DEBUG)
      System.out.println("BuildNoSQLSchema created: " + outputModel + " in " + (System.currentTimeMillis() - startTime) + " ms");
  }
}
