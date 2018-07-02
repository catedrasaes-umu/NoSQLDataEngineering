package es.um.nosql.orchestrator.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.util.emf.ModelLoader;
import es.um.nosql.s13e.util.emf.ResourceManager;

public class ModifyModelTest
{
  String inputRoute = "models/stackoverflow.xmi";
  String outputRoute = "models/stackoverflow_output.xmi";

  @Test
  public void test()
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(inputRoute), NoSQLSchema.class);

    for (Entity e : schema.getEntities())
      for (EntityVariation ev : e.getEntityvariations())
        ev.getProperties().stream().filter(p -> p instanceof Reference).map(p -> (Reference)p).forEach(ref ->
        {
          if (e.getName().equals("Posts") && (ref.getName().equals("LastEditorUserId") || ref.getName().equals("OwnerUserId")))
            ref.setOriginalType("String");

          if (e.getName().equals("Postlinks") && (ref.getName().equals("PostId") || ref.getName().equals("RelatedPostId")))
            ref.setOriginalType("String");

          if (e.getName().equals("Tags") && (ref.getName().equals("ExcerptPostId") || ref.getName().equals("WikiPostId")))
            ref.setOriginalType("String");

          if (e.getName().equals("Votes") && (ref.getName().equals("PostId") || ref.getName().equals("UserId")))
            ref.setOriginalType("String");

          if (e.getName().equals("Comments") && (ref.getName().equals("PostId") || ref.getName().equals("UserId")))
            ref.setOriginalType("String");

          if (e.getName().equals("Badges") && (ref.getName().equals("UserId")))
            ref.setOriginalType("String");
        });

    Resource outputRes = new ResourceManager(NoSQLSchemaPackage.eINSTANCE).getResourceSet().createResource(URI.createFileURI(outputRoute));
    outputRes.getContents().add(schema);
  
    NoSQLSchemaPackage.eINSTANCE.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));
    Map<Object,Object> options = new HashMap<Object,Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8");
  
    try
    {
      outputRes.save(new FileOutputStream(outputRoute), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}