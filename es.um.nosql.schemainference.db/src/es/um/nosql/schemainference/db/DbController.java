package es.um.nosql.schemainference.db;


import org.apache.commons.lang3.tuple.Pair;

import es.um.nosql.schemainference.db.adapters.DbClient;
import es.um.nosql.schemainference.db.adapters.couchdb.CouchDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.interfaces.Model2Db;
import es.um.nosql.schemainference.db.utils.DbType;

public class DbController
{
	private DbClient client;

	public DbController(DbType db, String ip)
	{
		switch (db)
		{
			case COUCHDB: { client = CouchDbAdapter.getCouchDbClient(ip); break;}
			case MONGODB: { client = MongoDbAdapter.getMongoDbClient(ip); break;}
			default: throw new IllegalArgumentException("Database type not implemented yet.");
		}
	}

	public boolean shutdown()
	{
		return client.shutdown();
	}

	public void model2Couch(String modelRoute, String jsonFolder, int minInstances, int maxInstances)
	{
		long startTime = System.currentTimeMillis();

		System.out.println("Reading input model " + modelRoute + "...");
		Model2Db mInterface = new Model2Db();
		Pair<String, String> jsonPair = mInterface.getJSONContent(modelRoute, jsonFolder, minInstances, maxInstances);

		client.cleanDb(jsonPair.getLeft());
		client.insert(jsonPair.getLeft(), jsonPair.getRight());

		System.out.println(jsonPair.getLeft() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
	}
	
	public void model2Mongo(String modelRoute, String jsonFolder, int minInstances, int maxInstances)
	{
		long startTime = System.currentTimeMillis();

		System.out.println("Reading input model " + modelRoute + "...");
		Model2Db mInterface = new Model2Db();
		Pair<String, String> jsonPair = mInterface.getJSONContent(modelRoute, jsonFolder, minInstances, maxInstances);

		client.cleanDb(jsonPair.getLeft());
		client.insert(jsonPair.getLeft(), jsonPair.getRight());

		System.out.println(jsonPair.getLeft() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
	}
/*
	public void insertFromXML(String xmlRoute)
	{
		long startTime = System.currentTimeMillis();

		System.out.println("Reading xml file " + xmlRoute + "...");
		Pair<String, String> jsonPair = xmlInterface.getJSONContent(xmlRoute);

		client.cleanDb(jsonPair.getLeft());
		client.insert(jsonPair.getLeft(), jsonPair.getRight());

		System.out.println(jsonPair.getLeft() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
	}
*/
}
