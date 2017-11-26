package es.um.nosql.schemainference.everypolitician;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Embedded
public class Other_name
{
  @Property
  @NotNull(message = "name can't be null")
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Property
  @NotNull(message = "note can't be null")
  private String note;
  public String getNote() {return this.note;}
  public void setNote(String note) {this.note = note;}
  
  @Property
  private String lang;
  public String getLang() {return this.lang;}
  public void setLang(String lang) {this.lang = lang;}
}
