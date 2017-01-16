package es.um.nosql.schemainference.dbgenerator.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
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

/**
 * Class used to generate a random JSON file from a NOSQLSCHEMA model.
 * @author Alberto Hernández Chillón
 */
public class JsonGenerator
{
	private int MIN_INSTANCES_VERSION;
	private int MAX_INSTANCES_VERSION;

	private int MIN_INT_VALUE;
	private int MAX_INT_VALUE;

	private int MIN_STR_VALUE;
	private int MAX_STR_VALUE;

	private JsonNodeFactory factory = JsonNodeFactory.instance;

	private Map<EntityVersion, List<ObjectNode>> mapEV;

	private ArrayNode lStorage;

	public JsonGenerator()
	{
		MIN_INSTANCES_VERSION = 3;
		MAX_INSTANCES_VERSION = 10;

		MIN_INT_VALUE = 0;
		MAX_INT_VALUE = 200;

		MIN_STR_VALUE = 1;
		MAX_STR_VALUE = 200;
	}

	/**
	 * Method used to get a random value between two values.
	 * @param minValue The minimum value.
	 * @param maxValue The maximum value.
	 * @return A random integer between the two given values.
	 */
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
		return "value_" + ThreadLocalRandom.current().nextInt(MIN_STR_VALUE, MAX_STR_VALUE);
	}

	private int getRandomInt()
	{
		return ThreadLocalRandom.current().nextInt(MIN_INT_VALUE, MAX_INT_VALUE);
	}

	private boolean getRandomBoolean()
	{
		return ThreadLocalRandom.current().nextBoolean();
	}

	public void setRandomIntRange(int minValue, int maxValue)
	{
		MIN_INT_VALUE = 0;
		MAX_INT_VALUE = 200;
	}

	public void setRandomStrRange(int minValue, int maxValue)
	{
		MIN_STR_VALUE = 1;
		MAX_INT_VALUE = 200;
	}

	public String generate(NoSQLSchema schema, int minInstances, int maxInstances) throws JsonProcessingException
	{
		MIN_INSTANCES_VERSION = minInstances;
		MAX_INSTANCES_VERSION = maxInstances;

		return generate(schema);
	}

	public String generate(NoSQLSchema schema) throws JsonProcessingException
	{
		mapEV = new HashMap<EntityVersion, List<ObjectNode>>();
		lStorage = factory.arrayNode();

		// First run to generate all the primitive types, tuples and references.
		int IDENTIFIER = 0;
		for (Entity entity : schema.getEntities())
		{
			for (EntityVersion eVersion : entity.getEntityversions())
			{
				mapEV.put(eVersion, new ArrayList<ObjectNode>());

				for (int i = 0; i < getRandomBetween(MIN_INSTANCES_VERSION, MAX_INSTANCES_VERSION); i++)
				{
					ObjectNode strObj = factory.objectNode();
					strObj.put("id", ++IDENTIFIER);
					strObj.put("type", entity.getName());

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
						if (property instanceof Reference)
						{
							Reference ref = (Reference)property;

							int lBound = ref.getLowerBound() > 0 ? ref.getLowerBound() : 0;
							int uBound = ref.getUpperBound() > 0 ? ref.getUpperBound() : 5;

							if (lBound == 1 && lBound == uBound && getRandomBoolean())
								strObj.put(ref.getName(), String.valueOf(getRandomInt()));
							else
							{
								ArrayNode refArray = factory.arrayNode();
								strObj.put(ref.getName(), refArray);

								for (int j = 0; j < getRandomBetween(lBound, uBound); j++)
									refArray.add(String.valueOf(getRandomInt()));
							}
						}
					}
					mapEV.get(eVersion).add(strObj);

					lStorage.add(strObj);
				}
			}
		}

		// Second run to generate the aggregates since now all the versions and instances exist.
		for (Entity entity : schema.getEntities())
			for (EntityVersion eVersion : entity.getEntityversions())
				for (ObjectNode strObj : mapEV.get(eVersion))
				{
					for (Property property : eVersion.getProperties())
					{
						if (property instanceof Aggregate)
						{
							Aggregate aggr = (Aggregate)property;
							ArrayNode array = factory.arrayNode();
							strObj.put(aggr.getName(), array);

							for (EntityVersion aggrEV : aggr.getRefTo())
								array.add(mapEV.get(aggrEV).get(0));
						}
					}
				}

		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lStorage);
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
			case "Int": case "int": {strObj.put(name, getRandomInt()); break;}
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
			case "Int": case "int": {arrayObj.add(getRandomInt()); break;}
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
