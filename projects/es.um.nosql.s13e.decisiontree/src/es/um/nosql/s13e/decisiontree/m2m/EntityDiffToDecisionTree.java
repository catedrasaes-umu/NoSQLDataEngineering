package es.um.nosql.s13e.decisiontree.m2m;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import es.um.nosql.s13e.DecisionTree.DecisionTreeFactory;
import es.um.nosql.s13e.DecisionTree.DecisionTreeForEntity;
import es.um.nosql.s13e.DecisionTree.DecisionTreeNode;
import es.um.nosql.s13e.DecisionTree.DecisionTrees;
import es.um.nosql.s13e.DecisionTree.IntermediateNode;
import es.um.nosql.s13e.DecisionTree.LeafNode;
import es.um.nosql.s13e.DecisionTree.PropertySpec2;
import es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.decisiontree.util.ModelNode;
import es.um.nosql.s13e.decisiontree.util.OpenJ48;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.Serializer;
import weka.classifiers.trees.j48.ClassifierTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class EntityDiffToDecisionTree
{
  public DecisionTrees m2m(File modelFile)
  {
    ModelLoader loader = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    EntityDifferentiation entityDiff = loader.load(modelFile, EntityDifferentiation.class);

    return m2m(entityDiff);
  }

  public DecisionTrees m2m(EntityDifferentiation entityDiff)
  {
    DecisionTrees decTrees = DecisionTreeFactory.eINSTANCE.createDecisionTrees();

    decTrees.setName(entityDiff.getName());

    entityDiff.getEntityDiffSpecs().stream().filter(ed -> ed.getEntityVariationProps().size() > 1)
      .forEach(eDiffSpec ->
      {
        ModelNode root = generateTreeForEntity(eDiffSpec);
        DecisionTreeForEntity decTreeEntity = DecisionTreeFactory.eINSTANCE.createDecisionTreeForEntity();
        decTreeEntity.setEntity(eDiffSpec.getEntity());
        decTreeEntity.setRoot(decisionTreeForEntity(root));
        decTrees.getTrees().add(decTreeEntity);
      });

    return decTrees;
  }

  private ModelNode generateTreeForEntity(EntityDiffSpec eDiffSpec)
  {
    Map<EntityVariationProp, List<Pair<String, PropertySpec>>> propsByEv =
      eDiffSpec.getEntityVariationProps().stream().collect(toMap(Function.identity(),
        eVarProp -> Stream.concat(
          eVarProp.getPropertySpecs().stream().map(ps -> Pair.of(serialize(ps), ps)),
          eVarProp.getNotProps().stream().map(ps -> Pair.of(serializeNot(ps), ps)))
        .collect(toList()), (u,v) -> u, LinkedHashMap::new));

    // serializedFeature -> PropertySpec. We do the final reducing to leave just one element on 
    // the list, as there may be features in different entity variation that serialize to the same
    // string. We just select one.
    Map<String, PropertySpec> features = propsByEv.values().stream().flatMap(l -> l.stream())
      .collect(toMap(Pair::getKey, Pair::getValue, (u,v) -> u, LinkedHashMap::new));

    final List<String> fValues = Arrays.asList(new String[]{"yes","no"});

    // Attributes associated to each feature (propertyspec serialization)
    Map<String, Attribute> attrMap = features.keySet().stream().collect(toMap(Function.identity(),
      s -> new Attribute(s, fValues), (u,v) -> u, LinkedHashMap::new));

    // Generate inverted index for feature serialization to feature vector position

    final String entityName = eDiffSpec.getEntity().getName();
    Map<String,EntityVariationProp> classNameToEvp = eDiffSpec.getEntityVariationProps().stream()
      .map(evp -> Pair.of(String.format("%1$s_%2$d", entityName, evp.getEntityVariation().getVariationId()),evp))
      .collect(toMap(Pair::getKey,Pair::getValue));
    Map<EntityVariationProp,String> evpToClassName = classNameToEvp.entrySet().stream()
      .collect(toMap(Map.Entry::getValue, Map.Entry::getKey));

    // Get classes and count them. We do it in the order that they are obtained
    // from the original list of EntityVariationProps
    List<String> classes = propsByEv.keySet().stream().map(evp -> evpToClassName.get(evp)).collect(toCollection(ArrayList::new));

    // Define Weka Instances Model. Build Attribute models for weka
    ArrayList<Attribute> atts = new ArrayList<>(attrMap.values());
    Attribute tag = new Attribute("tag", classes);
    atts.add(tag);

    // Generate Dataset
    Instances dataset = new Instances("Train", atts, classes.size());

    final double[] defaultValues = new double[features.size() + 1];
    Arrays.fill(defaultValues,1.0);

    Map<EntityVariationProp, Instance> featuresByEv = propsByEv.entrySet().stream()
      .collect(toMap(Map.Entry::getKey,
          e -> {
            Instance inst = new DenseInstance(1.0, defaultValues);
            e.getValue().forEach(p -> inst.setValue(attrMap.get(p.getKey()),"yes"));
            inst.setValue(tag, evpToClassName.get(e.getKey()));
            return inst;
          }));

    dataset.addAll(featuresByEv.values());
    dataset.setClass(tag);

    try
    {
      // Get Classification Tree
      OpenJ48 tree = generateTree(dataset);
      ClassifierTree root = tree.get_m_root();

      System.out.println(tree);
      for (int i = 0; i < dataset.numInstances(); i++){
        System.out.println(dataset.get(i).stringValue(tag)+": "
            + tree.classifyInstance(dataset.get(i)));
      }

      System.out.println(dataset);
      ModelNode modelTree = getModelTree(root, classNameToEvp.entrySet().stream() .collect(toMap(Map.Entry::getKey, v -> v.getValue().getEntityVariation())), features);
      printModelTree(eDiffSpec.getEntity(),modelTree);

      return modelTree;
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    // Error
    return null;
  }

  private DecisionTreeNode decisionTreeForEntity(ModelNode root)
  {
    if (root.isLeaf())
    {
      LeafNode leafNode =  DecisionTreeFactory.eINSTANCE.createLeafNode();
      leafNode.setIdentifiedVariation(root.getEv());
      return leafNode;
    }
    else
    {
      IntermediateNode intermNode = DecisionTreeFactory.eINSTANCE.createIntermediateNode();

      // Exchange branches when the test is negative.
      if (root.isCheckNot())
      {
        intermNode.setNoBranch(decisionTreeForEntity(root.getNodePresent()));
        intermNode.setYesBranch(decisionTreeForEntity(root.getNodeAbsent()));
      }
      else
      {
        intermNode.setYesBranch(decisionTreeForEntity(root.getNodePresent()));
        intermNode.setNoBranch(decisionTreeForEntity(root.getNodeAbsent()));
      }

      // Create PropertySpec2 from PropertySpec
      PropertySpec2 propSpec2 = DecisionTreeFactory.eINSTANCE.createPropertySpec2();
      propSpec2.setProperty(root.getProperty().getProperty());
      propSpec2.setNeedsTypeCheck(root.getProperty().isNeedsTypeCheck());

      intermNode.setCheckedProperty(propSpec2);

      return intermNode;
    }
  }

  private OpenJ48 generateTree(Instances train) throws Exception
  {
    OpenJ48 classifier = new OpenJ48();
    classifier.setUnpruned(true);
    classifier.setMinNumObj(1);
    classifier.buildClassifier(train);

    return classifier;
  }

  private ModelNode getModelTree(ClassifierTree tree, Map<String, EntityVariation> entityVariations, Map<String, PropertySpec> properties) throws Exception
  {
    if (tree.isLeaf())
    {
      String tag = tree.prefix();
      Pattern pattern = Pattern.compile("\\[(.*?) .*$");
      Matcher matcher = pattern.matcher(tag);

      if (matcher.find())
      {
        EntityVariation ev = entityVariations.get(matcher.group(1));
        return new ModelNode(ev);
      }
      else
        throw new Exception("Invalid exp reg for: "+tag);
    }
    else
    {
      //ClassifierSplitModel classifierSplitModel = tree.getLocalModel();
      ClassifierTree[] sons = tree.getSons();
      String left = tree.getLocalModel().leftSide(tree.getTrainingData()).trim();

      PropertySpec propSpec = properties.get(left);

      if (propSpec == null)
        throw new Exception("Unknown Property Name: " + left);

      if (sons.length != 2)
        throw new Exception("This is not a binary decision tree");

      ModelNode m = new ModelNode(propSpec, left.startsWith("!"));
      for (int i = 0 ; i < sons.length; i++)
      {
        String value = tree.getLocalModel().rightSide(i, tree.getTrainingData()).trim();
        ModelNode node = getModelTree(sons[i], entityVariations, properties);
        if (value.equals("= yes"))
          m.setNodePresent(node);
        else if (value.contentEquals("= no"))
          m.setNodeAbsent(node);
        else
          throw new Exception("Unknown Right side: " + value.trim());
      }

      return m;
    }
  }

  private void printModelTree(Entity entity, ModelNode tree)
  {
    printModelTree(entity, tree, 0);
  }

  private void printModelTree(Entity entity, ModelNode tree, int level)
  {
    String indent = String.join("", Collections.nCopies(level, "  "));

    if (tree.isLeaf())
      System.out.println(indent + "Entity: " + entity.getName() + ", Variation: " + tree.getEv().getVariationId());
    else
    {
      Function<Boolean,String> present = v -> v ? " is present." : " is NOT present."; 

      System.out.println(indent + tree.getProperty().getProperty().getName() + present.apply(!tree.isCheckNot()));
      printModelTree(entity, tree.getNodePresent(), level + 1);

      System.out.println(indent + tree.getProperty().getProperty().getName() + present.apply(tree.isCheckNot()));
      printModelTree(entity, tree.getNodeAbsent(), level + 1);
    }
  }

  private String serialize(PropertySpec propSpec)
  {
    if (propSpec.isNeedsTypeCheck())
      return Serializer.serialize(propSpec.getProperty());
    else
      return propSpec.getProperty().getName();
  }

  private String serializeNot(PropertySpec propSpec)
  {
    return "!" + propSpec.getProperty().getName();
  }
}