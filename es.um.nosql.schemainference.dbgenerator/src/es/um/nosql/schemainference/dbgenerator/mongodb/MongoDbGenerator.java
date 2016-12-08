/**
 *
 */
package es.um.nosql.schemainference.dbgenerator.mongodb;

import java.io.File;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.dbgenerator.util.ModelLoader;

/**
 * @author dsevilla
 *
 */
public class MongoDbGenerator
{
	public static void main(String[] args)
	{
		(new MongoDbGenerator()).run(args);
	}

	private Map<Entity, MongoCollection<Document>> collections;

	private void run(String[] args)
	{
		MongoClient mongo;
		String filename = args.length == 0 ? "model/test.xmi" : args[0];
		File file = new File(filename);

		ModelLoader<NoSQLSchema> loader = new ModelLoader<>(NoSQLSchemaPackage.eINSTANCE);
		NoSQLSchema schema = loader.load(file);

		try {
			mongo = new MongoClient("localhost", 27017);
			MongoDatabase db = mongo.getDatabase("library");

			collections = schema.getEntities().stream()
					.collect(Collectors.toMap(Function.identity(),
							e -> db.getCollection(e.getName())));



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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
