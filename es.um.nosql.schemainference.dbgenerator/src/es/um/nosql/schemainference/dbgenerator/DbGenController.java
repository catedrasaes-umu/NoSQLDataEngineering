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

	public void startTest(String modelsFolder, String jsonFolder)
	{
		ModelLoader<NoSQLSchema> loader = new ModelLoader<NoSQLSchema>(NoSQLSchemaPackage.eINSTANCE);
		JsonGenerator generator = new JsonGenerator();

		for (File file : new File(modelsFolder).listFiles())
		{
			NoSQLSchema schema = loader.load(file);

			try(PrintWriter fileOut = new PrintWriter(jsonFolder + schema.getName() + ".json"))
			{
				String jsonContent = generator.generate(schema, 10, 30);
				client.insert(schema.getName(), jsonContent);
				fileOut.println(generator.generate(schema));
			} catch (FileNotFoundException exception)
			{
				exception.printStackTrace();
			} catch (JsonProcessingException e)
			{
				e.printStackTrace();
			}
		}

		shutdown();
	}
}
