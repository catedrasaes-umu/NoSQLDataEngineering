package es.um.unosql.xtext.athena.utils.io

import java.io.File
import java.io.PrintStream

class CodeWriter
{
  def writeToFile(File outputDir, String filename, CharSequence toWrite)
  {
    val outFile = outputDir.toPath().resolve(filename).toFile()
    val outFileWriter = new PrintStream(outFile)

    outFileWriter.print(toWrite)
    outFileWriter.close()
  }
}
