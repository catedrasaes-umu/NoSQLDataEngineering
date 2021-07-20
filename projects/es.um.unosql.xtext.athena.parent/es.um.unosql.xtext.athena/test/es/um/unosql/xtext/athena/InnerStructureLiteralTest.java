package es.um.unosql.xtext.athena;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.um.unosql.xtext.athena.athena.AthenaSchema;
import es.um.unosql.xtext.athena.m2m.AthenaNormalizer;
import es.um.unosql.xtext.athena.utils.io.AthenaIO;

public class InnerStructureLiteralTest
{
  private AthenaIO athenaIO;
  private AthenaNormalizer normalizer;
  private Path testPath;

  @BeforeEach
  public void setUp()
  {
    athenaIO = new AthenaIO();
    normalizer = new AthenaNormalizer();
    testPath = Path.of("models/tests/InnerStructuresTest.athena");
  }

  @Test
  public void testInnerStructure()
  {
    AthenaSchema schema = athenaIO.load(testPath);
    schema = normalizer.m2m(schema);

    assertEquals("Schema InnerStructuresTest:1\r\n" +
        "\r\n" +
        "root entity Entity1\r\n" +
        "{\r\n" +
        "  variation 1\r\n"
        + "  {\r\n"
        + "    inner_aggr: aggr<Inner_aggr.1>+\r\n"
        + "  }\r\n" +
        "}\r\n" +
        "\r\n" +
        "root entity Entity2\r\n" +
        "{\r\n" +
        "  variation 1\r\n"
        + "  {\r\n"
        + "    inner_aggr: aggr<Inner_aggr.2>+,\r\n"
        + "    inner_inner_aggr: aggr<Inner_inner_aggr.1>+\r\n"
        + "  }\r\n" +
        "}\r\n" +
        "\r\n" +
        "root entity E3\r\n"
        + "{\r\n"
        + "  +_id: String\r\n"
        + "}\r\n" +
        "\r\n" +
        "root entity E4\r\n" +
        "{\r\n" +
        "  variation 1\r\n"
        + "  {\r\n"
        + "    +_id: Identifier,\r\n"
        + "    inner_aggr: aggr<Inner_aggr.2>+,\r\n"
        + "    inner_array_aggr: aggr<Inner_array_aggr.1 , Inner_array_aggr.2>*\r\n"
        + "  }\r\n" +
        "}\r\n" +
        "\r\n" +
        "entity Inner_array_aggr\r\n" + 
        "{\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    a1: String,\r\n"
        + "    ?a2: Number\r\n"
        + "  }\r\n" + 
        "  variation 2\r\n"
        + "  {\r\n"
        + "    a1: String,\r\n"
        + "    ?a3: Boolean\r\n"
        + "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "entity Inner_aggr\r\n" +
        "{\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    attr1: String,\r\n"
        + "    attr2: Number,\r\n"
        + "    attr3: Bool\r\n"
        + "  }\r\n" + 
        "  variation 2\r\n"
        + "  {\r\n"
        + "    attr: String,\r\n"
        + "    attr2: Number,\r\n"
        + "    ?attr3: Bool\r\n"
        + "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "entity Inner_inner_aggr\r\n" + 
        "{\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    attr4: String,\r\n"
        + "    attr5: aggr<Attr5.1>+\r\n"
        + "  }\r\n" + 
        "}\r\n" + 
        "\r\n" + 
        "entity Attr5\r\n" + 
        "{\r\n" + 
        "  variation 1\r\n"
        + "  {\r\n"
        + "    attr6: String,\r\n"
        + "    attr7: Timestamp\r\n"
        + "  }\r\n" + 
        "}\r\n", athenaIO.serialize(schema));
  }
}
