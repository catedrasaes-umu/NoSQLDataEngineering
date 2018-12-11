package es.um.nosql.s13e.evolution.stats.outliers.modes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;

public class EpsilonOutlierDetector implements OutlierDetector
{
  private double threshold;

  public EpsilonOutlierDetector()
  {
    this(ConfigConstants.OUTLIER_EPSILON);
  }

  public EpsilonOutlierDetector(double threshold)
  {
    this.threshold = threshold;
  }

  @Override
  public void setFactor(double factor)
  {
    this.threshold = factor;    
  }

  @Override
  public double getFactor()
  {
    return this.threshold;
  }

  @Override
  public List<StructuralVariation> removeOutliers(Classifier classifier)
  {
    long totalCount = classifier.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    double countThreshold = Math.round(totalCount * threshold);
    List<StructuralVariation> result = new ArrayList<StructuralVariation>();

    result.addAll(classifier.getVariations().stream().filter(var -> var.getCount() < countThreshold).collect(Collectors.toList()));
    classifier.getVariations().removeAll(result);

    return result;
  }

  @Override
  public void reset()
  {
    this.threshold = ConfigConstants.OUTLIER_EPSILON;
  }

  @Override
  public String getSummary()
  {
    return null;
  }
}
