package es.um.nosql.s13e.design.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.design.services.util.SchemaCollector;

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
	}

	/**
	 * Method used for the Entity Union Schema viewpoint to gather entities related to
	 * any EVersion root.
	 * @param entity The entity of which the union is being performed
	 * @return A list of Entities
	 */
	public List<Entity> getEntitiesFromEntityUnion(Entity entity)
	{
		List<Entity> result = new ArrayList<Entity>();

		entity.getEntityversions().stream().filter(ev -> ev.isRoot()).forEach(ev -> result.addAll(getEntitiesFromSchema(ev)));

		return result;
	}

	/**
	 * Method used to gather all the entities related to a EVersion root in any way.
	 * @param root The EVersion of which we want to collect all the entities
	 * @return A list of Entities
	 */
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

	/**
	 * Method used for the Entity Union Schema viewpoint to gather EVersions related to
	 * any EVersion root.
	 * @param entity The entity of which the union is being performed
	 * @return A list of EVersions
	 */
	public List<EntityVersion> getEVersionsFromEntityUnion(Entity entity)
	{
		List<EntityVersion> result = new ArrayList<EntityVersion>();

		entity.getEntityversions().stream().filter(ev -> ev.isRoot()).forEach(ev -> result.addAll(getReducedEVersionsFromSchema(ev)));

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
