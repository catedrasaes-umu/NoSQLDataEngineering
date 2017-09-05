/**
 *
 */
package es.um.nosql.schemainference.m2m;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec;
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation;
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationFactory;
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage;
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec;
import es.um.nosql.schemainference.m2m.util.PropertyHashingStrategy;
import es.um.nosql.schemainference.m2m.util.PropertyJustNameHashingStrategy;
import es.um.nosql.schemainference.util.emf.ResourceManager;

/**
 * @author dsevilla
 *
 */
public class NoSQLSchemaToEntityDiff
{
	//private static final String MODEL_ROUTE = "tests/mongoMovies3.xmi";
	//private static final String SPECIAL_TYPE_IDENTIFIER = "type";

	public static void main(String[] args)
	{		
		(new NoSQLSchemaToEntityDiff()).run(args);
	}

	private Map<Entity, Set<Property>> commonEntityProperties;
	private Map<EntityVersion, Set<Property>> evOwnProperties;
	private Map<EntityVersion, EntityVersionProp> evPropsByEv;
	private PropertyHashingStrategy propertyHashing = new PropertyHashingStrategy();

	private void run(String[] args)
  {
	  File INPUT_MODEL = new File("MyTests/mongoMovies3.xmi");
	  File OUTPUT_MODEL = new File("MyTests/mongoMovies3_Diff.xmi");

    NoSQLSchemaPackage nosqlschemaPackage = NoSQLSchemaPackage.eINSTANCE;
    EntitydifferentiationPackage entitydiffPackage = EntitydifferentiationPackage.eINSTANCE;
    ResourceManager resManager = new ResourceManager(nosqlschemaPackage, entitydiffPackage);

//    String relativePath = Paths.get(OUTPUT_MODEL.getAbsolutePath()).relativize(Paths.get(INPUT_MODEL.getAbsolutePath())).toString().substring(3);

    // Load the origin model.
    resManager.loadResourcesAsStrings(INPUT_MODEL.getAbsolutePath());
    NoSQLSchema schema = (NoSQLSchema)resManager.getResources().iterator().next().getContents().get(0);

    EntityDifferentiation differentiation = doTransform(schema);

    Resource outputRes = resManager.getResourceSet().createResource(URI.createFileURI(OUTPUT_MODEL.getAbsolutePath()));
    outputRes.getContents().add(differentiation);

    // Configure output
    nosqlschemaPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.schemainference/model/nosqlschema.ecore", true));
    entitydiffPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.schemainference.entitydifferentiation/model/entitydifferentiation.ecore", true));
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
  }

/*
	private void run(String[] args)
	{
		NoSQLSchemaPackage dbsp = NoSQLSchemaPackage.eINSTANCE;
		EntitydifferentiationPackage tdp = EntitydifferentiationPackage.eINSTANCE;
		ResourceManager rm = new ResourceManager(dbsp, tdp);

		// Load the origin model.
		File sourceRes = new File(args[0]);
		rm.loadResourcesAsStrings("file://" + sourceRes.getAbsolutePath());
		//rm.loadResourcesAsStrings(args[0]);

		Iterable<Resource> resources = rm.getResources();
		EntityDifferentiation diff = EntitydifferentiationFactory.eINSTANCE.createEntityDifferentiation();

		doTransform((NoSQLSchema)resources.iterator().next().getContents().get(0), diff);

		File outResource = new File(args[1]);
		Resource outputRes = rm.getResourceSet().createResource(URI.createFileURI("file://" + outResource.getAbsolutePath()));
		//Resource outputRes = rm.getResourceSet().createResource(URI.createFileURI(args[1]));
		outputRes.getContents().add(diff);

		// Configure output
		tdp.eResource().setURI(
				URI.createPlatformResourceURI("es.um.nosql.schemainference.entitydifferentiation/model/entitydifferentiation.ecore", true));
		dbsp.eResource().setURI(
				URI.createPlatformResourceURI("es.um.nosql.schemainference/model/nosqlschema.ecore", true));
		Map<Object,Object> options = new HashMap<Object,Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMIResource.OPTION_ENCODING, "UTF-8");

		try {
			OutputStream os = new FileOutputStream(outResource);
			outputRes.save(os, options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/

	/*
	 * The following code takes some assumptions.
	 *
	 * - All the objects are represented, so just storing their differences
	 *   at the level of attributes contained or not contained is enough
	 *   to discern them
	 * - In cases of same name attributes with different types between entity versions,
	 *   more complete type enabled tests are output
	 *
	 * So what we do is:
	 *
	 * 1. For each Entity, check all the common properties of all the EntityVersions
	 *    (name and type). This will generate simple checks against property names.
	 * 2. For each EntityVersion, record the specific properties of that entity. There
	 *    will be two types: Those with properties that don't appear in any other EVs
	 *    (we have to check just the existence of the property with its name), and
	 *    those with properties that exist in any other of the EVs. For those, we will
	 *    have to check for the type.
	 */
	private EntityDifferentiation doTransform(NoSQLSchema schema)
	{
    EntityDifferentiation differentiation = EntitydifferentiationFactory.eINSTANCE.createEntityDifferentiation();

    differentiation.setName(schema.getName());
    differentiation.setSchema(schema);
		
		initCalculations(schema);

		// Generate entity References
		for (Entity e: schema.getEntities())
			transformEntity(e, differentiation);

		return differentiation;
	}

