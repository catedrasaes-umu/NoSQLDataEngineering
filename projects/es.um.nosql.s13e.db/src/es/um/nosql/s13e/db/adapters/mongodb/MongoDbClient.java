package es.um.nosql.s13e.db.adapters.mongodb;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.um.nosql.s13e.db.adapters.DbClient;

public class MongoDbClient extends MongoClient implements DbClient
{
	public MongoDbClient(String ip, int port)
	{
		super(ip, port);
	}

	@Override
	public void insert(String dbName, String jsonContent)
	{
		Map<String, List<Document>> collections = new HashMap<String, List<Document>>();
		// For each object create a collection of that type (obj.type) and remove the type attribute.
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonItems = mapper.readTree(jsonContent);

			for (JsonNode item : jsonItems)
			{
				String type = item.get("_type").asText().toLowerCase();
				if (!collections.containsKey(type))
					collections.put(type, new ArrayList<Document>());

				ObjectNode object = (ObjectNode)item;
				object.remove("_type");

				collections.get(type).add(Document.parse(object.toString()));
			}

			insert(dbName, collections);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void insert(String dbName, String collectionName, String jsonContent)
	{
		Map<String, List<Document>> collection = new HashMap<String, List<Document>>();
		List<Document> docList = new ArrayList<Document>();

		try
		{
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonItems = mapper.readTree(jsonContent);

			jsonItems.forEach(jsonElement ->
			{
				docList.add(Document.parse(jsonElement.toString()));
			});

			collection.put(collectionName, docList);
			insert(dbName, collection);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void insert(String dbName, Map<String, List<Document>> collections)
	{
		MongoDatabase db = getDatabase(dbName);

		for (String collName : collections.keySet())
		{
			MongoCollection<Document> collection = db.getCollection(collName);
			collection.insertMany(collections.get(collName));
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
