package es.um.nosql.schemainference.design.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.schemainference.NoSQLSchema.Aggregate;
import es.um.nosql.schemainference.NoSQLSchema.Entity;
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion;
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema;
import es.um.nosql.schemainference.NoSQLSchema.Property;
import es.um.nosql.schemainference.NoSQLSchema.Reference;
import es.um.nosql.schemainference.design.services.util.SchemaCollector;

public class NoSQLSchemaServices
{
	public boolean existsSchemaVersion(NoSQLSchema model)
	{
		for (Entity entity : model.getEntities())
			for (EntityVersion eVersion : entity.getEntityversions())
				if (eVersion.isRoot())
					return true;

		return false;
	}

	public List<Entity> getEntitiesForTreeSchemaBranch(NoSQLSchema model)
	{
		List<Entity> result = new ArrayList<Entity>();

		for (Entity entity : model.getEntities())
			for (EntityVersion eVersion : entity.getEntityversions())
				if (eVersion.isRoot())
				{
					result.add(entity);
					break;
				}

		result.sort(new Comparator<Entity>()
		{
			public int compare(Entity e0, Entity e1)
			{
				return e0.getName().compareTo(e1.getName());
			}
		});

		return result;
	}

	public List<EntityVersion> getEVersionsForIndexBranch(NoSQLSchema model)
	{
		List<EntityVersion> result = new ArrayList<EntityVersion>();

		ECollections.sort(model.getEntities(), new Comparator<Entity>()
		{
			public int compare(Entity e0, Entity e1)
			{
				return e0.getName().compareTo(e1.getName());
			}
		});

		for (Entity entity : model.getEntities())
			for (EntityVersion eVersion : entity.getEntityversions())
				result.add(eVersion);

		return result;
	}

	public List<EntityVersion> getEVersionsFromSchema(EntityVersion root)
	{
		List<EntityVersion> result = SchemaCollector.getEVersionsFromSchema(root);
		result.sort(new Comparator<EntityVersion>()
		{
			public int compare(EntityVersion ev0, EntityVersion ev1)
			{
				return ev0.getVersionId() > ev1.getVersionId() ? 1 : -1;
			}
		});

		return result;
/*
		Cache<EntityVersion, List<EntityVersion>> rootsCache = Caffeine.newBuilder().build();
		return rootsCache.get(root,
				ev ->
				{
					List<EntityVersion> result = SchemaCollector.getEVersionsFromSchema(ev);
					result.sort(new Comparator<EntityVersion>()
					{
						public int compare(EntityVersion ev0, EntityVersion ev1)
						{
							return ev0.getVersionId() > ev1.getVersionId() ? 1 : -1;
						}
					});
					return result;
				});*/
	}

	public List<Entity> getEntitiesFromSchema(EntityVersion root)
	{
		List<Entity> result = SchemaCollector.getEntitiesFromSchema(root);
		result.sort(new Comparator<Entity>()
		{
			public int compare(Entity e0, Entity e1)
			{
				return e0.getName().compareTo(e1.getName());
			}
		});

		return result;
	}

	public List<EntityVersion> getReducedEVersionsFromSchema(EntityVersion root)
	{
		List<EntityVersion> result = SchemaCollector.getReducedEVersionsFromSchema(root);

		return result;
	}

	public List<Entity> getIndirectEntitiesFromSchema(EntityVersion root)
	{
		List<Entity> result = SchemaCollector.getEntitiesFromSchema(root);

		for (Property prop : root.getProperties())
			if (prop instanceof Reference)
				result.remove(((Reference)prop).getRefTo());

		return result;
	}

	public List<EntityVersion> getIndirectEVersionsFromSchema(EntityVersion root)
	{
		List<EntityVersion> result = SchemaCollector.getReducedEVersionsFromSchema(root);

		for (Property prop : root.getProperties())
			if (prop instanceof Aggregate)
				result.removeAll(((Aggregate)prop).getRefTo());

		return result;
	}

	public List<EntityVersion> getEVersionsFromEVersion(EntityVersion eVersion)
	{
		List<EntityVersion> result = new ArrayList<EntityVersion>();
		NoSQLSchema model = (NoSQLSchema)eVersion.eContainer().eContainer();

		ECollections.sort(model.getEntities(), new Comparator<Entity>()
		{
			public int compare(Entity e0, Entity e1)
			{
				return e0.getName().compareTo(e1.getName());
			}
		});

		for (Entity entity : model.getEntities())
			for (EntityVersion evInEntity : entity.getEntityversions())
				if (evInEntity.isRoot() && (evInEntity == eVersion || SchemaCollector.getEVersionsFromSchema(evInEntity).contains(eVersion)))
					result.add(evInEntity);

		return result;
	}
}
