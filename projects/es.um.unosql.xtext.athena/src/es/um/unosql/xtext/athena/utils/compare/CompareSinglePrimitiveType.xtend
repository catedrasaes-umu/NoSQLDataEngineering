package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.SinglePrimitiveType

class CompareSinglePrimitiveType extends Comparator<SinglePrimitiveType>
{
  override boolean compare(SinglePrimitiveType p1, SinglePrimitiveType p2)
  {
    if (super.checkNulls(p1, p2))
      return false

    if (super.checkEquals(p1, p2))
      return true

    if ((p1.typename === null && p2.typename === null) ||
      (p1.typename !== null && p2.typename !== null && p1.typename.equals(p2.typename)))
      return true

    // TODO: We do not check Unrestricted/Restricted classes for now.

    return false
  }
}
