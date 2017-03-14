package es.um.nosql.schemainference.db.adapters.mongodb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.um.nosql.schemainference.db.adapters.DbClient;

public class MongoDbClient extends MongoClient implements DbClient
{
	public MongoDbClient(String ip, int port)
	{
		super(ip, port);
	}

	@Override
	public void insert(String name, String jsonContent)
	{
		// For each object create a collection of that type (obj.type) and remove the type attribute.
		try
		{
			MongoDatabase db = getDatabase(name);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonItems = mapper.readTree(jsonContent);
			Map<String, List<Document>> collections = new HashMap<String, List<Document>>();

			for (JsonNode item : jsonItems)
			{
				String type = item.get("type").asText();
				if (!collections.containsKey(type))
					collections.put(type, new ArrayList<Document>());

				ObjectNode object = (ObjectNode)item;
				object.remove("type");

				collections.get(type).add(Document.parse(object.toString()));
			}

			// For each entity detected insert all its objects as a collection
			for (String collName : collections.keySet())
			{
				dropDatabase(collName);
				MongoCollection<Document> collection = db.getCollection(collName);
				collection.insertMany(collections.get(collName));
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void cleanDb(String dbName)
	{
		dropDatabase(dbName);
	}

	@Override
	public void cleanAllDbs()
	{
		for (String dbName : listDatabaseNames())
			if (!dbName.equals("admin") && !dbName.equals("local"))
				dropDatabase(dbName);
	}

	@Override
	public boolean shutdown()
	{
		close();
		return true;
	}
}
