/**
 *
 */
package es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.jackson;

import org.codehaus.jackson.JsonNode;

import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJNull;

/**
 * @author dsevilla
 *
 */
public class JacksonNull extends JacksonElement implements IAJNull
{
	public JacksonNull(JsonNode val) {
		super(val);
	}
}
