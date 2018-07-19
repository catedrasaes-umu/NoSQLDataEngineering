package es.um.nosql.s13e.db.gen.generator.properties;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.NoSQLSchema.Type;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.BooleanGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;
import es.um.nosql.s13e.db.gen.util.constants.Types;

public class TupleGen
{
  private PrimitiveTypeGen pTypeGen;
  private NumberGen numGen;
  private BooleanGen boolGen;

  public TupleGen()
  {
    pTypeGen = new PrimitiveTypeGen();
    numGen = NumberGen.GET_INSTANCE();
    boolGen = BooleanGen.GET_INSTANCE();
  }

  public ArrayNode genTuple(List<Type> elements)
  {
    ArrayNode result = JsonNodeFactory.instance.arrayNode();

    for (Type type : elements)
      for (int i = 0; i < numGen.getInclusiveRandom(ConfigConstants.GET_TUPLE_MIN_TUPLE_ELEMENTS(), ConfigConstants.GET_TUPLE_MAX_TUPLE_ELEMENTS()); i++)
        if (type instanceof PrimitiveType)
        {
          String theType = ((PrimitiveType)type).getName();

          if (boolGen.thisHappens(ConfigConstants.GET_TUPLE_NULL_PROBABILITY()))
            theType = "null";
          else if (boolGen.thisHappens(ConfigConstants.GET_TUPLE_STRANGE_TYPES_PROBABILITY()))
            theType = Types.GET_RANDOM_TUPLE_TYPE_EXCLUDING(theType);

          result.add(pTypeGen.genTrustedPrimitiveType(theType));
        }
        else if (type instanceof Tuple)
          result.add(genTuple(((Tuple)type).getElements()));

    return result;
  }
}
