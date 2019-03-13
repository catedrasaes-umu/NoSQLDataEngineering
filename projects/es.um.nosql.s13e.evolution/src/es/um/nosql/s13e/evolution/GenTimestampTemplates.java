package es.um.nosql.s13e.evolution;

import java.io.IOException;
import java.io.PrintWriter;

import es.um.nosql.s13e.evolution.templates.TimestampAnalyzer;
import es.um.nosql.s13e.evolution.templates.BasicTimestampAnalyzer;
import es.um.nosql.s13e.evolution.templates.DateTimestampAnalyzer;
import es.um.nosql.s13e.evolution.templates.DefaultTimestampAnalyzer;
import es.um.nosql.s13e.evolution.templates.OIDTimestampAnalyzer;

public class GenTimestampTemplates
{
  private static String OUTPUT_DIR = "timestamp/";
  private static String EXTENSION = ".js";

  private static void GENERATE_TIMESTAMP_FILE(TimestampAnalyzer analyzer)
  {
    try
    {
      PrintWriter writer = new PrintWriter(OUTPUT_DIR + analyzer.getClass().getSimpleName() + EXTENSION, "UTF-8");
      writer.print(analyzer.toString());
      writer.close();      
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    GENERATE_TIMESTAMP_FILE(new DefaultTimestampAnalyzer());
    GENERATE_TIMESTAMP_FILE(new BasicTimestampAnalyzer());
    GENERATE_TIMESTAMP_FILE(new DateTimestampAnalyzer());
    GENERATE_TIMESTAMP_FILE(new OIDTimestampAnalyzer());
  }
}
