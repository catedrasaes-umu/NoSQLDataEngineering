package es.um.nosql.s13e.evolution;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.evolution.output.OutputGen;
import es.um.nosql.s13e.evolution.timestamp.TimestampInferrer;
import es.um.nosql.s13e.evolution.timestamp.gen.BasicTimestampAnalyzer;
import es.um.nosql.s13e.evolution.timestamp.gen.DateTimestampAnalyzer;
import es.um.nosql.s13e.evolution.timestamp.gen.DefaultTimestampAnalyzer;
import es.um.nosql.s13e.evolution.timestamp.gen.OIDTimestampAnalyzer;
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
  }

  public void runWebclickExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new BasicTimestampAnalyzer("timestamp"));
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }

  public void runPublicationsExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new BasicTimestampAnalyzer("pub_year"));
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }

  public void runFacebookExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new DateTimestampAnalyzer("created_time"));
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }

  public void runSanctionsExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new DateTimestampAnalyzer("timestamp"));
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }

  public void runProteinsExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new OIDTimestampAnalyzer());
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }

  public void runStackOverflowExample(String dbName)
  {
    NoSQLSchema schema = inferrer.infer(dbName, new DateTimestampAnalyzer("CreationDate"));
    System.out.println(NoSQLSchemaPrettyPrinter.printPretty(schema));
  }
}

//TODO: Join timestamps, create map, etc.
// Generate conclusions
// Filters? GreaterThan class, LessThan, GreaterOrEqual, LessOrEqual, Equal, Zero, Nonzero...how about a list of conditions?