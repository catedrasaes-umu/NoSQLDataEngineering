package es.um.nosql.s13e.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"title", "is_past", "provider"})
public class Providership
{
  private String title;

  private Boolean is_past;

  private NamePermalink provider;

  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @JsonProperty("is_past")
  public Boolean getIs_past() {
    return is_past;
  }

  public void setIs_past(Boolean is_past) {
    this.is_past = is_past;
  }

  @JsonProperty("provider")
  public NamePermalink getProvider() {
    return provider;
  }

  public void setProvider(NamePermalink provider) {
    this.provider = provider;
  }
}
