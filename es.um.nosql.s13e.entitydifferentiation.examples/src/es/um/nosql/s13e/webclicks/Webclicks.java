package es.um.nosql.s13e.webclicks;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "webclicks", noClassnameStored = true)
public class Webclicks
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "count can't be null")
  private Integer count;
  public Integer getCount() {return this.count;}
  public void setCount(Integer count) {this.count = count;}
  
  @Property
  private String from;
  public String getFrom() {return this.from;}
  public void setFrom(String from) {this.from = from;}
  
  @Property
  @NotNull(message = "timestamp can't be null")
  private Integer timestamp;
  public Integer getTimestamp() {return this.timestamp;}
  public void setTimestamp(Integer timestamp) {this.timestamp = timestamp;}
  
  @Property
  @NotNull(message = "to can't be null")
  private String to;
  public String getTo() {return this.to;}
  public void setTo(String to) {this.to = to;}
}
