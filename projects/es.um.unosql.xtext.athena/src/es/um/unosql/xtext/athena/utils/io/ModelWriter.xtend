package es.um.unosql.xtext.athena.utils.io

import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.emf.common.util.URI
import java.io.FileOutputStream
import java.util.Map
import java.util.HashMap
import es.um.unosql.xtext.athena.athena.AthenaSchema
import es.um.unosql.xtext.athena.AthenaStandaloneSetup
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.xmi.XMLResource

class ModelWriter
{
  XtextResourceSet resourceSet
  Map<Object, Object> options

  new()
  {
    val injector = new AthenaStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)
    options = new HashMap<Object, Object>()
    options.put(XtextResource.OPTION_ENCODING, "UTF-8")
    options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
  }

  def writeXMI(AthenaSchema schema, String outputRoute)
  {
    val theURI = URI.createURI(outputRoute)
    var resource = resourceSet.getResource(theURI, false)

    if (resource === null)
      resource = resourceSet.createResource(theURI)

    write(resource, schema, outputRoute)
  }

  def write(AthenaSchema schema, String outputRoute)
  {
    val theURI = URI.createURI(outputRoute)
    var resource = resourceSet.getResource(theURI, false) as XtextResource

    if (resource === null)
      resource = resourceSet.createResource(theURI) as XtextResource

    write(resource, schema, outputRoute)
  }

  private def write(Resource resource, AthenaSchema schema, String outputRoute)
  {
    resource.getContents().add(schema)
    // TODO: Examples with Imports are not correctly solved yet.

    resource.save(new FileOutputStream(outputRoute), options)
  }
}
