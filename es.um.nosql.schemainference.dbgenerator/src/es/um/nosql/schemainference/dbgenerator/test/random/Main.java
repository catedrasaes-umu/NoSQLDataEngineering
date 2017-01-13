package es.um.nosql.schemainference.dbgenerator.test.random;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.dbgenerator.util.ModelLoader;

/**
 * Main class used to generate a JSON file for the DBSCHEMA models existing in an input folder.
 * @author Alberto Hernández Chillón
 */
public class Main
{
	private static final String INPUT_FOLDER = "examples/models/";

	private static final String JSON_FOLDER = "examples/json/";

	private static final String OUTPUT_FOLDER = "examples/gen/";

	private static final String MODEL_EXT = ".nosql_schema";

	private static final String JSON_EXT = ".json";

	public static void main(String[] args)
	{
//		generateModelAndJson();
		generateJsonFromModel();
	}
/*
	private static void generateModelAndJson()
	{
		String NAME = "example";

		NoSQLSchemaIO nosqld_io = NoSQLSchemaIO.getInstance();

		try(PrintWriter fileOut = new PrintWriter(OUTPUT_FOLDER + NAME + JSON_EXT))
		{
			fileOut.println(ObjGenerator.getInstance().toString());
		} catch (FileNotFoundException exception)
		{
			exception.printStackTrace();
		}

		NoSQLSchema schema = ObjGenerator.getInstance().getSchema(NAME);

		nosqld_io.write(schema, OUTPUT_FOLDER + NAME + MODEL_EXT);
	}
*/
	private static void generateJsonFromModel()
	{
		String filename = "model/NoSQLSchema.xmi";
		File file = new File(filename);
		String dbname = file.getName().split("\\.")[0];

		ModelLoader<NoSQLSchema> loader = new ModelLoader<>(NoSQLSchemaPackage.eINSTANCE);
		NoSQLSchema schema = loader.load(file);
		JsonGenerator generator = new JsonGenerator();
		try
		{
			System.out.println(generator.generate(schema));
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
/*		NoSQLSchemaIO nosqld_io = NoSQLSchemaIO.getInstance();
		JsonGenerator generator = new JsonGenerator();

		for (File file : new File(INPUT_FOLDER).listFiles())
		{
			NoSQLSchema schema = nosqld_io.readNoSQLSchema(file.toString());

			try(PrintWriter fileOut = new PrintWriter(JSON_FOLDER + schema.getName() + JSON_EXT))
			{
				fileOut.println(generator.generate(schema));
			} catch (FileNotFoundException exception)
			{
				exception.printStackTrace();
			}
		}
*/
	}
}
