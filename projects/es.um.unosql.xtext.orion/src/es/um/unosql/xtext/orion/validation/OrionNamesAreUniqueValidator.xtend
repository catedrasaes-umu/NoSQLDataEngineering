package es.um.unosql.xtext.orion.validation

import org.eclipse.xtext.validation.NamesAreUniqueValidationHelper
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.ValidationMessageAcceptor
import org.eclipse.emf.ecore.EObject
import es.um.unosql.xtext.orion.orion.AggrAdd

class OrionNamesAreUniqueValidator extends NamesAreUniqueValidationHelper
{
  override void checkUniqueNames(Iterable<IEObjectDescription> descriptions, CancelIndicator cancelIndicator, ValidationMessageAcceptor acceptor)
  {
    super.checkUniqueNames(descriptions.reject[eobj | isContainedInInnerStructureLiteralArray(eobj.EObjectOrProxy)], cancelIndicator, acceptor)
  }

  private def boolean isContainedInInnerStructureLiteralArray(EObject obj)
  {
    if (obj.eContainer === null)
      return false

    if (obj.eContainer instanceof AggrAdd)
      return true

    return isContainedInInnerStructureLiteralArray(obj.eContainer)
  }
}
