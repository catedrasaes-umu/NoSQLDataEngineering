package es.um.nosql.schemainference.json2dbschema.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.node.ArrayNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import com.google.gson.JsonArray;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.json2dbschema.main.util.JSON2Schema;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJAdapter;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.IAJArray;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson.GsonAdapter;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson.GsonArray;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.jackson.JacksonArray;
import es.um.nosql.schemainference.util.emf.ResourceManager;

/**
 * @author dsevilla
 *
 */
public class BuildNoSQLSchema
{
	public BuildNoSQLSchema()
	{
	}

	public void buildFromGsonArray(String schemaName, JsonArray jArray, String outputFile)
	{
		buildFromArray(schemaName, new GsonArray(jArray), outputFile, new GsonAdapter());
	}

	public void buildFromJacksonArray(String schemaName, ArrayNode jArray, String outputFile)
	{
		buildFromArray(schemaName, new JacksonArray(jArray), outputFile, new JacksonAdapter());
	}

	public void buildFromArray(String schemaName, IAJArray objRows, String outputFile, IAJAdapter<?> adapter)
	{
		NoSQLSchemaPackage packageInstance = NoSQLSchemaPackage.eINSTANCE;
		NoSQLSchema schema = new JSON2Schema<>(packageInstance.getNoSQLSchemaFactory(), adapter).fromJSONArray(schemaName, objRows);
		schema2File(packageInstance, schema, outputFile);
	}

	public void buildFromGsonFile(String inputFile, String outputFile)
	{
		buildFromFile(inputFile, outputFile, new GsonAdapter());
	}

	public void buildFromJacksonFile(String inputFile, String outputFile)
	{
		buildFromFile(inputFile, outputFile, new JacksonAdapter());
	}

	private void buildFromFile(String inputFile, String outputFile, IAJAdapter<?> adapter)
	{
		NoSQLSchemaPackage packageInstance = NoSQLSchemaPackage.eINSTANCE;

		NoSQLSchema schema = null;
		try
		{
			schema = new JSON2Schema<>(packageInstance.getNoSQLSchemaFactory(), adapter).fromJSONFile(inputFile);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		schema2File(packageInstance, schema, outputFile);
	}

	private void schema2File(NoSQLSchemaPackage packageInstance, NoSQLSchema schema, String outputFile)
	{
		// Create a new resource to serialize the ecore model
		Resource outputRes = new ResourceManager(packageInstance).getResourceSet().createResource(URI.createFileURI(outputFile));
		// Add our new package to resource contents
		outputRes.getContents().add(schema);

		// Make the actual URI to be exported in the generated models. This
		// allows using the models without having to register them.
		packageInstance.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.schemainference/model/nosqlschema.ecore", true));
		Map<Object,Object> options = new HashMap<Object,Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMIResource.OPTION_ENCODING, "UTF-8");

		try
		{
			outputRes.save(new FileOutputStream(outputFile), options);
		} catch (IOException e)
		{
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) throws IOException
	{
		String inputFile;
		String outputFile;

		if (args.length < 2)
		{
			System.err.println("At least the JSON file must be specified.");
			System.err.println("Usage: BuildNoSQLSchema JSONfile [outputXMIfile]");
			System.exit(-1);
			return;
		} else
		{
			inputFile = args[0];
			outputFile = args[1];
			File file = new File(outputFile);
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		BuildNoSQLSchema builder = new BuildNoSQLSchema();
		builder.buildFromJacksonFile(inputFile, outputFile);
	}
}
