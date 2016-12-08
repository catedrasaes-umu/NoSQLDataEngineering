/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.jackson;

import org.codehaus.jackson.JsonNode;

import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJNumber;

/**
 * @author dsevilla
 *
 */
public class JacksonNumber extends JacksonElement implements IAJNumber
{
	public JacksonNumber(JsonNode val) {
		super(val);
	}
}
