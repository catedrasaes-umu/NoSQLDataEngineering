package es.um.nosql.schemainference.db.interfaces;

import java.io.File;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.db.adapters.DbClient;
import es.um.nosql.schemainference.db.generator.JsonGenerator;
import es.um.nosql.schemainference.util.emf.ModelLoader;

public class Model2Db
{
	private DbClient client;

	public Model2Db(DbClient client)
	{
		this.client = client;
	}

	public void storeJSONContent(String modelRoute, int minInstances, int maxInstances)
	{
		ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
		JsonGenerator generator = new JsonGenerator();

		NoSQLSchema schema = loader.load(new File(modelRoute), NoSQLSchema.class);
		String jsonContent = "";

		try
		{
			jsonContent = generator.generate(schema, minInstances, maxInstances);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		client.cleanDb(schema.getName());
		client.insert(schema.getName(), jsonContent);
	}
}
