package es.um.nosql.s13e.json2dbschema.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.PList;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.NoSQLSchema.Type;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ArraySC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.BooleanSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NullSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NumberSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectIdSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.SchemaComponent;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.StringSC;
import es.um.nosql.s13e.json2dbschema.process.util.ReferenceMatcher;
import es.um.nosql.s13e.json2dbschema.util.inflector.Inflector;

public class NoSQLModelBuilder
{
  private NoSQLSchemaFactory factory;

  // Reverse indexes for finding StructuralVariations
  private Map<SchemaComponent, StructuralVariation> mStructuralVariations;

  // List of Entities
  private List<EntityClass> mEntities;

  // Reference matcher
  private ReferenceMatcher<EntityClass> refMatcher;

  private String name;

  public NoSQLModelBuilder(NoSQLSchemaFactory factory2, String name2)
  {
    factory = factory2;
    name = name2;

    mEntities = new ArrayList<EntityClass>(20);
    mStructuralVariations = new HashMap<SchemaComponent, StructuralVariation>();
  }

  public NoSQLSchema build(Map<String, List<SchemaComponent>> rawEntities)
  {
    // TODO: Identify objects that are references in the form of MongoDB
    // references: https://docs.mongodb.org/manual/reference/database-references/#dbrefs
    // { "$ref" : <type>, "$id" : <value>, "$db" : <value> }

    // Build reverse indices & Create Entities & Populate with StructuralVariations
    rawEntities.forEach((EntityClassName, schemas) -> {
      EntityClass EntityClass = factory.createEntityClass();
      EntityClass.setName(EntityClassName);
      EntityClass.setRoot(schemas.stream().anyMatch(schema -> {
        return ((ObjectSC)schema).isRoot;
      }));

      mEntities.add(EntityClass);

      OfInt intIterator = IntStream.iterate(1, i -> i+1).iterator();

      schemas.forEach(schema -> {
        ObjectSC obj = (ObjectSC)schema;

        StructuralVariation theEV = factory.createStructuralVariation();
        theEV.setVariationId(intIterator.next());
        theEV.setCount(obj.count);
        theEV.setTimestamp(obj.timestamp);

        EntityClass.getVariations().add(theEV);
        mStructuralVariations.put(schema, theEV);
      });
    });

    // Consider as reference matcher only those Entities of which at least one variation is root
    refMatcher = createReferenceMatcher();

    // Populate empty StructuralVariations
    mStructuralVariations.forEach((schema, ev) -> fillEV(schema, ev));

    /** TODO: We are removing the opposite calculations for the moment since there is no easy way
     * to infer these. On the near future we might try to complete this property but for the time being
     * it is safer for the user to programatically find the opposites on demand.
     */
/*
    mEntities.forEach(eFrom -> {
      eFrom.getStructuralVariations().forEach(ev -> {
        ev.getProperties().stream().filter(p -> p instanceof Reference).forEach(r -> {
          Reference ref = (Reference)r;
          EntityClass eTo = ref.getRefTo();

          // Find a StructuralVariation of eTo that has a reference to the
          // current EntityClass eFrom
          Optional<Property> refTo =
            eTo.getStructuralVariations().stream().flatMap(evTo ->
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

  private ReferenceMatcher<EntityClass> createReferenceMatcher() 
  {
    return new ReferenceMatcher<>(mEntities.stream()
        .filter(EntityClass::isRoot)
        .map(e -> 
        Pair.of(new HashSet<String>(Arrays.asList(
            e.getName(),
            Inflector.getInstance().pluralize(e.getName()),
            Inflector.getInstance().singularize(e.getName())))
            ,e))
        .flatMap(p -> p.getKey().stream().map(s -> Pair.of(s,p.getValue()))));
  }

  private void fillEV(SchemaComponent schema, StructuralVariation ev)
  {
    assert(schema instanceof ObjectSC);

    // Set properties
    ev.getProperties().addAll(((ObjectSC)schema).getInners().stream()
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

    if (sc instanceof ObjectIdSC)
      return propertyFromSchemaComponent(en, (ObjectIdSC)sc);

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
    a.getAggregates().add(mStructuralVariations.get(sc));
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
          // Or else  build a PList with the correct types
          Attribute a = factory.createAttribute();
          a.setName(en);
          a.setType(PListOrPTupleForArray(sc));
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
        a.getAggregates().add(mStructuralVariations.get(inner));
        return a;
      }
    }

    // Non-homogeneous array. If all elements are objects, then
    // create an aggregate. If not, create a PList
    StructuralVariation ev = mStructuralVariations.get(sc.getInners().get(0));
    if (ev != null)
    {
      Aggregate a = factory.createAggregate();
      a.setName(en);
      a.setLowerBound(0);
      a.setUpperBound(sc.getUpperBounds() > 1 ? -1 : sc.getUpperBounds());

      // FIXME: OJO, error en Ecore/EMF desde el 2005 sin arreglar, y aquí tiene problema:
      // https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325
      a.getAggregates().addAll(sc.getInners().stream()
          .map(mStructuralVariations::get)
          .collect(Collectors.toList()));

      return a;
    }
    else
    {
      // Or else  build a PList with the correct types
      Attribute a = factory.createAttribute();
      a.setName(en);
      a.setType(PListOrPTupleForArray(sc));
      return a;
    }
  }

  private Type PListOrPTupleForArray(ArraySC sc)
  {
	// Return a list by default if empty array
    if (sc.size() == 0)
      return factory.createPList();
    
    if (sc.isHomogeneous())
    {
    	PList t = factory.createPList();
    	t.setElementType(schemaComponentToTypeRecursive(sc.getInners().get(0)));
    	return t;
    }

    PTuple t = factory.createPTuple();

    t.getElements().addAll(
        sc.getInners().stream()
        	.map(this::schemaComponentToTypeRecursive)
        	.collect(Collectors.toList()));

    return t;
  }

  private Type schemaComponentToTypeRecursive(SchemaComponent sc)
  {
      // Recursive
      if (sc instanceof ArraySC)
        return PListOrPTupleForArray((ArraySC)sc);

      // TODO: Consider Objects?
      String primType = schemaComponentToPrimitiveType(sc);

      PrimitiveType pt = factory.createPrimitiveType();
      pt.setName(primType);

      return pt;	  
  }
  
  private String schemaComponentToPrimitiveType(SchemaComponent sc)
  {
    if (sc instanceof BooleanSC)
      return "Boolean";

    if (sc instanceof NumberSC)
      return "Number";

    // TODO: Check the _type parameter
    if (sc instanceof StringSC)
      return "String";

    if (sc instanceof ObjectIdSC)
      return "ObjectId";

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
    return propertyFromPrimitive(en, sc, sc.getValue());
  }

  private Property propertyFromSchemaComponent(String en, ObjectIdSC sc)
  {
    return propertyFromPrimitive(en, sc, "ObjectId");
  }

  private Optional<Property> maybeReference(String en, SchemaComponent sc)
  {
    return refMatcher.maybeMatch(en).map(e -> {
      Reference r = factory.createReference();
      r.setName(en);
      r.setRefsTo(e);
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
