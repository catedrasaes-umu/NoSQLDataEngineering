package es.um.nosql.s13e.evolution.stats.outliers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Streams;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class OutlierAnalyzer
{
  private Map<Classifier, List<StructuralVariation>> outliers;
  private double threshold;
  private double percentage;

  public OutlierAnalyzer(double threshold)
  {
    this.outliers = new HashMap<Classifier, List<StructuralVariation>>();
  }

  public void removeOutliersByEpsilon(NoSQLSchema schema, double threshold)
  {
    for (Classifier classifier : Streams.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      removeOutliersByEpsilon(classifier, threshold);    
  }

  public void removeOutliersByEpsilon(Classifier classifier, double threshold)
  {
    this.threshold = threshold;
    long totalCount = classifier.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    double countThreshold = Math.round(totalCount * threshold);
    outliers.put(classifier, new ArrayList<StructuralVariation>());
    outliers.get(classifier).addAll(classifier.getVariations().stream().filter(var -> var.getCount() < countThreshold).collect(Collectors.toList()));
    classifier.getVariations().removeAll(outliers.get(classifier));
  }

  // TODO: Under testing
  public void removeOutliersByCoverage(Classifier classifier, double percentage)
  {
    if (percentage < 0.0 || percentage > 100)
      throw new IllegalArgumentException("Percentage must be greater than 0 but less than 100");

    this.percentage = percentage;
    long totalCount = classifier.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    double countCoverage = (totalCount * percentage) / 100;
    double currentCoverage = 0;
    outliers.put(classifier, new ArrayList<StructuralVariation>());

    for (StructuralVariation var : classifier.getVariations())
      if (currentCoverage > countCoverage)
        outliers.get(classifier).add(var);
      else
        currentCoverage += var.getCount();

    classifier.getVariations().removeAll(outliers.get(classifier));
  }

  public void resetAnalyzer()
  {
    this.threshold = 0.0;
    this.percentage = 100;
    this.outliers.clear();
  }

  public List<StructuralVariation> getOutliers(Classifier classifier)
  {
    return outliers.get(classifier);
  }

  public String getSummary()
  {
    StringBuilder result = new StringBuilder();
    String endLine = "\n";

    for (Classifier classifier : outliers.keySet())
    {
      long totalCount = Streams.concat(classifier.getVariations().stream(), outliers.get(classifier).stream()).mapToLong(var -> var.getCount()).sum();
      int totalVariations = classifier.getVariations().size() + outliers.get(classifier).size();
      double countThreshold = Math.round(totalCount * threshold);

      result.append("Classifier " + classifier.getName() + ": " + totalCount + " items" + endLine);
      result.append("Variations with less than " + countThreshold + " should be treated as outliers" + endLine);
      result.append("From " + totalVariations + " variations, " + classifier.getVariations().size() + " would remain" + endLine + endLine);
    }

    return result.toString();
  }
}
