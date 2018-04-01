/**
 *
 */
package es.um.nosql.streaminginference.json2dbschema.intermediate.raw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author dsevilla
 *
 */
public class ObjectSC extends SchemaComponent implements Serializable
{
	private static final long serialVersionUID = 581406329571280748L;

	private List<Pair<String, SchemaComponent>> inners;

	public boolean isRoot;
	public String entityName;

	public ObjectSC()
	{
		inners = new ArrayList<Pair<String, SchemaComponent>>(20);
	}

	@Override
	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		
		if (other instanceof ObjectSC)
			return inners.equals(((ObjectSC)other).inners);

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

	public List<Pair<String, SchemaComponent>> getInners() {
		return inners;
	}

	public int size() {
		return inners.size();
	}
}
