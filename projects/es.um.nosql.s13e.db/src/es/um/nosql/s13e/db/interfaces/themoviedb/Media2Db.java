package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class Media2Db
{
  private int MAX_LINES_BEFORE_STORE = 10000;

  private DbClient dbClient;

  private String collectionName;

  private ObjectMapper mapper;

  private List<Integer> mediaIds;

  public Media2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    collectionName = "media";
    mapper = new ObjectMapper();
    mediaIds = new ArrayList<Integer>();
  }

  public void inject(String jsonRoute, String dbName)
  {
    int numLines = 0;
    int totalLines = 1;

    try
    {
      ArrayNode mediaArray = mapper.createArrayNode();
      for (File jsonFile : new File(jsonRoute).listFiles())
      {
        String content = Files.readAllLines(jsonFile.toPath()).get(0);
        ArrayNode media = TheMovieDbMapper.transformMedia((ObjectNode)mapper.readTree(content));

        for (JsonNode medium : media)
          if (!mediaIds.contains(medium.get("_id").asInt()))
          {
            mediaArray.add(medium);
            mediaIds.add(medium.get("_id").asInt());
          }

        if (++numLines == MAX_LINES_BEFORE_STORE)
        {
          dbClient.insert(dbName, "media", mediaArray.toString());
          mediaArray.removeAll();
          numLines = 0;
          System.out.println("Line count: " + totalLines);
        }

        totalLines++;
      }

      if (mediaArray.size() > 0)
      {
        System.out.println("Storing remaining files...");
        dbClient.insert(dbName, collectionName, mediaArray.toString());
      }
    } catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
