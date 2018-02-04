package es.um.nosql.schemainference.everypolitician;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Identifier
{
  @Property
  @NotNull(message = "identifier can't be null")
  private String identifier;
  public String getIdentifier() {return this.identifier;}
  public void setIdentifier(String identifier) {this.identifier = identifier;}
  
  @Property
  @NotNull(message = "scheme can't be null")
  private String scheme;
  public String getScheme() {return this.scheme;}
  public void setScheme(String scheme) {this.scheme = scheme;}
}
