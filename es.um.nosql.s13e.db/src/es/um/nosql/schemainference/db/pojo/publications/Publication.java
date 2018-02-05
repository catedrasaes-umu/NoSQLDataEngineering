package es.um.nosql.s13e.db.pojo.publications;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"pmid", "article_title", "pub_year", "authors"})
public class Publication
{
  private Integer pmid;

  private String article_title;

  private Integer pub_year;

  private String authors;

  @JsonProperty("pmid")
  public Integer getPmid() {
    return pmid;
  }

  public void setPmid(Integer pmid) {
    this.pmid = pmid;
  }

  @JsonProperty("article_title")
  public String getArticle_title() {
    return article_title;
  }

  public void setArticle_title(String article_title) {
    this.article_title = article_title;
  }

  @JsonProperty("pub_year")
  public Integer getPub_year() {
    return pub_year;
  }

  public void setPub_year(Integer pub_year) {
    this.pub_year = pub_year;
  }

  @JsonProperty("authors")
  public String getAuthors() {
    return authors;
  }

  public void setAuthors(String authors) {
    this.authors = authors;
  }
}
