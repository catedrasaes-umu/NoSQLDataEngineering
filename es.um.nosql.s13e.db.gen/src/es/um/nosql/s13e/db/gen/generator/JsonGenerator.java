package es.um.nosql.s13e.db.gen.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.utils.Constants;

public class JsonGenerator
{
  private PrimitiveTypeGen pTypeGen;
  private TupleGen tupleGen;
  private ReferenceGen refGen;
  private AggregateGen aggrGen;
  private NumberGen numGen;
  private Map<String, List<JsonNode>> entityIdMap;
  private Map<EntityVersion, List<ObjectNode>> evMap;
  private ArrayNode lStorage;

  private JsonNodeFactory factory = JsonNodeFactory.instance;

  public JsonGenerator()
  {
    pTypeGen = new PrimitiveTypeGen();
    tupleGen = new TupleGen();
    refGen = new ReferenceGen();
    aggrGen = new AggregateGen();
    numGen = NumberGen.GET_INSTANCE();
    entityIdMap = new HashMap<String, List<JsonNode>>();
    evMap = new HashMap<EntityVersion, List<ObjectNode>>();
  }

  public String generate(NoSQLSchema schema) throws Exception
  {
    //TODO: Maybe we should try to divide the work into several splits so we don't get blocked by passing a really gigantic JSON.
    lStorage = factory.arrayNode();

    // First run to generate all the primitive types and tuples.
    for (Entity entity : schema.getEntities())
    {
      entityIdMap.put(entity.getName(), new ArrayList<JsonNode>());

      for (EntityVersion eVersion : entity.getEntityversions())
      {
        evMap.put(eVersion, new ArrayList<ObjectNode>());

        for (int i = 0; i < numGen.getInclusiveRandom(Constants.GET_MIN_INSTANCES(), Constants.GET_MAX_INSTANCES()); i++)
        {
          ObjectNode oNode = factory.objectNode();
          evMap.get(eVersion).add(oNode);

          eVersion.getProperties().stream().filter(p -> p instanceof Attribute && !p.getName().equals("_id")).forEach(p -> this.generateAttribute(oNode, (Attribute)p));

          if (eVersion.isRoot())
          {
            lStorage.add(oNode);
            this.generateMetadata(oNode, entity.getName(), eVersion.getProperties().stream()
                .filter(p -> p instanceof Attribute && p.getName().equals("_id")).map(p -> (Attribute)p)
                .findFirst());
          }
        }
      }
    }

    // Second run to generate the references and aggregates since now all the versions and instances exist.
    for (Entity entity : schema.getEntities())
      for (EntityVersion eVersion : entity.getEntityversions())
        for (ObjectNode strObj : evMap.get(eVersion))
          eVersion.getProperties().stream().filter(p -> p instanceof Association).forEach(p -> this.generateAssociation(strObj, (Association)p));

    evMap.clear();
    entityIdMap.clear();

    return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lStorage);
  }

  private void generateMetadata(ObjectNode oNode, String entityName, Optional<Attribute> theId)
  {
    if (!theId.isPresent())
      oNode.put("_id", pTypeGen.genTrustedObjectId("objectid"));
    else
      oNode.put("_id", pTypeGen.genTrustedObjectId(((PrimitiveType)theId.get().getType()).getName()));

    entityIdMap.get(entityName).add(oNode.get("_id"));      

    if (Constants.GET_ENTITY_INCLUDE_TYPE() && !oNode.has("_type"))
      oNode.put("_type", entityName);
  }

  private void generateAttribute(ObjectNode oNode, Attribute attr)
  {
    if (attr.getType() instanceof PrimitiveType)
      oNode.put(attr.getName(), pTypeGen.genPrimitiveType(((PrimitiveType)attr.getType()).getName()));
    else if (attr.getType() instanceof Tuple)
      oNode.put(attr.getName(), tupleGen.genTuple(((Tuple)attr.getType()).getElements()));
  }

  private void generateAssociation(ObjectNode oNode, Association assc)
  {
      if (assc instanceof Reference)
        oNode.put(assc.getName(), refGen.genReference((Reference)assc, entityIdMap));
//      if (assc instanceof Aggregate)
//        oNode.put(assc.getName(), aggrGen.genAggregate((Aggregate)assc, evMap));
  }
}