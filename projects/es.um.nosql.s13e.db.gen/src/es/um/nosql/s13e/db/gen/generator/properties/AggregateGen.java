package es.um.nosql.s13e.db.gen.generator.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class AggregateGen
{
  private NumberGen numGen;
  private JsonNodeFactory jsonFactory;

  public AggregateGen()
  {
    numGen = NumberGen.GET_INSTANCE();
    jsonFactory = JsonNodeFactory.instance;
  }

  public JsonNode genAggregate(Aggregate aggr, Map<EntityVariation, List<ObjectNode>> evMap)
  {
    JsonNode result = null;

    int lBound = aggr.getLowerBound() >= 0 ? aggr.getLowerBound() : ConfigConstants.GET_AGGREGATE_MIN_ALLOWED();
    int uBound = aggr.getUpperBound() > 0 ? aggr.getUpperBound() : ConfigConstants.GET_AGGREGATE_MAX_ALLOWED();

    if (lBound == 1 && uBound == 1)
      result = this.getRandomAggrs(aggr.getRefTo().get(0), evMap, 1).get(0);
    else
    {
      ArrayNode aggrArray = jsonFactory.arrayNode();

      // TODO: This might aggregate the same object several times. It might be a problem.
      // For each aggregation we have to include, we select a random aggregated variation and aggregate one object according to that variation.
      // TODO: This is for sure optimisable. We should be able to shuffle variations and extract some objects of them, not only one variation.
      // TODO: Fix cardinalities
      for (EntityVariation ev : aggr.getRefTo())
        aggrArray.addAll(this.getRandomAggrs(ev, evMap, uBound));

      result = aggrArray;
    }

    return result;
  }

  private List<JsonNode> getRandomAggrs(EntityVariation eVariation, Map<EntityVariation, List<ObjectNode>> evMap, int numElements)
  {
    List<JsonNode> result = new ArrayList<JsonNode>();

    for (int i = 0; i < numElements; i++)
      result.add(evMap.get(eVariation).get(numGen.getExclusiveRandom(0, evMap.get(eVariation).size())));

    return result;
  }
}
