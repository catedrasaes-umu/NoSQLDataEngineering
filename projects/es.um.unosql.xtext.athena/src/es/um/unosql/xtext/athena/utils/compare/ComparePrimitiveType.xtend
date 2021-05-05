package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.PrimitiveType
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.athena.OptionPrimitiveType

class ComparePrimitiveType extends Comparator<PrimitiveType>
{
  override boolean compare(PrimitiveType p1, PrimitiveType p2)
  {
    if (super.checkNulls(p1, p2))
      return false

    if (super.checkEquals(p1, p2))
      return true

    if (p1 instanceof SinglePrimitiveType && p2 instanceof SinglePrimitiveType)
      return new CompareSinglePrimitiveType().compare(p1 as SinglePrimitiveType, p2 as SinglePrimitiveType)

    if (p1 instanceof OptionPrimitiveType && p2 instanceof OptionPrimitiveType)
      return new CompareOptionPrimitiveType().compare(p1 as OptionPrimitiveType, p2 as OptionPrimitiveType)

    return false
  }
}
