package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"price_amount", "price_currency_code", "term_code", "source_url", "source_description", "acquired_year", "acquired_month", "acquired_day", "acquiring_company"})
public class Acquisition
{
  private Double price_amount;

  private String price_currency_code;

  private String term_code;

  private String source_url;

  private String source_description;

  private Integer acquired_year;

  private Integer acquired_month;

  private Integer acquired_day;

  private NamePermalink acquiring_company;

  @JsonProperty("price_amount")
  public Double getPrice_amount() {
    return price_amount;
  }

  public void setPrice_amount(Double price_amount) {
    this.price_amount = price_amount;
  }

  @JsonProperty("price_currency_code")
  public String getPrice_currency_code() {
    return price_currency_code;
  }

  public void setPrice_currency_code(String price_currency_code) {
    this.price_currency_code = price_currency_code;
  }

  @JsonProperty("term_code")
  public String getTerm_code() {
    return term_code;
  }

  public void setTerm_code(String term_code) {
    this.term_code = term_code;
  }

  @JsonProperty("source_url")
  public String getSource_url() {
    return source_url;
  }

  public void setSource_url(String source_url) {
    this.source_url = source_url;
  }

  @JsonProperty("source_description")
  public String getSource_description() {
    return source_description;
  }

  public void setSource_description(String source_description) {
    this.source_description = source_description;
  }

  @JsonProperty("acquired_year")
  public Integer getAcquired_year() {
    return acquired_year;
  }

  public void setAcquired_year(Integer acquired_year) {
    this.acquired_year = acquired_year;
  }

  @JsonProperty("acquired_month")
  public Integer getAcquired_month() {
    return acquired_month;
  }

  public void setAcquired_month(Integer acquired_month) {
    this.acquired_month = acquired_month;
  }

  @JsonProperty("acquired_day")
  public Integer getAcquired_day() {
    return acquired_day;
  }

  public void setAcquired_day(Integer acquired_day) {
    this.acquired_day = acquired_day;
  }

  @JsonProperty("acquiring_company")
  public NamePermalink getAcquiring_company() {
    return acquiring_company;
  }

  public void setAcquiring_company(NamePermalink acquiring_company) {
    this.acquiring_company = acquiring_company;
  }

}