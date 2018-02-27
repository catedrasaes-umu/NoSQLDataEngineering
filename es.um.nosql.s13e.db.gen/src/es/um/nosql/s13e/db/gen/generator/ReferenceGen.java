package es.um.nosql.s13e.db.gen.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.BooleanGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.utils.Constants;
import es.um.nosql.s13e.db.gen.utils.EntityIdMap;

public class ReferenceGen
{
  private NumberGen numGen;
  private BooleanGen boolGen;
  private JsonNodeFactory jsonFactory;
  private List<String> definedTypes;

  public ReferenceGen()
  {
    numGen = NumberGen.GET_INSTANCE();
    boolGen = BooleanGen.GET_INSTANCE();
    jsonFactory = JsonNodeFactory.instance;
    definedTypes  = Arrays.asList("string", "number", "objectid");
  }

  public JsonNode genReference(Reference ref, EntityIdMap eIdMap)
  {
    JsonNode result = null;

    int lBound = ref.getLowerBound() > 0 ? ref.getLowerBound() : Constants.GET_REFERENCE_MIN_ALLOWED();
    int uBound = ref.getUpperBound() > 0 ? ref.getUpperBound() : Constants.GET_REFERENCE_MAX_ALLOWED();

    if (lBound == 1 && uBound == 1)
      result = this.getRandomRefIds(ref, eIdMap, 1).get(0);
    else
    {
      ArrayNode refArray = jsonFactory.arrayNode();

      // TODO: This might reference the same object several times. It might be a problem.
      refArray.addAll(this.getRandomRefIds(ref, eIdMap, numGen.getInclusiveRandom(lBound, uBound)));

      result = refArray;
    }

    return result;
  }

  private List<JsonNode> getRandomRefIds(Reference ref, EntityIdMap eIdMap, int numElements) throws IllegalArgumentException
  {
    String refOriginalType = ref.getOriginalType() != null ? ref.getOriginalType().toLowerCase() : "string";

    if (boolGen.thisHappens(Constants.GET_REFERENCE_STRANGE_TYPES_PROBABILITY()))
      refOriginalType = getRandomTypeExcluding(refOriginalType);

    List<JsonNode> result = new ArrayList<JsonNode>();

    if (!eIdMap.containsKey(ref.getRefTo().getName(), ref.getOriginalType()))
      throw new IllegalArgumentException("Reference " + ref.getRefTo().getName() + " with type " + ref.getOriginalType() + " not found.");

    for (int i = 0; i < numElements; i++)
      result.add(eIdMap.getRandomElement(ref.getRefTo().getName(), refOriginalType));

    return result;
  }

  private String getRandomTypeExcluding(String type)
  {
    int index = numGen.getExclusiveRandom(0, definedTypes.size());

    while (definedTypes.get(index).equals(type))
      index = numGen.getExclusiveRandom(0, definedTypes.size());

    return definedTypes.get(index);
  }
}