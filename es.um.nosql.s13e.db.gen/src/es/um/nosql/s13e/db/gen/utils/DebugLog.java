package es.um.nosql.s13e.db.gen.utils;

public class DebugLog
{
  public static void PRINTOUT(String message)
  {
    if (Constants.DEBUG())
      System.out.println(message);
  }

  public static void PRINTERR(String message)
  {
    if (Constants.DEBUG())
      System.err.println(message);
  }
}