package es.um.nosql.schemainference.db.interfaces;

import java.io.File;
import java.nio.file.Paths;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.db.utils.DbType;
import es.um.nosql.schemainference.db.utils.generator.JsonGenerator;
import es.um.nosql.schemainference.util.emf.ModelLoader;

public class Model2Db extends Source2Db
{
	public Model2Db(DbType db, String ip)
	{
	  super(db, ip);
  }

	public void run(String modelRoute, int minInstances, int maxInstances)
	{
	  long startTime = System.currentTimeMillis();

	  System.out.println("Reading input model " + modelRoute + "...");
	  storeJSONContent(modelRoute, minInstances, maxInstances);
	  System.out.println(Paths.get(modelRoute).getFileName() + " table created in " + (System.currentTimeMillis() - startTime) + " ms");
	}

  private void storeJSONContent(String modelRoute, int minInstances, int maxInstances)
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

		getClient().cleanDb(schema.getName().toLowerCase());
		getClient().insert(schema.getName().toLowerCase(), jsonContent);
	}
}
