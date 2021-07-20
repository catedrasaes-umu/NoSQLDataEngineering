package es.um.unosql.xtext.athena;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer;
import es.um.unosql.xtext.athena.utils.io.AthenaIO;

public class CrossReferenceTest
{
  private AthenaIO athenaIO;
  private Path testPath;

  @BeforeEach
  public void setUp()
  {
    athenaIO = new AthenaIO();
    testPath = Path.of("models/tests/CrossReferenceTest1.athena");
  }

  @Test
  public void testCrossreferences()
  {
    AthenaSchema schema = athenaIO.loadAll(testPath);

    assertEquals("Schema CrossRef1:1\r\n" +
        "\r\n" +
        "import CrossRef2:1\r\n" +
        "\r\n" +
        "FSet fSet1 { attr1, attr2 }\r\n" +
        "FSet fSet2 CrossRef2:1.Hello\r\n" +
        "FSet fSet3 CrossRef2:1.Hello.1\r\n" +
        "FSet fSet4 CrossRef2:1.World\r\n", athenaIO.serialize(schema));
  }

  @Test
  public void testCrossreferencesAndNormalizer()
  {
    AthenaSchema schema = athenaIO.loadAll(testPath);
    schema = new AthenaNormalizer().m2m(schema);

    assertEquals("Schema CrossRef1:1\r\n" +
        "\r\n" +
        "import CrossRef2:1\r\n" +
        "\r\n" +
        "fset fSet1\r\n" +
        "{\r\n" +
        "  attr1,\r\n" +
        "  attr2\r\n" +
        "}\r\n" +
        "\r\n" +
        "fset fSet2\r\n" +
        "{\r\n" +
        "  +_id: String,\r\n" +
        "  hello: String,\r\n" +
        "  world: Number\r\n" +
        "}\r\n" +
        "\r\n" +
        "fset fSet3\r\n" +
        "{\r\n" +
        "  in_the: Boolean,\r\n" +
        "  morning: List<String>\r\n" +
        "}\r\n" +
        "\r\n" +
        "fset fSet4\r\n" +
        "{\r\n" +
        "  tests: String,\r\n" +
        "  in_the: Number,\r\n" +
        "  night: Tuple\r\n" +
        "}\r\n" +
        "\r\n", athenaIO.serialize(schema));
  }
}
