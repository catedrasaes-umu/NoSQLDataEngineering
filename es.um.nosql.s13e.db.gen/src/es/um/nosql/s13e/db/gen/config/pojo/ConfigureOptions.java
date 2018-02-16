package es.um.nosql.s13e.db.gen.config.pojo;

public class ConfigureOptions
{
  private Integer minInstancesVersion;

  public void setMinInstancesVersion(Integer minInstancesVersion) {this.minInstancesVersion = minInstancesVersion;}

  public Integer getMinInstancesVersion() {return this.minInstancesVersion;}

  private Integer maxInstancesVersion;

  public void setMaxInstancesVersion(Integer maxInstancesVersion) {this.maxInstancesVersion = maxInstancesVersion;}

  public Integer getMaxInstancesVersion() {return this.maxInstancesVersion;}

  private Boolean strict;

  public void setStrict(Boolean strict) {this.strict = strict;}

  public Boolean getStrict() {return this.strict;}

  private Boolean allowNulls;

  public void setAllowNulls(Boolean allowNulls) {this.allowNulls = allowNulls;}

  public Boolean getAllowNulls() {return this.allowNulls;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinInstancesVersion() != null)
      result.append("  -Min instances per Version: " + this.getMinInstancesVersion() + "\n");

    if (this.getMaxInstancesVersion() != null)
      result.append("  -Max instances per Version: " + this.getMaxInstancesVersion() + "\n");

    if (this.getStrict() != null)
      result.append("  -Strict: " + this.getStrict() + "\n");

    if (this.getAllowNulls() != null)
      result.append("  -Allow nulls: " + this.getAllowNulls() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinInstancesVersion() != null && this.getMinInstancesVersion() < 0)
      throw new IllegalArgumentException(" - Input model is required to generate database content. Please provide a route to a XMI file.");

    if (this.getMaxInstancesVersion() != null && this.getMaxInstancesVersion() < 1)
      throw new IllegalArgumentException("MaxInstancesVersion must be equal or greater than 1.");

    if (this.getMinInstancesVersion() != null && this.getMaxInstancesVersion() != null && this.getMinInstancesVersion() > this.getMaxInstancesVersion())
      throw new IllegalArgumentException("MinInstancesVersion must be equal or greater than MaxInstancesVersion.");

    return true;
  }
}