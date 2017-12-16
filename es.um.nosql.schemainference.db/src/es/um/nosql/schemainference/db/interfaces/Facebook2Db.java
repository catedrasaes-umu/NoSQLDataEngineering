package es.um.nosql.schemainference.db.interfaces;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import es.um.nosql.schemainference.db.pojo.facebook.Comment;
import es.um.nosql.schemainference.db.pojo.facebook.Page;
import es.um.nosql.schemainference.db.pojo.facebook.Post;
import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.db.utils.deserializer.NumberToNumberDeserializer;
import es.um.nosql.schemainference.db.utils.deserializer.StringToStringDeserializer;

public class Facebook2Db extends Source2Db
{
  private int MAX_LINES_BEFORE_STORE = 25000;

  private HashMap<String, List<String>> uniqueKeys;

  public Facebook2Db(DbType db, String ip)
  {
    super(db, ip);
  }

  public void run(String csvRoute, String dbName)
  {
    long startTime = System.currentTimeMillis();

    System.out.println("Reading csv file " + csvRoute + "...");
    storeCSVContent(csvRoute, dbName);
    System.out.println(dbName + ":" + Paths.get(csvRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
  }

  private void storeCSVContent(String csvRoute, String dbName)
  {
    uniqueKeys = new HashMap<String, List<String>>();
    File csvFile = new File(csvRoute);
    CsvMapper csvMapper = new CsvMapper();
    MappingIterator<?> mappingIterator = null;
    ObjectMapper oMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
    String collectionName = null;

    SimpleModule module = new SimpleModule();
    module.addDeserializer(Integer.class, new NumberToNumberDeserializer());
    module.addDeserializer(String.class, new StringToStringDeserializer());
    csvMapper.registerModule(module);

    try
    {
      if (csvFile.getName().contains("post"))
      {
        uniqueKeys.put("posts", new ArrayList<String>());
        mappingIterator = csvMapper.reader(Post.class).with(CsvSchema.emptySchema().withHeader()).readValues(csvFile);
        collectionName = "posts";
      }
      else if (csvFile.getName().contains("pagename"))
      {
        uniqueKeys.put("pages", new ArrayList<String>());
        mappingIterator = csvMapper.reader(Page.class).with(CsvSchema.emptySchema().withHeader()).readValues(csvFile);
        collectionName = "pages";
      }
      else if (csvFile.getName().contains("comment"))
      {
        mappingIterator = csvMapper.reader(Comment.class).with(CsvSchema.emptySchema().withHeader()).readValues(csvFile);
        collectionName = "comments";
      }

      int numLines = 0;
      int totalLines = 1;
      ArrayNode jsonArray = oMapper.createArrayNode();

        while (mappingIterator.hasNext())
        {
          JsonNode objectToInsert = oMapper.readTree(oMapper.writeValueAsString(mappingIterator.next()));
          if (transformObject(collectionName, (ObjectNode)objectToInsert))
            jsonArray.add(objectToInsert);

          if (++numLines == MAX_LINES_BEFORE_STORE)
          {
            getClient().insert(dbName, collectionName, jsonArray.toString());
            jsonArray.removeAll();
            numLines = 0;
            System.out.println("Line count: " + totalLines);
          }

          totalLines++;
        }

        if (jsonArray.size() > 0)
        {
          System.out.println("Storing remaining files...");
          getClient().insert(dbName, collectionName, jsonArray.toString());
        }
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private boolean transformObject(String collectionName, ObjectNode obj)
  {
    switch (collectionName)
    {
      case "posts":
      {
        obj.put("_id", obj.get("post_id").asText());
        obj.remove("post_id");
        break;
      }
      case "pages":
      {
        obj.put("_id", obj.get("page_id").asText());
        obj.remove("page_id");
        break;
      }
      default:
      {
        return true;
      }
    }

    if (uniqueKeys.get(collectionName).stream().anyMatch(id -> id.equals(obj.get("_id").asText())))
      return false;
    else
    {
      uniqueKeys.get(collectionName).add(obj.get("_id").asText());
      return true;
    }
  }
}
