/**
 *
 */
package es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.gson;

import com.google.gson.JsonElement;

import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJTextual;

/**
 * @author dsevilla
 *
 */
public class GsonTextual extends GsonElement implements IAJTextual
{
	public GsonTextual(JsonElement val) {
		super(val);
	}
}
