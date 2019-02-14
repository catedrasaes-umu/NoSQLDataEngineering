package es.um.nosql.s13e.db.interfaces.themoviedb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TheMovieDbMapper
{
  private static ObjectMapper mapper = new ObjectMapper();

  public static ObjectNode transformMovie(ObjectNode obj)
  {
    // Genre
    ArrayNode arrGenres = mapper.createArrayNode();
    for (JsonNode genre : obj.get("genres"))
      arrGenres.add(genre.get("id").asInt());
    obj.put("genres", arrGenres);

    // Production_companies
    ArrayNode arrCompanies = mapper.createArrayNode();
    for (JsonNode company : obj.get("production_companies"))
      arrCompanies.add(company.get("id").asInt());
    obj.put("production_companies", arrCompanies);

    for (JsonNode prodCountry : obj.get("production_countries"))
      stripNulls((ObjectNode)prodCountry);

    for (JsonNode spokenLanguage : obj.get("spoken_languages"))
      stripNulls((ObjectNode)spokenLanguage);

    // Images
    ArrayNode arrImages = mapper.createArrayNode();
    arrImages.addAll((ArrayNode)obj.get("images").get("backdrops"));
    arrImages.addAll((ArrayNode)obj.get("images").get("posters"));
    for (JsonNode image : arrImages)
      stripNulls((ObjectNode)image);

    obj.put("images", arrImages);

    // Alternative titles
    ArrayNode arrAltTitles = mapper.createArrayNode();
    arrAltTitles.addAll((ArrayNode)obj.get("alternative_titles").get("titles"));
    for (JsonNode altTitle : arrAltTitles)
      stripNulls((ObjectNode)altTitle);

    obj.put("alternative_titles", arrAltTitles);

    // Videos
    ArrayNode arrVideos = mapper.createArrayNode();
    for (JsonNode video : obj.get("videos").get("results"))
      arrVideos.add(video.get("id").asText());
    obj.put("videos", arrVideos);

    // Credits
    ArrayNode arrCredits = mapper.createArrayNode();
    arrCredits.addAll((ArrayNode)obj.get("credits").get("cast"));
    arrCredits.addAll((ArrayNode)obj.get("credits").get("crew"));
    for (JsonNode credit : arrCredits)
    {
      ObjectNode objCredit = (ObjectNode)credit;
      objCredit.remove("credit_id");
      objCredit.put("person_id", objCredit.get("id"));
      objCredit.remove("id");
      stripNulls(objCredit);
    }
    obj.put("credits", arrCredits);

    // Keywords
    ArrayNode arrKeywords = mapper.createArrayNode();
    for (JsonNode keyword : obj.get("keywords").get("keywords"))
      arrKeywords.add(keyword.get("id").asInt());
    obj.put("keywords", arrKeywords);

    ArrayNode arrDates = mapper.createArrayNode();
    for (JsonNode date : obj.get("release_dates").get("results"))
      for (JsonNode innerDate : date.get("release_dates"))
      {
        ObjectNode objInnerDate = (ObjectNode)innerDate;
        objInnerDate.put("iso_3166_1", date.get("iso_3166_1").asText());
        arrDates.add(objInnerDate);
        stripNulls(objInnerDate);
      }
    obj.put("release_dates", arrDates);

    // Similar movies
    ArrayNode arrSimilar = mapper.createArrayNode();
    for (JsonNode similarMovie : obj.get("similar_movies").get("results"))
      arrSimilar.add(similarMovie.get("id").asInt());
    obj.put("similar_movies", arrSimilar);

    // Recommendations
    ArrayNode arrRecommendations = mapper.createArrayNode();
    for (JsonNode recommendation : obj.get("recommendations").get("results"))
      arrRecommendations.add(recommendation.get("id").asInt());
    obj.put("recommendations", arrRecommendations);

    return stripNulls(renameId(obj, Integer.class));
  }

  public static ArrayNode transformMedia(ObjectNode obj)
  {
    ArrayNode arrMedia = mapper.createArrayNode();

    for (JsonNode taggedImage : obj.get("tagged_images").get("results"))
      arrMedia.add(stripNulls(renameId((ObjectNode)taggedImage.get("media"), Integer.class)));

    return arrMedia;
  }

  public static ObjectNode transformPerson(ObjectNode obj)
  {
    obj.put("external_ids", stripNulls((ObjectNode)obj.get("external_ids")));

    ArrayNode arrTranslations = (ArrayNode)obj.get("translations").get("translations");
    for (JsonNode translation : arrTranslations)
    {
      ObjectNode objTranslation = (ObjectNode)translation;
      objTranslation.put("biography", translation.get("data").get("biography"));
      objTranslation.remove("data");
      stripNulls(objTranslation);
    }
    obj.put("translations", arrTranslations);

    ArrayNode arrCredits = mapper.createArrayNode();
    arrCredits.addAll((ArrayNode)obj.get("combined_credits").get("cast"));
    arrCredits.addAll((ArrayNode)obj.get("combined_credits").get("crew"));
    for (JsonNode credit : arrCredits)
    {
      ObjectNode objCredit = (ObjectNode)credit;
      objCredit.remove("credit_id");
      objCredit.put(objCredit.get("media_type").asText() + "_id", objCredit.get("id"));
      objCredit.remove("id");
      stripNulls(objCredit);
    }
    obj.put("credits", arrCredits);
    obj.remove("combined_credits");

    ArrayNode arrImages = mapper.createArrayNode();
    arrImages.addAll((ArrayNode)obj.get("images").get("profiles"));
    arrImages.addAll((ArrayNode)obj.get("tagged_images").get("results"));
    for (JsonNode image : arrImages)
    {
      ObjectNode objImage = (ObjectNode)image;
      if (objImage.has("media"))
        objImage.put("media", objImage.get("media").get("id"));
      stripNulls(objImage);
    }

    obj.put("images", arrImages);
    obj.remove("tagged_images");

    return stripNulls(renameId(obj, Integer.class));
  }

  public static ObjectNode transformCompany(ObjectNode obj, ArrayNode logos, ArrayNode altNames)
  {
    // Replace parent_company by its id.
    if (obj.has("parent_company") && !obj.get("parent_company").isNull())
    {
      int parentId = obj.get("parent_company").get("id").asInt();
      obj.put("parent_company", parentId);
    }

    // Add an array reference with the logos ids.
    if (logos != null && logos.size() > 0)
    {
      ArrayNode logoRefs = mapper.createArrayNode();
      obj.put("logos", logoRefs);

      logos.forEach(logo -> logoRefs.add(logo.get("_id")));
    }

    // Add alternative_names as embedded objects.
    if (altNames != null && altNames.size() > 0)
      obj.put("alternative_names", altNames);

    return stripNulls(renameId(obj, Integer.class));
  }

  public static ArrayNode transformAlternativeName(ObjectNode obj)
  {
    ArrayNode altNames = mapper.createArrayNode();

    obj.get("results").forEach(altName -> altNames.add(stripNulls((ObjectNode)altName)));

    return altNames;
  }

  public static ArrayNode transformLogo(ObjectNode obj)
  {
    ArrayNode logos = mapper.createArrayNode();

    obj.get("logos").forEach(logo ->
    {
      stripNulls(renameId((ObjectNode)logo, String.class));
      logos.add(logo);
    });

    return logos;
  }

  public static ObjectNode transformNetwork(ObjectNode obj)
  {
    return stripNulls(renameId(obj, Integer.class));
  }

  public static ObjectNode transformGenre(ObjectNode obj)
  {
    return stripNulls(renameId(obj, Integer.class));
  }

  public static ObjectNode transformKeyword(ObjectNode obj)
  {
    return stripNulls(renameId(obj, Integer.class));
  }

  private static ObjectNode stripNulls(ObjectNode obj)
  {
    Iterator<String> fieldNames = obj.fieldNames();
    List<String> nullFields = new ArrayList<String>();

    while (fieldNames.hasNext())
    {
      String fName = fieldNames.next();
      if (obj.get(fName).isNull() || ((obj.get(fName).isArray() || obj.get(fName).isObject()) && obj.get(fName).size() == 0))
        nullFields.add(fName);
    }
    obj.remove(nullFields);

    return obj;
  }

  private static <T> ObjectNode renameId(ObjectNode obj, Class<T> theClass)
  {
    
    if (obj.has("id"))
    {
      switch (theClass.getName())
      {
        case "java.lang.String": { obj.put("_id", obj.get("id").asText()); break;}
        case "java.lang.Integer": { obj.put("_id", obj.get("id").asInt()); break;}
      }
      obj.remove("id");
    }

    return obj;
  }
}
