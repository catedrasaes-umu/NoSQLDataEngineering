package es.um.nosql.s13e.evolution.timestamp.gen


class BasicTimestampAnalyzer extends TimestampAnalyzer
{
  String attrName;

  new()
  {
    this("timestamp");
  }

  new(String attrName)
  {
    this.attrName = attrName;
  }

  override toString()
  '''
  // This basic TimestampAnalyzer just looks for an attribute with a given name,
  // given as a long, and captures its value as the timestamp value.
  var TimestampAnalyzer =
  {
    _attrName: "«attrName»",
    _value: "",

    getAttrValue: function()
    {
      if (this._value === "")
        return 0;
      else
        return this._value;
    },
    analyzeAttribute: function(attrName, attrValue)
    {
      if (attrName === this._attrName)
        this._value = attrValue;
    }
  };
  '''
}
