package es.um.nosql.s13e.db.gen.config;

import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class VariationOptions
{
  private Integer minInstances;
  public void setMinInstances(Integer minInstances) {this.minInstances = minInstances;}
  public Integer getMinInstances() {return this.minInstances;}

  private Integer maxInstances;
  public void setMaxInstances(Integer maxInstances) {this.maxInstances = maxInstances;}
  public Integer getMaxInstances() {return this.maxInstances;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinInstances() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Min instances per Variation: " + this.getMinInstances() + "\n");
    if (this.getMaxInstances() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Max instances per Variation: " + this.getMaxInstances() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinInstances() != null && this.getMinInstances() < 0)
      throw new IllegalArgumentException("Minimum instances per variation must be equal or greater than 0.");

    if (this.getMaxInstances() != null && this.getMaxInstances() < 1)
      throw new IllegalArgumentException("Maximum instances per variation must be equal or greater than 1.");

    if (this.getMinInstances() != null && this.getMaxInstances() != null && this.getMinInstances() > this.getMaxInstances())
      throw new IllegalArgumentException("Maximum instances per variation must be equal or greater than Minimum instances per variation.");

    return true;
  }
}