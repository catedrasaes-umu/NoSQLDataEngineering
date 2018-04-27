package es.um.nosql.s13e.db.gen.config;

import java.io.File;
import java.util.Arrays;

import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class PrimitiveTypeOptions
{
  private Double strangeTypesProbability;
  public void setStrangeTypesProbability(Double strangeTypesProbability) {this.strangeTypesProbability = strangeTypesProbability;}
  public Double getStrangeTypesProbability() {return this.strangeTypesProbability;}

  private Double nullProbability;
  public void setNullProbability(Double nullProbability) {this.nullProbability = nullProbability;}
  public Double getNullProbability() {return this.nullProbability;}

  private String stringNamesFile;
  public void setStringNamesFile(String stringNamesFile) {this.stringNamesFile = stringNamesFile;}
  public String getStringNamesFile() {return this.stringNamesFile;}

  private String stringSurnamesFile;
  public void setStringSurnamesFile(String stringSurnamesFile) {this.stringSurnamesFile = stringSurnamesFile;}
  public String getStringSurnamesFile() {return this.stringSurnamesFile;}

  private String stringWordsFile;
  public void setStringWordsFile(String stringWordsFile) {this.stringWordsFile = stringWordsFile;}
  public String getStringWordsFile() {return this.stringWordsFile;}

  private String stringType;
  public void setStringType(String stringType) {this.stringType = stringType;}
  public String getStringType() {return this.stringType;}

  private Integer minIntegerAllowed;
  public void setMinIntegerAllowed(Integer minIntegerAllowed) {this.minIntegerAllowed = minIntegerAllowed;}
  public Integer getMinIntegerAllowed() {return this.minIntegerAllowed;}

  private Integer maxIntegerAllowed;
  public void setMaxIntegerAllowed(Integer maxIntegerAllowed) {this.maxIntegerAllowed = maxIntegerAllowed;}
  public Integer getMaxIntegerAllowed() {return this.maxIntegerAllowed;}

  private Double minDoubleAllowed;
  public void setMinDoubleAllowed(Double minDoubleAllowed) {this.minDoubleAllowed = minDoubleAllowed;}
  public Double getMinDoubleAllowed() {return this.minDoubleAllowed;}

  private Double maxDoubleAllowed;
  public void setMaxDoubleAllowed(Double maxDoubleAllowed) {this.maxDoubleAllowed = maxDoubleAllowed;}
  public Double getMaxDoubleAllowed() {return this.maxDoubleAllowed;}

  private Integer doubleDecimalsAllowed;
  public void setDoubleDecimalsAllowed(Integer doubleDecimalsAllowed) {this.doubleDecimalsAllowed = doubleDecimalsAllowed;}
  public Integer getDoubleDecimalsAllowed() {return this.doubleDecimalsAllowed;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getStrangeTypesProbability() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Strange types probability: " + this.getStrangeTypesProbability() + "\n");
    if (this.getNullProbability() != null)          result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Null probability: " + this.getNullProbability() + "\n");
    if (this.getStringType() != null)               result.append(ConfigConstants.GET_TABS(this.getClass()) + "-StringType: " + this.getStringType() + "\n");
    if (this.getMinIntegerAllowed() != null)        result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Min integer allowed: " + this.getMinIntegerAllowed() + "\n");
    if (this.getMaxIntegerAllowed() != null)        result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Max integer allowed: " + this.getMaxIntegerAllowed() + "\n");
    if (this.getMinDoubleAllowed() != null)         result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Min double allowed: " + this.getMinDoubleAllowed() + "\n");
    if (this.getMaxDoubleAllowed() != null)         result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Max double allowed: " + this.getMaxDoubleAllowed() + "\n");
    if (this.getDoubleDecimalsAllowed() != null)    result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Double decimals allowed: " + this.getDoubleDecimalsAllowed() + "\n");
    if (this.getStringNamesFile() != null)          result.append(ConfigConstants.GET_TABS(this.getClass()) + "-String names file: " + this.getStringNamesFile() + "\n");
    if (this.getStringSurnamesFile() != null)       result.append(ConfigConstants.GET_TABS(this.getClass()) + "-String surnames file: " + this.getStringSurnamesFile() + "\n");
    if (this.getStringWordsFile() != null)          result.append(ConfigConstants.GET_TABS(this.getClass()) + "-String words file: " + this.getStringWordsFile() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getStrangeTypesProbability() != null && (this.getStrangeTypesProbability() < 0.0 || this.getStrangeTypesProbability() >= 1.0))
      throw new IllegalArgumentException("The \"StrangeTypesProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    if (this.getNullProbability() != null && (this.getNullProbability() < 0.0 || this.getNullProbability() >= 1.0))
      throw new IllegalArgumentException("The \"NullProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    if (this.getStringType() != null && !Arrays.asList("word", "phrase", "word_number", "nonsense", "name", "surname", "random", "large").contains(this.getStringType()))
      throw new IllegalArgumentException("String type must be of the following types: \"random\", \"large\", \"word\", \"phrase\", \"word_number\", \"nonsense\", \"name\", \"name_surname\"");

    if (this.getMinIntegerAllowed() != null && this.getMaxIntegerAllowed() != null && this.getMinIntegerAllowed() > this.getMaxIntegerAllowed())
      throw new IllegalArgumentException("Maximum int allowed must be equal or greater than Minimum int allowed.");

    if (this.getMinDoubleAllowed() != null && this.getMaxDoubleAllowed() != null && this.getMinDoubleAllowed() >= this.getMaxDoubleAllowed())
      throw new IllegalArgumentException("Maximum double allowed must be equal or greater than Minimum double allowed.");

    if (this.getDoubleDecimalsAllowed() != null && this.getDoubleDecimalsAllowed() < 0)
      throw new IllegalArgumentException("Double decimals allowed must be greater than 0.");

    if (this.getStringNamesFile() != null && !new File(this.getStringNamesFile()).exists())
      throw new IllegalArgumentException("The \"names\" file can't be located.");

    if (this.getStringSurnamesFile() != null && !new File(this.getStringSurnamesFile()).exists())
      throw new IllegalArgumentException("The \"surnames\" file can't be located.");

    if (this.getStringWordsFile() != null && !new File(this.getStringWordsFile()).exists())
      throw new IllegalArgumentException("The \"words\" file can't be located.");

    return true;
  }
}