package es.um.nosql.schemainference.dbgenerator.db.couchdb;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.nosql.schemainference.dbgenerator.db.DbClient;

public class CouchDbClient extends StdCouchDbInstance implements DbClient
{
	public CouchDbClient(HttpClient client)
	{
		super(client);
	}

	@Override
	public void insert(String path, String jsonContent)
	{
		try
		{
			CouchDbConnector connector = createConnector(path, true);
			ObjectMapper mapper = new ObjectMapper();

			JsonNode jsonItems = mapper.readTree(jsonContent);
			List<JsonNode> itemList = new ArrayList<JsonNode>();

			if (jsonItems.isArray())
				for (JsonNode item : jsonItems)
					itemList.add(item);

			connector.executeBulk(itemList);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void cleanDbs()
	{
		for (String dbName : getAllDatabases())
			deleteDatabase(dbName);
	}

	@Override
	public boolean shutdown()
	{
		return true;
	}
}
