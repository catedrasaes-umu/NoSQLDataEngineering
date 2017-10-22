package es.um.nosql.schemainference.mongoMovies3;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
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
  private String[] names;
  public String[] getNames() {return this.names;}
  public void setNames(String[] names) {this.names = names;}
  
  @Property("name")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
}
