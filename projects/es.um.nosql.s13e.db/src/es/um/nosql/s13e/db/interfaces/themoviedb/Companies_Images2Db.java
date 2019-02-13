package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class Companies_Images2Db
{
  private DbClient dbClient;

  private String collectionName;

  private ObjectMapper mapper;

  public Companies_Images2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    collectionName = "logos";
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
        ObjectNode obj = (ObjectNode)mapper.readTree(content);

        if (obj.has("logos") && obj.get("logos").size() > 0)
          jsonArray.addAll(TheMovieDbMapper.transformLogo(obj));
      }

      dbClient.insert(dbName, collectionName, jsonArray.toString());
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
