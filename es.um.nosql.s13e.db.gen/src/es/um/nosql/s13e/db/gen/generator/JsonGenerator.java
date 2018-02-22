package es.um.nosql.s13e.db.gen.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Association;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.Entity;
import es.um.nosql.s13e.NoSQLSchema.EntityVersion;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
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
  private Map<EntityVersion, List<ObjectNode>> evMap;
  private Map<String, List<String>> entityIdMap;
  private ArrayNode lStorage;

  private JsonNodeFactory factory = JsonNodeFactory.instance;

  public JsonGenerator()
  {
    pTypeGen = new PrimitiveTypeGen();
    tupleGen = new TupleGen();
    refGen = new ReferenceGen();
    aggrGen = new AggregateGen();
    numGen = NumberGen.GET_INSTANCE();
  }

  public String generate(NoSQLSchema schema) throws Exception
  {
    evMap = new HashMap<EntityVersion, List<ObjectNode>>();
    entityIdMap = new HashMap<String, List<String>>();

    lStorage = factory.arrayNode();

    // First run to generate all the primitive types and tuples.
    for (Entity entity : schema.getEntities())
    {
      entityIdMap.put(entity.getName().toLowerCase(), new ArrayList<String>());
      for (EntityVersion eVersion : entity.getEntityversions())
      {
        evMap.put(eVersion, new ArrayList<ObjectNode>());
        int countInstances = numGen.getInclusiveRandom(Constants.GET_MIN_INSTANCES(), Constants.GET_MAX_INSTANCES());

        for (int i = 0; i < countInstances; i++)
        {
          ObjectNode oNode = factory.objectNode();

          eVersion.getProperties().stream().filter(p -> p instanceof Attribute).forEach(p -> this.generateAttribute(oNode, (Attribute)p));

          // We will override the _id and the type parameters...
          if (eVersion.isRoot())
          {
            oNode.put("_id", pTypeGen.genTrustedPrimitiveType("objectid"));
            oNode.put("_type", entity.getName());
            lStorage.add(oNode);
            entityIdMap.get(entity.getName().toLowerCase()).add(oNode.get("_id").asText());
          }

          evMap.get(eVersion).add(oNode);
        }
      }
    }

    // Second run to generate the references and aggregates since now all the versions and instances exist.
    for (Entity entity : schema.getEntities())
      for (EntityVersion eVersion : entity.getEntityversions())
        for (ObjectNode strObj : evMap.get(eVersion))
          eVersion.getProperties().stream().filter(p -> p instanceof Association).forEach(p -> this.generateAssociation(strObj, (Association)p));

    return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(lStorage);
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
      if (assc instanceof Aggregate)
        oNode.put(assc.getName(), aggrGen.genAggregate((Aggregate)assc, evMap));
  }
}