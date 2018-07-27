package es.um.nosql.s13e.design.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.design.services.util.SchemaCollector;

public class NoSQLSchemaServices
{
  public boolean existsSchemaVariation(NoSQLSchema model)
  {
    for (Entity entity : model.getEntities())
      if (entity.isRoot())
        return true;

    return false;
  }

  public List<Entity> getEntitiesForTreeSchemaBranch(NoSQLSchema model)
  {
    List<Entity> result = new ArrayList<Entity>();

    for (Entity entity : model.getEntities())
      if (entity.isRoot())
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

  public List<EntityVariation> getEVariationsForIndexBranch(NoSQLSchema model)
  {
    List<EntityVariation> result = new ArrayList<EntityVariation>();

    ECollections.sort(model.getEntities(), new Comparator<Entity>()
    {
      public int compare(Entity e0, Entity e1)
      {
        return e0.getName().compareTo(e1.getName());
      }
    });

    for (Entity entity : model.getEntities())
      for (EntityVariation eVariation : entity.getVariations())
        result.add(eVariation);

    return result;
  }

  public List<EntityVariation> getEVariationsFromSchema(EntityVariation root)
  {
    List<EntityVariation> result = SchemaCollector.getEVariationsFromSchema(root);
    result.sort(new Comparator<EntityVariation>()
    {
      public int compare(EntityVariation ev0, EntityVariation ev1)
      {
        return ev0.getVariationId() > ev1.getVariationId() ? 1 : -1;
      }
    });

    return result;
  }

  /**
   * Method used for the Entity Union Schema viewpoint to gather entities related
   * to any eVariation root.
   * 
   * @param entity
   *          The entity of which the union is being performed
   * @return A list of Entities
   */
  public List<Entity> getEntitiesFromEntityUnion(Entity entity)
  {
    List<Entity> result = new ArrayList<Entity>();

    if (!entity.isRoot())
      return result;

    entity.getVariations().stream().forEach(ev -> result.addAll(getEntitiesFromSchema(ev)));

    return result;
  }

  /**
   * Method used to gather all the entities related to a eVariation root in any way.
   * 
   * @param root The eVariation of which we want to collect all the entities
   * @return A list of Entities
   */
  public List<Entity> getEntitiesFromSchema(EntityVariation root)
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
   * Method used for the Entity Union Schema viewpoint to gather eVariations related
   * to any eVariation root.
   * 
   * @param entity
   *          The entity of which the union is being performed
   * @return A list of eVariations
   */
  public List<EntityVariation> getEVariationsFromEntityUnion(Entity entity)
  {
    List<EntityVariation> result = new ArrayList<EntityVariation>();

    if (!entity.isRoot())
      return result;

    entity.getVariations().stream().forEach(ev -> result.addAll(getReducedEVariationsFromSchema(ev)));

    return result;
  }

  public List<EntityVariation> getReducedEVariationsFromSchema(EntityVariation root)
  {
    List<EntityVariation> result = SchemaCollector.getReducedEVariationsFromSchema(root);

    return result;
  }

  public List<Entity> getIndirectEntitiesFromSchema(EntityVariation root)
  {
    List<Entity> result = SchemaCollector.getEntitiesFromSchema(root);

    for (Property prop : root.getProperties())
      if (prop instanceof Reference)
        result.remove(((Reference) prop).getRefTo());

    return result;
  }

  public List<EntityVariation> getIndirectEVariationsFromSchema(EntityVariation root)
  {
    List<EntityVariation> result = SchemaCollector.getReducedEVariationsFromSchema(root);

    for (Property prop : root.getProperties())
      if (prop instanceof Aggregate)
        result.removeAll(((Aggregate) prop).getRefTo());

    return result;
  }

  public List<EntityVariation> getEVariationsFromeVariation(EntityVariation eVariation)
  {
    List<EntityVariation> result = new ArrayList<EntityVariation>();
    NoSQLSchema model = (NoSQLSchema) eVariation.eContainer().eContainer();

    ECollections.sort(model.getEntities(), new Comparator<Entity>()
    {
      public int compare(Entity e0, Entity e1)
      {
        return e0.getName().compareTo(e1.getName());
      }
    });

    for (Entity entity : model.getEntities())
      for (EntityVariation evInEntity : entity.getVariations())
        if (entity.isRoot()
            && (evInEntity == eVariation || SchemaCollector.getEVariationsFromSchema(evInEntity).contains(eVariation)))
          result.add(evInEntity);

    return result;
  }
}