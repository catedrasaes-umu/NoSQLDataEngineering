package es.um.nosql.s13e.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;

public class NoSQLSchemaWriter
{
  private ResourceSet resourceSet;
  private Map<Object, Object> options;

  public NoSQLSchemaWriter()
  {
    resourceSet = new ResourceManager(NoSQLSchemaPackage.eINSTANCE).getResourceSet();
    NoSQLSchemaPackage.eINSTANCE.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));
    options = new HashMap<Object,Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8");
    options.put(XMIResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
  }

  public Map<Object, Object> getOptions()
  {
    return options;
  }

  public void write(NoSQLSchema schema, String outputRoute)
  {
    Resource outputRes = resourceSet.createResource(URI.createFileURI(outputRoute));
    outputRes.getContents().add(schema);

    try
    {
      outputRes.save(new FileOutputStream(outputRoute), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
