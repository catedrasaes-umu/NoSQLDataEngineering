package es.um.nosql.s13e.db.interfaces;

import java.io.File;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;

import es.um.nosql.s13e.db.pojo.urban.Word;
import es.um.nosql.s13e.db.util.DbType;
import es.um.nosql.s13e.db.util.deserializer.NumberToNumberDeserializer;
import es.um.nosql.s13e.db.util.deserializer.StringToStringDeserializer;

public class UrbanDictionary2Db extends Source2Db
{
  private int MAX_LINES_BEFORE_STORE = 25000;

  public UrbanDictionary2Db(DbType db, String ip)
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
    File jsonFile = new File(jsonRoute);
    MappingIterator<?> mappingIterator = null;
    ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_EMPTY);
    String collectionName = null;

    SimpleModule module = new SimpleModule();
    module.addDeserializer(Integer.class, new NumberToNumberDeserializer());
    module.addDeserializer(String.class, new StringToStringDeserializer());
    mapper.registerModule(module);

    try
    {
      mappingIterator = mapper.reader(Word.class).readValues(jsonFile);
      collectionName = "urban_words";

      int numLines = 0;
      int totalLines = 1;
      ArrayNode jsonArray = mapper.createArrayNode();

      while (mappingIterator.hasNext())
      {
        jsonArray.add(mapper.readTree(mapper.writeValueAsString(mappingIterator.next())));

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
}