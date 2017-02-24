/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		JsonParser parser = new JsonParser();
		try
		{
			JsonObject root = parser.parse(new BufferedReader(new FileReader(jsonFile))).getAsJsonObject();
			return wrap(root);
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
