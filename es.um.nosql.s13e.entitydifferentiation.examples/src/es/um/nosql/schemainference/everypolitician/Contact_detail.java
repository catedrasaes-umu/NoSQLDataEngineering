package es.um.nosql.s13e.everypolitician;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Contact_detail
{
  @Property
  @NotNull(message = "type can't be null")
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
  
  @Property
  @NotNull(message = "value can't be null")
  private String value;
  public String getValue() {return this.value;}
  public void setValue(String value) {this.value = value;}
}
