package es.um.nosql.s13e.nosqlimport.db.mongodb;

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

import es.um.nosql.s13e.nosqlimport.util.MapReduceSources;
import es.um.nosql.s13e.nosqlimport.util.MongoDBStreamAdapter;

public class MongoDBImport
{
  private MongoDBStreamAdapter adapter;
  private String dbIP;
  private String tableName;

  public MongoDBImport(String dbIP, String tableName)
  {
    this.adapter = new MongoDBStreamAdapter();
    this.dbIP = dbIP;
    this.tableName = tableName;
  }

  public Stream<JsonObject> mapRed2Stream(String mapRedDir)
  {
    return mapRed2Stream(MapReduceSources.fromDir(mapRedDir));
  }

  public Stream<JsonObject> mapRed2Stream(MapReduceSources mrs)
  {
    return performMapReduce(mrs);
  }

  public JsonArray mapRed2Array(String mapRedDir)
  {
    return mapRed2Array(MapReduceSources.fromDir(mapRedDir));
  }

  public JsonArray mapRed2Array(MapReduceSources mrs)
  {
    return adapter.stream2JsonArray(performMapReduce(mrs));
  }

  public void mapRed2File(String mapRedDir, String outputFile)
  {
    mapRed2File(MapReduceSources.fromDir(mapRedDir), outputFile);
  }

  public void mapRed2File(MapReduceSources mrs, String outputFile)
  {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    try
    {
      PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
      writer.print(gson.toJson(adapter.stream2JsonObject(performMapReduce(mrs))));
      writer.close();
    } catch (IOException e)
    {
      System.err.println("Error while writing JSON inference to file!");
    }
  }

  private Stream<JsonObject> performMapReduce(MapReduceSources mrs)
  {
    MongoClient mClient = new MongoClient(dbIP, 27017);
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
