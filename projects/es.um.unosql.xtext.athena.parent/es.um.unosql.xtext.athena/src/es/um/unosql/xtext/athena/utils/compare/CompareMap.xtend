package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.Map

class CompareMap extends Comparator<Map>
{
  override boolean compare(Map m1, Map m2)
  {
    if (super.checkNulls(m1, m2))
      return false

    if (super.checkEquals(m1, m2))
      return true

    return ((m1.keyType === null && m2.keyType === null) || new ComparePrimitiveType().compare(m1.keyType, m2.keyType))
        && ((m1.valueType === null && m2.valueType === null) || new CompareDataType().compare(m1.valueType, m2.valueType));
  }
}
