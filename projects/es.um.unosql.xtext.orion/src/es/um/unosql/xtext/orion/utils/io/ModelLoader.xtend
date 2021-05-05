package es.um.unosql.xtext.orion.utils.io

import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.XtextResource
import java.io.File
import org.eclipse.emf.common.util.URI
import es.um.unosql.xtext.orion.OrionStandaloneSetup
import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.orion.orion.OrionPackage
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer
import es.um.unosql.xtext.orion.utils.Constants

class ModelLoader
{
  XtextResourceSet resourceSet

  new()
  {
    EPackage.Registry.INSTANCE.put(OrionPackage.eNS_URI, OrionPackage.eINSTANCE);
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("orion", OrionPackage.eINSTANCE);

    val injector = new OrionStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)
  }

  def OrionOperations load(String route)
  {
    return load(new File(route));
  }

  def OrionOperations load(File file)
  {
    return load(URI.createFileURI(file.getAbsolutePath()));
  }

  def OrionOperations load(URI resourceURI)
  {
    val resource = resourceSet.getResource(resourceURI, true)
    val result = resource.getContents().get(0) as OrionOperations

    if (Constants.ORION_REFERENCE_MAP.containsKey(result.name))
      result.imports.importedNamespace = new AthenaNormalizer().m2m(new es.um.unosql.xtext.athena.utils.io.ModelLoader().load(Constants.ORION_REFERENCE_MAP.get(result.name)))

    return result
  }
}
