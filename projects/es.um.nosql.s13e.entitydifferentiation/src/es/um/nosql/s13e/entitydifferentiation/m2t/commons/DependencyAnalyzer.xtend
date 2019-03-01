package es.um.nosql.s13e.entitydifferentiation.m2t.commons

import java.util.Map
import es.um.nosql.s13e.NoSQLSchema.EntityClass
import java.util.Set
import java.util.List
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff
import es.um.nosql.s13e.NoSQLSchema.Aggregate
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation

/**
 * This class is designed to analyze the EntityDifferentiation model and
 * fill some collections useful for the generation process, such as dependencies
 * between entities or union attributes.
 */
class DependencyAnalyzer
{
  List<EntityClass> topOrderEntities
  Map<EntityClass, Set<EntityClass>> entityDeps
  Map<EntityClass, Set<EntityClass>> inverseEntityDeps
  Map<EntityClass, EntityDiff> diffByEntity
  Map<EntityClass, Map<String, List<PropertySpec>>> typeListByPropertyName

  def performAnalysis(EntityDifferentiation diff)
  {
    val entities = diff.entityDiffs.map[entity]

    diffByEntity = newHashMap(diff.entityDiffs.map[ed | ed.entity -> ed])
    topOrderEntities = calculateDeps(entities)
    typeListByPropertyName = calcTypeListMatrix(entities)
  }

  // Fill, for each property of each entity that appear in more than 
  // one entity variation *with different type* (those that hold the needsTypeCheck
  // boolean attribute), the list of types, to check possible type folding in
  // a latter pass
  def calcTypeListMatrix(List<EntityClass> entities)
  {
    entities.toInvertedMap[e |
      diffByEntity.get(e).variationDiffs
        .map[propertySpecs]
        .flatten
        .filter[needsTypeCheck].groupBy[property.name]
    ]
  }

  /**
   * Method used to calculate the dependencies between entities, and reorder them in the correct order
   */
  private def calculateDeps(List<EntityClass> entities) 
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
  private def getDepsFor(EntityClass entity)
  {
    entity.variations.map[ev | 
      ev.properties.filter[p | p instanceof Aggregate]
      .map[p | (p as Aggregate).aggregates.map[ev2 | ev2.eContainer as EntityClass]]
      .flatten
    ].flatten.toSet
  }

  private def List<EntityClass> topologicalOrder()
  {
    depListRec(
      entityDeps.filter[k, v| v.empty].keySet,
      newLinkedList(),
      newHashSet()
    )
  }

  private def List<EntityClass> depListRec(Set<EntityClass> to_consider, 
  					List<EntityClass> top_order, Set<EntityClass> seen)
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

  def getTopOrderEntities()
  {
    return topOrderEntities;
  }

  def getEntityDeps()
  {
    return entityDeps;
  }

  def getTypeListByPropertyName()
  {
    return typeListByPropertyName;
  }

  def getDiffByEntity()
  {
    return diffByEntity;
  }
}