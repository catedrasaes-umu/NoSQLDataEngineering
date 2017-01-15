package es.um.nosql.schemainference.dbgenerator.test.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	private static final int MIN_INSTANCES = 3;
	private static final int MAX_INSTANCES = 10;

	private JsonNodeFactory factory = JsonNodeFactory.instance;

	private Map<EntityVersion, List<ObjectNode>> mapEV;
	private Random random;

	private ArrayNode lStorage;

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

	/**
	 * Method used to generate a random float and make sure it doesn't end in .00.
	 * @return A random float value.
	 */
	private float getRandomFloat()
	{
		double d = Math.round(random.nextFloat() * 100 * 100d) / 100d;

		if (d == Math.floor(d))
			d += 0.01;

		return (float) d;
	}

	/**
	 * Method used to generate the Json code from the DBSCHEMA.
	 * @param schema The schema.
	 * @return A Json object formatted accordingly.
	 * @throws JsonProcessingException 
	 */
	public String generate(NoSQLSchema schema) throws JsonProcessingException
	{
		mapEV = new HashMap<EntityVersion, List<ObjectNode>>();

		random = new Random();
		lStorage = factory.arrayNode();

		// First run to generate all the primitive types, tuples and references.
		int IDENTIFIER = 0;
		for (Entity entity : schema.getEntities())
		{
			for (EntityVersion eVersion : entity.getEntityversions())
			{
				mapEV.put(eVersion, new ArrayList<ObjectNode>());

				for (int i = 0; i < getRandomBetween(MIN_INSTANCES, MAX_INSTANCES); i++)
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

							if (lBound == 1 && lBound == uBound && random.nextBoolean())
								strObj.put(ref.getName(), String.valueOf(random.nextInt(200)));
							else
							{
								ArrayNode refArray = factory.arrayNode();
								strObj.put(ref.getName(), refArray);

								for (int j = 0; j < getRandomBetween(lBound, uBound); j++)
									refArray.add(String.valueOf(random.nextInt(200)));
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
			case "String": case "string": {strObj.put(name, "value_" + getRandomBetween(1, 200)); break;}
			case "Int": case "int": {strObj.put(name, random.nextInt(200)); break;}
			case "Double": case "double": case "float": case "Float": {strObj.put(name, getRandomFloat()); break;}
			case "Bool": case "bool": case "Boolean": case "boolean": {strObj.put(name, random.nextBoolean()); break;}
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
			case "String": case "string": {arrayObj.add("value_" + getRandomBetween(1, 200)); break;}
			case "Int": case "int": {arrayObj.add(random.nextInt(200)); break;}
			case "Double": case "double": case "float": case "Float": {arrayObj.add(getRandomFloat()); break;}
			case "Bool": case "bool": case "Boolean": case "boolean": {arrayObj.add(random.nextBoolean()); break;}
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
			{
				generatePrimitiveType(arrayObj, ((PrimitiveType)type).getName());
			}
			else if (type instanceof Tuple)
			{
				ArrayNode innerArray = factory.arrayNode();
				arrayObj.add(innerArray);
				generateTuple(innerArray, ((Tuple)type).getElements());
			}
		}
	}
}
