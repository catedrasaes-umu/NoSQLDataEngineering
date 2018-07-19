package es.um.nosql.s13e.db.gen.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;

import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;


public class EntityIdMap
{
  private NumberGen numGen;
  private Map<String, List<JsonNode>> entityObjectIdMap;
  private Map<String, List<TextNode>> entityStringIdMap;
  private Map<String, List<NumericNode>> entityNumberIdMap;

  public EntityIdMap()
  {
    numGen = NumberGen.GET_INSTANCE();
    entityObjectIdMap = new HashMap<String, List<JsonNode>>();
    entityStringIdMap = new HashMap<String, List<TextNode>>();
    entityNumberIdMap = new HashMap<String, List<NumericNode>>();
  }

  public void initialize(String name)
  {
    entityObjectIdMap.put(name, new ArrayList<JsonNode>());
    entityStringIdMap.put(name, new ArrayList<TextNode>());
    entityNumberIdMap.put(name, new ArrayList<NumericNode>());
  }

  public void add(String key, JsonNode value)
  {
    if (value.isObject())
      entityObjectIdMap.get(key).add(value);
    else if (value.isTextual())
      entityStringIdMap.get(key).add((TextNode)value);
    else if (value.isNumber())
      entityNumberIdMap.get(key).add((NumericNode)value);
    else
      throw new IllegalArgumentException(value + " is not a valid type (Object | String | Number)");
  }

  public JsonNode getRandomElement(String key, String type)
  {
    switch (type.toLowerCase())
    {
      case "objectid":            return entityObjectIdMap.get(key).get(numGen.getExclusiveRandom(0, entityObjectIdMap.get(key).size()));
      case "string":              return entityStringIdMap.get(key).get(numGen.getExclusiveRandom(0, entityStringIdMap.get(key).size()));
      case "int": case "number":  return entityNumberIdMap.get(key).get(numGen.getExclusiveRandom(0, entityNumberIdMap.get(key).size()));
      default:                    throw new IllegalArgumentException("Reference type " + type + " is not valid.");
    }
  }

  public boolean containsKey(String key, String type)
  {
    switch (type.toLowerCase())
    {
      case "objectid":            return entityObjectIdMap.containsKey(key) && !entityObjectIdMap.get(key).isEmpty();
      case "string":              return entityStringIdMap.containsKey(key) && !entityStringIdMap.get(key).isEmpty();
      case "int": case "number":  return entityNumberIdMap.containsKey(key) && !entityNumberIdMap.get(key).isEmpty();
      default:                    return false;
    }
  }

  public void clear()
  {
    entityStringIdMap.clear();
    entityNumberIdMap.clear();
    entityObjectIdMap.clear();
  }
}
