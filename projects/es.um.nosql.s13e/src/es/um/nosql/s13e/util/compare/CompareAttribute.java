package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Attribute;

public class CompareAttribute extends Comparator<Attribute>
{
  @Override
  public boolean compare(Attribute a1, Attribute a2)
  {
    if (super.checkNulls(a1, a2))
      return false;

    if (super.checkEquals(a1, a2))
      return true;

    return (a1.getType() == null && a2.getType() == null)
        || new CompareType().compare(a1.getType(), a2.getType());
  }
}
