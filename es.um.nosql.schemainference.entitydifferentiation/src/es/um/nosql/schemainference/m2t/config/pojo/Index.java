package es.um.nosql.schemainference.m2t.config.pojo;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Index
{
  private String[] attr;

  public void setAttr(String[] attr) {this.attr = attr;}

  public String[] getAttr() {return this.attr;}

  private String type;

  public void setType(String type) {this.type = type;}

  public String getType() {return this.type;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();
    if (attr != null)
      result.append("    -Attr: " + Arrays.stream(this.getAttr()).collect(Collectors.joining(", ")) + "\n");
    result.append("    Type: " + this.getType() + "\n");

    return result.toString();
  }
}