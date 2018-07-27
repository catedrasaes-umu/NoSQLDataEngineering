package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Association;

public class CompareAssociation<Q extends Association> extends Comparator<Q>
{
	@Override
	public boolean compare(Q t1, Q t2) {
		if (!super.compare(t1, t2))
			return false;

		// Compare type
		return t1.getLowerBound() == t2.getLowerBound()
				&& t1.getUpperBound() == t2.getUpperBound();
	}

}
