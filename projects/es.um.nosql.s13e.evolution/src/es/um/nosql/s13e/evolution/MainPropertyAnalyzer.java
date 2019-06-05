package es.um.nosql.s13e.evolution;

import java.io.File;
import java.util.ArrayList;
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
import es.um.nosql.s13e.evolution.m2m.NoSQLSchemaToEvolNoSQLSchema;
import es.um.nosql.s13e.evolution.util.EvolutionPrinter;
import es.um.nosql.s13e.util.ModelLoader;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class MainPropertyAnalyzer
{
  public final static String DB_NAME = "products";

  public final static String INPUT_MODEL = "../es.um.nosql.models/" + DB_NAME + "/" + DB_NAME + ".xmi";

  public final static List<String> collections = Arrays.asList("Products", "Posts");

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
    NoSQLSchema schema = loader.load(new File(INPUT_MODEL), NoSQLSchema.class);

    // Detect and remove outliers given Epsilon = 0.0001 or Coverage = 99.9%
    OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
    oAnalyzer.removeOutliers(schema);

    preparePrintedExample(schema);
    //prepareM2MTransformation(schema);
  }

  private static void preparePrintedExample(NoSQLSchema schema)
  {
    // TODO: Do some merges if neccesary
    EvolutionPrinter printer = new EvolutionPrinter();

    // Analyze each property
    for (SchemaType sType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).filter(ent -> collections.contains(ent.getName())).collect(Collectors.toList()))
    {
      DependencyAnalyzer depDetector = new DependencyAnalyzer(DB_NAME, sType, true);
      System.out.println(printer.printPretty(depDetector));
      System.out.println(printer.printPretty(depDetector.getSubtypes()));
      System.out.println(printer.printPretty(depDetector.getDiscriminatorSeeker()));
    }
  }

  private static void prepareM2MTransformation(NoSQLSchema schema)
  {
    List<DependencyAnalyzer> analyzers = new ArrayList<DependencyAnalyzer>();
    for (SchemaType sType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).filter(ent -> collections.contains(ent.getName())).collect(Collectors.toList()))
      analyzers.add(new DependencyAnalyzer(DB_NAME, sType, true));

    NoSQLSchemaToEvolNoSQLSchema transformer = new NoSQLSchemaToEvolNoSQLSchema();
    NoSQLSchema newSchema = transformer.m2m(schema, analyzers);

    NoSQLSchemaWriter writer = new NoSQLSchemaWriter();
    writer.write(newSchema, INPUT_MODEL.replace(".xmi", "_evol.xmi"));
  }
}
