package es.um.nosql.schemainference.db.pojo.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"page_name", "page_id"})
public class Page
{
  private String page_name;

  private String page_id;

  @JsonProperty("page_name")
  public String getPage_name() {
    return page_name;
  }

  public void setPage_name(String page_name) {
    this.page_name = page_name;
  }

  @JsonProperty("page_id")
  public String getPage_id() {
    return page_id;
  }

  public void setPage_id(String page_id) {
    this.page_id = page_id;
  }
}
