package es.um.nosql.s13e.entitydifferentiation.util;

import java.io.File;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.util.ModelLoader;

public class EntityDifferentiationPrinter
{
  private static final String TAB = "  ";
  private static final String ENDL = System.lineSeparator();

  public static void main(String[] args)
  {
    EntityDifferentiationPrinter printer = new EntityDifferentiationPrinter();
    String INPUT_FOLDER = "../es.um.nosql.models/";
    String[] input_models = new String[] {"everypolitician_sweden", "facebook", "harvard", "links","mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks", "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + "_Diff.xmi";
      System.out.println(printer.printPretty(inputFile));
    }
  }

  public String printPretty(String inputFile)
  {
    ModelLoader loader = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    EntityDifferentiation eDiff = loader.load(new File(inputFile), EntityDifferentiation.class);

    return printPretty(eDiff);
  }

  public String printPretty(EntityDifferentiation entityDifferentiation)
  {
    if (entityDifferentiation == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append("EntityDifferentiation name: " + entityDifferentiation.getName() + ENDL);

    for (EntityDiff eDiffSpec : entityDifferentiation.getEntityDiffs())
      result.append(printPretty(eDiffSpec, TAB) + ENDL);

    return result.toString();
  }

  public String printPretty(EntityDiff eDiffSpec)
  {
    return printPretty(eDiffSpec, "");
  }

  private String printPretty(EntityDiff eDiffSpec, String defTabs)
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

    if (!eDiffSpec.getVariationDiffs().isEmpty())
    {
      for (StructuralVariationDiff evProp : eDiffSpec.getVariationDiffs())
      {
        result.append(defTabs + "EV " + evProp.getVariation().getVariationId() 
        		+ " ---------" + ENDL);

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

  public String prettyPrint(PropertySpec pSpec)
  {
    return prettyPrint(pSpec, "");
  }

  private String prettyPrint(PropertySpec pSpec, String defTabs)
  {
    if (pSpec == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append(defTabs + " * " + pSpec.getProperty().getName() + (pSpec.isNeedsTypeCheck() ? " (needsTypeCheck)" : ""));

    return result.toString();
  }
}
