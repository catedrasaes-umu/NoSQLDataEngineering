package es.um.nosql.s13e.nosqlimport.util;

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
/*
    //TODO: Code under testing. We do not return _id field, but the schema, count and timestamp.
    result = mapRedMap.entrySet().stream().flatMap(e ->
    StreamSupport.stream(e.getValue().spliterator(), false).map(doc ->
    {
      Document docValue = doc.get("value", Document.class);
      JsonObject jObj = new JsonObject();

      jObj.add("schema", parser.parse(docValue.getString("schema")));
      jObj.addProperty("count", docValue.getDouble("count").intValue());
      jObj.addProperty("timestamp", docValue.getDouble("timestamp").longValue());
      jObj.getAsJsonObject("schema").addProperty("_type", e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1));

      return jObj;
    }));
*/
    result = mapRedMap.entrySet().stream().flatMap(e ->
    {
      return StreamSupport.stream(e.getValue().spliterator(), false).map(doc ->
      {
        JsonObject jObj = (JsonObject)(parser).parse(doc.get("_id").toString());
        jObj.addProperty("_type", e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1));

        return jObj;
      });
    });

    return result;
  }
}
