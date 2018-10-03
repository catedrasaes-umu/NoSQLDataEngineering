package es.um.nosql.s13e.xtext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

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

  @Test
  public void testUnifiedModelLoader()
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema1 = loader.load(new File(route1), NoSQLSchema.class);
    es.um.nosql.s13e.util.ModelLoader loader2 = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema2 = loader2.load(new File(route1), NoSQLSchema.class);

    assertTrue(new CompareNoSQLSchema().compare(schema1, schema2));
    assertNotNull(loader.load(new File(route2), NoSQLSchema.class));

    ModelLoader loader3 = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    assertNotNull(loader3.load(new File(route3), EntityDifferentiation.class));
  }
}
