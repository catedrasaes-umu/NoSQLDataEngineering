package es.um.nosql.s13e.db.gen.generator;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.utils.Constants;

public class ReferenceGen
{
  private NumberGen numGen;
  private JsonNodeFactory jsonFactory;

  public ReferenceGen()
  {
    numGen = NumberGen.GET_INSTANCE();
    jsonFactory = JsonNodeFactory.instance;
  }

  public JsonNode genReference(Reference assc, Map<String, List<String>> entityIdMap)
  {
    JsonNode result = null;
    Reference ref = (Reference)assc;

    int lBound = ref.getLowerBound() > 0 ? ref.getLowerBound() : Constants.GET_REFERENCE_MIN_ALLOWED();
    int uBound = ref.getUpperBound() > 0 ? ref.getUpperBound() : Constants.GET_REFERENCE_MAX_ALLOWED();

    try
    {
      if (lBound == 1 && uBound == 1)
        result = jsonFactory.textNode(getRandomRefId(ref.getName(), entityIdMap));
      else
      {
        ArrayNode refArray = jsonFactory.arrayNode();

        for (int j = 0; j < numGen.getInclusiveRandom(lBound, uBound); j++)
          refArray.add(getRandomRefId(ref.getName(), entityIdMap));

        result = refArray;
      }
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  private String getRandomRefId(String name, Map<String, List<String>> entityIdMap) throws Exception
  {
    for (String eName : entityIdMap.keySet())
      if (eName.toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(eName.toLowerCase()))
        return entityIdMap.get(eName).get(numGen.getExclusiveRandom(0, entityIdMap.get(eName).size()));

    throw new Exception("Reference not found: " + name);
  }
}