package es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson;

import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJArray;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJBoolean;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJElement;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJNumber;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJNull;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJObject;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJTextual;

public class GsonElement implements IAJElement
{
	private class It implements Iterator<IAJElement>
	{
		private Iterator<JsonElement> theIt;

		public It(Iterator<JsonElement> i)
		{
			theIt = i;
		}

		@Override
		public boolean hasNext() {
			return theIt.hasNext();
		}

		@Override
		public IAJElement next()
		{
			JsonElement next = theIt.next();
			return new GsonElement(next);
		}
	}

	public GsonElement(JsonElement e)
	{
		this.e = e;
	}

	@Override
	public Iterator<IAJElement> iterator()
	{
		if (e.isJsonArray())
		{
			JsonArray a = e.getAsJsonArray();
			return new It(a.iterator());
		}

		return null;
	}

	private JsonElement e;

	@Override
	public IAJElement get(int index)
	{
		if (e.isJsonArray())
		{
			JsonArray a = e.getAsJsonArray();
			return new GsonArray(a.get(index));
		}
		return null;
	}

	@Override
	public IAJElement get(String fieldName)
	{
		if (e.isJsonObject())
		{
			JsonObject o = e.getAsJsonObject();
			// Maybe return a GsonElement?
			return new GsonArray(o.get(fieldName));
		}
		return null;
	}

	@Override
	public boolean isArray() {
		return e.isJsonArray();
	}

	@Override
	public boolean isObject() {
		return e.isJsonObject();
	}

	@Override
	public boolean isNumber() {
		if (e != null && e.isJsonPrimitive())
		{
			JsonPrimitive p = e.getAsJsonPrimitive();
			return p.isNumber();
		}
		return false;
	}

	@Override
	public boolean isTextual() {
		if (e != null && e.isJsonPrimitive())
		{
			JsonPrimitive p = e.getAsJsonPrimitive();
			return p.isString();
		}
		return false;
	}

	@Override
	public boolean isBoolean() {
		if (e != null && e.isJsonPrimitive())
		{
			JsonPrimitive p = e.getAsJsonPrimitive();
			return p.isBoolean();
		}
		return false;
	}

	@Override
	public boolean isNull() {
		return e.isJsonNull();
	}

	@Override
	public IAJArray asArray() {
		if (e.isJsonArray())
			return new GsonArray(e.getAsJsonArray());
		return null;
	}

	@Override
	public IAJObject asObject() {
		if (e.isJsonObject())
			return new GsonObject(e.getAsJsonObject());
		return null;
	}

	@Override
	public String asString() {
		return isTextual() ? e.getAsString() : null;
	}

	@Override
	public IAJTextual asTextual() {
		return isTextual() ? new GsonTextual(e) : null;
	}

	@Override
	public IAJBoolean asBoolean() {
		return isBoolean() ? new GsonBoolean(e) : null;
	}

	@Override
	public IAJNull asNull() {
		return isNull() ? new GsonNull(e) : null;
	}

	@Override
	public IAJNumber asNumber() {
		return isNumber() ? new GsonNumber(e) : null;
	}

	@Override
	public Iterator<String> getFieldNames() {
		if (e.isJsonObject())
		{
			JsonObject o = e.getAsJsonObject();
			return o.entrySet().stream().map(e -> e.getKey()).iterator();
		}
		return null;
	}

}
