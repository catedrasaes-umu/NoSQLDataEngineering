package es.um.nosql.schemainference.m2t.config.pojo;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigIndex
{
  ////////////////////////////////////////////////////////////////////////////
  //// Global options                                                     ////
  ////////////////////////////////////////////////////////////////////////////

  private String[] attr;

  public void setAttr(String[] attr) {this.attr = attr;}

  public String[] getAttr() {return this.attr;}

  private String type;

  public void setType(String type) {this.type = type;}

  public String getType() {return this.type;}

  private Boolean unique;

  public void setUnique(Boolean unique) {this.unique = unique;}

  public Boolean getUnique() {return this.unique;}

  private Boolean background;

  public void setBackground(Boolean background) {this.background = background;}

  public Boolean getBackground() {return this.background;}

  private Boolean sparse;

  public void setSparse(Boolean sparse) {this.sparse = sparse;}

  public Boolean getSparse() {return this.sparse;}

  private String name;

  public void setName(String name) {this.name = name;}

  public String getName() {return this.name;}

  private String partialFilterExpression;

  public void setPartialFilterExpression(String partialFilterExpression) {this.partialFilterExpression = partialFilterExpression;}

  public String getPartialFilterExpression() {return this.partialFilterExpression;}

  private Integer expireAfterSeconds;

  public void setExpireAfterSeconds(Integer expireAfterSeconds) {this.expireAfterSeconds = expireAfterSeconds;}

  public Integer getExpireAfterSeconds() {return this.expireAfterSeconds;}

  ////////////////////////////////////////////////////////////////////////////
  //// IndexType.TEXT Indexes                                             ////
  ////////////////////////////////////////////////////////////////////////////

  private Integer weight;

  public void setWeight(Integer weight) {this.weight = weight;}

  public Integer getWeight() {return this.weight;}

  private String default_language;

  public void setDefault_Language(String default_language) {this.default_language = default_language;}

  public String getDefault_Language() {return this.default_language;}

  private String language_override;

  public void setLanguage_Override(String language_override) {this.language_override = language_override;}

  public String getLanguage_Override() {return this.language_override;}

  private Integer textIndexVersion;

  public void setTextIndexVersion(Integer textIndexVersion) {this.textIndexVersion = textIndexVersion;}

  public Integer getTextIndexVersion() {return this.textIndexVersion;}

  ////////////////////////////////////////////////////////////////////////////
  //// IndexType.2DSPHERE Indexes                                         ////
  ////////////////////////////////////////////////////////////////////////////

  private Integer geo2dsphereIndexVersion;

  public void setGeo2dsphereIndexVersion(Integer geo2dsphereIndexVersion) {this.geo2dsphereIndexVersion = geo2dsphereIndexVersion;}

  public Integer getGeo2dsphereIndexVersion() {return this.geo2dsphereIndexVersion;}

  ////////////////////////////////////////////////////////////////////////////
  //// IndexType.2D Indexes                                               ////
  ////////////////////////////////////////////////////////////////////////////

  private Integer bits;

  public void setBits(Integer bits) {this.bits = bits;}

  public Integer getBits() {return this.bits;}

  private Double min;

  public void setMin(Double min) {this.min = min;}

  public Double getMin() {return this.min;}

  private Double max;

  public void setMax(Double max) {this.max = max;}

  public Double getMax() {return this.max;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getAttr() != null)
      result.append("    -Attr: [" + String.join(", ", this.getAttr()) + "] ");
    if (this.getType() != null)
      result.append(" Type: " + this.getType());
    if (this.getUnique() != null)
      result.append(" Unique: " + this.getUnique());
    if (this.getBackground() != null)
      result.append(" Background: " + this.getBackground());
    if (this.getSparse() != null)
      result.append(" Sparse: " + this.getSparse());
    if (this.getName() != null)
      result.append(" Name: " + this.getName());
    if (this.getPartialFilterExpression() != null)
      result.append(" PartialFilterExpression: " + this.getPartialFilterExpression());
    if (this.getExpireAfterSeconds() != null)
      result.append(" ExpireAfterSeconds: " + this.getExpireAfterSeconds());

    if (this.getWeight() != null)
      result.append(" Weight: " + this.getWeight());
    if (this.getDefault_Language() != null)
      result.append(" Default_language: " + this.getDefault_Language());
    if (this.getLanguage_Override() != null)
      result.append(" Language_override: " + this.getLanguage_Override());
    if (this.getTextIndexVersion() != null)
      result.append(" TextIndexVersion: " + this.getTextIndexVersion());

    if (this.getGeo2dsphereIndexVersion() != null)
      result.append(" Geo2dsphereIndexVersion: " + this.getGeo2dsphereIndexVersion());

    if (this.getBits() != null)
      result.append(" Bits: " + this.getBits());
    if (this.getMin() != null)
      result.append(" Min: " + this.getMin());
    if (this.getMax() != null)
      result.append(" Max: " + this.getMax());

    result.append("\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getAttr() == null)
      throw new IllegalArgumentException("Index must have an \"attr\" list of fields to be indexed");

    if (this.getType() != null && !Arrays.asList("text", "hashed", "asc", "desc", "geo2d", "geo2dsphere").contains(this.getType().toLowerCase()))
      throw new IllegalArgumentException("Index type [" + String.join(", ", this.getAttr()) + "] must be of type Text, Hashed, ASC, DESC, GEO2D or GEO2DSPHERE");

    if (this.getWeight() != null && (this.getWeight() < 1 || this.getWeight() > 99999))
      throw new IllegalArgumentException("Index [\" + String.join(\", \", this.getAttr()) + \"] weight must be between 1 and 99.999");

    if (this.getTextIndexVersion() != null && this.getTextIndexVersion() != 1 && this.getTextIndexVersion() != 2)
      throw new IllegalArgumentException("Index [\" + String.join(\", \", this.getAttr()) + \"] TextIndexVersion must have one of the following values: 1 or 2");

    if (this.getGeo2dsphereIndexVersion() != null && this.getGeo2dsphereIndexVersion() != 1 && this.getGeo2dsphereIndexVersion() != 2)
      throw new IllegalArgumentException("Index [\" + String.join(\", \", this.getAttr()) + \"] Geo2DSphereIndexVersion must have one of the following values: 1 or 2");

    if (this.getBits() != null && (this.getBits() < 1 || this.getBits() > 32))
      throw new IllegalArgumentException("Index [\" + String.join(\", \", this.getAttr()) + \"] Bits must be between 1 and 32");

    return true;
  }
}