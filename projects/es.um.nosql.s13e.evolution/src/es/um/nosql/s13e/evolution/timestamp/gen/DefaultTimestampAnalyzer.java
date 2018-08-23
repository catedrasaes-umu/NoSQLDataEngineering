package es.um.nosql.s13e.evolution.timestamp.gen;

public class DefaultTimestampAnalyzer extends AbstractTimestampAnalyzer
{
  private String comment;

  private String getAttrValueFunction;

  private String analyzeAttributeFunction;

  public DefaultTimestampAnalyzer()
  {
    comment = "// This TimestampAnalyzer doesnt perform any timestamp study.\n";
    comment += "// It is used when a user configures the inferrer to not use any timestamp.\n";
    getAttrValueFunction = "getAttrValue: function() {return 0;}";
    analyzeAttributeFunction = "analyzeAttribute: function(attrName, attrValue) {}";
  }

  @Override
  public String toString()
  {
    String TABS = "  ";
    StringBuilder result = new StringBuilder();

    result.append(comment);
    result.append("var TimestampAnalyzer =\n");
    result.append("{\n");
    result.append(TABS + getAttrValueFunction + ",\n");
    result.append(TABS + analyzeAttributeFunction + "\n");
    result.append("};\n");

    return result.toString();
  }
}
