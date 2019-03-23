package es.um.nosql.s13e.util;

import java.io.File;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;

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

    for (RelationshipType rel : nosqlschema.getRelationships())
      result.append(printPretty(rel, TAB));

    for (EntityType entity : nosqlschema.getEntities())
      result.append(printPretty(entity, TAB));

    return result.toString();
  }

  public String printPretty(RelationshipType rel)
  {
    return printPretty(rel, "");
  }

  private String printPretty(RelationshipType rel, String defTabs)
  {
    if (rel == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "RelationshipType name: " + rel.getName() + ENDL);

    if (!rel.getParents().isEmpty())
      result.append(defTabs + "parents: " + rel.getParents().stream().map(parent -> parent.getName()).collect(Collectors.joining(", ")));

    for (StructuralVariation stVariation : rel.getVariations())
      result.append(printPretty(stVariation, defTabs + TAB));

    return result.toString();
  }

  public String printPretty(EntityType entity)
  {
    return printPretty(entity, "");
  }

  private String printPretty(EntityType entity, String defTabs)
  {
    if (entity == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "EntityType name: " + entity.getName() + (entity.isRoot() ? " (root)" : "") + ENDL);

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
