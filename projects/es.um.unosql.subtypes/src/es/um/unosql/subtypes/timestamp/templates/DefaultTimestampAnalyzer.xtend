package es.um.unosql.subtypes.timestamp.templates

class DefaultTimestampAnalyzer extends TimestampAnalyzer
{
  override toString()
  '''
  // This TimestampAnalyzer doesnt perform any timestamp study.
  // It is used when a user configures the inferrer to not use any timestamp.
  var TimestampAnalyzer =
  {
    getAttrValue: function() {return 0;},
    analyzeAttribute: function(attrName, attrValue) {}
  };
  '''
}
