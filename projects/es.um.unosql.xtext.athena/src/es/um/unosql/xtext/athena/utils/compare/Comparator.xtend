package es.um.unosql.xtext.athena.utils.compare

import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.EObject

abstract class Comparator<T>
{
  def boolean compare(T o1, T o2)

  def boolean checkNulls(T o1, T o2)
  {
    return o1 === null || o2 === null
  }

  def boolean checkEquals(T o1, T o2)
  {
    return o1 == o2 || o1.equals(o2) || EcoreUtil.equals(o1 as EObject, o2 as EObject)
  }
}
