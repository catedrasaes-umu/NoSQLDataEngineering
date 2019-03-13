package es.um.nosql.s13e.evolution.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

import es.um.nosql.s13e.evolution.templates.TimestampAnalyzer;
import es.um.nosql.s13e.nosqlimport.util.MapReduceSources;

public class MapReduceTimestampSources extends MapReduceSources
{
  private static final String BEGIN_REPLACE_TIMESTAMP = "///// BEGIN TimestampAnalyzer declaration /////";
  private static final String END_REPLACE_TIMESTAMP   = "///// END TimestampAnalyzer declaration /////";
  private String timestampCode;

  public MapReduceTimestampSources(String mapReduceDir)
  {
    super(mapReduceDir);
  }

  public MapReduceTimestampSources(String mapReduceDir, String tsCodeFile)
  {
    super(mapReduceDir);
    setTimestampCodeFile(tsCodeFile);
  }

  public MapReduceTimestampSources(String mapReduceDir, TimestampAnalyzer analyzer)
  {
    super(mapReduceDir);
    timestampCode = new String(analyzer.toString());
  }

  public void setTimestampCodeFile(String tsCodeFile)
  {
    try
    {
      Path pathTsCode = new File(tsCodeFile).toPath();
      timestampCode = new String(Files.readAllBytes(pathTsCode));
    } catch(Exception e)
    {
      throw new IllegalArgumentException("Timestamp code could not be found in " + tsCodeFile);
    }
  }

  public String getTimestampCode()
  {
    return this.timestampCode;
  }

  public String getMapJSCode()
  {
    if (timestampCode == null || timestampCode.isEmpty())
      throw new IllegalStateException("Timestamp code was not set before!");

    Pattern pattern = Pattern.compile(BEGIN_REPLACE_TIMESTAMP + ".*" + END_REPLACE_TIMESTAMP, Pattern.DOTALL);
    return pattern.matcher(super.getMapJSCode()).replaceFirst(timestampCode);
  }
}
