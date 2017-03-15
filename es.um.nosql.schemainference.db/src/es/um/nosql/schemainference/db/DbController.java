package es.um.nosql.schemainference.db;


import java.nio.file.Paths;

import es.um.nosql.schemainference.db.adapters.DbClient;
import es.um.nosql.schemainference.db.adapters.couchdb.CouchDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.interfaces.Model2Db;
import es.um.nosql.schemainference.db.interfaces.XML2Db;
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

	public void model2Db(String modelRoute, String jsonFolder, int minInstances, int maxInstances)
	{
		long startTime = System.currentTimeMillis();

		System.out.println("Reading input model " + modelRoute + "...");
		Model2Db loader = new Model2Db(client);
		
		loader.storeJSONContent(modelRoute, jsonFolder, minInstances, maxInstances);

		System.out.println(Paths.get(modelRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public void xml2Db(String xmlRoute, String dbName)
	{
		long startTime = System.currentTimeMillis();

		System.out.println("Reading xml file " + xmlRoute + "...");
		XML2Db loader = new XML2Db(client);

		loader.storeXMLContent(xmlRoute, dbName);

		System.out.println(dbName + ":" + Paths.get(xmlRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
	}
}
