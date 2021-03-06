package es.um.nosql.s13e.db.gen.util;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.db.gen.config.DbGenOptions;
import es.um.nosql.s13e.util.ModelLoader;

public class IOUtils
{
  public static DbGenOptions PARSE_CONFIG_FILE(File configFile)
  {
    if (configFile == null || !configFile.exists())
    {
      DebugLog.PRINTERR("Config file is null or does not exist! Ignoring...");
      return new DbGenOptions();
    }

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    try
    {
      DbGenOptions config = mapper.readValue(configFile, DbGenOptions.class);
      config.doCheck();
      DebugLog.PRINTOUT("Config file read and validated!");

      return config;

    } catch (Exception e)
    {
      DebugLog.PRINTERR("Errors detected in config file: " + e.getMessage() + " Ignoring...");
      return new DbGenOptions();
    }
  }

  public static NoSQLSchema READ_MODEL(File modelFile)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);

    return loader.load(modelFile, NoSQLSchema.class);
  }

  public static void PRINT_TO_CONSOLE() {};
}
