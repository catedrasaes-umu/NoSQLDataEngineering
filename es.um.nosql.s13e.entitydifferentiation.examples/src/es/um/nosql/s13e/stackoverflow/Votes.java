package es.um.nosql.s13e.stackoverflow;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.PreLoad;
import com.mongodb.DBObject;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "votes", noClassnameStored = true)
public class Votes
{
  @Property
  private Integer BountyAmount;
  public Integer getBountyAmount() {return this.BountyAmount;}
  public void setBountyAmount(Integer BountyAmount) {this.BountyAmount = BountyAmount;}
  
  @Property
  @NotNull(message = "CreationDate can't be null")
  private String CreationDate;
  public String getCreationDate() {return this.CreationDate;}
  public void setCreationDate(String CreationDate) {this.CreationDate = CreationDate;}
  
  @Reference(idOnly = true, lazy = true)
  @NotNull(message = "PostId can't be null")
  private Posts PostId;
  public Posts getPostId() {return this.PostId;}
  public void setPostId(Posts PostId) {this.PostId = PostId;}
  
  @Reference(idOnly = true, lazy = true)
  private Users UserId;
  public Users getUserId() {return this.UserId;}
  public void setUserId(Users UserId) {this.UserId = UserId;}
  
  @Reference(idOnly = true, lazy = true)
  private Votes VoteTypeId;
  public Votes getVoteTypeId() {return this.VoteTypeId;}
  public void setVoteTypeId(Votes VoteTypeId) {this.VoteTypeId = VoteTypeId;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
}
