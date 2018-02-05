package es.um.nosql.s13e.stackoverflow_reduced;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;


@Entity(value = "tags", noClassnameStored = true)
public class Tags
{
  @Property
  @NotNull(message = "Count can't be null")
  private Integer Count;
  public Integer getCount() {return this.Count;}
  public void setCount(Integer Count) {this.Count = Count;}
  
  @Reference(idOnly = true)
  private Posts ExcerptPostId;
  public Posts getExcerptPostId() {return this.ExcerptPostId;}
  public void setExcerptPostId(Posts ExcerptPostId) {this.ExcerptPostId = ExcerptPostId;}
  
  @Property
  @NotNull(message = "TagName can't be null")
  private String TagName;
  public String getTagName() {return this.TagName;}
  public void setTagName(String TagName) {this.TagName = TagName;}
  
  @Reference(idOnly = true)
  private Posts WikiPostId;
  public Posts getWikiPostId() {return this.WikiPostId;}
  public void setWikiPostId(Posts WikiPostId) {this.WikiPostId = WikiPostId;}
  
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
}
