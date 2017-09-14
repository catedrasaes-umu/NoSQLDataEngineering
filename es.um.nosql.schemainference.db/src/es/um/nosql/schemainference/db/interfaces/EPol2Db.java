package es.um.nosql.schemainference.db.interfaces;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.nosql.schemainference.db.utils.DbType;

public class EPol2Db extends Source2Db
{
  public EPol2Db(DbType db, String ip)
  {
    super(db, ip);
  }

  public void run(String jsonRoute, String dbName)
  {
    long startTime = System.currentTimeMillis();

    System.out.println("Reading json file " + jsonRoute + "...");
    storeJSONContent(jsonRoute, dbName);
    System.out.println(dbName + ":" + Paths.get(jsonRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
  }

	private void storeJSONContent(String jsonRoute, String dbName)
	{
		try
		{
			JsonNode rootObj = new ObjectMapper().readTree(new File(jsonRoute));
			rootObj.fieldNames().forEachRemaining( fieldName ->
			{
				if (!fieldName.equals("meta"))
				{
					JsonNode collection = rootObj.get(fieldName);
					if (collection.size() > 0)
						getClient().insert(dbName, fieldName, collection.toString());
				}
			});
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
