package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.PList;

public class ComparePList extends Comparator<PList>
{
  @Override
  public boolean compare(PList l1, PList l2)
  {
    if (super.checkNulls(l1, l2))
      return false;

    if (super.checkEquals(l1, l2))
      return true;

    if (l1.getElementType() == null)
      return l2.getElementType() == null;

    return new CompareType().compare(l1.getElementType(), l2.getElementType());
  }
}
