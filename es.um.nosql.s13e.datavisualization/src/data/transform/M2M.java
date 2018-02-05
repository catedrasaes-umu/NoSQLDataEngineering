package data.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import Version_Diff.Version_DiffFactory;
import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.NoSQLSchema.Type;
import data.utils.serializer.NoSQLSchemaSerializer;
import Version_Diff.HasField;
import Version_Diff.HasNotField;
import Version_Diff.NoSQLDifferences;
import Version_Diff.PrimitiveType;
import Version_Diff.AggregateType;
import Version_Diff.TypeDifference;
import Version_Diff.EntityType;
import Version_Diff.HeterogeneousTupleType;
import Version_Diff.TypeHint;
import Version_Diff.HomogeneousTupleType;
import Version_Diff.ReferenceType;
import Version_Diff.FieldType;

/**
 * Class used to transform DBSCHEMA models into DBDIFFERENCES models.
 * @author Alberto Hernández Chillón
 */
public class M2M
{
	/**
	 * Singleton reference.
	 */
	private static M2M instance = null;

	/**
	 * Method used to get the M2M instance.
	 * @return M2M instance.
	 */
	public static M2M getInstance()
	{
		if (instance == null)
			instance = new M2M();

		return instance;
	}

	/**
	 * Default private constructor.
	 */
	private M2M()
	{
	}

	/**
	 * Transform method used to transform NoSQLSchema into a NoSQLDifferences.
	 * @param baseModel The NoSQLSchema object to be transformed.
	 * @return A NoSQLDifferences object.
	 */
	public NoSQLDifferences transform(NoSQLSchema baseModel)
	{
		NoSQLDifferences differenceModel = Version_DiffFactory.eINSTANCE.createNoSQLDifferences();
		differenceModel.setName(baseModel.getName());
		Map<EntityVersion, List<Pair<String, FieldType>>> evMap;

		for (Entity entity : baseModel.getEntities())
		{
			evMap = getEVPropertiesMap(entity);

			for (EntityVersion entityVersion : entity.getEntityversions())
			{
				TypeDifference tDiff = Version_DiffFactory.eINSTANCE.createTypeDifference();
				tDiff.setName(entity.getName() + "_" + entityVersion.getVersionId());
				differenceModel.getHasTypeDifferences().add(tDiff);

				addHasFields(evMap.get(entityVersion), tDiff);
				addHasNotFields(entityVersion, tDiff, evMap);
			}
		}
		return differenceModel;
	}

	/**
	 * Method used to get a map in which properties (name, value) are associated to each EntityVersion of an Entity.
	 * @param baseEntity The Entity containing the EntityVersions to be mapped.
	 * @return A Map<EntityVersion, List<Pair<String, String>>> in which the mapping is stored.
	 */
	private Map<EntityVersion, List<Pair<String, FieldType>>> getEVPropertiesMap(Entity baseEntity)
	{
		Map<EntityVersion, List<Pair<String, FieldType>>> evMap = new HashMap<EntityVersion, List<Pair<String, FieldType>>>();

		for (EntityVersion ev : baseEntity.getEntityversions())
		{
			List<Pair<String, FieldType>> pairList = new ArrayList<Pair<String, FieldType>>();
			evMap.put(ev, pairList);

			for (Property property : ev.getProperties())
			{
				String name = property.getName();
				FieldType type = null;

				if (property instanceof Attribute)
				{
					Type theType = ((Attribute)property).getType();

					if (theType instanceof es.um.nosql.s13e.NoSQLSchema.PrimitiveType)
					{
						if (name.equals("type") || name.equals("Type"))
						{
							type = Version_DiffFactory.eINSTANCE.createEntityType();
							((EntityType)type).setType(((es.um.nosql.s13e.NoSQLSchema.PrimitiveType)theType).getName());
						}
						else
						{
							type = Version_DiffFactory.eINSTANCE.createPrimitiveType();
							((PrimitiveType)type).setType(((es.um.nosql.s13e.NoSQLSchema.PrimitiveType)theType).getName());
						}
					}
					else if (theType instanceof Tuple)
					{
						type = Version_DiffFactory.eINSTANCE.createHeterogeneousTupleType();

						for (Type tupleType : ((Tuple)theType).getElements())
						{
							if (tupleType instanceof es.um.nosql.s13e.NoSQLSchema.PrimitiveType)
								((HeterogeneousTupleType)type).getType().add(((es.um.nosql.s13e.NoSQLSchema.PrimitiveType)tupleType).getName());
							else if (tupleType instanceof Tuple)
								((HeterogeneousTupleType)type).getType().add("Tuple[" + NoSQLSchemaSerializer.getInstance().stringify(((Tuple)tupleType).getElements()) + "]");
						}

						String aType = getAType(((HeterogeneousTupleType)type).getType().get(0));
						if (checkSameTypes(((HeterogeneousTupleType)type).getType(), aType))
						{
							type = Version_DiffFactory.eINSTANCE.createHomogeneousTupleType();
							((HomogeneousTupleType)type).setType(aType);
						}
								
					}
				}
				else if (property instanceof Reference)
				{
					type = Version_DiffFactory.eINSTANCE.createReferenceType();

					((ReferenceType)type).setType(((Reference)property).getRefTo().getName());
					((ReferenceType)type).setLowerBound(((Reference)property).getLowerBound());
					((ReferenceType)type).setUpperBound(((Reference)property).getUpperBound());
				}
				else if (property instanceof Aggregate)
				{
					type = Version_DiffFactory.eINSTANCE.createAggregateType();

					((AggregateType)type).setLowerBound(((Aggregate)property).getLowerBound());
					((AggregateType)type).setUpperBound(((Aggregate)property).getUpperBound());
					for (EntityVersion aggregatedEV : ((Aggregate)property).getRefTo())
						((AggregateType)type).getType().add(((Entity)aggregatedEV.eContainer()).getName() + "_" + String.valueOf(aggregatedEV.getVersionId()));
				}

				pairList.add(new MutablePair<String, FieldType>(name, type));
			}

			boolean typeIsDefined = false;
			for (Pair<String, FieldType> pair : pairList)
				if (pair.getKey().equals("type") && pair.getValue() instanceof EntityType)
				{
					typeIsDefined = true;
					break;
				}
			if (!typeIsDefined)
			{
				FieldType type = Version_DiffFactory.eINSTANCE.createEntityType();
				((EntityType)type).setType(baseEntity.getName());
				pairList.add(new MutablePair<String, FieldType>("type", type));
			}
				
		}
		return evMap;
	}

