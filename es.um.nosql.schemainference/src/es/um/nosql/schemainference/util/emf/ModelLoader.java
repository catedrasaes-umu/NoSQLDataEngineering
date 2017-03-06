package es.um.nosql.schemainference.util.emf;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ModelLoader<T>
{
	private ResourceSet resourceSet;

	static
	{
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = reg.getExtensionToFactoryMap();
		map.put("xmi", new XMIResourceFactoryImpl());
		map.put("nosqlschema", new XMIResourceFactoryImpl());
	}

	public ModelLoader(EPackage thePackage)
	{
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(thePackage.getNsURI(), thePackage);
	}

	@SuppressWarnings("unchecked")
	public T load(File file)
	{
		Resource r = resourceSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
		return (T)r.getContents().get(0);
	}
}
