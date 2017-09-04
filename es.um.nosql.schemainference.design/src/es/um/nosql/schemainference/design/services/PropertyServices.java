package es.um.nosql.schemainference.design.services;

import java.util.ArrayList;
import java.util.List;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import es.um.nosql.schemainference.NoSQLSchema.Aggregate;
import es.um.nosql.schemainference.NoSQLSchema.Association;
import es.um.nosql.schemainference.NoSQLSchema.Attribute;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.NoSQLSchema.Reference;
import es.um.nosql.schemainference.NoSQLSchema.Tuple;

public class PropertyServices
{
	Cache<Entity, List<Attribute>> entityAllAttributesCache = Caffeine.newBuilder().build();
	Cache<Entity, List<Attribute>> entityAllAssociationsCache = Caffeine.newBuilder().build();
	Cache<Entity, List<Attribute>> entityCommonAttributesCache = Caffeine.newBuilder().build();
	Cache<Entity, List<Association>> entityCommonAssociationsCache = Caffeine.newBuilder().build();

	public List<Attribute> getAttributeList(Entity entity)
	{
		List<Attribute> result = new ArrayList<Attribute>();

		for (EntityVersion eVersion : entity.getEntityversions())
		{
			eVersion.getProperties().stream().filter(prop -> prop instanceof Attribute).forEach(
				prop ->
				{
					Attribute attr = (Attribute)prop;

					if (attr.getType() instanceof PrimitiveType)
					{
						PrimitiveType attr1Type = (PrimitiveType)attr.getType();

						if (!result.stream().anyMatch(attr2 -> attr2.getType() instanceof PrimitiveType
							&& attr2.getName().equals(attr.getName())
							&& ((PrimitiveType)attr2.getType()).getName().equals(attr1Type.getName())))
							result.add(attr);
					}
					else if (attr.getType() instanceof Tuple)
					{
						//TODO: Some way of differentiating tuples...
						result.add(attr);
					}
				});
		}
		result.sort((attr1, attr2) -> attr1.getName().compareTo(attr2.getName()));

		return result;
	}

	public List<Association> getAssociationList(Entity entity)
	{
		List<Association> result = new ArrayList<Association>();

		for (EntityVersion eVersion : entity.getEntityversions())
		{
			eVersion.getProperties().stream().filter(prop -> prop instanceof Association).forEach(
				prop ->
				{
					Association assoc = (Association)prop;

					if (assoc instanceof Reference)
					{
						Reference ref = (Reference)assoc;

						if (result.stream().noneMatch(ref2 -> ref2 instanceof Reference
							&& ref.getName().equals(ref2.getName())
							&& (ref.getRefTo().getName().equals(((Reference)ref2).getRefTo().getName()))))
							result.add(ref);
					}
					else if (assoc instanceof Aggregate)
					{
						Aggregate aggr = (Aggregate)assoc;

						// If there was no other Aggregation with the name, just add the Aggregation
						if (result.stream().noneMatch(aggr2 -> aggr2 instanceof Aggregate
							&& aggr.getName().equals(aggr2.getName())))
							result.add(aggr);
						else
						{
							// Otherwise, get the Aggregation with the same name and add all the aggregated objects to the Aggregation
							// Please note that we don't really care about the cardinality of the Aggregation. Otherwise we would need to adjust that...
							Aggregate aggr2 = (Aggregate)result.stream().filter(any -> any instanceof Aggregate && aggr.getName().equals(any.getName())).findFirst().get();
							aggr2.getRefTo().addAll(aggr.getRefTo());
							aggr2.setUpperBound(-1);
						}
					}
				}
			);
		}
		result.sort((assoc1, assoc2) -> assoc1.getName().compareTo(assoc2.getName()));

		return result;
	}

	public List<Attribute> getCommonAttributeList(Entity entity)
	{
		List<Attribute> result = new ArrayList<Attribute>();

		if (entity.getEntityversions().isEmpty())
			return result;

		EntityVersion eVersion = entity.getEntityversions().get(0);

		for (Property prop : eVersion.getProperties())
		  if (prop instanceof Attribute)
		    result.add((Attribute)prop);

		// TODO: Check for common properties of each entity version.
		// TODO: Also provide methods for getting specific attributes of each version...
/*		result = result.stream().filter(attr ->
		{
		  for (int i = 1; i < entity.getEntityversions().size(); i++)
		    if (entity.getEntityversions().get(i).getProperties().stream().anyMatch(predicate))
		      continue;
		    else
		      return false;

		  return true;
		}).collect(Collectors.toList());
*/
		result.sort((attr1, attr2) -> attr1.getName().compareTo(attr2.getName()));

		return result;
	}
}
