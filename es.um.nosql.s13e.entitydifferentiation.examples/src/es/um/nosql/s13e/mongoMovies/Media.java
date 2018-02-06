package es.um.nosql.s13e.mongoMovies;

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
  @NotNull(message = "url can't be null")
  private String url;
  public String getUrl() {return this.url;}
  public void setUrl(String url) {this.url = url;}
}
