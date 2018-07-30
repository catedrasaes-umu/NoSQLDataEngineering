package es.um.nosql.s13e.json2dbschema.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import es.um.nosql.s13e.json2dbschema.intermediate.raw.ArraySC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.BooleanSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NullSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.NumberSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectIdSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.ObjectSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.SchemaComponent;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.StringSC;
import es.um.nosql.s13e.json2dbschema.intermediate.raw.util.SchemaPrinter;
import es.um.nosql.s13e.json2dbschema.process.util.EVariationMerger;
import es.um.nosql.s13e.json2dbschema.process.util.EntityJoiner;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJArray;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJBoolean;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJElement;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJNumber;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJNull;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJObject;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJObjectId;
import es.um.nosql.s13e.json2dbschema.util.abstractjson.IAJTextual;
import es.um.nosql.s13e.json2dbschema.util.constants.ConfigConstants;
import es.um.nosql.s13e.json2dbschema.util.inflector.Inflector;

import org.apache.commons.lang3.tuple.Pair;

public class SchemaInference
{
  private IAJArray theArray;
  private Map<String, List<SchemaComponent>> rawEntities;
  private Set<String> innerSchemaNames;
  private EVariationMerger merger;
  private EntityJoiner joiner;

  private static final boolean ROOT_OBJECT = true;
  private static final boolean NON_ROOT_OBJECT = false;

  private static final boolean DEBUG = false;

  public SchemaInference(IAJArray rows)
  {
    if(!validateRows(rows))
      throw new IllegalArgumentException("JSON rows do not follow the expected schema: [ {schema: <JSON Object>, count: <Integer>, timestamp: <Long>} ...]");

    theArray = rows;
    rawEntities = new HashMap<String, List<SchemaComponent>>();
    innerSchemaNames = new HashSet<String>();
    joiner = new EntityJoiner();
    merger = new EVariationMerger();
  }

  private boolean validateRows(IAJArray rows)
  {
    // Check just the first element, suppose the rest are correct, as this will be the result of some automated process
    Iterator<IAJElement> iterator = rows.iterator();
    if (!iterator.hasNext())
      return true; // Empty

    IAJObject triple = iterator.next().asObject();
    return Optional.ofNullable(triple.get("schema")).filter(IAJElement::isObject).isPresent() &&
        Optional.ofNullable(triple.get("count")).filter(IAJElement::isNumber).isPresent() &&
        Optional.ofNullable(triple.get("timestamp")).filter(IAJElement::isNumber).isPresent();
  }

  private void innerCountAndTimestamp(Set<String> innerSchemaNames, Map<String, List<SchemaComponent>> rawEntities)
  {
    // For each non-root entity...
    for (String innerSchema : innerSchemaNames)
    {
      // Each of these non-root objects have count and timestamp = 0
      for (SchemaComponent schComponent : rawEntities.get(innerSchema))
      {
        ObjectSC nonRootObj = (ObjectSC)schComponent;

        rawEntities.values().stream().flatMap(List::stream)
        .forEach(sc -> {
          ObjectSC osc = (ObjectSC)sc;
          if (osc.getInners().stream().map(Pair::getValue)
              .anyMatch(innerSchComponent -> 
                (innerSchComponent instanceof ArraySC && ((ArraySC)innerSchComponent).getInners().contains(nonRootObj))
                  || (innerSchComponent instanceof ObjectSC && innerSchComponent.equals(nonRootObj))))
          {
            nonRootObj.count += osc.count;

            if (nonRootObj.timestamp == 0 || osc.timestamp < nonRootObj.timestamp)
              nonRootObj.timestamp = osc.timestamp;
          }
        });
      }
    }
  }

  public Map<String, List<SchemaComponent>> infer()
  {
    theArray.forEach(n -> {
      infer(n.get("schema"), Optional.<String>empty(), ROOT_OBJECT, n.get("count").asLong(), n.get("timestamp").asLong());
    });

    joiner.joinAggregatedEntities(rawEntities, innerSchemaNames);
    innerCountAndTimestamp(innerSchemaNames, rawEntities);
    merger.mergeEquivalentEVs(rawEntities);

    if (DEBUG)
      SchemaPrinter.schemaEntities(rawEntities);

    return rawEntities;
  }

