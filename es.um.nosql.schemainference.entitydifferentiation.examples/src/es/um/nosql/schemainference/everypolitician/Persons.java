package es.um.nosql.schemainference.everypolitician;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import es.um.nosql.schemainference.everypolitician.commons.Commons;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import com.mongodb.BasicDBList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;

import es.um.nosql.schemainference.everypolitician.Contact_detail;
import es.um.nosql.schemainference.everypolitician.Identifier;
import es.um.nosql.schemainference.everypolitician.Image;
import es.um.nosql.schemainference.everypolitician.Other_name;
import es.um.nosql.schemainference.everypolitician.Link;

@Entity(value = "persons", noClassnameStored = true)
public class Persons
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "birth_date can't be null")
  private String birth_date;
  public String getBirth_date() {return this.birth_date;}
  public void setBirth_date(String birth_date) {this.birth_date = birth_date;}
  
  @Embedded
  private Contact_detail[] contact_details;
  public Contact_detail[] getContact_details() {return this.contact_details;}
  public void setContact_details(Contact_detail[] contact_details) {this.contact_details = contact_details;}
  
  @Property
  private String death_date;
  public String getDeath_date() {return this.death_date;}
  public void setDeath_date(String death_date) {this.death_date = death_date;}
  
  @Property
  private String email;
  public String getEmail() {return this.email;}
  public void setEmail(String email) {this.email = email;}
  
  @Property
  @NotNull(message = "family_name can't be null")
  private String family_name;
  public String getFamily_name() {return this.family_name;}
  public void setFamily_name(String family_name) {this.family_name = family_name;}
  
  @Property
  @NotNull(message = "gender can't be null")
  private String gender;
  public String getGender() {return this.gender;}
  public void setGender(String gender) {this.gender = gender;}
  
  @Property
  @NotNull(message = "given_name can't be null")
  private String given_name;
  public String getGiven_name() {return this.given_name;}
  public void setGiven_name(String given_name) {this.given_name = given_name;}
  
  @Embedded
  @NotNull(message = "identifiers can't be null")
  private Identifier[] identifiers;
  public Identifier[] getIdentifiers() {return this.identifiers;}
  public void setIdentifiers(Identifier[] identifiers) {this.identifiers = identifiers;}
  
  @Property
  @NotNull(message = "image can't be null")
  private String image;
  public String getImage() {return this.image;}
  public void setImage(String image) {this.image = image;}
  
  @Embedded
  @NotNull(message = "images can't be null")
  private Image[] images;
  public Image[] getImages() {return this.images;}
  public void setImages(Image[] images) {this.images = images;}
  
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
  @NotNull(message = "sort_name can't be null")
  private String sort_name;
  public String getSort_name() {return this.sort_name;}
  public void setSort_name(String sort_name) {this.sort_name = sort_name;}
}
