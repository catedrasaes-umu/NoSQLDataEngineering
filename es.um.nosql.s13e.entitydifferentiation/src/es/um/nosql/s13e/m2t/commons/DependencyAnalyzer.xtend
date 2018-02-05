package es.um.nosql.s13e.m2t.commons

import java.util.Map
import es.um.nosql.s13e.NoSQLSchema.Entity
import java.util.Set
import java.util.List
import es.um.nosql.s13e.entitydifferentiation.PropertySpec
import es.um.nosql.s13e.entitydifferentiation.EntityDiffSpec
import es.um.nosql.s13e.NoSQLSchema.Aggregate
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation

/**
 * This class is designed to analyze the EntityDifferentiation model and
 * fill some collections useful for the generation process, such as dependencies
 * between entities or union attributes.
 */
class DependencyAnalyzer
{
  List<Entity> topOrderEntities
  Map<Entity, Set<Entity>> entityDeps
  Map<Entity, Set<Entity>> inverseEntityDeps
  Map<Entity, EntityDiffSpec> diffByEntity
  Map<Entity, Map<String, List<PropertySpec>>> typeListByPropertyName

 public def performAnalysis(EntityDifferentiation diff)
  {
    val entities = diff.entityDiffSpecs.map[entity]

    diffByEntity = newHashMap(diff.entityDiffSpecs.map[ed | ed.entity -> ed])
    topOrderEntities = calculateDeps(entities)
    typeListByPropertyName = calcTypeListMatrix(entities)
  }

  // Fill, for each property of each entity that appear in more than 
  // one entity version *with different type* (those that hold the needsTypeCheck
  // boolean attribute), the list of types, to check possible type folding in
  // a latter pass
  def calcTypeListMatrix(List<Entity> entities)
  {
    entities.toInvertedMap[e |
      diffByEntity.get(e).entityVersionProps
        .map[propertySpecs]
        .flatten
        .filter[needsTypeCheck].groupBy[property.name]
    ]
  }

  /**
   * Method used to calculate the dependencies between entities, and reorder them in the correct order
   */
  private def calculateDeps(List<Entity> entities) 
  { 
    entityDeps = newHashMap(entities.map[e | e -> getDepsFor(e)])
    inverseEntityDeps = newHashMap(entities.map[e | 
      e -> entities.filter[e2 | entityDeps.get(e2).contains(e)].toSet
    ])

    // Implement a topological order, Khan's algorithm
    // https://en.wikipedia.org/wiki/Topological_sorting#Kahn.27s_algorithm
    topologicalOrder()
  }

  // Get the first level of dependencies for an Entity
  private def getDepsFor(Entity entity)
  {
    entity.entityversions.map[ev | 
      ev.properties.filter[p | p instanceof Aggregate]
      .map[p | (p as Aggregate).refTo.map[ev2 | ev2.eContainer as Entity]]
      .flatten
    ].flatten.toSet
  }

  private def List<Entity> topologicalOrder()
  {
    depListRec(
      entityDeps.filter[k, v| v.empty].keySet,
      newLinkedList(),
      newHashSet()
    )
  }

  private def List<Entity> depListRec(Set<Entity> to_consider, List<Entity> top_order, Set<Entity> seen)
  {
    // End condition
    if (to_consider.isEmpty)
      top_order
    else
    {
      // Recursive
      val e = to_consider.head
      val to_consider_ = to_consider.tail.toSet

      // Add current node (no dependencies to cover)
      top_order.add(e)
      seen.add(e)

      val dependent = inverseEntityDeps.get(e)
      to_consider_.addAll(
        dependent.filter[ d | seen.containsAll(entityDeps.get(d))
      ])

      depListRec(to_consider_, top_order, seen)
    }
  }

  def List<Entity> getTopOrderEntities()
  {
    return topOrderEntities;
  }

  def Map<Entity, Set<Entity>> getEntityDeps()
  {
    return entityDeps;
  }

  def Map<Entity, Map<String, List<PropertySpec>>> getTypeListByPropertyName()
  {
    return typeListByPropertyName;
  }

  def Map<Entity, EntityDiffSpec> getDiffByEntity()
  {
    return diffByEntity;
  }
}