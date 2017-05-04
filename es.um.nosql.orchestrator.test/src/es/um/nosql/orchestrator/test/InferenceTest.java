package es.um.nosql.orchestrator.test;

import java.io.IOException;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.db.DbController;
import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.db.couchdb.CouchDBImport;
import es.um.nosql.schemainference.nosqlimport.db.mongodb.MongoDBImport;

public class InferenceTest
{
	private static final String COUCHDB_IP = "localhost";
	private static final String MONGODB_IP = "localhost";
	private static final String MODELS_FOLDER = "models/";
	private static final String TABLENAME = "mongoMovies3";
	private static final String MODEL_FILE = MODELS_FOLDER + TABLENAME + ".xmi";
	private static final String COUCHDB_OUTPUT_MODEL = MODELS_FOLDER + TABLENAME + "_COUCHDB.xmi";
	private static final String MONGODB_OUTPUT_MODEL = MODELS_FOLDER + TABLENAME + "_MONGODB.xmi";
	private static final String COUCHDB_MAPREDUCE_FOLDER = "mapreduce/couchdb/v1";
	private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";

	public static void prepareCouchDBExample()
	{
		long startTime = System.currentTimeMillis();
		int minInstances = 2;
		int maxInstances = 5;

		DbController controller = new DbController(DbType.COUCHDB, COUCHDB_IP);
		controller.model2Db(MODEL_FILE, minInstances, maxInstances);

		System.out.println("Starting inference...");
		CouchDBImport inferrer = new CouchDBImport();
		JsonArray jArray = inferrer.mapRed2Array(COUCHDB_IP, TABLENAME, COUCHDB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(TABLENAME, jArray, COUCHDB_OUTPUT_MODEL);
		System.out.println("BuildNoSQLSchema created: " + COUCHDB_OUTPUT_MODEL + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareMongoDBExample()
	{
		long startTime = System.currentTimeMillis();
		int minInstances = 2;
		int maxInstances = 5;

		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);
		controller.model2Db(MODEL_FILE, minInstances, maxInstances);

		mongoDbExtract(MODEL_FILE, MONGODB_OUTPUT_MODEL);
		System.out.println("BuildNoSQLSchema created: " + MONGODB_OUTPUT_MODEL + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareMongoDBSOFExample()
	{
		long startTime = System.currentTimeMillis();

		String stackOverflowTable = "stackoverflow";
		String stackOverflowModel = MODELS_FOLDER + stackOverflowTable + "_SOF.xmi";

		mongoDbExtract(stackOverflowTable, stackOverflowModel);
		System.out.println("BuildNoSQLSchema created: " + stackOverflowModel + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareMongoDBEPolExample()
	{
		long startTime = System.currentTimeMillis();

		String ePolTable = "everypolitician";
		String ePolModel = MODELS_FOLDER + ePolTable + ".xmi";

		mongoDbExtract(ePolTable, ePolModel);
		System.out.println("BuildNoSQLSchema created: " + ePolModel + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareErrorEPolExample()
	{
		long startTime = System.currentTimeMillis();

		String ERROR_FILE = "json/ERROR_Sweden.json";
		String DBNAME = "everypolitician";
		String ePolModel = MODELS_FOLDER + DBNAME + ".xmi";

		System.out.println("Use with caution");
		System.out.println("This is a test method used to analyze some kind of error on the inference process.");

		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);
		controller.ePol2Db(ERROR_FILE, DBNAME);

		mongoDbExtract(DBNAME, ePolModel);
		System.out.println("BuildNoSQLSchema created: " + ePolModel + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void prepareJsonMongoExample()
	{
		long startTime = System.currentTimeMillis();

		String DBNAME = "mongoMovies4";
		String jsonFile = "json/mongoMovies4.json";
		String jsonModel = MODELS_FOLDER + DBNAME + ".xmi";

		System.out.println("Inserting the JSON file...");
		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);
		controller.json2Db(jsonFile, DBNAME);

		mongoDbExtract(DBNAME, jsonModel);
		System.out.println("BuildNoSQLSchema created: " + DBNAME + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}
/*
	public static void prepareUrbanExample()
	{
		long startTime = System.currentTimeMillis();

		String DBNAME = "urban";
		String jsonFile = "/media/alberto/braxis/urban/words.json";
		String jsonModel = MODELS_FOLDER + DBNAME + ".xmi";

		System.out.println("Inserting the JSON file...");
		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);
		controller.json2Db(jsonFile, DBNAME);

		mongoDbExtract(DBNAME, jsonModel);
		System.out.println("BuildNoSQLSchema created: " + DBNAME + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}
*/
	private static void mongoDbExtract(String dbName, String model)
	{
		System.out.println("Starting inference...");
		MongoDBImport inferrer = new MongoDBImport();
		JsonArray jArray = inferrer.mapRed2Array(MONGODB_IP, dbName, MONGODB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(dbName, jArray, model);
	}

	public static void main(String[] args) throws IOException
	{
//		prepareCouchDBExample();
//		prepareMongoDBExample();
//		prepareMongoDBSOFExample();
//		prepareMongoDBEPolExample();
		prepareErrorEPolExample();
//		prepareJsonMongoExample();
//		prepareUrbanExample();
	}
}
