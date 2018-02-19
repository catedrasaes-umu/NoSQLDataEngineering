package es.um.nosql.s13e.db.gen.generator;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.um.nosql.s13e.db.gen.utils.Constants;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.BooleanGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.ObjectGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.StringGen;

public class PrimitiveTypeGen
{
  private StringGen strGen;
  private NumberGen numGen;
  private BooleanGen boolGen;
  private ObjectGen objGen;

  public PrimitiveTypeGen()
  {
    strGen  = StringGen.GET_INSTANCE();
    numGen  = NumberGen.GET_INSTANCE();
    boolGen = BooleanGen.GET_INSTANCE();
    objGen  = ObjectGen.GET_INSTANCE();
  }

  public void generatePrimitiveType(ObjectNode strObj, String name, String type)
  {
    switch (type.toLowerCase())
    {
      case "string": {strObj.put(name, this.genRandomString()); break;}
      case "int": case "number": {strObj.put(name, this.genRandomInt()); break;}
      case "double": case "float": {strObj.put(name, this.genRandomDouble()); break;}
      case "bool": case "boolean": {strObj.put(name, this.genRandomBoolean()); break;}
    }
  }

  public void generatePrimitiveType(ArrayNode arrayObj, String type)
  {
    switch (type.toLowerCase())
    {
      case "string": {arrayObj.add(this.genRandomString()); break;}
      case "int": case "number": {arrayObj.add(this.genRandomInt()); break;}
      case "double": case "float": {arrayObj.add(this.genRandomDouble()); break;}
      case "bool": case "boolean": {arrayObj.add(this.genRandomBoolean()); break;}
    }
  }

  public String genRandomString()
  {
    switch (Constants.GET_PRIMITIVE_TYPES_STRING_TYPE())
    {
      case "random":        return strGen.getRandomString();
      case "large":         return strGen.getRandomLargeString();
      case "word":          return strGen.getRandomWord();
      case "phrase":        return strGen.getRandomPhrase();
      case "word_number":   return strGen.getRandomWordNumber();
      case "nonsense":      return strGen.getRandomNonsense();
      case "name":          return strGen.getRandomName();
      case "name_surname":  return strGen.getRandomFullname();
    };

    return null;
  }

  public boolean genRandomBoolean()
  {
    return boolGen.getRandomBoolean();
  }

  public int genRandomInt()
  {
    return numGen.getRandomInteger();
  }

  public double genRandomDouble()
  {
    return numGen.getRandomDouble();
  }

  public ObjectId genRandomObjectId()
  {
    return objGen.getRandomObjectId();
  }

  public Object genNull()
  {
    return objGen.getNull();
  }
}