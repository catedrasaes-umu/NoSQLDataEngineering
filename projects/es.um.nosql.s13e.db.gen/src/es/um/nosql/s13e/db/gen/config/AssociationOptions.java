package es.um.nosql.s13e.db.gen.config;

import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class AssociationOptions
{
  private AggregateOptions aggregates;
  public void setAggregates(AggregateOptions aggregates) {this.aggregates = aggregates;}
  public AggregateOptions getAggregates() {return this.aggregates;}

  private ReferenceOptions references;
  public void setReferences(ReferenceOptions references) {this.references = references;}
  public ReferenceOptions getReferences() {return this.references;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getAggregates() != null) result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Aggregates:\n" + this.getAggregates().toString());
    if (this.getReferences() != null) result.append(ConfigConstants.GET_TABS(this.getClass()) + "-References:\n" + this.getReferences().toString());

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getAggregates() != null)
      this.getAggregates().doCheck();

    if (this.getReferences() != null)
      this.getReferences().doCheck();

    return true;
  }
}