/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.jackson;

import org.codehaus.jackson.JsonNode;

import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJTextual;

/**
 * @author dsevilla
 *
 */
public class JacksonTextual extends JacksonElement implements IAJTextual
{
	public JacksonTextual(JsonNode val) {
		super(val);
	}
}
