package es.um.unosql.xtext.athena;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.athena.EntityDecl;
import es.um.unosql.xtext.athena.utils.io.AthenaIO;

public class ModelLoaderTest
{
  private AthenaIO athenaIO;
  private Path testPath;

  @BeforeEach
  public void setUp()
  {
    athenaIO = new AthenaIO();
    testPath = Path.of("models/tests/LoaderTest.athena");
  }

  @Test
  public void testPathModelLoader()
  {
    AthenaSchema schema = athenaIO.load(testPath);

    assertEquals("LoaderTest", schema.getId().getName());
    assertEquals(1, schema.getId().getVersion());
    assertTrue(schema.getFeatureSets().isEmpty());
    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(1, schema.getEntities().size());

    EntityDecl entity = schema.getEntities().get(0);

    assertEquals("Hello", entity.getName());
    assertTrue(entity.getParents().isEmpty());
  }
}
