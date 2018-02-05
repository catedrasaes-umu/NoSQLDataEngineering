package es.um.nosql.s13e.m2t.config.pojo;

import java.util.Arrays;

public class ConfigIndex
{
  ////////////////////////////////////////////////////////////////////////////
  //// Global options                                                     ////
  ////////////////////////////////////////////////////////////////////////////

  private String[] attr;

  public void setAttr(String[] attr) {this.attr = attr;}

  public String[] getAttr() {return this.attr;}

  private String[] type;

  public void setType(String[] type) {this.type = type;}

  public String[] getType() {return this.type;}

  private Boolean unique;

  public void setUnique(Boolean unique) {this.unique = unique;}

  public Boolean getUnique() {return this.unique;}

  private Boolean background;

  public void setBackground(Boolean background) {this.background = background;}

  public Boolean getBackground() {return this.background;}

  private Boolean disableValidation;

  public void setDisableValidation(Boolean disableValidation) {this.disableValidation = disableValidation;}

  public Boolean getDisableValidation() {return this.disableValidation;}

  private Boolean sparse;

  public void setSparse(Boolean sparse) {this.sparse = sparse;}

  public Boolean getSparse() {return this.sparse;}

  private String name;

  public void setName(String name) {this.name = name;}

  public String getName() {return this.name;}

  private String partialFilter;

  public void setPartialFilterExpression(String partialFilter) {this.partialFilter = partialFilter;}

  public String getPartialFilter() {return this.partialFilter;}

  private Integer expireAfterSeconds;

  public void setExpireAfterSeconds(Integer expireAfterSeconds) {this.expireAfterSeconds = expireAfterSeconds;}

  public Integer getExpireAfterSeconds() {return this.expireAfterSeconds;}

  ////////////////////////////////////////////////////////////////////////////
  //// IndexType.TEXT Indexes                                             ////
  ////////////////////////////////////////////////////////////////////////////

  private Integer[] weight;

  public void setWeight(Integer[] weight) {this.weight = weight;}

  public Integer[] getWeight() {return this.weight;}

  private String default_language;

  public void setDefault_language(String default_language) {this.default_language = default_language;}

  public String getDefault_language() {return this.default_language;}

  private String language_override;

  public void setLanguage_override(String language_override) {this.language_override = language_override;}

  public String getLanguage_override() {return this.language_override;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getAttr() != null)
      result.append("    -Attr: [" + String.join(", ", this.getAttr()) + "] ");
    if (this.getType() != null)
      result.append(" Type: [" + String.join(", ", this.getType()) + "] ");
    if (this.getUnique() != null)
      result.append(" Unique: " + this.getUnique());
    if (this.getBackground() != null)
      result.append(" Background: " + this.getBackground());
    if (this.getDisableValidation() != null)
      result.append(" DisableValidation: " + this.getDisableValidation());
    if (this.getSparse() != null)
      result.append(" Sparse: " + this.getSparse());
    if (this.getName() != null)
      result.append(" Name: " + this.getName());
    if (this.getPartialFilter() != null)
      result.append(" PartialFilter: " + this.getPartialFilter());
    if (this.getExpireAfterSeconds() != null)
      result.append(" ExpireAfterSeconds: " + this.getExpireAfterSeconds());

    if (this.getWeight() != null)
      result.append(" Weight: " + Arrays.toString(this.getWeight()));
    if (this.getDefault_language() != null)
      result.append(" Default_language: " + this.getDefault_language());
    if (this.getLanguage_override() != null)
      result.append(" Language_override: " + this.getLanguage_override());

    result.append("\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getAttr() == null || this.getType() == null)
      throw new IllegalArgumentException("Index must have an \"attr\" list of fields to be indexed and a \"type\" list of index types");

    if (!Arrays.stream(this.getType()).allMatch(t -> Arrays.asList("text", "hashed", "asc", "desc", "geo2d", "geo2dsphere").contains(t.toLowerCase())))
      throw new IllegalArgumentException("Index type [" + String.join(", ", this.getAttr()) + "] must be of type Text, Hashed, ASC, DESC, GEO2D or GEO2DSPHERE");

    if (this.getAttr().length > 1 && this.getAttr().length != this.getType().length)
      throw new IllegalArgumentException("Composed index type [" + String.join(", ", this.getAttr()) + "] must declare the same number of types as number of attributes");

    if (this.getAttr().length > 1 && !Arrays.stream(this.getType()).allMatch(t -> Arrays.asList("asc", "desc").contains(t.toLowerCase()))
        && !Arrays.stream(this.getType()).allMatch(t -> t.toLowerCase().equals("text")))
      throw new IllegalArgumentException("Composed index type [" + String.join(", ", this.getAttr()) + "] must be exclusively of types [ASC*, DESC*], or [TEXT]");

    if (this.getAttr().length > 1 && this.getWeight() != null && Arrays.stream(this.getType()).allMatch(t -> t.toLowerCase().equals("text")) && this.getWeight().length != this.getType().length)
      throw new IllegalArgumentException("Composed index type [" + String.join(", ", this.getAttr()) + "] of type [TEXT] must have the same number of types as weights, or not define weights at all");

    if (this.getWeight() != null && !this.getType()[0].toLowerCase().equals("text"))
      throw new IllegalArgumentException("Index [" + String.join(", ", this.getAttr()) + "] weight attribute only makes sense on TEXT indexes");

    if (this.getWeight() != null && !Arrays.stream(this.getWeight()).allMatch(w -> w >= 1 || w <= 99999))
      throw new IllegalArgumentException("Index [" + String.join(", ", this.getAttr()) + "] weights must be between 1 and 99.999");

    return true;
  }
}