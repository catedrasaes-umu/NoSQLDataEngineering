package es.um.nosql.s13e.mongosongs;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;
import java.util.List;


@Embedded
public class Prize
{
  @Property
  private String certification;
  public String getCertification() {return this.certification;}
  public void setCertification(String certification) {this.certification = certification;}
  
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
  private List<String> names;
  public List<String> getNames() {return this.names;}
  public void setNames(List<String> names) {this.names = names;}
  
  @Property
  @NotNull(message = "units can't be null")
  private Integer units;
  public Integer getUnits() {return this.units;}
  public void setUnits(Integer units) {this.units = units;}
  
  @Property
  @NotNull(message = "year can't be null")
  private Integer year;
  public Integer getYear() {return this.year;}
  public void setYear(Integer year) {this.year = year;}
}
