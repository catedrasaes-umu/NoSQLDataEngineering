package es.um.nosql.s13e.db.interfaces;

import java.io.File;
import java.nio.file.Paths;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.utils.DbType;

public class Json2Db extends Source2Db
{
	public Json2Db(DbType db, String ip)
	{
	  super(db, ip);
  }

	public void run(String jsonRoute, String dbName)
	{
	  long startTime = System.currentTimeMillis();

	  System.out.println("Reading input model " + jsonRoute + "...");
	  storeJSONContent(jsonRoute, dbName);
	  System.out.println(Paths.get(jsonRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
	}

  private void storeJSONContent(String jsonRoute, String dbName)
	{
    try
    {
      ObjectMapper mapper = new ObjectMapper();
      ArrayNode arrayToInsert = mapper.createArrayNode();
      JsonNode objArray = mapper.readTree(new File(jsonRoute));
      String collName = "";

      for (JsonNode node : objArray)
      {
        ObjectNode obj = (ObjectNode)node;
        obj.put("_id", new ObjectId().toString());
        String newCollName = obj.get("type").asText();
        obj.remove("type");

        if (!collName.equals(newCollName) && arrayToInsert.size() != 0)
        {
          getClient().insert(dbName, collName, arrayToInsert.toString());
          arrayToInsert.removeAll();
        }
        arrayToInsert.add(obj);
        collName = newCollName;
      }

      getClient().insert(dbName, collName, arrayToInsert.toString());

    } catch (Exception e)
    {
      e.printStackTrace();
    }
	}
}