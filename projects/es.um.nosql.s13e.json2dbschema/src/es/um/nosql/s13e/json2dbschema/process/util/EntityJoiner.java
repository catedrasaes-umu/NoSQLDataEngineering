package es.um.nosql.s13e.json2dbschema.process.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.SchemaComponent;

public class EntityJoiner
{
  private static List<String> AggregateHintWords = Arrays.asList(new String[]{"has","with", "set", "list", "setof", "listof", "array", "arrayof", "collection","collectionof"});

  public void joinAggregatedEntities(Map<String, List<SchemaComponent>> rawEntities, Set<String> innerSchemaNames) 
  {
    innerSchemaNames.forEach(iSchemaName ->
    {
      rawEntities.keySet().stream().filter(entity ->
          AggregateHintWords.stream().anyMatch(hintWord ->
            (hintWord + entity).equalsIgnoreCase(iSchemaName) || (entity + hintWord).equalsIgnoreCase(iSchemaName)))
        // Why find first only? If we try to apply this to two entities we will end up with two entities with the same name,
        // but ignoring everything but the first match could lead us to some bad-named entities...
        .findFirst()
        .ifPresent(v ->
        {
          // Change the name of the Entity Name for the new entities that are in turn old entities with slightly different name
          rawEntities.get(iSchemaName).forEach(sc -> ((ObjectSC)sc).entityName = v);

          // And all them at the end of the found entity
          rawEntities.get(v).addAll(rawEntities.get(iSchemaName));
          rawEntities.remove(iSchemaName);
      });
    });
  }
}
