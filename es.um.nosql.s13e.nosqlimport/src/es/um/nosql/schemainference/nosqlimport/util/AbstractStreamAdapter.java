package es.um.nosql.s13e.nosqlimport.util;

import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class AbstractStreamAdapter
{
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

    try (Stream<JsonObject> copyOfAStream = stream)
    {
      copyOfAStream.forEach(elem -> array.add(elem));
    }

    result.add("rows", array);

    return result;
  }

  public JsonArray stream2JsonArray(Stream<JsonObject> stream)
  {
    JsonArray result = new JsonArray();

    try (Stream<JsonObject> copyOfAStream = stream)
    {
      copyOfAStream.forEach(elem -> result.add(elem));
    };

    return result;
  }
}
