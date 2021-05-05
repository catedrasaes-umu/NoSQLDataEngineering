package es.um.unosql.xtext.athena.utils.compare

import es.um.unosql.xtext.athena.athena.Feature
import es.um.unosql.xtext.athena.athena.ComposedReference
import es.um.unosql.xtext.athena.athena.SimpleFeature

class CompareFeature extends Comparator<Feature>
{
  override boolean compare(Feature f1, Feature f2)
  {
    if (super.checkNulls(f1, f2))
      return false

    if (super.checkEquals(f1, f2))
      return true

    if (f1 instanceof ComposedReference && f2 instanceof ComposedReference)
      return new CompareComposedReference().compare(f1 as ComposedReference, f2 as ComposedReference)

    if (f1 instanceof SimpleFeature && f2 instanceof SimpleFeature)
      return new CompareSimpleFeature().compare(f1 as SimpleFeature, f2 as SimpleFeature)

    return false
  }
}
