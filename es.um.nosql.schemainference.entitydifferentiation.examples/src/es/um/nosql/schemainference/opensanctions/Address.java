package es.um.nosql.schemainference.opensanctions;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;


@Embedded
public class Address
{
  @Property
  private String city;
  public String getCity() {return this.city;}
  public void setCity(String city) {this.city = city;}
  
  @Property
  private String country;
  public String getCountry() {return this.country;}
  public void setCountry(String country) {this.country = country;}
  
  @Property
  private String country_code;
  public String getCountry_code() {return this.country_code;}
  public void setCountry_code(String country_code) {this.country_code = country_code;}
  
  @Property
  private String note;
  public String getNote() {return this.note;}
  public void setNote(String note) {this.note = note;}
  
  @Property
  private String postal_code;
  public String getPostal_code() {return this.postal_code;}
  public void setPostal_code(String postal_code) {this.postal_code = postal_code;}
  
  @Property
  private String region;
  public String getRegion() {return this.region;}
  public void setRegion(String region) {this.region = region;}
  
  @Property
  private String street;
  public String getStreet() {return this.street;}
  public void setStreet(String street) {this.street = street;}
  
  @Property
  private String street_2;
  public String getStreet_2() {return this.street_2;}
  public void setStreet_2(String street_2) {this.street_2 = street_2;}
  
  @Property
  private String text;
  public String getText() {return this.text;}
  public void setText(String text) {this.text = text;}
}