	/**
	 * Initialize calculations for properties, etc.
	 */
	private void initCalculations(NoSQLSchema schema)
	{
		commonEntityProperties = schema.getEntities().stream()
				.collect(toMap(identity(), this::calcCommonProperties));

		evOwnProperties = schema.getEntities().stream()
				.flatMap(e -> e.getEntityversions().stream().map(ev -> Tuples.pair(ev,
						ev.getProperties().stream().filter(p -> !commonEntityProperties.get(e).contains(p))
							.collect(toCollection(() -> new UnifiedSetWithHashingStrategy<>(propertyHashing))))))
				.collect(toMap(Pair::getOne, Pair::getTwo));

		evPropsByEv = new HashMap<>();
	}

	private void transformEntity(Entity e, EntityDifferentiation diff)
	{
		// Holds the set of properties to check for each EntityVersion. It will be used to
		// not to create duplicate checks
		Map<EntityVersion, Map<String, PropertySpec>> propSpecsByEV = new HashMap<>();

		EntityDiffSpec de = EntitydifferentiationFactory.eINSTANCE.createEntityDiffSpec();
		de.setEntity(e);

		Set<Property> commonProperties = commonEntityProperties.get(e);

		// Add Common Props
		de.getCommonProps().addAll(commonProperties.stream()
				.map(this::genPropertySpecNamed).collect(toList()));

		// Create and add all the EntityVersionProp. This is because it is easier, when
		// identified the own attributes of an EntityVersion, output "HasNotField" items
		// for the rest of EntityVersions
		e.getEntityversions().forEach(ev -> {
			EntityVersionProp evp = EntitydifferentiationFactory.eINSTANCE.createEntityVersionProp();
			evp.setEntityVersion(ev);
			de.getEntityVersionProps().add(evp);

			// Set of properties by entity, for fast lookup
			propSpecsByEV.put(ev, new HashMap<>());

			// Add to the list of DiffSpecs for the decision tree afterwards
			evPropsByEv.put(ev, evp);
		});

		if (e.getEntityversions().size() > 1)
		{
			for (EntityVersion ev : e.getEntityversions())
			{
				// For each property, check whether other entity versions having this property have also the same type for it
				for (Property p : evOwnProperties.get(ev))
				{
					// If this property appears in any other entity version with a different type, needs to type check
					boolean hasToTypeCheck =
							!evOwnProperties.entrySet().stream().filter(en -> en.getKey() != ev)
							.flatMap(en -> en.getValue().stream().filter(p1 -> PropertyJustNameHashingStrategy.checkEquality(p, p1)))
							.allMatch(p2 -> PropertyHashingStrategy.checkEquality(p, p2));

					PropertySpec ps = hasToTypeCheck ? genPropertySpecTyped(p): genPropertySpecNamed(p);
					propSpecsByEV.get(ev).put(p.getName(), ps);
				}
			}

			for (EntityVersionProp evp: de.getEntityVersionProps())
			{
				propSpecsByEV.get(evp.getEntityVersion()).values().forEach(pe -> {
					evp.getPropertySpecs().add(pe);
				});
			}
			
			// Calc notProps
			
			// For each entity version, for each property appearing in the rest 
			// of entityVersions but not in this one, add a "notProp"
			for (EntityVersionProp evp: de.getEntityVersionProps())
			{
				Set<String> ownPropNames =
						evp.getPropertySpecs().stream().map(pe -> pe.getProperty().getName())
						.collect(Collectors.toSet());
				
				Map<String, PropertySpec> otherPropsByName = 
					de.getEntityVersionProps().stream()
					.filter(_evp -> _evp != evp)
					.flatMap(_evp -> _evp.getPropertySpecs().stream())
					.filter(_ps -> !ownPropNames.contains(_ps.getProperty().getName()))
					.collect(Collectors.groupingBy(
								_ps -> _ps.getProperty().getName(),
								Collectors.reducing(null,(l,r) -> r)
							));
				
				otherPropsByName.entrySet().stream().forEach(_e ->
					evp.getNotProps().add(genPropertySpecNamed(_e.getValue().getProperty()))
				);
			}
		}

		// Add it to the model
		diff.getEntityDiffSpecs().add(de);
	}

	/**
	 * @param e
	 * @return
	 */
	private Set<Property> calcCommonProperties(Entity e)
	{
		Map<EntityVersion, Set<Property>> allProperties =
				e.getEntityversions().stream()
				.collect(toMap(
						identity(),
						ev -> new UnifiedSetWithHashingStrategy<>(propertyHashing, ev.getProperties())));

		Set<Property> initCommonProperties = allProperties.values().stream().findFirst().get();
		final Set<Property> commonProperties = Collections.unmodifiableSet(
				allProperties.values().stream().skip(1)
				.reduce(initCommonProperties,
						(acc_set, sp) -> {acc_set.retainAll(sp); return acc_set;}));

		return commonProperties;
	}

	private PropertySpec genPropertySpecNamed(Property p) {
		PropertySpec ps = EntitydifferentiationFactory.eINSTANCE.createPropertySpec();
		ps.setProperty(p);
		return ps;
	}

	private PropertySpec genPropertySpecTyped(Property p) {
		PropertySpec ps = EntitydifferentiationFactory.eINSTANCE.createPropertySpec();
		ps.setNeedsTypeCheck(true);
		ps.setProperty(p);
		return ps;
	}
}
