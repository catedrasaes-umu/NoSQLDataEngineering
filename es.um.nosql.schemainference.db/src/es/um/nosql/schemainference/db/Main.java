package es.um.nosql.schemainference.db;

import java.io.File;

import es.um.nosql.schemainference.db.utils.DbType;

public class Main
{
	private static final String COUCHDB_IP = "localhost";

	private static final String MONGODB_IP = "localhost";

	private static final String INPUT_FOLDER = "models/";

	private static final String JSON_FOLDER = "json/";

	public static void prepareModel2Couch()
	{
		int minInstances = 2;
		int maxInstances = 5;

		DbController controller = new DbController(DbType.COUCHDB, COUCHDB_IP);

		for (String fileRoute : new File(INPUT_FOLDER).list())
			controller.model2Db(INPUT_FOLDER + fileRoute, JSON_FOLDER, minInstances, maxInstances);

		controller.shutdown();
	}

	public static void prepareModel2Mongo()
	{
		int minInstances = 2;
		int maxInstances = 5;

		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);

		for (String fileRoute : new File(INPUT_FOLDER).list())
			controller.model2Db(INPUT_FOLDER + fileRoute, JSON_FOLDER, minInstances, maxInstances);

		controller.shutdown();
	}

	public static void prepareXML2Mongo()
	{
		String BASE_DIR = "/media/alberto/braxis/StackOverFlow/";
		String USER_FILE = BASE_DIR + "Users.xml";
		String VOTES_FILE = BASE_DIR + "Votes.xml";
		String COMMENTS_FILE = BASE_DIR + "Comments.xml";
		String POSTS_FILE = BASE_DIR + "Posts.xml";
		String DBNAME = "stackoverflow";

		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);
//		controller.xml2Db(USER_FILE, DBNAME);//6438660 filas => 38 minutos
//		controller.xml2Db(VOTES_FILE, DBNAME);//116720227 filas => 10 horas
//		controller.xml2Db(COMMENTS_FILE, DBNAME);//53566720 filas => 5 horas
		controller.xml2Db(POSTS_FILE, DBNAME);
	}

	public static void prepareXML2Couch()
	{
		String BASE_DIR = "/media/alberto/braxis/StackOverFlow/";
		String USER_FILE = BASE_DIR + "Users.xml";
		String VOTES_FILE = BASE_DIR + "Votes.xml";
		String COMMENTS_FILE = BASE_DIR + "Comments.xml";
		String POSTS_FILE = BASE_DIR + "Posts.xml";
		String DBNAME = "stackoverflow";

		DbController controller = new DbController(DbType.COUCHDB, COUCHDB_IP);
		controller.xml2Db(USER_FILE, DBNAME);
		controller.xml2Db(VOTES_FILE, DBNAME);
		controller.xml2Db(COMMENTS_FILE, DBNAME);
		controller.xml2Db(POSTS_FILE, DBNAME);
	}

	public static void main(String[] args)
	{
//		prepareModel2Couch();
//		prepareModel2Mongo();
		prepareXML2Mongo();
//		prepareXML2Couch();
	}
}
