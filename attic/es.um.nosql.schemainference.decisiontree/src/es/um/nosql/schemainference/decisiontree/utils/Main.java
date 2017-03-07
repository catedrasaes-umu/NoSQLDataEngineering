package es.um.nosql.schemainference.decisiontree.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.util.emf.ModelLoader;
import es.um.nosql.schemainference.util.emf.NoSQLSchemaSerializer;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class Main {

	public static Classifier generateTree(Instances train) throws Exception
    {
		J48 classifier = new J48();
		classifier.setUnpruned(true);
		classifier.setMinNumObj(1);
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
				NoSQLSchemaSerializer noSQLSchemaSerializer = NoSQLSchemaSerializer.getInstance();
				// Get List of properties Names
				List<String> properties = entityVersion.getProperties().stream()
						.map(x -> noSQLSchemaSerializer.serialize(x))
						.collect(Collectors.toList());
								
				// Add current Entity Version to entities Map
				String key = String.format("%1$s:%2$d", entity.getName(), entityVersion.getVersionId());
				classes.put(key, properties);
			}
		}

		return classes;
	}
	
	public static Map<String, int[]> one_hot(Map<String, List<String>> classes, List<String> featuresList)
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

    public static ArrayList<Attribute> get_weka_attributes(List<String> classes, List<String> features)
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
		Map<String, int[]> binary_vectors = one_hot(classes, featuresList);
		
		// Build Attribute models for weka
		ArrayList<Attribute> atts = get_weka_attributes(classesList, featuresList);
		Attribute tag = atts.get(atts.size() - 1);
		
		// Generate Dataset
		Instances dataset = getDataset(atts, classesList, binary_vectors);
				
		
		try {
			// Get Classification Tree 
			Classifier tree = generateTree(dataset);
			System.out.println(tree);
			for (int i = 0; i < dataset.numInstances(); i++){
				System.out.println(dataset.get(i).stringValue(tag)+": "+ tree.classifyInstance(dataset.get(i)));
			}
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
