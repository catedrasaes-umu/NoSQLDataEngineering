package es.um.nosql.schemainference.test1;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "customDate3", noClassnameStored = true)
public class CustomDate3
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "date3 can't be null")
  private String date3;
  public String getDate3() {return this.date3;}
  public void setDate3(String date3) {this.date3 = date3;}
}
