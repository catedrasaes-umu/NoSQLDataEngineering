package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(noClassnameStored = true)
public class Prize
{
  @Property("event")
  @NotNull(message = "event can't be null")
  private String event;
  public String getEvent() {return this.event;}
  public void setEvent(String event) {this.event = event;}
  
  @Property("year")
  @NotNull(message = "year can't be null")
  private Integer year;
  public Integer getYear() {return this.year;}
  public void setYear(Integer year) {this.year = year;}
  
  @Property("names")
  private  names;
  public  getNames() {return this.names;}
  public void setNames( names) {this.names = names;}
  
  @Property("name")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
}
