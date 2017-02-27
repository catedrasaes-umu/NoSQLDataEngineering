package es.um.nosql.orchestrator.test;

import java.io.IOException;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.dbgenerator.DbGenController;
import es.um.nosql.schemainference.dbgenerator.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.couchdb.CouchDBSchemaInference;

public class Main
{
	private static final String COUCHDB_IP = "localhost";
	private static final String JSON_FOLDER = "json/";
	private static final String MODELS_FOLDER = "models/";
	private static final String TABLENAME = "Food";
	private static final String MODEL_FILE = MODELS_FOLDER + TABLENAME + ".nosqlschema";
	private static final String OUTPUT_MODEL = MODELS_FOLDER + TABLENAME + "_2.xmi";
	private static final String MAPREDUCE_FOLDER = "mapreduce/couchdb/v1";

	public static void main(String[] args) throws IOException
	{
		int minInstances = 1;
		int maxInstances = 3;

		DbGenController controller = new DbGenController(DbType.COUCHDB, COUCHDB_IP);
		controller.insertTableDb(MODEL_FILE, JSON_FOLDER, minInstances, maxInstances);

		System.out.println("Starting inference...");
		CouchDBSchemaInference inferrer = new CouchDBSchemaInference();
		JsonArray jArray = inferrer.mapRed2Array(COUCHDB_IP, TABLENAME, MAPREDUCE_FOLDER);
		System.out.println("Inference finished.");

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromGsonArray(TABLENAME + "_2.xmi", jArray, OUTPUT_MODEL);
		System.out.println("BuildNoSQLSchema created: " + OUTPUT_MODEL);
	}
}
