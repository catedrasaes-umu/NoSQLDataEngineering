package es.um.nosql.s13e.design.services.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;

public class SchemaCollector
{
  public static List<EntityVersion> getEVersionsFromSchema(EntityVersion eVersion)
  {
    List<EntityVersion> result = new ArrayList<EntityVersion>();
    result.addAll(getElementsFromSchema(eVersion).getLeft());

    return result;
  }

  public static List<Entity> getEntitiesFromSchema(EntityVersion eVersion)
  {
    List<Entity> result = new ArrayList<Entity>();
    result.addAll(getElementsFromSchema(eVersion).getRight());

    return result;
  }

  /**
   * Method used to get a list of reduced EntityVersions.
   * This list will gather each EntityVersion and then remove those EntityVersions
   * added because their Entities were referenced. In other words, this method
   * will only get versions explicitly aggregated by the process.
   * @param root The version to which we want to get the reduced EntityVersions list.
   * @return An EntityVersion list
   */
  public static List<EntityVersion> getReducedEVersionsFromSchema(EntityVersion root)
  {
    List<EntityVersion> result = new ArrayList<EntityVersion>();
    Pair<Set<EntityVersion>, Set<Entity>> elementList = getElementsFromSchema(root);

    for (Entity entity : elementList.getRight())
      for (EntityVersion eVersion : entity.getEntityversions())
        elementList.getLeft().remove(eVersion);

    result.addAll(elementList.getLeft());

    return result;
  }

  private static Pair<Set<EntityVersion>, Set<Entity>> getElementsFromSchema(EntityVersion eVersion)
  {
    boolean keepDiscovering;
    Pair<Set<EntityVersion>, Set<Entity>> elementList = new Pair<Set<EntityVersion>, Set<Entity>>(
        new HashSet<EntityVersion>(), new HashSet<Entity>());

    keepDiscovering = true;

    Set<EntityVersion> eVToDiscover = new HashSet<EntityVersion>();
    Set<EntityVersion> newEntityVersions = new HashSet<EntityVersion>();
    Set<Entity> newEntities = new HashSet<Entity>();

    eVToDiscover.add(eVersion);

    /*
     * Discovery loop.
     */
    while (keepDiscovering)
    {
      // For each EntityVersion to discover, add more EntityVersions to an auxiliar list.
      for (EntityVersion eV : eVToDiscover)
        newEntityVersions.addAll(getAllEntityVersionsFor(eV));

      // For each EntityVersion to discover, add its Entities to an auxiliar list.
      for (EntityVersion eV : eVToDiscover)
        newEntities.addAll(getAllEntitiesFor(eV));

      // Now that we iterated over each EntityVersion to discover, prepare the list for next iteration.
      // Add the new EntityVersions discovered and the EntityVersions contained in the Entities discovered.
      eVToDiscover.clear();
      eVToDiscover.addAll(newEntityVersions);

      for (Entity entity : newEntities)
        eVToDiscover.addAll(entity.getEntityversions());

      // Now remove EntityVersions that were already discovered before.
      eVToDiscover.removeAll(elementList.getLeft());

      // And add to the final lists new discoveries.
      elementList.getLeft().addAll(eVToDiscover);
      elementList.getRight().addAll(newEntities);

      newEntityVersions.clear();
      newEntities.clear();

      // Exit if, and only if, there is no more discovers to do.
      keepDiscovering = !eVToDiscover.isEmpty();
    }

    return elementList;
  }

  private static Set<EntityVersion> getAllEntityVersionsFor(EntityVersion eVersion)
  {
    Set<EntityVersion> newEVersions = new HashSet<EntityVersion>();

    for (Property property : eVersion.getProperties())
      if (property instanceof Aggregate)
        for (EntityVersion refToeVersion : ((Aggregate) property).getRefTo())
          newEVersions.add(refToeVersion);

    return newEVersions;
  }

  private static Set<Entity> getAllEntitiesFor(EntityVersion eVersion)
  {
    Set<Entity> newEntities = new HashSet<Entity>();

    for (Property property : eVersion.getProperties())
      if (property instanceof Reference)
        newEntities.add(((Reference) property).getRefTo());

    return newEntities;
  }
}