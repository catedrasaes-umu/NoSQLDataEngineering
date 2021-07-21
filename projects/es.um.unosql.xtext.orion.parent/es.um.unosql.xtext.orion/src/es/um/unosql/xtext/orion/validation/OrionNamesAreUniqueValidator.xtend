package es.um.unosql.xtext.orion.validation

import org.eclipse.xtext.validation.NamesAreUniqueValidationHelper
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.ValidationMessageAcceptor
import org.eclipse.emf.ecore.EObject
import es.um.unosql.xtext.orion.orion.AggregateAddSpec
import es.um.unosql.xtext.orion.orion.EntityAddSpec

class OrionNamesAreUniqueValidator extends NamesAreUniqueValidationHelper
{
  override void checkUniqueNames(Iterable<IEObjectDescription> descriptions, CancelIndicator cancelIndicator, ValidationMessageAcceptor acceptor)
  {
    super.checkUniqueNames(descriptions, cancelIndicator, acceptor)
//    super.checkUniqueNames(descriptions.reject[eobj | isCertainOperation(eobj.EObjectOrProxy) || isContainedInInnerStructureLiteralArray(eobj.EObjectOrProxy)*/], cancelIndicator, acceptor)
  }
/*
  private def dispatch boolean isCertainOperation(EObject eobj)
  {
    if (eobj instanceof EntityAddSpec)
      return false
    return true
  }
*/
/**
  private def dispatch boolean isContainedInInnerStructureLiteralArray(EObject obj)
  {
    if (obj.eContainer === null)
      return false

    if (obj.eContainer instanceof AggregateAddSpec)
      return true

    return isContainedInInnerStructureLiteralArray(obj.eContainer)
  }
*/
}
