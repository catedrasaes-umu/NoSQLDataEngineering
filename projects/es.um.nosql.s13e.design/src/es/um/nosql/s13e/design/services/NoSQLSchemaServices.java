package es.um.nosql.s13e.design.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.design.services.util.SchemaCollector;

public class NoSQLSchemaServices
{
  public boolean existsSchemaVariation(NoSQLSchema model)
  {
    return model.getEntities().stream().anyMatch(entity -> entity.isRoot());
  }

  public List<EntityType> getEntitiesForTreeSchemaBranch(NoSQLSchema model)
  {
    List<EntityType> result = new ArrayList<EntityType>();

    result.addAll(model.getEntities().stream().filter(entity -> entity.isRoot()).collect(Collectors.toList()));
    result.sort((entity1, entity2) -> entity1.getName().compareTo(entity2.getName()));

    return result;
  }

  public List<StructuralVariation> getEVariationsForIndexBranch(NoSQLSchema model)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();

    for (EntityType entity : model.getEntities())
      result.addAll(entity.getVariations());

    result.sort((var1, var2) ->
    {
      int compareTo = var1.getContainer().getName().compareTo(var2.getContainer().getName());

      return compareTo != 0 ? compareTo : (var1.getVariationId() > var2.getVariationId() ? 1 : -1);
    });

    return result;
  }

  /**
   * Method used for the EntityType Union Schema viewpoint to gather entities related
   * to any eVariation root.
   * @param entity The entity of which the union is being performed
   * @return A list of Entities
   */
  public List<EntityType> getEntitiesFromEntityClassUnion(EntityType entity)
  {
    List<EntityType> result = new ArrayList<EntityType>();

    if (entity.isRoot())
      entity.getVariations().stream().forEach(var -> result.addAll(getEntitiesFromSchema(var)));      

    return result;
  }

  /**
   * Method used to gather all the entities related to a eVariation root in any way.
   * @param root The eVariation of which we want to collect all the entities
   * @return A list of Entities
   */
  public List<EntityType> getEntitiesFromSchema(StructuralVariation root)
  {
    List<EntityType> result = SchemaCollector.getEntitiesFromSchema(root);
    result.sort((entity1, entity2) -> entity1.getName().compareTo(entity2.getName()));

    return result;
  }

  /**
   * Method used for the EntityClass Union Schema viewpoint to gather eVariations related
   * to any eVariation root.
   * @param entity The entity of which the union is being performed
   * @return A list of eVariations
   */
  public List<StructuralVariation> getEVariationsFromEntityTypeUnion(EntityType entity)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();

    if (entity.isRoot())
      entity.getVariations().stream().forEach(var -> result.addAll(getReducedEVariationsFromSchema(var)));

    return result;
  }

  public List<StructuralVariation> getReducedEVariationsFromSchema(StructuralVariation root)
  {
    return SchemaCollector.getReducedEVariationsFromSchema(root);
  }

  public List<EntityType> getIndirectEntitiesFromSchema(StructuralVariation root)
  {
    List<EntityType> result = SchemaCollector.getEntitiesFromSchema(root);

    root.getProperties().stream().filter(prop -> prop instanceof Reference).forEach(prop ->
    {
      result.remove(((Reference)prop).getRefsTo());
    });

    return result;
  }

  public List<StructuralVariation> getIndirectEVariationsFromSchema(StructuralVariation root)
  {
    List<StructuralVariation> result = SchemaCollector.getReducedEVariationsFromSchema(root);

    root.getProperties().stream().filter(prop -> prop instanceof Aggregate).forEach(prop ->
    {
      result.removeAll(((Aggregate)prop).getAggregates());
    });

    return result;
  }

  public List<StructuralVariation> getEVariationsFromeVariation(StructuralVariation eVariation)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();
    NoSQLSchema model = (NoSQLSchema) eVariation.eContainer().eContainer();

    model.getEntities().sort((entity1, entity2) -> entity1.getName().compareTo(entity2.getName()));

    model.getEntities().stream().filter(entity -> entity.isRoot()).forEach(entity ->
    {
      result.addAll(entity.getVariations().stream()
          .filter(var -> var == eVariation || SchemaCollector.getEVariationsFromSchema(var).contains(eVariation)).collect(Collectors.toList()));
    });

    return result;
  }
}