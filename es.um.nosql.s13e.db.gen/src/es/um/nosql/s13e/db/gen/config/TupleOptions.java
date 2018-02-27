package es.um.nosql.s13e.db.gen.config;

import es.um.nosql.s13e.db.gen.utils.Constants;

public class TupleOptions
{
  private Integer minTupleElements;
  public void setMinTupleElements(Integer minTupleElements) {this.minTupleElements = minTupleElements;}
  public Integer getMinTupleElements() {return this.minTupleElements;}

  private Integer maxTupleElements;
  public void setMaxTupleElements(Integer maxTupleElements) {this.maxTupleElements = maxTupleElements;}
  public Integer getMaxTupleElements() {return this.maxTupleElements;}

  private Double strangeTypesProbability;
  public void setStrangeTypesProbability(Double strangeTypesProbability) {this.strangeTypesProbability = strangeTypesProbability;}
  public Double getStrangeTypesProbability() {return this.strangeTypesProbability;}

  private Double nullProbability;
  public void setNullProbability(Double nullProbability) {this.nullProbability = nullProbability;}
  public Double getNullProbability() {return this.nullProbability;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinTupleElements() != null)         result.append(Constants.GET_TABS(this.getClass()) + "-Min tuple elements: " + this.getMinTupleElements() + "\n");
    if (this.getMaxTupleElements() != null)         result.append(Constants.GET_TABS(this.getClass()) + "-Max tuple elements: " + this.getMaxTupleElements() + "\n");
    if (this.getStrangeTypesProbability() != null)  result.append(Constants.GET_TABS(this.getClass()) + "-Strange types probability: " + this.getStrangeTypesProbability() + "\n");
    if (this.getNullProbability() != null)          result.append(Constants.GET_TABS(this.getClass()) + "-Null probability: " + this.getNullProbability() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinTupleElements() != null && this.getMinTupleElements() < 0)
      throw new IllegalArgumentException("Minimum tuple elements must be equal or greater than 0.");

    if (this.getMaxTupleElements() != null && this.getMaxTupleElements() < 1)
      throw new IllegalArgumentException("Maximum tuple elements must be equal or greater than 1.");

    if (this.getMinTupleElements() != null && this.getMaxTupleElements() != null && this.getMinTupleElements() > this.getMaxTupleElements())
      throw new IllegalArgumentException("Maximum tuple elements must be equal or greater than Minimum tuple elements.");

    if (this.getStrangeTypesProbability() != null && (this.getStrangeTypesProbability() < 0.0 || this.getStrangeTypesProbability() >= 1.0))
      throw new IllegalArgumentException("The \"StrangeTypesProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    if (this.getNullProbability() != null && (this.getNullProbability() < 0.0 || this.getNullProbability() >= 1.0))
      throw new IllegalArgumentException("The \"NullProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    return true;
  }
}