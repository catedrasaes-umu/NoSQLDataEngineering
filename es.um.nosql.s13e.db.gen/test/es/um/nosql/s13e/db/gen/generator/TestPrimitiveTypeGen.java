package es.um.nosql.s13e.db.gen.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import es.um.nosql.s13e.db.gen.utils.StringGenerator;

class TestPrimitiveTypeGen
{
  private PrimitiveTypeGen pTypeGen = new PrimitiveTypeGen();
  private int ITERATIONS_BOOL = 100000000;
  private int ITERATIONS_STRING = 100000000;

  @Test
  void testBooleans()
  {
    int boolTrue = 0;
    int boolFalse = 0;

    for (int i = 0; i < ITERATIONS_BOOL; i++)
      if (pTypeGen.genRandomBoolean())
        boolTrue++;
      else
        boolFalse++;

    System.out.println("Bools generated to TRUE: " + boolTrue);
    System.out.println("Bools generated to FALSE: " + boolFalse);
  }

  @Test
  void testStrings()
  {
    StringGenerator strGenerator = StringGenerator.GET_INSTANCE();

    for (int i = 0; i < ITERATIONS_STRING; i++)
    {
      Assertions.assertFalse(strGenerator.getRandomName().contains(" "));
      Assertions.assertFalse(strGenerator.getRandomSurname().contains(" "));
      Assertions.assertTrue(strGenerator.getRandomFullname().contains(" "));
      Assertions.assertFalse(strGenerator.getRandomWord().contains(" "));
      Assertions.assertTrue(strGenerator.getRandomPhrase().contains(" "));
      Assertions.assertTrue(strGenerator.getRandomWordNumber().contains("_") && !strGenerator.getRandomWordNumber().contains(" "));
      Assertions.assertTrue(strGenerator.getRandomLargeString().length() > 3000);
    }
  }
}
