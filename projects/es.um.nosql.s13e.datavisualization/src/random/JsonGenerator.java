package random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.NoSQLSchema.Type;

/**
 * Class used to generate a random JSON file from a DBSCHEMA model.
 * @author Alberto Hernández Chillón
 */
public class JsonGenerator
{
	private static final int MIN_INSTANCES = 3;
	private static final int MAX_INSTANCES = 10;

	private Map<EntityVariation, List<JsonObject>> mapEV;
	private Random random;

	private JsonArray lStorage;

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
	 */
	public String generate(NoSQLSchema schema)
	{
		mapEV = new HashMap<EntityVariation, List<JsonObject>>();

		random = new Random();
		lStorage = new JsonArray();

		// First run to generate all the primitive types, tuples and references.
		int IDENTIFIER = 0;
		for (Entity entity : schema.getEntities())
		{
			for (EntityVariation eVariation : entity.getEntityVariations())
			{
				mapEV.put(eVariation, new ArrayList<JsonObject>());

				for (int i = 0; i < getRandomBetween(MIN_INSTANCES, MAX_INSTANCES); i++)
				{
					JsonObject strObj = new JsonObject();
					strObj.addProperty("_id", ++IDENTIFIER);
					strObj.addProperty("type", entity.getName());

					for (Property property : eVariation.getProperties())
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
								strObj.addProperty(ref.getName(), String.valueOf(random.nextInt(200)));
							else
							{
								JsonArray refArray = new JsonArray();
								strObj.add(ref.getName(), refArray);

								for (int j = 0; j < getRandomBetween(lBound, uBound); j++)
									refArray.add(String.valueOf(random.nextInt(200)));
							}
						}
					}
					mapEV.get(eVariation).add(strObj);

					lStorage.add(strObj);
				}
			}
		}

		// Second run to generate the aggregates since now all the variations and instances exist.
		for (Entity entity : schema.getEntities())
			for (EntityVariation eVariation : entity.getEntityVariations())
				for (JsonObject strObj : mapEV.get(eVariation))
				{
					for (Property property : eVariation.getProperties())
					{
						if (property instanceof Aggregate)
						{
							Aggregate aggr = (Aggregate)property;
							JsonArray array = new JsonArray();
							strObj.add(aggr.getName(), array);

							for (EntityVariation aggrEV : aggr.getRefTo())
								array.add(mapEV.get(aggrEV).get(0));
						}
					}
				}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		return gson.toJson(lStorage);
	}

	/**
	 * Method used to generate a primitive type and insert it in a JsonObject.
	 * @param strObj The JsonObject in which the type is being inserted.
	 * @param name The type key.
	 * @param type The type to generate.
	 */
	private void generatePrimitiveType(JsonObject strObj, String name, String type)
	{
		switch (type)
		{
			case "String": case "string": {strObj.addProperty(name, "value_" + getRandomBetween(1, 200)); break;}
			case "Int": case "int": {strObj.addProperty(name, random.nextInt(200)); break;}
			case "Double": case "double": case "float": case "Float": {strObj.addProperty(name, getRandomFloat()); break;}
			case "Bool": case "bool": case "Boolean": case "boolean": {strObj.addProperty(name, random.nextBoolean()); break;}
		}
	}

	/**
	 * Method used to generate a primitive type and insert it in a JsonArray.
	 * @param arrayObj The JsonArray in which the type is being stored.
	 * @param type The type to generate.
	 */
	private void generatePrimitiveType(JsonArray arrayObj, String type)
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
	private void generateTuple(JsonObject strObj, String name, List<Type> elements)
	{
		JsonArray array = new JsonArray();
		strObj.add(name, array);

		for (Type type : elements)
		{
			if (type instanceof PrimitiveType)
				generatePrimitiveType(array, ((PrimitiveType)type).getName());
			else if (type instanceof Tuple)
			{
				JsonArray innerArray = new JsonArray();
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
	private void generateTuple(JsonArray arrayObj, List<Type> elements)
	{
		for (Type type : elements)
		{
			if (type instanceof PrimitiveType)
			{
				generatePrimitiveType(arrayObj, ((PrimitiveType)type).getName());
			}
			else if (type instanceof Tuple)
			{
				JsonArray innerArray = new JsonArray();
				arrayObj.add(innerArray);
				generateTuple(innerArray, ((Tuple)type).getElements());
			}
		}
	}
}
