// This basic TimestampAnalyzer just looks for an attribute with a given name,
// given as a long, and captures its value as the timestamp value.
var TimestampAnalyzer =
{
  _attrName: "timestamp",
  _value: "",

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
