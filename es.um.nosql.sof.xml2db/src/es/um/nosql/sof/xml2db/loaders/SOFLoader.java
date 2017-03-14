package es.um.nosql.sof.xml2db.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SOFLoader
{
	private int MAX_LINES_BEFORE_STORE = 50000;

	private MongoClient mClient;

	private String DBNAME = "stackoverflow";

	private ArrayNode jsonArray;

	public SOFLoader(String databaseIP)
	{
		mClient = new MongoClient(databaseIP);
	}

	public void readXMLFile(String userXMLRoute, int limit)
	{
		long startTime = System.currentTimeMillis();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(userXMLRoute))))
		{
			jsonArray = new ObjectMapper().createArrayNode();
			int numLines = 0;
			int lineCounter = 0;
			reader.readLine();	// XML line
			String collectionName = reader.readLine(); // <collectionName> line

			if (collectionName.length() < 2)
			{
				System.out.println("Error reading the collection name on line 2.");
				System.exit(-1);
			}
			else
			{
				collectionName = collectionName.substring(1, collectionName.length() - 1);
				System.out.println("Inserting " + collectionName + " collection");
				System.out.println("Storing each " + MAX_LINES_BEFORE_STORE);
			}

			String previousLine = reader.readLine();

			for (String line; (line = reader.readLine()) != null; lineCounter++)
			{
				addJsonLine(previousLine);
				previousLine = line;

				if (++numLines == MAX_LINES_BEFORE_STORE)
				{
					System.out.println("Storing content in " + collectionName + "@line: " + lineCounter);
					storeJsonContent(collectionName);
					numLines = 0;
				}

				if (lineCounter > limit)
				{
					System.out.println("Limit reached: " + limit + ". Exiting...");
					break;
				}
			}

			System.out.println("File ending reached. Storing remaining files...");
			storeJsonContent(collectionName);
			System.out.println("XML file succesfully read in " + (System.currentTimeMillis() - startTime) + " ms");
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void addJsonLine(String line)
	{
		XmlMapper mapper = new XmlMapper();
		try
		{
			ObjectNode obj = mapper.readValue(line, ObjectNode.class);
			obj.put("_id", obj.get("Id").asText());
			obj.remove("Id");

			jsonArray.add(obj);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void storeJsonContent(String collectionName)
	{
		MongoDatabase db = mClient.getDatabase(DBNAME);
		List<Document> docCollection = new ArrayList<Document>();

		jsonArray.forEach(jsonElement -> 
		{
			docCollection.add(Document.parse(jsonElement.toString()));
		});

		MongoCollection<Document> collection = db.getCollection(collectionName);
		collection.insertMany(docCollection);

		jsonArray.removeAll();
	}
}
