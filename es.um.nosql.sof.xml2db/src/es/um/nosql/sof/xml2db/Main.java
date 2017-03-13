package es.um.nosql.sof.xml2db;

import es.um.nosql.sof.xml2db.loaders.SOFLoader;

public class Main
{
	private static final String BASE_DIR = "/home/alberto/StackOverFlow/";

	private static final String USER_FILE = BASE_DIR + "Users.xml";

	private static final String VOTES_FILE = BASE_DIR + "Votes.xml";

	private static final String COMMENTS_FILE = BASE_DIR + "Comments.xml";

	private static final String DBIP = "localhost";

	public static void main(String[] args)
	{
		SOFLoader loader = new SOFLoader(DBIP);
		loader.readXMLFile(USER_FILE);
		loader.readXMLFile(VOTES_FILE);
		loader.readXMLFile(COMMENTS_FILE);
	}
}
