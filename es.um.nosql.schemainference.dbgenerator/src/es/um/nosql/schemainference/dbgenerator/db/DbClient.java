package es.um.nosql.schemainference.dbgenerator.db;

public interface DbClient
{
	public boolean shutdown();

	public void insert(String name, String jsonContent);
}
