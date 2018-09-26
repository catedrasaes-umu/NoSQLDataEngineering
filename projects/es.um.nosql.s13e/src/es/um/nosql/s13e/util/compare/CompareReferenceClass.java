package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.ReferenceClass;

public class CompareReferenceClass extends Comparator<ReferenceClass>
{
  @Override
  public boolean compare(ReferenceClass r1, ReferenceClass r2)
  {
    if (super.checkNulls(r1, r2))
      return false;

    if (super.checkEquals(r1, r2))
      return true;

    return true;
  }
}
