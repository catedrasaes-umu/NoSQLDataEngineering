package es.um.nosql.schemainference.dbgenerator;

import es.um.nosql.schemainference.dbgenerator.utils.DbType;

public class Main
{
	private static final String COUCHDB_IP = "155.54.190.237";

//	private static final String MONGODB_IP = "155.54.190.237";

	private static final String INPUT_FOLDER = "models/";

	private static final String JSON_FOLDER = "json/";

	public static void main(String[] args)
	{
		DbGenController controller = new DbGenController(DbType.COUCHDB, COUCHDB_IP);
		controller.startTest(INPUT_FOLDER, JSON_FOLDER);
	}
}
