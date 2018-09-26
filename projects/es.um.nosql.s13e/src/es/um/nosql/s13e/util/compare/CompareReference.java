package es.um.nosql.s13e.util.compare;

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

    if (r1.getOpposite() != null && !new CompareReference().compare(r1.getOpposite(), r2.getOpposite()))
      return false;

    if (r1.getFeatures() == null ^ r2.getFeatures() == null)
      return false;

    if (r1.getFeatures() != null && !new CompareStructuralVariation().compare(r1.getFeatures(), r2.getFeatures()))
      return false;

    if (r1.getRefsTo() == null ^ r2.getRefsTo() == null)
      return false;

    return r1.getRefsTo() == null || new CompareClassifier().compare(r1.getRefsTo(), r2.getRefsTo());
  }
}
