package es.um.nosql.orchestrator.util;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.json2dbschema.process.util.PropertyAnalyzer;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class OptionalPatcher {

  public static void main(String[] args)
  {
    OptionalPatcher patcher = new OptionalPatcher();
    patcher.patch("../es.um.nosql.models/stackoverflow/stackoverflow.xmi");
  }

  public void patch(String modelRoute)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(modelRoute), NoSQLSchema.class);

    PropertyAnalyzer analyzer = new PropertyAnalyzer();

    for (EntityClass e : schema.getEntities())
    {
      e.getVariations().forEach(var -> { var.getProperties().forEach(prop -> { prop.setOptional(false); }); });
      analyzer.setOptionalProperties(e.getVariations());
    }

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, modelRoute);
  }
}
