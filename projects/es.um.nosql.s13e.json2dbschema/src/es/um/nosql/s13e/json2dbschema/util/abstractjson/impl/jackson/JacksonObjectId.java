/**
 *
 */
package es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.jackson;

import org.codehaus.jackson.JsonNode;

import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJObjectId;

public class JacksonObjectId extends JacksonElement implements IAJObjectId
{
	public JacksonObjectId(JsonNode val) {
		super(val);
	}
}
