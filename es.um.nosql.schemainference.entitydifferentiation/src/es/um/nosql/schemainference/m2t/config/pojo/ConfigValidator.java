package es.um.nosql.schemainference.m2t.config.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigValidator
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

    if (this.getAttr() != null)
      result.append("    -Attr: [" + String.join(", ", this.getAttr()) + "] ");
    if (this.getType() != null)
      result.append(" Type: " + this.getType());
    if (this.getValue() != null)
      result.append(" Value: [" + String.join(", ", this.getValue()) + "] ");

    result.append("\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getAttr() == null)
      throw new IllegalArgumentException("Validator must have an \"attr\" field to be validated");

    if (this.getType() == null)
      throw new IllegalArgumentException("Validator must have a \"type\" field indicating the type of validation to be performed");

    if (this.getValue() == null)
      throw new IllegalArgumentException("Validator must have a \"value\" list of fields with the validation expression");

    return true;
  }
}