package es.um.nosql.schemainference.test1;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class CustomDate1
{
  @Property
  @NotNull(message = "date1 can't be null")
  private Integer date1;
  public Integer getDate1() {return this.date1;}
  public void setDate1(Integer date1) {this.date1 = date1;}
}
