package es.um.nosql.s13e.stackoverflow;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "posts", noClassnameStored = true)
public class Posts
{
  @Property
  private String AcceptedAnswerId;
  public String getAcceptedAnswerId() {return this.AcceptedAnswerId;}
  public void setAcceptedAnswerId(String AcceptedAnswerId) {this.AcceptedAnswerId = AcceptedAnswerId;}
  
  @Property
  private Integer AnswerCount;
  public Integer getAnswerCount() {return this.AnswerCount;}
  public void setAnswerCount(Integer AnswerCount) {this.AnswerCount = AnswerCount;}
  
  @Property
  @NotNull(message = "Body can't be null")
  private String Body;
  public String getBody() {return this.Body;}
  public void setBody(String Body) {this.Body = Body;}
  
  @Property
  private String ClosedDate;
  public String getClosedDate() {return this.ClosedDate;}
  public void setClosedDate(String ClosedDate) {this.ClosedDate = ClosedDate;}
  
  @Property
  @NotNull(message = "CommentCount can't be null")
  private String CommentCount;
  public String getCommentCount() {return this.CommentCount;}
  public void setCommentCount(String CommentCount) {this.CommentCount = CommentCount;}
  
  @Property
  private String CommunityOwnedDate;
  public String getCommunityOwnedDate() {return this.CommunityOwnedDate;}
  public void setCommunityOwnedDate(String CommunityOwnedDate) {this.CommunityOwnedDate = CommunityOwnedDate;}
  
  @Property
  @NotNull(message = "CreationDate can't be null")
  private String CreationDate;
  public String getCreationDate() {return this.CreationDate;}
  public void setCreationDate(String CreationDate) {this.CreationDate = CreationDate;}
  
  @Property
  private String FavoriteCount;
  public String getFavoriteCount() {return this.FavoriteCount;}
  public void setFavoriteCount(String FavoriteCount) {this.FavoriteCount = FavoriteCount;}
  
  @Property
  @NotNull(message = "LastActivityDate can't be null")
  private String LastActivityDate;
  public String getLastActivityDate() {return this.LastActivityDate;}
  public void setLastActivityDate(String LastActivityDate) {this.LastActivityDate = LastActivityDate;}
  
  @Property
  private String LastEditDate;
  public String getLastEditDate() {return this.LastEditDate;}
  public void setLastEditDate(String LastEditDate) {this.LastEditDate = LastEditDate;}
  
  @Property
  private String LastEditorDisplayName;
  public String getLastEditorDisplayName() {return this.LastEditorDisplayName;}
  public void setLastEditorDisplayName(String LastEditorDisplayName) {this.LastEditorDisplayName = LastEditorDisplayName;}
  
  @Reference(idOnly = true, lazy = true)
  private Users LastEditorUserId;
  public Users getLastEditorUserId() {return this.LastEditorUserId;}
  public void setLastEditorUserId(Users LastEditorUserId) {this.LastEditorUserId = LastEditorUserId;}
  
  @Property
  private String OwnerDisplayName;
  public String getOwnerDisplayName() {return this.OwnerDisplayName;}
  public void setOwnerDisplayName(String OwnerDisplayName) {this.OwnerDisplayName = OwnerDisplayName;}
  
  @Reference(idOnly = true, lazy = true)
  private Users OwnerUserId;
  public Users getOwnerUserId() {return this.OwnerUserId;}
  public void setOwnerUserId(Users OwnerUserId) {this.OwnerUserId = OwnerUserId;}
  
  @Property
  private String ParentId;
  public String getParentId() {return this.ParentId;}
  public void setParentId(String ParentId) {this.ParentId = ParentId;}
  
  @Property
  @NotNull(message = "PostTypeId can't be null")
  private Integer PostTypeId;
  public Integer getPostTypeId() {return this.PostTypeId;}
  public void setPostTypeId(Integer PostTypeId) {this.PostTypeId = PostTypeId;}
  
  @Property
  @NotNull(message = "Score can't be null")
  private Integer Score;
  public Integer getScore() {return this.Score;}
  public void setScore(Integer Score) {this.Score = Score;}
  
  @Reference(idOnly = true, lazy = true)
  private Tags Tags;
  public Tags getTags() {return this.Tags;}
  public void setTags(Tags Tags) {this.Tags = Tags;}
  
  @Property
  private String Title;
  public String getTitle() {return this.Title;}
  public void setTitle(String Title) {this.Title = Title;}
  
  @Property
  private String ViewCount;
  public String getViewCount() {return this.ViewCount;}
  public void setViewCount(String ViewCount) {this.ViewCount = ViewCount;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
}
