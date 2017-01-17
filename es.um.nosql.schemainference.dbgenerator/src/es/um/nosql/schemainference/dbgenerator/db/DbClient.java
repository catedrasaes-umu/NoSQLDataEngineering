package es.um.nosql.schemainference.dbgenerator.db;

public interface DbClient
{
	public void insert(String name, String jsonContent);

	public boolean shutdown();

	public void cleanDbs();
}
