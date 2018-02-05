/**
 *
 */
package es.um.nosql.s13e.json2dbschema.intermediate.raw.util;

import es.um.nosql.s13e.json2dbschema.intermediate.raw.ArraySC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.BooleanSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NullSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NumberSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.SchemaComponent;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.StringSC;

/**
 * @author dsevilla
 *
 */
public class SchemaPrinter
{
	public static String schemaString(SchemaComponent sc)
	{
		StringBuilder sb = new StringBuilder(20);
		return schemaString(sc, sb);
	}

	private static String schemaString(SchemaComponent sc, StringBuilder sb)
	{
		if (sc instanceof ObjectSC)
			schemaString((ObjectSC)sc, sb);

		if (sc instanceof ArraySC)
			schemaString((ArraySC)sc, sb);

		if (sc instanceof BooleanSC)
			sb.append('b');

		if (sc instanceof NumberSC)
			sb.append('n');

		if (sc instanceof NullSC)
			sb.append('0');

		if (sc instanceof StringSC)
			sb.append('s');

		return sb.toString();
	}

	private static void _outname(String name, StringBuilder sb)
	{
		sb.append('"');
		sb.append(name);
		sb.append('"');
	}

	private static void schemaString(ObjectSC sc, StringBuilder sb)
	{
		if (sc.isRoot)
			sb.append("(root)");
		sb.append("<");
		sb.append(sc.entityName);
		sb.append(">{");
		sc.getInners().forEach(e -> {
			_outname(e.getLeft(), sb);
			schemaString(e.getRight(), sb);
		});
		sb.append("}");
	}

	private static void schemaString(ArraySC sc, StringBuilder sb)
	{
		sb.append("[");
		sc.getInners().forEach(e -> schemaString(e, sb));
		sb.append("]");
	}
}
