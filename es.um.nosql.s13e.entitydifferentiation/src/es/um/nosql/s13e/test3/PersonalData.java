package es.um.nosql.s13e.test3;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class PersonalData
{
  @Property
  @NotNull(message = "Age can't be null")
  private Integer Age;
  public Integer getAge() {return this.Age;}
  public void setAge(Integer Age) {this.Age = Age;}
  
  @Property
  @NotNull(message = "Name can't be null")
  private String Name;
  public String getName() {return this.Name;}
  public void setName(String Name) {this.Name = Name;}
}
