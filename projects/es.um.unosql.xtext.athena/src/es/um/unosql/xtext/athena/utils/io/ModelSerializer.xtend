package es.um.unosql.xtext.athena.utils.io

import es.um.unosql.xtext.athena.athena.AthenaSchema
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import es.um.unosql.xtext.athena.AthenaStandaloneSetup
import org.eclipse.xtext.EcoreUtil2

class ModelSerializer
{
  val XtextResourceSet resourceSet

  new()
  {
    val injector = new AthenaStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)
  }

  def String serialize(EObject obj)
  {
    val schema = obj instanceof AthenaSchema ? obj as AthenaSchema : EcoreUtil2.getRootContainer(obj) as AthenaSchema
    val theURI = URI.createURI("dummy/" + schema.name + ".athena")
    var resource = resourceSet.getResource(theURI, false) as XtextResource

    if (resource === null)
    {
      resource = resourceSet.createResource(theURI) as XtextResource
      resource.getContents().add(schema)
    }
    // TODO: Examples with Imports are not correctly solved yet.

    return resource.serializer.serialize(obj)
  }
}
