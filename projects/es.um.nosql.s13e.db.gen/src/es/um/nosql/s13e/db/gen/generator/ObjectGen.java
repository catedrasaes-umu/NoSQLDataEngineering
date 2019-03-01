package es.um.nosql.s13e.db.gen.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.db.gen.generator.properties.AggregateGen;
import es.um.nosql.s13e.db.gen.generator.properties.PrimitiveTypeGen;
import es.um.nosql.s13e.db.gen.generator.properties.ReferenceGen;
import es.um.nosql.s13e.db.gen.generator.properties.PTupleGen;
import es.um.nosql.s13e.db.gen.util.EntityIdMap;
import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class ObjectGen
{
  private PrimitiveTypeGen pTypeGen;
  private PTupleGen tupleGen;
  private ReferenceGen refGen;
  private AggregateGen aggrGen;
  private JsonNodeFactory factory;
  private EntityIdMap eIdMap;
  private Map<StructuralVariation, List<ObjectNode>> evMap;

  public ObjectGen()
  {
    pTypeGen = new PrimitiveTypeGen();
    tupleGen = new PTupleGen();
    refGen = new ReferenceGen();
    aggrGen = new AggregateGen();
    factory = JsonNodeFactory.instance;
    eIdMap = new EntityIdMap();
    evMap = new HashMap<StructuralVariation, List<ObjectNode>>();
  }

  public Map<String, ArrayNode> generateBulk(NoSQLSchema schema, int objectsVariations)
  {
    return generate(schema, objectsVariations);
  }

  public Map<String, Stream<JsonNode>> generateStream(NoSQLSchema schema, int objectsVariations)
  {
    Map<String, Stream<JsonNode>> result = new HashMap<String, Stream<JsonNode>>();
    Map<String, ArrayNode> generatedResult = generate(schema, objectsVariations);

    for (String key : generatedResult.keySet())
      result.put(key, StreamSupport.stream(generatedResult.get(key).spliterator(), false));

    return result;
  }

  private Map<String, ArrayNode> generate(NoSQLSchema schema, int objectsVariations)
  {
    Map<String, ArrayNode> result = new HashMap<String, ArrayNode>();

    // First run to generate all the primitive types and tuples.
    for (EntityClass entity : schema.getEntities())
    {
      ArrayNode entityObjs = factory.arrayNode();

      if (entity.isRoot())
        eIdMap.initialize(entity.getName());

      for (StructuralVariation eVariation : entity.getVariations())
      {
        evMap.put(eVariation, new ArrayList<ObjectNode>());

        for (int i = 0; i < objectsVariations; i++)
        {
          ObjectNode oNode = factory.objectNode();
          evMap.get(eVariation).add(oNode);

          eVariation.getProperties().stream().filter(p -> p instanceof Attribute && !p.getName().equals("_id")).forEach(p -> this.generateAttribute(oNode, (Attribute)p));

          if (entity.isRoot())
          {
            entityObjs.add(oNode);
            this.generateMetadata(oNode, entity, eVariation.getProperties().stream()
                .filter(p -> p instanceof Attribute && p.getName().equals("_id")).map(p -> (Attribute)p)
                .findFirst());
          }
        }
      }

      if (entityObjs.size() > 0)
        result.put(entity.getName(), entityObjs);
    }

    // Second run to generate the references and aggregates since now all the variations and instances exist.
    for (EntityClass entity : schema.getEntities())
      for (StructuralVariation eVariation : entity.getVariations())
        for (ObjectNode strObj : evMap.get(eVariation))
          eVariation.getProperties().stream().filter(p -> p instanceof Association).forEach(p -> this.generateAssociation(strObj, (Association)p));

    evMap.clear();
    eIdMap.clear();

    return result;
  }

  private void generateMetadata(ObjectNode oNode, EntityClass entity, Optional<Attribute> theId)
  {
    if (!theId.isPresent())
      oNode.set("_id", pTypeGen.genTrustedObjectId("objectid"));
    else
      oNode.set("_id", pTypeGen.genTrustedObjectId(((PrimitiveType)theId.get().getType()).getName()));

    eIdMap.add(entity.getName(), oNode.get("_id"));

    if (ConfigConstants.GET_INCLUDE_TYPE())
      oNode.put("_type", entity.getName());
  }

  private void generateAttribute(ObjectNode oNode, Attribute attr)
  {
    if (attr.getType() instanceof PrimitiveType)
      oNode.set(attr.getName(), pTypeGen.genPrimitiveType(((PrimitiveType)attr.getType()).getName()));
    else if (attr.getType() instanceof PTuple)
      oNode.set(attr.getName(), tupleGen.genPTuple(((PTuple)attr.getType()).getElements()));
  }

  private void generateAssociation(ObjectNode oNode, Association assc)
  {
    if (assc instanceof Reference)
      oNode.set(assc.getName(), refGen.genReference((Reference)assc, eIdMap));
    if (assc instanceof Aggregate)
      oNode.set(assc.getName(), aggrGen.genAggregate((Aggregate)assc, evMap));
  }
}
