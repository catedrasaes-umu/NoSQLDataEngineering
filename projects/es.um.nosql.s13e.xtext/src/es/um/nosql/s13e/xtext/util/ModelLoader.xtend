package es.um.nosql.s13e.xtext.util

import es.um.nosql.s13e.xtext.NoSQLSchemaStandaloneSetup
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.emf.ecore.EPackage

class ModelLoader extends es.um.nosql.s13e.util.ModelLoader
{
  XtextResourceSet resourceSet;

  new()
  {
    super()

    // This createInjectorAndDoEMFRegistration() performs the magic. Dont know why, but
    // XtextResourceSet is not required to load models...
    val injector = new NoSQLSchemaStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)
  }

  new(EPackage thePackage)
  {
    this()
    super.registerPackages(thePackage)
  }
}
