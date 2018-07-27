package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;

public class CompareAggregate extends CompareAssociation<Aggregate>
{
	@Override
	public boolean compare(Aggregate t1, Aggregate t2) {
		if (!super.compare(t1, t2))
			return false;

		// Compare type
		return t1.getAggregates().equals(t2.getAggregates());
	}

}
