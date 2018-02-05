package es.um.nosql.s13e.everypolitician;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Link
{
  @Property
  @NotNull(message = "note can't be null")
  private String note;
  public String getNote() {return this.note;}
  public void setNote(String note) {this.note = note;}
  
  @Property
  @NotNull(message = "url can't be null")
  private String url;
  public String getUrl() {return this.url;}
  public void setUrl(String url) {this.url = url;}
}
