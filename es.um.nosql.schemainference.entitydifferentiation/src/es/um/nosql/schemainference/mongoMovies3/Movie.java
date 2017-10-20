package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.mongoMovies3.Prize;
import es.um.nosql.schemainference.mongoMovies3.Rating;
import es.um.nosql.schemainference.mongoMovies3.Criticism;

@Entity(noClassnameStored = true)
public class Movie
{
  
  @Property("title")
  @NotNull(message = "title can't be null")
  private String title;
  public String getTitle() {return this.title;}
  public void setTitle(String title) {this.title = title;}
  
  @Property("year")
  @NotNull(message = "year can't be null")
  private Integer year;
  public Integer getYear() {return this.year;}
  public void setYear(Integer year) {this.year = year;}
  
  @Property("type")
  @NotNull(message = "type can't be null")
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
  
  @Property("writers")
  private  writers;
  public  getWriters() {return this.writers;}
  public void setWriters( writers) {this.writers = writers;}
  
  @Property("genre")
  private String genre;
  public String getGenre() {return this.genre;}
  public void setGenre(String genre) {this.genre = genre;}
  
  
  @Property("running_time")
  private Integer running_time;
  public Integer getRunning_time() {return this.running_time;}
  public void setRunning_time(Integer running_time) {this.running_time = running_time;}
  
  
  
  @Property("genres")
  private  genres;
  public  getGenres() {return this.genres;}
  public void setGenres( genres) {this.genres = genres;}
}
