package es.um.nosql.schemainference.decisiontree.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.util.emf.ModelLoader;
import es.um.nosql.schemainference.util.emf.NoSQLSchemaSerializer;
import weka.classifiers.trees.j48.ClassifierSplitModel;
import weka.classifiers.trees.j48.ClassifierTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Main {

	public static OpenJ48 generateTree(Instances train) throws Exception
    {
		OpenJ48 classifier = new OpenJ48();
		classifier.setUnpruned(true);
		classifier.setMinNumObj(1);
		classifier.buildClassifier(train);
		return classifier;
	}
	
	
	public static Map<String, EntityVersion> getEntityVersions(NoSQLSchema schema)
	{
		Map<String, EntityVersion> entityVersions = new HashMap<String, EntityVersion>();		
		for (Entity entity: schema.getEntities())
		{
			for (EntityVersion entityVersion: entity.getEntityversions())
			{
				String key = String.format("%1$s:%2$d", entity.getName(), entityVersion.getVersionId());
				entityVersions.put(key, entityVersion);
			}
		}

		return entityVersions;
	}
	
	public static Map<String, Property> getProperties(NoSQLSchema schema)
	{
		Map<String, Property> properties = new HashMap<String, Property>();
		
		for (Entity entity: schema.getEntities())
		{
			for (EntityVersion entityVersion: entity.getEntityversions())
			{
				for (Property property: entityVersion.getProperties()){
					properties.put(NoSQLSchemaSerializer.serialize(property), property);
				}
			}
		}

		return properties;
		
	}
	
	public static Map<String, List<String>> getClasses(NoSQLSchema schema)
	{
		Map<String, List<String>> classes = new HashMap<String, List<String>>();
		for (Entity entity: schema.getEntities())
		{
			for (EntityVersion entityVersion: entity.getEntityversions())
			{
				// Get List of properties Names
				List<String> properties = entityVersion.getProperties().stream()
						.map(NoSQLSchemaSerializer::serialize)
						.collect(Collectors.toList());
								
				// Add current Entity Version to entities Map
				String key = String.format("%1$s:%2$d", entity.getName(), entityVersion.getVersionId());
				classes.put(key, properties);
			}
		}

		return classes;
	}
	
	public static Map<String, int[]> oneHot(Map<String, List<String>> classes, List<String> featuresList)
	{
		Map<String, int[]> result = new HashMap<String, int[]>();
		Map<String, Integer> features = new HashMap<String, Integer>();
		
		features = IntStream.range(0, featuresList.size()).boxed()
			.collect(Collectors.toMap(i -> featuresList.get(i), Function.identity()));
		
		int vector_size = features.size(); // Features + Tag
		
		String[] list_classes = classes.keySet().toArray(new String[classes.size()]);
		for (int i=0; i < list_classes.length; i++)
        {
			int[] base = new int[vector_size];
			String tag = list_classes[i];
			List<String> current_features = classes.get(tag);
			for (String feature: current_features)
            {
				int index = features.get(feature);
				base[index] = 1;
			}

			result.put(tag, base);
		}
		
		return result;
	}

    public static ArrayList<Attribute> getWekaAttributes(List<String> classes, List<String> features)
    {
		// Count properties
		int maxNumFeatures = features.size();

		// Define Nominal values for features fields	
		List<String> f_values = Arrays.asList(new String[]{"1","0"});
		
		// Define Weka Instances Model
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		for (int i = 0; i < maxNumFeatures; i++)
        {
			Attribute attribute = new Attribute(features.get(i), f_values);
			atts.add(attribute);
		}
		Attribute tag = new Attribute("tag", classes);	
		atts.add(tag);
		return atts;
	}
	
	public static Instances getDataset(ArrayList<Attribute> attributes, List<String> classes, Map<String, int[]> binary_vectors)
    {
		// Build a Dataset from Weka Attributes 
		int num_classes = classes.size();
		Instances dataset = new Instances("Train", attributes, num_classes);
		Attribute tag = attributes.get(attributes.size() - 1);

		// For each classes, create a Weka Instance and add it to the dataset
		for (String name: classes)
		{
			// TODO: Update to Class Weight
			double weight = 1.0; 
			int[] vector = binary_vectors.get(name);
			Instance ints = new DenseInstance(vector.length + 1);
			
			for (int i = 0; i < vector.length; i++)
			{
				ints.setValue(attributes.get(i), String.valueOf(vector[i]));
			}
			
			ints.setValue(tag, name);
			dataset.add(ints);			
		}

		dataset.setClass(tag);
		return dataset;
	}
	

	public static ModelTree getModelTree(ClassifierTree tree, Map<String, EntityVersion> entityVersions, Map<String, Property> properties) throws Exception
	{
		if (tree.isLeaf())
		{
			// Print Class value
			String tag = tree.prefix();
			
			Pattern pattern = Pattern.compile("\\[(.*?) \\([\\d\\.\\,]+\\)\\]");
			Matcher matcher = pattern.matcher(tag);
			
			if (matcher.find())
			{
				EntityVersion ev = entityVersions.get(matcher.group(1));
				return new ModelTree(ev);			
			}
			
			else throw new Exception("Invalid exp reg for: "+tag);
		}
		else
		{			
			ClassifierSplitModel classifierSplitModel = tree.getLocalModel();
			ClassifierTree[] sons = tree.getSons();	
			String left = tree.getLocalModel().leftSide(tree.getTrainingData());
			Property p = properties.get(left.trim());
			
			if (p==null) throw new Exception("Unknown Property Name: " + left.trim());
			if (sons.length != 2) throw new Exception("This is not a binary decision tree");

			ModelTree m = new ModelTree(p);			
			for (int i = 0 ; i < sons.length; i++)
			{
				String value = tree.getLocalModel().rightSide(i, tree.getTrainingData());
				ModelTree node = getModelTree(sons[i], entityVersions, properties);
				if (value.trim().equals("= 1"))
				{
					m.setNodePresent(node);
				}else if (value.trim().contentEquals("= 0"))
				{
					m.setNodeAbsent(node);
				}
				else
				{
					throw new Exception("Unknown Right side: " + value.trim());
				}
			}
			
			return m;
		}
	}
	
	public static void runModelTree(ModelTree tree){
		runModelTree(tree, 0);
	}
	
	public static void runModelTree(ModelTree tree, int level){
		String indent = "";
		for (int i = 0; i<level; i++) indent += "  ";
		
		if (tree.is_leaft()){
			Entity e = (Entity) tree.getTag().eContainer();
			System.out.println(indent+"Entity: "+e.getName()+", Version: "+tree.getTag().getVersionId());
		}
		else{
			System.out.println(indent+tree.getProperty().getName()+" is present");
			runModelTree(tree.getNodePresent(), level+1);
			System.out.println(indent+tree.getProperty().getName()+" isn't present");
			runModelTree(tree.getNodeAbsent(), level+1);
		}
	}

	public static void main(String[] args)
    {
		ModelLoader<NoSQLSchema> loader = new ModelLoader<NoSQLSchema>(NoSQLSchemaPackage.eINSTANCE);
		NoSQLSchema schema = loader.load(new File("model/mongoMovies3.xmi"));
		
		// Get list of classes and list of their properties
		Map<String, List<String>> classes = getClasses(schema);
		
		// Count classes
		int num_classes = classes.size();

		// Get List of properties names
		Set<String> featuresNames = new HashSet<String>();		
		for (List<String> list : classes.values())
		{
			featuresNames.addAll(list);
		}
		
		List<String> featuresList = Arrays.asList(featuresNames.toArray(new String[featuresNames.size()]));		
		List<String> classesList = Arrays.asList(classes.keySet().toArray(new String[num_classes]));
		
		// Encode classes into binary vectors
		Map<String, int[]> binary_vectors = oneHot(classes, featuresList);
		
		// Build Attribute models for weka
		ArrayList<Attribute> atts = getWekaAttributes(classesList, featuresList);
		Attribute tag = atts.get(atts.size() - 1);
		
		// Generate Dataset
		Instances dataset = getDataset(atts, classesList, binary_vectors);
				
		
		try {
			// Get Classification Tree 
			OpenJ48 tree = generateTree(dataset);
			ClassifierTree root = tree.get_m_root();
			
			System.out.println(tree);
			for (int i = 0; i < dataset.numInstances(); i++){
				System.out.println(dataset.get(i).stringValue(tag)+": "+ tree.classifyInstance(dataset.get(i)));
			}
			
			ModelTree  modelTree = Main.getModelTree(root, Main.getEntityVersions(schema), Main.getProperties(schema));
			Main.runModelTree(modelTree);
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
