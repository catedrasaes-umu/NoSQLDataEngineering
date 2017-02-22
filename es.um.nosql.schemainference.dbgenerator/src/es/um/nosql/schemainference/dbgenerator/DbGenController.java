package es.um.nosql.schemainference.dbgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.dbgenerator.db.DbClient;
import es.um.nosql.schemainference.dbgenerator.db.couchdb.CouchDbAdapter;
import es.um.nosql.schemainference.dbgenerator.db.mongodb.MongoDbAdapter;
import es.um.nosql.schemainference.dbgenerator.generator.JsonGenerator;
import es.um.nosql.schemainference.dbgenerator.utils.DbType;
import es.um.nosql.schemainference.dbgenerator.utils.ModelLoader;

public class DbGenController
{
	private DbClient client;

	public DbGenController(DbType db, String ip)
	{
		switch (db)
		{
			case COUCHDB: { client = CouchDbAdapter.getCouchDbClient(ip); break;}
			case MONGODB: { client = MongoDbAdapter.getMongoDbClient(ip); break;}
			default: throw new IllegalArgumentException("Database type not implemented yet.");
		}
	}

	public boolean shutdown()
	{
		return client.shutdown();
	}

	private void cleanDbs()
	{
		client.cleanDbs();
	}

	public void startTest(String modelsFolder, String jsonFolder, int minInstances, int maxInstances)
	{
		long startTime = System.currentTimeMillis();

		cleanDbs();
		System.out.println("Database tables cleaned in " + (System.currentTimeMillis() - startTime) + " ms");

		System.out.println("Reading input models...");
		ModelLoader<NoSQLSchema> loader = new ModelLoader<NoSQLSchema>(NoSQLSchemaPackage.eINSTANCE);
		JsonGenerator generator = new JsonGenerator();

		for (File file : new File(modelsFolder).listFiles())
		{
			long intermTime = System.currentTimeMillis();
			NoSQLSchema schema = loader.load(file);

			try(PrintWriter fileOut = new PrintWriter(jsonFolder + schema.getName() + ".json"))
			{
				String jsonContent = generator.generate(schema, minInstances, maxInstances);
				client.insert(schema.getName(), jsonContent);
				fileOut.println(jsonContent);
				System.out.println(schema.getName() + " table created in " + (System.currentTimeMillis() - intermTime) + " ms");
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			} catch (JsonProcessingException e2)
			{
				e2.printStackTrace();
			} catch (Exception e3)
			{
				e3.printStackTrace();
			}
		}

		System.out.println("Test finished in " + (System.currentTimeMillis() - startTime + " ms"));
		shutdown();
	}
}
