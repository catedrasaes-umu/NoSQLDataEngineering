package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "director", noClassnameStored = true)
public class Director
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId getObjectId() {return this._id;}
  public void setObjectId(ObjectId _id) {this._id = _id;}

  @Property
  private String[] actor_movies;
  public String[] getActor_movies() {return this.actor_movies;}
  public void setActor_movies(String[] actor_movies) {this.actor_movies = actor_movies;}
  
  @Property
  @NotNull(message = "directed_movies can't be null")
  private String[] directed_movies;
  public String[] getDirected_movies() {return this.directed_movies;}
  public void setDirected_movies(String[] directed_movies) {this.directed_movies = directed_movies;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
}
