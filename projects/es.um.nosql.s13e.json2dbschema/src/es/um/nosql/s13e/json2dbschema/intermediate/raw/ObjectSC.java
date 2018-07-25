package es.um.nosql.s13e.json2dbschema.intermediate.raw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class ObjectSC extends SchemaComponent
{
	private List<Pair<String, SchemaComponent>> inners;

	public boolean isRoot;
	public int count;
	public long timestamp;
	public String entityName;

	public ObjectSC()
	{
		inners = new ArrayList<Pair<String, SchemaComponent>>(20);
	}

	@Override
	public boolean equals(Object other)
	{
		if (other instanceof ObjectSC)
			return entityName.equals(((ObjectSC)other).entityName)
			    && isRoot == ((ObjectSC)other).isRoot
			    && inners.equals(((ObjectSC)other).inners);

		return false;
	}

	public void addAll(Collection<Pair<String, SchemaComponent>> elements)
	{
		inners.addAll(elements);
	}

	public void addAll(Iterable<Pair<String, SchemaComponent>> elements)
	{
		for (Pair<String, SchemaComponent> p : elements)
			inners.add(p);
	}

	public void add(Pair<String, SchemaComponent> sc)
	{
		inners.add(sc);
	}

	public List<Pair<String, SchemaComponent>> getInners()
	{
		return inners;
	}

	public int size() {
		return inners.size();
	}
}
