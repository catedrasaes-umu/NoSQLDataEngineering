package es.um.nosql.s13e.util;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;

// TODO: Adapt to version 2
public class NoSQLSchemaPrettyPrinter
{
  private static final String TAB = "  ";
  private static final String ENDL = System.lineSeparator();

  public static void main(String[] args)
  {
    String INPUT_FOLDER = "../es.um.nosql.models/";
    String[] input_models = new String[] {/*"everypolitician_sweden", "facebook", "harvard", "links","mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks",*/ "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + ".xmi";
      System.out.println(printPretty(inputFile));
    }
  }

  public static String printPretty(String inputFile)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema nosqlschema = loader.load(new File(inputFile), NoSQLSchema.class);

    return printPretty(nosqlschema);
  }

  public static String printPretty(NoSQLSchema nosqlschema)
  {
    if (nosqlschema == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append("NoSQLSchema name: " + nosqlschema.getName() + ENDL);

    for (EntityClass entity : nosqlschema.getEntities())
      result.append(printPretty(entity, TAB));

    return result.toString();
  }

  public static String printPretty(EntityClass entity)
  {
    return printPretty(entity, "");
  }

  private static String printPretty(EntityClass entity, String defTabs)
  {
    if (entity == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "Entity name: " + entity.getName() + (entity.isRoot() ? " (root)" : "") + ENDL);

    for (StructuralVariation eVariation : entity.getVariations())
      result.append(printPretty(eVariation, defTabs + TAB));

    return result.toString();
  }

  public static String printPretty(StructuralVariation eVariation)
  {
    return printPretty(eVariation, "");
  }

  private static String printPretty(StructuralVariation eVariation, String defTabs)
  {
    if (eVariation == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "EntityVariation vId: " + eVariation.getVariationId() + " count: " + eVariation.getCount() + " ts: " + eVariation.getTimestamp() + ENDL);
    for (Property prop : eVariation.getProperties())
      result.append(printPretty(prop, defTabs + TAB));

    return result.toString();
  }

  public static String printPretty(Property property)
  {
    return printPretty(property, "");
  }

  private static String printPretty(Property property, String defTabs)
  {
    if (property == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + Serializer.serialize(property) + ENDL);

    return result.toString();
  }
}
