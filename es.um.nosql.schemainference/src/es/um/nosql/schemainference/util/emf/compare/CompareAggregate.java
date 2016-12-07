package es.um.nosql.schemainference.util.emf.compare;

import es.um.nosql.schemainference.NoSQLSchema.Aggregate;

public class CompareAggregate extends CompareAssociation<Aggregate>
{
	@Override
	public boolean compare(Aggregate t1, Aggregate t2) {
		if (!super.compare(t1, t2))
			return false;

		// Compare type
		return t1.getRefTo().equals(t2.getRefTo());
	}

}