	/**
	 * Method used to add HasField objects to a TypeDifference object from a propertiesList.
	 * @param propertiesList The propertiesList associated to the EntityVersion whose TypeDifferences are being generated.
	 * @param tDiff A TypeDifference object in which store the HasField objects.
	 */
	private void addHasFields(List<Pair<String, FieldType>> propertiesList, TypeDifference tDiff)
	{
		// Para cada EntityVersion, agregamos sus propiedades como HasValue.
		for (Pair<String, FieldType> propertyPair : propertiesList)
		{
			HasField hField = Version_DiffFactory.eINSTANCE.createHasField();
			hField.setWithName(propertyPair.getLeft());
			hField.setWithType(createNewFieldType(propertyPair.getRight()));

			tDiff.getHints().add(hField);
		}
	}

	/**
	 * Method used to add HasNotField objects from a Map to a TypeDifference object associated with an EntityVersion.
	 * @param theEntityVersion The EntityVersion whose TypeDifferences are being generated.
	 * @param tDiff TypeDifferences whose fields HasNotFields are being generated.
	 * @param evMap The map in which EntityVersions are associated with their Properties (name, value).
	 */
	private void addHasNotFields(EntityVersion theEntityVersion, TypeDifference tDiff, Map<EntityVersion, List<Pair<String, FieldType>>> evMap)
	{
		boolean propertyFound = false;

		for (EntityVersion evInList : evMap.keySet())
		{
			// And we add other EntityVersion properties as HasNotValue.
			if (evInList != theEntityVersion)
			{
				for (Pair<String, FieldType> pairProperty : evMap.get(evInList))
				{
					for (TypeHint hint : tDiff.getHints())
					{
						// Except if an EntityVersion has a {name, value} property equals to another's.
						// Or the property was already added as HasNotField.
						if (hint.getWithName().equals(pairProperty.getLeft()) && sameTypeHints(hint.getWithType(), pairProperty.getRight()))
						{
							propertyFound = true;
							break;
						}
					}

					// We can't do this step inside the loop because we are modifying the collection we are iterating.
					if (!propertyFound)
					{
						HasNotField hNotField = Version_DiffFactory.eINSTANCE.createHasNotField();
						hNotField.setWithName(pairProperty.getLeft());
						hNotField.setWithType(createNewFieldType(pairProperty.getRight()));

						tDiff.getHints().add(hNotField);
					}

					propertyFound = false;
				}
			}
		}
	}

	/**
	 * Method used to check if two WithTypes are equal. The result will depend on the WithType subtypes.
	 * @param type1 The type1 to be checked.
	 * @param type2 The type2 to be checked.
	 * @return True if the two types are equal, false otherwise.
	 */
	private boolean sameTypeHints(FieldType type1, FieldType type2)
	{
		if (!type1.getClass().equals(type2.getClass()))
			return false;

		// Two primitive types are equal if their types are exactly equal.
		if (type1 instanceof PrimitiveType)
			return ((PrimitiveType)type1).getType().equals(((PrimitiveType)type2).getType());

		// Two entities are equal if their names are equal.
		if (type1 instanceof EntityType)
			return ((EntityType)type1).getType().equals(((EntityType)type2).getType());

		// Two homogeneous tuples are equal if their type is equal.
		if (type1 instanceof HomogeneousTupleType)
			return ((HomogeneousTupleType)type1).getType().equals(((HomogeneousTupleType)type2).getType()); 

		// Two heterogeneous tuples are equal if their types are equal one by one.
		if (type1 instanceof HeterogeneousTupleType)
		{
			HeterogeneousTupleType type11 = (HeterogeneousTupleType)type1;
			HeterogeneousTupleType type22 = (HeterogeneousTupleType)type2;

			return type11.getType().equals(type22.getType());
		}

		// Two aggregates are equal if they are of the same size and they are equal aggregate by aggregate. Cardinalities are not taken into account.
		if (type1 instanceof AggregateType)
		{
			AggregateType type11 = (AggregateType)type1;
			AggregateType type22 = (AggregateType)type2;

			return type11.getType().equals(type22.getType());
		}

		// Two references are equal if their types are equal. Cardinalities are not taken into account.
		if (type1 instanceof ReferenceType)
		{
			ReferenceType type11 = (ReferenceType)type1;
			ReferenceType type22 = (ReferenceType)type2;

			return type11.getType().equals(type22.getType());
		}

		return false;
	}

