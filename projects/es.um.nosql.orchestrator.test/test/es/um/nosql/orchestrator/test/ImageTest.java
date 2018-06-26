package es.um.nosql.orchestrator.test;

import org.junit.Test;

public class ImageTest
{
  @Test
  public void test()
  {
    // MongoDbClient client =
    // MongoDbAdapter.getMongoDbClient(Constants.GET_OUTPUT_DATABASE());
/*    Mongo mongo = new Mongo("localhost", 27017);
    DB db = mongo.getDB("imagedb");
    DBCollection collection = db.getCollection("dummyColl");
    String filename = "/home/alberto/icon.png";
    try
    {
      File imageFile = new File(filename);
      FileInputStream f = new FileInputStream(imageFile);

      byte b[] = new byte[f.available()];
      f.read(b);

      Binary data = new Binary(b);
      BasicDBObject o = new BasicDBObject();
      o.append("photo", data);
      collection.insert(o);
      System.out.println("Inserted record.");

      f.close();

      // client.insert(dbName, collectionName, jsonContent);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  */
  }
}
