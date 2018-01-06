package es.um.nosql.schemainference.m2t.config.pojo;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Validator
{
  private String attr;

  public void setAttr(String attr) {this.attr = attr;}

  public String getAttr() {return this.attr;}

  private String type;

  public void setType(String type) {this.type = type;}

  public String getType() {return this.type;}

  private String[] value;

  public void setValue(String[] value) {this.value = value;}

  public String[] getValue() {return this.value;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();
    result.append("    -Attr: " + this.getAttr() + "\n");
    result.append("    Type: " + this.getType() + "\n");
    if (value != null)
      result.append("    Value: " + Arrays.stream(this.getValue()).collect(Collectors.joining(", ")) + "\n");

    return result.toString();
  }
}