package es.um.nosql.s13e.test.morphia.datamanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.mongomovies.Criticism;
import es.um.nosql.s13e.mongomovies.Media;
import es.um.nosql.s13e.mongomovies.Movie;

public class MongoMoviesDataManagement
{
  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private Datastore newDatastore;
  private String dbName;
  private String newDbName;

  public MongoMoviesDataManagement(String ip, String dbName)
  {
    this.dbName = dbName;
    this.newDbName = dbName + "_management";
    this.morphia = new Morphia();
    this.morphia = morphia.mapPackage("es.um.nosql.s13e." + this.dbName);
    new ValidationExtension(this.morphia);
    this.client = MongoDbAdapter.getMongoDbClient(ip);
    this.datastore = this.morphia.createDatastore(this.client, this.dbName);
    this.datastore.ensureIndexes();
    this.newDatastore = this.morphia.createDatastore(this.client, this.newDbName);
    this.newDatastore.ensureIndexes();
  }

  public void startDemo()
  {
    testMediaUnions();
  }

  /**
   * This method checks how many movies have Medias as String and as List\<Media\>.
   * Then it transforms all the Media strings to Media objects and stores it in another table.
   * Then it checks how many movies have Medias as String and as List\<Media\> on the new table.
   * Then it drops the new collection.
   */
  private void testMediaUnions()
  {
    List<Movie> lMovies = datastore.createQuery(Movie.class).asList();

    printMedias(lMovies);

    lMovies.stream()
    .filter(m -> Objects.nonNull(m.getCriticisms()))
    .forEach(m ->
    {
      for (Criticism c : m.getCriticisms())
      {
        if (c.getMedia() instanceof String)
        {
          List<Media> mediaList = new ArrayList<Media>();
          Media newMedia = new Media(); newMedia.setName((String)c.getMedia()); newMedia.setUrl("");
          mediaList.add(newMedia);
          c.setMedia(mediaList);
        }
      }
    });

    newDatastore.save(lMovies);
    printMedias(newDatastore.createQuery(Movie.class).asList());
    newDatastore.getDB().dropDatabase();
  }

  private void printMedias(List<Movie> mList)
  {
    long mediasAsString = mList.stream().map(m ->
    {
      if (m.getCriticisms() == null) return (long)0;
      else return m.getCriticisms().stream().filter(c -> (c.getMedia() instanceof String)).count();
    }).reduce(Long::sum).get();

    long mediasAsObject = mList.stream().map(m ->
    {
      if (m.getCriticisms() == null) return (long)0;
      else return m.getCriticisms().stream().filter(c -> (c.getMedia() instanceof List)).count();
    }).reduce(Long::sum).get();

    int medias = mList.stream().map(m ->
    {
      if (m.getCriticisms() == null) return 0;
      else return m.getCriticisms().size();
    }).reduce(Integer::sum).get();

    System.out.println("Movies embedding Criticisms with Media as a \"string\": " + mediasAsString);
    System.out.println("Movies embedding Criticisms with Media as an \"Object\": " + mediasAsObject);
    System.out.println("Movies with Medias: " + medias);
  }

  public static void main(String[] args)
  {
    MongoMoviesDataManagement manager = new MongoMoviesDataManagement("localhost", "mongomovies");
    manager.startDemo();
  }
}