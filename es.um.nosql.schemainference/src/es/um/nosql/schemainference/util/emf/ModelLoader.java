package es.um.nosql.schemainference.util.emf;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ModelLoader<T>
{
	private ResourceSet resourceSet;
	static Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;

	static
	{
		Map<String, Object> map = reg.getExtensionToFactoryMap();
		map.put("xmi", new XMIResourceFactoryImpl());
		map.put("nosqlschema", new XMIResourceFactoryImpl());
	}

	public ModelLoader()
	{
		resourceSet = new ResourceSetImpl();
		final ExtendedMetaData extendedMetaData = 
			new BasicExtendedMetaData(resourceSet.getPackageRegistry());
		resourceSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA,
		    extendedMetaData);
	}
	
	public ModelLoader(EPackage thePackage)
	{
		this();
		registerPackages(thePackage);
	}

	// builder
	public ModelLoader<T> forPackages(EPackage... packages)
	{
		registerPackages(packages);
		return this;
	}

	// builder
	public ModelLoader<T> withExtension(String extension, Object rf)
	{
		registerExtension(extension,rf);
		return this;
	}
	
	public void registerExtension(String extension, Object resourceFactory)
	{
		reg.getExtensionToFactoryMap().put(extension, resourceFactory);
	}
	
	public void registerPackages(EPackage... packages)
	{
		for (EPackage p : packages)
			resourceSet.getPackageRegistry().put(p.getNsURI(), p);
	}
	
	@SuppressWarnings("unchecked")
	public T load(File file)
	{
		Resource r = resourceSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
		return (T)r.getContents().get(0);
	}

	public ResourceSet getResourceSet()
	{
		return resourceSet;
	}
}
