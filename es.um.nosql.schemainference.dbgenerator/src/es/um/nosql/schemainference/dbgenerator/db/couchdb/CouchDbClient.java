package es.um.nosql.schemainference.dbgenerator.db.couchdb;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.schemainference.dbgenerator.db.DbClient;

public class CouchDbClient extends StdCouchDbInstance implements DbClient
{
	public CouchDbClient(HttpClient client)
	{
		super(client);
	}

	public void populate()
	{
		deleteDatabase("test");
		System.out.println(getAllDatabases());
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

	@Override
	public void insert(String path, String jsonContent)
	{
		try
		{
			CouchDbConnector connector = createConnector(path, true);
			ObjectMapper mapper = new ObjectMapper();

			JsonNode jsonItems = mapper.readTree(jsonContent);
			List<JsonNode> itemList = new ArrayList<JsonNode>();

			if (jsonItems.isArray())
				for (JsonNode item : jsonItems)
					itemList.add(item);

			connector.executeBulk(itemList);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean shutdown()
	{
		return true;
	}
}
