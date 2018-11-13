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
  private double threshold;
  private Map<Classifier, List<StructuralVariation>> outliers;

  public OutlierAnalyzer(double threshold)
  {
    this.threshold = threshold;
    this.outliers = new HashMap<Classifier, List<StructuralVariation>>();
  }

  public void removeOutliers(NoSQLSchema schema)
  {
    removeOutliers(schema, threshold);
  }

  public void removeOutliers(NoSQLSchema schema, double threshold)
  {
    for (Classifier classifier : Streams.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      removeOutliers(classifier, threshold);    
  }

  public void removeOutliers(Classifier classifier)
  {
    removeOutliers(classifier, threshold);
  }

  public void removeOutliers(Classifier classifier, double threshold)
  {
    long totalCount = classifier.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    double countThreshold = Math.round(totalCount * threshold);
    outliers.put(classifier, new ArrayList<StructuralVariation>());
    outliers.get(classifier).addAll(classifier.getVariations().stream().filter(var -> var.getCount() < countThreshold).collect(Collectors.toList()));
    classifier.getVariations().removeAll(outliers.get(classifier));
  }

  public void resetAnalyzer()
  {
    resetAnalyzer(threshold);
  }

  public void resetAnalyzer(double threshold)
  {
    this.threshold = threshold;
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
