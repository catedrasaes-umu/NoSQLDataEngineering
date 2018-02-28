package es.um.nosql.s13e.db.gen.output;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.db.gen.utils.Constants;

public class OutputGen
{
  private ObjectMapper oMapper;
  private final static String extension = ".json";
  private Map<String, Integer> splitMap;

  public OutputGen()
  {
    oMapper = new ObjectMapper();
    splitMap = new HashMap<String, Integer>();
  }

  public void genOutput(Map<String, ArrayNode> jsonContent)
  {
    for (String collName : jsonContent.keySet())
    {
      if (Constants.IS_DEFINED_OUTPUT_CONSOLE())
        genToConsole(jsonContent.get(collName));

      if (Constants.IS_DEFINED_OUTPUT_DATABASE())
        genToDatabase(jsonContent.get(collName), collName);

      if (Constants.IS_DEFINED_OUTPUT_FOLDER())
        genToFile(jsonContent.get(collName), collName);
    }
  }

  private void genToConsole(ArrayNode arrayNode)
  {
    ObjectWriter writer = oMapper.writerWithDefaultPrettyPrinter();

    try
    {
      System.out.println(writer.writeValueAsString(arrayNode));
    } catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
  }

  private void genToDatabase(ArrayNode arrayNode, String collName)
  {
    MongoDbClient client = MongoDbAdapter.getMongoDbClient(Constants.GET_OUTPUT_DATABASE());
    ObjectWriter writer = oMapper.writerWithDefaultPrettyPrinter();

    try
    {
      String result = writer.writeValueAsString(arrayNode);
      client.insert(Constants.GET_OUTPUT_DATABASE_COLLECTION(), result);      
    } catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }

    client.close();
  }

  private void genToFile(ArrayNode arrayNode, String collName)
  {
    ObjectWriter writer = oMapper.writerWithDefaultPrettyPrinter();

    if (splitMap.containsKey(collName))
      splitMap.put(collName, splitMap.get(collName) + 1);
    else
      splitMap.put(collName, 0);

    int splitExtension = splitMap.get(collName);

    File outputFile = Paths.get(Constants.GET_OUTPUT_FOLDER(), collName + "_" + splitExtension + extension).toFile();
    outputFile.getParentFile().mkdirs();

    try
    {
      writer.writeValue(outputFile, arrayNode);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}