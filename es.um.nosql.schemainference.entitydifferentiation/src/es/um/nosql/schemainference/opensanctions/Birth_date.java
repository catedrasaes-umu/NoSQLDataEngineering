package es.um.nosql.schemainference.opensanctions;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;


@Embedded
public class Birth_date
{
  @Property
  private String date;
  public String getDate() {return this.date;}
  public void setDate(String date) {this.date = date;}
  
  @Property
  private String quality;
  public String getQuality() {return this.quality;}
  public void setQuality(String quality) {this.quality = quality;}
}
