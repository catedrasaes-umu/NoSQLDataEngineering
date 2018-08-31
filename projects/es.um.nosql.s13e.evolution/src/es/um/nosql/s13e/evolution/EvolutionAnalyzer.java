package es.um.nosql.s13e.evolution;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.evolution.output.OutputGen;
import es.um.nosql.s13e.evolution.timestamp.TimestampInferrer;
import es.um.nosql.s13e.evolution.timestamp.gen.BasicTimestampAnalyzer;
import es.um.nosql.s13e.util.NoSQLSchemaPrettyPrinter;

public class EvolutionAnalyzer
{
  private TimestampInferrer inferrer;
  private OutputGen output;

  public EvolutionAnalyzer()
  {
    inferrer = new TimestampInferrer();
    output = new OutputGen();
  }

  public void runLinksExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new BasicTimestampAnalyzer("timestamp"));

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
    //TODO Format has to work properly.
    //TODO: Join timestamps, create map, etc.
    // Generate conclusions
    // Filters? GreaterThan class, LessThan, GreaterOrEqual, LessOrEqual, Equal, Zero, Nonzero...how about a list of conditions?
  }

  public void runWebclickExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new BasicTimestampAnalyzer("timestamp"));

    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
    //TODO: Join timestamps, create map, etc.
    // Generate conclusions
  }
}
