package es.um.nosql.s13e.db.gen.config;

import java.util.Arrays;

import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class EntityOptions
{
  private VariationOptions variations;
  public void setVariations(VariationOptions variations) {this.variations = variations;}
  public VariationOptions getVariations() {return this.variations;}

  public String dateFormat;
  public void setDateFormat(String dateFormat) {this.dateFormat = dateFormat;}
  public String getDateFormat() {return this.dateFormat;}

  public String timestamp;
  public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
  public String getTimestamp() {return this.timestamp;}

  private Boolean includeType;
  public void setIncludeType(Boolean includeType) {this.includeType = includeType;}
  public Boolean getIncludeType() {return this.includeType;}

  private String interval;
  public void setInterval(String interval) {this.interval = interval;}
  public String getInterval() {return this.interval;}

  private Double randomIntervalProbability;
  public void setRandomIntervalProbability(Double randomIntervalProbability) {this.randomIntervalProbability = randomIntervalProbability;}
  public Double getRandomIntervalProbability() {return this.randomIntervalProbability;}

  private Double randomIntervalMargin;
  public void setRandomIntervalMargin(Double randomIntervalMargin) {this.randomIntervalMargin = randomIntervalMargin;}
  public Double getRandomIntervalMargin() {return this.randomIntervalMargin;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getVariations() != null)                 result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Variations:\n" + this.getVariations());
    if (this.getIncludeType() != null)                result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Include type: " + this.getIncludeType() + "\n");
    if (this.getDateFormat() != null)                 result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Date format: " + this.getDateFormat() + "\n");
    if (this.getTimestamp() != null)                  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Timestamp: " + this.getTimestamp() + "\n");
    if (this.getInterval() != null)                   result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Interval: " + this.getInterval() + "\n");
    if (this.getRandomIntervalProbability() != null)  result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Random interval probability: " + this.getRandomIntervalProbability() + "\n");
    if (this.getRandomIntervalMargin() != null)       result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Random interval margin: " + this.getRandomIntervalProbability() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if ((this.getTimestamp() == null && this.getInterval() != null) || (this.getTimestamp() != null && this.getInterval() == null))
      throw new IllegalArgumentException("If TimeStamp or Interval is defined, then the other attribute must also be defined.");

    if (this.getInterval() != null)
    {
      String[] interval = this.getInterval().split(" ");
      if (interval.length != 2 || !Arrays.asList("second", "seconds", "minute", "minutes", "hour", "hours", "day", "days", "month", "months", "year", "years").contains(interval[1].toLowerCase()))
        throw new IllegalArgumentException("The \"Interval\" must have the following format: \"<Number> <seconds|minutes|hours|days|months|years>\"");
    }

    if (this.getRandomIntervalProbability() != null && (this.getRandomIntervalProbability() < 0.0 || this.getRandomIntervalProbability() > 1.0))
      throw new IllegalArgumentException("The \"RandomIntervalProbability\" double must be equal or greater than 0.0 and equal or less than 1.0.");

    if (this.getRandomIntervalMargin() != null && (this.getRandomIntervalMargin() < 0.0 || this.getRandomIntervalMargin() > 1.0))
      throw new IllegalArgumentException("The \"RandomIntervalMargin\" double must be equal or greater than 0.0 and equal or less than 1.0.");

    if (this.getVariations() != null)
      this.getVariations().doCheck();

    return true;
  }
}
