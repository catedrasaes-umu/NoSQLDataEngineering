package es.um.nosql.schemainference.db.pojo.webclick;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"count", "timestamp", "from", "to"})
public class Webclick
{
  private Integer count;

  private Integer timestamp;

  private String from;

  private String to;

  @JsonProperty("count")
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @JsonProperty("timestamp")
  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  @JsonProperty("from")
  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  @JsonProperty("to")
  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }
}