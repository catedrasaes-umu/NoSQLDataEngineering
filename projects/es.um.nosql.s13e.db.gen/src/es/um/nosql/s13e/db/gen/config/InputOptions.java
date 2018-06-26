package es.um.nosql.s13e.db.gen.config;

import java.io.File;

import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class InputOptions
{
  private String model;
  public void setModel(String model) {this.model = model;}
  public String getModel() {return this.model;}

  private Boolean debug;
  public void setDebug(Boolean debug) {this.debug = debug;}
  public Boolean getDebug() {return this.debug;}

  private Integer splits;
  public void setSplits(Integer splits) {this.splits = splits;}
  public Integer getSplits() {return this.splits;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getModel() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Model: " + this.getModel() + "\n");
    if (this.getDebug() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Debug: " + this.getDebug() + "\n");
    if (this.getSplits() != null) result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Splits: " + this.getSplits() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getModel() == null)
      throw new IllegalArgumentException("Input model is required to generate database content. Please provide a route to a XMI file.");

    if (this.getSplits() != null && this.getSplits() < 1)
      throw new IllegalArgumentException("Splits value must be equal or greater than 1.");

    if (!(new File(this.getModel()).exists()))
      throw new IllegalArgumentException(this.getModel() + " - XMI File does not exist.");

    return true;
  }
}