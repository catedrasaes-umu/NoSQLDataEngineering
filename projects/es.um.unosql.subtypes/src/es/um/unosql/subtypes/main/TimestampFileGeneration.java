package es.um.unosql.subtypes.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import es.um.unosql.subtypes.timestamp.templates.BasicTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.DateTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.DefaultTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.OIDTimestampAnalyzer;
import es.um.unosql.subtypes.timestamp.templates.TimestampAnalyzer;
import es.um.unosql.subtypes.util.configuration.TimestampFileGenerationConfig;

public class TimestampFileGeneration
{
  /**
   * This main function is used to generate TimestampAnalyzer.js files for each and every analyzer in a folder.
   * Later on these analyzers will be integrated on the MapReduce process to infer Timestamps.
   * 
   * Usually, if the timestamp/ folder exists there is no need to execute this main.
   */
  public static void main(String[] args)
  {
    TimestampFileGenerationConfig config = new TimestampFileGenerationConfig(args);

    runTimestampFileGeneration(new DefaultTimestampAnalyzer(), config);
    runTimestampFileGeneration(new BasicTimestampAnalyzer(), config);
    runTimestampFileGeneration(new DateTimestampAnalyzer(), config);
    runTimestampFileGeneration(new OIDTimestampAnalyzer(), config);
  }

  private static void runTimestampFileGeneration(TimestampAnalyzer analyzer, TimestampFileGenerationConfig config)
  {
    Path outputPath = config.getOutputPath();

    System.out.println("> Generating \"" + analyzer.getClass().getSimpleName() + "\" in " + outputPath);

    outputPath.toFile().mkdirs();
    Path outputFilePath = outputPath.resolve(analyzer.getClass().getSimpleName() + ".js");

    try
    {
      PrintWriter writer = new PrintWriter(outputFilePath.toString(), "UTF-8");
      writer.print(analyzer.toString());
      writer.close();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
