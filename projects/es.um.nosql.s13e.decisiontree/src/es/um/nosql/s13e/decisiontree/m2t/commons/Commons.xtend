package es.um.nosql.s13e.decisiontree.m2t.commons

import java.io.PrintStream
import java.io.File

class Commons
{
  static def void WRITE_TO_FILE(File outputDir, String filename, CharSequence toWrite)
  {
    val outFile = outputDir.toPath().resolve(filename).toFile()
    val outFileWriter = new PrintStream(outFile)
    outFileWriter.print(toWrite)
    outFileWriter.close()
  }

  static def IS_STRING(String type) { #["string"].contains(type)}
  static def IS_INT(String type) { #["int", "integer", "number"].contains(type)}
  static def IS_DOUBLE(String type) { #["float", "double"].contains(type)}
  static def IS_BOOLEAN(String type) { #["boolean", "bool"].contains(type)}
  static def IS_OBJECTID(String type) { #["objectid"].contains(type)}
}
