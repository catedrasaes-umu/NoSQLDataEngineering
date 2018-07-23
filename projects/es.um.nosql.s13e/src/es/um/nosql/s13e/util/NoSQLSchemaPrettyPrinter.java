package es.um.nosql.s13e.util;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;

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

    for (Entity entity : nosqlschema.getEntities())
      result.append(printPretty(entity, TAB));

    return result.toString();
  }

  public static String printPretty(Entity entity)
  {
    return printPretty(entity, "");
  }

  private static String printPretty(Entity entity, String defTabs)
  {
    if (entity == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "Entity name: " + entity.getName() + (entity.isRoot() ? " (root)" : "") + ENDL);

    for (EntityVariation eVariation : entity.getEntityVariations())
      result.append(printPretty(eVariation, defTabs + TAB));

    return result.toString();
  }

  public static String printPretty(EntityVariation eVariation)
  {
    return printPretty(eVariation, "");
  }

  private static String printPretty(EntityVariation eVariation, String defTabs)
  {
    if (eVariation == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "EntityVariation vId: " + eVariation.getVariationId() + ENDL);
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
