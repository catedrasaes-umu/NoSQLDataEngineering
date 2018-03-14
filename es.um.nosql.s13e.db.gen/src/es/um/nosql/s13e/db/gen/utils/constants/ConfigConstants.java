package es.um.nosql.s13e.db.gen.utils.constants;

import java.io.File;

import es.um.nosql.s13e.db.gen.config.DbGenOptions;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public final class ConfigConstants
{
  static
  {
    options = IOUtils.PARSE_CONFIG_FILE(new File("config/config.yaml"));
  }

  private static final DbGenOptions options;

  private static final boolean DEFAULT_DEBUG                                        = true;
  private static final int DEFAULT_SPLITS                                           = 1;
  private static final double ATTRIBUTES_PRIMITIVE_TYPES_STRANGE_TYPES_PROBABILITY  = 0;
  private static final double ATTRIBUTES_PRIMITIVE_TYPES_NULL_PROBABILITY           = 0;
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_STRING_TYPE                = "random";
  private static final int ATTRIBUTES_PRIMITIVE_TYPES_MIN_INT_ALLOWED               = Integer.MIN_VALUE + 1;
  private static final int ATTRIBUTES_PRIMITIVE_TYPES_MAX_INT_ALLOWED               = Integer.MAX_VALUE - 1;
  private static final double ATTRIBUTES_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED         = Double.MIN_VALUE + 1;
  private static final double ATTRIBUTES_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED         = Double.MAX_VALUE - 1;
  private static final int ATTRIBUTES_PRIMITIVE_TYPES_DOUBLE_DECIMALS_ALLOWED       = 2;
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_NAMES_FILE                 = "config/names.txt";
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_SURNAMES_FILE              = "config/surnames.txt";
  private static final String ATTRIBUTES_PRIMITIVE_TYPES_WORDS_FILE                 = "config/words.txt";
  private static final int ATTRIBUTES_TUPLE_MIN_TUPLE_ELEMENTS                      = 1;
  private static final int ATTRIBUTES_TUPLE_MAX_TUPLE_ELEMENTS                      = 10;
  private static final double ATTRIBUTES_TUPLE_STRANGE_TYPES_PROBABILITY            = 0;
  private static final double ATTRIBUTES_TUPLE_NULL_PROBABILITY                     = 0;
  private static final int EVERSIONS_MIN_INSTANCES                                  = 10;
  private static final int EVERSIONS_MAX_INSTANCES                                  = 20;
  private static final int AGGREGATES_MIN_ALLOWED                                   = 2;
  private static final int AGGREGATES_MAX_ALLOWED                                   = 3;
  private static final int REFERENCES_MIN_ALLOWED                                   = 2;
  private static final int REFERENCES_MAX_ALLOWED                                   = 3;
  private static final int REFERENCES_STRANGE_TYPES_PROBABILITY                     = 0;
  private static final String ENTITIES_DATEFORMAT                                   = "dd/MM/yyyy";
  private static final String ENTITIES_TIMESTAMP                                    = "17/06/1988";
  private static final boolean ENTITIES_INCLUDE_TYPE                                = false;
  private static final boolean OUTPUT_FOLDER                                        = false;
  private static final boolean OUTPUT_CONSOLE                                       = false;
  private static final boolean OUTPUT_DATABASE                                      = false;
  private static final String DEFAULT_FOLDER                                        = "output/";
  private static final String DEFAULT_DATABASE                                      = "localhost";
  private static final String DEFAULT_DATABASE_COLLECTION                           = "default_collection";
  private static final String SPACES                                                = "  ";

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                           ENTITY OPTIONS                           ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static int GET_MIN_INSTANCES()
  {
    if (options.getEntities() != null && options.getEntities().getVersions() != null && options.getEntities().getVersions().getMinInstances() != null)
      return options.getEntities().getVersions().getMinInstances();
    else
      return EVERSIONS_MIN_INSTANCES;
  }

  public static int GET_MAX_INSTANCES()
  {
    if (options.getEntities() != null && options.getEntities().getVersions() != null && options.getEntities().getVersions().getMaxInstances() != null)
      return options.getEntities().getVersions().getMaxInstances();
    else
      return EVERSIONS_MAX_INSTANCES;
  }

  public static boolean GET_INCLUDE_TYPE()
  {
    if (options.getEntities() != null && options.getEntities().getIncludeType() != null)
      return options.getEntities().getIncludeType();
    else
      return ENTITIES_INCLUDE_TYPE;
  }

  public static String GET_DATEFORMAT()
  {
    if (options.getEntities() != null && options.getEntities().getDateFormat() != null)
      return options.getEntities().getDateFormat();
    else
      return ENTITIES_DATEFORMAT;
  }

  public static String GET_INITIAL_TIMESTAMP()
  {
    if (options.getEntities() != null && options.getEntities().getTimestamp() != null)
      return options.getEntities().getTimestamp();
    else
      return ENTITIES_TIMESTAMP;
  }

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
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getMinIntegerAllowed() != null)
      return options.getAttributes().getPrimitiveTypes().getMinIntegerAllowed();
    else
      return ATTRIBUTES_PRIMITIVE_TYPES_MIN_INT_ALLOWED;
  }

  public static int GET_PRIMITIVE_TYPES_MAX_INT_ALLOWED()
  {
    if (options.getAttributes() != null && options.getAttributes().getPrimitiveTypes() != null && options.getAttributes().getPrimitiveTypes().getMaxIntegerAllowed() != null)
      return options.getAttributes().getPrimitiveTypes().getMaxIntegerAllowed();
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

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                         REFERENCE OPTIONS                          ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static int GET_REFERENCE_MIN_ALLOWED()
  {
    if (options.getAssociations() != null && options.getAssociations().getReferences() != null && options.getAssociations().getReferences().getMinReferenceAllowed() != null)
      return options.getAssociations().getReferences().getMinReferenceAllowed();
    else
      return REFERENCES_MIN_ALLOWED;
  }

  public static int GET_REFERENCE_MAX_ALLOWED()
  {
    if (options.getAssociations() != null && options.getAssociations().getReferences() != null && options.getAssociations().getReferences().getMaxReferenceAllowed() != null)
      return options.getAssociations().getReferences().getMaxReferenceAllowed();
    else
      return REFERENCES_MAX_ALLOWED;
  }

  public static double GET_REFERENCE_STRANGE_TYPES_PROBABILITY()
  {
    if (options.getAssociations() != null && options.getAssociations().getReferences() != null && options.getAssociations().getReferences().getStrangeTypesProbability() != null)
      return options.getAssociations().getReferences().getStrangeTypesProbability();
    else
      return REFERENCES_STRANGE_TYPES_PROBABILITY;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                         AGGREGATE OPTIONS                          ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static int GET_AGGREGATE_MIN_ALLOWED()
  {
    if (options.getAssociations() != null && options.getAssociations().getAggregates() != null && options.getAssociations().getAggregates().getMinAggregateAllowed() != null)
      return options.getAssociations().getAggregates().getMinAggregateAllowed();
    else
      return AGGREGATES_MIN_ALLOWED;
  }

  public static int GET_AGGREGATE_MAX_ALLOWED()
  {
    if (options.getAssociations() != null && options.getAssociations().getAggregates() != null && options.getAssociations().getAggregates().getMaxAggregateAllowed() != null)
      return options.getAssociations().getAggregates().getMaxAggregateAllowed();
    else
      return AGGREGATES_MAX_ALLOWED;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                           OUTPUT OPTIONS                           ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static boolean IS_DEFINED_OUTPUT_FOLDER()
  {
    if (options.getOutput() != null)
      return options.getOutput().getFolder() != null;
    else
      return OUTPUT_FOLDER;
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

  public static String GET_OUTPUT_FOLDER()
  {
    if (IS_DEFINED_OUTPUT_FOLDER())
      return options.getOutput().getFolder();
    else
      return DEFAULT_FOLDER;
  }

  public static String GET_OUTPUT_DATABASE()
  {
    if (IS_DEFINED_OUTPUT_DATABASE())
      return options.getOutput().getDatabase();
    else
      return DEFAULT_DATABASE;
  }

  public static String GET_OUTPUT_DATABASE_COLLECTION()
  {
    if (options.getOutput() != null && options.getOutput().getDatabaseCollection() != null)
      return options.getOutput().getDatabaseCollection();
    else
      return DEFAULT_DATABASE_COLLECTION;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////                               UTILS                                ///////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public static boolean DEBUG()
  {
    if (options != null && options.getInput() != null && options.getInput().getDebug() != null)
      return options.getInput().getDebug();
    else
      return DEFAULT_DEBUG;
  }

  public static String GET_INPUT_FILE()
  {
    return options.getInput().getModel();
  }

  public static int GET_SPLITS()
  {
    if (options.getInput().getSplits() != null)
      return options.getInput().getSplits();
    else
      return DEFAULT_SPLITS;
  }

  public static String GET_TABS(Class<?> className)
  {
    StringBuilder result = new StringBuilder();

    switch (className.getSimpleName())
    {
      case "DbGenOptions": break;
      case "EntityOptions": case "AttributeOptions": case "AssociationOptions": case "InputOptions": case "OutputOptions": {result.append(SPACES); break;}
      case "VersionOptions": case "PrimitiveTypeOptions": case "TupleOptions": case "AggregateOptions": case "ReferenceOptions": {result.append(SPACES + SPACES); break;}
      default: System.err.println("Didn't find spacing for: " + className.getSimpleName());
    }

    return result.toString();
  }

  public static String GET_OPTIONS()
  {
    StringBuilder result = new StringBuilder();

    result.append("\n===== ENTITY OPTIONS =====\n\n");
    result.append("GET_MIN_INSTANCES: " + ConfigConstants.GET_MIN_INSTANCES() + "\n");
    result.append("GET_MAX_INSTANCES: " + ConfigConstants.GET_MAX_INSTANCES() + "\n");
    result.append("GET_ENTITY_INCLUDE_TYPE: " + ConfigConstants.GET_INCLUDE_TYPE() + "\n");
    result.append("GET_DATEFORMAT: " + ConfigConstants.GET_DATEFORMAT() + "\n");
    result.append("GET_INITIAL_TIMESTAMP: " + ConfigConstants.GET_INITIAL_TIMESTAMP() + "\n");

    result.append("\n===== PRIMITIVE TYPES OPTIONS =====\n\n");
    result.append("GET_PRIMITIVE_TYPES_NAMES_FILE: " + ConfigConstants.GET_PRIMITIVE_TYPES_NAMES_FILE() + "\n");
    result.append("GET_PRIMITIVE_TYPES_SURNAMES_FILE: " + ConfigConstants.GET_PRIMITIVE_TYPES_SURNAMES_FILE() + "\n");
    result.append("GET_PRIMITIVE_TYPES_WORD_FILE: " + ConfigConstants.GET_PRIMITIVE_TYPES_WORDS_FILE() + "\n");
    result.append("GET_PRIMITIVE_TYPES_STRANGE_TYPES_PROBABILITY: " + ConfigConstants.GET_PRIMITIVE_TYPES_STRANGE_TYPES_PROBABILITY() + "\n");
    result.append("GET_PRIMITIVE_TYPES_NULL_PROBABILITY: " + ConfigConstants.GET_PRIMITIVE_TYPES_NULL_PROBABILITY() + "\n");
    result.append("GET_PRIMITIVE_TYPES_STRING_TYPE: " + ConfigConstants.GET_PRIMITIVE_TYPES_STRING_TYPE() + "\n");
    result.append("GET_PRIMITIVE_TYPES_MIN_INT_ALLOWED: " + ConfigConstants.GET_PRIMITIVE_TYPES_MIN_INT_ALLOWED() + "\n");
    result.append("GET_PRIMITIVE_TYPES_MAX_INT_ALLOWED: " + ConfigConstants.GET_PRIMITIVE_TYPES_MAX_INT_ALLOWED() + "\n");
    result.append("GET_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED: " + ConfigConstants.GET_PRIMITIVE_TYPES_MIN_DOUBLE_ALLOWED() + "\n");
    result.append("GET_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED: " + ConfigConstants.GET_PRIMITIVE_TYPES_MAX_DOUBLE_ALLOWED() + "\n");
    result.append("GET_PRIMITIVE_TYPES_DOUBLE_DECIMALS_ALLOWED: " + ConfigConstants.GET_PRIMITIVE_TYPES_DOUBLE_DECIMALS_ALLOWED() + "\n");

    result.append("\n===== TUPLE OPTIONS =====\n\n");
    result.append("GET_TUPLE_MIN_TUPLE_ELEMENTS: " + ConfigConstants.GET_TUPLE_MIN_TUPLE_ELEMENTS() + "\n");
    result.append("GET_TUPLE_MAX_TUPLE_ELEMENTS: " + ConfigConstants.GET_TUPLE_MAX_TUPLE_ELEMENTS() + "\n");
    result.append("GET_TUPLE_STRANGE_TYPES_PROBABILITY: " + ConfigConstants.GET_TUPLE_STRANGE_TYPES_PROBABILITY() + "\n");
    result.append("GET_TUPLE_NULL_PROBABILITY: " + ConfigConstants.GET_TUPLE_NULL_PROBABILITY() + "\n");

    result.append("\n===== REFERENCE OPTIONS =====\n\n");
    result.append("GET_REFERENCE_MIN_ALLOWED: " + ConfigConstants.GET_REFERENCE_MIN_ALLOWED() + "\n");
    result.append("GET_REFERENCE_MAX_ALLOWED: " + ConfigConstants.GET_REFERENCE_MAX_ALLOWED() + "\n");

    result.append("\n===== AGGREGATE OPTIONS =====\n\n");
    result.append("GET_AGGREGATE_MIN_ALLOWED: " + ConfigConstants.GET_AGGREGATE_MIN_ALLOWED() + "\n");
    result.append("GET_AGGREGATE_MAX_ALLOWED: " + ConfigConstants.GET_AGGREGATE_MAX_ALLOWED() + "\n");

    result.append("\n===== OUTPUT OPTIONS =====\n\n");
    result.append("IS_DEFINED_OUTPUT_FOLDER: " + ConfigConstants.IS_DEFINED_OUTPUT_FOLDER() + "\n");
    result.append("IS_DEFINED_OUTPUT_CONSOLE: " + ConfigConstants.IS_DEFINED_OUTPUT_CONSOLE() + "\n");
    result.append("IS_DEFINED_OUTPUT_DATABASE: " + ConfigConstants.IS_DEFINED_OUTPUT_DATABASE() + "\n");
    result.append("GET_OUTPUT_FOLDER: " + ConfigConstants.GET_OUTPUT_FOLDER() + "\n");
    result.append("GET_OUTPUT_DATABASE: " + ConfigConstants.GET_OUTPUT_DATABASE() + "\n");

    return result.toString();
  }
}