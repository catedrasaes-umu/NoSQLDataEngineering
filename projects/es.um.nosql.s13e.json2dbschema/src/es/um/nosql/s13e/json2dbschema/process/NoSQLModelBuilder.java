package es.um.nosql.s13e.json2dbschema.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.NoSQLSchema.Type;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ArraySC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.BooleanSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NullSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NumberSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.SchemaComponent;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.StringSC;
import es.um.nosql.s13e.json2dbschema.process.util.ReferenceMatcher;
import es.um.nosql.s13e.json2dbschema.util.inflector.Inflector;

/**
 * @author dsevilla
 *
 */
public class NoSQLModelBuilder
{
	private NoSQLSchemaFactory factory;

	// Reverse indexes for finding EntityVariations
	private Map<SchemaComponent, EntityVariation> mEntityVariations;

	// List of Entities
	private List<Entity> mEntities;

	// Reference matcher
	ReferenceMatcher<Entity> rm;

	private String name;

	public NoSQLModelBuilder(NoSQLSchemaFactory factory2, String name2)
	{
		factory = factory2;
		name = name2;

		mEntities = new ArrayList<Entity>(20);
		mEntityVariations = new HashMap<SchemaComponent, EntityVariation>();
	}

	public NoSQLSchema build(Map<String, List<SchemaComponent>> rawEntities)
	{
		// TODO: Identify objects that are references in the form of MongoDB
		// references: https://docs.mongodb.org/manual/reference/database-references/#dbrefs
		// { "$ref" : <type>, "$id" : <value>, "$db" : <value> }


		// Build reverse indices & Create Entities & Populate with EntityVariations
		rawEntities.forEach((entityName, schemas) -> {
			// Entity creation
			Entity e = factory.createEntity();
			e.setName(entityName);
			mEntities.add(e);

			OfInt n = IntStream.iterate(1, i -> i+1).iterator();

			schemas.forEach(schema -> {
			  EntityVariation theEV = factory.createEntityVariation();
				theEV.setVariationId(n.next());

				// If any variation is not root, then the entity is not root
				ObjectSC obj = (ObjectSC)schema;
				if (!obj.isRoot)
				  e.setRoot(false);

				theEV.setCount(obj.count);
				theEV.setTimestamp(obj.timestamp);

				e.getEntityVariations().add(theEV);
				mEntityVariations.put(schema, theEV);
			});
		});

		// Consider as reference matcher only those Entities of which
		// at least one variation is root
		rm = createReferenceMatcher();

		// Populate empty EntityVariations
		mEntityVariations.forEach((schema, ev) -> fillEV(schema, ev));

		/** TODO: We are removing the opposite calculations for the moment since there is no easy way
		 * to infer these. On the near future we might try to complete this property but for the time being
		 * it is safer for the user to programatically find the opposites on demand.
		 */
		// Opposite references
		/*
		mEntities.forEach(eFrom -> {
			eFrom.getEntityVariations().forEach(ev -> {
				ev.getProperties().stream().filter(p -> p instanceof Reference).forEach(r -> {
					Reference ref = (Reference)r;
					Entity eTo = ref.getRefTo();

					// Find a EntityVariation of eTo that has a reference to the
					// current Entity eFrom
					Optional<Property> refTo =
							eTo.getEntityVariations().stream().flatMap(evTo ->
							evTo.getProperties().stream().filter(pTo -> pTo instanceof Reference))
							.filter(rTo -> ((Reference)rTo).getRefTo() == eFrom).findFirst();

					refTo.ifPresent(r_ -> ref.setOpposite((Reference)r_));
				});
			});
		});
		*/

		NoSQLSchema finalSchema = factory.createNoSQLSchema();
		finalSchema.setName(name);
		finalSchema.getEntities().addAll(mEntities);

		return finalSchema;
	}

	private ReferenceMatcher<Entity> createReferenceMatcher() 
	{
		return new ReferenceMatcher<>(mEntities.stream()
				.filter(e -> e.isRoot())
				.map(e -> 
					Pair.of(new HashSet<String>(Arrays.asList(
								e.getName(),
								Inflector.getInstance().pluralize(e.getName()),
								Inflector.getInstance().singularize(e.getName())))
							,e))
				.flatMap(p -> p.getKey().stream().map(s -> Pair.of(s,p.getValue()))));
	}

	private void fillEV(SchemaComponent schema, EntityVariation ev)
	{
		assert(schema instanceof ObjectSC);

		ObjectSC obj = (ObjectSC)schema;

		// Set properties
		ev.getProperties().addAll(obj.getInners().stream()
				.map(p -> propertyFromSchemaComponent(p.getLeft(), p.getRight())).collect(Collectors.toList()));
	}

	private Property propertyFromSchemaComponent(String en, SchemaComponent sc)
	{
		if (sc instanceof ObjectSC)
			return propertyFromSchemaComponent(en, (ObjectSC)sc);

		if (sc instanceof ArraySC)
			return propertyFromSchemaComponent(en, (ArraySC)sc);

		if (sc instanceof BooleanSC)
			return propertyFromSchemaComponent(en, (BooleanSC)sc);

		if (sc instanceof NumberSC)
			return propertyFromSchemaComponent(en, (NumberSC)sc);

		if (sc instanceof NullSC)
			return propertyFromSchemaComponent(en, (NullSC)sc);

		if (sc instanceof StringSC)
			return propertyFromSchemaComponent(en, (StringSC)sc);

		return null;
	}

