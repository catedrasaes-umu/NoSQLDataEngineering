package es.um.nosql.s13e.db.gen.utils;

import java.io.File;

import es.um.nosql.s13e.db.gen.config.pojo.DbGenOptions;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public final class Constants
{
  static
  {
    options = IOUtils.PARSE_CONFIG_FILE(new File("config/config.yaml"));
  }

  private static final DbGenOptions options;

  private static final double ATTRIBUTES_PRIMITIVE_TYPES_STRANGE_TYPES_PROBABILITY  = 0;
  private static final double ATTRIBUTES_PRIMITIVE_TYPES_NULL_PROBABILITY           = 0;
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_STRING_TYPE                = "random";
  private static final int ATTRIBUTES_PRIMITIVE_TYPES_MIN_INT_ALLOWED               = Integer.MIN_VALUE;
  private static final int ATTRIBUTES_PRIMITIVE_TYPES_MAX_INT_ALLOWED               = Integer.MAX_VALUE;
  private static final double ATTRIBUTES_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED         = Double.MIN_VALUE;
  private static final double ATTRIBUTES_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED         = Double.MAX_VALUE;
  private static final int ATTRIBUTES_PRIMITIVE_TYPES_DOUBLE_DECIMALS_ALLOWED       = 2;
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_NAMES_FILE                 = "config/names.txt";
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_SURNAMES_FILE              = "config/surnames.txt";
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_WORDS_FILE                 = "config/words.txt";
  private static final int ATTRIBUTES_TUPLE_MIN_TUPLE_ELEMENTS                      = 1;
  private static final int ATTRIBUTES_TUPLE_MAX_TUPLE_ELEMENTS                      = 10;
  private static final double ATTRIBUTES_TUPLE_STRANGE_TYPES_PROBABILITY            = 0;
  private static final double ATTRIBUTES_TUPLE_NULL_PROBABILITY                     = 0;
  private static final double ATTRIBUTES_TUPLE_TUPLES_IN_TUPLES_PROBABILITY         = 0;
  private static final int CONFIGURE_MIN_INSTANCES_VERSION                          = 10;
  private static final int CONFIGURE_MAX_INSTANCES_VERSION                          = 20;
  private static final boolean CONFIGURE_INCLUDE_TYPE                               = false;
  private static final boolean OUTPUT_FILE                                          = false;
  private static final boolean OUTPUT_CONSOLE                                       = true;
  private static final boolean OUTPUT_DATABASE                                      = false;

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                      PRIMITIVE TYPE OPTIONS                        ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static String GET_PRIMITIVE_TYPES_NAMES_FILE()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getStringNamesFile() != null)
      return options.getAttributes().getPrimitiveTypes().getStringNamesFile();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_NAMES_FILE;
  }

  public static String GET_PRIMITIVE_TYPES_SURNAMES_FILE()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getStringSurnamesFile() != null)
      return options.getAttributes().getPrimitiveTypes().getStringSurnamesFile();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_SURNAMES_FILE;
  }

  public static String GET_PRIMITIVE_TYPES_WORDS_FILE()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getStringWordsFile() != null)
      return options.getAttributes().getPrimitiveTypes().getStringWordsFile();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_WORDS_FILE;
  }

  public static double GET_PRIMITIVE_TYPES_STRANGE_TYPES_PROBABILITY()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getStrangeTypesProbability() != null)
      return options.getAttributes().getPrimitiveTypes().getStrangeTypesProbability();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_STRANGE_TYPES_PROBABILITY;
  }

  public static double GET_PRIMITIVE_TYPES_NULL_PROBABILITY()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getNullProbability() != null)
      return options.getAttributes().getPrimitiveTypes().getNullProbability();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_NULL_PROBABILITY;
  }

  public static String GET_PRIMITIVE_TYPES_STRING_TYPE()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getStringType() != null)
      return options.getAttributes().getPrimitiveTypes().getStringType();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_STRING_TYPE;
  }

  public static int GET_PRIMITIVE_TYPES_MIN_INT_ALLOWED()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getMinIntAllowed() != null)
      return options.getAttributes().getPrimitiveTypes().getMinIntAllowed();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_MIN_INT_ALLOWED;
  }

  public static int GET_PRIMITIVE_TYPES_MAX_INT_ALLOWED()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getMaxIntAllowed() != null)
      return options.getAttributes().getPrimitiveTypes().getMaxIntAllowed();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_MAX_INT_ALLOWED;
  }

  public static double GET_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getMinDoubleAllowed() != null)
      return options.getAttributes().getPrimitiveTypes().getMinDoubleAllowed();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED;
  }

  public static double GET_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getMaxDoubleAllowed() != null)
      return options.getAttributes().getPrimitiveTypes().getMaxDoubleAllowed();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED;
  }

  public static int GET_PRIMITIVE_TYPES_DOUBLE_DECIMALS_ALLOWED()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getDoubleDecimalsAllowed() != null)
      return options.getAttributes().getPrimitiveTypes().getDoubleDecimalsAllowed();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_DOUBLE_DECIMALS_ALLOWED;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                           TUPLE OPTIONS                            ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static int GET_TUPLE_MIN_TUPLE_ELEMENTS()
  {
    if (options.getAttributes() != null && options.getAttributes().getTuples() != null && options.getAttributes().getTuples().getMinTupleElements() != null)
      return options.getAttributes().getTuples().getMinTupleElements();
    else
      return ATTRIBUTES_TUPLE_MIN_TUPLE_ELEMENTS;
  }

  public static int GET_TUPLE_MAX_TUPLE_ELEMENTS()
  {
    if (options.getAttributes() != null && options.getAttributes().getTuples() != null && options.getAttributes().getTuples().getMaxTupleElements() != null)
      return options.getAttributes().getTuples().getMaxTupleElements();
    else
      return ATTRIBUTES_TUPLE_MAX_TUPLE_ELEMENTS;
  }

  public static double GET_TUPLE_STRANGE_TYPES_PROBABILITY()
  {
    if (options.getAttributes() != null && options.getAttributes().getTuples() != null && options.getAttributes().getTuples().getStrangeTypesProbability() != null)
      return options.getAttributes().getTuples().getStrangeTypesProbability();
    else
      return ATTRIBUTES_TUPLE_STRANGE_TYPES_PROBABILITY;
  }

  public static double GET_TUPLE_NULL_PROBABILITY()
  {
    if (options.getAttributes() != null && options.getAttributes().getTuples() != null && options.getAttributes().getTuples().getNullProbability() != null)
      return options.getAttributes().getTuples().getNullProbability();
    else
      return ATTRIBUTES_TUPLE_NULL_PROBABILITY;
  }

  public static double GET_TUPLE_TUPLES_IN_TUPLES_PROBABILITY()
  {
    if (options.getAttributes() != null && options.getAttributes().getTuples() != null && options.getAttributes().getTuples().getTuplesInTuplesProbability() != null)
      return options.getAttributes().getTuples().getTuplesInTuplesProbability();
    else
      return ATTRIBUTES_TUPLE_TUPLES_IN_TUPLES_PROBABILITY;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                         CONFIGURE OPTIONS                          ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static int GET_MIN_INSTANCES_VERSION()
  {
    if (options.getConfigure() != null && options.getConfigure().getMinInstancesVersion() != null)
      return options.getConfigure().getMinInstancesVersion();
    else
      return CONFIGURE_MIN_INSTANCES_VERSION;
  }

  public static int GET_MAX_INSTANCES_VERSION()
  {
    if (options.getConfigure() != null && options.getConfigure().getMaxInstancesVersion() != null)
      return options.getConfigure().getMaxInstancesVersion();
    else
      return CONFIGURE_MAX_INSTANCES_VERSION;
  }

  public static boolean GET_INCLUDE_TYPE()
  {
    if (options.getConfigure() != null && options.getConfigure().getIncludeType() != null)
      return options.getConfigure().getIncludeType();
    else
      return CONFIGURE_INCLUDE_TYPE;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                           OUTPUT OPTIONS                           ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

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