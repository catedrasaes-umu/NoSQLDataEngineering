package es.um.nosql.s13e.evolution;

import java.io.File;
import java.util.stream.Collectors;

import com.google.common.collect.Streams;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.stats.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.util.ModelLoader;

public class GenStats
{
  public final static String INPUT_MODEL = "../es.um.nosql.models/stackoverflow/stackoverflow.xmi";

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(INPUT_MODEL), NoSQLSchema.class);

//    MatrixCreator matrix = new MatrixCreator();
//    matrix.analyze(schema);
//    matrix.printMatrix();
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer();

    for (Classifier classifier : Streams.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
        oAnalyzer.analyzeClassifier(classifier);
  }
}
