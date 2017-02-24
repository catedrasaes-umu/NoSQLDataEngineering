package es.um.nosql.schemainference.nosqlimport.couchdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.DesignDocument.MapReduce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import es.um.nosql.schemainference.nosqlimport.util.CouchDBStreamAdapter;
import es.um.nosql.schemainference.nosqlimport.util.MapReduceSources;

/**
 * @author dsevilla
 */
public class CouchDBSchemaInference
{
	private CouchDBStreamAdapter adapter;

	public CouchDBSchemaInference()
	{
		this.adapter = new CouchDBStreamAdapter();
	}

	public Stream<JsonObject> mapRed2Stream(String dbIP, String tableName, String mapRedDir)
	{
		return performMapReduce(dbIP, tableName, mapRedDir);
	}

	public JsonArray mapRed2Array(String dbIP, String tableName, String mapRedDir)
	{
		return adapter.stream2JsonArray(performMapReduce(dbIP, tableName, mapRedDir));
	}

	public void mapRed2File(String dbIP, String tableName, String mapRedDir, String outputFile)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try
		{
			PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
			writer.print(gson.toJson(adapter.stream2JsonObject(performMapReduce(dbIP, tableName, mapRedDir))));
			writer.close();
		} catch (IOException e)
		{
			System.err.println("Error while writing JSON inference to file!");
		}
	}

	private Stream<JsonObject> performMapReduce(String dbIP, String tableName, String mapRedDir)
	{
		MapReduceSources mrs = MapReduceSources.fromDir(mapRedDir);
		CouchDbProperties properties = new CouchDbProperties(tableName.toLowerCase(), true, "http", dbIP, 5984, null, null);
		CouchDbClient dbClient = new CouchDbClient(properties);
		MapReduce mapRedObj = new MapReduce();
		mapRedObj.setMap(mrs.getMapJSCode());
		mapRedObj.setReduce(mrs.getReduceJSCode());
		List<JsonObject> list = dbClient.view("_temp_view").tempView(mapRedObj).group(true)
			.includeDocs(false).reduce(true).query(JsonObject.class);

		// This step will remove the pesky _rev attribute
		return adapter.adaptStream(list.stream());
	}
}
