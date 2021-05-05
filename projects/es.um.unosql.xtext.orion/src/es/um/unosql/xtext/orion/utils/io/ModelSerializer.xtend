package es.um.unosql.xtext.orion.utils.io

import org.eclipse.xtext.resource.XtextResourceSet
import es.um.unosql.xtext.orion.OrionStandaloneSetup
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import es.um.unosql.xtext.orion.orion.OrionOperations
import org.eclipse.xtext.EcoreUtil2

class ModelSerializer
{
  val XtextResourceSet resourceSet

  new()
  {
    val injector = new OrionStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)
  }

  def String serialize(EObject obj)
  {
    val operations = obj instanceof OrionOperations ? obj as OrionOperations : EcoreUtil2.getRootContainer(obj) as OrionOperations
    val theURI = URI.createURI("dummy/" + operations.name + ".orion")
    var resource = resourceSet.getResource(theURI, false) as XtextResource

    if (resource === null)
    {
      resource = resourceSet.createResource(theURI) as XtextResource
      resource.getContents().add(operations)
    }

    return resource.serializer.serialize(obj)
  }
}
