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
		JsonParser parser = new JsonParser();

		return stream.map(jsonObject -> {
			JsonObject jObj = parser.parse(jsonObject.get("key").getAsString()).getAsJsonObject();
			jObj.remove("_rev");

			return jObj;
		});
	}

	public void printStream(Stream<JsonObject> stream)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		stream.forEach(object-> {
			System.out.println(gson.toJson(object));
		});
	}

	public JsonObject stream2Json(Stream<JsonObject> stream)
	{
		JsonObject result = new JsonObject();
		JsonArray array = new JsonArray();
		result.add("rows", array);

		stream.forEach(elem -> array.add(elem));

		return result;
	}
}
