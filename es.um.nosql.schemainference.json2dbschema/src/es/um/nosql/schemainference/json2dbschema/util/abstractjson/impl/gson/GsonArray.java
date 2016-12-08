/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson;

import com.google.gson.JsonElement;

import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJArray;

/**
 * @author dsevilla
 *
 */
public class GsonArray extends GsonElement implements IAJArray
{
	public GsonArray(JsonElement val) {
		super(val);
	}
}
