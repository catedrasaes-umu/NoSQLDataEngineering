package es.um.nosql.schemainference.m2t.config.pojo;

public class ConfigValidator
{
  private String attr;

  public void setAttr(String attr) {this.attr = attr;}

  public String getAttr() {return this.attr;}

  private Integer min;

  public void setMin(Integer min) {this.min = min;}

  public Integer getMin() {return this.min;}

  private Integer max;

  public void setMax(Integer max) {this.max = max;}

  public Integer getMax() {return this.max;}

  private String[] enumValues;

  public void setEnumValues(String[] enumValues) {this.enumValues = enumValues;}

  public String[] getEnumValues() {return this.enumValues;}

  private String match;

  public void setMatch(String match) {this.match = match;}

  public String getMatch() {return this.match;}

  private Integer minLength;

  public void setMinLength(Integer minLength) {this.minLength = minLength;}

  public Integer getMinLength() {return this.minLength;}

  private Integer maxLength;

  public void setMaxLength(Integer maxLength) {this.maxLength = maxLength;}

  public Integer getMaxLength() {return this.maxLength;}

  private String message;

  public void setMessage(String message) {this.message = message;}

  public String getMessage() {return this.message;}

  private String custom;

  public void setCustom(String custom) {this.custom = custom;}

  public String getCustom() {return this.custom;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getAttr() != null)
      result.append("    -Attr: " + this.getAttr());
    if (this.getMin() != null)
      result.append(" Min: " + this.getMin());
    if (this.getMax() != null)
      result.append(" Value: " + this.getMax());
    if (this.getEnumValues() != null)
      result.append(" EnumValues: [" + String.join(", ", this.getEnumValues()) + "]");
    if (this.getMatch() != null)
      result.append(" Match: " + this.getMatch());
    if (this.getMessage() != null)
      result.append(" Message: " + this.getMessage());
    if (this.getMinLength() != null)
      result.append(" MinLength: " + this.getMinLength());
    if (this.getMaxLength() != null)
      result.append(" MaxLength: " + this.getMaxLength());
    if (this.getCustom() != null)
      result.append(" Custom: " + this.getCustom());
    result.append("\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getAttr() == null)
      throw new IllegalArgumentException("Validator must have an \"attr\" field to be validated");

    if (this.getEnumValues() == null && this.getMatch() == null && this.getMin() == null && this.getMax() == null
        && this.getMinLength() == null && this.getMaxLength() == null && this.getCustom() == null)
      throw new IllegalArgumentException("The validator for " + this.getAttr()
        + " must define at least one validation criterium (Min | Max | Enum | Match | MinLength | MaxLength | Custom)");

    if (this.getMin() != null && this.getMax() != null && this.getMin() > this.getMax())
      throw new IllegalArgumentException("The validator for " + this.getAttr() + " Mininum value must be less or equals than Maximum value");

    if (this.getMinLength() != null && this.getMaxLength() != null && this.getMinLength() > this.getMaxLength())
      throw new IllegalArgumentException("The validator for " + this.getAttr() + " MininumLength value must be less or equals than MaximumLength value");

    if (this.getEnumValues() != null && this.getMatch() != null)
      throw new IllegalArgumentException("The validator for " + this.getAttr() + " can't define a range of values and also a pattern at the same time");

    return true;
  }
}