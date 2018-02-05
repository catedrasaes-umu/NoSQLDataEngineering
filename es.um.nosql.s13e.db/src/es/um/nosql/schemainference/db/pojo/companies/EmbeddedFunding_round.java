package es.um.nosql.s13e.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder("funding_round")
public class EmbeddedFunding_round
{
  private Funding_round2[] funding_round;

  @JsonProperty("funding_round")
  public Funding_round2[] getFunding_round() {
    return funding_round;
  }

  public void setFunding_round(Funding_round2[] funding_round) {
    this.funding_round = funding_round;
  }
}
