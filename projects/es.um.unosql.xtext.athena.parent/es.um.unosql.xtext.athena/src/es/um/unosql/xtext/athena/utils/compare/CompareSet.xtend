package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.Set

class CompareSet extends Comparator<Set>
{
  override boolean compare(Set s1, Set s2)
  {
    if (super.checkNulls(s1, s2))
      return false

    if (super.checkEquals(s1, s2))
      return true

    return (s1.elementType === null && s2.elementType === null)
        || new CompareDataType().compare(s1.elementType, s2.elementType)
  }
}
