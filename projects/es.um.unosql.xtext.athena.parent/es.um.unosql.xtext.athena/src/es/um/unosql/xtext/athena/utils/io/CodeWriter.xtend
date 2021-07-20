package es.um.unosql.xtext.athena.utils.io

import java.io.PrintStream
import java.nio.file.Path

class CodeWriter
{
  def write(CharSequence toWrite, Path outputRoute)
  {
    val outFileWriter = new PrintStream(outputRoute.toFile)

    outFileWriter.print(toWrite)
    outFileWriter.close()
  }
}
