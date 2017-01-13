package es.um.nosql.schemainference.dbgenerator.test.util.couchdb;

import org.ektorp.CouchDbConnector;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CouchDbClient extends StdCouchDbInstance
{
	public CouchDbClient(HttpClient client)
	{
		super(client);
	}

	public void populate()
	{
		System.out.println(getAllDatabases());
		deleteDatabase("test");
		CouchDbConnector connector = createConnector("test", true);
		System.out.println(getAllDatabases());

		JsonNodeFactory factory = JsonNodeFactory.instance;

		ObjectNode s = factory.objectNode();
		s.put("anInteger", 15);
		s.put("value", "theValue");
		s.put("anotherInteger", 10);
		s.put("aString", "blablablabla");
		connector.create("idThis", s);

		s = factory.objectNode();
		s.put("anInteger", 16);
		s.put("value", "theValuee");
		s.put("anotherInteger", 100);
		s.put("aString", "blebleble");
		connector.create("idThat", s);

		s = factory.objectNode();
		s.put("anInteger", 16);
		connector.create("idThose", s);

		for (String st : connector.getAllDocIds())
			System.out.println(connector.get(Object.class, st));
	}
}
