package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.SchemaTypeDecl
import java.util.ArrayList
import es.um.unosql.xtext.athena.athena.SimpleAggregationTarget

class CompareSimpleAggregationTarget extends Comparator<SimpleAggregationTarget>
{
  override boolean compare(SimpleAggregationTarget a1, SimpleAggregationTarget a2)
  {
    if (super.checkNulls(a1, a2))
      return false

    if (super.checkEquals(a1, a2))
      return true

    if ((a1.multiplicity === null && a2.multiplicity !== null) ||
        (a1.multiplicity !== null && a2.multiplicity === null) ||
        !a1.multiplicity.equals(a2.multiplicity))
      return false

    if (a1.aggr.size !== a2.aggr.size)
      return false

    if (a1.aggr.head instanceof VariationDecl && a2.aggr.head instanceof VariationDecl)
    {
      val a2ElemCopy = new ArrayList<VariationDecl>(a2.aggr.map[x | x as VariationDecl])

      for (VariationDecl v1 : a1.aggr.map[x | x as VariationDecl])
      {
        val varToErase = a2ElemCopy.findFirst[v2 |
          v1.name.equals(v2.name) &&
          (v1.eContainer as SchemaTypeDecl).name.equals((v2.eContainer as SchemaTypeDecl).name)]
        if (varToErase !== null)
          a2ElemCopy.remove(varToErase)
        else
          return false
      }

      val a1Var = a1.aggr as VariationDecl
      val a2Var = a2.aggr as VariationDecl

      return a1Var.name.equals(a2Var.name) && (a1Var.eContainer as SchemaTypeDecl).name.equals((a2Var.eContainer as SchemaTypeDecl).name)
    }
    else if (a1.aggr.head instanceof SchemaTypeDecl && a1.aggr.head instanceof SchemaTypeDecl)
      return (a1.aggr as SchemaTypeDecl).name.equals((a2.aggr as SchemaTypeDecl).name)

    return false
  }
}
