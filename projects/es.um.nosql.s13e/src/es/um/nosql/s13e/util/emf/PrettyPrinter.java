package es.um.nosql.s13e.util.emf;

import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;

public class PrettyPrinter
{
	private static final String TAB = "\t";

	public static String printPretty(NoSQLSchema theSchema)
	{
		if (theSchema == null)
			return null;

		StringBuilder result = new StringBuilder();

		result.append("NoSQLSchema name:" + theSchema.getName()
			+ System.lineSeparator());

		for (Entity entity : theSchema.getEntities())
			result.append(printPretty(entity, TAB));

		return result.toString();
	}

	public static String printPretty(Entity entity)
	{
		return printPretty(entity, "");
	}

	private static String printPretty(Entity entity, String defTabs)
	{
		if (entity == null)
			return null;

		String tabs = defTabs + TAB;

		String result = defTabs + "Entity name:" + entity.getName() + System.lineSeparator() 
				 + entity.getEntityvariations().stream()
				 	.map(ev -> printPretty(ev,tabs))
				 	.collect(Collectors.joining(""));

		return result;
	}

	public static String printPretty(EntityVariation eVersion)
	{
		return printPretty(eVersion, "");
	}

	private static String printPretty(EntityVariation eVersion, String defTabs)
	{
		if (eVersion == null)
			return null;

		String tabs = defTabs + TAB;

		String result = defTabs + "EntityVersion versionId:" + eVersion.getVersionId() + System.lineSeparator() 
				 + eVersion.getProperties().stream()
				 	.map(p -> tabs + Serializer.serialize(p) + System.lineSeparator())
				 	.collect(Collectors.joining());

		return result;
	}
}
