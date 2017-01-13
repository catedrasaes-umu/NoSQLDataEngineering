package es.um.nosql.schemainference.dbgenerator.test.util.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbClient extends MongoClient
{
	public MongoDbClient(String ip, int port)
	{
		super(ip, port);
	}

	public void printDatabase(String dbName)
	{
		MongoDatabase database = getDatabase(dbName);
		for (String s : database.listCollectionNames())
			for (Document doc : database.getCollection(s).find())
				System.out.println(doc.keySet());
	}

	public void testInsert()
	{
		MongoDatabase db = getDatabase("library");
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

		printDatabase("library");
		cleanup();
	}

	public void cleanup()
	{
		MongoDatabase db = getDatabase("library");
		db.drop();
	}
}
