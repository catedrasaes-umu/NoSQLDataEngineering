// This Basic TimestampAnalyzer just looks for an attribute, or an array of given attribute names,
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
    if (this._attrName.indexOf(attrName) > -1 && this._value === "")
      this._value = attrValue;
  }
};
