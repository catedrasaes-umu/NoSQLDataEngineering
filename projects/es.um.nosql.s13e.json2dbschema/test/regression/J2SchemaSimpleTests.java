package regression;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import es.um.nosql.s13e.json2dbschema.intermediate.raw.SchemaComponent;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.util.SchemaPrinter;
import es.um.nosql.s13e.json2dbschema.main.util.JSON2RawSchema;
import es.um.nosql.s13e.json2dbschema.main.util.RawSchemaGen;

public class J2SchemaSimpleTests
{
  private final static String HEADER = "(count: 0)(ts: 0)<null>";

  @Test
  public void test1() 
  {
    try
    {
      JsonNode n = JSON2RawSchema.fromJSON("{\"a\": 1}");

      SchemaComponent sc = RawSchemaGen.deSchema(null, n);
      String result = SchemaPrinter.schemaString(sc);

      assertEquals("test1", HEADER + "{\"a\": Number } ", result);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Test
  public void test2() 
  {
    try
    {
      JsonNode n = JSON2RawSchema.fromJSON("{\"a\": [1,2,3]}");

      SchemaComponent sc = RawSchemaGen.deSchema(null, n);
      String result = SchemaPrinter.schemaString(sc);

      assertEquals("test2", HEADER + "{\"a\": [Number ] } ", result);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Test
  public void test3() 
  {
    try
    {
      JsonNode n = JSON2RawSchema.fromJSON("{\"c\": 1,\"b\": 1,\"a\": 1}");

      SchemaComponent sc = RawSchemaGen.deSchema(null, n);
      String result = SchemaPrinter.schemaString(sc);

      assertEquals("test3", HEADER + "{\"a\": Number \"b\": Number \"c\": Number } ", result);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
