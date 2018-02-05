package es.um.nosql.s13e.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "round_code", "source_url", "source_description", "raised_amount", "raised_currency_code", "funded_year", "funded_month", "funded_day", "investments"})
public class Funding_round
{
  private Integer id;
  private String round_code;
  private String source_url;
  private String source_description;
  private Double raised_amount;
  private String raised_currency_code;
  private Integer funded_year;
  private Integer funded_month;
  private Integer funded_day;
  private Investment[] investments;

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("round_code")
  public String getRound_code() {
    return round_code;
  }

  public void setRound_code(String round_code) {
    this.round_code = round_code;
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

  @JsonProperty("raised_amount")
  public Double getRaised_amount() {
    return raised_amount;
  }

  public void setRaised_amount(Double raised_amount) {
    this.raised_amount = raised_amount;
  }

  @JsonProperty("raised_currency_code")
  public String getRaised_currency_code() {
    return raised_currency_code;
  }

  public void setRaised_currency_code(String raised_currency_code) {
    this.raised_currency_code = raised_currency_code;
  }

  @JsonProperty("funded_year")
  public Integer getFunded_year() {
    return funded_year;
  }

  public void setFunded_year(Integer funded_year) {
    this.funded_year = funded_year;
  }

  @JsonProperty("funded_month")
  public Integer getFunded_month() {
    return funded_month;
  }

  public void setFunded_month(Integer funded_month) {
    this.funded_month = funded_month;
  }

  @JsonProperty("funded_day")
  public Integer getFunded_day() {
    return funded_day;
  }

  public void setFunded_day(Integer funded_day) {
    this.funded_day = funded_day;
  }

  @JsonProperty("investments")
  public Investment[] getInvestments() {
    return investments;
  }
  public void setInvestments(Investment[] investments) {
    this.investments = investments;
  }
}