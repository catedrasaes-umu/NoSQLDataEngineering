package es.um.nosql.schemainference.dbgenerator.db.mongodb;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.um.nosql.schemainference.dbgenerator.db.DbClient;

/**
 * Not implemented yet. Do not even try.
 * @author Alberto
 */
public class MongoDbClient extends MongoClient implements DbClient
{
	public MongoDbClient(String ip, int port)
	{
		super(ip, port);
	}

	public void cleanup()
	{
		MongoDatabase db = getDatabase("library");
		db.drop();
	}

	@Override
	public void insert(String name, String jsonContent)
	{
	}

	@Override
	public void cleanDbs()
	{
	}

	@Override
	public boolean shutdown()
	{
		return true;
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
/*
 * MongoClient mongo;
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
 */
	}
}
