package es.um.nosql.s13e.evolution;

import java.io.File;
import java.nio.file.Paths;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierAnalyzer;
import es.um.nosql.s13e.evolution.analyzer.outliers.OutlierMigrator;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierMode;
import es.um.nosql.s13e.evolution.output.OutputGen;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;
import es.um.nosql.s13e.util.ModelLoader;

public class MainGenCSVs
{
  public static void main(String[] args)
  {
    String[] inputModels = new String[] {"facebook", "harvard", "links", "opensanctions", "stackoverflow", "webclicks"};

    ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);

    for (String inputModel : inputModels)
    {
      String inputFile = ConfigConstants.MODELS_FOLDER + inputModel + "/" + inputModel + ".xmi";
      NoSQLSchema schema = loader.load(new File(inputFile), NoSQLSchema.class);
      OutputGen output = new OutputGen();
      String folder = Paths.get(ConfigConstants.OUTPUT_FOLDER, schema.getName()).toString();
      String schemaName = schema.getName();

      output.genOutput(folder, schema);

      // Detect and remove outliers given Epsilon = 0.0001 or Coverage = 99.9%
      OutlierAnalyzer oAnalyzer = new OutlierAnalyzer(OutlierMode.COVERAGE);
      oAnalyzer.removeOutliers(schema);
      schema.setName(schemaName + "_livevars");
      output.genOutput(folder, schema);

      OutlierMigrator oMigrator = new OutlierMigrator(schema, oAnalyzer.getOutliers());
      output.genOutput(folder, oMigrator.createNoSQLSchemaFromOutliers(schemaName + "_outliers"));
    }
  }
}
