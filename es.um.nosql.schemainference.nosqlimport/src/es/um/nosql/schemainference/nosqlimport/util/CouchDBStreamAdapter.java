package es.um.nosql.schemainference.nosqlimport.util;

import java.util.stream.Stream;

import com.google.gson.JsonObject;

public class CouchDBStreamAdapter
{
	public Stream<JsonObject> adaptStream(Stream<JsonObject> stream)
	{
		return stream.map(jsonObject -> {
//			System.out.println(jsonObject);
			return jsonObject;
		});
	}
}
