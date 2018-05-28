package es.um.nosql.s13e.mongosongs;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;
import java.util.List;

import es.um.nosql.s13e.mongosongs.Rating;

@Entity(value = "track", noClassnameStored = true)
public class Track
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Reference(idOnly = true, lazy = true)
  @NotNull(message = "artist_id can't be null")
  private List<Artist> artist_id;
  public List<Artist> getArtist_id() {return this.artist_id;}
  public void setArtist_id(List<Artist> artist_id) {this.artist_id = artist_id;}
  
  @Property
  @NotNull(message = "genres can't be null")
  private List<String> genres;
  public List<String> getGenres() {return this.genres;}
  public void setGenres(List<String> genres) {this.genres = genres;}
  
  @Property
  @NotNull(message = "length can't be null")
  private Double length;
  public Double getLength() {return this.length;}
  public void setLength(Double length) {this.length = length;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  @NotNull(message = "popularity can't be null")
  private Double popularity;
  public Double getPopularity() {return this.popularity;}
  public void setPopularity(Double popularity) {this.popularity = popularity;}
  
  @Embedded
  private List<Rating> ratings;
  public List<Rating> getRatings() {return this.ratings;}
  public void setRatings(List<Rating> ratings) {this.ratings = ratings;}
}
