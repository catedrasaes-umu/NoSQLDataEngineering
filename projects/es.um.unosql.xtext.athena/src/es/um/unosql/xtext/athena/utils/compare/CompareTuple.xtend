package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.Tuple

class CompareTuple extends Comparator<Tuple>
{
  override boolean compare(Tuple t1, Tuple t2)
  {
    if (super.checkNulls(t1, t2))
      return false

    if (super.checkEquals(t1, t2))
      return true

    if (t1.elements === null && t2.elements === null)
      return true

    if ((t1.elements === null && t2.elements !== null) || (t1.elements !== null && t2.elements === null))
      return false

    if (t1.elements.size != t2.elements.size)
      return false

    val typeComparer = new CompareDataType()

    return t1.elements.forall[type1 | t2.elements.exists[type2 | typeComparer.compare(type1, type2)]]
  }
}
