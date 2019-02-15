package es.um.nosql.s13e.db.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.util.DbType;

public class Reddit2Db extends Source2Db
{
  private int MAX_LINES_BEFORE_STORE = 50000;

  public Reddit2Db(DbType db, String ip)
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
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(jsonRoute))))
    {
      ObjectMapper mapper = new ObjectMapper();
      ArrayNode jsonArray = mapper.createArrayNode();
      String collectionName = "posts";
      int numLines = 0;
      int totalLines = 1;
      System.out.println("Storing each " + MAX_LINES_BEFORE_STORE);

      for (String line; (line = reader.readLine()) != null; totalLines++)
      {
        jsonArray.add(transformObject((ObjectNode)mapper.readTree(line)));

        if (++numLines == MAX_LINES_BEFORE_STORE)
        {
          getClient().insert(dbName, collectionName, jsonArray.toString());
          jsonArray.removeAll();
          numLines = 0;
          System.out.println("Line count: " + totalLines);
        }
      }

      if (jsonArray.size() > 0)
      {
        System.out.println("Storing remaining files...");
        getClient().insert(dbName, collectionName, jsonArray.toString());
      }
    } catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private ObjectNode transformObject(ObjectNode obj)
  {
    if (obj.has("id"))
    {
      obj.put("_id", obj.get("id").asText());
      obj.remove("id");
    }

    Iterator<String> fieldNames = obj.fieldNames();
    List<String> nullFields = new ArrayList<String>();

    while (fieldNames.hasNext())
    {
      String fName = fieldNames.next();
      if (obj.get(fName).isNull())
        nullFields.add(fName);
    }
    obj.remove(nullFields);

    return obj;
  }
}
