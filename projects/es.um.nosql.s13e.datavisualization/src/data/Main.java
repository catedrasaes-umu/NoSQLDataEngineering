package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import Variation_Diff.NoSQLDifferences;
import data.generation.DifferencesToJSData;
import data.generation.DifferencesToJSObj;
import data.transform.M2M;
import data.utils.io.NoSQLDataIO;
import data.utils.serializer.NoSQLDataSerializer;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import data.utils.io.NoSQLSchemaIO;
import data.utils.serializer.NoSQLSchemaSerializer;

public class Main
{
	private static final String MODEL_INPUT_FOLDER = "examples/models/";

	private static final String MODEL_OUTPUT_FOLDER = "examples/differences/";

	private static final String CODE_OUTPUT_FOLDER = "examples/code/";

	private static final String INPUT_MODEL_EXT = ".nosqlschema";

	private static final String OUTPUT_MODEL_EXT = ".variation_diff";

	private static final String OUTPUT_CODE_EXT = ".js";

	private static final String OUTPUT_HTML_EXT = ".html";

	private static final boolean GENERATE_INCLUSIVE_COMPARISONS = true;

	private static final boolean GENERATE_EXCLUSIVE_COMPARISONS = false;

	private static void writeCodeFile(CharSequence code, String fileName)
	{
		try(PrintWriter fileOut = new PrintWriter(fileName))
		{
			fileOut.println(code);
		} catch (FileNotFoundException exception) { exception.printStackTrace(); }
	}

	public static void main(String[] args)
	{
		NoSQLSchemaIO nosql_io = NoSQLSchemaIO.getInstance();
		NoSQLDataIO nosqld_io = NoSQLDataIO.getInstance();
		DifferencesToJSObj m2tObjTransform = new DifferencesToJSObj();
		DifferencesToJSData m2tDataTransform = new DifferencesToJSData();

		// If no arguments were provided, get the default folder and start generating schemas for each model.
		if (args.length == 0)
		{
			System.out.println("WARNING: No models were provided. Working with default settings.\n");
			System.out.println("Working with models in folder: " + MODEL_INPUT_FOLDER);
			System.out.println("Generating data differences in folder: " + MODEL_OUTPUT_FOLDER);

			File inputFolder = new File(MODEL_INPUT_FOLDER);

			if (!inputFolder.exists())
				System.out.println("Default folder doesn't exist.");

			for (File file : inputFolder.listFiles())
			{
				if (!file.getName().substring(file.getName().lastIndexOf(".")).equals(INPUT_MODEL_EXT))
					continue;

				System.out.println("Generating data differences for " + file.getPath());

				NoSQLDifferences dataModelObject = M2M.getInstance().transform(nosql_io.readNoSQLSchema(file.getPath()));
				nosqld_io.write(dataModelObject, MODEL_OUTPUT_FOLDER + file.getName().replace(INPUT_MODEL_EXT, OUTPUT_MODEL_EXT));

				writeCodeFile(m2tObjTransform.generateObjFunctions(dataModelObject, GENERATE_INCLUSIVE_COMPARISONS),
						CODE_OUTPUT_FOLDER + "_OBJ_I_" + file.getName().replace(INPUT_MODEL_EXT, OUTPUT_CODE_EXT));

				writeCodeFile(m2tObjTransform.generateObjFunctions(dataModelObject, GENERATE_EXCLUSIVE_COMPARISONS),
						CODE_OUTPUT_FOLDER + "_OBJ_E_" + file.getName().replace(INPUT_MODEL_EXT, OUTPUT_CODE_EXT));

				writeCodeFile(m2tDataTransform.generateStructFunctions(dataModelObject),
						CODE_OUTPUT_FOLDER + "_STRUCT_" + file.getName().replace(INPUT_MODEL_EXT, OUTPUT_CODE_EXT));

				writeCodeFile(m2tDataTransform.printIndex(dataModelObject.getName()),
						CODE_OUTPUT_FOLDER + "INDEX_" + file.getName().replace(INPUT_MODEL_EXT, OUTPUT_HTML_EXT));
			}
		}
		// Parse arguments if they were provided.
		else
		{
			Collection<File> modelList = new ArrayList<File>();
			boolean verbose = false;
			String outputRoute = MODEL_OUTPUT_FOLDER;

			for (String param : args)
			{
				// Verbose mode.
				if (param.equals("--v"))
					verbose = true;

				// Output folder.
				else if (param.startsWith("--o="))
					outputRoute = param.replace("--o=", "");

				else
					modelList.add(new File(param));
			}

			System.out.println("Generating data differences in folder: " + outputRoute);

			// Generate data differences for each given model.
			for (File inputModel : modelList)
			{
				if (!inputModel.exists())
				{
					System.err.println(inputModel + " doesn't exist. Skipping generation.");
					continue;
				}

				System.out.println("Generating data differences for " + inputModel.getPath());

				NoSQLSchema modelObject = nosql_io.readNoSQLSchema(inputModel.getPath());

				if (verbose)
					System.out.println(NoSQLSchemaSerializer.getInstance().stringify(modelObject));

				NoSQLDifferences dataModelObject = M2M.getInstance().transform(modelObject);

				if (verbose)
					System.out.println(NoSQLDataSerializer.getInstance().stringify(dataModelObject));

				if (verbose)
					System.out.println("Generating OBJ code inclusive differences.");

				writeCodeFile(m2tObjTransform.generateObjFunctions(dataModelObject, GENERATE_INCLUSIVE_COMPARISONS),
						CODE_OUTPUT_FOLDER + "_OBJ_I_" + inputModel.getName().replace(INPUT_MODEL_EXT, OUTPUT_CODE_EXT));

				if (verbose)
					System.out.println("Generating OBJ code exclusive differences.");

				writeCodeFile(m2tObjTransform.generateObjFunctions(dataModelObject, GENERATE_EXCLUSIVE_COMPARISONS),
						CODE_OUTPUT_FOLDER + "_OBJ_E_" + inputModel.getName().replace(INPUT_MODEL_EXT, OUTPUT_CODE_EXT));

				if (verbose)
					System.out.println("Generating STRUCT code.");

				writeCodeFile(m2tDataTransform.generateStructFunctions(dataModelObject),
						CODE_OUTPUT_FOLDER + "_STRUCT_" + inputModel.getName().replace(INPUT_MODEL_EXT, OUTPUT_CODE_EXT));

				nosqld_io.write(dataModelObject, outputRoute + inputModel.getName().replace(INPUT_MODEL_EXT, OUTPUT_MODEL_EXT));
			}
		}
	}
}
