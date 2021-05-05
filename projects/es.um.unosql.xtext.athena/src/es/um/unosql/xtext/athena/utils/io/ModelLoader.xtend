package es.um.unosql.xtext.athena.utils.io

import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.XtextResource
import java.io.File
import org.eclipse.emf.common.util.URI
import es.um.unosql.xtext.athena.AthenaStandaloneSetup
import es.um.unosql.xtext.athena.athena.AthenaSchema
import org.eclipse.emf.ecore.EPackage
import es.um.unosql.xtext.athena.athena.AthenaPackage
import org.eclipse.emf.ecore.resource.Resource

class ModelLoader
{
  XtextResourceSet resourceSet
  String ext

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
  }

  def AthenaSchema load(String route)
  {
    return load(URI.createFileURI(route));
  }

  def AthenaSchema load(File file)
  {
    return load(URI.createFileURI(file.getAbsolutePath()));
  }

  def AthenaSchema load(URI resourceURI)
  {
    val resource = resourceSet.getResource(resourceURI, true)

    return resource.getContents().get(0) as AthenaSchema
  }

  def AthenaSchema loadAll(String route)
  {
    return loadAll(new File(route))
  }

  def AthenaSchema loadAll(File file)
  {
    return loadAll(URI.createFileURI(file.getAbsolutePath()))
  }

  def AthenaSchema loadAll(URI resourceURI)
  {
    val result = load(resourceURI)
    val parentFile = new File(resourceURI.path).parentFile

    for (File f : parentFile.listFiles.filter[f | f.isFile && !f.absolutePath.equals(resourceURI.toFileString) && f.name.endsWith(ext)])
      load(f)

    return result
  }
}
