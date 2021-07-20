package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.InnerStructureLiteral

class CompareInnerStructureLiteral extends Comparator<InnerStructureLiteral>
{
  override boolean compare(InnerStructureLiteral t1, InnerStructureLiteral t2)
  {
    if (super.checkNulls(t1, t2))
      return false

    if (super.checkEquals(t1, t2))
      return true

    if (t1.spec === null && t2.spec === null)
      return true

    if ((t1.spec === null && t2.spec !== null) || (t1.spec !== null && t2.spec === null))
      return false

    if (t1.spec.features.size != t2.spec.features.size)
      return false

    val featComparer = new CompareFeature()

    return t1.spec.features.forall[feat1 | t2.spec.features.exists[feat2 | featComparer.compare(feat1, feat2)]]
  }
}
