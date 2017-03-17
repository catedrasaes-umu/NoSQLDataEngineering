package es.um.nosql.schemainference.decisiontree.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.tuple.Pair;

import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec;
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage;
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec;
import es.um.nosql.schemainference.util.emf.ModelLoader;
import es.um.nosql.schemainference.util.emf.Serializer;
import weka.classifiers.trees.j48.ClassifierSplitModel;
import weka.classifiers.trees.j48.ClassifierTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Main2 {

	private OpenJ48 generateTree(Instances train) throws Exception
    {
		OpenJ48 classifier = new OpenJ48();
		classifier.setUnpruned(true);
		classifier.setMinNumObj(1);
		classifier.buildClassifier(train);
		return classifier;
	}

	private Map<String, List<String>> getClasses(NoSQLSchema schema)
	{
		Map<String, List<String>> classes = new HashMap<String, List<String>>();
		for (Entity entity: schema.getEntities())
		{
			for (EntityVersion entityVersion: entity.getEntityversions())
			{
				// FIXME
				if (!entityVersion.isRoot())
					continue;

				// Get List of properties Names
				List<String> properties = entityVersion.getProperties().stream()
						.map(Serializer::serialize)
						.collect(toList());

				// Add current Entity Version to entities Map
				String key = String.format("%1$s:%2$d", entity.getName(), entityVersion.getVersionId());
				classes.put(key, properties);
			}
		}

		return classes;
	}

    private ArrayList<Attribute> getWekaAttributes(EntityDiffSpec eds, Map<String, PropertySpec> features, List<String> classes)
    {
		// Define Nominal values for features fields
		final List<String> f_values = Arrays.asList(new String[]{"1","0"});

		// Define Weka Instances Model
		ArrayList<Attribute> atts =
			features.keySet().stream().map(s -> new Attribute(s, f_values))
			.collect(toCollection(ArrayList::new));


		atts.add(new Attribute("tag", classes));
		return atts;
	}

	private Instances getDataset(ArrayList<Attribute> attributes, List<String> classes, Map<String, int[]> binary_vectors)
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


	private ModelTree getModelTree(ClassifierTree tree, Map<String, EntityVersion> entityVersions, Map<String, List<Property>> properties) throws Exception
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
				return new ModelTree((Entity)ev.eContainer(),ev);
			}

			else throw new Exception("Invalid exp reg for: "+tag);
		}
		else
		{
			ClassifierSplitModel classifierSplitModel = tree.getLocalModel();
			ClassifierTree[] sons = tree.getSons();
			String left = tree.getLocalModel().leftSide(tree.getTrainingData());
			List<Property> p = properties.get(left.trim());

			if (p==null) throw new Exception("Unknown Property Name: " + left.trim());

			if (sons.length != 2) throw new Exception("This is not a binary decision tree");

			ModelTree m = new ModelTree(p.get(0));
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

	private void runModelTree(ModelTree tree){
		runModelTree(tree, 0);
	}

	private void runModelTree(ModelTree tree, int level)
	{
		String indent = String.join("", Collections.nCopies(level, "  "));

		if (tree.is_leaf())
		{
			Entity e = tree.getEntity();
			System.out.println(indent+"Entity: "+e.getName()+", Version: "+tree.getTag().getVersionId());
		}
		else
		{
			System.out.println(indent+tree.getProperty().getName()+" is present");
			runModelTree(tree.getNodePresent(), level+1);
			System.out.println(indent+tree.getProperty().getName()+" isn't present");
			runModelTree(tree.getNodeAbsent(), level+1);
		}
	}

	public static void main(String[] args)
    {
		(new Main2()).run(args);
	}

	private void run(String[] args)
	{
		ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
		loader.registerPackages(EntitydifferentiationPackage.eINSTANCE);
		EntityDifferentiation diff = loader.load(new File("model/mongoMovies3_Diff.xmi"),
				EntityDifferentiation.class);

		for (EntityDiffSpec eds : diff.getEntityDiffSpecs())
			generateTreeForEntity(eds);
	}

	private String serialize(PropertySpec ps)
	{
		if (ps.isNeedsTypeCheck())
			return Serializer.serialize(ps.getProperty());
		else
			return ps.getProperty().getName();
	}

	private String serializeNot(PropertySpec ps)
	{
		return "!" + ps.getProperty().getName();
	}

	private void generateTreeForEntity(EntityDiffSpec eds)
	{
		Map<EntityVersionProp, List<Pair<String, PropertySpec>>> propsByEv =
			eds.getEntityVersionProps().stream()
			.collect(toMap(Function.identity(),
					evp ->
						Stream.concat(
								evp.getPropertySpecs().stream().map(ps -> Pair.of(serialize(ps), ps)),
								evp.getNotProps().stream().map(ps -> Pair.of(serializeNot(ps), ps)))
							.collect(toList())));

		Map<String, PropertySpec> features =
			propsByEv.values().stream().flatMap(l -> l.stream())
				.collect(groupingBy(Pair::getKey,
									mapping(Pair::getValue,
											reducing(null, (l,r) -> r))));

		// Generate inverted index for feature serialization to feature vector position
		final Iterator<Map.Entry<String,PropertySpec>> it = features.entrySet().iterator();
		Map<String,Integer> arrayPos =
			IntStream.range(0,features.entrySet().size())
				.boxed()
				.collect(toMap(e -> it.next().getKey(),
							Function.identity()));

		Map<EntityVersionProp, int[]> featuresByEv =
			propsByEv.entrySet().stream()
			.collect(toMap(Map.Entry::getKey,
					e -> {
						int[] values = new int[features.size()];
						e.getValue().forEach(p -> values[arrayPos.get(p.getKey())] = 1);
						return values;
					}));

		final String entityName = eds.getEntity().getName();
		List<String> classes = eds.getEntityVersionProps().stream()
				.map(evp -> String.format("%1$s_%2$d", entityName, evp.getEntityVersion().getVersionId()))
				.collect(toList());

		// Count classes
		int num_classes = eds.getEntityVersionProps().size();

		// Build Attribute models for weka
		ArrayList<Attribute> atts = getWekaAttributes(eds, features, classes);
//		Attribute tag = atts.get(atts.size() - 1);
//
//		// Generate Dataset
//		Instances dataset = getDataset(atts, classesList, binary_vectors);
//
//
//		try {
//			// Get Classification Tree
//			OpenJ48 tree = generateTree(dataset);
//			ClassifierTree root = tree.get_m_root();
//
//			System.out.println(tree);
//			for (int i = 0; i < dataset.numInstances(); i++){
//				System.out.println(dataset.get(i).stringValue(tag)+": "
//						+ tree.classifyInstance(dataset.get(i)));
//			}
//
//			ModelTree modelTree =
//					getModelTree(root, getEntityVersions(schema), getProperties(schema));
//			runModelTree(modelTree);
//		}
//
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
