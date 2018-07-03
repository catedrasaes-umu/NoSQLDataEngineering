package es.um.nosql.s13e.design.services.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;

public class SchemaCollector
{
  public static List<EntityVariation> getEVariationsFromSchema(EntityVariation eVariation)
  {
    List<EntityVariation> result = new ArrayList<EntityVariation>();
    result.addAll(getElementsFromSchema(eVariation).getLeft());

    return result;
  }

  public static List<Entity> getEntitiesFromSchema(EntityVariation eVariation)
  {
    List<Entity> result = new ArrayList<Entity>();
    result.addAll(getElementsFromSchema(eVariation).getRight());

    return result;
  }

  /**
   * Method used to get a list of reduced EntityVariations.
   * This list will gather each EntityVariation and then remove those EntityVariations
   * added because their Entities were referenced. In other words, this method
   * will only get variations explicitly aggregated by the process.
   * @param root The variation to which we want to get the reduced EntityVariations list.
   * @return An EntityVariation list
   */
  public static List<EntityVariation> getReducedEVariationsFromSchema(EntityVariation root)
  {
    List<EntityVariation> result = new ArrayList<EntityVariation>();
    Pair<Set<EntityVariation>, Set<Entity>> elementList = getElementsFromSchema(root);

    for (Entity entity : elementList.getRight())
      for (EntityVariation eVariation : entity.getEntityvariations())
        elementList.getLeft().remove(eVariation);

    result.addAll(elementList.getLeft());

    return result;
  }

  private static Pair<Set<EntityVariation>, Set<Entity>> getElementsFromSchema(EntityVariation eVariation)
  {
    boolean keepDiscovering;
    Pair<Set<EntityVariation>, Set<Entity>> elementList = new Pair<Set<EntityVariation>, Set<Entity>>(
        new HashSet<EntityVariation>(), new HashSet<Entity>());

    keepDiscovering = true;

    Set<EntityVariation> eVToDiscover = new HashSet<EntityVariation>();
    Set<EntityVariation> newEntityVariations = new HashSet<EntityVariation>();
    Set<Entity> newEntities = new HashSet<Entity>();

    eVToDiscover.add(eVariation);

    /*
     * Discovery loop.
     */
    while (keepDiscovering)
    {
      // For each EntityVariation to discover, add more EntityVariations to an auxiliar list.
      for (EntityVariation eV : eVToDiscover)
        newEntityVariations.addAll(getAllEntityVariationsFor(eV));

      // For each EntityVariation to discover, add its Entities to an auxiliar list.
      for (EntityVariation eV : eVToDiscover)
        newEntities.addAll(getAllEntitiesFor(eV));

      // Now that we iterated over each EntityVariation to discover, prepare the list for next iteration.
      // Add the new EntityVariations discovered and the EntityVariations contained in the Entities discovered.
      eVToDiscover.clear();
      eVToDiscover.addAll(newEntityVariations);

      for (Entity entity : newEntities)
        eVToDiscover.addAll(entity.getEntityvariations());

      // Now remove EntityVariations that were already discovered before.
      eVToDiscover.removeAll(elementList.getLeft());

      // And add to the final lists new discoveries.
      elementList.getLeft().addAll(eVToDiscover);
      elementList.getRight().addAll(newEntities);

      newEntityVariations.clear();
      newEntities.clear();

      // Exit if, and only if, there is no more discovers to do.
      keepDiscovering = !eVToDiscover.isEmpty();
    }

    return elementList;
  }

  private static Set<EntityVariation> getAllEntityVariationsFor(EntityVariation eVariation)
  {
    Set<EntityVariation> neweVariations = new HashSet<EntityVariation>();

    for (Property property : eVariation.getProperties())
      if (property instanceof Aggregate)
        for (EntityVariation refToeVariation : ((Aggregate) property).getRefTo())
          neweVariations.add(refToeVariation);

    return neweVariations;
  }

  private static Set<Entity> getAllEntitiesFor(EntityVariation eVariation)
  {
    Set<Entity> newEntities = new HashSet<Entity>();

    for (Property property : eVariation.getProperties())
      if (property instanceof Reference)
        newEntities.add(((Reference) property).getRefTo());

    return newEntities;
  }
}