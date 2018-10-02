package es.um.nosql.s13e.xtext;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.compare.CompareNoSQLSchema;

public class ModelLoaderTest
{
  private String route1 = "../es.um.nosql.models/mongosongs/mongosongs.xmi";
  private String route2 = "models/test1.nosqlschema";

  @Before
  public void setUp()
  {
  }

  @Test
  public void testModelLoader()
  {
    Injector injector = new NoSQLSchemaStandaloneSetup().createInjectorAndDoEMFRegistration();
    XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);

    resourceSet.getPackageRegistry().put(NoSQLSchemaPackage.eINSTANCE.getNsURI(), NoSQLSchemaPackage.eINSTANCE);

    resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
    Resource resource = resourceSet.getResource(URI.createFileURI(route1), true);

    NoSQLSchema schema1 = (NoSQLSchema)resource.getContents().get(0);


    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema2 = loader.load(new File(route1), NoSQLSchema.class);

//    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
//    NoSQLSchema nosqlschema = loader.load(new File(route2), NoSQLSchema.class);

    assertTrue(new CompareNoSQLSchema().compare(schema1, schema2));
  }
}
