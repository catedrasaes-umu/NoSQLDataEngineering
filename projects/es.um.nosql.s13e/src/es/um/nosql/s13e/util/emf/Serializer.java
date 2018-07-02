package es.um.nosql.s13e.util.emf;

import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.NoSQLSchema.Type;

public class Serializer
{
	public static String serialize(EntityVariation eVersion)
	{
		if (eVersion == null)
			return null;

		return ((Entity)eVersion.eContainer()).getName() + "_" + eVersion.getVersionId();
	}

	public static String serialize(Property property)
	{
		if (property == null)
			return null;

		StringBuilder result = new StringBuilder();

		if (property instanceof Attribute)
			result.append(serialize((Attribute)property));
		else if (property instanceof Association)
			result.append(serialize((Association)property));

		return result.toString();
	}

	public static String serialize(Attribute attribute)
	{
		if (attribute == null)
			return null;

		return attribute.getName() + ":" + serialize(attribute.getType());
	}

	public static String serialize(Type type)
	{
		if (type == null)
			return null;

		StringBuilder result = new StringBuilder();

		if (type instanceof PrimitiveType)
			result.append(((PrimitiveType)type).getName());
		else // if (type instanceof Tuple)
			result.append("Tuple[" + serialize(((Tuple)type).getElements()) + "]");

		return result.toString();
	}

	public static String serialize(List<Type> typeList)
	{
		if (typeList == null)
			return null;

		String result = typeList.stream().map(type ->
			{
				if (type instanceof PrimitiveType)
					return ((PrimitiveType)type).getName();
				else // if (type instanceof Tuple)
					return "Tuple[" + serialize(((Tuple)type).getElements()) + "]";
			}).collect(Collectors.joining(";"));

		return result;
	}

	public static String serialize(Association association)
	{
		if (association == null)
			return null;

		StringBuilder result = new StringBuilder();

		result.append(association.getName() + ":");

		if (association instanceof Aggregate)
		{
			Aggregate aggregate = (Aggregate)association;
			result.append("[");

			result.append(
					aggregate.getRefTo().stream()
					.map(ev -> serialize(ev))
					.collect(Collectors.joining(";")));
			result.append("]:[" + association.getLowerBound() + ".." + association.getUpperBound() + "]");
		}
		else if (association instanceof Reference)
		{
			Reference reference = (Reference)association;
			result.append(((Entity)reference.getRefTo()).getName());
			result.append(":[" + association.getLowerBound() + ".." + association.getUpperBound() + "]:");
			result.append("opp[");

			Reference oppositeRef = reference.getOpposite();

			if (oppositeRef == null)
				result.append(oppositeRef);
			else
			{
				result.append(oppositeRef.getName() + ":" + ((Entity)oppositeRef.getRefTo()).getName());
				result.append(":[" + oppositeRef.getLowerBound() + ".." + oppositeRef.getUpperBound() + "]]");
			}
		}

		return result.toString();
	}
}
