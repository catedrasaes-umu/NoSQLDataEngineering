package es.um.nosql.s13e.m2t.commons

import java.io.PrintStream
import java.io.File
import es.um.nosql.s13e.NoSQLSchema.Reference
import java.util.regex.Pattern
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.databind.ObjectMapper
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation
import es.um.nosql.s13e.m2t.config.BaseConfig

/**
 * This class factors out some common methods to the Morphia and Mongoose generators.
 * All its methods should remain static.
 */
class Commons
{
  /**
   * Method used to write a generated CharSequence to a file
   */
  public static def void WRITE_TO_FILE(File outputDir, String filename, CharSequence toWrite)
  {
    val outFile = outputDir.toPath().resolve(filename).toFile()
    val outFileWriter = new PrintStream(outFile)
    outFileWriter.print(toWrite)
    outFileWriter.close()
  }

  /**
   * Method used to process a Reference and check out if it is a DBRef or just a string|number storing an id.
   */
  public static def EXPAND_REF(Reference reference) 
  {
    val pat = Pattern.compile("DBRef\\((.+?)\\)")
    val m = pat.matcher(reference.originalType)
    if (m.matches)
      #["dbref", m.group(0)]
    else
      #[reference.originalType]
  }

  public static def IS_DBREF(Reference reference)
  {
    return EXPAND_REF(reference).length == 2
  }

  public static def IS_STRING(String type) { #["string"].contains(type)}
  public static def IS_INT(String type) { #["int", "integer", "number"].contains(type)}
  public static def IS_DOUBLE(String type) { #["float", "double"].contains(type)}
  public static def IS_BOOLEAN(String type) { #["boolean", "bool"].contains(type)}
  public static def IS_OBJECTID(String type) { #["objectid"].contains(type)}

  def static <T extends BaseConfig> T PARSE_CONFIG_FILE(Class<T> className, File configFile, EntityDifferentiation diff)
  {
    if (configFile === null || !configFile.exists)
    {
      System.err.println("Config file is null or does not exist! Ignoring...");
      return null;
    }

    val mapper = new ObjectMapper(new YAMLFactory());
    try
    {
      var config = mapper.readValue(configFile, className);
      config.doCheck(diff);
      println("Config file read and validated!");

      return config;
    } catch (Exception e)
    {
      System.err.println("Errors detected in config file: " + e.message + ". Ignoring...")
      return null;
    }
  }
}