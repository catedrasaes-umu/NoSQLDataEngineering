package es.um.nosql.s13e.design.services.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.EntityClass;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;

public class SchemaCollector
{
  public static List<StructuralVariation> getEVariationsFromSchema(StructuralVariation eVariation)
  {
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();
    result.addAll(getElementsFromSchema(eVariation).getLeft());

    return result;
  }

  public static List<EntityClass> getEntitiesFromSchema(StructuralVariation eVariation)
  {
    List<EntityClass> result = new ArrayList<EntityClass>();
    result.addAll(getElementsFromSchema(eVariation).getRight());

    return result;
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
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();
    Pair<Set<StructuralVariation>, Set<EntityClass>> elementList = getElementsFromSchema(root);

    for (EntityClass entity : elementList.getRight())
      for (StructuralVariation eVariation : entity.getVariations())
        elementList.getLeft().remove(eVariation);

    result.addAll(elementList.getLeft());

    return result;
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

  private static Set<StructuralVariation> getAllStructuralVariationsFor(StructuralVariation eVariation)
  {
    Set<StructuralVariation> neweVariations = new HashSet<StructuralVariation>();

    for (Property property : eVariation.getProperties())
      if (property instanceof Aggregate)
        for (StructuralVariation refToeVariation : ((Aggregate) property).getAggregates())
          neweVariations.add(refToeVariation);

    return neweVariations;
  }

  private static Set<EntityClass> getAllEntitiesFor(StructuralVariation eVariation)
  {
    Set<EntityClass> newEntities = new HashSet<EntityClass>();

    for (Property property : eVariation.getProperties())
      if (property instanceof Reference)
        newEntities.add(((Reference) property).getRefsTo());

    return newEntities;
  }
}