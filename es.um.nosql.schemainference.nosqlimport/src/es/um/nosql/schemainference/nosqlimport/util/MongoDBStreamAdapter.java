package es.um.nosql.schemainference.nosqlimport.util;

import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.bson.Document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MapReduceIterable;

public class MongoDBStreamAdapter extends AbstractStreamAdapter
{
	public Stream<JsonObject> adaptStream(Map<String, MapReduceIterable<Document>> mapRedMap)
	{
		Stream<JsonObject> result = Stream.empty();
		JsonParser parser = new JsonParser();

		for (String type : mapRedMap.keySet())
			result = Stream.concat(result, StreamSupport.stream(mapRedMap.get(type).spliterator(), false).map(doc ->
			{
				JsonObject jObj = (JsonObject)(parser).parse(doc.get("_id").toString());
				jObj.addProperty("type", type);

				return jObj;
			}));

		return result;
	}
}
