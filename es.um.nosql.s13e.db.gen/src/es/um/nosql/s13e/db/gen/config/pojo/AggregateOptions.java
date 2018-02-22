package es.um.nosql.s13e.db.gen.config.pojo;

import es.um.nosql.s13e.db.gen.utils.Constants;

public class AggregateOptions
{
  private Integer minAggregateAllowed;
  public void setMinAggregateAllowed(Integer minAggregateAllowed) {this.minAggregateAllowed = minAggregateAllowed;}
  public Integer getMinAggregateAllowed() { return this.minAggregateAllowed;}

  private Integer maxAggregateAllowed;
  public void setMaxAggregateAllowed(Integer maxAggregateAllowed) {this.maxAggregateAllowed = maxAggregateAllowed;}
  public Integer getMaxAggregateAllowed() { return this.maxAggregateAllowed;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinAggregateAllowed() != null)  result.append(Constants.GET_TABS(this.getClass()) + "-Min aggregates allowed: " + this.getMinAggregateAllowed() + "\n");
    if (this.getMaxAggregateAllowed() != null)  result.append(Constants.GET_TABS(this.getClass()) + "-Max aggregates allowed: " + this.getMaxAggregateAllowed() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinAggregateAllowed() != null && this.getMinAggregateAllowed() < 0)
      throw new IllegalArgumentException("Minimum aggregation cardinality allowed must be equal or greater than 0.");

    if (this.getMaxAggregateAllowed() != null && this.getMaxAggregateAllowed() < 1)
      throw new IllegalArgumentException("Maximum aggregation cardinality allowed must be equal or greater than 1.");

    if (this.getMinAggregateAllowed() != null && this.getMaxAggregateAllowed() != null && this.getMinAggregateAllowed() > this.getMaxAggregateAllowed())
      throw new IllegalArgumentException("Maximum aggregation cardinality must be equal or greater than Minimum aggregation cardinality.");

    return true;
  }
}