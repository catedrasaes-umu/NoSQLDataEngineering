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

  public Map<SchemaType, List<StructuralVariation>> getOutliers()
  {
    return outliers;
  }

  public OutlierDetector getOutlierDetector()
  {
    return outlierDetector;
  }

  public void removeOutliers(NoSQLSchema schema)
  {
    for (SchemaType schemaType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).collect(Collectors.toList()))
      outliers.put(schemaType, outlierDetector.removeOutliers(schemaType));
  }
}
