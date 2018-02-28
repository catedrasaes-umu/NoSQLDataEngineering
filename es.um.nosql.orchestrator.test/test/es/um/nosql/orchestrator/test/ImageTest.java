package es.um.nosql.orchestrator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.bson.types.Binary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import es.um.nosql.s13e.db.interfaces.Comp2Db;
import es.um.nosql.s13e.db.utils.DbType;
import es.um.nosql.s13e.json2dbschema.main.BuildNoSQLSchema;
import es.um.nosql.s13e.nosqlimport.db.mongodb.MongoDBImport;

public class ImageTest
{
  @Test
  public void test()
  {
    // MongoDbClient client =
    // MongoDbAdapter.getMongoDbClient(Constants.GET_OUTPUT_DATABASE());
    Mongo mongo = new Mongo("localhost", 27017);
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
  
  }
}
