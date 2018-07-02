package data.utils.serializer;

import Variation_Diff.HasField;
import Variation_Diff.HasNotField;
import Variation_Diff.NoSQLDifferences;
import Variation_Diff.PrimitiveType;
import Variation_Diff.ReferenceType;
import Variation_Diff.TypeDifference;
import Variation_Diff.AggregateType;
import Variation_Diff.EntityType;
import Variation_Diff.FieldType;
import Variation_Diff.HeterogeneousTupleType;
import Variation_Diff.TypeHint;
import Variation_Diff.HomogeneousTupleType;

/**
 * Class used to serialize a NoSQLDifferences object in a human-readable way.
 * Implemented as a Singleton.
 * @author Alberto Hernández Chillón
 */
public class NoSQLDataSerializer
{
	/**
	 * Singleton reference.
	 */
	private static NoSQLDataSerializer instance;

	/**
	 * Endline character.
	 */
	private static final char ENDLINE = '\n';

	/**
	 * Tab character.
	 */
	private static final char TAB = '\t';

	/**
	 * Method used to get the NoSQLDataSerializer instance.
	 * @return NoSQLSchemaSerializer instance.
	 */
	public static NoSQLDataSerializer getInstance()
	{
		if (instance == null)
			instance = new NoSQLDataSerializer();

		return instance;
	}

	/**
	 * Method used to stringify a NoSQLDifferences.
	 * @param theDifferences The NoSQLDifferences to be serialized.
	 * @return A String containing a serialized NoSQLDifferences.
	 */
	public String stringify(NoSQLDifferences theDifferences)
	{
		if (theDifferences == null)
			return null;

		String tabs = new StringBuilder(TAB).toString();
		StringBuilder result = new StringBuilder();

		result.append("NoSQLDifferences - name: " + theDifferences.getName() + ENDLINE);

		for (TypeDifference typeDiff : theDifferences.getHasTypeDifferences())
			result.append(stringify(typeDiff, tabs));

		return result.toString();
	}

	/**
	 * Method used to stringify a TypeDifference.
	 * @param typeDiff The TypeDifference to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized TypeDifference.
	 */
	public String stringify(TypeDifference typeDiff, String defTabs)
	{
		if (typeDiff == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "TypeDifference - name: " + typeDiff.getName() + ENDLINE);

		for (TypeHint tHint : typeDiff.getHints())
		{
			if (tHint instanceof HasField)
				result.append(stringify((HasField)tHint, tabs));
			if (tHint instanceof HasNotField)
				result.append(stringify((HasNotField)tHint, tabs));
		}

		return result.toString();
	}

	/**
	 * Method used to stringify a HasField.
	 * @param hint The HasField to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized HasField.
	 */
	public String stringify(HasField hint, String defTabs)
	{
		if (hint == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "HasField: " + "{ " + hint.getWithName() + ": " + stringify(hint.getWithType()) + " }" + ENDLINE);

		return result.toString();
	}

	/**
	 * Method used to stringify a HasNotField.
	 * @param hint The HasNotField to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized HasNotField.
	 */
	public String stringify(HasNotField hint, String defTabs)
	{
		if (hint == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "HasNotField: " + "{ " + hint.getWithName() + ": " + stringify(hint.getWithType()) + " }" + ENDLINE);

		return result.toString();
	}

	/**
	 * Method used to stringify a WithType.
	 * @param type The WithType to be serialized.
	 * @return A String contaning a serialized WithType.
	 */
	private String stringify(FieldType type)
	{
		if (type == null)
			return null;

		StringBuilder result = new StringBuilder();

		if (type instanceof PrimitiveType)
			result.append(((PrimitiveType)type).getType());
		else if (type instanceof EntityType)
			result.append(((EntityType)type).getType());
		else if (type instanceof HeterogeneousTupleType)
		{
			result.append("Tuple[");
			for (String s : ((HeterogeneousTupleType)type).getType())
				result.append(s + ", ");
			result.delete(result.lastIndexOf(", "), result.length());
			result.append("]");
		}
		else if (type instanceof HomogeneousTupleType)
		{
			result.append("Tuple[");
			result.append(((HomogeneousTupleType)type).getType() + "*");
			result.append("]");
		}
		else if (type instanceof AggregateType)
		{
			result.append("Aggr[" + ((AggregateType)type).getLowerBound() + ".." + ((AggregateType)type).getUpperBound() + "], [");
			for (String s : ((AggregateType)type).getType())
				result.append(s + ", ");
			result.delete(result.lastIndexOf(", "), result.length());
			result.append("]");
		}
		else if (type instanceof ReferenceType)
			result.append("Refr[" + ((ReferenceType)type).getLowerBound() + ".." + ((ReferenceType)type).getUpperBound() + "], " + ((ReferenceType)type).getType());

		return result.toString();
	}
}
