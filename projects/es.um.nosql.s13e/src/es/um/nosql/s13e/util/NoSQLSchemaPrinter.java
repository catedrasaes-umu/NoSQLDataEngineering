package es.um.nosql.s13e.util;

import java.io.File;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;

public class NoSQLSchemaPrinter
{
  private static final String TAB = "  ";
  private static final String ENDL = System.lineSeparator();

  public static void main(String[] args)
  {
    NoSQLSchemaPrinter printer = new NoSQLSchemaPrinter();
    String INPUT_FOLDER = "../es.um.nosql.models/";
    String[] input_models = new String[] {/*"everypolitician_sweden", "facebook", "harvard", "links","mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks",*/ "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + ".xmi";
      System.out.println(printer.printPretty(inputFile));
    }
  }

  public String printPretty(String inputFile)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema nosqlschema = loader.load(new File(inputFile), NoSQLSchema.class);

    return printPretty(nosqlschema);
  }

  public String printPretty(NoSQLSchema nosqlschema)
  {
    if (nosqlschema == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append("NoSQLSchema name: " + nosqlschema.getName() + ENDL);

    for (ReferenceClass ref : nosqlschema.getRefClasses())
      result.append(printPretty(ref, TAB));

    for (EntityClass entity : nosqlschema.getEntities())
      result.append(printPretty(entity, TAB));

    return result.toString();
  }

  public String printPretty(ReferenceClass ref)
  {
    return printPretty(ref, "");
  }

  private String printPretty(ReferenceClass ref, String defTabs)
  {
    if (ref == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "ReferenceClass name: " + ref.getName() + ENDL);

    if (!ref.getParents().isEmpty())
      result.append(defTabs + "parents: " + ref.getParents().stream().map(parent -> parent.getName()).collect(Collectors.joining(", ")));

    for (StructuralVariation stVariation : ref.getVariations())
      result.append(printPretty(stVariation, defTabs + TAB));

    return result.toString();
  }

  public String printPretty(EntityClass entity)
  {
    return printPretty(entity, "");
  }

  private String printPretty(EntityClass entity, String defTabs)
  {
    if (entity == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "EntityClass name: " + entity.getName() + (entity.isRoot() ? " (root)" : "") + ENDL);

    if (!entity.getParents().isEmpty())
      result.append(defTabs + "parents: " + entity.getParents().stream().map(parent -> parent.getName()).collect(Collectors.joining(", ")));

    for (StructuralVariation stVariation : entity.getVariations())
      result.append(printPretty(stVariation, defTabs + TAB));

    return result.toString();
  }

  public String printPretty(StructuralVariation eVariation)
  {
    return printPretty(eVariation, "");
  }

  private String printPretty(StructuralVariation stVariation, String defTabs)
  {
    if (stVariation == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "StructuralVariation vId: " + stVariation.getVariationId() + " count: " + stVariation.getCount() +
        " initTs: " + stVariation.getFirstTimestamp() + " lastTs: " + stVariation.getLastTimestamp() + ENDL);
    for (Property prop : stVariation.getProperties())
      result.append(printPretty(prop, defTabs + TAB));

    return result.toString();
  }

  public String printPretty(Property property)
  {
    return printPretty(property, "");
  }

  private String printPretty(Property property, String defTabs)
  {
    if (property == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + Serializer.serialize(property) + ENDL);

    return result.toString();
  }
}
