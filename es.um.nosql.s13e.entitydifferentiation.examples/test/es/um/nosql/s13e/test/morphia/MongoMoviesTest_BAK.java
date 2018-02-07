package es.um.nosql.s13e.test.morphia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.VerboseJSR303ConstraintViolationException;
import org.mongodb.morphia.query.Query;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.mongomovies.Criticism;
import es.um.nosql.s13e.mongomovies.Director;
import es.um.nosql.s13e.mongomovies.Media;
import es.um.nosql.s13e.mongomovies.Movie;
import es.um.nosql.s13e.mongomovies.Movietheater;
import es.um.nosql.s13e.mongomovies.Prize;

public class MongoMoviesTest_BAK
{
  private final static int N_MOVIES = 5000;
  private final static int N_MOVIETHEATER = 2000;
  private final static int N_DIRECTORS = 2000;

  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    dbName = "mongomovies";
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
    checkMongomoviesDb(datastore);
  }

  @Test
  public void testDuplicateDbAndCheck()
  {
    String newDbName = dbName + "_test_1";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    List<Movietheater> lMovietheaters = new ArrayList<Movietheater>();
    lMovietheaters.addAll(datastore.createQuery(Movietheater.class).asList());
    newDatastore.save(lMovietheaters);

    List<Director> lDirectors = new ArrayList<Director>();
    lDirectors.addAll(datastore.createQuery(Director.class).asList());
    newDatastore.save(lDirectors);

    List<Movie> lMovies = new ArrayList<Movie>();
    lMovies.addAll(datastore.createQuery(Movie.class).asList());
    newDatastore.save(lMovies);

    checkMongomoviesDb(newDatastore);
  }

  @Test
  public void testAddErrorAndCheck()
  {
    String newDbName = dbName + "_test_2";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    Movietheater mt1 = new Movietheater();
    Movietheater mt2 = new Movietheater(); mt2.set_id(new ObjectId().toString()); mt2.setCity("city"); mt2.setCountry("country"); mt2.setName("name");

    assertEquals(4, validator.validate(mt1).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(mt1);});

    Media m1 = new Media(); m1.setName("name1"); m1.setUrl("url1");
    Media m2 = new Media(); m2.setName("name2"); m2.setUrl("url2");
    assertEquals(0, validator.validate(m1).size());
    assertEquals(0, validator.validate(m2).size());

    Criticism c1 = new Criticism(); c1.setColor("color1"); c1.setJournalist("journalist1"); c1.setMedia(new Media[] {m1, m2});
    Criticism c2 = new Criticism(); c2.setColor("color2"); c2.setJournalist("journalist2"); c2.setMedia("media2");
    Criticism c3 = new Criticism(); c3.setColor("color3");
    assertThrows(ClassCastException.class, () -> {c3.setMedia(false);});
    assertThrows(ClassCastException.class, () -> {c3.setMedia(m1);});

    assertEquals(0, validator.validate(c1).size());
    assertEquals(0, validator.validate(c2).size());
    assertEquals(1, validator.validate(c3).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(c3);});

    newDatastore.getDB().dropDatabase();
  }

  private void checkMongomoviesDb(Datastore theDatastore)
  {
    Query<Movietheater> qMovietheaters = datastore.createQuery(Movietheater.class);
    assertEquals(N_MOVIETHEATER, qMovietheaters.count());
    //testCollection(qMovietheaters.asList(), Movietheater.class);

    Query<Director> qDirectors = datastore.createQuery(Director.class);
    assertEquals(N_DIRECTORS, qDirectors.count());
    //testCollection(qDirectors.asList(), Director.class);

    for (Director director : qDirectors)
    {
      for (Movie actor_movie : director.getActor_movies())
        assertEquals(1, datastore.createQuery(Movie.class).filter("_id =", actor_movie.get_id()).count());
      for (Movie directed_movie : director.getDirected_movies())
        assertEquals(1, datastore.createQuery(Movie.class).filter("_id =", directed_movie.get_id()).count());
    }

    Query<Movie> qMovies = datastore.createQuery(Movie.class);
    assertEquals(N_MOVIES, qMovies.count());
    testCollection(qMovies.asList().toArray(new Movie[0]), Movie.class);

    for (Movie movie : qMovies)
    {
      testCollection(movie.getCriticisms(), Criticism.class);
      assertEquals(1, datastore.createQuery(Director.class).filter("_id =", movie.getDirector_id().get_id()).count());
      testCollection(movie.getPrizes(), Prize.class);

      if (movie.getRating() != null)
        assertEquals(0, validator.validate(movie.getRating()).size());
    }
  }

  private <T> void testCollection(T[] collection, Class<T> className)
  {
    if (collection == null)
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