package es.um.nosql.schemainference.facebook;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "posts", noClassnameStored = true)
public class Posts
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "created_time can't be null")
  private String created_time;
  public String getCreated_time() {return this.created_time;}
  public void setCreated_time(String created_time) {this.created_time = created_time;}
  
  @Property
  private String description;
  public String getDescription() {return this.description;}
  public void setDescription(String description) {this.description = description;}
  
  @Property
  private String link;
  public String getLink() {return this.link;}
  public void setLink(String link) {this.link = link;}
  
  @Property
  private String message;
  public String getMessage() {return this.message;}
  public void setMessage(String message) {this.message = message;}
  
  @Reference(idOnly = true)
  @NotNull(message = "page_id can't be null")
  private Pages page_id;
  public Pages getPage_id() {return this.page_id;}
  public void setPage_id(Pages page_id) {this.page_id = page_id;}
  
  @Property
  @NotNull(message = "react_angry can't be null")
  private Integer react_angry;
  public Integer getReact_angry() {return this.react_angry;}
  public void setReact_angry(Integer react_angry) {this.react_angry = react_angry;}
  
  @Property
  @NotNull(message = "react_haha can't be null")
  private Integer react_haha;
  public Integer getReact_haha() {return this.react_haha;}
  public void setReact_haha(Integer react_haha) {this.react_haha = react_haha;}
  
  @Property
  @NotNull(message = "react_like can't be null")
  private Integer react_like;
  public Integer getReact_like() {return this.react_like;}
  public void setReact_like(Integer react_like) {this.react_like = react_like;}
  
  @Property
  @NotNull(message = "react_love can't be null")
  private Integer react_love;
  public Integer getReact_love() {return this.react_love;}
  public void setReact_love(Integer react_love) {this.react_love = react_love;}
  
  @Property
  @NotNull(message = "react_sad can't be null")
  private Integer react_sad;
  public Integer getReact_sad() {return this.react_sad;}
  public void setReact_sad(Integer react_sad) {this.react_sad = react_sad;}
  
  @Property
  @NotNull(message = "react_wow can't be null")
  private Integer react_wow;
  public Integer getReact_wow() {return this.react_wow;}
  public void setReact_wow(Integer react_wow) {this.react_wow = react_wow;}
  
  @Property
  @NotNull(message = "scrape_time can't be null")
  private String scrape_time;
  public String getScrape_time() {return this.scrape_time;}
  public void setScrape_time(String scrape_time) {this.scrape_time = scrape_time;}
  
  @Property
  @NotNull(message = "shares can't be null")
  private Integer shares;
  public Integer getShares() {return this.shares;}
  public void setShares(Integer shares) {this.shares = shares;}
}
