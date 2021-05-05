package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.athena.athena.Null
import es.um.unosql.xtext.athena.athena.List
import es.um.unosql.xtext.athena.athena.Set
import es.um.unosql.xtext.athena.athena.Tuple
import es.um.unosql.xtext.athena.athena.Map
import es.um.unosql.xtext.athena.athena.PrimitiveType

class CompareDataType extends Comparator<DataType>
{
  override boolean compare(DataType t1, DataType t2)
  {
    if (super.checkNulls(t1, t2))
      return false

    if (super.checkEquals(t1, t2))
      return true

    if (t1 instanceof Null && t2 instanceof Null)
      return new CompareNull().compare(t1 as Null, t2 as Null)

    if (t1 instanceof List && t2 instanceof List)
      return new CompareList().compare(t1 as List, t2 as List)

    if (t1 instanceof Set && t2 instanceof Set)
      return new CompareSet().compare(t1 as Set, t2 as Set)

    if (t1 instanceof Tuple && t2 instanceof Tuple)
      return new CompareTuple().compare(t1 as Tuple, t2 as Tuple)

    if (t1 instanceof Map && t2 instanceof Map)
      return new CompareMap().compare(t1 as Map, t2 as Map)

    if (t1 instanceof PrimitiveType && t2 instanceof PrimitiveType)
      return new ComparePrimitiveType().compare(t1 as PrimitiveType, t2 as PrimitiveType)

    return false
  }
}
