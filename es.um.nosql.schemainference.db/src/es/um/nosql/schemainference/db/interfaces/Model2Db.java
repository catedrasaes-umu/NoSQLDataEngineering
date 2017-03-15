package es.um.nosql.schemainference.db.interfaces;

import java.io.File;
import java.io.PrintWriter;

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

	public void storeJSONContent(String modelRoute, String jsonFolder, int minInstances, int maxInstances)
	{
		ModelLoader<NoSQLSchema> loader = new ModelLoader<NoSQLSchema>(NoSQLSchemaPackage.eINSTANCE);
		JsonGenerator generator = new JsonGenerator();

		NoSQLSchema schema = loader.load(new File(modelRoute));
		String jsonContent = "";

		try(PrintWriter fileOut = new PrintWriter(jsonFolder + schema.getName() + ".json"))
		{
			jsonContent = generator.generate(schema, minInstances, maxInstances);
			fileOut.println(jsonContent);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		client.cleanDb(schema.getName());
		client.insert(schema.getName(), jsonContent);
	}
}
