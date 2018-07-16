package es.um.nosql.s13e.db.interfaces;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import es.um.nosql.s13e.db.util.DbType;

public class Pleiades2Db extends Source2Db
{
  public Pleiades2Db(DbType db, String ip)
  {
    super(db, ip);
  }

  public void run(String jsonRoute, String dbName)
  {
    long startTime = System.currentTimeMillis();

    System.out.println("Reading json file " + jsonRoute + "...");
    storeJSONContent(jsonRoute, dbName);
    System.out.println(dbName + ":" + Paths.get(jsonRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
  }

  private void storeJSONContent(String jsonRoute, String dbName)
  {
    try
    {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootObj = mapper.readTree(new File(jsonRoute));
      ArrayNode collection = (ArrayNode) rootObj.get("@graph");
      ArrayNode partCollection = mapper.createArrayNode();
      int MAX_OBJECTS_TO_COMMIT = 1000;
      int countObjects = 0;
      int totalObjects = 0;

      for (JsonNode obj : collection)
      {
        partCollection.add(obj);

        if (++countObjects == MAX_OBJECTS_TO_COMMIT)
        {
          getClient().insert(dbName, "@graph", partCollection.toString());
          partCollection.removeAll();
          countObjects = 0;
          System.out.println("Object count: " + totalObjects);
        }

        totalObjects++;
      }

      if (partCollection.size() > 0)
      {
        System.out.println("Storing remaining objects...");
        getClient().insert(dbName, "@graph", partCollection.toString());
      }
    } catch (JsonProcessingException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}