package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image
{
  private String attribution;

  private Object[][] available_sizes;

  @JsonProperty("attribution")
  public String getAttribution() {
    return attribution;
  }

  public void setAttribution(String attribution) {
    this.attribution = attribution;
  }

  @JsonProperty("available_sizes")
  public Object[][] getAvailable_sizes() {
    return available_sizes;
  }

  public void setAvailable_sizes(Object[][] available_sizes) {
    this.available_sizes = available_sizes;
  }
}