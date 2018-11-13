package es.um.nosql.s13e.evolution;

import java.io.File;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
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

    // Detect and remove outliers given Epsilon = 0.0001
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(ConfigConstants.EPSILON);
    oAnalyzer.removeOutliers(schema);
    System.out.println(oAnalyzer.getSummary());

    // Analyze each property
    DependencyDetector depDetector = new DependencyDetector();
    depDetector.analyzeSchema(schema);
  }
}
