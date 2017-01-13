package es.um.nosql.schemainference.dbgenerator.test.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.schemainference.NoSQLSchema.Aggregate;
import es.um.nosql.schemainference.NoSQLSchema.Attribute;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType;
import es.um.nosql.schemainference.NoSQLSchema.Reference;
import es.um.nosql.schemainference.NoSQLSchema.Tuple;

/**
 * Class used to generate a random JSON file and the associated DBSCHEMA file.
 * @author Alberto Hernández Chillón
 */
public class ObjGenerator
{
	private static ObjGenerator theInstance = null;

	private static final String[] ENTITIES = {"Book", "Author", "Journal", "Publisher", "Content"};
	private static final int MIN_INSTANCES = 1;
	private static final int MAX_INSTANCES = 10;
	private static final int MIN_ENTITY_VERSIONS = 3;
	private static final int MAX_ENTITY_VERSIONS = 10;
	private static final int MIN_STRING_ATTR = 1;
	private static final int MAX_STRING_ATTR = 5;
	private static final int MIN_INT_ATTR = 0;
	private static final int MAX_INT_ATTR = 3;
	private static final int MIN_FLOAT_ATTR = 0;
	private static final int MAX_FLOAT_ATTR = 2;
	private static final int MIN_BOOL_ATTR = 0;
	private static final int MAX_BOOL_ATTR = 2;
	private static final int MIN_TUPLE_ATTR = 0;
	private static final int MAX_TUPLE_ATTR = 2;
	private static final int MIN_AGGR = 0;
	private static final int MAX_AGGR = 3;
	private static final int MIN_REF = 0;
	private static final int MAX_REF = 3;

	private NoSQLSchema schema;
	private Map<String, EntityVersion> mapEV;
	private Map<String, Entity> mapE;
	private Random random;

	private JsonNodeFactory factory = JsonNodeFactory.instance;
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
	 * Method used to return the Singleton ObjGenerator object.
	 * @return The ObjGenerator.
	 */
	public static ObjGenerator getInstance()
	{
		if (theInstance == null)
			theInstance = new ObjGenerator();

		return theInstance;
	}

	/**
	 * Default constructor.
	 */
	private ObjGenerator()
	{
		schema = NoSQLSchemaFactory.eINSTANCE.createNoSQLSchema();
		mapE = new HashMap<String, Entity>();
		mapEV = new HashMap<String, EntityVersion>();

		random = new Random();
		lStorage = factory.arrayNode();

		for (String entity : ENTITIES)
		{
			Entity oEntity = NoSQLSchemaFactory.eINSTANCE.createEntity();
			oEntity.setName(entity);
			schema.getEntities().add(oEntity);
			mapE.put(entity, oEntity);
		}

		int IDENTIFIER = 0;
		for (String entity : ENTITIES)
		{
			IDENTIFIER = 0;
			for (int i = 1; i < getRandomBetween(MIN_ENTITY_VERSIONS, MAX_ENTITY_VERSIONS) + 1; i++)
			{
				EntityVersion oev = NoSQLSchemaFactory.eINSTANCE.createEntityVersion();
				oev.setVersionId(++IDENTIFIER);
				oev.setRoot(true);
				mapE.get(entity).getEntityversions().add(oev);

				ObjectNode strObj = factory.objectNode();

				strObj.put("_id", IDENTIFIER);

				// Generate "type" (entityName) attribute.
				generateType(oev, strObj, entity);

				// Generate Primitive Types.
				generateStrings(oev, strObj);
				generateInts(oev, strObj);
				generateFloats(oev, strObj);
				generateBools(oev, strObj);

				// Generate Tuples.
				generateTuples(oev, strObj);

				// Generate References.
				for (int j = 0; j < getRandomBetween(MIN_REF, MAX_REF); j++)
				{
					Entity e = mapE.get(ENTITIES[random.nextInt(ENTITIES.length)]);

					Reference ref = NoSQLSchemaFactory.eINSTANCE.createReference();
					ref.setName(e.getName() + "_id_reference");

					boolean randomVal = random.nextBoolean();

					if (randomVal)
					{
						ref.setLowerBound(1);
						ref.setUpperBound(1);
					}
					else
					{
						ref.setLowerBound(random.nextInt(2));
						ref.setUpperBound(random.nextInt(2) + 1);
					}
					ref.setRefTo(e);
					oev.getProperties().add(ref);

					if (randomVal)
						strObj.put(e.getName() + "_id_reference", String.valueOf(getRandomBetween(1000, 2000)));
					else
					{
						ArrayNode refArray = factory.arrayNode();
						refArray.add(String.valueOf(getRandomBetween(1000, 2000)));
						strObj.put(e.getName() + "_id_reference", refArray);
					}
				}

				// Generate Aggregates.
				generateAggregates(oev, strObj);

				mapEV.put(entity + "_" + IDENTIFIER, oev);
				lStorage.add(strObj);

				for (int j = 1; j < getRandomBetween(MIN_INSTANCES, MAX_INSTANCES); j++)
					lStorage.add(strObj);
			}
		}
	}

