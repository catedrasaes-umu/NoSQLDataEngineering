package es.um.nosql.schemainference.db;

import java.io.File;

import es.um.nosql.schemainference.db.interfaces.SOFLoader;
import es.um.nosql.schemainference.db.utils.DbType;

public class Main
{
	private static final String COUCHDB_IP = "localhost";

	private static final String MONGODB_IP = "localhost";

	private static final String INPUT_FOLDER = "models/";

	private static final String JSON_FOLDER = "json/";

	public static void prepareModel2CouchExample()
	{
		int minInstances = 2;
		int maxInstances = 5;

		DbController controller = new DbController(DbType.COUCHDB, COUCHDB_IP);

		for (String fileRoute : new File(INPUT_FOLDER).list())
			controller.model2Couch(INPUT_FOLDER + fileRoute, JSON_FOLDER, minInstances, maxInstances);

		controller.shutdown();
	}

	public static void prepareModel2MongoExample()
	{
		int minInstances = 2;
		int maxInstances = 5;

		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);

		for (String fileRoute : new File(INPUT_FOLDER).list())
			controller.model2Couch(INPUT_FOLDER + fileRoute, JSON_FOLDER, minInstances, maxInstances);

		controller.shutdown();
	}

	public static void prepareXML2Mongo()
	{
		String BASE_DIR = "/media/alberto/braxis/StackOverFlow/";
		String USER_FILE = BASE_DIR + "Users.xml";
		String VOTES_FILE = BASE_DIR + "Votes.xml";
		String COMMENTS_FILE = BASE_DIR + "Comments.xml";
		String POSTS_FILE = BASE_DIR + "Posts.xml";

		SOFLoader loader = new SOFLoader(MONGODB_IP);
		loader.readXMLFile(USER_FILE);//6438660
		loader.readXMLFile(VOTES_FILE);//116720227
		loader.readXMLFile(COMMENTS_FILE);//53566720
		loader.readXMLFile(POSTS_FILE);//
	}

	public static void main(String[] args)
	{
//		prepareModel2Couch();
//		prepareModel2Mongo();
		prepareXML2Mongo();
	}
}
