package es.um.nosql.s13e.evolution.timestamp.gen;

public class BasicTimestampAnalyzer extends AbstractTimestampAnalyzer
{
  private String comment;

  private String _attrName;

  private String _value;

  private String getAttrValueFunction;

  private String analyzeAttributeFunction;

  public BasicTimestampAnalyzer()
  {
    this("timestamp");
  }

  public BasicTimestampAnalyzer(String attrName)
  {
    comment = "// This basic TimestampAnalyzer just looks for an attribute with a given name,\n";
    comment += "// given as a long, and captures its value as the timestamp value.\n";

    _attrName = "_attrName: \"" + attrName + "\"";
    _value = "_value: \"\"";

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


var TimestampAnalyzer =
{
_attrName:  "timestamp",
_value:     "",

getAttrValue: function()
{
 if (this._value === null)
   return new Date().getTime();
 else
   return this._value;
},
analyzeAttribute: function(attrName, attrValue)
{
 if (attrName === this._attrName)
   this._value = attrValue;
}
};
