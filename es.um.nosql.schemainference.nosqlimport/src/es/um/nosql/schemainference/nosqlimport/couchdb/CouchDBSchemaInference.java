package es.um.nosql.schemainference.nosqlimport.couchdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.DesignDocument.MapReduce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;

import es.um.nosql.schemainference.nosqlimport.util.CouchDBStreamAdapter;
import es.um.nosql.schemainference.nosqlimport.util.MapReduceSources;

/**
 * @author dsevilla
 */
public class CouchDBSchemaInference
{
	private String dbIP;
	private String tableName;
	private String mapRedDir;

	public CouchDBSchemaInference(String ip, String tableName, String mapRedDir)
	{
		this.dbIP = ip;
		this.tableName = tableName;
		this.mapRedDir = mapRedDir;
	}

	public void inferAndWrite(String outputJson)
	{
		json2File(outputJson, performMapReduce());
	}

	public ObjectNode performMapReduce()
	{
		ObjectNode result = null;
		try
		{
			MapReduceSources mrs = MapReduceSources.fromDir(mapRedDir);
			CouchDbProperties properties = new CouchDbProperties(tableName, true, "http", dbIP, 5984, null, null);
			CouchDbClient dbClient = new CouchDbClient(properties);
			MapReduce mapRedObj = new MapReduce();
			mapRedObj.setMap(mrs.getMapJSCode());
			mapRedObj.setReduce(mrs.getReduceJSCode());
			List<JsonObject> list = dbClient.view("_temp_view").tempView(mapRedObj).group(true)
					.includeDocs(false).reduce(true).query(JsonObject.class);

			CouchDBStreamAdapter adapter = new CouchDBStreamAdapter();

			// This step will cast Gson objects to Jackson objects and remove the pesky _rev attribute
			// It will also concatenate each object in a rows[] array and create a global json object.
			result = adapter.stream2Json(adapter.adaptStream(list.stream()));

		} catch (MapReduceSources.MalformedDirectoryStructure e)
		{
			System.err.println("Cannot access map.js and/or reduce.js files.");
			e.printStackTrace();			
		} catch (Exception e)
		{
			System.err.println("Error in the process!.");
			e.printStackTrace();
		}

		return result;
	}

	private void json2File(String jsonPath, ObjectNode json)
	{
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			PrintWriter writer = new PrintWriter(jsonPath, "UTF-8");
			writer.print(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
			writer.close();
		} catch (IOException e)
		{
			System.err.println("Error while writing JSON inference to file!");
		}
	}
}
