package es.um.nosql.s13e.evolution.timestamp.gen

import es.um.nosql.s13e.evolution.timestamp.gen.AbstractTimestampAnalyzer

class DefaultTimestampAnalyzer extends AbstractTimestampAnalyzer
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
