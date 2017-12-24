package es.um.nosql.schemainference.everypolitician;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import es.um.nosql.schemainference.everypolitician.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.everypolitician.Other_name;
import es.um.nosql.schemainference.everypolitician.Link;
import es.um.nosql.schemainference.everypolitician.Identifier;

@Entity(value = "organizations", noClassnameStored = true)
public class Organizations
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "classification can't be null")
  private String classification;
  public String getClassification() {return this.classification;}
  public void setClassification(String classification) {this.classification = classification;}
  
  @Embedded
  private Identifier[] identifiers;
  public Identifier[] getIdentifiers() {return this.identifiers;}
  public void setIdentifiers(Identifier[] identifiers) {this.identifiers = identifiers;}
  
  @Property
  private String image;
  public String getImage() {return this.image;}
  public void setImage(String image) {this.image = image;}
  
  @Embedded
  private Link[] links;
  public Link[] getLinks() {return this.links;}
  public void setLinks(Link[] links) {this.links = links;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Embedded
  private Other_name[] other_names;
  public Other_name[] getOther_names() {return this.other_names;}
  public void setOther_names(Other_name[] other_names) {this.other_names = other_names;}
  
  @Property
  private Integer seats;
  public Integer getSeats() {return this.seats;}
  public void setSeats(Integer seats) {this.seats = seats;}
  
  @Property
  private String srgb;
  public String getSrgb() {return this.srgb;}
  public void setSrgb(String srgb) {this.srgb = srgb;}
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
}
