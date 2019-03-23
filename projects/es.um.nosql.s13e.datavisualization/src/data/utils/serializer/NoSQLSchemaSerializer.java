package data.utils.serializer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Triple;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.NoSQLSchema.DataType;

/**
 * Class used to serialize a NoSQLSchema object in a human-readable way.
 * Implemented as a Singleton.
 * @author Alberto Hernández Chillón
 */
public class NoSQLSchemaSerializer
{
	/**
	 * Singleton reference.
	 */
	private static NoSQLSchemaSerializer instance;

	/**
	 * Endline character.
	 */
	private static final char ENDLINE = '\n';

	/**
	 * Tab character.
	 */
	private static final char TAB = '\t';

	/**
	 * Method used to get the NoSQLSchemaSerializer instance.
	 * @return NoSQLSchemaSerializer instance.
	 */
	public static NoSQLSchemaSerializer getInstance()
	{
		if (instance == null)
			instance = new NoSQLSchemaSerializer();

		return instance;
	}

	/**
	 * Method used to stringify a NoSQLSchema.
	 * @param theSchema The NoSQLSchema to be serialized.
	 * @return A String containing a serialized NoSQLSchema.
	 */
	public String stringify(NoSQLSchema theSchema)
	{
		if (theSchema == null)
			return null;

		String tabs = new StringBuilder(TAB).toString();
		StringBuilder result = new StringBuilder();

		result.append("NoSQLSchema - name: " + theSchema.getName() + ENDLINE);

		for (EntityType entity : theSchema.getEntities())
			result.append(stringify(entity, tabs));

		return result.toString();
	}

	/**
	 * Method used to stringify an EntityType.
	 * @param entity The EntityType to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized EntityType.
	 */
	public String stringify(EntityType entity, String defTabs)
	{
		if (entity == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "EntityType - name: " + entity.getName() + ", root: " + entity.isRoot() + ENDLINE);

		for (StructuralVariation eVariation : entity.getVariations())
			result.append(stringify(eVariation, tabs));

		return result.toString();
	}

	/**
	 * Method used to stringify an StructuralVariation.
	 * @param eVariation The StructuralVariation to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized StructuralVariation.
	 */
	public String stringify(StructuralVariation eVariation, String defTabs)
	{
		if (eVariation == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "StructuralVariation - variationId: " + eVariation.getVariationId() +
		    ", count: " + eVariation.getCount() + ", firstTs: " + eVariation.getFirstTimestamp() + ", lastTs: " + eVariation.getLastTimestamp() + ENDLINE);

		for (Property property : eVariation.getProperties())
		{
			if (property instanceof Attribute)
				result.append(stringify((Attribute)property, tabs));
			else if (property instanceof Association)
				result.append(stringify((Association)property, tabs));
		}

		return result.toString();
	}

	/**
	 * Method used to stringify an Attribute.
	 * @param attribute The Attribute to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized Attribute.
	 */
	public String stringify(Attribute attribute, String defTabs)
	{
		if (attribute == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "Attribute - name: " + attribute.getName() + ", optional: " + attribute.isOptional() + ", " + stringify(attribute.getType()) + ENDLINE);

		return result.toString();
	}

	/**
	 * Method used to stringify a Type.
	 * @param type The Type to be serialized.
	 * @return A String containing a serialized Type.
	 */
	public String stringify(DataType type)
	{
		if (type == null)
			return null;

		StringBuilder result = new StringBuilder();

		if (type instanceof PrimitiveType)
			result.append("type: " + ((PrimitiveType)type).getName());
		if (type instanceof PTuple)
			result.append("type: PTuple[" + stringify(((PTuple)type).getElements()) + "]");

		return result.toString();
	}

	/**
	 * Method used to stringify a list of Types in case a PTuple is being processed.
	 * @param typeList The Type list to be serialized.
	 * @return A String containing a serialized Type list.
	 */
	public String stringify(List<DataType> typeList)
	{
		if (typeList == null)
			return null;

		return typeList.stream().map(type -> {
			if (type instanceof PrimitiveType)
				return ((PrimitiveType)type).getName();
			if (type instanceof PTuple)
				return "PTuple[" + stringify(((PTuple)type).getElements()) + "]";
			return "";
		}).collect(Collectors.joining(", "));
	}

