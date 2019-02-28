package es.um.nosql.s13e.evolution.analyzer.outliers.modes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;

public class CoverageOutlierDetector implements OutlierDetector
{
  private double percentage;

  public CoverageOutlierDetector()
  {
    this(ConfigConstants.OUTLIER_COVERAGE);
  }

  public CoverageOutlierDetector(double percentage)
  {
    this.percentage = percentage;
  }

  @Override
  public void setFactor(double percentage)
  {
    this.percentage = percentage;
  }

  @Override
  public double getFactor()
  {
    return this.percentage;
  }

  @Override
  public List<StructuralVariation> removeOutliers(Classifier classifier)
  {
    if (percentage < 0.0 || percentage > 100)
      throw new IllegalArgumentException("Percentage must be greater than 0 but less than 100");

    long totalCount = classifier.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    long countCoverage = Math.round((totalCount * percentage) / 100);
    double currentCoverage = 0;
    ArrayList<StructuralVariation> result = new ArrayList<StructuralVariation>();

    ECollections.sort(classifier.getVariations(), (var1, var2) -> Long.compare(var2.getCount(), var1.getCount()));

    for (StructuralVariation var : classifier.getVariations())
      if (currentCoverage > countCoverage)
        result.add(var);
      else
        currentCoverage += var.getCount();

    classifier.getVariations().removeAll(result);
    ECollections.sort(classifier.getVariations(), (var1, var2) -> Long.compare(var1.getVariationId(), var2.getVariationId()));

    return result;
  }

  @Override
  public void reset()
  {
    this.percentage = ConfigConstants.OUTLIER_COVERAGE;
  }

  @Override
  public String getSummary()
  {
    return null;
  }
}
