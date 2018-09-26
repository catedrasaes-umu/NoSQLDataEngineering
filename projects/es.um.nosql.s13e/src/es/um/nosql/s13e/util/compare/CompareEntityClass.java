package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.EntityClass;

public class CompareEntityClass extends Comparator<EntityClass>
{
  @Override
  public boolean compare(EntityClass e1, EntityClass e2)
  {
    if (super.checkNulls(e1, e2))
      return false;

    if (super.checkEquals(e1, e2))
      return true;

    return e1.isRoot() == e2.isRoot();
  }
}
