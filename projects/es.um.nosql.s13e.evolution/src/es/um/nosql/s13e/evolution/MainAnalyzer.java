package es.um.nosql.s13e.evolution;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.analyzer.DependencyAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierTransformer;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierMode;
import es.um.nosql.s13e.evolution.util.EvolutionPrinter;
import es.um.nosql.s13e.util.ModelLoader;

public class MainAnalyzer
{
  public final static String INPUT_MODEL = "../es.um.nosql.models/reddit/reddit.xmi";

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(INPUT_MODEL), NoSQLSchema.class);

    // Detect and remove outliers given Epsilon = 0.0001 or Coverage = 99%
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
    oAnalyzer.removeOutliers(schema);
    analyzeOutliers(schema, oAnalyzer.getOutliers());
  }

  private static void analyzeIdentifyingProperties(NoSQLSchema schema)
  {
    //TODO: Work for the evolution project.
    EvolutionPrinter printer = new EvolutionPrinter();

    // Analyze each property
    for (SchemaType sType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).collect(Collectors.toList()))
    {
      DependencyAnalyzer depDetector = new DependencyAnalyzer(sType);
      depDetector.performAnalysis();
      System.out.println(printer.printPretty(depDetector));
    }
  }

  private static void analyzeOutliers(NoSQLSchema schema, Map<SchemaType, List<StructuralVariation>> outliers)
  {
    OutlierTransformer transformer = new OutlierTransformer(schema, outliers);
    transformer.analyzeAlternativeVariations(1);

    EvolutionPrinter printer = new EvolutionPrinter();
    System.out.println(printer.printPretty(transformer));
  }
}
