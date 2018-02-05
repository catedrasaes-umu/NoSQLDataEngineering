package es.um.nosql.s13e.db.pojo.companies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"competitor"})
public class Competition
{
  private NamePermalink competitor;

  @JsonProperty("competitor")
  public NamePermalink getCompetitor() {
    return competitor;
  }

  public void setCompetitor(NamePermalink competitor) {
    this.competitor = competitor;
  }
}