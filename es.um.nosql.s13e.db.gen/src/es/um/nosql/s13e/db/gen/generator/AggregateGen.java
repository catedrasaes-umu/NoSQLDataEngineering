package es.um.nosql.s13e.db.gen.generator;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;

public class AggregateGen
{
  private NumberGen numGen;
  private JsonNodeFactory jsonFactory;

  public AggregateGen()
  {
    numGen = NumberGen.GET_INSTANCE();
    jsonFactory = JsonNodeFactory.instance;
  }

  public JsonNode genAggregate(Aggregate aggr, Map<EntityVersion, List<ObjectNode>> evMap)
  {
    if (aggr.getLowerBound() == 1 && aggr.getUpperBound() == 1)
      return this.getRandomAggr(aggr.getRefTo().get(0), evMap);
    else
    {
      ArrayNode array = jsonFactory.arrayNode();

      // We keep all the aggregated versions in a banned list because we won't add them to the database as standalone objects.
      for (EntityVersion aggrEV : aggr.getRefTo())
      {
        ObjectNode aggrNode = this.getRandomAggr(aggrEV, evMap);
        array.add(aggrNode);
      }

      return array;
    }
  }

  private ObjectNode getRandomAggr(EntityVersion eVersion, Map<EntityVersion, List<ObjectNode>> evMap)
  {
    return evMap.get(eVersion).get(numGen.getExclusiveRandom(0, evMap.get(eVersion).size()));
  }
}