package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Property;

public class CompareProperty<Q extends Property> extends Comparator<Q>
{
	@Override
	public boolean compare(Q p1, Q p2) {
		if (super.compare(p1, p2))
			return true;

		return p1.getName().equals(p2.getName());
	}
}
