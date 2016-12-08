/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson;

import com.google.gson.JsonElement;

import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJNumber;

/**
 * @author dsevilla
 *
 */
public class GsonNumber extends GsonElement implements IAJNumber
{
	public GsonNumber(JsonElement val) {
		super(val);
	}
}
