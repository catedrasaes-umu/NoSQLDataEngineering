package es.um.nosql.s13e.evolution.timestamp.gen

import es.um.nosql.s13e.evolution.timestamp.gen.AbstractTimestampAnalyzer

class DateTimestampAnalyzer extends AbstractTimestampAnalyzer
{
  String attrName;
  String format;
//TODO: Still not working. User has to give a valid format and Date.parse() the string to get the date.
  new(String format)
  {
    this("timestamp", format);
  }

  new(String attrName, String format)
  {
    this.attrName = attrName;
    this.format = format;
  }

  override toString()
  '''
  // This Date TimestampAnalyzer just looks for an attribute with a given name,
  // given as a long, and captures its value as the timestamp value.
  var TimestampAnalyzer =
  {
    _attrName: "«attrName»",
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
  '''
}
