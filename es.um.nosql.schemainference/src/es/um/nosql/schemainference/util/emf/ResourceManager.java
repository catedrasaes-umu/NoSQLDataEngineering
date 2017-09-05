package es.um.nosql.schemainference.util.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;


public class ResourceManager
{
	private ResourceSet resourceSet;

	/**
	 * Create the resource manager.
	 */
	public ResourceManager(EPackage... packageInstances)
	{
		setResourceSet(createResourceSet());

		// register factories
		Resource.Factory.Registry.INSTANCE
			.getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());

		// Add them to the resourceSet
		for (EPackage p : packageInstances)
			resourceSet.getPackageRegistry().put(p.getNsURI(), p);
	}

	/**
	 * Create the resource set.
	 */
	private ResourceSet createResourceSet() {
		ResourceSet rs = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone
		rs.getResourceFactoryRegistry()
		.getExtensionToFactoryMap()
		.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());

		return rs;
	}

	/**
	 * @return the resourceSet
	 */
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	private final void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void loadResourcesAsStrings(String... args)
	{
		for(String model: args)
			resourceSet.getResource(URI.createFileURI(model), true);
	}

	public Iterable<Resource> getResources()
	{
		return resourceSet.getResources();
	}
}
