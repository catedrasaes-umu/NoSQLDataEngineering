package es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.jackson;

import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJArray;
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJBoolean;
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJElement;
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJNull;
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJNumber;
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJObject;
import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJTextual;

public class JacksonElement implements IAJElement
{
	private class It implements Iterator<IAJElement>
	{
		private Iterator<JsonNode> theIt;

		public It(Iterator<JsonNode> i)
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
			JsonNode next = theIt.next();
			return new JacksonElement(next);
		}
	}

	public JacksonElement(JsonNode val)
	{
		n = val;
	}
	
	
	public JacksonElement(Iterable<JacksonElement> sequence) 
	{
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode array = mapper.createArrayNode();
		sequence.forEach(element -> {
			array.add(element.n);
		});
		n = array;
	}

	@Override
	public Iterator<IAJElement> iterator()
	{
		return new It(n.iterator());
	}

	protected JsonNode n;

	@Override
	public IAJElement get(int index)
	{
		JsonNode val = n.get(index);
		if (val != null)
			return new JacksonElement(val);
		return null;
	}

	@Override
	public IAJElement get(String fieldName)
	{
		JsonNode val = n.get(fieldName);
		if (val != null)
			return new JacksonElement(val);
		return null;
	}

	@Override
	public boolean isArray() {
		return n.isArray();
	}

	@Override
	public boolean isObject() {
		return n.isObject();
	}

	@Override
	public boolean isNumber() {
		return n.isNumber();
	}

	@Override
	public boolean isTextual() {
		return n.isTextual();
	}

	@Override
	public boolean isBoolean() {
		return n.isBoolean();
	}

	@Override
	public boolean isNull() {
		return n.isNull();
	}

	@Override
	public IAJArray asArray() {
		return new JacksonArray(n);
	}

	@Override
	public IAJObject asObject() {
		return new JacksonObject(n);
	}

	@Override
	public Iterator<String> getFieldNames() {
		return n.getFieldNames();
	}

	@Override
	public String asString() {
		return n.asText();
	}

	@Override
	public IAJBoolean asBoolean() {
		return new JacksonBoolean(n);
	}

	@Override
	public IAJNumber asNumber() {
		return new JacksonNumber(n);
	}

	@Override
	public IAJNull asNull() {
		return new JacksonNull(n);
	}

	@Override
	public IAJTextual asTextual() {
		return new JacksonTextual(n);
	}
}
