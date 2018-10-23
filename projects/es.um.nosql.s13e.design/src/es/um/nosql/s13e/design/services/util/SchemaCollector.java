package es.um.nosql.s13e.design.services.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.Reference;

public class SchemaCollector
{
  public static List<StructuralVariation> getEVariationsFromSchema(StructuralVariation var)
  {
    return getElementsFromSchema(var).getLeft().stream().collect(Collectors.toList());
  }

  public static List<EntityClass> getEntitiesFromSchema(StructuralVariation var)
  {
    return getElementsFromSchema(var).getRight().stream().collect(Collectors.toList());
  }

  /**
   * Method used to get a list of reduced StructuralVariations.
   * This list will gather each StructuralVariation and then remove those StructuralVariations
   * added because their Entities were referenced. In other words, this method
   * will only get variations explicitly aggregated by the process.
   * @param root The variation to which we want to get the reduced StructuralVariations list.
   * @return An StructuralVariation list
   */
  public static List<StructuralVariation> getReducedEVariationsFromSchema(StructuralVariation root)
  {
    Pair<Set<StructuralVariation>, Set<EntityClass>> elementList = getElementsFromSchema(root);

    elementList.getRight().forEach(entity ->
    {
      entity.getVariations().stream().forEach(var ->
      {
        elementList.getLeft().remove(var);
      });
    });

    return elementList.getLeft().stream().collect(Collectors.toList());
  }

  private static Pair<Set<StructuralVariation>, Set<EntityClass>> getElementsFromSchema(StructuralVariation eVariation)
  {
    boolean keepDiscovering;
    Pair<Set<StructuralVariation>, Set<EntityClass>> elementList = new Pair<Set<StructuralVariation>, Set<EntityClass>>(
        new HashSet<StructuralVariation>(), new HashSet<EntityClass>());

    keepDiscovering = true;

    Set<StructuralVariation> eVToDiscover = new HashSet<StructuralVariation>();
    Set<StructuralVariation> newStructuralVariations = new HashSet<StructuralVariation>();
    Set<EntityClass> newEntities = new HashSet<EntityClass>();

    eVToDiscover.add(eVariation);

    /*
     * Discovery loop.
     */
    while (keepDiscovering)
    {
      // For each StructuralVariation to discover, add more StructuralVariations to an auxiliar list.
      for (StructuralVariation eV : eVToDiscover)
        newStructuralVariations.addAll(getAllStructuralVariationsFor(eV));

      // For each StructuralVariation to discover, add its Entities to an auxiliar list.
      for (StructuralVariation eV : eVToDiscover)
        newEntities.addAll(getAllEntitiesFor(eV));

      // Now that we iterated over each StructuralVariation to discover, prepare the list for next iteration.
      // Add the new StructuralVariations discovered and the StructuralVariations contained in the Entities discovered.
      eVToDiscover.clear();
      eVToDiscover.addAll(newStructuralVariations);

      for (EntityClass entity : newEntities)
        eVToDiscover.addAll(entity.getVariations());

      // Now remove StructuralVariations that were already discovered before.
      eVToDiscover.removeAll(elementList.getLeft());

      // And add to the final lists new discoveries.
      elementList.getLeft().addAll(eVToDiscover);
      elementList.getRight().addAll(newEntities);

      newStructuralVariations.clear();
      newEntities.clear();

      // Exit if, and only if, there is no more discovers to do.
      keepDiscovering = !eVToDiscover.isEmpty();
    }

    return elementList;
  }

  private static Set<StructuralVariation> getAllStructuralVariationsFor(StructuralVariation var)
  {
    return var.getProperties().stream()
        .filter(prop -> prop instanceof Aggregate)
        .flatMap(prop -> ((Aggregate)prop).getAggregates().stream())
        .collect(Collectors.toSet());
  }

  private static Set<EntityClass> getAllEntitiesFor(StructuralVariation var)
  {
    return var.getProperties().stream()
        .filter(prop -> prop instanceof Reference)
        .map(prop -> ((Reference)prop).getRefsTo())
        .collect(Collectors.toSet());
  }
}
