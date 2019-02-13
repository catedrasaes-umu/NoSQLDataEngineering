package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class Networks2Db
{
  private DbClient dbClient;

  private String collectionName;

  private ObjectMapper mapper;

  public Networks2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    collectionName = "networks";
    mapper = new ObjectMapper();
  }

  public void inject(String jsonRoute, String dbName)
  {
    try
    {
      ArrayNode jsonArray = mapper.createArrayNode();

      for (File jsonFile : new File(jsonRoute).listFiles())
      {
        String content = Files.readAllLines(jsonFile.toPath()).get(0);
        jsonArray.add(TheMovieDbMapper.transformNetwork((ObjectNode)mapper.readTree(content)));
      }

      dbClient.insert(dbName, collectionName, jsonArray.toString());
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
