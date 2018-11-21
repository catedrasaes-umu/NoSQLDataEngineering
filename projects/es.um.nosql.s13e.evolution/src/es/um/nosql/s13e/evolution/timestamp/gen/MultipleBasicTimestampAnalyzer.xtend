package es.um.nosql.s13e.evolution.timestamp.gen


class MultipleBasicTimestampAnalyzer extends TimestampAnalyzer
{
  String[] attrNames;

  new()
  {
    this(#["timestamp"]);
  }

  new(String[] attrNames)
  {
    this.attrNames = attrNames;
  }

  override toString()
  '''
  // This multiple Basic TimestampAnalyzer just looks for an array of given attribute names,
  // and captures the first matching occurrence value as the timestamp value.
  var TimestampAnalyzer =
  {
    _attrName: ["«attrNames.join(", ")»"],
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
      if (this._attrName.indexOf(attrName) > -1)
        this._value = attrValue;
    }
  };
  '''
}
