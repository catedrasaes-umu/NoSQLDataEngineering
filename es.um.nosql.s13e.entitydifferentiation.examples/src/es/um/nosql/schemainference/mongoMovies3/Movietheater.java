package es.um.nosql.s13e.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "movietheater", noClassnameStored = true)
public class Movietheater
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
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
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  private Integer noOfRooms;
  public Integer getNoOfRooms() {return this.noOfRooms;}
  public void setNoOfRooms(Integer noOfRooms) {this.noOfRooms = noOfRooms;}
}
