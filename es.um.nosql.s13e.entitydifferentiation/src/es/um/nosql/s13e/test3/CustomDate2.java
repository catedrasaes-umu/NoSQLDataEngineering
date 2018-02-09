package es.um.nosql.s13e.test3;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class CustomDate2
{
  @Property
  @NotNull(message = "date2 can't be null")
  private Boolean date2;
  public Boolean getDate2() {return this.date2;}
  public void setDate2(Boolean date2) {this.date2 = date2;}
}
