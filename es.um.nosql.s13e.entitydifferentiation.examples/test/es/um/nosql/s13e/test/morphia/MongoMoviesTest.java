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
import es.um.nosql.s13e.everypolitician.Organizations;
import es.um.nosql.s13e.mongomovies.Director;
import es.um.nosql.s13e.mongomovies.Movie;
import es.um.nosql.s13e.mongomovies.Movietheater;

public class MongoMoviesTest
{
  private final static int N_MOVIES = 100000;
  private final static int N_MOVIETHEATER = 40000;
  private final static int N_DIRECTORS = 40000;

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
  @Ignore
  public void testDuplicateBdAndCheck()
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
    newDatastore.getDB().dropDatabase();
  }

  @Test
  @Ignore
  public void testAddErrorAndCheck()
  {
    fail("Not implemented yet.");
  }

  private void checkMongomoviesDb(Datastore theDatastore)
  {
    Query<Movietheater> qMovietheaters = datastore.createQuery(Movietheater.class);
    assertEquals(N_MOVIETHEATER, qMovietheaters.count());
    testCollection(qMovietheaters.asList().toArray(new Movietheater[0]), Movietheater.class);

    Query<Director> qDirectors = datastore.createQuery(Director.class);
    assertEquals(N_DIRECTORS, qDirectors.count());
    testCollection(qDirectors.asList().toArray(new Director[0]), Director.class);

    for (Director director : qDirectors)
    {
      testCollection(director.getActor_movies(), Movie.class);
      testCollection(director.getDirected_movies(), Movie.class);
    }

    Query<Movie> qMovies = datastore.createQuery(Movie.class);
    assertEquals(N_MOVIES, qDirectors.count());
    testCollection(qMovies.asList().toArray(new Movie[0]), Movie.class);
/*
    for (Movie movie : qMovies)
    {
      testCollection(movie.getCriticisms(), Criticism.class);
      testCollection(movie.getDirector_id(), Director.class);
      testCollection(movie.get.getDirector_id(), Director.class);
      
    }*/
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