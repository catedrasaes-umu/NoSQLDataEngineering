package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.List

class CompareList extends Comparator<List>
{
  override boolean compare(List l1, List l2)
  {
    if (super.checkNulls(l1, l2))
      return false

    if (super.checkEquals(l1, l2))
      return true

    return (l1.elementType === null && l2.elementType === null)
        || new CompareDataType().compare(l1.elementType, l2.elementType)
  }
}
