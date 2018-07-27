package data.utils.serializer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
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

		for (Entity entity : theSchema.getEntities())
			result.append(stringify(entity, tabs));

		return result.toString();
	}

	/**
	 * Method used to stringify an Entity.
	 * @param entity The Entity to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized Entity.
	 */
	public String stringify(Entity entity, String defTabs)
	{
		if (entity == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "Entity - name: " + entity.getName() + ", root: " + entity.isRoot() + ENDLINE);

		for (EntityVariation eVariation : entity.getVariations())
			result.append(stringify(eVariation, tabs));

		return result.toString();
	}

	/**
	 * Method used to stringify an EntityVariation.
	 * @param eVariation The EntityVariation to be serialized.
	 * @param defTabs The number of tabs to apply to the serialization.
	 * @return A String containing a serialized EntityVariation.
	 */
	public String stringify(EntityVariation eVariation, String defTabs)
	{
		if (eVariation == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "EntityVariation - variationId: " + eVariation.getVariationId() +
		    ", count: " + eVariation.getCount() + ", timestamp: " + eVariation.getTimestamp() + ENDLINE);

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

		result.append(tabs + "Attribute - name: " + attribute.getName() + ", " + stringify(attribute.getType()) + ENDLINE);

		return result.toString();
	}

	/**
	 * Method used to stringify a Type.
	 * @param type The Type to be serialized.
	 * @return A String containing a serialized Type.
	 */
	public String stringify(Type type)
	{
		if (type == null)
			return null;

		StringBuilder result = new StringBuilder();

		if (type instanceof PrimitiveType)
			result.append("type: " + ((PrimitiveType)type).getName());
		if (type instanceof Tuple)
			result.append("type: Tuple[" + stringify(((Tuple)type).getElements()) + "]");

		return result.toString();
	}

	/**
	 * Method used to stringify a list of Types in case a Tuple is being processed.
	 * @param typeList The Type list to be serialized.
	 * @return A String containing a serialized Type list.
	 */
	public String stringify(List<Type> typeList)
	{
		if (typeList == null)
			return null;

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < typeList.size(); i++)
		{
			Type type = typeList.get(i);
			if (type instanceof PrimitiveType)
				result.append(((PrimitiveType)type).getName());
			if (type instanceof Tuple)
				result.append("Tuple[" + stringify(((Tuple)type).getElements()) + "]");
			if (i != (typeList.size() - 1))
				result.append(";");
		}
		return result.toString();
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

		result.append(" - name: " + association.getName() + ", ");
		result.append("lowerBound: " + association.getLowerBound() + ", upperBound: " + association.getUpperBound() + ", ");

		if (association instanceof Aggregate)
		{
			Aggregate aggregate = (Aggregate)association;
			result.insert(0, tabs + "Aggregate");
			result.append("refTo: ");

			for (int i = 0; i < aggregate.getRefTo().size(); i++)
			{
			  EntityVariation eVariation = aggregate.getRefTo().get(i);
				result.append("EntityVariation " + eVariation.getVariationId());
				if (i != (aggregate.getRefTo().size() - 1))
					result.append(", ");
			}
		}

		if (association instanceof Reference)
		{
			Reference reference = (Reference)association;
			result.insert(0, tabs + "Reference");
			result.append("opposite: " + reference.getOpposite() + ", refTo: " + ((Entity)reference.getRefTo()).getName());
		}

		result.append(ENDLINE);

		return result.toString();
	}

	/**
	 * Method used to stringify an EntityVariation Triple list with the associated schema info.
	 * @param entityVariationList The EntityVariation Triple list.
	 * @return A String containing a serialized EntityVariation Triple list.
	 */
	public String stringifyEntityVariationList(List<Triple<EntityVariation, Set<EntityVariation>, Set<Entity>>> entityVariationList)
	{
		if (entityVariationList == null)
			return null;

		Collections.sort(entityVariationList, new Comparator<Triple<EntityVariation, Set<EntityVariation>, Set<Entity>>>()
		{
			public int compare(Triple<EntityVariation, Set<EntityVariation>, Set<Entity>> o1, Triple<EntityVariation, Set<EntityVariation>, Set<Entity>> o2)
			{
				return o1.getLeft().getVariationId() < o2.getLeft().getVariationId() ? -1 : 1;
			}
		});

		StringBuilder result = new StringBuilder();
		result.append("Printing EntityVariation list...\n");

		if (entityVariationList.isEmpty())
			result.append("EntityVariation list is empty.\n");
		else
		{
			result.append("[\n");
			for (Triple<EntityVariation, Set<EntityVariation>, Set<Entity>> triple : entityVariationList)
			{
				result.append("\t<EntityVariation " + triple.getLeft().getVariationId() + ", EntityVariation List: [ ");

				for (EntityVariation ev : triple.getMiddle())
					result.append("EV_" + ev.getVariationId() + " ");
				result.append("],");

				result.append(" Entity List: [ ");
				for (Entity e : triple.getRight())
					result.append(e.getName() + " ");
				result.append("]>\n");
			}
			result.append("]\n");
		}
		return result.toString();
	}

	/**
	 * Method used to stringify an Entity Triple list with the associated schema info.
	 * @param entityList The Entity Triple list.
	 * @return A String containing a serialized Entity Triple list.
	 */
	public String stringifyEntityList(List<Triple<Entity, Set<EntityVariation>, Set<Entity>>> entityList)
	{
		if (entityList == null)
			return null;

		Collections.sort(entityList, new Comparator<Triple<Entity, Set<EntityVariation>, Set<Entity>>>()
		{
			public int compare(Triple<Entity, Set<EntityVariation>, Set<Entity>> o1, Triple<Entity, Set<EntityVariation>, Set<Entity>> o2)
			{
				return o1.getLeft().getName().compareTo(o2.getLeft().getName());
			}
		});

		StringBuilder result = new StringBuilder();
		result.append("Printing Entity list...\n");

		if (entityList.isEmpty())
			result.append("Entity list is empty.\n");
		else
		{
			result.append("[\n");
			for (Triple<Entity, Set<EntityVariation>, Set<Entity>> triple : entityList)
			{
				result.append("\t<Entity " + triple.getLeft().getName() + ", EntityVariation List: [ ");

				for (EntityVariation ev : triple.getMiddle())
					result.append("EV_" + ev.getVariationId() + " ");
				result.append("],");

				result.append(" Entity List: [ ");
				for (Entity e : triple.getRight())
					result.append(e.getName() + " ");
				result.append("]>\n");
			}
			result.append("]\n");
		}
		return result.toString();
	}
}
