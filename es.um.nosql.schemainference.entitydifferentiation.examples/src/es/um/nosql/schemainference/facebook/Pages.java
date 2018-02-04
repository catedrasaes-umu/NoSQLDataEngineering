package es.um.nosql.schemainference.facebook;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "pages", noClassnameStored = true)
public class Pages
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "page_name can't be null")
  private String page_name;
  public String getPage_name() {return this.page_name;}
  public void setPage_name(String page_name) {this.page_name = page_name;}
}
