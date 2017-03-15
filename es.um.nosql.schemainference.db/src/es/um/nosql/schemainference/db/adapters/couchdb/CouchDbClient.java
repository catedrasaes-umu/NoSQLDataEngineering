package es.um.nosql.schemainference.db.adapters.couchdb;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.schemainference.db.adapters.DbClient;

public class CouchDbClient extends StdCouchDbInstance implements DbClient
{
	public CouchDbClient(HttpClient client)
	{
		super(client);
	}

	@Override
	public void insert(String dbName, String jsonContent)
	{
		try
		{
			CouchDbConnector connector = createConnector(dbName, true);
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
	public void insert(String dbName, String collectionName, String jsonContent)
	{
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = null;

		try
		{
			arrayNode = (ArrayNode)mapper.readTree(jsonContent);
			arrayNode.forEach(jsonElement ->
			{
				((ObjectNode)jsonElement).put("type", collectionName);
			});

			insert(dbName, arrayNode.toString());

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void cleanDb(String dbName)
	{
		try
		{
			deleteDatabase(dbName);
		} catch (DocumentNotFoundException e)
		{
			System.err.println("Database doesnt exist: " + dbName);
		}
	}

	@Override
	public void cleanAllDbs()
	{
		for (String dbName : getAllDatabases())
			deleteDatabase(dbName);
	}

	@Override
	public boolean shutdown()
	{
		return true;
	}
}
