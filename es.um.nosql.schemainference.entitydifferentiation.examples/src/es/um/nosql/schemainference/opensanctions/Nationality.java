package es.um.nosql.schemainference.opensanctions;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Nationality
{
  @Property
  @NotNull(message = "country can't be null")
  private String country;
  public String getCountry() {return this.country;}
  public void setCountry(String country) {this.country = country;}
  
  @Property
  private String country_code;
  public String getCountry_code() {return this.country_code;}
  public void setCountry_code(String country_code) {this.country_code = country_code;}
}
