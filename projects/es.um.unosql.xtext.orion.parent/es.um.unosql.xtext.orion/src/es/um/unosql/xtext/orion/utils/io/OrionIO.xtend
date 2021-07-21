package es.um.unosql.xtext.orion.utils.io

import org.eclipse.xtext.resource.XtextResourceSet
import java.util.Map
import org.eclipse.emf.ecore.EPackage
import es.um.unosql.xtext.orion.orion.OrionPackage
import org.eclipse.emf.ecore.resource.Resource
import es.um.unosql.xtext.orion.OrionStandaloneSetup
import org.eclipse.xtext.resource.XtextResource
import es.um.unosql.xtext.orion.orion.OrionOperations
import java.nio.file.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import java.util.HashMap
import org.eclipse.emf.ecore.xmi.XMLResource
import java.io.FileOutputStream
import es.um.unosql.xtext.athena.utils.io.AthenaIO

class OrionIO
{
  XtextResourceSet resourceSet
  String ext
  Map<Object, Object> options

  new()
  {
    ext = "orion"
    EPackage.Registry.INSTANCE.put(OrionPackage.eNS_URI, OrionPackage.eINSTANCE);
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(ext, OrionPackage.eINSTANCE);

    val injector = new OrionStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)

    options = new HashMap<Object, Object>()
    options.put(XtextResource.OPTION_ENCODING, "UTF-8")
    options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE)
  }

  def OrionOperations load(Path inputPath)
  {
    val resourceURI = URI.createFileURI(inputPath.toString)
    val resource = resourceSet.getResource(resourceURI, true)
    val result = resource.getContents().get(0) as OrionOperations

    if (result.imports !== null && result.imports.importedNamespace.eIsProxy)
      result.imports.importedNamespace = new AthenaIO().resolve(inputPath.parent, result.imports.importedNamespace)

    return result
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
      // TODO: For the moment we do not solve serializations with Imports. Something wrong with the Global scope provider...?
    }

    return resource.serializer.serialize(obj)
  }

  def writeXMI(OrionOperations operations, Path outputPath)
  {
    val theURI = URI.createURI(outputPath.toString)
    var resource = resourceSet.getResource(theURI, false)

    if (resource === null)
      resource = resourceSet.createResource(theURI)

    write(resource, operations, outputPath)
  }

  def write(OrionOperations operations, Path outputPath)
  {
    val theURI = URI.createURI(outputPath.toString)
    var resource = resourceSet.getResource(theURI, false) as XtextResource

    if (resource === null)
      resource = resourceSet.createResource(theURI) as XtextResource

    write(resource, operations, outputPath)
  }

  private def write(Resource resource, OrionOperations operations, Path outputPath)
  {
    resource.getContents().add(operations)
    // TODO: For the moment we do not solve writes with Imports. Something wrong with the Global scope provider...?

    resource.save(new FileOutputStream(outputPath.toFile), options)
  }
}
