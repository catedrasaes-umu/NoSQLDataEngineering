package es.um.nosql.s13e.db.gen.utils;

import es.um.nosql.s13e.db.gen.utils.constants.ConfigConstants;

public class DebugLog
{
  public static void PRINTOUT(String message)
  {
    if (ConfigConstants.DEBUG())
      System.out.println(message);
  }

  public static void PRINTERR(String message)
  {
    if (ConfigConstants.DEBUG())
      System.err.println(message);
  }
}