	private Property propertyFromSchemaComponent(String en, ObjectSC sc)
	{
		// TODO: Check for complex DBRef references

		// Note that at this point, there is no need to recursively explore inner objects
		// as they have been all put at the root level in the previous phase.
		Aggregate a = factory.createAggregate();
		a.setName(en);
		a.setLowerBound(1);
		a.setUpperBound(1);
		a.getRefTo().add(mEntityVariations.get(sc));
		return a;
	}

	private Property propertyFromSchemaComponent(String en, ArraySC sc)
	{
		if (sc.isHomogeneous())
		{
			// If it is empty or it is NOT an Object (it is a simple type),
			// then it may be a reference
			SchemaComponent inner = sc.getInners().get(0);
			if (sc.size() == 0 || !(inner instanceof ObjectSC))
			{
				return maybeReference(Inflector.getInstance().singularize(en), sc).map(p -> {
					Reference ref = (Reference)p;
					ref.setName(en);
					// All arrays that come from a real array are signaled by a 0 lower bound
					ref.setLowerBound(0);
					ref.setUpperBound(-1);
					ref.setOriginalType(schemaComponentToPrimitiveType(inner));
					return p;
				}).orElseGet(() -> {
					// Or else  build a tuple with the correct types
					Attribute a = factory.createAttribute();
					a.setName(en);
					a.setType(tupleForArray(sc));
					return a;
				});
			}
			else
			{
				// size is not 0 and the homogeneous type is an object
				Aggregate a = factory.createAggregate();
				a.setName(en);
				a.setLowerBound(sc.getLowerBounds() == 1 ? 0 : sc.getLowerBounds());
				a.setUpperBound(sc.getUpperBounds() > 1 ? -1 : sc.getUpperBounds());
				a.getRefTo().add(mEntityVariations.get(inner));
				return a;
			}
		}

		// Non-homogeneous array. If all elements are objects, then
		// create an aggregate. If not, create a tuple
		EntityVariation ev = mEntityVariations.get(sc.getInners().get(0));
		if (ev != null)
		{
			Aggregate a = factory.createAggregate();
			a.setName(en);
			a.setLowerBound(0);
			a.setUpperBound(sc.getUpperBounds() > 1 ? -1 : sc.getUpperBounds());

			// FIXME: OJO, error en Ecore/EMF desde el 2005 sin arreglar, y aquí tiene problema:
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325
			a.getRefTo().addAll(sc.getInners().stream()
					.map(mEntityVariations::get)
					.collect(Collectors.toList()));

			return a;
		}
		else
		{
			// Or else  build a tuple with the correct types
			Attribute a = factory.createAttribute();
			a.setName(en);
			a.setType(tupleForArray(sc));
			return a;
		}
	}

	private Type tupleForArray(ArraySC sc) {
		Tuple t = factory.createTuple();

		if (sc.size() == 0)
			return t;

		Stream<SchemaComponent> ssc;

		if (sc.isHomogeneous())
			ssc = Collections.nCopies(sc.size(), sc.getInners().get(0)).stream();
		else
			ssc = sc.getInners().stream();

		t.getElements().addAll(
				ssc.map(_sc -> {

					// Recursive
					if (_sc instanceof ArraySC)
						return tupleForArray((ArraySC)_sc);

					// TODO: Consider Objects?
					String primType = schemaComponentToPrimitiveType(_sc);
					
					PrimitiveType pt = factory.createPrimitiveType();
					pt.setName(primType);

					return pt;
				}).collect(Collectors.toList()));

		return t;
	}

	private String schemaComponentToPrimitiveType(SchemaComponent sc)
	{
		if (sc instanceof BooleanSC)
			return "Boolean";

		if (sc instanceof NumberSC)
			return "Number";

		if (sc instanceof StringSC)
			return "String";	

		return "";
	}
	
	private Property propertyFromSchemaComponent(String en, NullSC sc)
	{
		return propertyFromPrimitive(en, sc, "Null");
	}

	private Property propertyFromSchemaComponent(String en, NumberSC sc)
	{
		return propertyFromPrimitive(en, sc, "Number");
	}

	private Property propertyFromSchemaComponent(String en, StringSC sc)
	{
		return propertyFromPrimitive(en, sc, "String");
	}

	private Optional<Entity> idReferencesEntity(String id)
	{
		return rm.maybeMatch(id);
	}

	private Optional<Property> maybeReference(String en, SchemaComponent sc)
	{
		return idReferencesEntity(en).map(e -> {
			Reference r = factory.createReference();
			r.setName(en);
			r.setRefTo(e);
			r.setLowerBound(1);
			r.setUpperBound(1);
			r.setOriginalType(schemaComponentToPrimitiveType(sc));
			return r;
		});
	}

	private Property propertyFromSchemaComponent(String en, BooleanSC sc)
	{
		Attribute p = factory.createAttribute();
		p.setName(en);
		PrimitiveType pt = factory.createPrimitiveType();
		pt.setName("Boolean");
		p.setType(pt);
		return p;
	}

	private Property propertyFromPrimitive(String en, SchemaComponent sc, String primitiveName)
	{
		return maybeReference(en, sc).orElseGet(() -> {
			Attribute a = factory.createAttribute();
			a.setName(en);
			PrimitiveType pt = factory.createPrimitiveType();
			pt.setName(primitiveName);
			a.setType(pt);
			return a;
		});
	}
}
