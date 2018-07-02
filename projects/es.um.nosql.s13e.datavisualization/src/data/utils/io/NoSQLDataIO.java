package data.utils.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Variation_Diff.Variation_DiffPackage;
import Variation_Diff.NoSQLDifferences;

/**
 * Class used to read a DBDIFFERENCES model from an input file or a String.
 * Implemented as a Singleton.
 * @author Alberto Hernández Chillón
 */
public class NoSQLDataIO
{
	/**
	 * Singleton reference.
	 */
	private static NoSQLDataIO instance;

	/**
	 * ResourceSet used to generate resource models.
	 */
	private ResourceSet resourceSet;

	/**
	 * Method used to get the NoSQLSchemaIO instance.
	 * @return NoSQLSchemaIO instance.
	 */
	public static NoSQLDataIO getInstance()
	{
		if (instance == null)
			instance = new NoSQLDataIO();

		return instance;
	}

	/**
	 * Default private constructor. Initializes the ResourceSet and the factory.
	 */
	private NoSQLDataIO()
	{
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(Variation_DiffPackage.eNS_URI, Variation_DiffPackage.eINSTANCE);		// DBDIFFERENCES metamodel
		Map<String, Object> options = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		options.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		options.put("ecore", new EcoreResourceFactoryImpl());
	}

	/**
	 * Method used to read a model from a local file and create a NoSQLDifferences model from it.
	 * @param routeToModel The route to the model file in a string.
	 * @return A NoSQLDifferences object containing the file model.
	 */
	public NoSQLDifferences readNoSQLDifferences(String routeToModel)
	{
		File theDifferencesFile = new File(routeToModel);

		if (!theDifferencesFile.exists() || !theDifferencesFile.isFile())
			return null;

		Resource resource = resourceSet.getResource(URI.createFileURI(theDifferencesFile.getAbsolutePath()), true);

		if (resource != null && !resource.getContents().isEmpty())
			return (NoSQLDifferences)resource.getContents().get(0);
		else
			return null;
	}

	/**
	 * Method used to read a model from a string and create a NoSQLDifferences model from it.
	 * @param dummy_model_uri The model route, just to create a key in the ResourceSet.
	 * @param model_text The textual model.
	 * @return A NoSQLDifferences object containing the textual model.
	 * @throws IOException
	 */
	public NoSQLDifferences readNoSQLDifferences(String dummy_model_uri, String model_text) throws IOException 
	{
		Resource resource = resourceSet.createResource(URI.createURI(dummy_model_uri));
		resource.load(new ByteArrayInputStream(model_text.getBytes()), resourceSet.getLoadOptions());

		if (resource != null && !resource.getContents().isEmpty())
			return (NoSQLDifferences)resource.getContents().get(0);
		else
			return null;
	}

	/**
	 * Method used to write a NoSQLDifferences model into a given file.
	 * @param differencesModel The differences model to be written.
	 * @param modelPath The route to the file which will contain the model.
	 */
	public void write(NoSQLDifferences differencesModel, String modelPath)
	{
		Resource resource = resourceSet.createResource(URI.createFileURI(modelPath));
		resource.getContents().add(differencesModel);

		try
		{
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
}
