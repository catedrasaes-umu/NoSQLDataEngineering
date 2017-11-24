package es.um.nosql.schemainference.m2t.commons

import java.io.PrintStream
import java.io.File
import es.um.nosql.schemainference.NoSQLSchema.Reference
import java.util.regex.Pattern

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

  public static def IS_INT(String type) { #["int", "integer", "number"].contains(type)}
  public static def IS_FLOAT(String type) { #["float", "double"].contains(type)}
  public static def IS_BOOLEAN(String type) { #["boolean", "bool"].contains(type)}
  public static def IS_OBJECTID(String type) { #["objectid"].contains(type)}
}