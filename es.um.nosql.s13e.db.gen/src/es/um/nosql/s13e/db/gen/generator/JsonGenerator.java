package es.um.nosql.s13e.db.gen.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.utils.Constants;

public class JsonGenerator
{
  private PrimitiveTypeGen pTypeGen;
  private TupleGen tupleGen;
  private NumberGen numGen;
  private Map<EntityVersion, List<ObjectNode>> evMap;
  private Map<String, List<String>> entityIdMap;
  private ArrayNode lStorage;

  private JsonNodeFactory factory = JsonNodeFactory.instance;

  public JsonGenerator()
  {
    pTypeGen = new PrimitiveTypeGen();
    tupleGen = new TupleGen();
    numGen = NumberGen.GET_INSTANCE();
  }

  public String generate(NoSQLSchema schema) throws Exception
  {
    evMap = new HashMap<EntityVersion, List<ObjectNode>>();
    entityIdMap = new HashMap<String, List<String>>();

    lStorage = factory.arrayNode();

    // First run to generate all the primitive types and tuples.
    for (Entity entity : schema.getEntities())
    {
      entityIdMap.put(entity.getName().toLowerCase(), new ArrayList<String>());
      for (EntityVersion eVersion : entity.getEntityversions())
      {
        evMap.put(eVersion, new ArrayList<ObjectNode>());
        int countInstances = numGen.getInclusiveRandom(Constants.GET_MIN_INSTANCES_VERSION(), Constants.GET_MAX_INSTANCES_VERSION());

        for (int i = 0; i < countInstances; i++)
        {
          ObjectNode oNode = factory.objectNode();

          eVersion.getProperties().stream().filter(p -> p instanceof Attribute).forEach(p -> this.generateAttribute(oNode, (Attribute)p));

          // We will override the _id and the type parameters...
          if (eVersion.isRoot())
          {
            oNode.put("_id", pTypeGen.genTrustedPrimitiveType("objectid"));
            oNode.put("_type", entity.getName());
            lStorage.add(oNode);
            entityIdMap.get(entity.getName().toLowerCase()).add(oNode.get("_id").asText());
          }

          evMap.get(eVersion).add(oNode);
        }
      }
    }

    // Second run to generate the references and aggregates since now all the versions and instances exist.
    for (Entity entity : schema.getEntities())
      for (EntityVersion eVersion : entity.getEntityversions())
        for (ObjectNode strObj : evMap.get(eVersion))
        {
          for (Property property : eVersion.getProperties())
          {
            if (property instanceof Reference)
            {
              Reference ref = (Reference)property;

              int lBound = ref.getLowerBound() > 0 ? ref.getLowerBound() : 0;
              int uBound = ref.getUpperBound() > 0 ? ref.getUpperBound() : 5;

              if (lBound == 1 && uBound == 1)
                strObj.put(ref.getName(), getRandomRefId(ref.getName()));
              else
              {
                ArrayNode refArray = factory.arrayNode();
                strObj.put(ref.getName(), refArray);

                for (int j = 0; j < numGen.getInclusiveRandom(lBound, uBound); j++)
                  refArray.add(getRandomRefId(ref.getName()));
              }
            }
            if (property instanceof Aggregate)
            {
              Aggregate aggr = (Aggregate)property;

              if (aggr.getLowerBound() == 1 && aggr.getUpperBound() == 1)
                strObj.put(aggr.getName(), getRandomAggr(aggr.getRefTo().get(0)));
              else
              {
                ArrayNode array = factory.arrayNode();
                strObj.put(aggr.getName(), array);
                // We keep all the aggregated versions in a banned list because we won't add them to the database as standalone objects.
                for (EntityVersion aggrEV : aggr.getRefTo())
                {
                  ObjectNode aggrNode = getRandomAggr(aggrEV);
                  array.add(aggrNode);
                }
              }
            }
          }
        }

    return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lStorage);
  }

  private String getRandomRefId(String name) throws Exception
  {
    for (String eName : entityIdMap.keySet())
      if (eName.toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(eName.toLowerCase()))
        return entityIdMap.get(eName).get(numGen.getExclusiveRandom(0, entityIdMap.get(eName).size()));

    throw new Exception("Reference not found: " + name);
  }

  private ObjectNode getRandomAggr(EntityVersion eVersion)
  {
    return evMap.get(eVersion).get(numGen.getExclusiveRandom(0, evMap.get(eVersion).size()));
  }

  private void generateAttribute(ObjectNode oNode, Attribute attr)
  {
    if (attr.getType() instanceof PrimitiveType)
      oNode.put(attr.getName(), pTypeGen.genPrimitiveType(((PrimitiveType)attr.getType()).getName()));
    else if (attr.getType() instanceof Tuple)
      oNode.put(attr.getName(), tupleGen.genTuple(((Tuple)attr.getType()).getElements()));
  }
}