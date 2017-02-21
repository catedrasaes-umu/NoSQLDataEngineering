package es.um.nosql.schemainference.nosqlimport.util;

import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CouchDBStreamAdapter
{
	public Stream<JsonObject> adaptStream(Stream<JsonObject> stream)
	{
		JsonParser parser = new JsonParser();

		return stream.map(jsonObject -> {
//			System.out.println(jsonObject);
			return parser.parse(jsonObject.get("key").getAsString()).getAsJsonObject();
		});
	}

	public void printStream(Stream<JsonObject> stream)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		stream.forEach(object-> {
			System.out.println(gson.toJson(object));
//						JsonParser jsonParser = new JsonParser();
//						JsonObject jo = (JsonObject)jsonParser.parse(object.get("key").getAsString());
//						System.out.println(gson.toJson(jo));
		});
//		System.out.println(stream);
	}
}
