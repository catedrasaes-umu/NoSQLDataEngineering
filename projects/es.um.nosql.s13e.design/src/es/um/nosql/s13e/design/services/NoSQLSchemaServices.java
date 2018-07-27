package es.um.nosql.s13e.design.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.design.services.util.SchemaCollector;

public class NoSQLSchemaServices
{
  public boolean existsSchemaVariation(NoSQLSchema model)
  {
    for (EntityClass entity : model.getEntities())
      if (entity.isRoot())
        return true;

    return false;
  }

  public List<EntityClass> getEntitiesForTreeSchemaBranch(NoSQLSchema model)
  {
    List<EntityClass> result = new ArrayList<EntityClass>();

    for (EntityClass entity : model.getEntities())
      if (entity.isRoot())
      {
        result.add(entity);
        break;
      }

    result.sort(new Comparator<EntityClass>()
    {
      public int compare(EntityClass e0, EntityClass e1)
      {
        return e0.getName().compareTo(e1.getName());
      }
    });

    return result;
  }

  public List<StructuralVariation> getEVariationsForIndexBranch(NoSQLSchema model)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();

    ECollections.sort(model.getEntities(), new Comparator<EntityClass>()
    {
      public int compare(EntityClass e0, EntityClass e1)
      {
        return e0.getName().compareTo(e1.getName());
      }
    });

    for (EntityClass entity : model.getEntities())
      for (StructuralVariation eVariation : entity.getVariations())
        result.add(eVariation);

    return result;
  }

  public List<StructuralVariation> getEVariationsFromSchema(StructuralVariation root)
  {
    List<StructuralVariation> result = SchemaCollector.getEVariationsFromSchema(root);
    result.sort(new Comparator<StructuralVariation>()
    {
      public int compare(StructuralVariation ev0, StructuralVariation ev1)
      {
        return ev0.getVariationId() > ev1.getVariationId() ? 1 : -1;
      }
    });

    return result;
  }

  /**
   * Method used for the EntityClass Union Schema viewpoint to gather entities related
   * to any eVariation root.
   * 
   * @param entity
   *          The entity of which the union is being performed
   * @return A list of Entities
   */
  public List<EntityClass> getEntitiesFromEntityClassUnion(EntityClass entity)
  {
    List<EntityClass> result = new ArrayList<EntityClass>();

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
  public List<EntityClass> getEntitiesFromSchema(StructuralVariation root)
  {
    List<EntityClass> result = SchemaCollector.getEntitiesFromSchema(root);
    result.sort(new Comparator<EntityClass>()
    {
      public int compare(EntityClass e0, EntityClass e1)
      {
        return e0.getName().compareTo(e1.getName());
      }
    });

    return result;
  }

  /**
   * Method used for the EntityClass Union Schema viewpoint to gather eVariations related
   * to any eVariation root.
   * 
   * @param entity
   *          The entity of which the union is being performed
   * @return A list of eVariations
   */
  public List<StructuralVariation> getEVariationsFromEntityClassUnion(EntityClass entity)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();

    if (!entity.isRoot())
      return result;

    entity.getVariations().stream().forEach(ev -> result.addAll(getReducedEVariationsFromSchema(ev)));

    return result;
  }

  public List<StructuralVariation> getReducedEVariationsFromSchema(StructuralVariation root)
  {
    List<StructuralVariation> result = SchemaCollector.getReducedEVariationsFromSchema(root);

    return result;
  }

  public List<EntityClass> getIndirectEntitiesFromSchema(StructuralVariation root)
  {
    List<EntityClass> result = SchemaCollector.getEntitiesFromSchema(root);

    for (Property prop : root.getProperties())
      if (prop instanceof Reference)
        result.remove(((Reference) prop).getRefsTo());

    return result;
  }

  public List<StructuralVariation> getIndirectEVariationsFromSchema(StructuralVariation root)
  {
    List<StructuralVariation> result = SchemaCollector.getReducedEVariationsFromSchema(root);

    for (Property prop : root.getProperties())
      if (prop instanceof Aggregate)
        result.removeAll(((Aggregate) prop).getAggregates());

    return result;
  }

  public List<StructuralVariation> getEVariationsFromeVariation(StructuralVariation eVariation)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();
    NoSQLSchema model = (NoSQLSchema) eVariation.eContainer().eContainer();

    ECollections.sort(model.getEntities(), new Comparator<EntityClass>()
    {
      public int compare(EntityClass e0, EntityClass e1)
      {
        return e0.getName().compareTo(e1.getName());
      }
    });

    for (EntityClass entity : model.getEntities())
      for (StructuralVariation evInEntityClass : entity.getVariations())
        if (entity.isRoot()
            && (evInEntityClass == eVariation || SchemaCollector.getEVariationsFromSchema(evInEntityClass).contains(eVariation)))
          result.add(evInEntityClass);

    return result;
  }
}