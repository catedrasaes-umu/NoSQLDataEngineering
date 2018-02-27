package es.um.nosql.s13e.db.gen.config;

import java.io.File;

import es.um.nosql.s13e.db.gen.utils.Constants;

public class InputOptions
{
  private String model;
  public void setModel(String model) {this.model = model;}
  public String getModel() {return this.model;}

  private Boolean debug;
  public void setDebug(Boolean debug) {this.debug = debug;}
  public Boolean getDebug() {return this.debug;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getModel() != null) result.append(Constants.GET_TABS(this.getClass()) + "-Model: " + this.getModel() + "\n");
    if (this.getDebug() != null) result.append(Constants.GET_TABS(this.getClass()) + "-Debug: " + this.getDebug() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getModel() == null)
      throw new IllegalArgumentException("Input model is required to generate database content. Please provide a route to a XMI file.");

    if (!(new File(this.getModel()).exists()))
      throw new IllegalArgumentException(this.getModel() + " - XMI File does not exist.");
    return true;
  }
}