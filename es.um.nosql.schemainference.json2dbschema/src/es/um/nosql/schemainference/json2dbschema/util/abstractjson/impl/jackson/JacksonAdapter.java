/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.jackson;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJAdapter;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJElement;

/**
 * @author dsevilla
 *
 */
public class JacksonAdapter implements IAJAdapter<JsonNode>
{
	public static IAJElement _wrap(JsonNode e)
	{
		if (e == null)
			return null;
		return new JacksonElement(e);
	}

	@Override
	public IAJElement wrap(JsonNode e)
	{
		return _wrap(e);
	}

	@Override
	public IAJElement readFromFile(File jsonFile)
	{
		ObjectMapper m = new ObjectMapper();
		try {
			JsonNode root = m.readTree(jsonFile);
			return wrap(root);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
