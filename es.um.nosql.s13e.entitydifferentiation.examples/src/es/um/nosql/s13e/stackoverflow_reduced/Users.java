package es.um.nosql.s13e.stackoverflow_reduced;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "users", noClassnameStored = true)
public class Users
{
  @Property
  @NotNull(message = "AboutMe can't be null")
  private String AboutMe;
  public String getAboutMe() {return this.AboutMe;}
  public void setAboutMe(String AboutMe) {this.AboutMe = AboutMe;}
  
  @Property
  @NotNull(message = "AccountId can't be null")
  private Integer AccountId;
  public Integer getAccountId() {return this.AccountId;}
  public void setAccountId(Integer AccountId) {this.AccountId = AccountId;}
  
  @Property
  @NotNull(message = "Age can't be null")
  private Integer Age;
  public Integer getAge() {return this.Age;}
  public void setAge(Integer Age) {this.Age = Age;}
  
  @Property
  @NotNull(message = "CreationDate can't be null")
  private String CreationDate;
  public String getCreationDate() {return this.CreationDate;}
  public void setCreationDate(String CreationDate) {this.CreationDate = CreationDate;}
  
  @Property
  @NotNull(message = "DisplayName can't be null")
  private String DisplayName;
  public String getDisplayName() {return this.DisplayName;}
  public void setDisplayName(String DisplayName) {this.DisplayName = DisplayName;}
  
  @Property
  @NotNull(message = "DownVotes can't be null")
  private Integer DownVotes;
  public Integer getDownVotes() {return this.DownVotes;}
  public void setDownVotes(Integer DownVotes) {this.DownVotes = DownVotes;}
  
  @Property
  @NotNull(message = "LastAccessDate can't be null")
  private String LastAccessDate;
  public String getLastAccessDate() {return this.LastAccessDate;}
  public void setLastAccessDate(String LastAccessDate) {this.LastAccessDate = LastAccessDate;}
  
  @Property
  private String Location;
  public String getLocation() {return this.Location;}
  public void setLocation(String Location) {this.Location = Location;}
  
  @Property
  private String ProfileImageUrl;
  public String getProfileImageUrl() {return this.ProfileImageUrl;}
  public void setProfileImageUrl(String ProfileImageUrl) {this.ProfileImageUrl = ProfileImageUrl;}
  
  @Property
  @NotNull(message = "Reputation can't be null")
  private Integer Reputation;
  public Integer getReputation() {return this.Reputation;}
  public void setReputation(Integer Reputation) {this.Reputation = Reputation;}
  
  @Property
  @NotNull(message = "UpVotes can't be null")
  private Integer UpVotes;
  public Integer getUpVotes() {return this.UpVotes;}
  public void setUpVotes(Integer UpVotes) {this.UpVotes = UpVotes;}
  
  @Property
  @NotNull(message = "Views can't be null")
  private String Views;
  public String getViews() {return this.Views;}
  public void setViews(String Views) {this.Views = Views;}
  
  @Property
  private String WebsiteUrl;
  public String getWebsiteUrl() {return this.WebsiteUrl;}
  public void setWebsiteUrl(String WebsiteUrl) {this.WebsiteUrl = WebsiteUrl;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
}
