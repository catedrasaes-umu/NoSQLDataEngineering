package es.um.nosql.s13e.test3;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "customDate1", noClassnameStored = true)
public class CustomDate1
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "date1 can't be null")
  private Integer date1;
  public Integer getDate1() {return this.date1;}
  public void setDate1(Integer date1) {this.date1 = date1;}
}
