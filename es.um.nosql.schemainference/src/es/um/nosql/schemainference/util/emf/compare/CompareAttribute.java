package es.um.nosql.schemainference.util.emf.compare;

import org.eclipse.emf.ecore.util.EcoreUtil;
import es.um.nosql.schemainference.NoSQLSchema.Attribute;

public class CompareAttribute extends CompareProperty<Attribute>
{
	@Override
	public boolean compare(Attribute t1, Attribute t2) {
		if (!super.compare(t1, t2))
			return false;

		// Compare type
		return (new EcoreUtil.EqualityHelper()).equals(t1.getType(), t2.getType());
	}

}
