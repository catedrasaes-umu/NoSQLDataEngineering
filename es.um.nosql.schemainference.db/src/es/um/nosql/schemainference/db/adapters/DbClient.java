package es.um.nosql.schemainference.db.adapters;

public interface DbClient
{
	public void insert(String name, String jsonContent);

	public boolean shutdown();

	public void cleanDb(String dbName);

	public void cleanAllDbs();
}
