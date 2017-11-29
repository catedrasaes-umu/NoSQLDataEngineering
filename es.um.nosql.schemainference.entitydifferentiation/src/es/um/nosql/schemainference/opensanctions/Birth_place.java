package es.um.nosql.schemainference.opensanctions;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;


@Embedded
public class Birth_place
{
  @Property
  private String country;
  public String getCountry() {return this.country;}
  public void setCountry(String country) {this.country = country;}
  
  @Property
  private String country_code;
  public String getCountry_code() {return this.country_code;}
  public void setCountry_code(String country_code) {this.country_code = country_code;}
  
  @Property
  private String place;
  public String getPlace() {return this.place;}
  public void setPlace(String place) {this.place = place;}
  
  @Property
  private String quality;
  public String getQuality() {return this.quality;}
  public void setQuality(String quality) {this.quality = quality;}
}
