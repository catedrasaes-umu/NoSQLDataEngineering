/**
 *
 */
package es.um.nosql.schemainference.nosqlimport.mongodb;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author dsevilla
 *
 */
public class MongoSchemaInference
{
	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			System.err.println("I need as a parameter a directory where the map.js and reduce.js files live.");
			return;
		}

		String dirName = args[0];
		Path dir = new File(dirName).toPath();

		MongoClient mongo;

		try {
			mongo = new MongoClient("localhost", 27017);
			MongoDatabase db = mongo.getDatabase("library");

			MongoCollection<Document> books = db.getCollection("books");

			Document book = new Document();
			book.put("name", "Understanding JAVA");
			book.put("pages", 100);
			books.insertOne(book);

			book = new Document();
			book.put("name", "Understanding JSON");
			book.put("pages", 200);
			books.insertOne(book);

			book = new Document();
			book.put("name", "Understanding XML");
			book.put("pages", 300);
			books.insertOne(book);

			book = new Document();
			book.put("name", "Understanding Web Services");
			book.put("pages", 400);
			books.insertOne(book);

			book = new Document();
			book.put("name", "Understanding Axis2");
			book.put("pages", 150);
			books.insertOne(book);

			// Read the map file
			Path mapFile = dir.resolve("map.js");
			String mapFunction = new String(Files.readAllBytes(mapFile));

			// Read the reduce file
			Path reduceFile = dir.resolve("reduce.js");
			String reduceFunction = new String(Files.readAllBytes(reduceFile));

			MapReduceIterable<Document> cmd =
					books.mapReduce(mapFunction, reduceFunction);

			for (Document o : cmd) {
				System.out.println(o.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
