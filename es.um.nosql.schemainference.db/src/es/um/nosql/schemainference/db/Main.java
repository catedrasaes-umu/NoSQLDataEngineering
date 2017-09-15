package es.um.nosql.schemainference.db;

import java.io.File;

import es.um.nosql.schemainference.db.interfaces.EPol2Db;
import es.um.nosql.schemainference.db.interfaces.Model2Db;
import es.um.nosql.schemainference.db.interfaces.SOF2Db;
import es.um.nosql.schemainference.db.interfaces.Urban2Db;
import es.um.nosql.schemainference.db.utils.DbType;

public class Main
{
	private static final String COUCHDB_IP = "localhost";

	private static final String MONGODB_IP = "localhost";

	private static final String INPUT_FOLDER = "models/";

  private static String EPOL_BASE_DIR = "json/everyPolitician/countries/";

  private static String URBAN_BASE_FILE = "/media/alberto/braxis/datasets/urban/error_words.json";

  private static String SOF_BASE_DIR = "/media/alberto/braxis/datasets/stackoverflow/";

	public static void main(String[] args)
	{
//	  PREPARE_MODEL2DB(DbType.COUCHDB, COUCHDB_IP, INPUT_FOLDER);
//	  PREPARE_MODEL2DB(DbType.MONGODB, MONGODB_IP, INPUT_FOLDER);
//	  PREPARE_SOF2DB(DbType.COUCHDB, COUCHDB_IP, SOF_BASE_DIR);
//	  PREPARE_SOF2DB(DbType.MONGODB, MONGODB_IP, SOF_BASE_DIR);
	  PREPARE_EPOL2DB(DbType.MONGODB, MONGODB_IP, EPOL_BASE_DIR);
//	  PREPARE_EPOL2DB(DbType.COUCHDB, COUCHDB_IP, EPOL_BASE_DIR);
//	  PREPARE_URBAN2DB(DbType.MONGODB, MONGODB_IP, URBAN_BASE_FILE);
	}

	public static void PREPARE_MODEL2DB(DbType type, String ip, String source)
	{
		int minInstances = 20;
		int maxInstances = 50;

		Model2Db controller = new Model2Db(type, ip);

		File theFile = new File(source);
		if (theFile.isDirectory())
		{
		  for (String fileRoute : theFile.list())
	      controller.run(INPUT_FOLDER + fileRoute, minInstances, maxInstances);
		}
		else
		  controller.run(INPUT_FOLDER + source, minInstances, maxInstances);
		
		controller.shutdown();
	}

	public static void PREPARE_EPOL2DB(DbType type, String ip, String source)
	{
	  String DBNAME = "everypolitician";

	  EPol2Db controller = new EPol2Db(type, ip);

	  File theFile = new File(source);
	  if (theFile.isDirectory())
	  {
	    for (String countryRoute : theFile.list())
	      controller.run(source + countryRoute, DBNAME);
	  }
	  else
	    controller.run(source, DBNAME);

	  controller.shutdown();
	}

	public static void PREPARE_URBAN2DB(DbType type, String ip, String sourceFile)
	{
    String DBNAME = "urbanDictionary";

    Urban2Db controller = new Urban2Db(type, ip);
    controller.run(sourceFile, DBNAME);

    controller.shutdown();
	}

	public static void PREPARE_SOF2DB(DbType type, String ip, String sourceDir)
	{
    String USER_FILE = sourceDir + "Users.xml";
    String VOTES_FILE = sourceDir + "Votes.xml";
    String COMMENTS_FILE = sourceDir + "Comments.xml";
    String POSTS_FILE = sourceDir + "Posts.xml";
    String TAGS_FILE = sourceDir + "Tags.xml";
    String POSTLINKS_FILE = sourceDir + "PostLinks.xml";
    String BADGES_FILE = sourceDir + "Badges.xml";
    String DBNAME = "stackoverflow";

    SOF2Db controller = new SOF2Db(type, ip);
    controller.run(USER_FILE,  DBNAME);//6438660 filas => 38 minutos
    controller.run(VOTES_FILE,  DBNAME);//116720227 filas => 10 horas
    controller.run(COMMENTS_FILE,  DBNAME);//53566720 filas => 5 horas
    controller.run(POSTS_FILE,  DBNAME);
    controller.run(TAGS_FILE,  DBNAME);//48375 filas
    controller.run(POSTLINKS_FILE,  DBNAME);//3993518 filas
    controller.run(BADGES_FILE,  DBNAME);//21882069 filas

    controller.shutdown();
	}
}
