package es.um.nosql.orchestrator.test;

import java.io.IOException;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.dbgenerator.DbGenController;
import es.um.nosql.schemainference.dbgenerator.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.db.couchdb.CouchDBSchemaInference;
import es.um.nosql.schemainference.nosqlimport.db.mongodb.MongoDBSchemaInference;

public class Main
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
		int minInstances = 5;
		int maxInstances = 10;

		DbGenController controller = new DbGenController(DbType.COUCHDB, COUCHDB_IP);
		controller.insertTableDb(MODEL_FILE, JSON_FOLDER, minInstances, maxInstances);

		System.out.println("Starting inference...");
		CouchDBSchemaInference inferrer = new CouchDBSchemaInference();
		JsonArray jArray = inferrer.mapRed2Array(COUCHDB_IP, TABLENAME, COUCHDB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(TABLENAME + "_2.xmi", jArray, COUCHDB_OUTPUT_MODEL);
		System.out.println("BuildNoSQLSchema created: " + COUCHDB_OUTPUT_MODEL);
	}

	private static void prepareMongoDBExample()
	{
		int minInstances = 5;
		int maxInstances = 10;

		DbGenController controller = new DbGenController(DbType.MONGODB, MONGODB_IP);
		controller.insertTableDb(MODEL_FILE, JSON_FOLDER, minInstances, maxInstances);

		System.out.println("Starting inference...");
		MongoDBSchemaInference inferrer = new MongoDBSchemaInference();
		JsonArray jArray = inferrer.mapRed2Array(MONGODB_IP, TABLENAME, MONGODB_MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(TABLENAME + "_2.xmi", jArray, MONGODB_OUTPUT_MODEL);
		System.out.println("BuildNoSQLSchema created: " + MONGODB_OUTPUT_MODEL);
	}

	public static void main(String[] args) throws IOException
	{
		prepareCouchDBExample();
		prepareMongoDBExample();
	}
}
