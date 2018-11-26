package es.um.nosql.s13e.evolution;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.output.OutputGen;
import es.um.nosql.s13e.evolution.stats.dependencies.DependencyDetector;
import es.um.nosql.s13e.evolution.stats.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;
import es.um.nosql.s13e.util.ModelLoader;

public class GenStats
{
  public final static String INPUT_MODEL = "../es.um.nosql.models/stackoverflow/stackoverflow.xmi";

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(INPUT_MODEL), NoSQLSchema.class);

    // Detect and remove outliers given Epsilon = 0.0001 or Coverage = 99.5%
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer();
    oAnalyzer.removeOutliersByCoverage(schema, ConfigConstants.OUTLIER_COVERAGE);
//    System.out.println(oAnalyzer.getSummary());

    // Analyze each property
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
    {
      DependencyDetector depDetector = new DependencyDetector(classifier);
      System.out.println(depDetector.getSummary());      
    }


//    OutputGen output = new OutputGen();
//    output.genConsole(schema);
  }
}
