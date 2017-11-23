package es.um.nosql.orchestrator.test.morphia;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.schemainference.mongoMovies3.Criticism;
import es.um.nosql.schemainference.mongoMovies3.Director;
import es.um.nosql.schemainference.mongoMovies3.Medium;
import es.um.nosql.schemainference.mongoMovies3.Movie;
import es.um.nosql.schemainference.mongoMovies3.Movietheater;
import es.um.nosql.schemainference.mongoMovies3.Prize;
import es.um.nosql.schemainference.mongoMovies3.Rating;

public class MorphiaTest
{
  private Datastore datastore;
  private MongoDbClient client;
  private String dbName;
  private Validator validator;

  private Movietheater mtheater;
  private Prize prize;
  private Rating rating;
  private Criticism criticism1;
  private Criticism criticism2;
  private Medium medium;
  private Director director;
  private Movie movie;

  @Before
  public void setUp() throws Exception
  {
    Morphia morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.schemainference.mongoMovies3");
    new ValidationExtension(morphia);
    dbName = "mongoMovies3";
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    validator = Validation.buildDefaultValidatorFactory().getValidator();

    mtheater = new Movietheater();
    mtheater.setName("name");
    mtheater.setCity("city");
    mtheater.setCountry("country");
    mtheater.setNoOfRooms(33); //Optional

    prize = new Prize();
    prize.setEvent("event");
    prize.setYear(33);
    prize.setNames(new String[] {"names"});
    prize.setName("name"); //Optional

    rating = new Rating();
    rating.setScore(33);
    rating.setVoters(22);

    medium = new Medium();
    medium.setName("name");
    medium.setUrl("url");

    criticism1 = new Criticism();
    criticism1.setColor("color");
    criticism1.setJournalist("journalist");
    criticism1.setMedia(medium); // Optional. Media is a UnionType(medium, string)

    criticism2 = new Criticism();
    criticism2.setColor("color2");
    criticism2.setJournalist("journalist2");
    criticism2.setMedia("a medium as a string"); // Optional. Media is a UnionType(medium, string)

    movie = new Movie();
    movie.setTitle("title");
    movie.setYear(133);
    movie.setRating(rating);
    movie.setCriticisms(new Criticism[] {criticism1, criticism2});

    movie.setObjectId(new ObjectId());

    director = new Director();
    director.setObjectId(new ObjectId());
    director.setName("name");
    director.setDirected_movies(new String[] {movie.getObjectId().toString()}); // Optional
    movie.setDirector_id(director.getObjectId().toString());
//    director.setActor_movies(new String[] {"act1", "act2"});  // Optional

  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testMovieTheater()
  {
    Set<ConstraintViolation<Movietheater>> violations = validator.validate(mtheater);

    for (ConstraintViolation<Movietheater> violation : violations)
      System.out.println(violation.getMessage());

    Assert.assertEquals(0, violations.size());
  }

  @Test
  public void testPrize()
  {
    Set<ConstraintViolation<Prize>> violations = validator.validate(prize);

    for (ConstraintViolation<Prize> violation : violations)
      System.out.println(violation.getMessage());

    Assert.assertEquals(0, violations.size());
  }

  @Test
  public void testRating()
  {
    Set<ConstraintViolation<Rating>> violations = validator.validate(rating);

    for (ConstraintViolation<Rating> violation : violations)
      System.out.println(violation.getMessage());

    Assert.assertEquals(0, violations.size());
  }

  @Test
  public void testCriticism()
  {
    Set<ConstraintViolation<Criticism>> violations = validator.validate(criticism1);

    for (ConstraintViolation<Criticism> violation : violations)
      System.out.println(violation.getMessage());

    Assert.assertEquals(0, violations.size());

    violations = validator.validate(criticism2);

    for (ConstraintViolation<Criticism> violation : violations)
      System.out.println(violation.getMessage());

    Assert.assertEquals(0, violations.size());
  }

  @Test
  public void testMedium()
  {
    Set<ConstraintViolation<Medium>> violations = validator.validate(medium);

    for (ConstraintViolation<Medium> violation : violations)
      System.out.println(violation.getMessage());

    Assert.assertEquals(0, violations.size());
  }

  @Test
  public void testDirector()
  {
    datastore.save(movie);
    Set<ConstraintViolation<Director>> violations = validator.validate(director);

    for (ConstraintViolation<Director> violation : violations)
      System.out.println(violation.getMessage());

    Assert.assertEquals(0, violations.size());
    datastore.save(director);
  }

}
