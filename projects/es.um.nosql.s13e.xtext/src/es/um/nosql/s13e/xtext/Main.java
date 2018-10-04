package es.um.nosql.s13e.xtext;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.xtext.util.ModelLoader;
import es.um.nosql.s13e.xtext.util.NoSQLSchemaWriter;

public class Main
{
  private static final String INPUT_FOLDER = "../es.um.nosql.models/";
  private static final String OUTPUT_FOLDER = "../es.um.nosql.models/";

  public static void main(String[] args)
  {
    String[] input_models = new String[] {"everypolitician_sweden", "facebook", "harvard", "links", "mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks", "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + ".xmi";
      String outputFile = OUTPUT_FOLDER + input_model + "/" + input_model + ".nosqlschema";

      ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
      NoSQLSchema schema = loader.load(inputFile, NoSQLSchema.class);

      NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
      //TODO: Fails with aggregations
      writer.writeAsXtext(schema, outputFile);
    }
  }
}
