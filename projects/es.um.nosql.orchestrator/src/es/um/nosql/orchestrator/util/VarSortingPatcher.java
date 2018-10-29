package es.um.nosql.orchestrator.util;

import java.io.File;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class VarSortingPatcher
{
  public static void main(String[] args)
  {
    VarSortingPatcher patcher = new VarSortingPatcher();
    patcher.patch("../es.um.nosql.models/stackoverflow/stackoverflow.xmi");
    patcher.patch("../es.um.nosql.models/stackoverflow/stackoverflow_legacy.xmi");
  }

  public void patch(String modelRoute)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(modelRoute), NoSQLSchema.class);

    schema.getEntities().forEach(entity ->
    {
      ECollections.sort(entity.getVariations(), (var1, var2) -> var1.getFirstTimestamp() > var2.getFirstTimestamp() ? 1 : -1);

      for (int i = 1; i <= entity.getVariations().size(); i++)
        entity.getVariations().get(i - 1).setVariationId(i);
    });

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, modelRoute);
  }
}
