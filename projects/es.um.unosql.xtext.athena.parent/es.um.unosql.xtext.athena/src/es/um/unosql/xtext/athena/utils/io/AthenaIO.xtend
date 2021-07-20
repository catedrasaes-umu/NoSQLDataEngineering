package es.um.unosql.xtext.athena.utils.io

import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.emf.ecore.EPackage
import es.um.unosql.xtext.athena.athena.AthenaPackage
import es.um.unosql.xtext.athena.AthenaStandaloneSetup
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.emf.ecore.resource.Resource
import es.um.unosql.xtext.athena.athena.AthenaSchema
import org.eclipse.emf.common.util.URI
import java.io.File
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import java.util.Map
import java.util.HashMap
import org.eclipse.emf.ecore.xmi.XMLResource
import java.io.FileOutputStream
import java.nio.file.Path

class AthenaIO
{
  XtextResourceSet resourceSet
  String ext
  Map<Object, Object> options

  // Comment from Dietrich himself: Load all resources manually.
  // https://stackoverflow.com/questions/39368784/xtext-cross-reference-to-other-files-works-but-i-cant-access-the-eobject
  new()
  {
    ext = "athena"
    EPackage.Registry.INSTANCE.put(AthenaPackage.eNS_URI, AthenaPackage.eINSTANCE);
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(ext, AthenaPackage.eINSTANCE);

    val injector = new AthenaStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)

    options = new HashMap<Object, Object>()
    options.put(XtextResource.OPTION_ENCODING, "UTF-8")
    options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE)
  }

  /**
   * Method to load a single model that has no imports.
   * If an AthenaSchema with dependencies is read with this method all imports will throw NullPointerException from Scope.
   */
  def AthenaSchema load(Path inputPath)
  {
    val resourceURI = URI.createFileURI(inputPath.toString)
    val resource = resourceSet.getResource(resourceURI, true)
    val result = resource.getContents().get(0) as AthenaSchema

    return result
  }

  /**
   * Method used by Orion and Deimos to solve proxies.
   * It reads the given folder and procees to resolve manually the given proxy.
   */
  def AthenaSchema resolve(Path inputPath, AthenaSchema schemaProxy)
  {
    loadAll(inputPath)

    return EcoreUtil2.resolve(schemaProxy, resourceSet) as AthenaSchema
  }

  /**
   * Method to load a model with imports or an entire folder.
   * In order to do so the entire containing folder is read, so dependencies can be solved instantly.
   */
  def AthenaSchema loadAll(Path inputPath)
  {
    val inputFolder = inputPath.toFile.isDirectory ? inputPath.toFile : inputPath.toFile.parentFile

    // Walk by all folders
    for (File f : inputFolder.listFiles.filter[f | f.isDirectory])
      loadAll(f.toPath)

    // Load all models in this folder except for the one being asked
    for (File f : inputFolder.listFiles.filter[f | f.isFile && !f.toPath.equals(inputPath) && f.name.endsWith(ext)])
      load(f.toPath)

    // Load the referenced one
    if (!inputPath.toFile.isDirectory)
      return load(inputPath)

    return null
  }

  def String serialize(EObject obj)
  {
    val schema = obj instanceof AthenaSchema ? obj as AthenaSchema : EcoreUtil2.getRootContainer(obj) as AthenaSchema
    val theURI = URI.createURI("dummy/" + schema.id.name + ":" + schema.id.version + ".athena")
    var resource = resourceSet.getResource(theURI, false) as XtextResource

    if (resource === null)
    {
      resource = resourceSet.createResource(theURI) as XtextResource
      resource.getContents().add(schema)
    }

    return resource.serializer.serialize(obj)
  }

  def writeXMI(AthenaSchema schema, Path outputPath)
  {
    val theURI = URI.createURI(outputPath.toString)
    var resource = resourceSet.getResource(theURI, false)

    if (resource === null)
      resource = resourceSet.createResource(theURI)

    write(resource, schema, outputPath)
  }

  def write(AthenaSchema schema, Path outputPath)
  {
    val theURI = URI.createURI(outputPath.toString)
    var resource = resourceSet.getResource(theURI, false) as XtextResource

    if (resource === null)
      resource = resourceSet.createResource(theURI) as XtextResource

    write(resource, schema, outputPath)
  }

  private def write(Resource resource, AthenaSchema schema, Path outputPath)
  {
    resource.getContents().add(schema)
    // TODO: Examples with Imports are not correctly solved yet.

    resource.save(new FileOutputStream(outputPath.toFile), options)
  }
}
