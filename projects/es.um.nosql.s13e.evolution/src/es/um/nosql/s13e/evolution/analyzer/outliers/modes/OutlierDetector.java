package es.um.nosql.s13e.evolution.analyzer.outliers.modes;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public interface OutlierDetector
{
  public void setFactor(double factor);

  public double getFactor();

  public List<StructuralVariation> removeOutliers(Classifier classifier);

  public void reset();

  public String getSummary();
}
