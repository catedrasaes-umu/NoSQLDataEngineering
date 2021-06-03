package es.um.unosql.subtypes;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbAdapter;
import es.um.unosql.documents.injectors.adapters.mongodb.MongoDbClient;

public class SOFPostTypeIdTest
{
  private String dbName = "stackoverflow";
  private String collName = "posts";
  private Integer MAX_POSTTYPE_ID = 8;

  @Test
  public void testPostTypeIdQueryByHand()
  {
    long initialT = System.currentTimeMillis();
    MongoDbClient client = MongoDbAdapter.getMongoDbClient("localhost");
    MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collName);
    Map<String, Integer> typeMap = new HashMap<String, Integer>();

    typeMap.put("1", 0);
    typeMap.put("2", 0);
    typeMap.put("3", 0);
    typeMap.put("4", 0);
    typeMap.put("5", 0);
    typeMap.put("6", 0);
    typeMap.put("7", 0);
    typeMap.put("8", 0);

    for (Document doc : documents.find().projection(Projections.include("PostTypeId")))
      typeMap.put(doc.getString("PostTypeId"), typeMap.get(doc.getString("PostTypeId")) + 1);

    client.shutdown();

    for (String key : typeMap.keySet())
      System.out.println(key + ") " + typeMap.get(key));

    long finalT = System.currentTimeMillis();
    System.out.println("PostTypeIdTest: " + (finalT - initialT) + " ms");
  }

  // Turns out countDocuments (and, in general, the $count stage) is translated to a $group one.
  // And again, we are querying 52 million posts eight times and this is just too expensive to do.
  public void testPostTypeIdQueryByCount()
  {
    long initialT = System.currentTimeMillis();
    MongoDbClient client = MongoDbAdapter.getMongoDbClient("localhost");
    MongoCollection<Document> documents = client.getDatabase(dbName).getCollection(collName);

    Map<String, Long> typeMap = new HashMap<String, Long>();

    for (int i = 1; i <= MAX_POSTTYPE_ID; i++)
    {
      Bson filter = Filters.eq("PostTypeId", i);
      typeMap.put(Integer.toString(i), documents.countDocuments(filter));
      System.out.println("Finished query for PostTypeId: " + i);
    }

    client.shutdown();

    for (String key : typeMap.keySet())
      System.out.println(key + ") " + typeMap.get(key));

    long finalT = System.currentTimeMillis();
    System.out.println("PostTypeIdTest: " + (finalT - initialT) + " ms");
  }
}
