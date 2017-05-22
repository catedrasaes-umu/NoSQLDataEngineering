package es.um.nosql.schemainference.design.services.util;

import org.eclipse.emf.common.util.EList;

import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;

public class ListOperations
{
	public static boolean checkTwoEntityListNames(EList<EntityVersion> l1, EList<EntityVersion> l2)
	{
		//TODO: Not quiet there.
		if (l1 == null || l2 == null || l1.size() != l2.size())
			return false;
/*
		l1.stream().for
		for (Entity e : l1)
			if (l2)
*/
		return true;
	}
}
