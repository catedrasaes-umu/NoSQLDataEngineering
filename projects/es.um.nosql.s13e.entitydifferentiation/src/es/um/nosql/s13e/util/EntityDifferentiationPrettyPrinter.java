package es.um.nosql.s13e.util;

import java.io.File;

import es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.util.ModelLoader;

public class EntityDifferentiationPrettyPrinter
{
  private static final String TAB = "  ";
  private static final String ENDL = System.lineSeparator();

  public static void main(String[] args)
  {
    String INPUT_FOLDER = "../es.um.nosql.models/";
    String[] input_models = new String[] {"everypolitician_sweden", "facebook", "harvard", "links","mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks", "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + "_Diff.xmi";
      System.out.println(printPretty(inputFile));
    }
  }

  public static String printPretty(String inputFile)
  {
    ModelLoader loader = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    EntityDifferentiation eDiff = loader.load(new File(inputFile), EntityDifferentiation.class);

    return printPretty(eDiff);
  }

  public static String printPretty(EntityDifferentiation entityDifferentiation)
  {
    if (entityDifferentiation == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append("EntityDifferentiation name: " + entityDifferentiation.getName() + ENDL);

    for (EntityDiffSpec eDiffSpec : entityDifferentiation.getEntityDiffSpecs())
      result.append(printPretty(eDiffSpec, TAB) + ENDL);

    return result.toString();
  }

  public static String printPretty(EntityDiffSpec eDiffSpec)
  {
    return printPretty(eDiffSpec, "");
  }

  private static String printPretty(EntityDiffSpec eDiffSpec, String defTabs)
  {
    if (eDiffSpec == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + "Entity: " + eDiffSpec.getEntity().getName() + ENDL);

    if (!eDiffSpec.getCommonProps().isEmpty())
    {
      result.append(defTabs + "Common props:" + ENDL);
      for (PropertySpec pSpec : eDiffSpec.getCommonProps())
        result.append(prettyPrint(pSpec, defTabs + TAB) + ENDL);
    }

    if (!eDiffSpec.getEntityVariationProps().isEmpty())
    {
      for (EntityVariationProp evProp : eDiffSpec.getEntityVariationProps())
      {
        result.append(defTabs + "EV " + evProp.getEntityVariation().getVariationId() + " ---------" + ENDL);

        if (!evProp.getPropertySpecs().isEmpty())
        {
          result.append(defTabs + "Own properties:" + ENDL);
          for (PropertySpec pSpec : evProp.getPropertySpecs())
            result.append(prettyPrint(pSpec, defTabs + TAB) + ENDL);          
        }

        if (!evProp.getNotProps().isEmpty())
        {
          result.append(defTabs + "Properties NOT to have:" + ENDL);
          for (PropertySpec pSpec : evProp.getNotProps())
            result.append(prettyPrint(pSpec, defTabs + TAB) + ENDL);          
        }
      }
    }

    return result.toString();
  }

  public static String prettyPrint(PropertySpec pSpec)
  {
    return prettyPrint(pSpec, "");
  }

  private static String prettyPrint(PropertySpec pSpec, String defTabs)
  {
    if (pSpec == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + " * " + pSpec.getProperty().getName() + (pSpec.isNeedsTypeCheck() ? " (needsTypeCheck)" : ""));

    return result.toString();
  }
}
