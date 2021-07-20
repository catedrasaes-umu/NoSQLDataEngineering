package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.athena.SimpleAggregateTarget
import es.um.unosql.xtext.athena.athena.InnerStructureLiteral
import es.um.unosql.xtext.athena.athena.InnerStructureLiteralArray

class CompareSimpleFeature extends Comparator<SimpleFeature>
{
  override boolean compare(SimpleFeature f1, SimpleFeature f2)
  {
    if (super.checkNulls(f1, f2))
      return false

    if (super.checkEquals(f1, f2))
      return true

    if (f1.isKey !== f2.isKey)
      return false

    if (f1.isOptional !== f2.isOptional)
      return false

    if (f1.isUnique !== f2.isUnique)
      return false

    if (!f1.name.equals(f2.name))
      return false

    if (f1.type === null && f2.type === null)
      return true

    if (f1.type !== null && f2.type !== null)
    {
      if (f1.type instanceof DataType && f2.type instanceof DataType)
        return new CompareDataType().compare(f1.type as DataType, f2.type as DataType)

      if (f1.type instanceof SimpleAggregateTarget && f2.type instanceof SimpleAggregateTarget)
        return new CompareSimpleAggregateTarget().compare(f1.type as SimpleAggregateTarget, f2.type as SimpleAggregateTarget)

      if (f1.type instanceof SimpleReferenceTarget && f2.type instanceof SimpleReferenceTarget)
        return new CompareSimpleReferenceTarget().compare(f1.type as SimpleReferenceTarget, f2.type as SimpleReferenceTarget)

      if (f1.type instanceof InnerStructureLiteral && f2.type instanceof InnerStructureLiteral)
        return new CompareInnerStructureLiteral().compare(f1.type as InnerStructureLiteral, f2.type as InnerStructureLiteral)

      if (f1.type instanceof InnerStructureLiteralArray && f2.type instanceof InnerStructureLiteralArray)
        return new CompareInnerStructureLiteralArray().compare(f1.type as InnerStructureLiteralArray, f2.type as InnerStructureLiteralArray)
    }

    return false
  }
}
