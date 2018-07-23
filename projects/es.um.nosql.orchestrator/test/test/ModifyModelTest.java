package test;

import java.io.File;
import org.junit.Test;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

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
      for (EntityVariation ev : e.getEntityVariations())
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

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, outputRoute);
  }
}