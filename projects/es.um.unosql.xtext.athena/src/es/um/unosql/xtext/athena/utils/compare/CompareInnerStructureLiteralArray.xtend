package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.InnerStructureLiteralArray

class CompareInnerStructureLiteralArray extends Comparator<InnerStructureLiteralArray>
{
  override boolean compare(InnerStructureLiteralArray t1, InnerStructureLiteralArray t2)
  {
    if (super.checkNulls(t1, t2))
      return false

    if (super.checkEquals(t1, t2))
      return true

    if (t1.structure === null && t2.structure === null)
      return true

    if ((t1.structure === null && t2.structure !== null) || (t1.structure !== null && t2.structure === null))
      return false

    if (t1.structure.size != t2.structure.size)
      return false

    val innerStructureLiteralComparer = new CompareInnerStructureLiteral()

    return t1.structure.forall[str1 | t2.structure.exists[str2 | innerStructureLiteralComparer.compare(str1, str2)]]
  }
}
