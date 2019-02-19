package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class ScreenedTheatrically2Db
{
  private int MAX_LINES_BEFORE_STORE = 5000;

  private DbClient dbClient;

  private String collectionName;

  private ObjectMapper mapper;

  public ScreenedTheatrically2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    collectionName = "screened_theatrically";
    mapper = new ObjectMapper();
  }

  public void inject(String jsonRoute, String dbName)
  {
    int numLines = 0;
    int totalLines = 1;

    try
    {
      ArrayNode scrThArray = mapper.createArrayNode();
      for (File jsonFile : new File(jsonRoute).listFiles())
      {
        String content = Files.readAllLines(jsonFile.toPath()).get(0);
        ObjectNode tv = (ObjectNode)mapper.readTree(content);

        if (tv.has("screened_theatrically") && tv.get("screened_theatrically").get("results").size() > 0)
          scrThArray.addAll(TheMovieDbMapper.transformScreenedTheatrically((ArrayNode)tv.get("screened_theatrically").get("results")));

        if (++numLines == MAX_LINES_BEFORE_STORE)
        {
          dbClient.insert(dbName, collectionName, scrThArray.toString());
          scrThArray.removeAll();
          numLines = 0;
          System.out.println("Line count: " + totalLines);
        }

        totalLines++;
      }

      if (scrThArray.size() > 0)
      {
        System.out.println("Storing remaining files...");
        dbClient.insert(dbName, collectionName, scrThArray.toString());
      }
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
