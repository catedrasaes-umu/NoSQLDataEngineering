package es.um.nosql.schemainference.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"valuation_amount", "valuation_currency_code", "pub_year", "pub_month", "pub_day", "stock_symbol"})
public class Ipo
{
  private Double valuation_amount;

  private String valuation_currency_code;

  private Integer pub_year;

  private Integer pub_month;

  private Integer pub_day;

  private String stock_symbol;

  @JsonProperty("valuation_amount")
  public Double getValuation_amount() {
    return valuation_amount;
  }

  public void setValuation_amount(Double valuation_amount) {
    this.valuation_amount = valuation_amount;
  }

  @JsonProperty("valuation_currency_code")
  public String getValuation_currency_code() {
    return valuation_currency_code;
  }

  public void setValuation_currency_code(String valuation_currency_code) {
    this.valuation_currency_code = valuation_currency_code;
  }

  @JsonProperty("pub_year")
  public Integer getPub_year() {
    return pub_year;
  }

  public void setPub_year(Integer pub_year) {
    this.pub_year = pub_year;
  }

  @JsonProperty("pub_month")
  public Integer getPub_month() {
    return pub_month;
  }

  public void setPub_month(Integer pub_month) {
    this.pub_month = pub_month;
  }

  @JsonProperty("pub_day")
  public Integer getPub_day() {
    return pub_day;
  }

  public void setPub_day(Integer pub_day) {
    this.pub_day = pub_day;
  }

  @JsonProperty("stock_symbol")
  public String getStock_symbol() {
    return stock_symbol;
  }

  public void setStock_symbol(String stock_symbol) {
    this.stock_symbol = stock_symbol;
  }
}