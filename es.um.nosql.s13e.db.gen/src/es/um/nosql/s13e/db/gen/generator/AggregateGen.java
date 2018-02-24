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
import es.um.nosql.s13e.db.gen.utils.Constants;

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
    JsonNode result = null;

    int lBound = aggr.getLowerBound() > 0 ? aggr.getLowerBound() : Constants.GET_AGGREGATE_MIN_ALLOWED();
    int uBound = aggr.getUpperBound() > 0 ? aggr.getUpperBound() : Constants.GET_AGGREGATE_MAX_ALLOWED();

    if (lBound == 1 && uBound == 1)
      result = this.getRandomAggr(aggr.getRefTo().get(0), evMap);
    else
    {
      ArrayNode aggrArray = jsonFactory.arrayNode();

      // TODO: This might aggregate the same object several times. It might be a problem.
      // For each aggregation we have to include, we select a random aggregated version and aggregate one object according to that version.
      for (int j = 0; j < numGen.getInclusiveRandom(lBound, uBound); j++)
        aggrArray.add(this.getRandomAggr(aggr.getRefTo().get(numGen.getExclusiveRandom(0,  aggr.getRefTo().size())), evMap));

      result = aggrArray;
    }

    return result;
  }

  private ObjectNode getRandomAggr(EntityVersion eVersion, Map<EntityVersion, List<ObjectNode>> evMap)
  {
    return evMap.get(eVersion).get(numGen.getExclusiveRandom(0, evMap.get(eVersion).size()));
  }
}