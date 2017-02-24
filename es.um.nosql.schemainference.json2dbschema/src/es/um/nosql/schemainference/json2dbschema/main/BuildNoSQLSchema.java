/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import com.google.gson.JsonElement;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.json2dbschema.main.util.JSON2Schema;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.gson.GsonAdapter;
import es.um.nosql.schemainference.json2dbschema.util.abstractjson.impl.jackson.JacksonAdapter;
import es.um.nosql.schemainference.util.emf.ResourceManager;

/**
 * @author dsevilla
 *
 */
public class BuildNoSQLSchema
{
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		String inFile, outFile;
		OutputStream outStream = System.out;

		if (args.length < 1)
		{
			System.err.println("At least the JSON file must be specified.");
			System.err.println("Usage: BuildNoSQLSchema JSONfile [outputXMIfile]");
			System.exit(-1);
			return;
		} else
		{
			inFile = args[0];

			if (args.length < 2)
			{
				outStream = System.out;
				outFile = "-";
			}
			else
			{
				outFile = args[1];
				File file = new File(outFile);
				file.getParentFile().mkdirs();
				file.createNewFile();
				outStream = new FileOutputStream(file);
			}
		}

		NoSQLSchemaPackage dbsp = NoSQLSchemaPackage.eINSTANCE;
		ResourceManager rm = new ResourceManager(dbsp);

		JacksonAdapter adapter = new JacksonAdapter();
		JSON2Schema<JsonNode, JacksonAdapter> j2s =
				new JSON2Schema<>(dbsp.getNoSQLSchemaFactory(), adapter);
		NoSQLSchema schema = j2s.fromJSONFile(inFile);

		// now create a new resource to serialize the ecore model
		Resource outputRes = rm.getResourceSet().createResource(URI.createFileURI(outFile));
		// add our new package to resource contents
		outputRes.getContents().add(schema);

		// Make the actual URI to be exported in the generated models. This
		// allows using the models without having to register them.
		dbsp.eResource().setURI(
				URI.createPlatformResourceURI("es.um.nosql.schemainference/model/dbschema.ecore", true));
		Map<Object,Object> options = new HashMap<Object,Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMIResource.OPTION_ENCODING, "UTF-8");

		// and at last, we save to standard out.
		// Remove the first argument to save to file specified in pathToOutputFile
		outputRes.save(outStream, options);
	}

	public static void mainGson(String[] args) throws IOException
	{
		String inFile, outFile;
		OutputStream outStream = System.out;

		if (args.length < 1)
		{
			System.err.println("At least the JSON file must be specified.");
			System.err.println("Usage: BuildNoSQLSchema JSONfile [outputXMIfile]");
			System.exit(-1);
			return;
		} else
		{
			inFile = args[0];

			if (args.length < 2)
			{
				outStream = System.out;
				outFile = "-";
			}
			else
			{
				outFile = args[1];
				File file = new File(outFile);
				file.getParentFile().mkdirs();
				file.createNewFile();
				outStream = new FileOutputStream(file);
			}
		}

		NoSQLSchemaPackage dbsp = NoSQLSchemaPackage.eINSTANCE;
		ResourceManager rm = new ResourceManager(dbsp);

		GsonAdapter adapter = new GsonAdapter();
		JSON2Schema<JsonElement, GsonAdapter> j2s =
				new JSON2Schema<>(dbsp.getNoSQLSchemaFactory(), adapter);
		NoSQLSchema schema = j2s.fromJSONFile(inFile);

		// now create a new resource to serialize the ecore model
		Resource outputRes = rm.getResourceSet().createResource(URI.createFileURI(outFile));
		// add our new package to resource contents
		outputRes.getContents().add(schema);

		// Make the actual URI to be exported in the generated models. This
		// allows using the models without having to register them.
		dbsp.eResource().setURI(
				URI.createPlatformResourceURI("es.um.nosql.schemainference/model/dbschema.ecore", true));
		Map<Object,Object> options = new HashMap<Object,Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMIResource.OPTION_ENCODING, "UTF-8");

		// and at last, we save to standard out.
		// Remove the first argument to save to file specified in pathToOutputFile
		outputRes.save(outStream, options);
	}
}
