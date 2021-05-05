package es.um.unosql.xtext.orion.utils.io

import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.emf.common.util.URI
import java.io.FileOutputStream
import java.util.Map
import java.util.HashMap
import es.um.unosql.xtext.orion.OrionStandaloneSetup
import es.um.unosql.xtext.orion.orion.OrionOperations
import org.eclipse.emf.ecore.xmi.XMLResource
import org.eclipse.emf.ecore.resource.Resource

class ModelWriter
{
  XtextResourceSet resourceSet
  Map<Object, Object> options

  new()
  {
    val injector = new OrionStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)
    options = new HashMap<Object, Object>()
    options.put(XtextResource.OPTION_ENCODING, "UTF-8")
    options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
  }

  def writeXMI(OrionOperations operations, String outputRoute)
  {
    val theURI = URI.createURI(outputRoute)
    var resource = resourceSet.getResource(theURI, false)

    if (resource === null)
      resource = resourceSet.createResource(theURI)

    write(resource, operations, outputRoute)
  }

  def write(OrionOperations operations, String outputRoute)
  {
    val theURI = URI.createURI(outputRoute)
    var resource = resourceSet.getResource(theURI, false) as XtextResource

    if (resource === null)
      resource = resourceSet.createResource(theURI) as XtextResource

    write(resource, operations, outputRoute)
  }

  private def write(Resource resource, OrionOperations operations, String outputRoute)
  {
    resource.getContents().add(operations)

    resource.save(new FileOutputStream(outputRoute), options)
  }
}
