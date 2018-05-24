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
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.VerboseJSR303ConstraintViolationException;
import org.mongodb.morphia.query.Query;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.mongosongs.Album;
import es.um.nosql.s13e.mongosongs.Artist;
import es.um.nosql.s13e.mongosongs.Media;
import es.um.nosql.s13e.mongosongs.Prize;
import es.um.nosql.s13e.mongosongs.Rating;
import es.um.nosql.s13e.mongosongs.Review;
import es.um.nosql.s13e.mongosongs.Track;

public class MongoSongsTest
{
  private final static int N_ARTISTS = 2000;
  private final static int N_ALBUMS = 4000;
  private final static int N_TRACKS = 2000;

  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    dbName = "mongosongs";
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

    List<Artist> lArtists = new ArrayList<Artist>();
    lArtists.addAll(datastore.createQuery(Artist.class).asList());
    newDatastore.save(lArtists);

    List<Album> lAlbums = new ArrayList<Album>();
    lAlbums.addAll(datastore.createQuery(Album.class).asList());
    newDatastore.save(lAlbums);

    List<Track> lTracks = new ArrayList<Track>();
    lTracks.addAll(datastore.createQuery(Track.class).asList());
    newDatastore.save(lTracks);

    checkMongomoviesDb(newDatastore);
    newDatastore.getDB().dropDatabase();
  }

  @Test
  public void testAddErrorAndCheck()
  {
    String newDbName = dbName + "_test_2";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    Artist a1 = new Artist();
    Artist a2 = new Artist(); a2.set_id(new ObjectId().toString()); a2.setName("name"); a2.setStartingYear(1999);

    assertEquals(5, validator.validate(a1).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(a1);});

    Media m1 = new Media(); m1.setName("name1"); m1.setUrl("url1");
    Media m2 = new Media(); m2.setName("name2"); m2.setUrl("url2");
    assertEquals(0, validator.validate(m1).size());
    assertEquals(0, validator.validate(m2).size());

    Review r1 = new Review(); r1.setJournalist("journalist1"); r1.setRank("high"); r1.setMedia(Arrays.asList(m1, m2));
    Review r2 = new Review(); r2.setJournalist("journalist2"); r2.setRank("medium"); r2.setMedia("media2");
    Review r3 = new Review(); r3.setJournalist("journalist3");
    assertThrows(ClassCastException.class, () -> {r3.setMedia(false);});
    assertThrows(ClassCastException.class, () -> {r3.setMedia(m1);});

    assertEquals(0, validator.validate(r1).size());
    assertEquals(0, validator.validate(r2).size());
    assertEquals(1, validator.validate(r3).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(r3);});

    newDatastore.getDB().dropDatabase();
  }

  private void checkMongomoviesDb(Datastore theDatastore)
  {
    Query<Artist> qArtists = datastore.createQuery(Artist.class);
    assertEquals(N_ARTISTS, qArtists.count());
    testCollection(qArtists.asList(), Artist.class);

    for (Artist artist : qArtists)
    {
      for (Album album : artist.getAlbums())
        assertEquals(1, datastore.createQuery(Album.class).filter("_id =", album.get_id()).count());

      for (Track track : artist.getComposedTracks())
        assertEquals(1, datastore.createQuery(Track.class).filter("_id =", track.get_id()).count());

      for (Track track : artist.getLyricsTracks())
        assertEquals(1, datastore.createQuery(Track.class).filter("_id =", track.get_id()).count());
    }

    Query<Album> qAlbums = datastore.createQuery(Album.class);
    assertEquals(N_ALBUMS, qAlbums.count());
    testCollection(qAlbums.asList(), Album.class);

    for (Album album : qAlbums)
    {
      for (Track track : album.getTracks())
        assertEquals(1, datastore.createQuery(Track.class).filter("_id =", track.get_id()).count());

      if (album.getPrizes() != null)
        testCollection(album.getPrizes(), Prize.class);

      if (album.getReviews() != null)
        testCollection(album.getReviews(), Review.class);
    }

    Query<Track> qTracks = datastore.createQuery(Track.class);
    assertEquals(N_TRACKS, qTracks.count());
    testCollection(qTracks.asList(), Track.class);

    for (Track track : qTracks)
    {
      for (Artist artist : track.getArtist_id())
        assertEquals(1, datastore.createQuery(Artist.class).filter("_id =", artist.get_id()).count());

      if (track.getRatings() != null)
        testCollection(track.getRatings(), Rating.class);
    }
  }

  private <T> void testCollection(List<T> collection, Class<T> className)
  {
    if (collection == null || collection.size() == 0)
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