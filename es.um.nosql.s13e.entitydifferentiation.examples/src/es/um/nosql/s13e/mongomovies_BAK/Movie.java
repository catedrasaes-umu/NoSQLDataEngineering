package es.um.nosql.s13e.mongomovies_BAK;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import es.um.nosql.s13e.mongomovies_BAK.Criticism;
import es.um.nosql.s13e.mongomovies_BAK.Prize;
import es.um.nosql.s13e.mongomovies_BAK.Rating;
import es.um.nosql.s13e.mongomovies_BAK.commons.Commons;

import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import com.mongodb.BasicDBList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

import javax.validation.constraints.NotNull;

@Entity(value = "movie", noClassnameStored = true)
public class Movie
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Embedded
  private List<Criticism> criticisms;
  public List<Criticism> getCriticisms() {return this.criticisms;}
  public void setCriticisms(List<Criticism> criticisms) {this.criticisms = criticisms;}
  
  @Reference(idOnly = true, lazy = true)
  @NotNull(message = "director_id can't be null")
  private Director director_id;
  public Director getDirector_id() {return this.director_id;}
  public void setDirector_id(Director director_id) {this.director_id = director_id;}
  
  @Property
  private String genre;
  public String getGenre() {return this.genre;}
  public void setGenre(String genre) {this.genre = genre;}
  
  @Property
  private List<String> genres;
  public List<String> getGenres() {return this.genres;}
  public void setGenres(List<String> genres) {this.genres = genres;}
  
  @Embedded
  private List<Prize> prizes;
  public List<Prize> getPrizes() {return this.prizes;}
  public void setPrizes(List<Prize> prizes) {this.prizes = prizes;}
  
  @Embedded
  private Rating rating;
  public Rating getRating() {return this.rating;}
  public void setRating(Rating rating) {this.rating = rating;}
  
  @Property
  private Integer running_time;
  public Integer getRunning_time() {return this.running_time;}
  public void setRunning_time(Integer running_time) {this.running_time = running_time;}
  
  @Property
  @NotNull(message = "title can't be null")
  private String title;
  public String getTitle() {return this.title;}
  public void setTitle(String title) {this.title = title;}
  
  @Property
  private List<String> writers;
  public List<String> getWriters() {return this.writers;}
  public void setWriters(List<String> writers) {this.writers = writers;}
  
  @Property
  @NotNull(message = "year can't be null")
  private Integer year;
  public Integer getYear() {return this.year;}
  public void setYear(Integer year) {this.year = year;}
}
