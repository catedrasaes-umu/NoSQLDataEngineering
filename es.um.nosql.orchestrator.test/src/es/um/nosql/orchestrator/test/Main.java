package es.um.nosql.orchestrator.test;

import java.io.IOException;

import es.um.nosql.schemainference.dbgenerator.DbGenController;
import es.um.nosql.schemainference.dbgenerator.utils.DbType;
import es.um.nosql.schemainference.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.schemainference.nosqlimport.couchdb.CouchDBSchemaInference;

public class Main
{
	private static final String COUCHDB_IP = "localhost";
	private static final String MODELS_FOLDER = "models/";
	private static final String JSON_FOLDER = "json/";
	private static final String TABLENAME = "mongomovies3";
	private static final String OUTPUT_JSON = JSON_FOLDER + TABLENAME + "_2.json";
	private static final String OUTPUT_MODEL = MODELS_FOLDER + TABLENAME + "_2.xmi";
	private final static String MAPREDUCE_FOLDER = "mapreduce/couchdb/v1";

	public static void main(String[] args) throws IOException
	{
		int minInstances = 5;
		int maxInstances = 10;

		DbGenController controller = new DbGenController(DbType.COUCHDB, COUCHDB_IP);
		controller.startTest(MODELS_FOLDER, JSON_FOLDER, minInstances, maxInstances);

		System.out.println("Starting inference...");
		CouchDBSchemaInference inferrer = new CouchDBSchemaInference(COUCHDB_IP, TABLENAME, MAPREDUCE_FOLDER);
		inferrer.inferAndWrite(OUTPUT_JSON);
		System.out.println("Inference finished: " + OUTPUT_JSON);

		System.out.println("Starting BuildNoSQLSchema...");
		BuildNoSQLSchema.main(new String[]{OUTPUT_JSON, OUTPUT_MODEL});
		System.out.println("BuildNoSQLSchema created: " + OUTPUT_MODEL);
	}
}
