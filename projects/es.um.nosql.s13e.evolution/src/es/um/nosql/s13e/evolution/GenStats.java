
package es.um.nosql.s13e.evolution;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.analyzer.dependencies.DependencyAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierMode;
import es.um.nosql.s13e.evolution.util.EvolutionPrinter;
import es.um.nosql.s13e.util.ModelLoader;

public class GenStats
{
  public final static String INPUT_MODEL = "../es.um.nosql.models/evolution_media/evolution_media.xmi";

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(INPUT_MODEL), NoSQLSchema.class);
    EvolutionPrinter printer = new EvolutionPrinter();

    // Detect and remove outliers given Epsilon = 0.0001 or Coverage = 99.9%
    //OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
    //oAnalyzer.removeOutliers(schema);

    // Analyze each property
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
    {
      DependencyAnalyzer depDetector = new DependencyAnalyzer(classifier);
      System.out.println(printer.printPretty(depDetector));
      depDetector.performAnalysis();
    }

//    OutputGen output = new OutputGen();
//    output.genConsole(schema);
  }
}
