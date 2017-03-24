package es.um.nosql.schemainference.db.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import es.um.nosql.schemainference.db.adapters.DbClient;

public class XML2Db
{
	private int MAX_OBJECTS = 83333;
	private int MAX_LINES_BEFORE_STORE = 25000;

	private DbClient client;

	public XML2Db(DbClient client)
	{
		this.client = client;
	}

	public void storeXMLContent(String userXMLRoute, String dbName)
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(userXMLRoute))))
		{
			ArrayNode jsonArray = new ObjectMapper().createArrayNode();
			int numLines = 0;
			int totalLines = 0;
			reader.readLine();	// XML header line
			String collectionName = reader.readLine(); // <collectionName> line

			if (collectionName.length() < 2)
			{
				System.out.println("Error reading the collection name on line 2.");
				System.exit(-1);
			}
			else
			{
				collectionName = collectionName.substring(1, collectionName.length() - 1);
				//TODO: Clean the collection before starting to load the file.
				System.out.println("Inserting " + collectionName + " collection");
				System.out.println("Storing each " + MAX_LINES_BEFORE_STORE);
			}

			String previousLine = reader.readLine();

			for (String line; (line = reader.readLine()) != null;totalLines++)
			{
				jsonArray.add(adaptXMLLine(previousLine));

				previousLine = line;

				if (++numLines == MAX_LINES_BEFORE_STORE)
				{
					client.insert(dbName, collectionName, jsonArray.toString());
					jsonArray.removeAll();
					numLines = 0;
				}
				if (totalLines > MAX_OBJECTS)
					break;
			}

			if (jsonArray.size() > 0)
			{
				System.out.println("Storing remaining files...");
				client.insert(dbName, collectionName, jsonArray.toString());
			}

		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private ObjectNode adaptXMLLine(String line)
	{
		XmlMapper mapper = new XmlMapper();
		ObjectNode objNode = null;
		try
		{
			objNode = mapper.readValue(line, ObjectNode.class);
			objNode.put("_id", objNode.get("Id").asText());
			objNode.remove("Id");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return objNode;
	}
}
