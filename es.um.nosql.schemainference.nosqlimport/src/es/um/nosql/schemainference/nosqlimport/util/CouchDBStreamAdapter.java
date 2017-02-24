package es.um.nosql.schemainference.nosqlimport.util;

import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CouchDBStreamAdapter
{
	public Stream<JsonObject> adaptStream(Stream<JsonObject> stream)
	{
		return stream.map(jsonObject ->
		{
			JsonObject result = (JsonObject)(new JsonParser()).parse(jsonObject.get("key").getAsString());
			result.remove("_rev");

			return result;
		});
	}

	public void printStream(Stream<JsonObject> stream)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		stream.forEach(jsonObject ->
		{
			System.out.println(gson.toJson(jsonObject));
		});
	}

	public JsonObject stream2JsonObject(Stream<JsonObject> stream)
	{
		JsonObject result = new JsonObject();
		JsonArray array = new JsonArray();
		stream.forEach(elem -> array.add(elem));
		result.add("rows", array);

		return result;
	}

	public JsonArray stream2JsonArray(Stream<JsonObject> stream)
	{
		JsonArray result = new JsonArray();
		stream.forEach(elem -> result.add(elem));

		return result;
	}
}
