package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.mongoMovies3.Rating;
import es.um.nosql.schemainference.mongoMovies3.Prize;
import es.um.nosql.schemainference.mongoMovies3.Criticism;

@Entity(noClassnameStored = true)
public class Movie
{
  @Id
  private ObjectId _id;
  public ObjectId getObjectId() {return this._id;}
  public void setObjectId(ObjectId _id) {this._id = _id;}

  @Property
  @NotNull(message = "director_id can't be null")
  private String director_id;
  public String getDirector_id() {return this.director_id;}
  public void setDirector_id(String director_id) {this.director_id = director_id;}
  
  @Property
  @NotNull(message = "title can't be null")
  private String title;
  public String getTitle() {return this.title;}
  public void setTitle(String title) {this.title = title;}
  
  @Property
  @NotNull(message = "year can't be null")
  private Integer year;
  public Integer getYear() {return this.year;}
  public void setYear(Integer year) {this.year = year;}
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
  
  @Embedded
  private Prize[] prizes;
  public Prize[] getPrizes() {return this.prizes;}
  public void setPrizes(Prize[] prizes) {this.prizes = prizes;}
  
  @Property
  private String[] genres;
  public String[] getGenres() {return this.genres;}
  public void setGenres(String[] genres) {this.genres = genres;}
  
  @Embedded
  private Rating rating;
  public Rating getRating() {return this.rating;}
  public void setRating(Rating rating) {this.rating = rating;}
  
  @Property
  private String genre;
  public String getGenre() {return this.genre;}
  public void setGenre(String genre) {this.genre = genre;}
  
  @Property
  private Integer running_time;
  public Integer getRunning_time() {return this.running_time;}
  public void setRunning_time(Integer running_time) {this.running_time = running_time;}
  
  @Embedded
  private Criticism[] criticisms;
  public Criticism[] getCriticisms() {return this.criticisms;}
  public void setCriticisms(Criticism[] criticisms) {this.criticisms = criticisms;}
  
  @Property
  private String[] writers;
  public String[] getWriters() {return this.writers;}
  public void setWriters(String[] writers) {this.writers = writers;}
}
