package es.um.nosql.s13e.util;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class NoSQLSchemaComparer
{
  public static void main(String[] args)
  {
/*    String INPUT_FOLDER = "../es.um.nosql.models/";
    String[] input_models = new String[] {"everypolitician_sweden", "facebook", "harvard", "links","mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks", "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + ".xmi";
      System.out.println(printPretty(inputFile));
    }*/

    CompareProperty cp = new CompareProperty();
    Aggregate a1 = NoSQLSchemaFactory.eINSTANCE.createAggregate(); a1.setName("a1");
    Aggregate n1 = NoSQLSchemaFactory.eINSTANCE.createAggregate(); n1.setName("a1");

    System.out.println(cp.compare(a1, n1));
  }
}
