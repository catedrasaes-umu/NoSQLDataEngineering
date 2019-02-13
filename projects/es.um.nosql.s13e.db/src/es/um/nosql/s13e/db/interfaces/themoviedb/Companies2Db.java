package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.adapters.DbClient;

public class Companies2Db
{
  private int MAX_LINES_BEFORE_STORE = 10000;

  private DbClient dbClient;

  private String collectionName;

  private ObjectMapper mapper;

  private static final String ALTERNATIVE_NAME_FOLDER = "companies_alternative_name";
  private static final String IMAGES_FOLDER = "companies_images";

  public Companies2Db(DbClient dbClient)
  {
    this.dbClient = dbClient;
    collectionName = "companies";
    mapper = new ObjectMapper();
  }

  public void inject(String jsonRoute, String dbName)
  {
    int numLines = 0;
    int totalLines = 1;
    String parentFolder = new File(jsonRoute).getParent();

    try
    {
      ArrayNode jsonArray = mapper.createArrayNode();

      for (File jsonFile : new File(jsonRoute).listFiles())
      {
        String content = Files.readAllLines(jsonFile.toPath()).get(0);
        ArrayNode logos = getLogos(Paths.get(parentFolder).resolve(IMAGES_FOLDER).resolve(jsonFile.getName()));
        ArrayNode altNames = getAlternativeNames(Paths.get(parentFolder).resolve(ALTERNATIVE_NAME_FOLDER).resolve(jsonFile.getName()));

        ObjectNode company = (ObjectNode)mapper.readTree(content);
        jsonArray.add(TheMovieDbMapper.transformCompany(company, logos, altNames));

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
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private ArrayNode getLogos(Path logoPath) throws IOException
  {
    File logoFile = logoPath.toFile();

    if (!logoFile.exists())
      return null;

    ArrayNode logos = mapper.createArrayNode();
    String content = Files.readAllLines(logoPath).get(0);
    ObjectNode obj = (ObjectNode)mapper.readTree(content);

    if (obj.has("logos") && obj.get("logos").size() > 0)
      logos.addAll(TheMovieDbMapper.transformLogo(obj));

    return logos;
  }

  private ArrayNode getAlternativeNames(Path altNamePath) throws IOException
  {
    File altNameFile = altNamePath.toFile();

    if (!altNameFile.exists())
      return null;

    ArrayNode altNames = mapper.createArrayNode();
    String content = Files.readAllLines(altNamePath).get(0);
    ObjectNode obj = (ObjectNode)mapper.readTree(content);

    if (obj.has("results") && obj.get("results").size() > 0)
      altNames.addAll(TheMovieDbMapper.transformAlternativeName(obj));

    return altNames;
  }
}
