package es.um.nosql.schemainference.dbgenerator.test.util.couchdb;

import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class CouchDbClient extends StdCouchDbInstance
{
	public CouchDbClient(HttpClient client)
	{
		super(client);
	}

	public void populate()
	{
		System.out.println(getAllDatabases());
		createDatabase("test");
		System.out.println(getAllDatabases());
		deleteDatabase("test");
	}
}
