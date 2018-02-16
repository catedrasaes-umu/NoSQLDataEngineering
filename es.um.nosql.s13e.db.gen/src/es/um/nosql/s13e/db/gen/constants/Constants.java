package es.um.nosql.s13e.db.gen.constants;

import java.io.File;

import es.um.nosql.s13e.db.gen.config.pojo.DbGenOptions;
import es.um.nosql.s13e.db.gen.utils.IOUtils;

public final class Constants
{
  private static final DbGenOptions options;

  private static final int CONFIGURE_MIN_INSTANCES_VERSION = 10;
  private static final int CONFIGURE_MAX_INSTANCES_VERSION = 20;
  private static final boolean OUTPUT_FILE_DEFAULT = false;
  private static final boolean OUTPUT_CONSOLE_DEFAULT = true;
  private static final boolean OUTPUT_DATABASE_DEFAULT = false;

  static
  {
    options = IOUtils.PARSE_CONFIG_FILE(new File("config/config.yaml"));
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

  public static boolean IS_DEFINED_OUTPUT_FILE()
  {
    if (options.getOutput() != null)
      return options.getOutput().getFile() != null && !options.getOutput().getFile().isEmpty();
    else
      return OUTPUT_FILE_DEFAULT;
  }

  public static boolean IS_DEFINED_OUTPUT_CONSOLE()
  {
    if (options.getOutput() != null)
      return options.getOutput().getConsole() != null && options.getOutput().getConsole();
    else
      return OUTPUT_CONSOLE_DEFAULT;
  }

  public static boolean IS_DEFINED_OUTPUT_DATABASE()
  {
    if (options.getOutput() != null)
      return options.getOutput().getDatabase() != null && !options.getOutput().getDatabase().isEmpty();
    else
      return OUTPUT_DATABASE_DEFAULT;
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