package es.um.nosql.s13e.xtext.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.util.compare.CompareNoSQLSchema;
import es.um.nosql.s13e.xtext.util.ModelLoader;

public class ModelLoaderTest
{
  private String route1 = "../es.um.nosql.models/mongosongs/mongosongs.xmi";
  private String route2 = "models/test1.nosqlschema";
  private String route3 = "../es.um.nosql.models/facebook/facebook_Diff.xmi";

  private ModelLoader xtextLoader;
  private es.um.nosql.s13e.util.ModelLoader xmiLoader;

  @Before
  public void setUp()
  {
    xtextLoader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    xmiLoader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
  }

  @Test
  public void testXtextModelLoader()
  {
    NoSQLSchema schema1 = xtextLoader.load(new File(route1), NoSQLSchema.class);
    NoSQLSchema schema2 = xmiLoader.load(new File(route1), NoSQLSchema.class);

    assertTrue(new CompareNoSQLSchema().compare(schema1, schema2));
    assertNotNull(xtextLoader.load(new File(route2), NoSQLSchema.class));

    ModelLoader xtextLoader2 = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    assertNotNull(xtextLoader2.load(new File(route3), EntityDifferentiation.class));
  }
}
