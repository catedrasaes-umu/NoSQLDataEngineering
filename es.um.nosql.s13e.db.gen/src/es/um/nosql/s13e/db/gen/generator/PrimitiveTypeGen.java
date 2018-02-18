package es.um.nosql.s13e.db.gen.generator;

import java.util.concurrent.ThreadLocalRandom;

import org.bson.types.ObjectId;

import es.um.nosql.s13e.db.gen.constants.Constants;
import es.um.nosql.s13e.db.gen.utils.StringGenerator;

public class PrimitiveTypeGen
{
  private StringGenerator strGenerator;

  public PrimitiveTypeGen()
  {
    strGenerator = StringGenerator.GET_INSTANCE();
  }

  public String genRandomString()
  {
    switch (Constants.GET_CONFIGURE_STRING_TYPE())
    {
      case "random": return strGenerator.getRandomString();
      case "large": return strGenerator.getRandomLargeString();
      case "word": return strGenerator.getRandomWord();
      case "phrase": return strGenerator.getRandomPhrase();
      case "word_number": return strGenerator.getRandomWordNumber();
      case "nonsense": return strGenerator.getRandomNonsense();
      case "name": return strGenerator.getRandomName();
      case "name_surname": return strGenerator.getRandomFullname();
    };

    return null;
  }

  public boolean genRandomBoolean()
  {
    return ThreadLocalRandom.current().nextBoolean();
  }

  public int genRandomInt()
  {
    return ThreadLocalRandom.current().nextInt(Constants.GET_CONFIGURE_MIN_INT_ALLOWED(), Constants.GET_CONFIGURE_MAX_INT_ALLOWED());
  }

  public double genRandomDouble()
  {
    return ThreadLocalRandom.current().nextDouble(Constants.GET_CONFIGURE_MIN_DOUBLE_ALLOWED(), Constants.GET_CONFIGURE_MAX_DOUBLE_ALLOWED());
  }

  public ObjectId genRandomObjectId()
  {
    return new ObjectId();
  }

  public Object genNull()
  {
    return null;
  }
}