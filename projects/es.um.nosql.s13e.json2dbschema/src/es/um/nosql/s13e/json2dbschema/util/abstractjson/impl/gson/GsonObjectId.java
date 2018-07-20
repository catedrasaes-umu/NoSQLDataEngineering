/**
 *
 */
package es.um.nosql.s13e.json2dbschema.util.abstractjson.impl.gson;

import com.google.gson.JsonElement;

import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJObjectId;

public class GsonObjectId extends GsonElement implements IAJObjectId
{
	public GsonObjectId(JsonElement val) {
		super(val);
	}
}
