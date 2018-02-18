package es.um.nosql.s13e.db.gen.config.pojo;

import java.io.File;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigureOptions
{
  private String stringNamesFile;
  public void setStringNamesFile(String stringNamesFile) {this.stringNamesFile = stringNamesFile;}
  public String getStringNamesFile() {return this.stringNamesFile;}

  private String stringSurnamesFile;
  public void setStringSurnamesFile(String stringSurnamesFile) {this.stringSurnamesFile = stringSurnamesFile;}
  public String getStringSurnamesFile() {return this.stringSurnamesFile;}

  private String stringWordsFile;
  public void setStringWordsFile(String stringWordsFile) {this.stringWordsFile = stringWordsFile;}
  public String getStringWordsFile() {return this.stringWordsFile;}

  private Integer minInstancesVersion;
  public void setMinInstancesVersion(Integer minInstancesVersion) {this.minInstancesVersion = minInstancesVersion;}
  public Integer getMinInstancesVersion() {return this.minInstancesVersion;}

  private Integer maxInstancesVersion;
  public void setMaxInstancesVersion(Integer maxInstancesVersion) {this.maxInstancesVersion = maxInstancesVersion;}
  public Integer getMaxInstancesVersion() {return this.maxInstancesVersion;}

  private String stringType;
  public void setStringType(String stringType) {this.stringType = stringType;}
  public String getStringType() {return this.stringType;}

  private Boolean strict;
  public void setStrict(Boolean strict) {this.strict = strict;}
  public Boolean getStrict() {return this.strict;}

  private Boolean allowNulls;
  public void setAllowNulls(Boolean allowNulls) {this.allowNulls = allowNulls;}
  public Boolean getAllowNulls() {return this.allowNulls;}

  private Integer minIntAllowed;
  public void setMinIntAllowed(Integer minIntAllowed) {this.minIntAllowed = minIntAllowed;}
  public Integer getMinIntAllowed() {return this.minIntAllowed;}

  private Integer maxIntAllowed;
  public void setMaxIntAllowed(Integer maxIntAllowed) {this.maxIntAllowed = maxIntAllowed;}
  public Integer getMaxIntAllowed() {return this.maxIntAllowed;}

  private Double minDoubleAllowed;
  public void setMinDoubleAllowed(Double minDoubleAllowed) {this.minDoubleAllowed = minDoubleAllowed;}
  public Double getMinDoubleAllowed() {return this.minDoubleAllowed;}

  private Double maxDoubleAllowed;
  public void setMaxDoubleAllowed(Double maxDoubleAllowed) {this.maxDoubleAllowed = maxDoubleAllowed;}
  public Double getMaxDoubleAllowed() {return this.maxDoubleAllowed;}

  private Integer minTupleElements;
  public void setMinTupleElements(Integer minTupleElements) {this.minTupleElements = minTupleElements;}
  public Integer getMinTupleElements() {return this.minTupleElements;}

  private Integer maxTupleElements;
  public void setMaxTupleElements(Integer maxTupleElements) {this.maxTupleElements = maxTupleElements;}
  public Integer getMaxTupleElements() {return this.maxTupleElements;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinInstancesVersion() != null)
      result.append("  -Min instances per Version: " + this.getMinInstancesVersion() + "\n");

    if (this.getMaxInstancesVersion() != null)
      result.append("  -Max instances per Version: " + this.getMaxInstancesVersion() + "\n");

    if (this.getStringType() != null)
      result.append("  -StringType: " + this.getStringType() + "\n");

    if (this.getStrict() != null)
      result.append("  -Strict: " + this.getStrict() + "\n");

    if (this.getAllowNulls() != null)
      result.append("  -Allow nulls: " + this.getAllowNulls() + "\n");

    if (this.getMinIntAllowed() != null)
      result.append("  -Min int allowed: " + this.getMinIntAllowed() + "\n");

    if (this.getMaxIntAllowed() != null)
      result.append("  -Max int allowed: " + this.getMaxIntAllowed() + "\n");

    if (this.getMinDoubleAllowed() != null)
      result.append("  -Min double allowed: " + this.getMinDoubleAllowed() + "\n");

    if (this.getMaxDoubleAllowed() != null)
      result.append("  -Max double allowed: " + this.getMaxDoubleAllowed() + "\n");

    if (this.getMinTupleElements() != null)
      result.append("  -Min tuple elements: " + this.getMinTupleElements() + "\n");

    if (this.getMaxTupleElements() != null)
      result.append("  -Max tuple elements: " + this.getMaxTupleElements() + "\n");

    if (this.getStringNamesFile() != null)
      result.append("  -String names file: " + this.getStringNamesFile() + "\n");

    if (this.getStringSurnamesFile() != null)
      result.append("  -String surnames file: " + this.getStringSurnamesFile() + "\n");

    if (this.getStringWordsFile() != null)
      result.append("  -String words file: " + this.getStringWordsFile() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinInstancesVersion() != null && this.getMinInstancesVersion() < 0)
      throw new IllegalArgumentException("Minimum instances per version must be equal or greater than 0.");

    if (this.getMaxInstancesVersion() != null && this.getMaxInstancesVersion() < 1)
      throw new IllegalArgumentException("Maximum instances per version must be equal or greater than 1.");

    if (this.getStringType() != null && !Arrays.asList("word", "phrase", "word_number", "nonsense", "name", "surname", "random", "large").contains(this.getStringType()))
      throw new IllegalArgumentException("String type must be of the following types: \"random\", \"large\", \"word\", \"phrase\", \"word_number\", \"nonsense\", \"name\", \"name_surname\"");

    if (this.getMinInstancesVersion() != null && this.getMaxInstancesVersion() != null && this.getMinInstancesVersion() > this.getMaxInstancesVersion())
      throw new IllegalArgumentException("Maximum instances per version must be equal or greater than Minimum instances per version.");

    if (this.getMinIntAllowed() != null && this.getMaxIntAllowed() != null && this.getMinIntAllowed() >= this.getMaxIntAllowed())
      throw new IllegalArgumentException("Maximum int allowed must be equal or greater than Minimum int allowed.");

    if (this.getMinDoubleAllowed() != null && this.getMaxDoubleAllowed() != null && this.getMinDoubleAllowed() >= this.getMaxDoubleAllowed())
      throw new IllegalArgumentException("Maximum double allowed must be equal or greater than Minimum double allowed.");

    if (this.getMinTupleElements() != null && this.getMinTupleElements() < 0)
      throw new IllegalArgumentException("Minimum tuple elements must be equal or greater than 0.");

    if (this.getMaxTupleElements() != null && this.getMaxTupleElements() < 1)
      throw new IllegalArgumentException("Maximum tuple elements must be equal or greater than 1.");

    if (this.getMinTupleElements() != null && this.getMaxTupleElements() != null && this.getMinTupleElements() > this.getMaxTupleElements())
      throw new IllegalArgumentException("Maximum tuple elements must be equal or greater than Minimum tuple elements.");

    if (this.getStringNamesFile() != null && !new File(this.getStringNamesFile()).exists())
      throw new IllegalArgumentException("The \"names\" file can't be located.");

    if (this.getStringSurnamesFile() != null && !new File(this.getStringSurnamesFile()).exists())
      throw new IllegalArgumentException("The \"surnames\" file can't be located.");

    if (this.getStringWordsFile() != null && !new File(this.getStringWordsFile()).exists())
      throw new IllegalArgumentException("The \"words\" file can't be located.");

    return true;
  }
}