package es.um.nosql.s13e.mongoMovies3;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Prize
{
  @Property
  @NotNull(message = "event can't be null")
  private String event;
  public String getEvent() {return this.event;}
  public void setEvent(String event) {this.event = event;}
  
  @Property
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  private Object[] names;
  public Object[] getNames() {return this.names;}
  public void setNames(Object[] names) {this.names = names;}
  
  @Property
  @NotNull(message = "year can't be null")
  private Integer year;
  public Integer getYear() {return this.year;}
  public void setYear(Integer year) {this.year = year;}
}
