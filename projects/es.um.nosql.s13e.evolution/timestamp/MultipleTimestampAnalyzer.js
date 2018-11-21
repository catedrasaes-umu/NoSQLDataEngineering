// This multiple TimestampAnalyzer just looks for an array of given attribute names,
// and captures the first matching occurrence value as the timestamp value.
var TimestampAnalyzer =
{
  _attrName: ["timestamp"],
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
