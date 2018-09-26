package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Null;
import es.um.nosql.s13e.NoSQLSchema.Property;

public class CompareProperty extends Comparator<Property>
{
  @Override
  public boolean compare(Property p1, Property p2)
  {
    if (super.checkNulls(p1, p2))
      return false;

    if (super.checkEquals(p1, p2))
      return true;

    if (p1.getName() == null ^ p2.getName() == null)
      return false;

    if (p1.getName() != null && !p1.getName().equals(p2.getName()))
      return false;

    if (p1 instanceof Association && p2 instanceof Association)
      return new CompareAssociation().compare((Association)p1, (Association)p2);

    if (p1 instanceof Null && p2 instanceof Null)
      return new CompareNull().compare((Null)p1, (Null)p2);

    if (p1 instanceof Attribute && p2 instanceof Attribute)
      return new CompareAttribute().compare((Attribute)p1, (Attribute)p2);

    return false;
  }
}
