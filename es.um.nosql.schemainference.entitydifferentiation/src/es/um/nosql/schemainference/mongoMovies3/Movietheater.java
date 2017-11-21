package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(noClassnameStored = true)
public class Movietheater
{
  @Id
  private ObjectId _id;
  public ObjectId getObjectId() {return this._id;}
  public void setObjectId(ObjectId _id) {this._id = _id;}

  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
  
  @Property
  @NotNull(message = "city can't be null")
  private String city;
  public String getCity() {return this.city;}
  public void setCity(String city) {this.city = city;}
  
  @Property
  @NotNull(message = "country can't be null")
  private String country;
  public String getCountry() {return this.country;}
  public void setCountry(String country) {this.country = country;}
  
  @Property
  private Integer noOfRooms;
  public Integer getNoOfRooms() {return this.noOfRooms;}
  public void setNoOfRooms(Integer noOfRooms) {this.noOfRooms = noOfRooms;}
}
