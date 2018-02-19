package es.um.nosql.s13e.db.gen.config.pojo;

public class ConfigureOptions
{
  private Integer minInstancesVersion;
  public void setMinInstancesVersion(Integer minInstancesVersion) {this.minInstancesVersion = minInstancesVersion;}
  public Integer getMinInstancesVersion() {return this.minInstancesVersion;}

  private Integer maxInstancesVersion;
  public void setMaxInstancesVersion(Integer maxInstancesVersion) {this.maxInstancesVersion = maxInstancesVersion;}
  public Integer getMaxInstancesVersion() {return this.maxInstancesVersion;}

  private Boolean includeType;
  public void setIncludeType(Boolean includeType) {this.includeType = includeType;}
  public Boolean getIncludeType() {return this.includeType;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinInstancesVersion() != null)  result.append("  -Min instances per Version: " + this.getMinInstancesVersion() + "\n");
    if (this.getMaxInstancesVersion() != null)  result.append("  -Max instances per Version: " + this.getMaxInstancesVersion() + "\n");
    if (this.getIncludeType() != null)          result.append("  -Include type: " + this.getIncludeType() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinInstancesVersion() != null && this.getMinInstancesVersion() < 0)
      throw new IllegalArgumentException("Minimum instances per version must be equal or greater than 0.");

    if (this.getMaxInstancesVersion() != null && this.getMaxInstancesVersion() < 1)
      throw new IllegalArgumentException("Maximum instances per version must be equal or greater than 1.");

    if (this.getMinInstancesVersion() != null && this.getMaxInstancesVersion() != null && this.getMinInstancesVersion() > this.getMaxInstancesVersion())
      throw new IllegalArgumentException("Maximum instances per version must be equal or greater than Minimum instances per version.");

    return true;
  }
}