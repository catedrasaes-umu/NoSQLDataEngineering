package es.um.nosql.schemainference.gen.schema.xtend;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage;
import es.um.nosql.schemainference.dslparameter.ODMParameterStandaloneSetupGenerated;
import es.um.nosql.schemainference.dsl4mongoose.MongooseModel;
//import jsonMM.Document;


public class Model_odm2model_xmi {
	/**
	 * Singleton reference.
	 */
	private static Model_odm2model_xmi instance;

	/**
	 * ResourceSet used to generate resource models.
	 */
	private ResourceSet resourceSet;

	/**
	 * Method used to get the ENoSQLSchemaIO instance.
	 * @return NoSQLSchemaIO instance.
	 */
	public static Model_odm2model_xmi getInstance()
	{
		if (instance == null)
			instance = new Model_odm2model_xmi();

		return instance;
	}

	private Model_odm2model_xmi()
	{
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(Dsl4mongoosePackage.eNS_URI, Dsl4mongoosePackage.eINSTANCE);		// EDBSCHEMA metamodel
		Map<String, Object> options = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		options.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		options.put("ecore", new EcoreResourceFactoryImpl());
	}

	private void exportXMI(String inM, String outM) {
	    // change MyLanguage with your language name
	    Injector injector = new ODMParameterStandaloneSetupGenerated()
	            .createInjectorAndDoEMFRegistration();
	    XtextResourceSet resourceSet = injector
	            .getInstance(XtextResourceSet.class);

	    // .ext is the extension of the model file
	    String inputURI =inM;//"tests/movies.odm";
	    String outputURI =outM;//"tests/movies_dsl_output.xmi";
	    System.out.println(inputURI+" "+outputURI);
	    URI uri = URI.createURI(inputURI);
	    Resource xtextResource = resourceSet.getResource(uri, true);

	    EcoreUtil.resolveAll(xtextResource);

	    Resource xmiResource = resourceSet
	            .createResource(URI.createURI(outputURI));
	    xmiResource.getContents().add(xtextResource.getContents().get(0));
	    try {
	        xmiResource.save(null);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		
		File inputModel= new File(args[0]);
		String inM=inputModel.getAbsolutePath();//.toString();
		String outM=new File (args[1]).getAbsolutePath();
				//.getPath();//
		//InyectorXMI.getInstance().exportXMI();
		//InyectorXMI..exportXMI();
		Model_odm2model_xmi.getInstance().exportXMI(inM,outM);
    }

}
