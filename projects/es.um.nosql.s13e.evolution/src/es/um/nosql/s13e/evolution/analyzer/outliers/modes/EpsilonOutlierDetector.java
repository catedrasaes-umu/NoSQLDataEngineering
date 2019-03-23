package es.um.nosql.s13e.evolution.analyzer.outliers.modes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
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
  public List<StructuralVariation> removeOutliers(SchemaType schemaType)
  {
    if (threshold < 0.0)
      throw new IllegalArgumentException("Epsilon value must be greater than 0");

    long numObjects = schemaType.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    double countThreshold = Math.round(numObjects * threshold);
    List<StructuralVariation> variationsToRemove = new ArrayList<StructuralVariation>();

    variationsToRemove.addAll(schemaType.getVariations().stream().filter(var -> var.getCount() < countThreshold).collect(Collectors.toList()));
    schemaType.getVariations().removeAll(variationsToRemove);

    return variationsToRemove;
  }

  @Override
  public void reset()
  {
    this.threshold = ConfigConstants.OUTLIER_EPSILON;
  }
}
