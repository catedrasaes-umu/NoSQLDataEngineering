package es.um.nosql.orchestrator.test;

import java.io.IOException;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.db.couchdb.CouchDBImport;
import es.um.nosql.schemainference.nosqlimport.db.mongodb.MongoDBImport;

public class InferenceTest
{
	private static final String DATABASE_IP = "localhost";
	private static final String MODELS_FOLDER = "models/";
	private static final String COUCHDB_MAPREDUCE_FOLDER = "mapreduce/couchdb/v1";
	private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";

	private static final boolean COUCHDB = false;
	private static final boolean MONGODB = true;

	public static void main(String[] args) throws IOException
	{
//	  prepareModelExample(MONGODB);
//	  prepareSOFExample(MONGODB);  //TODO: Not tested yet
//	  prepareEPolExample(MONGODB);
//	  prepareUrbanExample(COUCHDB);
	  prepareCompanyExample(MONGODB);
	}

	public static void prepareModelExample(boolean toMongoDb)
	{
	  String dbName = "mongoMovies3";
    String MODEL_FILE = MODELS_FOLDER + dbName + ".xmi";
    String OUTPUT_MODEL = MODELS_FOLDER + dbName + "_RESULT.xmi";

    long startTime = System.currentTimeMillis();

    if (toMongoDb)
    {
      System.out.println("Filling the MongoDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_MODEL2DB(DbType.MONGODB, DATABASE_IP, dbName + ".xmi");
      mongoDbExtract(dbName, OUTPUT_MODEL);
    }
    else
    {
      System.out.println("Filling the CouchDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_MODEL2DB(DbType.MONGODB, DATABASE_IP, MODEL_FILE);
      couchDbExtract(MODEL_FILE, OUTPUT_MODEL);
    }
    System.out.println("BuildNoSQLSchema created: " + OUTPUT_MODEL + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareSOFExample(boolean toMongoDb)
	{
		long startTime = System.currentTimeMillis();

		String dbName = "stackoverflow";
		String sofModel = MODELS_FOLDER + dbName + "_SOF.xmi";

    if (toMongoDb)
    {
      System.out.println("Filling the MongoDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_SOF2DB(DbType.MONGODB, DATABASE_IP, "/media/alberto/tarsonis/datasets/stackoverflow/");
      mongoDbExtract(dbName, sofModel);
    }
    else
    {
      System.out.println("Filling the CouchDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_SOF2DB(DbType.COUCHDB, DATABASE_IP, "/media/alberto/tarsonis/datasets/stackoverflow/");
      couchDbExtract(dbName, sofModel);
    }

		System.out.println("BuildNoSQLSchema created: " + dbName + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareEPolExample(boolean toMongoDb)
	{
    String dbName = "everypolitician";
    String ePolModel = MODELS_FOLDER + dbName + ".xmi";

	  long startTime = System.currentTimeMillis();

	  if (toMongoDb)
	  {
      System.out.println("Filling the MongoDB database...");
	    es.um.nosql.schemainference.db.Main.PREPARE_EPOL2DB(DbType.MONGODB, DATABASE_IP, "/media/alberto/tarsonis/datasets/everypolitician/countries/");
	    mongoDbExtract(dbName, ePolModel);
	  }
	  else
	  {
      System.out.println("Filling the CouchDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_EPOL2DB(DbType.COUCHDB, DATABASE_IP, "/media/alberto/tarsonis/datasets/everypolitician/countries/");
      couchDbExtract(dbName, ePolModel);
	  }
		System.out.println("BuildNoSQLSchema created: " + ePolModel + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareUrbanExample(boolean toMongoDb)
	{
	  String dbName = "urban";
    String jsonModel = MODELS_FOLDER + dbName + ".xmi";

	  long startTime = System.currentTimeMillis();

    if (toMongoDb)
    {
      System.out.println("Filling the MongoDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_URBAN2DB(DbType.MONGODB, DATABASE_IP, "/media/alberto/tarsonis/datasets/urban/words.json");
      mongoDbExtract(dbName, jsonModel);      
    }
    else
    {
      System.out.println("Filling the CouchDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_URBAN2DB(DbType.COUCHDB, DATABASE_IP, "/media/alberto/tarsonis/datasets/urban/words.json");
      couchDbExtract(dbName, jsonModel);   
    }
		System.out.println("BuildNoSQLSchema created: " + dbName + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareCompanyExample(boolean toMongoDb)
	{
	  String dbName = "companies";
    String jsonModel = MODELS_FOLDER + dbName + ".xmi";

    long startTime = System.currentTimeMillis();

    if (toMongoDb)
    {
      System.out.println("Filling the MongoDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_COMP2DB(DbType.MONGODB, DATABASE_IP, "/media/alberto/tarsonis/datasets/companies/companies_min.json");
      mongoDbExtract(dbName, jsonModel);      
    }
    else
    {
      System.out.println("Filling the CouchDB database...");
      es.um.nosql.schemainference.db.Main.PREPARE_COMP2DB(DbType.COUCHDB, DATABASE_IP, "/media/alberto/tarsonis/datasets/companies/companies.json");
      couchDbExtract(dbName, jsonModel);   
    }
    System.out.println("BuildNoSQLSchema created: " + dbName + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	private static void mongoDbExtract(String dbName, String model)
	{
		System.out.println("Starting inference...");
		MongoDBImport inferrer = new MongoDBImport();
		JsonArray jArray = inferrer.mapRed2Array(DATABASE_IP, dbName, MONGODB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(dbName, jArray, model);
	}

	private static void couchDbExtract(String dbName, String model)
	{
    System.out.println("Starting inference...");
    CouchDBImport inferrer = new CouchDBImport();
    JsonArray jArray = inferrer.mapRed2Array(DATABASE_IP, dbName, COUCHDB_MAPREDUCE_FOLDER);
    System.out.println("Inference finished.");

    System.out.println("Starting BuildNoSQLSchema...");
    BuildNoSQLSchema builder = new BuildNoSQLSchema();
    builder.buildFromGsonArray(dbName, jArray, model);
	}
}
