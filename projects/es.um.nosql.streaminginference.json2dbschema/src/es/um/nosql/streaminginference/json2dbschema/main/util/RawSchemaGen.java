/**
 * 
 */
package es.um.nosql.streaminginference.json2dbschema.main.util;

import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.Pair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.BooleanNode;
import org.codehaus.jackson.node.DoubleNode;
import org.codehaus.jackson.node.IntNode;
import org.codehaus.jackson.node.NullNode;
import org.codehaus.jackson.node.ObjectNode;

import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ArraySC;
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.BooleanSC;
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NullSC;
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.NumberSC;
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.streaminginference.json2dbschema.intermediate.raw.SchemaComponent;

/**
 * @author dsevilla
 *
 */
public class RawSchemaGen
{	
	public static SchemaComponent deSchema(String name, JsonNode n)
	{
		if (n.isObject())
			return deSchema(name, (ObjectNode)n);
		
		if (n.isArray())
			return deSchema(name, (ArrayNode)n);
		
		if (n.isBoolean())
			return deSchema(name, (BooleanNode)n);
		
		if(n.isInt())
			return deSchema(name, (IntNode)n);		
		
		if (n.isFloatingPointNumber())
			return deSchema(name, (DoubleNode)n);
		
		if (n.isNull())
			return deSchema(name, (NullNode)n);
		
		return null;
	}
	
	private static SchemaComponent deSchema(String name, ObjectNode o)
	{
		ObjectSC schema = new ObjectSC();
		
		SortedSet<String> fields = new TreeSet<String>();
		o.getFieldNames().forEachRemaining(fields::add);
		fields.forEach(f -> schema.add(Pair.of(f, deSchema(f, o.get(f)))));
		
		return schema;
	}

	private static SchemaComponent deSchema(String name, ArrayNode a)
	{
		ArraySC schema = new ArraySC();		
		a.forEach(e ->schema.add(deSchema(null, e)));
		
		return schema;
	}
	
	private static SchemaComponent deSchema(String name, BooleanNode b)
	{
		BooleanSC schema  = new BooleanSC();
		return schema;
	}

	private static SchemaComponent deSchema(String name, IntNode i)
	{
		NumberSC schema  = new NumberSC();
		return schema;
	}

	private static SchemaComponent deSchema(String name, DoubleNode i)
	{
		NumberSC schema  = new NumberSC();
		return schema;
	}
	
	private static SchemaComponent deSchema(String name, NullNode n)
	{
		NullSC schema  = new NullSC();
		return schema;
	}
}