	/**
	 * GET method.
	 * @param name The name to be given to the schema.
	 * @return A NoSQLSchema object.
	 */
	public NoSQLSchema getSchema(String name)
	{
		schema.setName(name);

		return schema;
	}

	/**
	 * ToString method.
	 */
	public String toString()
	{
		try
		{
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lStorage);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method used to generate a "Type" attribute and associate it to the JSON object and the EntityVersion.
	 * @param oev The EntityVersion to which the type is being associated.
	 * @param strObj The JSON object to which the "type" field is being added.
	 * @param type The type to be given.
	 */
	private void generateType(EntityVersion oev, ObjectNode strObj, String type)
	{
		Attribute attribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
		PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
		attribute.setName("type");
		pType.setName(type);
		attribute.setType(pType);
		oev.getProperties().add(attribute);

		strObj.put(attribute.getName(), type);
	}

	/**
	 * Method used to generate a "String" attribute and associate it to the JSON object and the EntityVersion.
	 * @param oev The EntityVersion to which the string is being associated.
	 * @param strObj The JSON object to which the "string" field is being added.
	 */
	private void generateStrings(EntityVersion oev, ObjectNode strObj)
	{
		for (int j = 0; j < getRandomBetween(MIN_STRING_ATTR, MAX_STRING_ATTR); j++)
		{
			Attribute attribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
			PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
			attribute.setName("atStr_" + j);
			pType.setName("string");
			attribute.setType(pType);
			oev.getProperties().add(attribute);

			strObj.put(attribute.getName(), "value_" + j);
		}
	}

	/**
	 * Method used to generate an "Int" attribute and associate it to the JSON object and the EntityVersion.
	 * @param oev The EntityVersion to which the int is being associated.
	 * @param strObj The JSON object to which the "int" field is being added.
	 */
	private void generateInts(EntityVersion oev, ObjectNode strObj)
	{
		for (int j = 0; j < getRandomBetween(MIN_INT_ATTR, MAX_INT_ATTR); j++)
		{
			Attribute attribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
			PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
			attribute.setName("atInt_" + j);
			pType.setName("int");
			attribute.setType(pType);
			oev.getProperties().add(attribute);

			strObj.put(attribute.getName(), random.nextInt(50));
		}
	}

	/**
	 * Method used to generate a "Float" attribute and associate it to the JSON object and the EntityVersion.
	 * @param oev The EntityVersion to which the float is being associated.
	 * @param strObj The JSON object to which the "float" field is being added.
	 */
	private void generateFloats(EntityVersion oev, ObjectNode strObj)
	{
		for (int j = 0; j < getRandomBetween(MIN_FLOAT_ATTR, MAX_FLOAT_ATTR); j++)
		{
			Attribute attribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
			PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
			attribute.setName("atFloat_" + j);
			pType.setName("float");
			attribute.setType(pType);
			oev.getProperties().add(attribute);

			strObj.put(attribute.getName(), (float)Math.round(random.nextFloat() * 100 * 100d) / 100d);
		}
	}

	/**
	 * Method used to generate a "Bool" attribute and associate it to the JSON object and the EntityVersion.
	 * @param oev The EntityVersion to which the bool is being associated.
	 * @param strObj The JSON object to which the "bool" field is being added.
	 */
	private void generateBools(EntityVersion oev, ObjectNode strObj)
	{
		for (int j = 0; j < getRandomBetween(MIN_BOOL_ATTR, MAX_BOOL_ATTR); j++)
		{
			Attribute attribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
			PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
			attribute.setName("atBool_" + j);
			pType.setName("bool");
			attribute.setType(pType);
			oev.getProperties().add(attribute);

			strObj.put(attribute.getName(), random.nextBoolean());
		}
	}

	/**
	 * Method used to generate a "Tuple" attribute and associate it to the JSON object and the EntityVersion.
	 * @param oev The EntityVersion to which the tuple is being associated.
	 * @param strObj The JSON object to which the "tuple" field is being added.
	 */
	private void generateTuples(EntityVersion oev, ObjectNode strObj)
	{
		for (int j = 0; j < getRandomBetween(MIN_TUPLE_ATTR, MAX_TUPLE_ATTR); j++)
		{
			Attribute attribute = NoSQLSchemaFactory.eINSTANCE.createAttribute();
			Tuple tuple = NoSQLSchemaFactory.eINSTANCE.createTuple();
			attribute.setName("atTuple_" + j);
			attribute.setType(tuple);

			ArrayNode array = factory.arrayNode();
			for (int k = 0; k < 5; k++)
				switch(random.nextInt(5))
				{
					case 0:
					{
						if (random.nextBoolean())
						{
							PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
							pType.setName("string");
							tuple.getElements().add(pType);
							array.add("strVal_" + k);
						}
						break;
					}
					case 1:
					{
						if (random.nextBoolean())
						{
							PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
							pType.setName("int");
							tuple.getElements().add(pType);
							array.add(random.nextInt(50));
						}
						break;
					}
					case 2:
					{
						if (random.nextBoolean())
						{
							PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
							pType.setName("float");
							tuple.getElements().add(pType);
							array.add((float)Math.round(random.nextFloat() * 100 * 100d) / 100d);
						}
						break;
					}
					case 3:
					{
						if (random.nextBoolean())
						{
							PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
							pType.setName("bool");
							tuple.getElements().add(pType);
							array.add(random.nextBoolean());
						}
						break;
					}
					case 4:
					{
						if (random.nextBoolean())
						{
							Tuple anotherTuple = NoSQLSchemaFactory.eINSTANCE.createTuple();
							tuple.getElements().add(anotherTuple);

							ArrayNode arrayAux = factory.arrayNode();
							array.add(arrayAux);
							for (int l = 0; l < random.nextInt(3) + 1; l++)
							{
								PrimitiveType pType = NoSQLSchemaFactory.eINSTANCE.createPrimitiveType();
								pType.setName("int");
								anotherTuple.getElements().add(pType);

								arrayAux.add(random.nextInt(50));
							}
						}
						break;
					}
				}
			if (array.size() > 0)
			{
				oev.getProperties().add(attribute);
				strObj.put(attribute.getName(), array);
			}
		}
	}

	/**
	 * Method used to generate a "Aggregate" attribute and associate it to the JSON object and the EntityVersion.
	 * @param oev The EntityVersion to which the aggregation is being associated.
	 * @param strObj The JSON object to which the "Aggregate" field is being added.
	 */
	private void generateAggregates(EntityVersion oev, ObjectNode strObj)
	{
		for (int j = 0; j < getRandomBetween(MIN_AGGR, MAX_AGGR) && !mapEV.isEmpty(); j++)
		{
			Aggregate aggr = NoSQLSchemaFactory.eINSTANCE.createAggregate();
			aggr.setName("aggregates_" + j);

			// Half the times a 1..1 aggregate is added as a simple item instead of an array.
			if (random.nextBoolean())
			{
				aggr.setLowerBound(1);
				aggr.setUpperBound(1);

				String evId = (String)mapEV.keySet().toArray()[random.nextInt(mapEV.size())];
				EntityVersion ev = mapEV.get(evId);
				ev.setRoot(false);
				aggr.getRefTo().add(ev);

				for (JsonNode jElem : lStorage)
					if (jElem.get("_id").asInt() == (ev.getVersionId()))
					{
						strObj.put(aggr.getName(), jElem);
						break;
					}
			}
			else
			{
				aggr.setLowerBound(random.nextInt(2));
				aggr.setUpperBound(random.nextInt(2) + 2);

				ArrayNode aggrArray = factory.arrayNode();

				for (int k = 1; k < getRandomBetween(aggr.getLowerBound(), aggr.getUpperBound()) && k < mapEV.size(); k++)
				{
					String evId = (String)mapEV.keySet().toArray()[random.nextInt(mapEV.size())];
					EntityVersion ev = mapEV.get(evId);
					ev.setRoot(false);
					aggr.getRefTo().add(ev);

					for (JsonNode jElem : lStorage)
						if (jElem.get("_id").asInt() == (ev.getVersionId()))
						{
							aggrArray.add(jElem);
							break;
						}
				}

				if (aggrArray.size() > 0)
				{
					oev.getProperties().add(aggr);
					strObj.put(aggr.getName(), aggrArray);
				}
			}
		}
	}
}
