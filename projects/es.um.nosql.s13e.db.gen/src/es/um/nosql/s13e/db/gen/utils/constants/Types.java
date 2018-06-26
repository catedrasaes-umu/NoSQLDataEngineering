package es.um.nosql.s13e.db.gen.utils.constants;

import java.util.Arrays;
import java.util.List;

import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;

public class Types
{
  private static final List<String> PRIMITIVE_TYPES = Arrays.asList("string", "int", "double", "bool", "objectid");
  private static final List<String> TUPLE_TYPES     = Arrays.asList("string", "int", "double", "bool", "objectid");
  private static final List<String> REFERENCE_TYPES = Arrays.asList("string", "number", "objectid");
  private static final NumberGen NUM_GEN            = NumberGen.GET_INSTANCE();

  public static String GET_RANDOM_PRIMITIVE_TYPE_EXCLUDING(String type)
  {
    return GET_RANDOM_ELEMENT_EXCLUDING(PRIMITIVE_TYPES, type);
  }

  public static String GET_RANDOM_TUPLE_TYPE_EXCLUDING(String type)
  {
    return GET_RANDOM_ELEMENT_EXCLUDING(TUPLE_TYPES, type);
  }

  public static String GET_RANDOM_REFERENCE_TYPE_EXCLUDING(String type)
  {
    return GET_RANDOM_ELEMENT_EXCLUDING(REFERENCE_TYPES, type);
  }

  private static String GET_RANDOM_ELEMENT_EXCLUDING(List<String> list, String type)
  {
    int index = NUM_GEN.getExclusiveRandom(0, list.size());

    while (list.get(index).equals(type))
      index = NUM_GEN.getExclusiveRandom(0, list.size());

    return list.get(index);
  }
}