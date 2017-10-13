package es.um.nosql.schemainference.db.pojo.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"url", "timestamp", "tags"})
public class Link
{
  private String url;

  private Integer timestamp;

  private String[] tags;

  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @JsonProperty("timestamp")
  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  @JsonProperty("tags")
  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }
}