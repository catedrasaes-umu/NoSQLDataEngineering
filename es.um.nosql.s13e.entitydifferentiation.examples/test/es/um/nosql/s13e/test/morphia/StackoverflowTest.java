package es.um.nosql.s13e.test.morphia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.VerboseJSR303ConstraintViolationException;
import org.mongodb.morphia.query.FindOptions;

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
  private final static int N_BADGES = 25000;
  private final static int N_COMMENTS = 25000;
  private final static int N_POSTLINKS = 25000;
  private final static int N_TAGS = 48373;
  private final static int N_POSTS = 25000;
  private final static int N_USERS = 25000;
  private final static int N_VOTES = 25000;

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
  public void testDuplicateDbAndCheck()
  {
    String newDbName = dbName + "_test_1";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    List<Badges> lBadges = new ArrayList<Badges>();
    lBadges.addAll(datastore.createQuery(Badges.class).asList(new FindOptions().limit(N_BADGES)));
    newDatastore.save(lBadges);
    lBadges.clear();

    List<Comments> lComments = new ArrayList<Comments>();
    lComments.addAll(datastore.createQuery(Comments.class).asList(new FindOptions().limit(N_COMMENTS)));
    newDatastore.save(lComments);
    lBadges.clear();

    List<Postlinks> lPostlinks = new ArrayList<Postlinks>();
    lPostlinks.addAll(datastore.createQuery(Postlinks.class).asList(new FindOptions().limit(N_POSTLINKS)));
    newDatastore.save(lPostlinks);
    lBadges.clear();

    List<Posts> lPosts = new ArrayList<Posts>();
    lPosts.addAll(datastore.createQuery(Posts.class).asList(new FindOptions().limit(N_POSTS)));
    newDatastore.save(lPosts);
    lPosts.clear();

    List<Tags> lTags = new ArrayList<Tags>();
    lTags.addAll(datastore.createQuery(Tags.class).asList(new FindOptions().limit(N_TAGS)));
    newDatastore.save(lTags);
    lTags.clear();

    List<Users> lUsers = new ArrayList<Users>();
    lUsers.addAll(datastore.createQuery(Users.class).asList(new FindOptions().limit(N_USERS)));
    newDatastore.save(lUsers);
    lUsers.clear();

    List<Votes> lVotes = new ArrayList<Votes>();
    lVotes.addAll(datastore.createQuery(Votes.class).asList(new FindOptions().limit(N_VOTES)));
    newDatastore.save(lVotes);
    lVotes.clear();

    checkStackOverflowDb(newDatastore);
    newDatastore.getDB().dropDatabase();
  }

  @Test
  public void testAddErrorAndCheck()
  {
    String newDbName = dbName + "_test_2";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    Users user1 = new Users(); user1.set_id((new ObjectId()).toString()); user1.setCreationDate("date1"); user1.setDisplayName("display1");
    user1.setDownVotes(1); user1.setLastAccessDate("accessdate1"); user1.setReputation(1); user1.setUpVotes(1); user1.setViews("views1");

    assertEquals(0, validator.validate(user1).size());

    Badges badge1 = new Badges(); badge1.set_id((new ObjectId()).toString());
    Badges badge2 = new Badges(); badge2.set_id((new ObjectId()).toString()); badge2.setTheClass(2);
    Badges badge3 = new Badges(); badge3.set_id((new ObjectId()).toString()); badge3.setTheClass(3); badge3.setDate("date3");
    Badges badge4 = new Badges(); badge4.set_id((new ObjectId()).toString()); badge4.setTheClass(4); badge4.setDate("date4"); badge4.setName("name4");
    Badges badge5 = new Badges(); badge5.set_id((new ObjectId()).toString()); badge5.setTheClass(5);
    badge5.setDate("date5"); badge5.setName("name5"); badge5.setTagBased("tagbased5");
    Badges badge6 = new Badges(); badge6.set_id((new ObjectId()).toString()); badge6.setTheClass(6);
    badge6.setDate("date6"); badge6.setName("name6"); badge6.setTagBased("tagbased6"); badge6.setUserId(user1);

    assertEquals(5, validator.validate(badge1).size());
    assertEquals(4, validator.validate(badge2).size());
    assertEquals(3, validator.validate(badge3).size());
    assertEquals(2, validator.validate(badge4).size());
    assertEquals(1, validator.validate(badge5).size());
    assertEquals(0, validator.validate(badge6).size());

    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(badge1);});
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(badge2);});
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(badge3);});
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(badge4);});
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(badge5);});

    newDatastore.getDB().dropDatabase();
  }

  private void checkStackOverflowDb(Datastore theDatastore)
  {
    List<Badges> lBadges = datastore.createQuery(Badges.class).asList(new FindOptions().limit(N_BADGES));
    assertEquals(N_BADGES, lBadges.size());
    testCollection(lBadges.toArray(new Badges[0]), Badges.class);
    lBadges.clear();

    List<Comments> lComments = datastore.createQuery(Comments.class).asList(new FindOptions().limit(N_COMMENTS));
    assertEquals(N_COMMENTS, lComments.size());
    testCollection(lComments.toArray(new Comments[0]), Comments.class);
    lComments.clear();

    List<Postlinks> lPostlinks = datastore.createQuery(Postlinks.class).asList(new FindOptions().limit(N_POSTLINKS));
    assertEquals(N_POSTLINKS, lPostlinks.size());
    testCollection(lPostlinks.toArray(new Postlinks[0]), Postlinks.class);
    lPostlinks.clear();

    List<Posts> lPosts = datastore.createQuery(Posts.class).asList(new FindOptions().limit(N_POSTS));
    assertEquals(N_POSTS, lPosts.size());
    testCollection(lPosts.toArray(new Posts[0]), Posts.class);
    lPosts.clear();

    List<Tags> lTags = datastore.createQuery(Tags.class).asList(new FindOptions().limit(N_TAGS));
    assertEquals(N_TAGS, lTags.size());
    testCollection(lTags.toArray(new Tags[0]), Tags.class);
    lTags.clear();

    List<Users> lUsers = datastore.createQuery(Users.class).asList(new FindOptions().limit(N_USERS));
    assertEquals(N_USERS, lUsers.size());
    testCollection(lUsers.toArray(new Users[0]), Users.class);
    lUsers.clear();

    List<Votes> lVotes = datastore.createQuery(Votes.class).asList(new FindOptions().limit(N_VOTES));
    assertEquals(N_VOTES, lVotes.size());
    testCollection(lVotes.toArray(new Votes[0]), Votes.class);
    lVotes.clear();
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