package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.ComposedReference

class CompareComposedReference extends Comparator<ComposedReference>
{
  override boolean compare(ComposedReference r1, ComposedReference r2)
  {
    if (super.checkNulls(r1, r2))
      return false

    if (super.checkEquals(r1, r2))
      return true

    if (!r1.names.equals(r2))
      return false

    return (r1.target === null && r2.target === null) || (r1.target.ref.name === r2.target.ref.name)
  }
}
