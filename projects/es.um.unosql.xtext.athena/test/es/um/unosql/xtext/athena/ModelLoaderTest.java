package es.um.unosql.xtext.athena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.athena.EntityDecl;
import es.um.unosql.xtext.athena.utils.Constants;
import es.um.unosql.xtext.athena.utils.io.ModelLoader;

public class ModelLoaderTest
{
  private ModelLoader loader;
  private String testFile;

  @Before
  public void setUp()
  {
    loader = new ModelLoader();
    testFile = "LoaderTest.athena";
  }

  @Test
  public void testStringModelLoader()
  {
    AthenaSchema schema = loader.load(Constants.TEST_FOLDER + testFile);

    assertEquals("LoaderTest", schema.getName());
    assertTrue(schema.getFeatureSets().isEmpty());
    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(1, schema.getEntities().size());

    EntityDecl entity = schema.getEntities().get(0);

    assertEquals("Hello", entity.getName());
    assertTrue(entity.getParents().isEmpty());
  }

  @Test
  public void testFileModelLoader()
  {
    AthenaSchema schema = loader.load(Constants.TEST_FOLDER + testFile);

    assertEquals("LoaderTest", schema.getName());
    assertTrue(schema.getFeatureSets().isEmpty());
    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(1, schema.getEntities().size());

    EntityDecl entity = schema.getEntities().get(0);

    assertEquals("Hello", entity.getName());
    assertTrue(entity.getParents().isEmpty());
  }

  @Test
  public void testURIModelLoader()
  {
    AthenaSchema schema = loader.load(URI.createFileURI(Constants.TEST_FOLDER + testFile));

    assertEquals("LoaderTest", schema.getName());
    assertTrue(schema.getFeatureSets().isEmpty());
    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(1, schema.getEntities().size());

    EntityDecl entity = schema.getEntities().get(0);

    assertEquals("Hello", entity.getName());
    assertTrue(entity.getParents().isEmpty());
  }
}
