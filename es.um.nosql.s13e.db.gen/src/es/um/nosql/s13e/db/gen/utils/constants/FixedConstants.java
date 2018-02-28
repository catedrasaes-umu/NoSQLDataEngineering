package es.um.nosql.s13e.db.gen.utils.constants;

public class FixedConstants
{
  private static final int RANDOM_FUNCTIONS_COUNT = 8;
  private static final int LARGE_STRING_LENGTH    = 1450;
  private static final int MIN_WORDS_IN_PHRASE    = 4;
  private static final int MAX_WORDS_IN_PHRASE    = 9;
  private static final int MIN_NONSENSE_CHARS     = 5;
  private static final int MAX_NONSENSE_CHARS     = 30;

  public static int GET_RANDOM_FUNCTIONS_COUNT()
  {
    return RANDOM_FUNCTIONS_COUNT;
  }

  public static int GET_LARGE_STRING_LENGTH()
  {
    return LARGE_STRING_LENGTH;
  }

  public static int GET_MIN_WORDS_IN_PHRASE()
  {
    return MIN_WORDS_IN_PHRASE;
  }

  public static int GET_MAX_WORDS_IN_PHRASE()
  {
    return MAX_WORDS_IN_PHRASE;
  }

  public static int GET_MIN_NONSENSE_CHARS()
  {
    return MIN_NONSENSE_CHARS;
  }

  public static int GET_MAX_NONSENSE_CHARS()
  {
    return MAX_NONSENSE_CHARS;
  }
}