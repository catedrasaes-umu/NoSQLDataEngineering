package es.um.nosql.schemainference.nosqlimport.couchdb;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.nosql.schemainference.nosqlimport.util.MapReduceSources;

public class CouchDBSchemaInference2
{
	public static void main(String[] args)
	{
		/**
		 * "art" database will not work
		 * "movies", "food" and "books" will
		 */
		String dbName = "art";
		String dirName = "mapreduce/couchdb/v1";

		try
		{
			StdCouchDbInstance dbClient = new StdCouchDbInstance(new StdHttpClient.Builder().host("localhost").port(5984).build());
			CouchDbConnector connector = dbClient.createConnector(dbName, false);

			MapReduceSources mrs = MapReduceSources.fromDir(dirName);

			//		List<JsonObject> allDocs = dbClient.view("_all_docs").query(JsonObject.class);
			//
			//		for (JsonObject o : allDocs)
			//			System.out.println(o.toString());
/*
			MapReduce mapRedObj = new MapReduce();
			mapRedObj.setMap(mrs.getMapJSCode());
			mapRedObj.setReduce(mrs.getReduceJSCode());

			List<JsonNode> list = dbClient.view("_temp_view")
					.tempView(mapRedObj)
					.group(true)
					.includeDocs(false)
					.reduce(true)
					.query(JsonObject.class);
*/
			ObjectMapper oMapper = new ObjectMapper();

/*			for (JsonNode o : list)
				System.out.println(oMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o));
*/
//			// Produce all the actual objects from the query. Couchdb won't allow include_docs to be specified
//			// for a reduce view, and if I include the document itself it causes a view overflow. So we have
//			// to take all the value objects and obtain them from the database directly
//			List<JsonObject> result = new ArrayList<JsonObject>(list.size());
//			for (JsonObject o : list)
//			{
//				String doc_id = o.get("value").getAsString();
//				JsonObject obj = dbClient.find(JsonObject.class, doc_id);
//				result.add(obj);
//			}
		} catch (MapReduceSources.MalformedDirectoryStructure e)
		{
			System.err.println("Cannot access map.js and/or reduce.js files.");
			e.printStackTrace();			
		} catch (Exception e) {
			System.err.println("Error in the process!.");
			e.printStackTrace();
		}
	}
}
