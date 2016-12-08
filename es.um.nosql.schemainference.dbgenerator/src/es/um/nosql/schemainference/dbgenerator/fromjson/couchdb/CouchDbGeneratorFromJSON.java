package es.um.nosql.schemainference.dbgenerator.fromjson.couchdb;
/**
 *
 */


import java.io.File;
import java.nio.file.Files;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author dsevilla
 *
 */
public class CouchDbGeneratorFromJSON
{
	public static void main(String[] args)
	{
		(new CouchDbGeneratorFromJSON()).run(args);
	}

	private void run(String[] args)
	{
		String filename = args[0];
		File file = new File(filename);
		String dbname = file.getName().split("\\.")[0];

		try {
			String theJSON = new String(Files.readAllBytes(file.toPath()));

			JsonElement jelement = new JsonParser().parse(theJSON);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonArray rows = jobject.getAsJsonArray("rows");

			CouchDbProperties properties =
					new CouchDbProperties(dbname, true, "http", "127.0.0.1", 5984,null,null);
			CouchDbClient dbClient = new CouchDbClient(properties);

			// Insert all objects into the database
			rows.forEach(e ->
			{
				JsonObject obj = e.getAsJsonObject();
				dbClient.save(obj);
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
