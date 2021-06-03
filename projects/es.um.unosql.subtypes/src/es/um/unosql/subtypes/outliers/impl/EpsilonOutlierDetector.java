package es.um.unosql.subtypes.outliers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.um.unosql.subtypes.outliers.OutlierDetector;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;

public class EpsilonOutlierDetector implements OutlierDetector
{
  private double threshold;
  private Map<SchemaType, List<StructuralVariation>> outliers;

  public EpsilonOutlierDetector(double threshold)
  {
    this.threshold = threshold;
    this.outliers = new HashMap<SchemaType, List<StructuralVariation>>();
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
  public void removeOutliers(uNoSQLSchema schema)
  {
    for (SchemaType schemaType : Stream.concat(schema.getEntities().stream(), schema.getRelationships().stream()).collect(Collectors.toList()))
      outliers.put(schemaType, removeOutliers(schemaType));
  }

  @Override
  public Map<SchemaType, List<StructuralVariation>> getOutliers()
  {
    return outliers;
  }
}
