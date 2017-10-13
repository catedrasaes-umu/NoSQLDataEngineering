package es.um.nosql.schemainference.db.interfaces;

import java.io.File;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import es.um.nosql.schemainference.db.pojo.publications.Publication;
import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.db.utils.deserializer.NumberToNumberDeserializer;
import es.um.nosql.schemainference.db.utils.deserializer.StringToStringDeserializer;

public class Publications2Db extends Source2Db
{
  private int MAX_LINES_BEFORE_STORE = 25000;

  public Publications2Db(DbType db, String ip)
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
    int numLines = 0;
    int totalLines = 1;
    CsvMapper csvMapper = new CsvMapper();
    MappingIterator<Publication> mappingIterator;
    ObjectMapper oMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
    ArrayNode jsonArray = oMapper.createArrayNode();
    String collectionName = "Publications";

    SimpleModule module = new SimpleModule();
    module.addDeserializer(Integer.class, new NumberToNumberDeserializer());
    module.addDeserializer(String.class, new StringToStringDeserializer());
    csvMapper.registerModule(module);

    try
    {
      mappingIterator = csvMapper.reader(Publication.class).with(CsvSchema.emptySchema().withHeader()).readValues(new File(csvRoute));

      while (mappingIterator.hasNext())
      {
        Publication pojo = mappingIterator.next();
        jsonArray.add(oMapper.readTree(oMapper.writeValueAsString(pojo)));

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
