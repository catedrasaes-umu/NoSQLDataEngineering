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

		result = mapRedMap.entrySet().stream().flatMap(e ->
			StreamSupport.stream(e.getValue().spliterator(), false).map(doc ->
			{
				JsonObject jObj = (JsonObject)(parser).parse(doc.get("_id").toString());
				jObj.addProperty("type", e.getKey());
				jObj.remove("_id");

//				if (jObj.get("_id").isJsonObject() && jObj.getAsJsonObject("_id").entrySet().isEmpty())
//					jObj.remove("_id");

				return jObj;
			}));

		return result;
	}
}
