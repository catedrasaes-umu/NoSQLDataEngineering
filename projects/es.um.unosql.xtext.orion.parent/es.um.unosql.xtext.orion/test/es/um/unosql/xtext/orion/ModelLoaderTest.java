package es.um.unosql.xtext.orion;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.unosql.xtext.orion.orion.OrionOperations;
import es.um.unosql.xtext.orion.utils.io.OrionIO;

public class ModelLoaderTest
{
  private OrionIO orionIO;
  private Path testPath;

  @BeforeEach
  public void setUp()
  {
    orionIO = new OrionIO();
    testPath = Path.of("../es.um.unosql.xtext.athena/models/tests/LoaderTest.orion");
  }

  @Test
  public void testXtextModelLoader()
  {
    OrionOperations ops = orionIO.load(testPath);
    System.out.println(ops);
    System.out.println(ops.getImports().getImportedNamespace());
    System.out.println(ops.getImports().getImportedNamespace().getId().getName());

/*    OrionOperations orion = loader.load(Constants.TEST_FOLDER + testFile);

    assertEquals(4, orion.getOperations().size());
    assertTrue(orion.getOperations().get(0) instanceof EntityAddOp);
    assertEquals("NewEntity", ((EntityAddOp)orion.getOperations().get(0)).getSpec().getName());
    assertFalse(((EntityAddOp)orion.getOperations().get(0)).getSpec().getFeatures().isEmpty());

    assertTrue(orion.getOperations().get(1) instanceof EntityAddOp);
    assertEquals("DelEntity", ((EntityAddOp)orion.getOperations().get(1)).getSpec().getName());
    assertTrue(((EntityAddOp)orion.getOperations().get(1)).getSpec().getFeatures().isEmpty());

    assertTrue(orion.getOperations().get(2) instanceof EntityDeleteOp);
    assertEquals("DelEntity", ((EntityDeleteOp)orion.getOperations().get(2)).getSpec().getRef());

    assertTrue(orion.getOperations().get(3) instanceof EntityRenameOp);
    assertEquals("NewEntity", ((EntityRenameOp)orion.getOperations().get(3)).getSpec().getRef());
    assertEquals("RenamedEntity", ((EntityRenameOp)orion.getOperations().get(3)).getSpec().getName());

    AthenaSchema schema = orion.getImports().getImportedNamespace();

    assertEquals("LoaderTest", schema.getName());
    assertTrue(schema.getFeatureSets().isEmpty());
    assertTrue(schema.getRelationships().isEmpty());
    assertEquals(1, schema.getEntities().size());

    EntityDecl entity = schema.getEntities().get(0);

    assertEquals("Hello", entity.getName());
    assertTrue(entity.getParents().isEmpty());
*/
  }
}
