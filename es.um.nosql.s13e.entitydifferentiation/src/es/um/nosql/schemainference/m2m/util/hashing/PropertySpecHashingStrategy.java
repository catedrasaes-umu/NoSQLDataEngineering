package es.um.nosql.s13e.m2m.util.hashing;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.emf.ecore.util.EcoreUtil;

import es.um.nosql.s13e.entitydifferentiation.PropertySpec;

public class PropertySpecHashingStrategy implements HashingStrategy<PropertySpec>
{
  private static final long serialVersionUID = 1881778206425842838L;

  public static boolean checkEquality(PropertySpec p0, PropertySpec p1)
  {
    return (new EcoreUtil.EqualityHelper()).equals(p0, p1);
  }

  @Override
  public int computeHashCode(PropertySpec arg0)
  {
    // Minimal condition to be true
    return arg0.hashCode();
  }

  @Override
  public boolean equals(PropertySpec arg0, PropertySpec arg1)
  {
    return checkEquality(arg0, arg1);
  }
}