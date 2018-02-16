package es.um.nosql.s13e.db.gen.config.pojo;

import java.io.File;

public class InputOptions
{
  private String model;

  public void setModel(String model) {this.model = model;}

  public String getModel() {return this.model;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getModel() != null)
      result.append("  -Model: " + this.getModel() + "\n");

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