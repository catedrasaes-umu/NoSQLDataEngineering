package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "director", noClassnameStored = true)
public class Director
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Reference(idOnly = true)
  private Movie[] actor_movies;
  public Movie[] getActor_movies() {return this.actor_movies;}
  public void setActor_movies(Movie[] actor_movies) {this.actor_movies = actor_movies;}
  
  @Reference(idOnly = true)
  @NotNull(message = "directed_movies can't be null")
  private Movie[] directed_movies;
  public Movie[] getDirected_movies() {return this.directed_movies;}
  public void setDirected_movies(Movie[] directed_movies) {this.directed_movies = directed_movies;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  @NotNull(message = "type can't be null")
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
}
