package es.um.nosql.schemainference.util.emf;

import java.util.List;

import es.um.nosql.schemainference.NoSQLSchema.Aggregate;
import es.um.nosql.schemainference.NoSQLSchema.Association;
import es.um.nosql.schemainference.NoSQLSchema.Attribute;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.NoSQLSchema.Reference;
import es.um.nosql.schemainference.NoSQLSchema.Tuple;
import es.um.nosql.schemainference.NoSQLSchema.Type;

public class NoSQLSchemaSerializer
{
	private static NoSQLSchemaSerializer instance;

	private static final char ENDLINE = '\n';

	private static final char TAB = '\t';

	public static NoSQLSchemaSerializer getInstance()
	{
		if (instance == null)
			instance = new NoSQLSchemaSerializer();

		return instance;
	}

	public String serializePretty(NoSQLSchema theSchema)
	{
		if (theSchema == null)
			return null;

		String tabs = new StringBuilder(TAB).toString();
		StringBuilder result = new StringBuilder();

		result.append("NoSQLSchema name:" + theSchema.getName() + ENDLINE);

		for (Entity entity : theSchema.getEntities())
			result.append(serializePretty(entity, tabs));

		return result.toString();
	}

	public String SerializePretty(Entity entity)
	{
		return serializePretty(entity, "");
	}

	private String serializePretty(Entity entity, String defTabs)
	{
		if (entity == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "Entity name:" + entity.getName() + ENDLINE);

		for (EntityVersion eVersion : entity.getEntityversions())
			result.append(serializePretty(eVersion, tabs));

		return result.toString();
	}

	public String serializePretty(EntityVersion eVersion)
	{
		return serializePretty(eVersion, "");
	}

	private String serializePretty(EntityVersion eVersion, String defTabs)
	{
		if (eVersion == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "EntityVersion versionId:" + eVersion.getVersionId() + ENDLINE);
		String propertiesTabs = tabs + TAB;

		for (Property property : eVersion.getProperties())
			result.append(propertiesTabs + serialize(property) + ENDLINE);

		return result.toString();
	}

	public String serialize(Property property)
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

	public String serialize(Attribute attribute)
	{
		if (attribute == null)
			return null;

		return "Attribute name:" + attribute.getName() + "," + serialize(attribute.getType());
	}

	public String serialize(Type type)
	{
		if (type == null)
			return null;

		StringBuilder result = new StringBuilder();

		if (type instanceof PrimitiveType)
			result.append("type:" + ((PrimitiveType)type).getName());
		if (type instanceof Tuple)
			result.append("type:Tuple[" + serialize(((Tuple)type).getElements()) + "]");

		return result.toString();
	}

	public String serialize(List<Type> typeList)
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
				result.append("Tuple[" + serialize(((Tuple)type).getElements()) + "]");
			if (i != (typeList.size() - 1))
				result.append(";");
		}
		return result.toString();
	}

	public String serialize(Association association)
	{
		if (association == null)
			return null;

		StringBuilder result = new StringBuilder();

		result.append(" name:" + association.getName() + ",");
		result.append("lowerBound:" + association.getLowerBound() + ",upperBound:" + association.getUpperBound() + ",");

		if (association instanceof Aggregate)
		{
			Aggregate aggregate = (Aggregate)association;
			result.insert(0, "Aggregate");
			result.append("refTo:");

			for (int i = 0; i < aggregate.getRefTo().size(); i++)
			{
				EntityVersion eVersion = aggregate.getRefTo().get(i);
				result.append(((Entity)eVersion.eContainer()).getName() + "_" + eVersion.getVersionId());
				if (i != (aggregate.getRefTo().size() - 1))
					result.append(",");
			}
		}

		if (association instanceof Reference)
		{
			Reference reference = (Reference)association;
			result.insert(0, "Reference");
			result.append("opposite:");

			Reference oppositeRef = reference.getOpposite();

			if (oppositeRef == null)
				result.append(oppositeRef);
			else
			{
				result.append("(name:" + oppositeRef.getName() + ",");
				result.append("lowerBound:" + oppositeRef.getLowerBound() + ",upperBound:" + oppositeRef.getUpperBound() + ",");
				result.append("refTo:" + ((Entity)oppositeRef.getRefTo()).getName() + ")");
			}
			result.append(",refTo:" + ((Entity)reference.getRefTo()).getName());
		}

		return result.toString();
	}
}
