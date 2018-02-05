package es.um.nosql.s13e.nosqlimport.util;

import java.util.List;
import java.util.stream.Stream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CouchDBStreamAdapter extends AbstractStreamAdapter
{
  public Stream<JsonObject> adaptStream(List<JsonObject> itemList)
  {
    JsonParser parser = new JsonParser();

    return itemList.stream().map(jsonObject ->
    {
      JsonObject result = (JsonObject)parser.parse(jsonObject.get("key").getAsString());
//      result.remove("_rev");
//      result.remove("_id");

      return result;
    });
  }
}
