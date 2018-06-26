package execution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

/**
 * Main class used to start the execution of the data visualization simulation.
 * It executes a main class which will generate some code and then create folders and
 * rearrange the generated code into those folders, ready to be displayed.
 * @author Alberto Hernández Chillón
 */
public class Main
{
	private static final Path BASE_PATH = Paths.get("NoSQLDataIndex/");

	private static final Path MODELS_PATH = Paths.get("examples/models/");

	private static final Path JSON_PATH = Paths.get("examples/json/");

	private static final Path DIFF_PATH = Paths.get("examples/differences/");

	private static final Path CODE_PATH = Paths.get("examples/code/");

	private static final Path LIB_PATH = Paths.get("lib/");

	private static final String BEGIN_OBJ_E_FILE = "_OBJ_E_";

	private static final String BEGIN_OBJ_I_FILE = "_OBJ_I_";

	private static final String BEGIN_STRUCT_FILE = "_STRUCT_";

	private static final String INDEX_HTML = "INDEX_";

	private static final String JS_EXT = ".js";

	private static final String JSON_EXT = ".json";

	private static final String HTML_EXT = ".html";

	/**
	 * Method used to stop the execution in case a folder is missing.
	 * @param missingDir The message to be displayed.
	 */
	private static void stopExecution(String missingDir)
	{
		System.err.println("Error while executing: Missing " + missingDir);
		System.exit(1);
	}

	public static void main(String[] args)
	{
		if (!MODELS_PATH.toFile().exists())
			stopExecution(MODELS_PATH.toString());

		if (!JSON_PATH.toFile().exists())
			stopExecution(JSON_PATH.toString());

		if (!DIFF_PATH.toFile().exists())
			stopExecution(DIFF_PATH.toString());

		if (!CODE_PATH.toFile().exists())
			stopExecution(CODE_PATH.toString());

		if (!LIB_PATH.toFile().exists())
			stopExecution(LIB_PATH.toString());

		try
		{
			// Recreate example dir.
			if (!BASE_PATH.toFile().exists())
				BASE_PATH.toFile().mkdirs();

			FileUtils.cleanDirectory(BASE_PATH.toFile());
			FileUtils.cleanDirectory(DIFF_PATH.toFile());
			FileUtils.cleanDirectory(CODE_PATH.toFile());

			// Execute the main method to generate code.
			data.Main.main(new String[0]);

			// For each model in the input folder, create a folder in the example dir with the code library and also the generated code for that example.
			for (File model : MODELS_PATH.toFile().listFiles())
			{
				String modelName = model.getName().substring(0, model.getName().lastIndexOf("."));
				Path modelFolderPath = BASE_PATH.resolve(modelName);
				modelFolderPath.toFile().mkdir();

				// Copy the lib files.
				FileUtils.copyDirectory(LIB_PATH.toFile(), modelFolderPath.toFile());

				// Copy the generated code.
				Path genFolder = modelFolderPath.resolve("js/gen/");
				genFolder.toFile().mkdir();
				File objIF = CODE_PATH.resolve(BEGIN_OBJ_I_FILE + modelName + JS_EXT).toFile();
				File objEF = CODE_PATH.resolve(BEGIN_OBJ_E_FILE + modelName + JS_EXT).toFile();
				File structF = CODE_PATH.resolve(BEGIN_STRUCT_FILE + modelName + JS_EXT).toFile();
				File indexF = CODE_PATH.resolve(INDEX_HTML + modelName + HTML_EXT).toFile();

				if (objIF.exists())
					FileUtils.copyFileToDirectory(objIF, genFolder.toFile());

				if (objEF.exists())
					FileUtils.copyFileToDirectory(objEF, genFolder.toFile());

				if (structF.exists())
					FileUtils.copyFileToDirectory(structF, genFolder.toFile());

				if (indexF.exists())
					FileUtils.copyFileToDirectory(indexF, modelFolderPath.toFile());

				// Copy the input json file.
				File jsonF = JSON_PATH.resolve(modelName + JSON_EXT).toFile();

				if (jsonF.exists())
					FileUtils.copyFileToDirectory(jsonF, modelFolderPath.toFile());
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
