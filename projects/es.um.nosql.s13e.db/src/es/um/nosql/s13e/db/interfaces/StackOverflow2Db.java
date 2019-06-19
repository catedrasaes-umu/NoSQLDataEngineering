package es.um.nosql.s13e.db.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import es.um.nosql.s13e.db.util.DbType;

public class StackOverflow2Db extends Source2Db
{
  private int MAX_LINES_BEFORE_STORE = 20000;

  private int MAX_OBJECTS = 2500000;

  public StackOverflow2Db(DbType db, String ip)
  {
    super(db, ip);
  }

  public void run(String xmlRoute, String dbName)
  {
    long startTime = System.currentTimeMillis();

    System.out.println("Reading xml file " + xmlRoute + "...");
    storeXMLContent(xmlRoute, dbName);
    System.out.println(dbName + ":" + Paths.get(xmlRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
  }

  private void storeXMLContent(String userXMLRoute, String dbName)
  {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(userXMLRoute))))
    {
      ArrayNode jsonArray = new ObjectMapper().createArrayNode();
      int numLines = 0;
      int totalLines = 0;
      reader.readLine();  // XML header line
      String collectionName = reader.readLine(); // <collectionName> line

      if (collectionName.length() < 2)
      {
        System.out.println("Error reading the collection name on line 2.");
        System.exit(-1);
      }
      else
      {
        collectionName = collectionName.substring(1, collectionName.length() - 1);
        System.out.println("Inserting " + collectionName + " collection");
        System.out.println("Storing each " + MAX_LINES_BEFORE_STORE);
      }

      String previousLine = reader.readLine();

      for (String line; (line = reader.readLine()) != null; totalLines++)
      {
        jsonArray.add(adaptXMLLine(previousLine));

        previousLine = line;

        if (++numLines == MAX_LINES_BEFORE_STORE)
        {
          getClient().insert(dbName, collectionName, jsonArray.toString());
          jsonArray.removeAll();
          numLines = 0;
          System.out.println("Line count: " + totalLines);
        }
        if (totalLines > MAX_OBJECTS)
          break;
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

  private ObjectNode adaptXMLLine(String line)
  {
    XmlMapper mapper = new XmlMapper();
    ObjectNode objNode = null;
    try
    {
      objNode = mapper.readValue(line, ObjectNode.class);
      objNode.put("_id", objNode.get("Id").asText());
      objNode.remove("Id");
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    return objNode;
  }
}
