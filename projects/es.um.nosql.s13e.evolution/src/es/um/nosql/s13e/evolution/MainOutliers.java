package es.um.nosql.s13e.evolution;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierMode;
import es.um.nosql.s13e.evolution.util.EvolutionPrinter;
import es.um.nosql.s13e.util.ModelLoader;

public class MainOutliers
{
  public final static String INPUT_MODEL = "../es.um.nosql.models/stackoverflow/stackoverflow.xmi";

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(INPUT_MODEL), NoSQLSchema.class);

    // Detect and remove outliers given Epsilon = 0.0001 or Coverage = 99.9%
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
    oAnalyzer.removeOutliers(schema);

    // Detect outliers
    // Extract CSV: All variations, only not outliers, only outliers
    // Analizar para cada outlier qué variación se acerca.
  }
}
