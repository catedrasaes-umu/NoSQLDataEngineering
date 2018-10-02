package es.um.nosql.s13e.util.compare;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.Reference;

public class CompareReference extends Comparator<Reference>
{
  @Override
  public boolean compare(Reference r1, Reference r2)
  {
    if (super.checkNulls(r1, r2))
      return false;

    if (super.checkEquals(r1, r2))
      return true;

    if (r1.getOriginalType() == null ^ r2.getOriginalType() == null)
      return false;

    if (r1.getOriginalType() != null && !r1.getOriginalType().equals(r2.getOriginalType()))
      return false;

    if (r1.getOpposite() == null ^ r2.getOpposite() == null)
      return false;

    if (r1.getOpposite() != null && !new CompareProperty().compare(r1.getOpposite(), r2.getOpposite()))
      return false;

    if (r1.getFeatures() == null ^ r2.getFeatures() == null)
      return false;

    if (r1.getFeatures() != null)
    {
      if (r1.getFeatures().eContainer() != null ^ r2.getFeatures().eContainer() != null)
        return false;

      if (r1.getFeatures().eContainer() != null && !(((Classifier)r1.getFeatures().eContainer()).getName().equals(((Classifier)r2.getFeatures().eContainer()).getName())))
        return false;
    }

    if (r1.getRefsTo() == null ^ r2.getRefsTo() == null)
      return false;

    return r1.getRefsTo() == null
        || (r1.getRefsTo().getName() + r1.getRefsTo().isRoot()).equals(r2.getRefsTo().getName() + r2.getRefsTo().isRoot());
  }
}
