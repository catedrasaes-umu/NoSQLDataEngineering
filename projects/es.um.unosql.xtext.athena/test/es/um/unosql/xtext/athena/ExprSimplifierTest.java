package es.um.unosql.xtext.athena;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer;
import es.um.unosql.xtext.athena.utils.Constants;
import es.um.unosql.xtext.athena.utils.io.ModelLoader;
import es.um.unosql.xtext.athena.utils.io.ModelSerializer;

public class ExprSimplifierTest
{
  private ModelLoader loader;
  private AthenaNormalizer normalizer;
  private ModelSerializer serializer;
  private String testFile;

  @Before
  public void setUp()
  {
    loader = new ModelLoader();
    normalizer = new AthenaNormalizer();
    serializer = new ModelSerializer();
    testFile = "SimplifierTest.athena";
  }

  @Test
  public void testExprSimplifier()
  {
    AthenaSchema schema = loader.load(Constants.TEST_FOLDER + testFile);
    schema = normalizer.m2m(schema);

    assertEquals("Schema SimplifierTest\r\n" +
        "\r\n" +
        "fset fs1 {\r\n"
        + "  b,\r\n"
        + "  c: Option<Bool, String, Integer>,\r\n"
        + "  d: String,\r\n"
        + "  e: Bool,\r\n"
        + "  (f, g): ref<E3>,\r\n"
        + "  z: List,\r\n"
        + "  y: Set<String>,\r\n"
        + "  x: Map<String, Integer>\r\n"
        + "}\r\n" +
        "\r\n" +
        "fset fs2\r\n"
        + "{\r\n"
        + "  b,\r\n"
        + "  c: Option<Bool, String>,\r\n"
        + "  d: String,\r\n"
        + "  e: Bool\r\n"
        + "}\r\n" +
        "\r\n" +
        "fset fs4 {\r\n"
        + "  e: Bool\r\n"
        + "}\r\n" +
        "\r\n" +
        "fset fs5 {\r\n"
        + "  e: Bool\r\n"
        + "}\r\n" +
        "\r\n" +
        "fset str0\r\n"
        + "{\r\n"
        + "  b,\r\n"
        + "  c: Option<Bool, String, Integer>,\r\n"
        + "  d: String,\r\n"
        + "  (f, g): ref<E3>\r\n"
        + "}\r\n" +
        "\r\n" +
        "entity E1\r\n"
        + "{\r\n"
        + "  +a\r\n"
        + "}\r\n" +
        "\r\n" +
        "entity E2\r\n" +
        "{\r\n" +
        "  variation 1\r\n" +
        "  {\r\n"
        + "    b,\r\n"
        + "    c: Option<Bool, String, Integer>,\r\n"
        + "    d: String,\r\n"
        + "    e: Bool,\r\n"
        + "    (f, g): ref<E3>,\r\n"
        + "    z: List,\r\n"
        + "    y: Set<String>,\r\n"
        + "    x: Map<String, Integer>,\r\n"
        + "    +a: String\r\n"
        + "  }\r\n" +
        "}\r\n" +
        "\r\n" +
        "entity E3\r\n"
        + "{\r\n"
        + "  +a\r\n"
        + "}\r\n", serializer.serialize(schema));
  }
}
