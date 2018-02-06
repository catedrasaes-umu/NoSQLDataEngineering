package es.um.nosql.s13e.stackoverflow;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "postlinks", noClassnameStored = true)
public class Postlinks
{
  @Property
  @NotNull(message = "CreationDate can't be null")
  private String CreationDate;
  public String getCreationDate() {return this.CreationDate;}
  public void setCreationDate(String CreationDate) {this.CreationDate = CreationDate;}
  
  @Property
  @NotNull(message = "LinkTypeId can't be null")
  private Integer LinkTypeId;
  public Integer getLinkTypeId() {return this.LinkTypeId;}
  public void setLinkTypeId(Integer LinkTypeId) {this.LinkTypeId = LinkTypeId;}
  
  @Reference(idOnly = true)
  @NotNull(message = "PostId can't be null")
  private Posts PostId;
  public Posts getPostId() {return this.PostId;}
  public void setPostId(Posts PostId) {this.PostId = PostId;}
  
  @Reference(idOnly = true)
  @NotNull(message = "RelatedPostId can't be null")
  private Posts RelatedPostId;
  public Posts getRelatedPostId() {return this.RelatedPostId;}
  public void setRelatedPostId(Posts RelatedPostId) {this.RelatedPostId = RelatedPostId;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
}
