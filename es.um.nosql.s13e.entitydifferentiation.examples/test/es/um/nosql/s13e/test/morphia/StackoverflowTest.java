package es.um.nosql.s13e.test.morphia;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.query.Query;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.stackoverflow.Badges;
import es.um.nosql.s13e.stackoverflow.Comments;
import es.um.nosql.s13e.stackoverflow.Postlinks;
import es.um.nosql.s13e.stackoverflow.Posts;
import es.um.nosql.s13e.stackoverflow.Tags;
import es.um.nosql.s13e.stackoverflow.Users;
import es.um.nosql.s13e.stackoverflow.Votes;

public class StackoverflowTest
{
  private final static int N_BADGES = 2500002;
  private final static int N_COMMENTS = 2500002;
  private final static int N_POSTLINKS = 2500002;
  private final static int N_TAGS = 48373;
  private final static int N_POSTS = 2500002;
  private final static int N_USERS = 2500002;
  private final static int N_VOTES = 2500002;

  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    dbName = "stackoverflow";
    morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.s13e." + dbName);
    new ValidationExtension(morphia);
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    datastore.ensureIndexes();
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testCheckConsistency()
  {
    checkStackOverflowDb(datastore);
  }

  @Test
  @Ignore
  public void testDuplicateDbAndCheck()
  {
    String newDbName = dbName + "_test_1";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    List<Badges> lBadges = new ArrayList<Badges>();
    lBadges.addAll(datastore.createQuery(Badges.class).asList());
    newDatastore.save(lBadges);

    List<Comments> lComments = new ArrayList<Comments>();
    lComments.addAll(datastore.createQuery(Comments.class).asList());
    newDatastore.save(lComments);

    List<Postlinks> lPostlinks = new ArrayList<Postlinks>();
    lPostlinks.addAll(datastore.createQuery(Postlinks.class).asList());
    newDatastore.save(lPostlinks);

    List<Posts> lPosts = new ArrayList<Posts>();
    lPosts.addAll(datastore.createQuery(Posts.class).asList());
    newDatastore.save(lPosts);

    List<Tags> lTags = new ArrayList<Tags>();
    lTags.addAll(datastore.createQuery(Tags.class).asList());
    newDatastore.save(lTags);

    List<Users> lUsers = new ArrayList<Users>();
    lUsers.addAll(datastore.createQuery(Users.class).asList());
    newDatastore.save(lUsers);

    List<Votes> lVotes = new ArrayList<Votes>();
    lVotes.addAll(datastore.createQuery(Votes.class).asList());
    newDatastore.save(lVotes);

    checkStackOverflowDb(newDatastore);
    newDatastore.getDB().dropDatabase();
  }

  @Test
  @Ignore
  public void testAddErrorAndCheck()
  {
    fail("Not implemented yet");
    String newDbName = dbName + "_test_2";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    // CODE

    newDatastore.getDB().dropDatabase();
  }

  private void checkStackOverflowDb(Datastore theDatastore)
  {
    Query<Badges> qBadges = datastore.createQuery(Badges.class);
    assertEquals(N_BADGES, qBadges.count());
    testCollection(qBadges.asList().toArray(new Badges[0]), Badges.class);

//    for (Badges badge : qBadges)
//      assertEquals(1, datastore.createQuery(Users.class).filter("_id =", badge.getUserId()).count());
/*
    Query<Comments> qComments = datastore.createQuery(Comments.class);
    assertEquals(N_COMMENTS, qComments.count());
    testCollection(qComments.asList().toArray(new Comments[0]), Comments.class);
*/
/*
    for (Comments comment : qComments)
    {
      assertEquals(1, datastore.createQuery(Posts.class).filter("_id =", comment.getPostId()).count());
      assertEquals(1, datastore.createQuery(Users.class).filter("_id =", comment.getUserId()).count());
    }
*/
/*
    Query<Postlinks> qPostlinks = datastore.createQuery(Postlinks.class);
    assertEquals(N_POSTLINKS, qPostlinks.count());
    testCollection(qPostlinks.asList().toArray(new Postlinks[0]), Postlinks.class);
*/
/*
    for (Postlinks postlink : qPostlinks)
    {
      assertEquals(1, datastore.createQuery(Posts.class).filter("_id =", postlink.getPostId()).count());
      assertEquals(1, datastore.createQuery(Posts.class).filter("_id =", postlink.getRelatedPostId()).count());
    }
*/
/*
    Query<Posts> qPosts = datastore.createQuery(Posts.class);
    assertEquals(N_POSTS, qPosts.count());
    testCollection(qPosts.asList().toArray(new Posts[0]), Posts.class);
*/
/*
    for (Posts post : qPosts)
    {
      assertEquals(1, datastore.createQuery(Users.class).filter("_id =", post.getLastEditorUserId()).count());
      assertEquals(1, datastore.createQuery(Users.class).filter("_id =", post.getOwnerUserId()).count());
      assertEquals(1, datastore.createQuery(Posts.class).filter("_id =", post.getPostTypeId()).count()); //TODO: Will fail, since this is a reference to an external table, right?
      assertEquals(1, datastore.createQuery(Tags.class).filter("_id =", post.getTags()).count());
    }
*/
/*
    Query<Tags> qTags = datastore.createQuery(Tags.class);
    assertEquals(N_TAGS, qTags.count());
    testCollection(qTags.asList().toArray(new Tags[0]), Tags.class);
*/
/*
    for (Tags tags : qTags)
    {
      assertEquals(1, datastore.createQuery(Posts.class).filter("_id =", tags.getExcerptPostId()).count());
      assertEquals(1, datastore.createQuery(Posts.class).filter("_id =", tags.getWikiPostId()).count());
    }
*/
/*
    Query<Users> qUsers = datastore.createQuery(Users.class);
    assertEquals(N_USERS, qUsers.count());
    testCollection(qUsers.asList().toArray(new Users[0]), Users.class);

    Query<Votes> qVotes = datastore.createQuery(Votes.class);
    assertEquals(N_VOTES, qVotes.count());
    testCollection(qVotes.asList().toArray(new Votes[0]), Votes.class);
*/
/*
    for (Votes vote : qVotes)
    {
      assertEquals(1, datastore.createQuery(Posts.class).filter("_id =", vote.getPostId()).count());
      assertEquals(1, datastore.createQuery(Users.class).filter("_id =", vote.getUserId()).count());
      assertEquals(1, datastore.createQuery(Votes.class).filter("_id =", vote.getVoteTypeId()).count());
    }
*/
  }

  private <T> void testCollection(T[] collection, Class<T> className)
  {
    if (collection == null || collection.length == 0)
      return;

    for (T t : collection)
    {
      Set<ConstraintViolation<T>> violations = validator.validate(t);

      for (ConstraintViolation<T> cVio : violations)
        System.out.println(cVio.getMessage());

      assertEquals(0, violations.size());
    }
  }
}