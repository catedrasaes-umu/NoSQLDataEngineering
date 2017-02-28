package es.um.nosql.schemainference.decisiontree.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import weka.classifiers.Classifier;
import weka.classifiers.trees.REPTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Main {

	public static Classifier generateTree(Instances train) throws Exception{
		Classifier classifier = new REPTree();
		classifier.buildClassifier(train);
		return classifier;
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
						.map(Property::getName)
						.collect(Collectors.toList());
								
				// Add current Entity Version to entities Map
				String key = String.format("%1$s:%2$d", entity.getName(), entityVersion.getVersionId());
				classes.put(key, properties);
			}
		}

		return classes;
	}
	
	public static Map<String, int[]> one_hot(Map<String, List<String>> classes, List<String> featuresList){
		Map<String, int[]> result = new HashMap<String, int[]>();
		Map<String, Integer> features = new HashMap<String, Integer>();
		
		for (int i = 0; i < featuresList.size(); i++){
			features.put(featuresList.get(i), i);
		}
		
		int vector_size = features.size(); // Features + Tag
		
		String[] list_classes = classes.keySet().toArray(new String[classes.size()]);
		for (int i=0; i < list_classes.length; i++){
			int[] base = new int[vector_size];
			String tag = list_classes[i];
			List<String> current_features = classes.get(tag);
			for (String feature: current_features){
				int index = features.get(feature);
				base[index] = 1;
			}

			result.put(tag, base);
		}
		
		return result;
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
		for (List<String> list : classes.values()){
			featuresNames.addAll(list);
		}
		List<String> featuresList = Arrays.asList(featuresNames.toArray(new String[featuresNames.size()]));
		
		// Count properties
		int maxNumFeatures = featuresList.size();
		
		// Encode classes into binary vectors
		Map<String, int[]> values = one_hot(classes, featuresList);	
		
		// Define Weka Instances Model
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		for (int i = 0; i < maxNumFeatures; i++){
			Attribute attribute = new Attribute("f"+i);
			atts.add(attribute);
		}

		Attribute tag = new Attribute("tag", Arrays.asList(classes.keySet().toArray(new String[num_classes])));		
		atts.add(tag);
		
		// Build a Dataset from Weka Attributes 
		Instances dataset = new Instances("Train", atts, num_classes);

		// For each classes, create a Weka Instance and add it to the dataset
		for (String name: values.keySet()){
			// TODO: Update to Class Weight
			double weight = 1.0; 
			int[] vector = values.get(name);
			Instance ints = new DenseInstance(vector.length + 1);
			for (int i = 0; i < vector.length; i++){
				ints.setValue(atts.get(i), vector[i]);
			}
			
			ints.setValue(tag, name);
			dataset.add(ints);			
		}

		dataset.setClass(tag);
		System.out.println(dataset.	toSummaryString());
		
		
		try {
			Classifier tree = generateTree(dataset);
			System.out.println(tree);
			System.out.println(dataset.numClasses());
			for (int i = 0; i < dataset.numInstances(); i++){
				System.out.println(dataset.get(i));
				System.out.println(tree.classifyInstance(dataset.get(i)));
			}
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
