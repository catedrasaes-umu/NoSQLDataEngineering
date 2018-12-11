package es.um.nosql.s13e.evolution.stats.outliers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.stats.outliers.modes.CoverageOutlierDetector;
import es.um.nosql.s13e.evolution.stats.outliers.modes.EpsilonOutlierDetector;
import es.um.nosql.s13e.evolution.stats.outliers.modes.OutlierDetector;
import es.um.nosql.s13e.evolution.stats.outliers.modes.OutlierMode;

public class OutlierAnalyzer
{
  private Map<Classifier, List<StructuralVariation>> outliers;
  private OutlierDetector outlierDetector;

  public OutlierAnalyzer(OutlierMode mode)
  {
    this.outliers = new HashMap<Classifier, List<StructuralVariation>>();

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

  public List<StructuralVariation> getOutliers(Classifier classifier)
  {
    return outliers.get(classifier);
  }

  public void removeOutliers(NoSQLSchema schema)
  {
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      outliers.put(classifier, outlierDetector.removeOutliers(classifier));
  }

  public String getSummary()
  {
    StringBuilder result = new StringBuilder();
    String endLine = "\n";

    for (Classifier classifier : outliers.keySet())
    {
      long totalCount = Stream.concat(classifier.getVariations().stream(), outliers.get(classifier).stream()).mapToLong(var -> var.getCount()).sum();
      int totalVariations = classifier.getVariations().size() + outliers.get(classifier).size();
      long countThreshold = Math.round(totalCount * outlierDetector.getFactor());
      long countCoverage = Math.round((totalCount * outlierDetector.getFactor()) / 100);

      result.append("Classifier " + classifier.getName() + ": " + totalCount + " items" + endLine);

      if (outlierDetector.getFactor() != 0)
        result.append("Variations with less than " + countThreshold + " should be treated as outliers" + endLine);
      if (outlierDetector.getFactor() != 100)
        result.append("Coverage is of " + outlierDetector.getFactor() + "% (" + countCoverage + " items)" + endLine);

      result.append("From " + totalVariations + " variations, " + classifier.getVariations().size() + " would remain" + endLine + endLine);
    }

    return result.toString();
  }
}
