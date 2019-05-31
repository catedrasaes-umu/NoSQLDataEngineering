package es.um.nosql.s13e.evolution;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.analyzer.DependencyAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierMode;
import es.um.nosql.s13e.evolution.util.EvolutionPrinter;
import es.um.nosql.s13e.util.ModelLoader;

public class MainPropertyAnalyzer
{
  public final static String DB_NAME = "stackoverflow";

  public final static String INPUT_MODEL = "../es.um.nosql.models/" + DB_NAME + "/" + DB_NAME + ".xmi";

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(INPUT_MODEL), NoSQLSchema.class);

    // Detect and remove outliers given Epsilon = 0.0001 or Coverage = 99.9%
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
    oAnalyzer.removeOutliers(schema);
    analyzeIdentifyingProperties(schema);
  }

  private static void analyzeIdentifyingProperties(NoSQLSchema schema)
  {
    // Do some merges if neccesary
    EvolutionPrinter printer = new EvolutionPrinter();

    // Analyze each property
    for (SchemaType sType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).collect(Collectors.toList()))
    {
      DependencyAnalyzer depDetector = new DependencyAnalyzer(DB_NAME, sType);
      System.out.println(printer.printPretty(depDetector));
      System.out.println(printer.printPretty(depDetector.getSubtypes()));
//      System.out.println(printer.printPretty(depDetector.getDiscriminatorField()));
    }
  }
}
