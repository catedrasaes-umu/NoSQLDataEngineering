package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.RelationshipDecl
import java.util.ArrayList

class CompareSimpleReferenceTarget extends Comparator<SimpleReferenceTarget>
{
  override boolean compare(SimpleReferenceTarget r1, SimpleReferenceTarget r2)
  {
    if (super.checkNulls(r1, r2))
      return false

    if (super.checkEquals(r1, r2))
      return true

    if ((r1.featuredBy.isEmpty && !r2.featuredBy.isEmpty) || (!r1.featuredBy.isEmpty && r2.featuredBy.isEmpty))
      return false

    if (!r1.featuredBy.isEmpty)
    {
      if (r1.featuredBy.size != r2.featuredBy.size)
        return false

      if (r1.featuredBy.head instanceof RelationshipDecl && r2.featuredBy.head instanceof RelationshipDecl)
      {
        if (!(r1.featuredBy.head as RelationshipDecl).name.equals((r2.featuredBy.head as RelationshipDecl).name))
          return false
      }
      else if (r1.featuredBy.head instanceof VariationDecl && r2.featuredBy.head instanceof VariationDecl)
      {
        val r2ElemCopy = new ArrayList<VariationDecl>(r2.featuredBy.map[x | x as VariationDecl])

        for (VariationDecl v1 : r1.featuredBy.map[x | x as VariationDecl])
        {
          val varToErase = r2ElemCopy.findFirst[v2 |
            v1.name.equals(v2.name) &&
            (v1.eContainer as RelationshipDecl).name.equals((v2.eContainer as RelationshipDecl).name)]
          if (varToErase !== null)
            r2ElemCopy.remove(varToErase)
          else
            return false
        }
      }
    }

    if (r1.ref === null || r2.ref === null || !r1.ref.name.equals(r2.ref.name))
      return false

    if ((r1.type === null && r2.type !== null) || (r1.type !== null && r2.type === null) || (!r1.type.equals(r2.type)))
      return false

    if ((r1.multiplicity === null && r2.multiplicity !== null) ||
        (r1.multiplicity !== null && r2.multiplicity === null) ||
        !r1.multiplicity.equals(r2.multiplicity))
      return false

    return true
  }
}
