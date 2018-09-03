package es.um.nosql.s13e.util.compare;

import org.eclipse.emf.ecore.util.EcoreUtil;
import es.um.nosql.s13e.NoSQLSchema.Attribute;

public class CompareAttribute extends CompareProperty<Attribute>
{
  @Override
  public boolean compare(Attribute t1, Attribute t2)
  {
    if (!super.compare(t1, t2))
      return false;

    // Compare type
    return (new EcoreUtil.EqualityHelper()).equals(t1.getType(), t2.getType());
  }
}
