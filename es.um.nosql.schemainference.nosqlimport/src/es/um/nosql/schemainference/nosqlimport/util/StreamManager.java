package es.um.nosql.schemainference.nosqlimport.util;

import java.util.function.Consumer;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StreamManager
{
	private static StreamManager INSTANCE;

	public static StreamManager getStrManager()
	{
		if (INSTANCE == null)
			INSTANCE = new StreamManager();

		return INSTANCE;
	}

	public void printStream(Stream<JsonObject> stream)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		stream.forEach(new Consumer<JsonObject>()
				{
					@Override
					public void accept(JsonObject object)
					{
						System.out.println(gson.toJson(object));
//						JsonParser jsonParser = new JsonParser();
//						JsonObject jo = (JsonObject)jsonParser.parse(object.get("key").getAsString());
//						System.out.println(gson.toJson(jo));
					}
				});
//		System.out.println(stream);
	}
}
