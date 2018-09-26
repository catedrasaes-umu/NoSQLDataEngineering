package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Null;

public class CompareNull extends Comparator<Null>
{
  @Override
  public boolean compare(Null n1, Null n2)
  {
    if (super.checkNulls(n1, n2))
      return false;

    if (super.checkEquals(n1, n2))
      return true;

    return true;
  }
}
