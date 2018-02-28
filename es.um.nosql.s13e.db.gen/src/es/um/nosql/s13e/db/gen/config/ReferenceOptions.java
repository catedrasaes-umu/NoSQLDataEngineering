package es.um.nosql.s13e.db.gen.config;

import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class ReferenceOptions
{
  private Double strangeTypesProbability;
  public void setStrangeTypesProbability(Double strangeTypesProbability) {this.strangeTypesProbability = strangeTypesProbability;}
  public Double getStrangeTypesProbability() {return this.strangeTypesProbability;}

  private Integer minReferenceAllowed;
  public void setMinAggregateAllowed(Integer minReferenceAllowed) {this.minReferenceAllowed = minReferenceAllowed;}
  public Integer getMinReferenceAllowed() { return this.minReferenceAllowed;}

  private Integer maxReferenceAllowed;
  public void setMaxReferenceAllowed(Integer maxReferenceAllowed) {this.maxReferenceAllowed = maxReferenceAllowed;}
  public Integer getMaxReferenceAllowed() { return this.maxReferenceAllowed;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getStrangeTypesProbability() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Strange types probability: " + this.getStrangeTypesProbability() + "\n");
    if (this.getMinReferenceAllowed() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Min references allowed: " + this.getMinReferenceAllowed() + "\n");
    if (this.getMaxReferenceAllowed() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Max references allowed: " + this.getMaxReferenceAllowed() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getStrangeTypesProbability() != null && (this.getStrangeTypesProbability() < 0.0 || this.getStrangeTypesProbability() >= 1.0))
      throw new IllegalArgumentException("The \"StrangeTypesProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    if (this.getMinReferenceAllowed() != null && this.getMinReferenceAllowed() < 0)
      throw new IllegalArgumentException("Minimum reference cardinality allowed must be equal or greater than 0.");

    if (this.getMaxReferenceAllowed() != null && this.getMaxReferenceAllowed() < 1)
      throw new IllegalArgumentException("Maximum reference cardinality allowed must be equal or greater than 1.");

    if (this.getMinReferenceAllowed() != null && this.getMaxReferenceAllowed() != null && this.getMinReferenceAllowed() > this.getMaxReferenceAllowed())
      throw new IllegalArgumentException("Maximum reference cardinality must be equal or greater than Minimum reference cardinality.");

    return true;
  }
}