package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.OptionPrimitiveType

class CompareOptionPrimitiveType extends Comparator<OptionPrimitiveType>
{
  override boolean compare(OptionPrimitiveType p1, OptionPrimitiveType p2)
  {
    if (super.checkNulls(p1, p2))
      return false

    if (super.checkEquals(p1, p2))
      return true

    if (p1.options === null && p2.options === null)
      return true

    if ((p1.options === null && p2.options !== null) || (p1.options !== null && p2.options === null))
      return false

    if (p1.options.size != p2.options.size)
      return false

    val comparer = new CompareSinglePrimitiveType()


    return p1.options.forall[type1 | p2.options.exists[type2 | comparer.compare(type1, type2)]]
  }
}