	/**
	 * Method used to clone a WithType object.
	 * @param type The type to be cloned.
	 * @return A new WithType object.
	 */
	private FieldType createNewFieldType(FieldType type)
	{

		if (type instanceof PrimitiveType)
		{
			PrimitiveType newType = Version_DiffFactory.eINSTANCE.createPrimitiveType();
			newType.setType(((PrimitiveType)type).getType());
			return newType;
		}
		else if (type instanceof EntityType)
		{
			EntityType newType = Version_DiffFactory.eINSTANCE.createEntityType();
			newType.setType(((EntityType)type).getType());
			return newType;
		}
		else if (type instanceof HomogeneousTupleType)
		{
			HomogeneousTupleType newType = Version_DiffFactory.eINSTANCE.createHomogeneousTupleType();
			newType.setType(((HomogeneousTupleType)type).getType());
			return newType;
		}
		else if (type instanceof HeterogeneousTupleType)
		{
			HeterogeneousTupleType newType = Version_DiffFactory.eINSTANCE.createHeterogeneousTupleType();
			newType.getType().addAll(((HeterogeneousTupleType)type).getType());
			return newType;
		}
		else if (type instanceof AggregateType)
		{
			AggregateType newType = Version_DiffFactory.eINSTANCE.createAggregateType();
			newType.setLowerBound(((AggregateType)type).getLowerBound());
			newType.setUpperBound(((AggregateType)type).getUpperBound());
			newType.getType().addAll(((AggregateType)type).getType());
			return newType;
		}
		else if (type instanceof ReferenceType)
		{
			ReferenceType newType = Version_DiffFactory.eINSTANCE.createReferenceType();
			newType.setLowerBound(((ReferenceType)type).getLowerBound());
			newType.setUpperBound(((ReferenceType)type).getUpperBound());
			newType.setType(((ReferenceType)type).getType());
			return newType;
		}

		return null;
	}

	/**
	 * Method used to get the first type of a given Tuple content in a string.
	 * @param type The Tuple content.
	 * @return A type in a string.
	 */
	private String getAType(String type)
	{
		String result = type;

		if (result.startsWith("Tuple["))
		{
			int startIndex = 6;
			int endIndex = result.length();

			if (result.contains(";"))
				endIndex = result.indexOf(";");
			else
				if (result.contains("]"))
					endIndex = result.indexOf("]");

			return getAType(result.substring(startIndex, endIndex));
		}

		return result;
	}

	/**
	 * Method used to check if all elements of an array are of the same type.
	 * @param typeList The type list to be checked.
	 * @param theType The type to check.
	 * @return True if all elements are of the same type as TheType or false otherwise.
	 */
	private boolean checkSameTypes(List<String> typeList, String theType)
	{
		for (String typeInList : typeList)
		{
			if (typeInList.startsWith("Tuple["))
			{
				List<String> eList = new ArrayList<String>();
				fillTupleList(eList, typeInList.substring(6, typeInList.length() - 1));

				if (!checkSameTypes(eList, theType))
					return false;
			}
			else if (!typeInList.equals(theType))
				return false;		
		}

		return true;
	}

	/**
	 * Method used to parse a tuple contained in a string into a list of strings, one for each element.
	 * @param list The list to be filled.
	 * @param typeChain The tuple chain to be parsed.
	 */
	private void fillTupleList(List<String> list, String typeChain)
	{
		String types = typeChain;

		if (types.startsWith(";"))
			types = types.substring(1);

		if (types.startsWith("Tuple["))
		{
			String auxVar = types.substring(6);
			int keyBalance = 1;
			boolean keepLooping = true;

			for (int i = 0; i < auxVar.length() && keepLooping; i++)
			{
				switch (String.valueOf(auxVar.charAt(i)))
				{
					case "[": {keyBalance++; break;}
					case "]": {keyBalance--; break;}
				}
				if (keyBalance == 0)
				{
					list.add("Tuple[" + auxVar.substring(0, i + 1));
					if (i + 1 < auxVar.length())
						fillTupleList(list, auxVar.substring(i + 1, auxVar.length()));

					keepLooping = false;
				}
			}
		}
		else
		{
			if (types.contains(";"))
			{
				list.add(types.substring(0, types.indexOf(";")));
				fillTupleList(list, types.substring(types.indexOf(";") + 1));
			}
			else
				list.add(types);
		}
	}
}
