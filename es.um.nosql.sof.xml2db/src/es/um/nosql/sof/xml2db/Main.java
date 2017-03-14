package es.um.nosql.sof.xml2db;

import es.um.nosql.sof.xml2db.loaders.SOFLoader;

public class Main
{
	private static final String BASE_DIR = "/media/alberto/braxis/StackOverFlow/";

	private static final String USER_FILE = BASE_DIR + "Users.xml";

	private static final String VOTES_FILE = BASE_DIR + "Votes.xml";

	private static final String COMMENTS_FILE = BASE_DIR + "Comments.xml";

	private static final String POSTS_FILE = BASE_DIR + "Posts.xml";

	private static final String DBIP = "localhost";

	public static void main(String[] args)
	{
		SOFLoader loader = new SOFLoader(DBIP);
		loader.readXMLFile(USER_FILE, 1500000);//6438660
		loader.readXMLFile(VOTES_FILE, 1500000);//116720227
		loader.readXMLFile(COMMENTS_FILE, 1500000);//53566720
		loader.readXMLFile(POSTS_FILE, 1500000);//
	}
}
