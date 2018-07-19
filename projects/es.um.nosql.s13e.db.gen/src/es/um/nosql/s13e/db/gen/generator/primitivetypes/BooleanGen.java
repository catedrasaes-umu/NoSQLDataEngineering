package es.um.nosql.s13e.db.gen.generator.primitivetypes;

import java.util.concurrent.ThreadLocalRandom;

public class BooleanGen
{
  private static BooleanGen THE_INSTANCE;

  private BooleanGen() {}

  public static BooleanGen GET_INSTANCE()
  {
    if (THE_INSTANCE == null)
      THE_INSTANCE = new BooleanGen();

    return THE_INSTANCE;
  }

  public boolean getRandomBoolean()
  {
    return ThreadLocalRandom.current().nextBoolean();
  }

  public boolean thisHappens(double probability)
  {
    return (ThreadLocalRandom.current().nextDouble() - probability) < 0;
  }
}
