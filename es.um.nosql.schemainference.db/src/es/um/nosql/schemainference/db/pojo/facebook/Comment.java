package es.um.nosql.schemainference.db.pojo.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"created_time", "from_id", "from_name", "message", "post_id"})
public class Comment
{
  private String created_time;

  private String from_id;

  private String from_name;

  private String message;

  private String post_id;

  @JsonProperty("created_time")
  public String getCreated_time() {
    return created_time;
  }

  public void setCreated_time(String created_time) {
    this.created_time = created_time;
  }

  @JsonProperty("from_id")
  public String getFrom_id() {
    return from_id;
  }

  public void setFrom_id(String from_id) {
    this.from_id = from_id;
  }

  @JsonProperty("from_name")
  public String getFrom_name() {
    return from_name;
  }

  public void setFrom_name(String from_name) {
    this.from_name = from_name;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @JsonProperty("post_id")
  public String getPost_id() {
    return post_id;
  }

  public void setPost_id(String post_id) {
    this.post_id = post_id;
  }
}
