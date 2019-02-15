package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class Videos2Db
{
  private int MAX_LINES_BEFORE_STORE = 10000;

  private DbClient dbClient;

  private String collectionName;

  private ObjectMapper mapper;

  private static final String MOVIES_FOLDER = "movies";
  private static final String TV_FOLDER = "tv";
  private static final String SEASONS_FOLDER = "seasons";

  public Videos2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    collectionName = "videos";
    mapper = new ObjectMapper();
  }

  public void inject(String jsonRoute, String dbName)
  {
    String parentFolder = new File(jsonRoute).getParent();

    getVideosFromFile(Paths.get(parentFolder).resolve(MOVIES_FOLDER).toFile(), dbName);
    getVideosFromFile(Paths.get(parentFolder).resolve(TV_FOLDER).toFile(), dbName);
    getVideosFromFile(Paths.get(parentFolder).resolve(SEASONS_FOLDER).toFile(), dbName);
  }

  private void getVideosFromFile(File movieFolder, String dbName)
  {
    int numLines = 0;
    int totalLines = 1;

      ArrayNode jsonArray = mapper.createArrayNode();

      for (File jsonFile : movieFolder.listFiles())
      {
        try
        {
          String content = Files.readAllLines(jsonFile.toPath()).get(0);
          ObjectNode obj = (ObjectNode)mapper.readTree(content);

          if (obj.has("videos") && obj.get("videos").get("results").size() > 0)
            jsonArray.addAll(TheMovieDbMapper.transformVideo(obj));

        } catch (IOException e)
        {
          e.printStackTrace();
        }

        if (++numLines == MAX_LINES_BEFORE_STORE)
        {
          dbClient.insert(dbName, collectionName, jsonArray.toString());
          jsonArray.removeAll();
          numLines = 0;
          System.out.println("Line count: " + totalLines);
        }

        totalLines++;
      }

      if (jsonArray.size() > 0)
      {
        System.out.println("Storing remaining files...");
        dbClient.insert(dbName, collectionName, jsonArray.toString());
      }
  }
}
