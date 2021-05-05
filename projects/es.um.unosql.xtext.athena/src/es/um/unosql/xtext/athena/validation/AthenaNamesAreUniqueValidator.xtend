package es.um.unosql.xtext.athena.validation

import org.eclipse.xtext.validation.NamesAreUniqueValidationHelper
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.ValidationMessageAcceptor
import es.um.unosql.xtext.athena.athena.InnerStructureLiteralArray
import org.eclipse.emf.ecore.EObject

class AthenaNamesAreUniqueValidator extends NamesAreUniqueValidationHelper
{
  // The correct way would be to limit Scope of InnerStructureLiteralArray to its content, and not to its whole EntityDecl.
  // By doing so we should probably override the getAssociatedClusterType, but I dont have the slightest idea of how to do that.
  //    if (AthenaPackage.Literals.FEATURE == eClass) return null;
  // Instead of that we are disabling UniqueName checking inside InnerStructureLiteralArray. It works more or less fine.

  override void checkUniqueNames(Iterable<IEObjectDescription> descriptions, CancelIndicator cancelIndicator, ValidationMessageAcceptor acceptor)
  {
    super.checkUniqueNames(descriptions.reject[eobj | isContainedInInnerStructureLiteralArray(eobj.EObjectOrProxy)], cancelIndicator, acceptor)
  }

  private def boolean isContainedInInnerStructureLiteralArray(EObject obj)
  {
    if (obj.eContainer === null)
      return false

    if (obj.eContainer instanceof InnerStructureLiteralArray)
      return true

    return isContainedInInnerStructureLiteralArray(obj.eContainer)
  }
}
