package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class Genres2Db
{
  private DbClient dbClient;

  private String collectionName;

  public Genres2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    this.collectionName = "genres";
  }

  public void inject(String jsonRoute, String dbName)
  {
    File jsonFile = new File(jsonRoute).listFiles()[0];
    try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile)))
    {
      ObjectMapper mapper = new ObjectMapper();
      ArrayNode jsonArray = mapper.createArrayNode();

      for (String line; (line = reader.readLine()) != null;)
        jsonArray.add(TheMovieDbMapper.transformGenre((ObjectNode)mapper.readTree(line)));

      dbClient.insert(dbName, collectionName, jsonArray.toString());
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
