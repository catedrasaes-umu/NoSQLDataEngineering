package es.um.nosql.schemainference.m2m.util;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.emf.ecore.util.EcoreUtil;

import es.um.nosql.schemainference.NoSQLSchema.Property;

public class PropertyHashingStrategy implements HashingStrategy<Property>
{
	//private EcoreUtil.EqualityHelper eqh = new EcoreUtil.EqualityHelper();
	/**
	 *
	 */
	private static final long serialVersionUID = 6583397962678333780L;

	public static boolean checkEquality(Property p0, Property p1) {
		return (new EcoreUtil.EqualityHelper()).equals(p0, p1);
	}

	@Override
	public int computeHashCode(Property arg0) {
		// Minimal condition to be true
		return arg0.getName().hashCode();
	}

	@Override
	public boolean equals(Property arg0, Property arg1) {
		return checkEquality(arg0, arg1);
	}
}
