package es.um.nosql.s13e.evolution.analyzer.outliers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.CoverageOutlierDetector;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.EpsilonOutlierDetector;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierDetector;
import es.um.nosql.s13e.evolution.analyzer.outliers.modes.OutlierMode;

public class OutlierAnalyzer
{
  private Map<SchemaType, List<StructuralVariation>> outliers;
  private OutlierDetector outlierDetector;

  public OutlierAnalyzer(OutlierMode mode)
  {
    this.outliers = new HashMap<SchemaType, List<StructuralVariation>>();

    switch (mode)
    {
      case COVERAGE: {this.outlierDetector = new CoverageOutlierDetector(); break;}
      case EPSILON: {this.outlierDetector = new EpsilonOutlierDetector(); break;}
      default: throw new IllegalArgumentException("Outlier detector type not implemented yet.");
    }
  }

  public void resetAnalyzer()
  {
    this.outliers.clear();
  }

  public List<StructuralVariation> getOutliers(SchemaType schemaType)
  {
    return outliers.get(schemaType);
  }

  public void removeOutliers(NoSQLSchema schema)
  {
    for (SchemaType schemaType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).collect(Collectors.toList()))
      outliers.put(schemaType, outlierDetector.removeOutliers(schemaType));
  }

  public String getSummary()
  {
    StringBuilder result = new StringBuilder();
    String endLine = "\n";

    for (SchemaType schemaType : outliers.keySet())
    {
      long numObjects = Stream.concat(schemaType.getVariations().stream(), outliers.get(schemaType).stream()).mapToLong(var -> var.getCount()).sum();
      int numVariations = schemaType.getVariations().size() + outliers.get(schemaType).size();

      result.append(schemaType.getName() + ": " + numObjects + " items" + endLine);

      if (outlierDetector instanceof CoverageOutlierDetector)
      {
        long countCoverage = Math.round((numObjects * outlierDetector.getFactor()) / 100);
        result.append("Coverage factor: " + outlierDetector.getFactor() + "% (" + countCoverage + " items)" + endLine);
      }
      else if (outlierDetector instanceof EpsilonOutlierDetector)
      {
        long countThreshold = Math.round(numObjects * outlierDetector.getFactor());
        result.append("Epsilon factor: " + String.format("%.4f", outlierDetector.getFactor()) + " (" + countThreshold + " items)" + endLine);
      }

      result.append("Variations before/after: " + numVariations + " => " + schemaType.getVariations().size() + endLine + endLine);
    }

    return result.toString();
  }
}
