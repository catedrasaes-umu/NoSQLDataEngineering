package es.um.nosql.s13e.db.gen.generator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import es.um.nosql.s13e.NoSQLSchema.Entity;
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

  public JsonNode genReference(Reference ref, Map<Entity, List<JsonNode>> entityIdMap)
  {
    JsonNode result = null;

    int lBound = ref.getLowerBound() > 0 ? ref.getLowerBound() : Constants.GET_REFERENCE_MIN_ALLOWED();
    int uBound = ref.getUpperBound() > 0 ? ref.getUpperBound() : Constants.GET_REFERENCE_MAX_ALLOWED();

    if (lBound == 1 && uBound == 1)
      result = this.getRandomRefId(ref, entityIdMap);
    else
    {
      ArrayNode refArray = jsonFactory.arrayNode();

      // TODO: This might reference the same object several times. It might be a problem.
      for (int j = 0; j < numGen.getInclusiveRandom(lBound, uBound); j++)
        refArray.add(this.getRandomRefId(ref, entityIdMap));

      result = refArray;
    }

    return result;
  }

  private JsonNode getRandomRefId(Reference ref, Map<Entity, List<JsonNode>> entityIdMap) throws IllegalArgumentException
  {
    String refOriginalType = ref.getOriginalType() != null ? ref.getOriginalType().toLowerCase() : "string";
    List<JsonNode> idsList = null;

    if (!entityIdMap.containsKey(ref.getRefTo()))
      throw new IllegalArgumentException("Reference not found: " + ref.getRefTo().getName());

    switch (refOriginalType)
    {
      case "string":              {idsList = entityIdMap.get(ref.getRefTo()).stream().filter(jsonNode -> jsonNode instanceof TextNode).collect(Collectors.toList()); break;}
      case "objectid":            {idsList = entityIdMap.get(ref.getRefTo()).stream().filter(jsonNode -> jsonNode instanceof ObjectNode).collect(Collectors.toList()); break;}
      case "int": case "number":  {idsList = entityIdMap.get(ref.getRefTo()).stream().filter(jsonNode -> jsonNode instanceof NumericNode).collect(Collectors.toList()); break;}
      default: {throw new IllegalArgumentException("Reference type " + refOriginalType + " is not valid.");}
    }

    if (idsList.isEmpty())
      throw new IllegalArgumentException("References of type " + refOriginalType + " could not be found.");

    return idsList.get(numGen.getExclusiveRandom(0, idsList.size()));
  }
}