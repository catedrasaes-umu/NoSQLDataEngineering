/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.jackson;

import org.codehaus.jackson.JsonNode;

import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJObject;

/**
 * @author dsevilla
 *
 */
public class JacksonObject extends JacksonElement implements IAJObject
{
	public JacksonObject(JsonNode val) {
		super(val);
	}
}
