package es.um.nosql.s13e.evolution.analyzer.outliers.modes;

import java.util.List;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

public interface OutlierDetector
{
  public void setFactor(double factor);

  public double getFactor();

  public List<StructuralVariation> removeOutliers(SchemaType schemaType);

  public void reset();
}
