package es.um.nosql.s13e.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Office
{
  private String description;

  private String address1;

  private String address2;

  private String zip_code;

  private String city;

  private String state_code;

  private String country_code;

  private Double latitude;

  private Double longitude;

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("address1")
  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  @JsonProperty("address2")
  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  @JsonProperty("zip_code")
  public String getZip_code() {
    return zip_code;
  }

  public void setZip_code(String zip_code) {
    this.zip_code = zip_code;
  }

  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @JsonProperty("state_code")
  public String getState_code() {
    return state_code;
  }

  public void setState_code(String state_code) {
    this.state_code = state_code;
  }

  @JsonProperty("country_code")
  public String getCountry_code() {
    return country_code;
  }

  public void setCountry_code(String country_code) {
    this.country_code = country_code;
  }

  @JsonProperty("latitude")
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  @JsonProperty("longitude")
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
}