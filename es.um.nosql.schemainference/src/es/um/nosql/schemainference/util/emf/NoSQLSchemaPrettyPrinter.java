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

public class NoSQLSchemaPrettyPrinter
{
	private static NoSQLSchemaPrettyPrinter instance;

	private static final char ENDLINE = '\n';

	private static final char TAB = '\t';

	public static NoSQLSchemaPrettyPrinter getInstance()
	{
		if (instance == null)
			instance = new NoSQLSchemaPrettyPrinter();

		return instance;
	}

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

	public String stringify(Entity entity, String defTabs)
	{
		if (entity == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "Entity - name: " + entity.getName() + ENDLINE);

		for (EntityVersion eVersion : entity.getEntityversions())
			result.append(stringify(eVersion, tabs));

		return result.toString();
	}

	public String stringify(EntityVersion eVersion, String defTabs)
	{
		if (eVersion == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "EntityVersion - versionId: " + eVersion.getVersionId() + ENDLINE);

		for (Property property : eVersion.getProperties())
			result.append(stringify(property, tabs));

		return result.toString();
	}

	public String stringify(Property property, String defTabs)
	{
		if (property == null)
			return null;

		StringBuilder result = new StringBuilder();

		if (property instanceof Attribute)
			result.append(stringify((Attribute)property, defTabs));
		else if (property instanceof Association)
			result.append(stringify((Association)property, defTabs));

		return result.toString();
	}

	public String stringify(Attribute attribute, String defTabs)
	{
		if (attribute == null)
			return null;

		String tabs = defTabs + TAB;
		StringBuilder result = new StringBuilder();

		result.append(tabs + "Attribute - name: " + attribute.getName() + ", " + stringify(attribute.getType()) + ENDLINE);

		return result.toString();
	}

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
				EntityVersion eVersion = aggregate.getRefTo().get(i);
				result.append(((Entity)eVersion.eContainer()).getName() + " " + eVersion.getVersionId());
				if (i != (aggregate.getRefTo().size() - 1))
					result.append(", ");
			}
		}

		if (association instanceof Reference)
		{
			Reference reference = (Reference)association;
			result.insert(0, tabs + "Reference");
			result.append("opposite: ");

			Reference oppositeRef = reference.getOpposite();

			if (oppositeRef == null)
				result.append(oppositeRef);
			else
			{
				result.append("(Reference " + oppositeRef.getName() + ", ");
				result.append("lowerBound: " + oppositeRef.getLowerBound() + ", upperBound: " + oppositeRef.getUpperBound() + ", ");
				result.append("refTo: " + ((Entity)oppositeRef.getRefTo()).getName() + ")");
			}
			result.append(", refTo: " + ((Entity)reference.getRefTo()).getName());
		}

		result.append(ENDLINE);

		return result.toString();
	}
}
