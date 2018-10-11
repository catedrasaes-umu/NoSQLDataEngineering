package es.um.nosql.s13e.json2dbschema.m2m;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.json2dbschema.util.inflector.Inflector;
import es.um.nosql.s13e.util.compare.CompareStructuralVariation;

public class NoSQLSchemaToDocumentDb
{
  private NoSQLSchemaFactory factory;

  private final static String REF_ENTITY_PREFIX = "Ref_";
  private final static String PMAP_ENTITY_PREFIX = "Map_";

  public NoSQLSchemaToDocumentDb()
  {
    factory = NoSQLSchemaFactory.eINSTANCE;
  }

  public void adaptToDocumentDb(NoSQLSchema schema)
  {
    List<ReferenceClass> refClasses = new ArrayList<ReferenceClass>();
    List<Attribute> mapAttributes = new ArrayList<Attribute>();

    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
    {
      if (classifier instanceof ReferenceClass)
        refClasses.add((ReferenceClass)classifier);

      for (StructuralVariation var : classifier.getVariations())
      {
        mapAttributes.addAll(var.getProperties().stream().filter(prop ->
        {
          return (prop instanceof Attribute && ((Attribute)prop).getType() instanceof PMap);
        }).map(prop -> (Attribute)prop).collect(Collectors.toList()));
      }
    }

    refClasses.forEach(refClass -> {refClassToEntityClass(schema, refClass);});
    mapAttributes.forEach(attr -> {removePMap(schema, attr);});
  }

  /**
   * This method translates every ReferenceClass on the current schema to an equivalent EntityClass.
   * The new EntityClass will contain every variation that the previous ReferenceClass used to have,
   * so any Reference pointing to a ReferenceClass variation will now point to an equivalent EntityClass variation.
   * @param schema The schema being processed
   * @param refClass The ReferenceClass being removed
   */
  private void refClassToEntityClass(NoSQLSchema schema, ReferenceClass refClass)
  {
    CompareStructuralVariation comparer = new CompareStructuralVariation();
    List<Reference> lReferences = new ArrayList<Reference>();
    String entityName = REF_ENTITY_PREFIX + Inflector.getInstance().capitalize(refClass.getName());

    // Get in a list each reference featuring a variation of the refClass
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      for (StructuralVariation var : classifier.getVariations())
        lReferences.addAll(var.getProperties().stream()
            .filter(prop -> {return prop instanceof Reference && refClass.getVariations().contains(((Reference)prop).getFeatures());})
            .map(prop -> (Reference)prop)
            .collect(Collectors.toList()));

    for (Reference ref : lReferences)
    {
      // We take the featured StructuralVariation and add a new Reference field.
      StructuralVariation var = ref.getFeatures();
      Reference newRef = factory.createReference();
      newRef.setName(ref.getName());
      newRef.setLowerBound(ref.getLowerBound());
      newRef.setUpperBound(ref.getUpperBound());
      newRef.setOpposite(ref.getOpposite());
      newRef.setRefsTo(ref.getRefsTo());
      var.getProperties().add(newRef); // What to do if it already exists a property named as the ref?

      // Now we replace the original reference with an aggregate.
      // BEWARE: At this point the aggregate aggregates a ReferenceClass variation, but we will change ReferenceClass to EntityClass later on.
      Aggregate newAggr = factory.createAggregate();
      newAggr.setName(ref.getName());
      newAggr.setLowerBound(1);
      newAggr.setUpperBound(1);
      newAggr.getAggregates().add(ref.getFeatures());

      var = (StructuralVariation)ref.eContainer();
      var.getProperties().remove(ref);
      var.getProperties().add(newAggr);
    }

    // Create the new EntityClass from a ReferenceClass
    EntityClass refEntity = null;
    Optional<EntityClass> optEntity = schema.getEntities().stream().filter(entity -> {return entity.getName().equals(entityName);}).findFirst();

    if (optEntity.isPresent())
      refEntity = optEntity.get();
    else
    {
      refEntity = factory.createEntityClass();
      refEntity.setName(entityName);
      refEntity.setRoot(false);
      refEntity.getParents().addAll(refClass.getParents());
      schema.getEntities().add(refEntity);
    }

    // If an EntityClass with the same name as a ReferenceClass existed, we add the variations but take care of the variationId identifier.
    int varSize = refEntity.getVariations().size();
    if (varSize != 0)
    {
      List<StructuralVariation> varsToMove = new ArrayList<StructuralVariation>();
      for (StructuralVariation var : refClass.getVariations())
        if (refEntity.getVariations().stream().noneMatch(innerVar -> {return comparer.compare(innerVar, var);}))
        {
          var.setVariationId(++varSize);
          varsToMove.add(var);
        }
      refEntity.getVariations().addAll(varsToMove);
    }
    else
      refEntity.getVariations().addAll(refClass.getVariations());

    schema.getRefClasses().remove(refClass);
  }

