/**
 *
 */
package es.um.nosql.schemainference.m2m.util.printer;


import java.io.File;
import org.eclipse.emf.ecore.resource.Resource;

import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec;
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationFactory;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage;
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec;
import es.um.nosql.schemainference.util.emf.ResourceManager;

/**
 * @author dsevilla
 *
 */
public class EntityDiffModelPrinter
{
	//private static final String MODEL_ROUTE = "tests/mongoMovies3.xmi";
	//private static final String SPECIAL_TYPE_IDENTIFIER = "type";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{		
		EntitydifferentiationPackage tdp = EntitydifferentiationPackage.eINSTANCE;
		ResourceManager rm = new ResourceManager(tdp);

		// Load the origin model.
		File sourceRes = new File(args[0]);
		rm.loadResourcesAsStrings("file://" + sourceRes.getAbsolutePath());
		//rm.loadResourcesAsStrings(args[0]);

		Iterable<Resource> resources = rm.getResources();
		EntityDifferentiation diff = EntitydifferentiationFactory.eINSTANCE.createEntityDifferentiation();

		printModel((EntityDifferentiation)resources.iterator().next().getContents().get(0));
	}

	private static void printModel(EntityDifferentiation entityDifferentiation)
	{
		for (EntityDiffSpec eds : entityDifferentiation.getEntityDiffSpecs())
		{
			System.out.println("Entity: " + eds.getEntity().getName());
			
			System.out.println("Common:");
			for (PropertySpec ps : eds.getCommonProps())
				System.out.println(" * " + ps.getProperty().getName() + (ps.isNeedsTypeCheck() ? "*" :""));

			for (EntityVersionProp evp : eds.getEntityVersionProps())
			{
				System.out.println("EV " + evp.getEntityVersion().getVersionId() + " -----------------");
				
				System.out.println("Own Properties:");
				for (PropertySpec ps: evp.getPropertySpecs())
					System.out.println(" * " + ps.getProperty().getName() + (ps.isNeedsTypeCheck() ? "*" :""));
				
				System.out.println("Properties NOT to have:");
				for (PropertySpec ps: evp.getNotProps())
					System.out.println(" * " + ps.getProperty().getName() + (ps.isNeedsTypeCheck() ? "*" :""));				
			}
		}
	}
}