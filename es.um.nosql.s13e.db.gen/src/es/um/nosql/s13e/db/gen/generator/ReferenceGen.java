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

  public JsonNode genReference(Reference ref, Map<String, List<String>> entityIdMap)
  {
    JsonNode result = null;

    int lBound = ref.getLowerBound() > 0 ? ref.getLowerBound() : Constants.GET_REFERENCE_MIN_ALLOWED();
    int uBound = ref.getUpperBound() > 0 ? ref.getUpperBound() : Constants.GET_REFERENCE_MAX_ALLOWED();

    try
    {
      if (lBound == 1 && uBound == 1)
        result = this.getRandomRefId(ref, entityIdMap);
      else
      {
        ArrayNode refArray = jsonFactory.arrayNode();

        for (int j = 0; j < numGen.getInclusiveRandom(lBound, uBound); j++)
          refArray.add(this.getRandomRefId(ref, entityIdMap));

        result = refArray;
      }
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  private JsonNode getRandomRefId(Reference ref, Map<String, List<String>> entityIdMap) throws Exception
  {
    String refEntityName = ref.getRefTo().getName();
    String refOriginalType = ref.getOriginalType() == null ? "string" : ref.getOriginalType().toLowerCase();

    if (!entityIdMap.containsKey(refEntityName))
      throw new IllegalArgumentException("Reference not found: " + ref.getRefTo().getName());

    String refResult = entityIdMap.get(refEntityName).get(numGen.getExclusiveRandom(0, entityIdMap.get(refEntityName).size()));
//TODO: What if the ref is actually NOT a castable Number...hmm we better FILTER first.
    switch (refOriginalType)
    {
      case "objectid":
      {
        return jsonFactory.objectNode().put("$oid", refResult);
      }
      case "number":          {return jsonFactory.nu}
      case "string": default: {return jsonFactory.textNode(refResult);}
    }
  }
}