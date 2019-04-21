package es.um.nosql.s13e.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCollection;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;

public class StackoverflowTest
{
  private MongoDbClient client;
  private String dbName;

  @Before
  public void setUp() throws Exception
  {
    client = MongoDbAdapter.getMongoDbClient("localhost");
    dbName = "stackoverflow";
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }
/*
  @Test
  public void testCheckConsistency()
  {
    MongoCollection<Document> posts = client.getDatabase(dbName).getCollection("posts");
    List<String> question = new ArrayList<String>();
    List<String> answer = new ArrayList<String>();
    List<String> wiki = new ArrayList<String>();
    List<String> tagwikiexcerpt = new ArrayList<String>();
    List<String> tagwiki = new ArrayList<String>();
    List<String> moderatornomination = new ArrayList<String>();
    List<String> wikiplaceholder = new ArrayList<String>();
    List<String> privilegewiki = new ArrayList<String>();

    for (Document post : posts.find())
    {
      int ptId = Integer.parseInt(post.getString("PostTypeId"));
      List<String> modifyList = null;
      switch (ptId)
      {
      case 1: { modifyList = question; break; }
      case 2: { modifyList = answer; break; }
      case 3: { modifyList = wiki; break; }
      case 4: { modifyList = tagwikiexcerpt; break; }
      case 5: { modifyList = tagwiki; break; }
      case 6: { modifyList = moderatornomination; break; }
      case 7: { modifyList = wikiplaceholder; break; }
      case 8: { modifyList = privilegewiki; break; }
      }

      int size = modifyList.size();
      for (String key : post.keySet())
      {
        if (!modifyList.contains(key))
          modifyList.add(key);
      }

      if (size != modifyList.size())
        System.out.println(ptId + ": PostTypeId => " + modifyList.stream().collect(Collectors.joining(", ")));
    }
  }
*/
  @Test
  public void testCheckQuestions()
  {
    MongoCollection<Document> posts = client.getDatabase(dbName).getCollection("posts");

    for (Document post : posts.find())
    {
      int ptId = Integer.parseInt(post.getString("PostTypeId"));
      if (ptId != 1)
        continue;

      if (ptId == 1)
      {
        if (!post.containsKey("Tags"))
          System.out.println("ERROR: " + post.getString("_id") + " is QUESTION but does not contain TAGS");
        if (!post.containsKey("AnswerCount"))
          System.out.println("ERROR: " + post.getString("_id") + " is QUESTION but does not contain ANSWER_COUNT");
        if (!post.containsKey("ViewCount"))
          System.out.println("ERROR: " + post.getString("_id") + " is QUESTION but does not contain VIEWCOUNT");
        if (!post.containsKey("Title"))
          System.out.println("ERROR: " + post.getString("_id") + " is QUESTION but does not contain TITLE");
      }
      else if (ptId == 2)
      {
        if (!post.containsKey("ParentId"))
          System.out.println("ERROR: " + post.getString("_id") + " is ANSWER but does not contain PARENTID");
      }
    }
  }

  @Test
  public void testCheckStrangeVariation()
  {
    MongoCollection<Document> posts = client.getDatabase(dbName).getCollection("posts");
    List<Integer> ids = new ArrayList<Integer>();

    for (Document post : posts.find())
    {
      int ptId = Integer.parseInt(post.getString("PostTypeId"));

      if (!post.containsKey("ParentId") && !post.containsKey("Tags") && !post.containsKey("AnswerCount")
          && !post.containsKey("FavoriteCount") && !post.containsKey("ViewCount") && !post.containsKey("ClosedDate") 
          && !post.containsKey("OwnerDisplayName") && !post.containsKey("AcceptedAnswerId") && !post.containsKey("LastEditorDisplayName")
          && !post.containsKey("Title")  && !post.containsKey("CommunityOwnedDate") && post.containsKey("LastEditorUserId")
          && post.containsKey("OwnerUserId") && post.containsKey("LastEditDate"))
      {
        if (!ids.contains(ptId))
        {
          ids.add(ptId);
          System.out.println(ids);
        }
      }
    }
  }
/*
  @Test
  public void testCheckAnswers()
  {
    MongoCollection<Document> posts = client.getDatabase(dbName).getCollection("posts");

    for (Document post : posts.find())
    {
      int ptId = Integer.parseInt(post.getString("PostTypeId"));
      if (ptId != 2)
        continue;

      
    }
  }*/
}
