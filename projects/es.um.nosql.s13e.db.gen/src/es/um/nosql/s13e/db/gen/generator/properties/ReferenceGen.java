package es.um.nosql.s13e.db.gen.generator.properties;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.BooleanGen;
import es.um.nosql.s13e.db.gen.util.EntityIdMap;
import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;
import es.um.nosql.s13e.db.gen.util.constants.Types;

public class ReferenceGen
{
  private BooleanGen boolGen;
  private JsonNodeFactory jsonFactory;

  public ReferenceGen()
  {
    boolGen = BooleanGen.GET_INSTANCE();
    jsonFactory = JsonNodeFactory.instance;
  }

  public JsonNode genReference(Reference ref, EntityIdMap eIdMap)
  {
    JsonNode result = null;

    int lBound = ref.getLowerBound() >= 0 ? ref.getLowerBound() : ConfigConstants.GET_REFERENCE_MIN_ALLOWED();
    int uBound = ref.getUpperBound() > 0 ? ref.getUpperBound() : ConfigConstants.GET_REFERENCE_MAX_ALLOWED();

    if (lBound == 1 && uBound == 1)
      result = this.getRandomRefIds(ref, eIdMap, 1).get(0);
    else
    {
      ArrayNode refArray = jsonFactory.arrayNode();
      refArray.addAll(this.getRandomRefIds(ref, eIdMap, uBound));
      result = refArray;
    }

    return result;
  }

  private List<JsonNode> getRandomRefIds(Reference ref, EntityIdMap eIdMap, int numElements) throws IllegalArgumentException
  {
    String refOriginalType = ref.getOriginalType() != null ? ref.getOriginalType().toLowerCase() : "string";

    if (boolGen.thisHappens(ConfigConstants.GET_REFERENCE_STRANGE_TYPES_PROBABILITY()))
      refOriginalType = Types.GET_RANDOM_REFERENCE_TYPE_EXCLUDING(refOriginalType);

    List<JsonNode> result = new ArrayList<JsonNode>();

    if (!eIdMap.containsKey(ref.getRefTo().getName(), refOriginalType))
      throw new IllegalArgumentException("Reference " + ref.getRefTo().getName() + " with type " + refOriginalType + " not found.");

    // TODO: This might reference the same object several times. It might be a problem.
    for (int i = 0; i < numElements; i++)
      result.add(eIdMap.getRandomElement(ref.getRefTo().getName(), refOriginalType));

    return result;
  }
}
