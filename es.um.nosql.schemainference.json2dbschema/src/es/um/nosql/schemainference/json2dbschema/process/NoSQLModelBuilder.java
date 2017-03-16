/**
 *
 */
package es.um.nosql.schemainference.json2dbschema.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import es.um.nosql.schemainference.NoSQLSchema.Aggregate;
import es.um.nosql.schemainference.NoSQLSchema.Attribute;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.NoSQLSchema.Reference;
import es.um.nosql.schemainference.NoSQLSchema.Tuple;
import es.um.nosql.schemainference.NoSQLSchema.Type;
import es.um.nosql.schemainference.json2dbschema.intermediate.raw.ArraySC;
import es.um.nosql.schemainference.json2dbschema.intermediate.raw.BooleanSC;
import es.um.nosql.schemainference.json2dbschema.intermediate.raw.NullSC;
import es.um.nosql.schemainference.json2dbschema.intermediate.raw.NumberSC;
import es.um.nosql.schemainference.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.schemainference.json2dbschema.intermediate.raw.SchemaComponent;
import es.um.nosql.schemainference.json2dbschema.intermediate.raw.StringSC;
import es.um.nosql.schemainference.json2dbschema.process.util.ReferenceMatcher;
import es.um.nosql.schemainference.json2dbschema.util.inflector.Inflector;

/**
 * @author dsevilla
 *
 */
public class NoSQLModelBuilder
{
	private NoSQLSchemaFactory factory;

	// Reverse indexes for finding EntityVersions
	private Map<SchemaComponent, EntityVersion> mEntityVersions;

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
		mEntityVersions = new HashMap<SchemaComponent, EntityVersion>();
	}

	public NoSQLSchema build(Map<String, List<SchemaComponent>> rawEntities)
	{
		// TODO: Identify objects that are references in the form of MongoDB
		// references: https://docs.mongodb.org/manual/reference/database-references/#dbrefs
		// { "$ref" : <type>, "$id" : <value>, "$db" : <value> }


		// Build reverse indices & Create Entities & Populate with EntityVersions
		rawEntities.forEach((entityName, schemas) -> {
			// Entity creation
			Entity e = factory.createEntity();
			e.setName(entityName);
			mEntities.add(e);

			OfInt n = IntStream.iterate(1, i -> i+1).iterator();

			schemas.forEach(schema -> {
				EntityVersion theEV = factory.createEntityVersion();
				theEV.setVersionId(n.next());

				// Set the root flag. It is needed to know which
				// entities can be destination of a reference
				ObjectSC obj = (ObjectSC)schema;
				theEV.setRoot(obj.isRoot);

				e.getEntityversions().add(theEV);
				mEntityVersions.put(schema, theEV);
			});
		});

		// Consider as reference matcher only those Entities of which
		// at least one version is root
		rm = createReferenceMatcher();

		// Populate empty EntityVersions
		mEntityVersions.forEach((schema, ev) -> fillEV(schema, ev));

		// Opposite references
		mEntities.forEach(eFrom -> {
			eFrom.getEntityversions().forEach(ev -> {
				ev.getProperties().stream().filter(p -> p instanceof Reference).forEach(r -> {
					Reference ref = (Reference)r;
					Entity eTo = ref.getRefTo();

					// Find a EntityVersion of eTo that has a reference to the
					// current Entity eFrom
					Optional<Property> refTo =
							eTo.getEntityversions().stream().flatMap(evTo ->
							evTo.getProperties().stream().filter(pTo -> pTo instanceof Reference))
							.filter(rTo -> ((Reference)rTo).getRefTo() == eFrom).findFirst();

					refTo.ifPresent(r_ -> ref.setOpposite((Reference)r_));
				});
			});
		});

		NoSQLSchema finalSchema = factory.createNoSQLSchema();
		finalSchema.setName(name);
		finalSchema.getEntities().addAll(mEntities);

		return finalSchema;
	}

	private ReferenceMatcher<Entity> createReferenceMatcher() 
	{
		return 
			new ReferenceMatcher<Entity>(mEntities.stream()
				.filter(e -> e.getEntityversions().stream().anyMatch(EntityVersion::isRoot))
				.map(e -> 
					Pair.of(Arrays.stream(new String[]{
								e.getName(),
								Inflector.getInstance().pluralize(e.getName()),
								Inflector.getInstance().singularize(e.getName())})
								.collect(Collectors.toSet())
							,e))
				.flatMap(p -> p.getKey().stream().map(s -> Pair.of(s,p.getValue()))));
	}

	private void fillEV(SchemaComponent schema, EntityVersion ev)
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
		a.getRefTo().add(mEntityVersions.get(sc));
		return a;
	}

	private Property propertyFromSchemaComponent(String en, ArraySC sc)
	{
		if (sc.isHomogeneous())
		{
			// If it is empty or it is NOT an Object (it is a simple type),
			// then it may be a reference
			if (sc.size() == 0 || !(sc.getInners().get(0) instanceof ObjectSC))
			{
				return maybeReference(Inflector.getInstance().singularize(en), sc).map(p -> {
					Reference ref = (Reference)p;
					ref.setName(en);
					// All arrays that come from a real array are signaled by a 0 lower bound
					//ref.setLowerBound(sc.getLowerBounds() == 1 ? 0 : sc.getLowerBounds());
					//ref.setUpperBound(sc.getUpperBounds() > 1 ? -1 : sc.getUpperBounds());
					ref.setLowerBound(0);
					ref.setUpperBound(-1);
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
				a.getRefTo().add(mEntityVersions.get(sc.getInners().get(0)));
				return a;
			}
		}

		// Non-homogeneous array. If all elements are objects, then
		// create an aggregate. If not, create a tuple
		EntityVersion ev = mEntityVersions.get(sc.getInners().get(0));
		if (ev != null)
		{
			Aggregate a = factory.createAggregate();
			a.setName(en);
			a.setLowerBound(0);
			a.setUpperBound(sc.getUpperBounds() > 1 ? -1 : sc.getUpperBounds());

			// FIXME: OJO, error en Ecore/EMF desde el 2005 sin arreglar, y aquí tiene problema:
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325
			a.getRefTo().addAll(sc.getInners().stream()
					.map(mEntityVersions::get)
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

					String primType = "";

					// TODO: Consider Objects?

					if (_sc instanceof BooleanSC)
						primType = "Boolean";

					if (_sc instanceof NumberSC)
						primType = "Number";

					if (_sc instanceof StringSC)
						primType = "String";

					PrimitiveType pt = factory.createPrimitiveType();
					pt.setName(primType);

					return pt;
				}).collect(Collectors.toList()));

		return t;
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
