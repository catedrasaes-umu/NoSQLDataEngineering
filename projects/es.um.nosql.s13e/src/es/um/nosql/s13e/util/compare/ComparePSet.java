package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.PSet;

public class ComparePSet extends Comparator<PSet>
{
  @Override
  public boolean compare(PSet s1, PSet s2)
  {
    if (super.checkNulls(s1, s2))
      return false;

    if (super.checkEquals(s1, s2))
      return true;

    return (s1.getElementType() == null && s2.getElementType() == null)
        || new CompareType().compare(s1.getElementType(), s2.getElementType());
  }
}
