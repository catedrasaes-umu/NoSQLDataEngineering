package es.um.nosql.schemainference.db.interfaces;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.nosql.schemainference.db.adapters.DbClient;

public class Json2Db
{
	private DbClient client;

	public Json2Db(DbClient client)
	{
		this.client = client;
	}

	public void storeJSONContent(String jsonRoute, String dbName)
	{
		try
		{
			JsonNode rootObj = new ObjectMapper().readTree(new File(jsonRoute));
			client.cleanDb(dbName);
			client.insert(dbName, rootObj.toString());
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
