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

  private final static String PMAP_ENTITY_PREFIX = "Map_";

  public NoSQLSchemaToDocumentDb()
  {
    factory = NoSQLSchemaFactory.eINSTANCE;
  }

  public void adaptToDocumentDb(NoSQLSchema schema)
  {
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      for (StructuralVariation var : classifier.getVariations())
      {
        List<Attribute> attrPMapList = new ArrayList<Attribute>();

        attrPMapList.addAll(var.getProperties().stream().filter(prop ->
        {
          return (prop instanceof Attribute && ((Attribute)prop).getType() instanceof PMap);
        }).map(prop -> (Attribute)prop).collect(Collectors.toList()));

        for (Attribute attr : attrPMapList)
          removePMap(schema, attr);
      }
  }

  private void refClassToEntityClass(NoSQLSchema schema, ReferenceClass refClass)
  {
    //TODO: Remove ReferenceClass
  }

  private void removeRefVar(NoSQLSchema schema, Reference ref)
  {
    //TODO: Remove Reference to StructuralVariation
  }

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
