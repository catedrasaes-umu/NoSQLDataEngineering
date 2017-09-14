package es.um.nosql.schemainference.db;

import java.io.File;

import es.um.nosql.schemainference.db.interfaces.EPol2Db;
import es.um.nosql.schemainference.db.interfaces.Model2Db;
import es.um.nosql.schemainference.db.utils.DbType;

public class Main
{
	private static final String COUCHDB_IP = "localhost";

	private static final String MONGODB_IP = "localhost";

	private static final String INPUT_FOLDER = "models/";

	 public static void main(String[] args)
	 {
//	   prepareModel2Db(DbType.COUCHDB, COUCHDB_IP);
//     prepareModel2Db(DbType.MONGODB, MONGODB_IP);
//	    prepareXML2Mongo();
//	    prepareXML2Couch();
//	    prepareEPol2Db(DbType.MONGODB, MONGODB_IP);
//      prepareEPol2Db(DbType.COUCHDB, COUCHDB_IP);
	 }

	public static void prepareModel2Db(DbType type, String ip)
	{
		int minInstances = 20;
		int maxInstances = 50;

		Model2Db controller = new Model2Db(type, ip);

		for (String fileRoute : new File(INPUT_FOLDER).list())
			controller.run(INPUT_FOLDER + fileRoute, minInstances, maxInstances);

		controller.shutdown();
	}

	public static void prepareEPol2Db(DbType type, String ip)
	{
	  String BASE_DIR = "json/everyPolitician/countries/";
	  String DBNAME = "everypolitician";

	  EPol2Db controller = new EPol2Db(type, ip);

	  for (File countryFile : new File(BASE_DIR).listFiles())
	    controller.run(countryFile.toString(), DBNAME);
	}
/*
	public static void prepareXML2Mongo()
	{
		String BASE_DIR = "/media/alberto/braxis/StackOverFlow/";
		String USER_FILE = BASE_DIR + "Users.xml";
		String VOTES_FILE = BASE_DIR + "Votes.xml";
		String COMMENTS_FILE = BASE_DIR + "Comments.xml";
		String POSTS_FILE = BASE_DIR + "Posts.xml";
		String TAGS_FILE = BASE_DIR + "Tags.xml";
		String POSTLINKS_FILE = BASE_DIR + "PostLinks.xml";
		String BADGES_FILE = BASE_DIR + "Badges.xml";
		String DBNAME = "stackoverflow";

		DbController controller = new DbController(DbType.MONGODB, MONGODB_IP);
		controller.xml2Db(USER_FILE, DBNAME);//6438660 filas => 38 minutos
		controller.xml2Db(VOTES_FILE, DBNAME);//116720227 filas => 10 horas
		controller.xml2Db(COMMENTS_FILE, DBNAME);//53566720 filas => 5 horas
		controller.xml2Db(POSTS_FILE, DBNAME);
		controller.xml2Db(TAGS_FILE, DBNAME);//48375 filas
		controller.xml2Db(POSTLINKS_FILE, DBNAME);//3993518 filas
		controller.xml2Db(BADGES_FILE, DBNAME);//21882069 filas
	}

	public static void prepareXML2Couch()
	{
		String BASE_DIR = "/media/alberto/braxis/StackOverFlow/";
		String USER_FILE = BASE_DIR + "Users.xml";
		String VOTES_FILE = BASE_DIR + "Votes.xml";
		String COMMENTS_FILE = BASE_DIR + "Comments.xml";
		String POSTS_FILE = BASE_DIR + "Posts.xml";
		String TAGS_FILE = BASE_DIR + "Tags.xml";
		String POSTLINKS_FILE = BASE_DIR + "PostLinks.xml";
		String BADGES_FILE = BASE_DIR + "Badges.xml";
		String DBNAME = "stackoverflow";

		DbController controller = new DbController(DbType.COUCHDB, COUCHDB_IP);
		controller.xml2Db(USER_FILE, DBNAME);
		controller.xml2Db(VOTES_FILE, DBNAME);
		controller.xml2Db(COMMENTS_FILE, DBNAME);
		controller.xml2Db(POSTS_FILE, DBNAME);
		controller.xml2Db(TAGS_FILE, DBNAME);
		controller.xml2Db(POSTLINKS_FILE, DBNAME);
		controller.xml2Db(BADGES_FILE, DBNAME);
	}
*/
}
