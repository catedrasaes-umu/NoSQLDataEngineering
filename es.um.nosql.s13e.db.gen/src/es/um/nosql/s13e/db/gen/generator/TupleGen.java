package es.um.nosql.s13e.db.gen.generator;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.NoSQLSchema.PrimitiveType;
import es.um.nosql.s13e.NoSQLSchema.Tuple;
import es.um.nosql.s13e.NoSQLSchema.Type;

public class TupleGen
{
  private PrimitiveTypeGen prTypeGen;

  public TupleGen()
  {
    prTypeGen = new PrimitiveTypeGen();
  }

  public void generateTuple(ObjectNode strObj, String name, List<Type> elements)
  {
    ArrayNode array = factory.arrayNode();
    strObj.put(name, array);

    for (Type type : elements)
    {
      if (type instanceof PrimitiveType)
        prTypeGen.generatePrimitiveType(array, ((PrimitiveType)type).getName());
      else if (type instanceof Tuple)
      {
        ArrayNode innerArray = factory.arrayNode();
        array.add(innerArray);
        generateTuple(innerArray, ((Tuple)type).getElements());
      }
    }
  }

  public void generateTuple(ArrayNode arrayObj, List<Type> elements)
  {
    for (Type type : elements)
    {
      if (type instanceof PrimitiveType)
        prTypeGen.generatePrimitiveType(arrayObj, ((PrimitiveType)type).getName());
      else if (type instanceof Tuple)
      {
        ArrayNode innerArray = factory.arrayNode();
        arrayObj.add(innerArray);
        generateTuple(innerArray, ((Tuple)type).getElements());
      }
    }
  }
}