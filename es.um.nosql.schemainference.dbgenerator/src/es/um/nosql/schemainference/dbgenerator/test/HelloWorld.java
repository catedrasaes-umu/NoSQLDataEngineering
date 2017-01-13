package es.um.nosql.schemainference.dbgenerator.test;

import es.um.nosql.schemainference.dbgenerator.test.util.couchdb.CouchDbAdapter;
import es.um.nosql.schemainference.dbgenerator.test.util.couchdb.CouchDbClient;

public class HelloWorld
{
	public static String DATABASE_IP = "155.54.190.237";

	public static void main(String[] args)
	{
		CouchDbClient client = CouchDbAdapter.getCouchDbClient(DATABASE_IP);
		client.populate();
	}
}