	/**
	 * Method used to stringify an Association.
	 * @param association The Association to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized Association.
	 */
	public String stringify(Association association, String defTabs)
	{
		if (association == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(" - name: " + association.getName() + ", optional: " + association.isOptional() + ", ");
		result.append("lowerBound: " + association.getLowerBound() + ", upperBound: " + association.getUpperBound() + ", ");

		if (association instanceof Aggregate)
		{
			Aggregate aggregate = (Aggregate)association;
			result.insert(0, tabs + "Aggregate");
			result.append("refTo: ");

			result.append(aggregate.getAggregates().stream()
					.map(ev -> "StructuralVariation " + ev.getVariationId()).collect(Collectors.joining(", ")));
		}

		if (association instanceof Reference)
		{
			Reference reference = (Reference)association;
			result.insert(0, tabs + "Reference");
			result.append("opposite: " + reference.getOpposite() + ", refTo: " + reference.getRefsTo().getName());
		}

		result.append(ENDLINE);

		return result.toString();
	}

	/**
	 * Method used to stringify an StructuralVariation Triple list with the associated schema info.
	 * @param entityVariationList The StructuralVariation Triple list.
	 * @return A String containing a serialized StructuralVariation Triple list.
	 */
	public String stringifyStructuralVariationList(List<Triple<StructuralVariation, Set<StructuralVariation>, Set<EntityType>>> entityVariationList)
	{
		if (entityVariationList == null)
			return null;

		Collections.sort(entityVariationList, new Comparator<Triple<StructuralVariation, Set<StructuralVariation>, Set<EntityType>>>()
		{
			public int compare(Triple<StructuralVariation, Set<StructuralVariation>, Set<EntityType>> o1, Triple<StructuralVariation, Set<StructuralVariation>, Set<EntityType>> o2)
			{
				return o1.getLeft().getVariationId() < o2.getLeft().getVariationId() ? -1 : 1;
			}
		});

		StringBuilder result = new StringBuilder();
		result.append("Printing StructuralVariation list...\n");

		if (entityVariationList.isEmpty())
			result.append("StructuralVariation list is empty.\n");
		else
		{
			result.append("[\n");
			for (Triple<StructuralVariation, Set<StructuralVariation>, Set<EntityType>> triple : entityVariationList)
			{
				result.append("\t<StructuralVariation " + triple.getLeft().getVariationId() + ", StructuralVariation List: [ ");

				for (StructuralVariation ev : triple.getMiddle())
					result.append("EV_" + ev.getVariationId() + " ");
				result.append("],");

				result.append(" EntityType List: [ ");
				for (EntityType e : triple.getRight())
					result.append(e.getName() + " ");
				result.append("]>\n");
			}
			result.append("]\n");
		}
		return result.toString();
	}

	/**
	 * Method used to stringify an EntityType Triple list with the associated schema info.
	 * @param entityList The EntityType Triple list.
	 * @return A String containing a serialized EntityType Triple list.
	 */
	public String stringifyEntityTypeList(List<Triple<EntityType, Set<StructuralVariation>, Set<EntityType>>> entityList)
	{
		if (entityList == null)
			return null;

		Collections.sort(entityList, new Comparator<Triple<EntityType, Set<StructuralVariation>, Set<EntityType>>>()
		{
			public int compare(Triple<EntityType, Set<StructuralVariation>, Set<EntityType>> o1, Triple<EntityType, Set<StructuralVariation>, Set<EntityType>> o2)
			{
				return o1.getLeft().getName().compareTo(o2.getLeft().getName());
			}
		});

		StringBuilder result = new StringBuilder();
		result.append("Printing EntityType list...\n");

		if (entityList.isEmpty())
			result.append("EntityType list is empty.\n");
		else
		{
			result.append("[\n");
			for (Triple<EntityType, Set<StructuralVariation>, Set<EntityType>> triple : entityList)
			{
				result.append("\t<EntityType " + triple.getLeft().getName() + ", StructuralVariation List: [ ");

				for (StructuralVariation ev : triple.getMiddle())
					result.append("EV_" + ev.getVariationId() + " ");
				result.append("],");

				result.append(" EntityType List: [ ");
				for (EntityType e : triple.getRight())
					result.append(e.getName() + " ");
				result.append("]>\n");
			}
			result.append("]\n");
		}
		return result.toString();
	}
}
