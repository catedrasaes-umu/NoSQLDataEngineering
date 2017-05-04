package es.um.nosql.schemainference.nosqlimport.db.mongodb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.um.nosql.schemainference.nosqlimport.util.MapReduceSources;
import es.um.nosql.schemainference.nosqlimport.util.MongoDBStreamAdapter;

/**
 * @author dsevilla
 *
 */
public class MongoDBImport
{
	private MongoDBStreamAdapter adapter;

	public MongoDBImport()
	{
		this.adapter = new MongoDBStreamAdapter();
	}

	public Stream<JsonObject> mapRed2Stream(String dbIP, String tableName, String mapRedDir)
	{
		return performMapReduce(dbIP, tableName, mapRedDir);
	}

	public JsonArray mapRed2Array(String dbIP, String tableName, String mapRedDir)
	{
		return adapter.stream2JsonArray(performMapReduce(dbIP, tableName, mapRedDir));
	}

	public void mapRed2File(String dbIP, String tableName, String mapRedDir, String outputFile)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try
		{
			PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
			writer.print(gson.toJson(adapter.stream2JsonObject(performMapReduce(dbIP, tableName, mapRedDir))));
			writer.close();
		} catch (IOException e)
		{
			System.err.println("Error while writing JSON inference to file!");
		}
	}

	private Stream<JsonObject> performMapReduce(String dbIP, String tableName, String mapRedDir)
	{
		MongoClient mClient = new MongoClient(dbIP, 27017);
		MapReduceSources mrs = MapReduceSources.fromDir(mapRedDir);
		MongoDatabase database = mClient.getDatabase(tableName);
		Map<String, MapReduceIterable<Document>> mapRedMap = new HashMap<String, MapReduceIterable<Document>>();
		
		for (String collName : database.listCollectionNames())
		{
			MongoCollection<Document> collection = database.getCollection(collName);
			mapRedMap.put(collName, collection.mapReduce(mrs.getMapJSCode(), mrs.getReduceJSCode()));
		}

		Stream<JsonObject> result = adapter.adaptStream(mapRedMap);
		result = result.onClose(() -> { mClient.close();});

		return result;
	}
}
