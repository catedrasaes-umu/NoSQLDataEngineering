package es.um.nosql.s13e.json2dbschema.process.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import es.um.nosql.s13e.json2dbschema.intermediate.raw.ArraySC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.SchemaComponent;

//TODO: I suspect this is not used anymore...how can we test this?
public class EVariationMerger
{
  public void mergeEquivalentEVs(Map<String, List<SchemaComponent>> rawEntities)
  {
    rawEntities.values().stream().forEach(lSchemaComponent ->
    {
      boolean listModified;

      // While a modification in the list is requested, continue looking for equivalences
      do
      {
        listModified = false;

        // Repeat through all the elements of the list if the list is not modified
        Iterator<SchemaComponent> it = lSchemaComponent.iterator();
        do {
          final SchemaComponent toConsider = it.next();
          for (SchemaComponent sc : lSchemaComponent)
          {
            if (sc != toConsider && mergeEquivalentEVs(toConsider, sc))
            {
              // Update references to the old SchemaComponent
              updateReferences(rawEntities, toConsider, sc);

              ((ObjectSC)sc).count += ((ObjectSC)toConsider).count;
              ((ObjectSC)sc).timestamp = Math.min(((ObjectSC)sc).timestamp, ((ObjectSC)toConsider).timestamp);

              // remove toConsider
              it.remove();

              // Start from the beginning, as the list has been modified
              listModified = true;
              break;
            }
          }
        } while (!listModified && it.hasNext());
      } while (listModified);
    });
  }

  // Check & merge both schema components into the first.
  private boolean mergeEquivalentEVs(SchemaComponent toConsider, SchemaComponent sc)
  {
    return walkAndMerge(null, toConsider, sc);
  }

  private boolean walkAndMerge(String id, SchemaComponent toConsider, SchemaComponent sc)
  {
    // Are both equal classes?
    if (!toConsider.getClass().equals(sc.getClass()))
      return false;

    if (toConsider instanceof ObjectSC)
      return walkAndMerge(id, (ObjectSC)toConsider, (ObjectSC)sc);

    if (toConsider instanceof ArraySC)
      return walkAndMerge(id, (ArraySC)toConsider, (ArraySC)sc);

    return toConsider.equals(sc);
  }

  private boolean walkAndMerge(String id, ObjectSC toConsider, ObjectSC sc)
  {
    if (toConsider.size() != sc.size())
      return false;

    Iterator<Pair<String, SchemaComponent>> toConsiderIt = toConsider.getInners().iterator();
    Iterator<Pair<String, SchemaComponent>> scIt = sc.getInners().iterator();

    while (toConsiderIt.hasNext())
    {
      Pair<String, SchemaComponent> toCP = toConsiderIt.next();
      Pair<String, SchemaComponent> scP = scIt.next();

      if (!toCP.getLeft().equals(scP.getLeft()) ||
          !walkAndMerge(toCP.getLeft(), toCP.getRight(), scP.getRight()))
        return false;
    }

    return true;
  }

  private boolean walkAndMerge(String id, ArraySC toConsider, ArraySC sc)
  {
    if (toConsider.isHomogeneous() != sc.isHomogeneous())
      return false;

    // Special case. Homogeneous arrays?
    if (toConsider.isHomogeneous() && sc.isHomogeneous())
      return homogeneousArraysMerge(id, toConsider, sc);
    else
    {
      // Normal case. Similar to ObjectSC, but without element names
      if (toConsider.size() != sc.size())
        return false;

      Iterator<SchemaComponent> toConsiderIt = toConsider.getInners().iterator();
      Iterator<SchemaComponent> scIt = sc.getInners().iterator();

      while (toConsiderIt.hasNext())
      {
        if (!walkAndMerge(id, toConsiderIt.next(), scIt.next()))
          return false;
      }

      return true;
    }
  }

  private boolean homogeneousArraysMerge(String id, ArraySC toConsider, ArraySC sc)
  {
    // Homogeneous arrays have either zero or one element
    // Not both of them can have zero elements, as they would have merged in the previous
    // phase, so find if any of them has zero size.
    if (toConsider.size() == 0 || sc.size() == 0
        || toConsider.getInners().get(0).equals(sc.getInners().get(0)))
    {
      int lowerBounds = Math.min(toConsider.getLowerBounds(), sc.getLowerBounds());

      // If this is the empty array, then it won't be empty
      if (sc.size() == 0)
        sc.add(toConsider.getInners().get(0));

      // Finally merge!
      sc.setLowerBounds(lowerBounds);
      int upperBounds = Math.max(toConsider.getUpperBounds(), sc.getUpperBounds());
      sc.setUpperBounds(upperBounds);

      return true;
    }

    return false;
  }

  private void updateReferences(Map<String, List<SchemaComponent>> rawEntities, SchemaComponent old, SchemaComponent neew)
  {
    rawEntities.forEach((n,l) ->
    l.forEach(sc -> updateReferences(old,neew, sc)));
  }

  private void updateReferences(SchemaComponent old, SchemaComponent neew, SchemaComponent sc) 
  {
    if (sc instanceof ObjectSC)
      updateReferences(old, neew, (ObjectSC)sc);

    if (sc instanceof ArraySC)
      updateReferences(old, neew, (ArraySC)sc);
  }

  private void updateReferences(SchemaComponent old, SchemaComponent neew, ObjectSC sc) 
  {
    sc.getInners().forEach(p -> {
      if (p.getValue() == old)
        p.setValue(neew);
      else
        updateReferences(old,neew,p.getValue());
    });
  }

  private void updateReferences(SchemaComponent old, SchemaComponent neew, ArraySC sc) 
  {
    sc.getInners().replaceAll(_sc -> _sc == old ? neew : _sc);
    sc.getInners().forEach(_sc -> {
      if (_sc != neew) // Already changed?
        updateReferences(old, neew, _sc);
    });
  }
}
