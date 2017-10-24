package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video
{
  private String embed_code;

  private String description;

  @JsonProperty("embed_code")
  public String getEmbed_code() {
    return embed_code;
  }

  public void setEmbed_code(String embed_code) {
    this.embed_code = embed_code;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}