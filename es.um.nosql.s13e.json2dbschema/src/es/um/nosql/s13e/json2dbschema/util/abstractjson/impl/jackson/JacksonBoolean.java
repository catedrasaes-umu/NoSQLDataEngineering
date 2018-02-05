/**
 *
 */
package es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.jackson;

import org.codehaus.jackson.JsonNode;

import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJBoolean;

/**
 * @author dsevilla
 *
 */
public class JacksonBoolean extends JacksonElement implements IAJBoolean
{
	public JacksonBoolean(JsonNode val) {
		super(val);
	}
}
