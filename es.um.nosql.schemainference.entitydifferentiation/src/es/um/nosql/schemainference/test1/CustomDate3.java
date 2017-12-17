package es.um.nosql.schemainference.test1;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class CustomDate3
{
  @Property
  @NotNull(message = "date3 can't be null")
  private String date3;
  public String getDate3() {return this.date3;}
  public void setDate3(String date3) {this.date3 = date3;}
}
