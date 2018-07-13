package es.um.nosql.s13e.db.gen.generator.primitivetypes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class NumberGen
{
  private static NumberGen THE_INSTANCE;

  private NumberGen() {}

  public static NumberGen GET_INSTANCE()
  {
    if (THE_INSTANCE == null)
      THE_INSTANCE = new NumberGen();

    return THE_INSTANCE;
  }

  public int getRandomInteger()
  {
    return this.getInclusiveRandom(ConfigConstants.GET_PRIMITIVE_TYPES_MIN_INT_ALLOWED(), ConfigConstants.GET_PRIMITIVE_TYPES_MAX_INT_ALLOWED());
  }

  public int getInclusiveRandom(int min, int max)
  {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  public int getExclusiveRandom(int min, int max)
  {
    return ThreadLocalRandom.current().nextInt(min, max);
  }

  public double getRandomDouble()
  {
    BigDecimal bd = new BigDecimal(ThreadLocalRandom.current().nextDouble(ConfigConstants.GET_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED(), ConfigConstants.GET_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED()));
    int decimals = ConfigConstants.GET_PRIMITIVE_TYPES_DOUBLE_DECIMALS_ALLOWED();
    bd = bd.setScale(decimals, RoundingMode.HALF_UP);

    return bd.doubleValue();
  }
}