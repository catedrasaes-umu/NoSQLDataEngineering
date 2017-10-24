package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"external_url", "title"})
public class External_link
{
  private String external_url;

  private String title;

  @JsonProperty("external_url")
  public String getExternal_url() {
    return external_url;
  }

  public void setExternal_url(String external_url) {
    this.external_url = external_url;
  }

  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}