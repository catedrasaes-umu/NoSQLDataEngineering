package es.um.nosql.s13e.db.pojo.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"created_time", "description", "link", "message", "page_id", "post_id", "react_angry",
  "react_haha", "react_like", "react_love", "react_sad", "react_wow", "scrape_time", "shares"})
public class Post
{
  private String created_time;

  private String description;

  private String link;

  private String message;

  private String page_id;

  private String post_id;

  private Integer react_angry;

  private Integer react_haha;

  private Integer react_like;

  private Integer react_love;

  private Integer react_sad;

  private Integer react_wow;

  private String scrape_time;

  private Integer shares;

  @JsonProperty("created_time")
  public String getCreated_time() {
    return created_time;
  }

  public void setCreated_time(String created_time) {
    this.created_time = created_time;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("link")
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @JsonProperty("page_id")
  public String getPage_id() {
    return page_id;
  }

  public void setPage_id(String page_id) {
    this.page_id = page_id;
  }

  @JsonProperty("post_id")
  public String getPost_id() {
    return post_id;
  }

  public void setPost_id(String post_id) {
    this.post_id = post_id;
  }

  @JsonProperty("react_angry")
  public Integer getReact_angry() {
    return react_angry;
  }

  public void setReact_angry(Integer react_angry) {
    this.react_angry = react_angry;
  }

  @JsonProperty("react_haha")
  public Integer getReact_haha() {
    return react_haha;
  }

  public void setReact_haha(Integer react_haha) {
    this.react_haha = react_haha;
  }

  @JsonProperty("react_like")
  public Integer getReact_like() {
    return react_like;
  }

  public void setReact_like(Integer react_like) {
    this.react_like = react_like;
  }

  @JsonProperty("react_love")
  public Integer getReact_love() {
    return react_love;
  }

  public void setReact_love(Integer react_love) {
    this.react_love = react_love;
  }

  @JsonProperty("react_sad")
  public Integer getReact_sad() {
    return react_sad;
  }

  public void setReact_sad(Integer react_sad) {
    this.react_sad = react_sad;
  }

  @JsonProperty("react_wow")
  public Integer getReact_wow() {
    return react_wow;
  }

  public void setReact_wow(Integer react_wow) {
    this.react_wow = react_wow;
  }

  @JsonProperty("scrape_time")
  public String getScrape_time() {
    return scrape_time;
  }

  public void setScrape_time(String scrape_time) {
    this.scrape_time = scrape_time;
  }

  @JsonProperty("shares")
  public Integer getShares() {
    return shares;
  }

  public void setShares(Integer shares) {
    this.shares = shares;
  }
}
