package es.um.nosql.s13e.util;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.util.compare.CompareNoSQLSchema;

public class NoSQLSchemaComparer
{
  public boolean compare(String route1, String route2)
  {
    return compare(new File(route1), new File(route2));
  }

  public boolean compare(File model1, File model2)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema1 = loader.load(model1, NoSQLSchema.class);
    NoSQLSchema schema2 = loader.load(model2, NoSQLSchema.class);

    return compare(schema1, schema2);
  }

  public boolean compare(NoSQLSchema schema1, NoSQLSchema schema2)
  {
    return new CompareNoSQLSchema().compare(schema1, schema2);
  }
}
