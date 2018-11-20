package es.um.nosql.s13e.evolution.stats.outliers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public class OutlierAnalyzer
{
  private Map<Classifier, List<StructuralVariation>> outliers;
  private double threshold;
  private double percentage;

  public OutlierAnalyzer()
  {
    this.outliers = new HashMap<Classifier, List<StructuralVariation>>();
  }

  public void removeOutliersByEpsilon(NoSQLSchema schema, double threshold)
  {
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
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

  public void removeOutliersByCoverage(NoSQLSchema schema, double percentage)
  {
    for (Classifier classifier : Stream.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      removeOutliersByCoverage(classifier, percentage);
  }

  public void removeOutliersByCoverage(Classifier classifier, double percentage)
  {
    if (percentage < 0.0 || percentage > 100)
      throw new IllegalArgumentException("Percentage must be greater than 0 but less than 100");

    this.percentage = percentage;
    long totalCount = classifier.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    long countCoverage = Math.round((totalCount * percentage) / 100);
    double currentCoverage = 0;
    outliers.put(classifier, new ArrayList<StructuralVariation>());

    ECollections.sort(classifier.getVariations(), (var1, var2) -> Long.compare(var2.getCount(), var1.getCount()));

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
      long totalCount = Stream.concat(classifier.getVariations().stream(), outliers.get(classifier).stream()).mapToLong(var -> var.getCount()).sum();
      int totalVariations = classifier.getVariations().size() + outliers.get(classifier).size();
      long countThreshold = Math.round(totalCount * threshold);
      long countCoverage = Math.round((totalCount * percentage) / 100);

      result.append("Classifier " + classifier.getName() + ": " + totalCount + " items" + endLine);

      if (threshold != 0)
        result.append("Variations with less than " + countThreshold + " should be treated as outliers" + endLine);
      if (percentage != 100)
        result.append("Coverage is of " + percentage + "% (" + countCoverage + " items)" + endLine);

      result.append("From " + totalVariations + " variations, " + classifier.getVariations().size() + " would remain" + endLine + endLine);
    }

    return result.toString();
  }
}
