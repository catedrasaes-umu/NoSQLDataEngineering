package es.um.nosql.schemainference.dbgenerator.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.schemainference.NoSQLSchema.Aggregate;
import es.um.nosql.schemainference.NoSQLSchema.Attribute;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.NoSQLSchema.Reference;
import es.um.nosql.schemainference.NoSQLSchema.Tuple;
import es.um.nosql.schemainference.NoSQLSchema.Type;

public class JsonGenerator
{
    private int MIN_INSTANCES_VERSION;
    private int MAX_INSTANCES_VERSION;

    private int zeroFactor;
    private int currentId;

    private Map<EntityVersion, List<ObjectNode>> evMap;
    private Map<String, List<String>> entityIdMap;
    private ArrayNode lStorage;

    private JsonNodeFactory factory = JsonNodeFactory.instance;

    public JsonGenerator()
    {
        MIN_INSTANCES_VERSION = 3;
        MAX_INSTANCES_VERSION = 10;

        zeroFactor = 0;
        currentId = 0;
    }

    private void setIdGeneratorFactor(NoSQLSchema schema)
    {
        int maxObjects = schema.getEntities().size();

        for (Entity entity : schema.getEntities())
            maxObjects += entity.getEntityversions().size();

        maxObjects *= MAX_INSTANCES_VERSION;
        zeroFactor = Integer.toString(maxObjects).length();
    }

    private String getId()
    {
        return String.format("%0" + zeroFactor + "d", ++currentId);
    }

    public String generate(NoSQLSchema schema, int minInstances, int maxInstances) throws Exception
    {
        MIN_INSTANCES_VERSION = minInstances;
        MAX_INSTANCES_VERSION = maxInstances;

        return generate(schema);
    }

    public String generate(NoSQLSchema schema) throws Exception
    {
    	evMap = new HashMap<EntityVersion, List<ObjectNode>>();
    	entityIdMap = new HashMap<String, List<String>>();
    	List<ObjectNode> bannedEVList = new ArrayList<ObjectNode>();

        lStorage = factory.arrayNode();

        setIdGeneratorFactor(schema);
        currentId = 0;

        // First run to generate all the primitive types and tuples.
        for (Entity entity : schema.getEntities())
            for (EntityVersion eVersion : entity.getEntityversions())
            {
            	evMap.put(eVersion, new ArrayList<ObjectNode>());
            	entityIdMap.put(entity.getName(), new ArrayList<String>());

                for (int i = 0; i < getRandomBetween(MIN_INSTANCES_VERSION, MAX_INSTANCES_VERSION); i++)
                {
                    ObjectNode strObj = factory.objectNode();

                    for (Property property : eVersion.getProperties())
                    {
                        if (property instanceof Attribute)
                        {
                            Attribute attr = (Attribute)property;
                            if (attr.getType() instanceof PrimitiveType)
                                generatePrimitiveType(strObj, attr.getName(), ((PrimitiveType)attr.getType()).getName());
                            else if (attr.getType() instanceof Tuple)
                                generateTuple(strObj, attr.getName(), ((Tuple)attr.getType()).getElements());
                        }
                    }
                    // We will override the _id and the type parameters...
                    strObj.put("_id", getId());
                    if (eVersion.isRoot())
                    	strObj.put("type", entity.getName());

                    evMap.get(eVersion).add(strObj);
                    entityIdMap.get(entity.getName()).add(strObj.get("_id").asText());
                }
            }

        // Second run to generate the references and aggregates since now all the versions and instances exist.
        for (Entity entity : schema.getEntities())
            for (EntityVersion eVersion : entity.getEntityversions())
                for (ObjectNode strObj : evMap.get(eVersion))
                {
                    for (Property property : eVersion.getProperties())
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
                            ArrayNode array = factory.arrayNode();
                            strObj.put(aggr.getName(), array);
                            // We keep all the aggregated versions in a banned list because we won't add them to the database as standalone objects.
                            for (EntityVersion aggrEV : aggr.getRefTo())
                            {
                            	ObjectNode aggrNode = getRandomAggr(aggrEV);
                            	bannedEVList.addAll(evMap.get(aggrEV));
                                array.add(aggrNode);
                            }
                        }
                    }
                }

        // A third run is required to add the actual JSON objects to the array, now that we know what are the aggregated EVs which we will not add
        for (Entity entity : schema.getEntities())
        	for (EntityVersion eVersion : entity.getEntityversions())
        		for (ObjectNode strObj : evMap.get(eVersion))
        			if (!bannedEVList.contains(strObj))
        				lStorage.add(strObj);

        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lStorage);
    }

    private String getRandomRefId(String name) throws Exception
    {
        for (String eName : entityIdMap.keySet())
            if (eName.toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(eName.toLowerCase()))
                return entityIdMap.get(eName).get(getRandomBetween(0, entityIdMap.get(eName).size() - 1));

        throw new Exception("Reference not found: " + name);
    }

    private ObjectNode getRandomAggr(EntityVersion eVersion)
    {
    	return evMap.get(eVersion).get(getRandomBetween(0, evMap.get(eVersion).size() - 1));
    }

    private int getRandomBetween(int minValue, int maxValue)
    {
        return (new Random()).nextInt(maxValue + 1 - minValue) + minValue;
    }

    private float getRandomFloat()
    {
        double d = Math.round(ThreadLocalRandom.current().nextFloat() * 100 * 100d) / 100d;

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
            case "Double": case "double": case "float": case "Float": {strObj.put(name, getRandomFloat()); break;}
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
            case "Double": case "double": case "float": case "Float": {arrayObj.add(getRandomFloat()); break;}
            case "Bool": case "bool": case "Boolean": case "boolean": {arrayObj.add(getRandomBoolean()); break;}
        }
    }

    /**
     * Method used to generate a tuple type and insert it in a JsonObject.
     * @param strObj The JsonObject in which the type is being inserted.
     * @param name The tuple key.
     * @param elements The tuple to generate.
     */
    private void generateTuple(ObjectNode strObj, String name, List<Type> elements)
    {
        ArrayNode array = factory.arrayNode();
        strObj.put(name, array);

        for (Type type : elements)
        {
            if (type instanceof PrimitiveType)
                generatePrimitiveType(array, ((PrimitiveType)type).getName());
            else if (type instanceof Tuple)
            {
                ArrayNode innerArray = factory.arrayNode();
                array.add(innerArray);
                generateTuple(innerArray, ((Tuple)type).getElements());
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
            else if (type instanceof Tuple)
            {
                ArrayNode innerArray = factory.arrayNode();
                arrayObj.add(innerArray);
                generateTuple(innerArray, ((Tuple)type).getElements());
            }
        }
    }
}
