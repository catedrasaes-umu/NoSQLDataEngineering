package es.um.nosql.s13e.db.gen.generator;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.NoSQLSchema.Type;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.BooleanGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.utils.Constants;

public class TupleGen
{
  private PrimitiveTypeGen pTypeGen;
  private NumberGen numGen;
  private BooleanGen boolGen;
  private List<String> definedTypes;

  public TupleGen()
  {
    pTypeGen = new PrimitiveTypeGen();
    numGen = NumberGen.GET_INSTANCE();
    boolGen = BooleanGen.GET_INSTANCE();
    definedTypes  = Arrays.asList("string", "int", "double", "bool", "objectid");
  }

  public ArrayNode genTuple(List<Type> elements)
  {
    ArrayNode result = JsonNodeFactory.instance.arrayNode();

    for (Type type : elements)
      for (int i = 0; i < numGen.getInclusiveRandom(Constants.GET_TUPLE_MIN_TUPLE_ELEMENTS(), Constants.GET_TUPLE_MAX_TUPLE_ELEMENTS()); i++)
        if (type instanceof PrimitiveType)
        {
          String theType = ((PrimitiveType)type).getName();

          if (boolGen.thisHappens(Constants.GET_TUPLE_NULL_PROBABILITY()))
            theType = "null";
          else if (boolGen.thisHappens(Constants.GET_TUPLE_STRANGE_TYPES_PROBABILITY()))
            theType = definedTypes.get(numGen.getExclusiveRandom(0, definedTypes.size()));

          result.add(pTypeGen.genTrustedPrimitiveType(theType));
        }
        else if (type instanceof Tuple)
          result.add(genTuple(((Tuple)type).getElements()));

    return result;
  }
}