package es.um.nosql.s13e.evolution.timestamp.gen


class DateTimestampAnalyzer extends TimestampAnalyzer
{
  String attrName;
  String format;

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
  // This Date TimestampAnalyzer looks for an attribute with a given name,
  // and translates it to nanoseconds according to the given format.
  var TimestampAnalyzer =
  {
    _attrName: "«attrName»",
    _format: "«format»",
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
      if (attrName === this._attrName && Date.parse(attrValue, format)...¿?)
        this._value = attrValue;
    }
  };
  '''
} //TODO: Still not working. User has to give a valid format and Date.parse() the string to get the date.
