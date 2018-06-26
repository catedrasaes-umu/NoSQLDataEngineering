package es.um.nosql.s13e.test.morphia.datamanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.mongosongs.Album;
import es.um.nosql.s13e.mongosongs.Artist;
import es.um.nosql.s13e.mongosongs.Media;
import es.um.nosql.s13e.mongosongs.Prize;
import es.um.nosql.s13e.mongosongs.Rating;
import es.um.nosql.s13e.mongosongs.Review;
import es.um.nosql.s13e.mongosongs.Track;

public class MongoSongsDataManagement
{
  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  private int pinkFloydId = 0;
  private int massiveAttackId = 100;

  public MongoSongsDataManagement(String ip, String dbName)
  {
    this.dbName = dbName;
    this.morphia = new Morphia();
    this.morphia = morphia.mapPackage("es.um.nosql.s13e.mongosongs");
    new ValidationExtension(this.morphia);
    this.client = MongoDbAdapter.getMongoDbClient(ip);
    this.datastore = this.morphia.createDatastore(this.client, this.dbName);
    this.datastore.ensureIndexes();
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  public void startDemo()
  {
    fillDbPinkFloyd();
    fillDbMassiveAttack();
  }

  private String getPinkFloydNextId()
  {
    return String.valueOf(++pinkFloydId);
  }

  private String getMassiveAttackNextId()
  {
    return String.valueOf(++massiveAttackId);
  }

  private void fillDbPinkFloyd()
  {
    Artist artist = new Artist(); artist.set_id(getPinkFloydNextId()); artist.setName("Pink Floyd"); artist.setStartingYear(1965);

    Album a1 = new Album(); a1.set_id(getPinkFloydNextId());
    Album a2 = new Album(); a2.set_id(getPinkFloydNextId());

    Track t1 = new Track(); t1.set_id(getPinkFloydNextId()); t1.setArtist_id(Arrays.asList(artist)); t1.setLength(13.32); t1.setName("Shine on you crazy diamond (I-V)");
    t1.setGenres(Arrays.asList("Progressive rock")); t1.setRatings(Arrays.asList(createRating(22.7, 1273)));

    Track t2 = new Track(); t2.set_id(getPinkFloydNextId()); t2.setArtist_id(Arrays.asList(artist)); t2.setLength(7.31); t2.setName("Welcome to the machine");
    t2.setGenres(Arrays.asList("Progressive rock", "Electronic rock")); t2.setRatings(Arrays.asList(createRating(5.3, 531)));

    Track t3 = new Track(); t3.set_id(getPinkFloydNextId()); t3.setArtist_id(Arrays.asList(artist)); t3.setLength(5.41); t3.setName("Wish you were here");
    t3.setGenres(Arrays.asList("Vocal")); t3.setRatings(Arrays.asList(createRating(33.1, 3310)));

    a1.setFormats(Arrays.asList("Vinyl", "Album")); a1.setName("Wish you were here"); a1.setReleaseYear(1975);
    a1.setTracks(Arrays.asList(t1, t2, t3)); a1.setAvailability(Arrays.asList("EN", "FR")); a1.setGenre("Progressive rock");
    a1.setReviews(Arrays.asList(
        createReview("Blender", "Excelent", "5/5", ""),
        createReview("Martin C. Strong", "Excelent", "10/10", "The Great Rock Discography"),
        createReview("Ben Edmonds", "Excelent", "5 stars", "The Rolling Stone Album Guide")));

    Track t4 = new Track(); t4.set_id(getPinkFloydNextId()); t4.setArtist_id(Arrays.asList(artist)); t4.setLength(3.09); t4.setName("Another Brick in the Wall (Part I)");
    t4.setGenres(Arrays.asList("Art rock", "Progressive rock", "Disco"));

    Track t5 = new Track(); t5.set_id(getPinkFloydNextId()); t5.setArtist_id(Arrays.asList(artist)); t5.setLength(2.45); t5.setName("Goodbye Blue Sky");
    t5.setGenres(Arrays.asList("Progressive rock"));

    Track t6 = new Track(); t6.set_id(getPinkFloydNextId()); t6.setArtist_id(Arrays.asList(artist)); t6.setLength(6.24); t6.setName("Comfortably Numb");
    t6.setGenres(Arrays.asList("Progressive rock")); t6.setRatings(Arrays.asList(createRating(45.5, 4550)));

    a2.setFormats(Arrays.asList("Vinyl", "Album")); a2.setName("The Wall"); a2.setReleaseYear(1979);
    a2.setTracks(Arrays.asList(t4, t5, t6)); a2.setAvailability(Arrays.asList("EN", "FR", "ES", "JP", "PT", "NE")); a2.setGenres(Arrays.asList("Art rock"));

    artist.setComposedTracks(Arrays.asList(t1, t2, t3, t4, t5, t6)); artist.setLyricsTracks(Arrays.asList(t1, t2, t3, t4, t5, t6)); artist.setAlbums(Arrays.asList(a1, a2));

    assertEquals(0, validator.validate(t1).size() + validator.validate(t2).size() + validator.validate(t3).size() + validator.validate(t4).size());
    assertEquals(0, validator.validate(t5).size() + validator.validate(t6).size() + validator.validate(a1).size() + validator.validate(a2).size() + validator.validate(artist).size());

    datastore.save(Arrays.asList(t1, t2, t3, t4, t5, t6));
    datastore.save(Arrays.asList(a1, a2));
    datastore.save(Arrays.asList(artist));
  }

  private void fillDbMassiveAttack()
  {
    Artist artist = new Artist(); artist.set_id(getMassiveAttackNextId()); artist.setName("Massive Attack"); artist.setStartingYear(1988);

    Album a1 = new Album(); a1.set_id(getMassiveAttackNextId());
    Album a2 = new Album(); a2.set_id(getMassiveAttackNextId());

    Track t1 = new Track(); t1.set_id(getMassiveAttackNextId()); t1.setArtist_id(Arrays.asList(artist)); t1.setLength(6.19); t1.setName("Angel");
    t1.setGenres(Arrays.asList("Trip hop", "Industrial rock")); t1.setRatings(Arrays.asList(createRating(18.0, 180)));

    Track t2 = new Track(); t2.set_id(getMassiveAttackNextId()); t2.setArtist_id(Arrays.asList(artist)); t2.setLength(5.31); t2.setName("Teardrop");
    t2.setGenres(Arrays.asList("Trip hop", "Downtempo")); t2.setRatings(Arrays.asList(createRating(19.0, 190)));

    Track t3 = new Track(); t3.set_id(getMassiveAttackNextId()); t3.setArtist_id(Arrays.asList(artist)); t3.setLength(6.06); t3.setName("Dissolved girl");
    t3.setGenres(Arrays.asList("Trip hop")); t3.setRatings(Arrays.asList(createRating(5.0, 50)));

    a1.setFormats(Arrays.asList("LP", "Album")); a1.setName("Mezzanine"); a1.setReleaseYear(1998);
    a1.setTracks(Arrays.asList(t1, t2, t3)); a1.setAvailability("ES, EN, FR"); a1.setGenre("Trip hop");
    a1.setReviews(Arrays.asList(
        createReview("Barney Hoskyns", "Very good", "3.5/5", "Rolling stone"),
        createReview("Alexis Petridis", "Excelent", 5, Arrays.asList(createMedia("The Guardian", "https://www.theguardian.com/us", "newspaper")))));
    a1.setPrizes(Arrays.asList(
        createPrize("RIAA", 70000, 1998, "Platinum disk", "Australia", null),
        createPrize("SNEP", 243000, 1998, null, null, Arrays.asList("France", "2x Gold disk"))));

    Track t4 = new Track(); t4.set_id(getMassiveAttackNextId()); t4.setArtist_id(Arrays.asList(artist)); t4.setLength(5.14); t4.setName("Karmacoma");
    t4.setGenres(Arrays.asList("Trip hop"));

    Track t5 = new Track(); t5.set_id(getMassiveAttackNextId()); t5.setArtist_id(Arrays.asList(artist)); t5.setLength(4.51); t5.setName("Live with me");
    t5.setGenres(Arrays.asList("Trip hop"));

    a2.setFormats(Arrays.asList("Album", "Vinyl")); a2.setName("Collected"); a2.setReleaseYear(2006);
    a2.setTracks(Arrays.asList(t4, t5, t1)); a2.setAvailability(Arrays.asList("EN", "JP")); a2.setGenres(Arrays.asList("Trip hop", "Electronica"));

    artist.setComposedTracks(Arrays.asList(t1, t2, t3, t4, t5)); artist.setAlbums(Arrays.asList(a1, a2));

    assertEquals(0, validator.validate(t1).size() + validator.validate(t2).size() + validator.validate(t3).size() + validator.validate(t4).size());
    assertEquals(0, validator.validate(t5).size() + validator.validate(a1).size() + validator.validate(a2).size() + validator.validate(artist).size());

    datastore.save(Arrays.asList(t1, t2, t3, t4, t5));
    datastore.save(Arrays.asList(a1, a2));
    datastore.save(Arrays.asList(artist));
  }

  private Rating createRating(Double score, Integer voters)
  {
    Rating rating = new Rating();
    rating.setScore(score);
    rating.setVoters(voters);

    return rating;
  }

  private Prize createPrize(String event, Integer units, Integer year, String certification, String name, List<String> names)
  {
    Prize prize = new Prize();
    prize.setEvent(event);
    prize.setUnits(units);
    prize.setYear(year);
    prize.setCertification(certification);
    prize.setName(name);
    prize.setNames(names);

    return prize;
  }

  private Review createReview(String journalist, String rank, Object stars, Object media)
  {
    Review review = new Review();
    review.setJournalist(journalist);
    review.setRank(rank);
    review.setStars(stars);
    review.setMedia(media);

    return review;
  }

  private Media createMedia(String name, String url, String type)
  {
    Media media = new Media();
    media.setName(name);
    media.setUrl(url);
    media.setType(type);

    return media;
  }

  public static void main(String[] args)
  {
    MongoSongsDataManagement manager = new MongoSongsDataManagement("localhost", "mongosongs_running");
    manager.startDemo();
  }
}