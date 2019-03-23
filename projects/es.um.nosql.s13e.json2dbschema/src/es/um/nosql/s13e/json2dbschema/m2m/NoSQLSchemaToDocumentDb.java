package es.um.nosql.s13e.json2dbschema.m2m;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.PMap;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.RelationshipType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.json2dbschema.util.inflector.Inflector;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;
import es.um.nosql.s13e.util.compare.CompareStructuralVariation;

public class NoSQLSchemaToDocumentDb
{
  private NoSQLSchemaFactory factory;

  private final static String REF_ENTITY_PREFIX = "Ref_";
  private final static String PMAP_ENTITY_PREFIX = "Map_";

  public static void main(String[] args)
  {
    NoSQLSchemaToDocumentDb var1 = new NoSQLSchemaToDocumentDb();

    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File("../es.um.nosql.models/dummy/dummy.xmi"), NoSQLSchema.class);
    var1.adaptToDocumentDb(schema);
    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(schema, "../es.um.nosql.models/dummy/dummy2.xmi");
  }

  public NoSQLSchemaToDocumentDb()
  {
    factory = NoSQLSchemaFactory.eINSTANCE;
  }

  public void adaptToDocumentDb(NoSQLSchema schema)
  {
    List<RelationshipType> refClasses = new ArrayList<RelationshipType>();
    List<Attribute> mapAttributes = new ArrayList<Attribute>();

    for (SchemaType schemaT : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).collect(Collectors.toList()))
    {
      if (schemaT instanceof RelationshipType)
        refClasses.add((RelationshipType)schemaT);

      for (StructuralVariation var : schemaT.getVariations())
      {
        mapAttributes.addAll(var.getProperties().stream().filter(prop ->
        {
          return (prop instanceof Attribute && ((Attribute)prop).getType() instanceof PMap);
        }).map(prop -> (Attribute)prop).collect(Collectors.toList()));
      }
    }

    refClasses.forEach(refClass -> {relTypeToEntityType(schema, refClass);});
    mapAttributes.forEach(attr -> {removePMap(schema, attr);});
  }

  /**
   * This method translates every RelationshipType on the current schema to an equivalent EntityType.
   * The new EntityType will contain every variation that the previous RelationshipType used to have,
   * so any Reference pointing to a RelationshipType variation will now point to an equivalent EntityType variation.
   * @param schema The schema being processed
   * @param relClass The RelationshipType being removed
   */
  private void relTypeToEntityType(NoSQLSchema schema, RelationshipType relType)
  {
    CompareStructuralVariation comparer = new CompareStructuralVariation();
    List<Reference> lReferences = new ArrayList<Reference>();
    String entityName = REF_ENTITY_PREFIX + Inflector.getInstance().capitalize(relType.getName());

    // Get in a list each reference featuring a variation of the refClass
    for (SchemaType schemaType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).collect(Collectors.toList()))
      for (StructuralVariation var : schemaType.getVariations())
        lReferences.addAll(var.getProperties().stream()
            .filter(prop -> {return prop instanceof Reference && !Collections.disjoint(relType.getVariations(), ((Reference)prop).getFeatures());})
            .map(prop -> (Reference)prop)
            .collect(Collectors.toList()));

    for (Reference ref : lReferences)
    {
      for (StructuralVariation var : ref.getFeatures())
      {
        // We take the featured StructuralVariation and add a new Reference field.
        Reference newRef = factory.createReference();
        newRef.setName(ref.getName());
        newRef.setLowerBound(ref.getLowerBound());
        newRef.setUpperBound(ref.getUpperBound());
        newRef.setOpposite(ref.getOpposite());
        newRef.setOriginalType(ref.getOriginalType());
        newRef.setRefsTo(ref.getRefsTo());
        var.getProperties().add(newRef); // What to do if it already exists a property named as the ref?

        if (!var.getProperties().stream().anyMatch(prop -> prop.getName().equals("_id")))
        {
          Attribute idAttr = factory.createAttribute();
          idAttr.setName("_id");
          PrimitiveType pType = factory.createPrimitiveType();
          pType.setName("ObjectId");
          idAttr.setType(pType);
          var.getProperties().add(idAttr);
        }

        Attribute theId = (Attribute)var.getProperties().stream().filter(prop -> prop.getName().equals("_id")).findFirst().get();
        String idType = ((PrimitiveType)theId.getType()).getName();
        ref.setOriginalType(idType);
      }

      // We modify the current reference to a new cardinality
      ref.setLowerBound(1);
      ref.setUpperBound(1);
      ref.getFeatures().clear();
    }

    // Create the new EntityType from a RelationshipType
    EntityType refEntity = null;
    Optional<EntityType> optEntity = schema.getEntities().stream().filter(entity -> {return entity.getName().equals(entityName);}).findFirst();

    if (optEntity.isPresent())
      refEntity = optEntity.get();
    else
    {
      refEntity = factory.createEntityType();
      refEntity.setName(entityName);
      refEntity.setRoot(false);
      refEntity.getParents().addAll(relType.getParents());
      schema.getEntities().add(refEntity);
    }

    // We also modify the current references to reference the new EntityType
    for (Reference ref : lReferences)
      ref.setRefsTo(refEntity);

    // If an EntityType with the same name as a RelationshipType existed, we add the variations but take care of the variationId identifier.
    int varSize = refEntity.getVariations().size();
    if (varSize != 0)
    {
      List<StructuralVariation> varsToMove = new ArrayList<StructuralVariation>();
      for (StructuralVariation var : relType.getVariations())
        if (refEntity.getVariations().stream().noneMatch(innerVar -> {return comparer.compare(innerVar, var);}))
        {
          var.setVariationId(++varSize);
          varsToMove.add(var);
        }
      refEntity.getVariations().addAll(varsToMove);
    }
    else
      refEntity.getVariations().addAll(relType.getVariations());

    schema.getRelationships().remove(relType);
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
    EntityType mapEntity = null;
    Optional<EntityType> optEntity = schema.getEntities().stream().filter(entity -> {return entity.getName().equals(entityName);}).findFirst();

    if (optEntity.isPresent())
      mapEntity = optEntity.get();
    else
    {
      mapEntity = factory.createEntityType();
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
