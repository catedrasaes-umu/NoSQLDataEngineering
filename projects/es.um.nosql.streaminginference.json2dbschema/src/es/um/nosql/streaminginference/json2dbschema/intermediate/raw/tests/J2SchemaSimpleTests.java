/**
 * 
 */
package es.um.nosql.streaminginference.json2dbschema.intermediate.raw.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.SchemaComponent;
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.util.SchemaPrinter;
import es.um.nosql.streaminginference.json2dbschema.main.util.JSON2RawSchema;
import es.um.nosql.streaminginference.json2dbschema.main.util.RawSchemaGen;

/**
 * @author dsevilla
 *
 */
public class J2SchemaSimpleTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() 
	{
		try {
			JsonNode n = JSON2RawSchema.fromJSON("{\"a\": 1}");
			
			SchemaComponent sc = RawSchemaGen.deSchema(null, n);
			String result = SchemaPrinter.schemaString(sc);
			assertEquals("test1", "{\"a\"n}", result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test2() 
	{
		try {
			JsonNode n = JSON2RawSchema.fromJSON("{\"a\": [1,2,3]}");
			
			SchemaComponent sc = RawSchemaGen.deSchema(null, n);
			String result = SchemaPrinter.schemaString(sc);
			assertEquals("test2", "{\"a\"[nnn]}", result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test3() 
	{
		try {
			JsonNode n = JSON2RawSchema.fromJSON("{\"c\": 1,\"b\": 1,\"a\": 1}");
			
			SchemaComponent sc = RawSchemaGen.deSchema(null, n);
			String result = SchemaPrinter.schemaString(sc);
			assertEquals("test3", "{\"a\"n\"b\"n\"c\"n}", result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
