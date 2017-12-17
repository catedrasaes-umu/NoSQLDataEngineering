package es.um.nosql.schemainference.test1;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "customDate4", noClassnameStored = true)
public class CustomDate4
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "date4 can't be null")
  private String date4;
  public String getDate4() {return this.date4;}
  public void setDate4(String date4) {this.date4 = date4;}
}
