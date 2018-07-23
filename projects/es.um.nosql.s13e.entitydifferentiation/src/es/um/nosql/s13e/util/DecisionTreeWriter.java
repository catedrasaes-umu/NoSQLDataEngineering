package es.um.nosql.s13e.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.s13e.DecisionTree.DecisionTreePackage;
import es.um.nosql.s13e.DecisionTree.DecisionTrees;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;

public class DecisionTreeWriter
{
  private ResourceSet resourceSet;
  private Map<Object, Object> options;

  public DecisionTreeWriter()
  {
    resourceSet = new ResourceManager(NoSQLSchemaPackage.eINSTANCE, EntityDifferentiationPackage.eINSTANCE, DecisionTreePackage.eINSTANCE).getResourceSet();
    NoSQLSchemaPackage.eINSTANCE.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));
    EntityDifferentiationPackage.eINSTANCE.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e.entitydifferentiation/model/entitydifferentiation.ecore", true));
    DecisionTreePackage.eINSTANCE.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e.entitydifferentiation/model/decisiontree.ecore", true));
    options = new HashMap<Object, Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8");
  }

  public void write(DecisionTrees treesModel, String outputRoute)
  {
    Resource outputRes = resourceSet.createResource(URI.createFileURI(outputRoute));
    outputRes.getContents().add(treesModel);

    try
    {
      outputRes.save(new FileOutputStream(outputRoute), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
