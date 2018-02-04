package es.um.nosql.schemainference.links;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "links", noClassnameStored = true)
public class Links
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "tags can't be null")
  private String[] tags;
  public String[] getTags() {return this.tags;}
  public void setTags(String[] tags) {this.tags = tags;}
  
  @Property
  @NotNull(message = "timestamp can't be null")
  private Integer timestamp;
  public Integer getTimestamp() {return this.timestamp;}
  public void setTimestamp(Integer timestamp) {this.timestamp = timestamp;}
  
  @Property
  @NotNull(message = "url can't be null")
  private String url;
  public String getUrl() {return this.url;}
  public void setUrl(String url) {this.url = url;}
}
