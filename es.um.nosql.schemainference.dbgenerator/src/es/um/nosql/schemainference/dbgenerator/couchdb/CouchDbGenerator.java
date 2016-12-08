package es.um.nosql.schemainference.dbgenerator.couchdb;
/**
 *
 */


import java.io.File;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.dbgenerator.util.ModelLoader;

/**
 * @author dsevilla
 *
 */
public class CouchDbGenerator
{
	public static void main(String[] args)
	{
		(new CouchDbGenerator()).run(args);
	}

	private void run(String[] args)
	{
		String filename = args.length == 0 ? "model/test.xmi" : args[0];
		File file = new File(filename);
		String dbname = file.getName().split("\\.")[0];

		ModelLoader<NoSQLSchema> loader = new ModelLoader<>(NoSQLSchemaPackage.eINSTANCE);
		NoSQLSchema schema = loader.load(file);

		try {
			CouchDbProperties properties =
					new CouchDbProperties(dbname, true, "http", "127.0.0.1", 5984, null, null);
			CouchDbClient dbClient = new CouchDbClient(properties);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
