package es.um.nosql.schemainference.nosqlimport.util;

import java.io.IOException;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;

public class CouchDBStreamAdapter
{
	private ObjectMapper mapper = new ObjectMapper();

	public Stream<ObjectNode> adaptStream(Stream<JsonObject> stream)
	{
		return stream.map(jsonObject ->
		{
			ObjectNode jObj = null;
			try
			{
				jObj = (ObjectNode) mapper.readTree(jsonObject.get("key").getAsString());
				jObj.remove("_rev");
			} catch (JsonProcessingException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return jObj;
		});
	}

	public void printStream(Stream<ObjectNode> stream)
	{
		stream.forEach(object->
		{
			try
			{
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
			} catch (JsonProcessingException e)
			{
				e.printStackTrace();
			}
		});
	}

	public ObjectNode stream2Json(Stream<ObjectNode> stream)
	{
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode result = factory.objectNode();
		ArrayNode array = result.putArray("rows");

		stream.forEach(elem -> array.add(elem));

		return result;
	}
}
