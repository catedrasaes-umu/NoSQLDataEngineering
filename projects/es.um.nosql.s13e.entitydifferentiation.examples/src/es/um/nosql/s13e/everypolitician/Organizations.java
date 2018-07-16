package es.um.nosql.s13e.everypolitician;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;
import java.util.List;

import es.um.nosql.s13e.everypolitician.Identifier;
import es.um.nosql.s13e.everypolitician.Link;
import es.um.nosql.s13e.everypolitician.Other_name;

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
  private List<Identifier> identifiers;
  public List<Identifier> getIdentifiers() {return this.identifiers;}
  public void setIdentifiers(List<Identifier> identifiers) {this.identifiers = identifiers;}
  
  @Property
  private String image;
  public String getImage() {return this.image;}
  public void setImage(String image) {this.image = image;}
  
  @Embedded
  private List<Link> links;
  public List<Link> getLinks() {return this.links;}
  public void setLinks(List<Link> links) {this.links = links;}
  
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Embedded
  private List<Other_name> other_names;
  public List<Other_name> getOther_names() {return this.other_names;}
  public void setOther_names(List<Other_name> other_names) {this.other_names = other_names;}
  
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
