package es.um.nosql.s13e.m2m.util.hashing;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.emf.ecore.util.EcoreUtil;

import es.um.nosql.s13e.NoSQLSchema.Property;

public class PropertyHashingStrategy implements HashingStrategy<Property>
{
  private static final long serialVersionUID = 6583397962678333780L;

  public static boolean checkEquality(Property p0, Property p1)
  {
    return (new EcoreUtil.EqualityHelper()).equals(p0, p1);
  }

  @Override
  public int computeHashCode(Property arg0)
  {
    // Minimal condition to be true
    return arg0.getName().hashCode();
  }

  @Override
  public boolean equals(Property arg0, Property arg1)
  {
    return checkEquality(arg0, arg1);
  }
}