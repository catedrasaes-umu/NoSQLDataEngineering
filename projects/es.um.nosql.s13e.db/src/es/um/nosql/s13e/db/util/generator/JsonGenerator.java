package es.um.nosql.s13e.db.util.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.NoSQLSchema.Type;

public class JsonGenerator
{
  private int MIN_INSTANCES_VARIATION;
  private int MAX_INSTANCES_VARIATION;

  private Map<StructuralVariation, List<ObjectNode>> evMap;
  private Map<String, List<String>> entityIdMap;
  private ArrayNode lStorage;

  private JsonNodeFactory factory = JsonNodeFactory.instance;

  public JsonGenerator()
  {
    MIN_INSTANCES_VARIATION = 3;
    MAX_INSTANCES_VARIATION = 10;
  }

  public String generate(NoSQLSchema schema, int minInstances, int maxInstances) throws Exception
  {
    MIN_INSTANCES_VARIATION = minInstances;
    MAX_INSTANCES_VARIATION = maxInstances;

    return generate(schema);
  }

  public String generate(NoSQLSchema schema) throws Exception
  {
    evMap = new HashMap<StructuralVariation, List<ObjectNode>>();
    entityIdMap = new HashMap<String, List<String>>();

    lStorage = factory.arrayNode();

    // First run to generate all the primitive types and tuples.
    for (EntityClass entity : schema.getEntities())
    {
      entityIdMap.put(entity.getName().toLowerCase(), new ArrayList<String>());
      for (StructuralVariation eVariation : entity.getVariations())
      {
        evMap.put(eVariation, new ArrayList<ObjectNode>());
        int countInstances = getRandomBetween(MIN_INSTANCES_VARIATION, MAX_INSTANCES_VARIATION);

        for (int i = 0; i < countInstances; i++)
        {
          ObjectNode strObj = factory.objectNode();

          for (Property property : eVariation.getProperties())
          {
            if (property instanceof Attribute)
            {
              Attribute attr = (Attribute)property;
              if (attr.getType() instanceof PrimitiveType)
                generatePrimitiveType(strObj, attr.getName(), ((PrimitiveType)attr.getType()).getName());
              else if (attr.getType() instanceof PTuple)
                generatePTuple(strObj, attr.getName(), ((PTuple)attr.getType()).getElements());
            }
          }

          // We will override the _id and the type parameters...
          if (entity.isRoot())
          {
            strObj.put("_id", new ObjectId().toString());
            strObj.put("_type", entity.getName());
            lStorage.add(strObj);
            entityIdMap.get(entity.getName().toLowerCase()).add(strObj.get("_id").asText());
          }

          evMap.get(eVariation).add(strObj);
        }
      }
    }

    // Second run to generate the references and aggregates since now all the variations and instances exist.
    for (EntityClass entity : schema.getEntities())
      for (StructuralVariation eVariation : entity.getVariations())
        for (ObjectNode strObj : evMap.get(eVariation))
        {
          for (Property property : eVariation.getProperties())
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

                for (int j = 0; j < getRandomBetween(lBound, uBound); j++)
                  refArray.add(getRandomRefId(ref.getName()));
              }
            }
            if (property instanceof Aggregate)
            {
              Aggregate aggr = (Aggregate)property;

              if (aggr.getLowerBound() == 1 && aggr.getUpperBound() == 1)
                strObj.put(aggr.getName(), getRandomAggr(aggr.getAggregates().get(0)));
              else
              {
                ArrayNode array = factory.arrayNode();
                strObj.put(aggr.getName(), array);
                // We keep all the aggregated variations in a banned list because we won't add them to the database as standalone objects.
                for (StructuralVariation aggrEV : aggr.getAggregates())
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
        return entityIdMap.get(eName).get(getRandomBetween(0, entityIdMap.get(eName).size() - 1));

    throw new Exception("Reference not found: " + name);
  }

  private ObjectNode getRandomAggr(StructuralVariation eVariation)
  {
    return evMap.get(eVariation).get(getRandomBetween(0, evMap.get(eVariation).size() - 1));
  }

  private int getRandomBetween(int minValue, int maxValue)
  {
    return (new Random()).nextInt(maxValue + 1 - minValue) + minValue;
  }

  private float getRandomDouble()
  {
    double d = Math.round(ThreadLocalRandom.current().nextDouble() * 100 * 100d) / 100d;

    if (d == Math.floor(d))
      d += 0.01;

    return (float) d;
  }

  private String getRandomString()
  {
    return "value_" + getRandomInt();
  }

  private int getRandomInt()
  {
    return ThreadLocalRandom.current().nextInt(0, 1000000);
  }

  private boolean getRandomBoolean()
  {
    return ThreadLocalRandom.current().nextBoolean();
  }

  /**
   * Method used to generate a primitive type and insert it in a JsonObject.
   * @param strObj The JsonObject in which the type is being inserted.
   * @param name The type key.
   * @param type The type to generate.
   */
  private void generatePrimitiveType(ObjectNode strObj, String name, String type)
  {
    switch (type)
    {
    case "String": case "string": {strObj.put(name, getRandomString()); break;}
    case "Int": case "int": case "Number": case "number": {strObj.put(name, getRandomInt()); break;}
    case "Double": case "double": case "float": case "Float": {strObj.put(name, getRandomDouble()); break;}
    case "Bool": case "bool": case "Boolean": case "boolean": {strObj.put(name, getRandomBoolean()); break;}
    }
  }

  /**
   * Method used to generate a primitive type and insert it in a JsonArray.
   * @param arrayObj The JsonArray in which the type is being stored.
   * @param type The type to generate.
   */
  private void generatePrimitiveType(ArrayNode arrayObj, String type)
  {
    switch (type)
    {
    case "String": case "string": {arrayObj.add(getRandomString()); break;}
    case "Int": case "int": case "Number": case "number": {arrayObj.add(getRandomInt()); break;}
    case "Double": case "double": case "float": case "Float": {arrayObj.add(getRandomDouble()); break;}
    case "Bool": case "bool": case "Boolean": case "boolean": {arrayObj.add(getRandomBoolean()); break;}
    }
  }

  /**
   * Method used to generate a tuple type and insert it in a JsonObject.
   * @param strObj The JsonObject in which the type is being inserted.
   * @param name The tuple key.
   * @param elements The tuple to generate.
   */
  private void generatePTuple(ObjectNode strObj, String name, List<Type> elements)
  {
    ArrayNode array = factory.arrayNode();
    strObj.put(name, array);

    for (Type type : elements)
    {
      if (type instanceof PrimitiveType)
        generatePrimitiveType(array, ((PrimitiveType)type).getName());
      else if (type instanceof PTuple)
      {
        ArrayNode innerArray = factory.arrayNode();
        array.add(innerArray);
        generateTuple(innerArray, ((PTuple)type).getElements());
      }
    }
  }

  /**
   * Method used to generate a tuple type and insert it in a JsonArray.
   * @param arrayObj The JsonArray in which the type is being inserted.
   * @param elements The tuple to generate.
   */
  private void generateTuple(ArrayNode arrayObj, List<Type> elements)
  {
    for (Type type : elements)
    {
      if (type instanceof PrimitiveType)
        generatePrimitiveType(arrayObj, ((PrimitiveType)type).getName());
      else if (type instanceof PTuple)
      {
        ArrayNode innerArray = factory.arrayNode();
        arrayObj.add(innerArray);
        generateTuple(innerArray, ((PTuple)type).getElements());
      }
    }
  }
}
