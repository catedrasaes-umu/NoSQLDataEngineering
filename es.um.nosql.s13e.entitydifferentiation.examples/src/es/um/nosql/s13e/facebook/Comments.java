package es.um.nosql.s13e.facebook;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "comments", noClassnameStored = true)
public class Comments
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  private String created_time;
  public String getCreated_time() {return this.created_time;}
  public void setCreated_time(String created_time) {this.created_time = created_time;}
  
  @Property
  private String from_id;
  public String getFrom_id() {return this.from_id;}
  public void setFrom_id(String from_id) {this.from_id = from_id;}
  
  @Property
  private String from_name;
  public String getFrom_name() {return this.from_name;}
  public void setFrom_name(String from_name) {this.from_name = from_name;}
  
  @Property
  private String message;
  public String getMessage() {return this.message;}
  public void setMessage(String message) {this.message = message;}
  
  @Reference(idOnly = true, lazy = true)
  private Posts post_id;
  public Posts getPost_id() {return this.post_id;}
  public void setPost_id(Posts post_id) {this.post_id = post_id;}
}
