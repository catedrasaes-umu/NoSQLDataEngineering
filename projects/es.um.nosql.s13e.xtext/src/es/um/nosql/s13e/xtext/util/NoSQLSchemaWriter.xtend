package es.um.nosql.s13e.xtext.util

import org.eclipse.xtext.resource.XtextResourceSet
import es.um.nosql.s13e.xtext.NoSQLSchemaStandaloneSetup
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema
import org.eclipse.xtext.resource.XtextResource
import java.io.FileOutputStream
import org.eclipse.emf.common.util.URI
import java.io.IOException

class NoSQLSchemaWriter extends es.um.nosql.s13e.util.NoSQLSchemaWriter
{
  XtextResourceSet resourceSet;

  new()
  {
    super()
    val injector = new NoSQLSchemaStandaloneSetup().createInjectorAndDoEMFRegistration()
    resourceSet = injector.getInstance(XtextResourceSet)
    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)
  }

  def void writeAsXtext(NoSQLSchema schema, String outputRoute)
  {
    val outputRes = resourceSet.createResource(URI.createURI(outputRoute)) as XtextResource;
    outputRes.getContents().add(schema);

    try
    {
      outputRes.save(new FileOutputStream(outputRoute), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
