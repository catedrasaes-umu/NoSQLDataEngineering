/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson;

import java.io.File;
import com.google.gson.JsonElement;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJAdapter;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJElement;

/**
 * @author dsevilla
 *
 */
public class GsonAdapter implements IAJAdapter<JsonElement>
{
	public static IAJElement _wrap(JsonElement e)
	{
		if (e == null)
			return null;
		return new GsonElement(e);
	}

	@Override
	public IAJElement wrap(JsonElement e)
	{
		return _wrap(e);
	}

	@Override
	public IAJElement readFromFile(File jsonFile)
	{
		return null;
	}
}
