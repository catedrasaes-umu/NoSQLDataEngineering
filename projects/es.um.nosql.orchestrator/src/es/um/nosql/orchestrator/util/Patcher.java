package es.um.nosql.orchestrator.util;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

// TODO: Yeah, this class shouldnt exist at all, but the inference process is not perfect and some fixes need to be maded.
public class Patcher
{
  /**
   * Temporal method used to patch all these inference errors until we actually fix them on the inference process.
   * It will set correctly the _type attribute on each variation.
   * TODO: I swear we will fix these problems!
   */
  public static void PATCH_INFERENCE_ERRORS(String outputModel)
  {
    File outputFile = new File(outputModel);
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(outputFile, NoSQLSchema.class);

    for (Entity e : schema.getEntities())
      for (EntityVariation ev : e.getVariations())
        for (Property p : ev.getProperties())
          if (p.getName().equals("_type"))
            ((PrimitiveType)((Attribute)p).getType()).setName(e.getName());

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, outputModel);
  }
}
