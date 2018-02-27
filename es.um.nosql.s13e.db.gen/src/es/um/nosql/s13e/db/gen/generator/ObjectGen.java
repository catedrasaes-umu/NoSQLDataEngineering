package es.um.nosql.s13e.db.gen.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.utils.Constants;
import es.um.nosql.s13e.db.gen.utils.EntityIdMap;

public class ObjectGen
{
  private NumberGen numGen;
  private PrimitiveTypeGen pTypeGen;
  private TupleGen tupleGen;
  private ReferenceGen refGen;
  private AggregateGen aggrGen;
  private JsonNodeFactory factory;
  private EntityIdMap eIdMap;
  private Map<EntityVersion, List<ObjectNode>> evMap;


  public ObjectGen()
  {
    numGen = NumberGen.GET_INSTANCE();
    pTypeGen = new PrimitiveTypeGen();
    tupleGen = new TupleGen();
    refGen = new ReferenceGen();
    aggrGen = new AggregateGen();
    factory = JsonNodeFactory.instance;
    eIdMap = new EntityIdMap();
    evMap = new HashMap<EntityVersion, List<ObjectNode>>();
  }

  public Map<String, ArrayNode> generate(NoSQLSchema schema, int objectsPerVersion)
  {
    //TODO: Maybe we should try to divide the work into several splits so we don't get blocked by passing a really gigantic JSON.
    Map<String, ArrayNode> result = new HashMap<String, ArrayNode>();

    // First run to generate all the primitive types and tuples.
    for (Entity entity : schema.getEntities())
    {
      ArrayNode entityObjs = factory.arrayNode();

      if (entity.getEntityversions().stream().anyMatch(ev -> ev.isRoot()))
        eIdMap.initialize(entity.getName());

      for (EntityVersion eVersion : entity.getEntityversions())
      {
        evMap.put(eVersion, new ArrayList<ObjectNode>());

        for (int i = 0; i < numGen.getInclusiveRandom(Constants.GET_MIN_INSTANCES(), Constants.GET_MAX_INSTANCES()); i++)
        {
          ObjectNode oNode = factory.objectNode();
          evMap.get(eVersion).add(oNode);

          eVersion.getProperties().stream().filter(p -> p instanceof Attribute && !p.getName().equals("_id")).forEach(p -> this.generateAttribute(oNode, (Attribute)p));

          if (eVersion.isRoot())
          {
            entityObjs.add(oNode);
            this.generateMetadata(oNode, entity, eVersion.getProperties().stream()
                .filter(p -> p instanceof Attribute && p.getName().equals("_id")).map(p -> (Attribute)p)
                .findFirst());
          }
        }
      }

      if (entityObjs.size() > 0)
        result.put(entity.getName(), entityObjs);
    }

    // Second run to generate the references and aggregates since now all the versions and instances exist.
    for (Entity entity : schema.getEntities())
      for (EntityVersion eVersion : entity.getEntityversions())
        for (ObjectNode strObj : evMap.get(eVersion))
          eVersion.getProperties().stream().filter(p -> p instanceof Association).forEach(p -> this.generateAssociation(strObj, (Association)p));

    evMap.clear();
    eIdMap.clear();

    return result;
  }

  private void generateMetadata(ObjectNode oNode, Entity entity, Optional<Attribute> theId)
  {
    if (!theId.isPresent())
      oNode.put("_id", pTypeGen.genTrustedObjectId("objectid"));
    else
      oNode.put("_id", pTypeGen.genTrustedObjectId(((PrimitiveType)theId.get().getType()).getName()));

    eIdMap.add(entity.getName(), oNode.get("_id"));

    if (Constants.GET_ENTITY_INCLUDE_TYPE())
      oNode.put("_type", entity.getName());
  }

  private void generateAttribute(ObjectNode oNode, Attribute attr)
  {
    if (attr.getType() instanceof PrimitiveType)
      oNode.put(attr.getName(), pTypeGen.genPrimitiveType(((PrimitiveType)attr.getType()).getName()));
    else if (attr.getType() instanceof Tuple)
      oNode.put(attr.getName(), tupleGen.genTuple(((Tuple)attr.getType()).getElements()));
  }

  private void generateAssociation(ObjectNode oNode, Association assc)
  {
    if (assc instanceof Reference)
      oNode.put(assc.getName(), refGen.genReference((Reference)assc, eIdMap));
    if (assc instanceof Aggregate)
      oNode.put(assc.getName(), aggrGen.genAggregate((Aggregate)assc, evMap));
  }
}