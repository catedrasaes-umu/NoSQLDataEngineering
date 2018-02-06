package es.um.nosql.s13e.stackoverflow;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "badges", noClassnameStored = true)
public class Badges
{
  @Property(value = "Class")
  @NotNull(message = "Class can't be null")
  private Integer theClass;
  public Integer getTheClass() {return this.theClass;}
  public void setTheClass(Integer theClass) {this.theClass = theClass;}
  
  @Property
  @NotNull(message = "Date can't be null")
  private String Date;
  public String getDate() {return this.Date;}
  public void setDate(String Date) {this.Date = Date;}
  
  @Property
  @NotNull(message = "Name can't be null")
  private String Name;
  public String getName() {return this.Name;}
  public void setName(String Name) {this.Name = Name;}
  
  @Property
  @NotNull(message = "TagBased can't be null")
  private Boolean TagBased;
  public Boolean getTagBased() {return this.TagBased;}
  public void setTagBased(Boolean TagBased) {this.TagBased = TagBased;}
  
  @Reference(idOnly = true)
  @NotNull(message = "UserId can't be null")
  private Users UserId;
  public Users getUserId() {return this.UserId;}
  public void setUserId(Users UserId) {this.UserId = UserId;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
}