  private SchemaComponent infer(IAJElement n, Optional<String> elementName, boolean isRoot, long count, long timestamp)
  {
    if (n.isObject())
      return infer(n.asObject(), elementName, isRoot, count, timestamp);

    if (n.isArray())
      return infer(n.asArray(), elementName.get());

    if (n.isBoolean())
      return infer(n.asBoolean(), elementName.get());

    if(n.isNumber())
      return infer(n.asNumber(), elementName.get());

    if (n.isNull())
      return infer(n.asNull(), elementName.get());

    if (n.isTextual())
      return infer(n.asTextual(), elementName.get());

    if (n.isObjectId())
      return infer(n.asObjectId(), elementName.get());

    assert(false);

    return null;
  }

  private SchemaComponent infer(IAJObject n, Optional<String> elementName, boolean isRoot, long count, long timestamp)
  {
    // Entity names are by convention capitalized
    Optional<String> typeName = Optional.empty();
    // TODO: Remember now a variation cant be root, it is entity which may be root.
    if (isRoot)
      typeName = Optional.ofNullable(n.get("_type")).map(_n -> Inflector.getInstance().capitalize(_n.asString()));

    ObjectSC schema = new ObjectSC();
    schema.isRoot = isRoot;
    schema.count = count;
    schema.timestamp = timestamp;
    schema.entityName = typeName.orElse(Inflector.getInstance().capitalize(elementName.orElse("")));

    // It is important this is a sorted set
    SortedSet<String> fields = new TreeSet<String>();
    n.getFieldNames().forEachRemaining(field ->
    {
      if (!ConfigConstants.IGNORED_ATTRIBUTES.contains(field))
        fields.add(field);
    });

    // Recursive phase
    schema.addAll(fields.stream()
        .map(f -> Pair.of(f, infer(n.get(f), Optional.of(f), NON_ROOT_OBJECT, 0, 0)))::iterator);

    // Now that we have the complete schema, try to compare it with any of the variations in the map
    List<SchemaComponent> entityVariations = rawEntities.get(schema.entityName);
    SchemaComponent retSchema = schema;

    if (entityVariations != null)
    {
      Optional<SchemaComponent> foundSchema =
          entityVariations.stream().filter(sc -> schema.equals(sc)).findFirst();
      if (foundSchema.isPresent())
        retSchema = foundSchema.get();
      else
        entityVariations.add(schema);
    }
    else
    {
      List<SchemaComponent> ll = new ArrayList<SchemaComponent>(10);
      ll.add(schema);
      rawEntities.put(schema.entityName, ll);

      // Add the name of this entity to the list of afterward checking for already existing entities 
      if (!isRoot)
        innerSchemaNames.add(schema.entityName);
    }

    return retSchema;
  }

  private SchemaComponent infer(IAJArray n, String elementName)
  {
    ArraySC schema = new ArraySC();

    // TODO: At this point we should use the ReferenceMatcher to test verbs such as has or Id.
    // If the name for this array can be made singular, do it.
    String singularName = Inflector.getInstance().singularize(elementName);

    final Optional<String> name = Optional.of(singularName);

    // Before adding a SchemaComponent to the array we first check there is not another similar object.
    // This way we simplify Aggr{V1, V2, V2, V2...V2} to Aggr{V1, V2}
    n.forEach(e ->
    {
      SchemaComponent sComponent = infer(e, name, NON_ROOT_OBJECT, 0, 0);

      if (!schema.getInners().stream().anyMatch(sc -> sc.equals(sComponent)))
        schema.add(sComponent);
    });

    return schema;
  }

  private SchemaComponent infer(IAJBoolean n, String elementName)
  {
    BooleanSC schema = new BooleanSC();
    return schema;
  }

  private SchemaComponent infer(IAJNumber n, String elementName)
  {
    NumberSC schema = new NumberSC();
    return schema;
  }

  private SchemaComponent infer(IAJNull n, String elementName)
  {
    NullSC schema = new NullSC();
    return schema;
  }

  private SchemaComponent infer(IAJTextual n, String elementName)
  {
    StringSC schema = new StringSC();
    return schema;
  }

  private SchemaComponent infer(IAJObjectId n, String elementName)
  {
    ObjectIdSC schema = new ObjectIdSC();
    return schema;
  }
}
