/**
 *
 */
package es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.gson;

import com.google.gson.JsonElement;

import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJBoolean;

/**
 * @author dsevilla
 *
 */
public class GsonBoolean extends GsonElement implements IAJBoolean
{
	public GsonBoolean(JsonElement val) {
		super(val);
	}
}
