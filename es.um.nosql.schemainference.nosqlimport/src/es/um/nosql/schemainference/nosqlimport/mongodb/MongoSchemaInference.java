/**
 *
 */
package es.um.nosql.schemainference.nosqlimport.mongodb;

import java.util.Optional;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.um.nosql.schemainference.nosqlimport.util.MapReduceSources;

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

		Optional<MongoClient> mongoRef = Optional.empty();
		
		try {
			String dirName = args[0];
			MapReduceSources mrs = MapReduceSources.fromDir(dirName);

			mongoRef = Optional.of(new MongoClient("localhost", 27017));
			MongoClient mongo = mongoRef.get();
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

			MapReduceIterable<Document> cmd =
					books.mapReduce(mrs.getMapJSCode(), mrs.getReduceJSCode());

			for (Document o : cmd) {
				System.out.println(o.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mongoRef.ifPresent(mongo -> mongo.close());
		}
	}
}
