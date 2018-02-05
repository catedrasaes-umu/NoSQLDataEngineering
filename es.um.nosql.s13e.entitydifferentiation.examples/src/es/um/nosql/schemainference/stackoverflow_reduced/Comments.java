package es.um.nosql.s13e.stackoverflow_reduced;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "comments", noClassnameStored = true)
public class Comments
{
  @Property
  @NotNull(message = "CreationDate can't be null")
  private String CreationDate;
  public String getCreationDate() {return this.CreationDate;}
  public void setCreationDate(String CreationDate) {this.CreationDate = CreationDate;}
  
  @Reference(idOnly = true)
  @NotNull(message = "PostId can't be null")
  private Posts PostId;
  public Posts getPostId() {return this.PostId;}
  public void setPostId(Posts PostId) {this.PostId = PostId;}
  
  @Property
  @NotNull(message = "Score can't be null")
  private Integer Score;
  public Integer getScore() {return this.Score;}
  public void setScore(Integer Score) {this.Score = Score;}
  
  @Property
  @NotNull(message = "Text can't be null")
  private String Text;
  public String getText() {return this.Text;}
  public void setText(String Text) {this.Text = Text;}
  
  @Property
  private String UserDisplayName;
  public String getUserDisplayName() {return this.UserDisplayName;}
  public void setUserDisplayName(String UserDisplayName) {this.UserDisplayName = UserDisplayName;}
  
  @Reference(idOnly = true)
  private Users UserId;
  public Users getUserId() {return this.UserId;}
  public void setUserId(Users UserId) {this.UserId = UserId;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
}
