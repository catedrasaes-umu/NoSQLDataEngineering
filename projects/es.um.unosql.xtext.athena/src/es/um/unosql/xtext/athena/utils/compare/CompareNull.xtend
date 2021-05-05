package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.Null

class CompareNull extends Comparator<Null>
{
  override boolean compare(Null n1, Null n2)
  {
    if (super.checkNulls(n1, n2))
      return false

    if (super.checkEquals(n1, n2))
      return true

    return true
  }
}