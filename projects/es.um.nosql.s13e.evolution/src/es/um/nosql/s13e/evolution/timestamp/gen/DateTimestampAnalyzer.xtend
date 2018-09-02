package es.um.nosql.s13e.evolution.timestamp.gen


class DateTimestampAnalyzer extends TimestampAnalyzer
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
  // This Date TimestampAnalyzer looks for an attribute with a given name,
  // and translates it to nanoseconds according to the given format.
  // Expected format: yyyy-MM-ddTHH:mm:ss
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
        this._value = Date.parse(attrValue);
    }
  };
  '''
}
