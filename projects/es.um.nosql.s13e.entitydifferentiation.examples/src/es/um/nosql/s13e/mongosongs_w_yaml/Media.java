package es.um.nosql.s13e.mongosongs_w_yaml;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Media
{
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  @NotNull(message = "type can't be null")
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
  
  @Property
  @NotNull(message = "url can't be null")
  private String url;
  public String getUrl() {return this.url;}
  public void setUrl(String url) {this.url = url;}
}
