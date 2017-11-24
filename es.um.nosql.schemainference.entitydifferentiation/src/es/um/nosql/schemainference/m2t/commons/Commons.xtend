package es.um.nosql.schemainference.m2t.commons

import java.io.PrintStream
import java.io.File

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
}