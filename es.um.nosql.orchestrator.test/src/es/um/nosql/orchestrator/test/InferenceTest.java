package es.um.nosql.orchestrator.test;

import java.io.IOException;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.db.DbController;
import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.db.couchdb.CouchDBSchemaInference;
import es.um.nosql.schemainference.nosqlimport.db.mongodb.MongoDBSchemaInference;

public class InferenceTest
{
	private static final String COUCHDB_IP = "localhost";
	private static final String MONGODB_IP = "localhost";
	private static final String JSON_FOLDER = "json/";
	private static final String MODELS_FOLDER = "models/";
	private static final String TABLENAME = "mongoMovies3";
	private static final String MODEL_FILE = MODELS_FOLDER + TABLENAME + ".xmi";
	private static final String COUCHDB_OUTPUT_MODEL = MODELS_FOLDER + TABLENAME + "_COUCHDB.xmi";
	private static final String MONGODB_OUTPUT_MODEL = MODELS_FOLDER + TABLENAME + "_MONGODB.xmi";
	private static final String COUCHDB_MAPREDUCE_FOLDER = "mapreduce/couchdb/v1";
	private static final String MONGODB_MAPREDUCE_FOLDER = "mapreduce/mongodb/v1";

	private static void prepareCouchDBExample()
	{
		long startTime = System.currentTimeMillis();
		int minInstances = 5;
		int maxInstances = 10;

		DbController controller = new DbController(DbType.COUCHDB, COUCHDB_IP);
		controller.insertTableDb(MODEL_FILE, JSON_FOLDER, minInstances, maxInstances);

		System.out.println("Starting inference...");
		CouchDBSchemaInference inferrer = new CouchDBSchemaInference();
		JsonArray jArray = inferrer.mapRed2Array(COUCHDB_IP, TABLENAME, COUCHDB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(TABLENAME, jArray, COUCHDB_OUTPUT_MODEL);
		System.out.println("BuildNoSQLSchema created: " + COUCHDB_OUTPUT_MODEL + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	private static void prepareMongoDBExample()
	{
		long startTime = System.currentTimeMillis();
		int minInstances = 5;
		int maxInstances = 10;

		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);
		controller.insertTableDb(MODEL_FILE, JSON_FOLDER, minInstances, maxInstances);

		System.out.println("Starting inference...");
		MongoDBSchemaInference inferrer = new MongoDBSchemaInference();
		JsonArray jArray = inferrer.mapRed2Array(MONGODB_IP, TABLENAME, MONGODB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(TABLENAME, jArray, MONGODB_OUTPUT_MODEL);
		System.out.println("BuildNoSQLSchema created: " + MONGODB_OUTPUT_MODEL + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	private static void prepareMongoDBSOFExample()
	{
		long startTime = System.currentTimeMillis();

		String stackOverflowTable = "stackoverflow";
		String stackOverflowModel = MODELS_FOLDER + stackOverflowTable + "_SOF.xmi";

		System.out.println("Starting inference...");
		MongoDBSchemaInference inferrer = new MongoDBSchemaInference();
		JsonArray jArray = inferrer.mapRed2Array(MONGODB_IP, stackOverflowTable, MONGODB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(stackOverflowTable, jArray, stackOverflowModel);

		System.out.println("BuildNoSQLSchema created: " + stackOverflowModel + " in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static void main(String[] args) throws IOException
	{
//		prepareCouchDBExample();
//		prepareMongoDBExample();
		prepareMongoDBSOFExample();
	}
}
