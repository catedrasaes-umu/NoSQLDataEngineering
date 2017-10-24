package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Partner
{
  private String partner_name;

  private String homepage_url;

  private String link_1_url;

  private String link_2_url;

  private String link_3_url;

  private String link_1_name;

  private String link_2_name;

  private String link_3_name;

  @JsonProperty("partner_name")
  public String getPartner_name() {
    return partner_name;
  }

  public void setPartner_name(String partner_name) {
    this.partner_name = partner_name;
  }

  @JsonProperty("homepage_url")
  public String getHomepage_url() {
    return homepage_url;
  }

  public void setHomepage_url(String homepage_url) {
    this.homepage_url = homepage_url;
  }

  @JsonProperty("link_1_url")
  public String getLink_1_url() {
    return link_1_url;
  }

  public void setLink_1_url(String link_1_url) {
    this.link_1_url = link_1_url;
  }

  @JsonProperty("link_2_url")
  public String getLink_2_url() {
    return link_2_url;
  }

  public void setLink_2_url(String link_2_url) {
    this.link_2_url = link_2_url;
  }

  @JsonProperty("link_3_url")
  public String getLink_3_url() {
    return link_3_url;
  }

  public void setLink_3_url(String link_3_url) {
    this.link_3_url = link_3_url;
  }

  @JsonProperty("link_1_name")
  public String getLink_1_name() {
    return link_1_name;
  }

  public void setLink_1_name(String link_1_name) {
    this.link_1_name = link_1_name;
  }

  @JsonProperty("link_2_name")
  public String getLink_2_name() {
    return link_2_name;
  }

  public void setLink_2_name(String link_2_name) {
    this.link_2_name = link_2_name;
  }

  @JsonProperty("link_3_name")
  public String getLink_3_name() {
    return link_3_name;
  }

  public void setLink_3_name(String link_3_name) {
    this.link_3_name = link_3_name;
  }
}