package es.um.nosql.s13e.decisiontree.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.decisiontree.util.ModelNode;
import es.um.nosql.s13e.decisiontree.util.OpenJ48;
import es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity;
import es.um.nosql.s13e.DecisionTree.DecisionTreeNode;
import es.um.nosql.s13e.DecisionTree.DecisionTrees;
import es.um.nosql.s13e.DecisionTree.DecisionTreeFactory;
import es.um.nosql.s13e.DecisionTree.DecisionTreePackage;
import es.um.nosql.s13e.DecisionTree.IntermediateNode;
import es.um.nosql.s13e.DecisionTree.LeafNode;
import es.um.nosql.s13e.DecisionTree.PropertySpec2;
import es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.util.emf.ModelLoader;
import es.um.nosql.s13e.util.emf.ResourceManager;
import es.um.nosql.s13e.util.emf.Serializer;
import weka.classifiers.trees.j48.ClassifierTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Main 
{
  public static final String INPUT_FOLDER = "../es.um.nosql.examples/";
  public static final String OUTPUT_FOLDER = "../es.um.nosql.examples/";

  public static void main(String[] args)
  {
    String[] input_models = new String[] {/*"everypolitician_sweden", "facebook", "harvard", "links", "mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks", */"mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + "_Diff.xmi";
      String outputFile = OUTPUT_FOLDER + input_model + "/" + input_model + "_Tree.xmi";
      prepareTreeExample(inputFile, outputFile);
    }
  }

  public static void prepareTreeExample(String inputFile, String outputFile)
  {
    File INPUT_MODEL = new File(inputFile);
    File OUTPUT_MODEL = new File(outputFile);

    System.out.println("Generating DecisionTree model for " + INPUT_MODEL.getName() + " in " + OUTPUT_MODEL.getPath());

    EntityDiffToDecisionTree transformer = new EntityDiffToDecisionTree();
    DecisionTrees dTrees = transformer.m2m(INPUT_MODEL);

    EntityDifferentiationPackage entitydiffPackage = EntityDifferentiationPackage.eINSTANCE;
    DecisionTreePackage decisiontreePackage = DecisionTreePackage.eINSTANCE;
    ResourceManager resManager = new ResourceManager(entitydiffPackage, decisiontreePackage);

    entitydiffPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e.entitydifferentiation/model/entitydifferentiation.ecore", true));
    decisiontreePackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e.entitydifferentiation/model/decisiontree.ecore", true));

    Resource outputRes = resManager.getResourceSet().createResource(URI.createFileURI(OUTPUT_MODEL.getAbsolutePath()));
    outputRes.getContents().add(dTrees);

    // Configure output
    Map<Object,Object> options = new HashMap<Object,Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8");

    try
    {
      outputRes.save(new FileOutputStream(OUTPUT_MODEL), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    System.out.println("Transformation model finished");
  }
}
