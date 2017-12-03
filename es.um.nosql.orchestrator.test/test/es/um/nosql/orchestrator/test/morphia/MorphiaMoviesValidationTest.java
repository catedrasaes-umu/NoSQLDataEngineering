package es.um.nosql.orchestrator.test.morphia;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.query.Query;

import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.schemainference.mongoMovies3.Criticism;
import es.um.nosql.schemainference.mongoMovies3.Director;
import es.um.nosql.schemainference.mongoMovies3.Movie;
import es.um.nosql.schemainference.mongoMovies3.Movietheater;
import es.um.nosql.schemainference.mongoMovies3.Prize;
import es.um.nosql.schemainference.mongoMovies3.Rating;

public class MorphiaMoviesValidationTest
{
  private Datastore datastore;
  private MongoDbClient client;
  private String dbName;
  private Validator validator;

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
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testRetrieveDirectors()
  {
    Query<Director> qDirectors = datastore.createQuery(Director.class);
    Assert.assertEquals(62, qDirectors.count());

    for (Director director : qDirectors)
    {
      Set<ConstraintViolation<Director>> violations = validator.validate(director);
      Assert.assertEquals(0, violations.size());
    }
  }

  @Test
  public void testRetrieveMovies()
  {
    Query<Movie> qMovies = datastore.createQuery(Movie.class);
    Assert.assertEquals(158, qMovies.count());

    for (Movie movie : qMovies)
    {
      Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
      Assert.assertEquals(0, violations.size());

      testCollection(movie.getCriticisms(), Criticism.class);
      testCollection(movie.getPrizes(), Prize.class);
      testCollection(movie.getRating(), Rating.class);
    }
  }

  @Test
  public void testRetrieveMovietheaters()
  {
    Query<Movietheater> qMovietheaters = datastore.createQuery(Movietheater.class);
    Assert.assertEquals(64, qMovietheaters.count());

    for (Movietheater moviet : qMovietheaters)
    {
      Set<ConstraintViolation<Movietheater>> violations = validator.validate(moviet);
      Assert.assertEquals(0, violations.size());
    }
  }

  private <T> void testCollection(T[] collection, Class<T> className)
  {
    if (collection == null || collection.length == 0)
      return;

    for (T t : collection)
    {
      Set<ConstraintViolation<T>> violations = validator.validate(t);

      for (ConstraintViolation<T> cVio : violations)
        System.out.println(cVio);

      Assert.assertEquals(0, violations.size());
    }
  }
}