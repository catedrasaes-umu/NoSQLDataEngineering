package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class Episodes2Db
{
  private int MAX_LINES_BEFORE_STORE = 5000;

  private DbClient dbClient;

  private String collectionName;

  private ObjectMapper mapper;

  public Episodes2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    collectionName = "episodes";
    mapper = new ObjectMapper();
  }

  public void inject(String jsonRoute, String dbName)
  {
    int numLines = 0;
    int totalLines = 1;

    try
    {
      ArrayNode episodesArray = mapper.createArrayNode();
      for (File jsonFile : new File(jsonRoute).listFiles())
      {
        String content = Files.readAllLines(jsonFile.toPath()).get(0);
        ObjectNode season = (ObjectNode)mapper.readTree(content);

        if (season.has("episodes") && season.get("episodes").size() > 0)
          episodesArray.addAll(TheMovieDbMapper.transformEpisodes((ArrayNode)season.get("episodes")));

        if (++numLines == MAX_LINES_BEFORE_STORE)
        {
          dbClient.insert(dbName, collectionName, episodesArray.toString());
          episodesArray.removeAll();
          numLines = 0;
          System.out.println("Line count: " + totalLines);
        }

        totalLines++;
      }

      if (episodesArray.size() > 0)
      {
        System.out.println("Storing remaining files...");
        dbClient.insert(dbName, collectionName, episodesArray.toString());
      }
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
