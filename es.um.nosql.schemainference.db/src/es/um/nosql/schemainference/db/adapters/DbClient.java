package es.um.nosql.schemainference.db.adapters;

public interface DbClient
{
	public void insert(String dbName, String jsonContent);

	public void insert(String dbName, String collectionName, String jsonContent);

	public boolean shutdown();

	public void cleanDb(String dbName);

	public void cleanAllDbs();
}
