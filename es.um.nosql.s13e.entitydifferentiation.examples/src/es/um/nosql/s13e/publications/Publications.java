package es.um.nosql.s13e.publications;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;


@Entity(value = "publications", noClassnameStored = true)
public class Publications
{
  @Id
  @NotNull(message = "_id can't be null")
  private ObjectId _id;
  public ObjectId get_id() {return this._id;}
  public void set_id(ObjectId _id) {this._id = _id;}
  
  @Property
  @NotNull(message = "article_title can't be null")
  private String article_title;
  public String getArticle_title() {return this.article_title;}
  public void setArticle_title(String article_title) {this.article_title = article_title;}
  
  @Property
  private String authors;
  public String getAuthors() {return this.authors;}
  public void setAuthors(String authors) {this.authors = authors;}
  
  @Property
  @NotNull(message = "pmid can't be null")
  private Integer pmid;
  public Integer getPmid() {return this.pmid;}
  public void setPmid(Integer pmid) {this.pmid = pmid;}
  
  @Property
  private Integer pub_year;
  public Integer getPub_year() {return this.pub_year;}
  public void setPub_year(Integer pub_year) {this.pub_year = pub_year;}
}
