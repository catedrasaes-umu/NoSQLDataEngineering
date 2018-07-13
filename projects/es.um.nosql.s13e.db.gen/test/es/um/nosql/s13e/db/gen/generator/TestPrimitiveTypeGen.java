package es.um.nosql.s13e.db.gen.generator;

import java.text.ParseException;

import org.bson.types.ObjectId;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import es.um.nosql.s13e.db.gen.generator.primitivetypes.BooleanGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.NumberGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.ObjectIdGen;
import es.um.nosql.s13e.db.gen.generator.primitivetypes.StringGen;
import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

class TestPrimitiveTypeGen
{
  private BooleanGen boolGen    = BooleanGen.GET_INSTANCE();
  private StringGen strGen      = StringGen.GET_INSTANCE();
  private NumberGen numGen      = NumberGen.GET_INSTANCE();
  private ObjectIdGen oidGen    = ObjectIdGen.GET_INSTANCE();
  private int ITERATIONS_BOOL   = 1000000;
  private int ITERATIONS_STRING = 1000000;
  private int ITERATIONS_INTS   = 1000000;
  private int ITERATIONS_DOUBLE = 1000000;
  private int ITERATIONS_OID    = 10000000;

  @Ignore
  void testBooleans()
  {
    int boolTrue = 0;
    int boolFalse = 0;

    for (int i = 0; i < ITERATIONS_BOOL; i++)
      if (boolGen.getRandomBoolean())
        boolTrue++;
      else
        boolFalse++;

    System.out.println("Bools generated to TRUE: " + boolTrue);
    System.out.println("Bools generated to FALSE: " + boolFalse);
  }

  @Ignore
  void testIntegers()
  {
    for (int i = 0; i < ITERATIONS_INTS; i++)
    {
      int theInteger = numGen.getRandomInteger();
      Assertions.assertTrue(theInteger <= ConfigConstants.GET_PRIMITIVE_TYPES_MAX_INT_ALLOWED());
      Assertions.assertTrue(theInteger >= ConfigConstants.GET_PRIMITIVE_TYPES_MIN_INT_ALLOWED());
    }
  }

  @Ignore
  void testDoubles()
  {
    for (int i = 0; i < ITERATIONS_DOUBLE; i++)
    {
      double theDouble = numGen.getRandomDouble();
      Assertions.assertTrue(theDouble <= ConfigConstants.GET_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED());
      Assertions.assertTrue(theDouble >= ConfigConstants.GET_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED());
    }
  }

  @Ignore
  void testStrings()
  {
    for (int i = 0; i < ITERATIONS_STRING; i++)
    {
      Assertions.assertFalse(strGen.getRandomName().contains(" "));
      Assertions.assertFalse(strGen.getRandomSurname().contains(" "));
      Assertions.assertTrue(strGen.getRandomFullname().contains(" "));
      Assertions.assertFalse(strGen.getRandomWord().contains(" "));
      Assertions.assertTrue(strGen.getRandomWordNumber().contains("_") && !strGen.getRandomWordNumber().contains(" "));
      Assertions.assertFalse(strGen.getRandomNonsense().contains(" "));
    }

    for (int i = 0; i < ITERATIONS_STRING / 10; i++)
    {
      Assertions.assertTrue(strGen.getRandomPhrase().contains(" "));
      Assertions.assertTrue(strGen.getRandomLargeString().length() >= 1450);
    }
  }

  @Test
  void testObjectIds() throws ParseException
  {
    for (int i = 0; i < ITERATIONS_OID; i++)
    {
      ObjectId oid = oidGen.getTimestampObjectId();
      System.out.println("Date: " + oid.getDate());
    }
  }
}