  /**
   * This method translated every PMap property to an equivalent Entity table with a variation with the same structure
   * as the PMap, and an aggregation. This is done because document-based NoSQL databases usually do not support Map structures,
   * but usually they support embedded documents. So for example, a property "pMapProp" of the type PMap<String, Int> will be
   * translated to an Entity called "Map_Pmapprop" with a variation with two properties (key: String, value: Int) and an aggregation
   * to that kind of variation, so the schema is unnafected. 
   * @param schema The schema being processed
   * @param attr The PMap attribute
   */
  private void removePMap(NoSQLSchema schema, Attribute attr)
  {
    CompareStructuralVariation comparer = new CompareStructuralVariation();
    String entityName = PMAP_ENTITY_PREFIX + Inflector.getInstance().capitalize(attr.getName());
    PMap attrMap = (PMap)attr.getType();

    // First of all check if an entity with the same construction already exists. If not, just create it.
    EntityClass mapEntity = null;
    Optional<EntityClass> optEntity = schema.getEntities().stream().filter(entity -> {return entity.getName().equals(entityName);}).findFirst();

    if (optEntity.isPresent())
      mapEntity = optEntity.get();
    else
    {
      mapEntity = factory.createEntityClass();
      mapEntity.setName(entityName);
      mapEntity.setRoot(false);
      schema.getEntities().add(mapEntity);
    }

    // Now create a StructuralVariation with two properties: Key and value.
    Attribute key = factory.createAttribute();
    key.setName("key");
    key.setType(attrMap.getKeyType());

    Attribute value = factory.createAttribute();
    value.setName("value");
    value.setType(attrMap.getValueType());

    StructuralVariation compareVar = factory.createStructuralVariation();
    compareVar.getProperties().add(key);
    compareVar.getProperties().add(value);

    // Check if an equivalent StructuralVariation in the given Entity already exists.
    Optional<StructuralVariation> optVar = mapEntity.getVariations().stream().filter(var -> {return comparer.compare(compareVar, var);}).findFirst();

    StructuralVariation theVar;
    if (optVar.isPresent())
      theVar = optVar.get();
    else
    {
      theVar = compareVar;
      int varId = mapEntity.getVariations().size() + 1;
      compareVar.setVariationId(varId);
      mapEntity.getVariations().add(compareVar);
    }

    // Exchange the PMap attribute with an aggregate.
    Aggregate aggr = factory.createAggregate();
    aggr.setName(attr.getName());
    aggr.setLowerBound(1);
    aggr.setUpperBound(1);
    aggr.setOptional(attr.isOptional());
    aggr.getAggregates().add(theVar);

    // The new aggregate will point to the StructuralVariation.
    // The old PMap attribute is unnecesary now.
    ((StructuralVariation)attr.eContainer()).getProperties().add(aggr);
    ((StructuralVariation)attr.eContainer()).getProperties().remove(attr);

    // Check this out! We do recursively remove every Map of a Map,
    // so cases like Map<String, Map<String, Int>> do not make us cry.
    if (value.getType() instanceof PMap)
      removePMap(schema, value);
  }
}
