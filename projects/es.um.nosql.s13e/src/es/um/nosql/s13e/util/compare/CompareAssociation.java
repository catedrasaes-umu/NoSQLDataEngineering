package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Reference;

public class CompareAssociation extends Comparator<Association>
{
  @Override
  public boolean compare(Association a1, Association a2)
  {
    if (super.checkNulls(a1, a2))
      return false;

    if (super.checkEquals(a1, a2))
      return true;

    if (a1.getUpperBound() != a2.getUpperBound() || a1.getLowerBound() != a2.getLowerBound())
      return false;

    if (a1 instanceof Aggregate && a2 instanceof Aggregate)
      return new CompareAggregate().compare((Aggregate)a1, (Aggregate)a2);

    if (a1 instanceof Reference && a2 instanceof Reference)
      return new CompareReference().compare((Reference)a1, (Reference)a2);

    return false;
  }
}
