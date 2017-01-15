package es.um.nosql.schemainference.dbgenerator.db.couchdb;

import org.ektorp.http.StdHttpClient;

public class CouchDbAdapter
{
	private static int COUCHDB_DEFAULT_PORT = 5984;

	public static CouchDbClient getCouchDbClient(String ip)
	{
		return getCouchDbClient(ip, COUCHDB_DEFAULT_PORT);
	}

	public static CouchDbClient getCouchDbClient(String ip, int port)
	{
		return new CouchDbClient(new StdHttpClient.Builder().host(ip).port(port).build());
	}
}
