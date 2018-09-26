package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;

public class ComparePrimitiveType extends Comparator<PrimitiveType>
{
  @Override
  public boolean compare(PrimitiveType p1, PrimitiveType p2)
  {
    if (super.checkNulls(p1, p2))
      return false;

    if (super.checkEquals(p1, p2))
      return true;

    if (p1.getName() == null ^ p2.getName() == null)
      return false;

    return (p1.getName() == null && p2.getName() == null)
        || (p1.getName().equals(p2.getName()));
  }
}
