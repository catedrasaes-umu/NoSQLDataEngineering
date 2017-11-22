package es.um.nosql.orchestrator.test.morphia;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.types.ObjectId;
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
import es.um.nosql.schemainference.mongoMovies3.CriticismBASE;
import es.um.nosql.schemainference.mongoMovies3.Director;
import es.um.nosql.schemainference.mongoMovies3.Medium;
import es.um.nosql.schemainference.mongoMovies3.Movie;
import es.um.nosql.schemainference.mongoMovies3.Movietheater;
import es.um.nosql.schemainference.mongoMovies3.Prize;
import es.um.nosql.schemainference.mongoMovies3.Rating;

public class MorphiaTest2
{
  private Datastore datastore;
  private MongoDbClient client;
  private String dbName;
  private Validator validator;

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
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testRetrieveObjects()
  {
    Query<Director> query = datastore.createQuery(Director.class);
    for (Director dir : query)
    {
      System.out.println(dir);
      System.out.println(dir.getName());
      for (String s : dir.getDirected_movies())
        System.out.println(s);

      System.out.println(dir.getObjectId());
      System.out.println("================");
    }

    Query<Movie> query2 = datastore.createQuery(Movie.class);
    for (Movie mov : query2)
    {
      System.out.println(mov.getDirector_id());
      System.out.println(mov.getTitle());
      System.out.println(mov.getYear());
      Rating rat = mov.getRating();
      System.out.println(" > " + rat.getScore());
      System.out.println(" > " + rat.getVoters());
      for (CriticismBASE c : mov.getCriticisms())
      {
        System.out.println(" >> " + c.getColor());
        System.out.println(" >> " + c.getJournalist());
        if (c.getMedia() instanceof String)
          System.out.println(" >> " + c.getMedia());
        else
        {
          System.out.println(c.getMedia().getClass());
          Medium m = (Medium)c.getMedia();
          System.out.println(" >> " + m.getName());
          System.out.println(" >> " + m.getUrl());
        }
      }
      System.out.println("%%%%%%%%%%%%%%%");
    }
  }
}
