package es.um.nosql.s13e.decisiontree;

import java.io.File;

import es.um.nosql.s13e.DecisionTree.DecisionTrees;
import es.um.nosql.s13e.decisiontree.m2m.EntityDiffToDecisionTree;
import es.um.nosql.s13e.decisiontree.m2t.DecisionTreeJSBaseGen;
import es.um.nosql.s13e.decisiontree.m2t.DecisionTreeToJS;
import es.um.nosql.s13e.util.DecisionTreeWriter;

public class Main 
{
  private static final String INPUT_FOLDER = "../es.um.nosql.models/";
  private static final String OUTPUT_FOLDER = "../es.um.nosql.models/";
  private static final boolean GENERATE_BASE_FILES = true;

  //TODO: Módulo de salida -> J48 a .dot
  public static void main(String[] args)
  {
    // Everypolitician_sweden and OpenSanctions examples do not work properly. Maybe there is a generation problem with aggregates...?
    // Mongosongs is not correctly classified. Album_1 and Album_2 are not correctly separated. Check it out.
    String[] input_models = new String[] {"everypolitician_sweden", "facebook", "harvard", "links","mongomovies", "opensanctions",
      "proteins", "publications", "stackoverflow", "urban", "webclicks", "mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + "_Diff.xmi";
      String outputFile = OUTPUT_FOLDER + input_model + "/" + input_model + "_Tree.xmi";
      String outputFolder = OUTPUT_FOLDER + input_model + "/" + input_model + "/";
      prepareM2MExample(new File(inputFile), new File(outputFile));
      prepareM2TExample(new File(outputFile), new File(outputFolder));
    }
  }

  public static void prepareM2MExample(File inputFile, File outputFile)
  {
    System.out.println("Generating DecisionTree model for " + inputFile.getName() + " in " + outputFile.getPath());

    EntityDiffToDecisionTree transformer = new EntityDiffToDecisionTree();
    DecisionTrees dTrees = transformer.m2m(inputFile);

    DecisionTreeWriter writer = new DecisionTreeWriter();
    writer.write(dTrees, outputFile.getAbsolutePath());

    System.out.println("Transformation model finished");
  }

  public static void prepareM2TExample(File inputFile, File outputFolder)
  {
    System.out.println("Generating Javascript code for " + inputFile + " in " + outputFolder);

    if (!outputFolder.exists())
      outputFolder.mkdirs();

    if (GENERATE_BASE_FILES)
    {
      DecisionTreeJSBaseGen baseGen = new DecisionTreeJSBaseGen();
      baseGen.m2t(inputFile, outputFolder);
    }

    DecisionTreeToJS tree2js = new DecisionTreeToJS();
    tree2js.m2t(inputFile, outputFolder);

    System.out.println("Code generation finished");
  }
}
