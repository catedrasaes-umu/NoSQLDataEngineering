/**
 * 
 */
package es.um.nosql.schemainference.dbgenerator.util;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * @author dsevilla
 *
 */
public class ModelLoader<T>  
{

	private ResourceSet set;
	
	static {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());
	}
	
	public ModelLoader(EPackage tp)
	{
		set = new ResourceSetImpl();
		set.getPackageRegistry().put(tp.getNsURI(), tp);
	}
	
	@SuppressWarnings("unchecked")
	public T load(File file)
	{
		Resource r = set.getResource(URI.createURI(file.getAbsolutePath()), true);
		return (T)r.getContents().get(0);
	}

	public ResourceSet getSet() {
		return set;
	}

}
