package es.um.nosql.s13e.db.gen.constants;

import java.io.File;

import es.um.nosql.s13e.db.gen.config.pojo.DbGenOptions;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public final class Constants
{
  private static final DbGenOptions options;

  private static final String STRING_NAMES_FILE = "config/names.txt";
  private static final String STRING_SURNAMES_FILE = "config/surnames.txt";
  private static final String STRING_WORDS_FILE = "config/words.txt";

  private static final int CONFIGURE_MIN_INSTANCES_VERSION = 10;
  private static final int CONFIGURE_MAX_INSTANCES_VERSION = 20;
  private static final String CONFIGURE_STRING_TYPE = "random";
  private static final int CONFIGURE_MIN_INT_ALLOWED = Integer.MIN_VALUE;
  private static final int CONFIGURE_MAX_INT_ALLOWED = Integer.MAX_VALUE;
  private static final double CONFIGURE_MIN_DOUBLE_ALLOWED = Double.MIN_VALUE;
  private static final double CONFIGURE_MAX_DOUBLE_ALLOWED = Double.MAX_VALUE;
  private static final int CONFIGURE_MIN_TUPLE_ELEMENTS = 1;
  private static final int CONFIGURE_MAX_TUPLE_ELEMENTS = 10;
  private static final boolean OUTPUT_FILE = false;
  private static final boolean OUTPUT_CONSOLE = true;
  private static final boolean OUTPUT_DATABASE = false;

  static
  {
    options = IOUtils.PARSE_CONFIG_FILE(new File("config/config.yaml"));
  }

  public static String GET_STRING_NAMES_FILE()
  {
    if (options.getConfigure() != null && options.getConfigure().getStringNamesFile() != null)
      return options.getConfigure().getStringNamesFile();
    else
      return STRING_NAMES_FILE;
  }

  public static String GET_STRING_SURNAMES_FILE()
  {
    if (options.getConfigure() != null && options.getConfigure().getStringSurnamesFile() != null)
      return options.getConfigure().getStringSurnamesFile();
    else
      return STRING_SURNAMES_FILE;
  }

  public static String GET_STRING_WORDS_FILE()
  {
    if (options.getConfigure() != null && options.getConfigure().getStringWordsFile() != null)
      return options.getConfigure().getStringWordsFile();
    else
      return STRING_WORDS_FILE;
  }

  public static int GET_CONFIGURE_MIN_INSTANCES_VERSION()
  {
    if (options.getConfigure() != null && options.getConfigure().getMinInstancesVersion() != null)
      return options.getConfigure().getMinInstancesVersion();
    else
      return CONFIGURE_MIN_INSTANCES_VERSION;
  }

  public static int GET_CONFIGURE_MAX_INSTANCES_VERSION()
  {
    if (options.getConfigure() != null && options.getConfigure().getMaxInstancesVersion() != null)
      return options.getConfigure().getMaxInstancesVersion();
    else
      return CONFIGURE_MAX_INSTANCES_VERSION;
  }

  public static String GET_CONFIGURE_STRING_TYPE()
  {
    if (options.getConfigure() != null && options.getConfigure().getStringType() != null)
      return options.getConfigure().getStringType();
    else
      return CONFIGURE_STRING_TYPE;
  }

  public static int GET_CONFIGURE_MIN_INT_ALLOWED()
  {
    if (options.getConfigure() != null && options.getConfigure().getMinIntAllowed() != null)
      return options.getConfigure().getMinIntAllowed();
    else
      return CONFIGURE_MIN_INT_ALLOWED;
  }

  public static int GET_CONFIGURE_MAX_INT_ALLOWED()
  {
    if (options.getConfigure() != null && options.getConfigure().getMaxIntAllowed() != null)
      return options.getConfigure().getMaxIntAllowed();
    else
      return CONFIGURE_MAX_INT_ALLOWED;
  }

  public static double GET_CONFIGURE_MIN_DOUBLE_ALLOWED()
  {
    if (options.getConfigure() != null && options.getConfigure().getMinDoubleAllowed() != null)
      return options.getConfigure().getMinDoubleAllowed();
    else
      return CONFIGURE_MIN_DOUBLE_ALLOWED;
  }

  public static double GET_CONFIGURE_MAX_DOUBLE_ALLOWED()
  {
    if (options.getConfigure() != null && options.getConfigure().getMaxDoubleAllowed() != null)
      return options.getConfigure().getMaxDoubleAllowed();
    else
      return CONFIGURE_MAX_DOUBLE_ALLOWED;
  }

  public static int GET_CONFIGURE_MIN_TUPLE_ELEMENTS()
  {
    if (options.getConfigure() != null && options.getConfigure().getMinTupleElements() != null)
      return options.getConfigure().getMinTupleElements();
    else
      return CONFIGURE_MIN_TUPLE_ELEMENTS;
  }

  public static int GET_CONFIGURE_MAX_TUPLE_ELEMENTS()
  {
    if (options.getConfigure() != null && options.getConfigure().getMaxTupleElements() != null)
      return options.getConfigure().getMaxTupleElements();
    else
      return CONFIGURE_MAX_TUPLE_ELEMENTS;
  }

  public static boolean IS_DEFINED_OUTPUT_FILE()
  {
    if (options.getOutput() != null)
      return options.getOutput().getFile() != null && !options.getOutput().getFile().isEmpty();
    else
      return OUTPUT_FILE;
  }

  public static boolean IS_DEFINED_OUTPUT_CONSOLE()
  {
    if (options.getOutput() != null)
      return options.getOutput().getConsole() != null && options.getOutput().getConsole();
    else
      return OUTPUT_CONSOLE;
  }

  public static boolean IS_DEFINED_OUTPUT_DATABASE()
  {
    if (options.getOutput() != null)
      return options.getOutput().getDatabase() != null && !options.getOutput().getDatabase().isEmpty();
    else
      return OUTPUT_DATABASE;
  }

  public static File GET_OUTPUT_FILE()
  {
    if (IS_DEFINED_OUTPUT_FILE())
      return new File(options.getOutput().getFile());
    else
      return null;
  }

  public static String GET_OUTPUT_DATABASE()
  {
    if (IS_DEFINED_OUTPUT_DATABASE())
      return options.getOutput().getDatabase();
    else
      return null;
  }
}