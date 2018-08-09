package es.um.nosql.s13e.evolution.util.constants;

import es.um.nosql.s13e.evolution.util.TSAnalyzerType;

public class ConfigConstants
{
  private static final String TIMESTAMP_DIR = "timestamp/";

  private static final String TIMESTAMP_EXTENSION = "TimestampAnalyzer.js";

  public static final String GET_TIMESTAMP_FILE(TSAnalyzerType tsType)
  {
    String result = TIMESTAMP_DIR;

    switch (tsType)
    {
      case BASIC: { result += "Basic"; break;}
      case NONE: { result += "Default"; break;}
      default: {throw new IllegalArgumentException("Not implemented yet");}
    }

    result += TIMESTAMP_EXTENSION;

    return result;
  }
}
