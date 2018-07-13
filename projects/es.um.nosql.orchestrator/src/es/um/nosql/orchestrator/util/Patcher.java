package es.um.nosql.orchestrator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.util.emf.ModelLoader;
import es.um.nosql.s13e.util.emf.ResourceManager;

// TODO: Yeah, this class shouldnt exist at all, but the inference process is not perfect and some fixes need to be maded.
public class Patcher
{
  /**
   * Temporal method used to patch all these inference errors until we actually fix them on the inference process.
   * First, it will set correctly the _type attribute on each variation.
   * Second, if an _id is inferred as an Aggregate instead of an ObjectId, it will fix it.
   * TODO: I swear we will fix these problems!
   */
  public static void PATCH_INFERENCE_ERRORS(String outputModel)
  {
    File outputFile = new File(outputModel);
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(outputFile, NoSQLSchema.class);

    Entity idToDelete = null;
    for (Entity e : schema.getEntities())
    {
      if (e.getName().equals("_id"))
      {
        idToDelete = e;
        continue;
      }
      for (EntityVariation ev : e.getEntityVariations())
      {
        Property malformedId = null;
        for (Property p : ev.getProperties())
        {
          if (p.getName().equals("_type"))
            ((PrimitiveType)((Attribute)p).getType()).setName(e.getName());
          if (p.getName().equals("_id") && p instanceof Aggregate)
            malformedId = p;
        }
        if (malformedId != null)
        {
          Attribute newId = NoSQLSchemaFactory.eINSTANCE.createAttribute();
          PrimitiveType newIdType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
          newId.setName("_id");
          newIdType.setName("ObjectId");
          newId.setType(newIdType);

          ev.getProperties().remove(malformedId);
          ev.getProperties().add(0, newId);
        }
      }
    }
    if (idToDelete != null)
      schema.getEntities().remove(idToDelete);

    NoSQLSchemaPackage nosqlschemaPackage = NoSQLSchemaPackage.eINSTANCE;
    ResourceManager resManager = new ResourceManager(nosqlschemaPackage);

    nosqlschemaPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e/model/nosqlschema.ecore", true));

    Resource outputRes = resManager.getResourceSet().createResource(URI.createFileURI(outputFile.getAbsolutePath()));
    outputRes.getContents().add(schema);

    // Configure output
    Map<Object,Object> options = new HashMap<Object,Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8");

    try
    {
      outputRes.save(new FileOutputStream(outputFile), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
