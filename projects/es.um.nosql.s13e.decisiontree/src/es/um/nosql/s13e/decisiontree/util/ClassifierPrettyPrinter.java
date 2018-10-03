package es.um.nosql.s13e.decisiontree.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.function.Function;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.decisiontree.util.constants.ConfigConstants;
import weka.core.Attribute;
import weka.core.Instances;

public class ClassifierPrettyPrinter
{
  public void printClassifierTree(Attribute tag, OpenJ48 classifier, Instances dataset)
  {
    System.out.println(classifier);

    try
    {
      for (int i = 0; i < dataset.numInstances(); i++)
        System.out.println(dataset.get(i).stringValue(tag) + ": " + classifier.classifyInstance(dataset.get(i)));
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    System.out.println(dataset);
  }

  public void printModelTree(EntityClass entity, ModelNode tree)
  {
    printModelTree(entity, tree, 0);
  }

  private void printModelTree(EntityClass entity, ModelNode tree, int level)
  {
    String indent = String.join("", Collections.nCopies(level, "  "));

    if (tree.isLeaf())
      System.out.println(indent + "Entity: " + entity.getName() + ", Variation: " + tree.getStructuralVariation().getVariationId());
    else
    {
      Function<Boolean,String> present = v -> v ? " is present." : " is NOT present."; 

      System.out.println(indent + tree.getProperty().getProperty().getName() + present.apply(!tree.isCheckNot()));
      printModelTree(entity, tree.getNodePresent(), level + 1);

      System.out.println(indent + tree.getProperty().getProperty().getName() + present.apply(tree.isCheckNot()));
      printModelTree(entity, tree.getNodeAbsent(), level + 1);
    }
  }

  public void generateTreePNG(OpenJ48 classifier, EntityDiffSpec eDiffSpec)
  {
    File theFolder = new File(ConfigConstants.IMAGE_FOLDER + ((EntityDifferentiation)eDiffSpec.eContainer()).getName() + "/");
    if (!theFolder.exists())
      theFolder.mkdirs();

    String dotRoute = theFolder.getAbsolutePath() + "/" + eDiffSpec.getEntity().getName() + ".dot";
    String pngRoute = theFolder.getAbsolutePath() + "/" + eDiffSpec.getEntity().getName() + ".png";

    try
    {
      PrintStream output = new PrintStream(new FileOutputStream(dotRoute));
      output.print(classifier.graph());
      output.close();
      String[] parameters = {"dot", "-Tpng", dotRoute, "-o", pngRoute};
      Process process = Runtime.getRuntime().exec(parameters);
      process.waitFor();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
