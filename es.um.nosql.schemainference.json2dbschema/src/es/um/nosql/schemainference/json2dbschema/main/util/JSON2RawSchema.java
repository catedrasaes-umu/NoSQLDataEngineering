/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.main.util;


import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author dsevilla
 *
 */
public class JSON2RawSchema
{
	private static ObjectMapper m = new ObjectMapper();
	
	public static JsonNode fromJSON(String json) throws JsonProcessingException, IOException
	{
		return m.readTree(json);
	}
}
