package es.um.nosql.s13e.db.gen.config.pojo;

import es.um.nosql.s13e.db.gen.utils.Constants;

public class ReferenceOptions
{
  private Integer minReferenceAllowed;
  public void setMinAggregateAllowed(Integer minReferenceAllowed) {this.minReferenceAllowed = minReferenceAllowed;}
  public Integer getMinReferenceAllowed() { return this.minReferenceAllowed;}

  private Integer maxReferenceAllowed;
  public void setMaxReferenceAllowed(Integer maxReferenceAllowed) {this.maxReferenceAllowed = maxReferenceAllowed;}
  public Integer getMaxReferenceAllowed() { return this.maxReferenceAllowed;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinReferenceAllowed() != null)  result.append(Constants.GET_TABS(this.getClass()) + "-Min references allowed: " + this.getMinReferenceAllowed() + "\n");
    if (this.getMaxReferenceAllowed() != null)  result.append(Constants.GET_TABS(this.getClass()) + "-Max references allowed: " + this.getMaxReferenceAllowed() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinReferenceAllowed() != null && this.getMinReferenceAllowed() < 0)
      throw new IllegalArgumentException("Minimum reference cardinality allowed must be equal or greater than 0.");

    if (this.getMaxReferenceAllowed() != null && this.getMaxReferenceAllowed() < 1)
      throw new IllegalArgumentException("Maximum reference cardinality allowed must be equal or greater than 1.");

    if (this.getMinReferenceAllowed() != null && this.getMaxReferenceAllowed() != null && this.getMinReferenceAllowed() > this.getMaxReferenceAllowed())
      throw new IllegalArgumentException("Maximum reference cardinality must be equal or greater than Minimum reference cardinality.");

    return true;
  }
}