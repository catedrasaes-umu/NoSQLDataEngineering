package es.um.unosql.subtypes.outliers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.ECollections;

import es.um.unosql.subtypes.outliers.OutlierDetector;
import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;

public class CoverageOutlierDetector implements OutlierDetector
{
  private double percentage;
  private Map<SchemaType, List<StructuralVariation>> outliers;

  public CoverageOutlierDetector(double percentage)
  {
    this.percentage = percentage;
    this.outliers = new HashMap<SchemaType, List<StructuralVariation>>();
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
  public List<StructuralVariation> removeOutliers(SchemaType schemaType)
  {
    if (percentage < 0.0 || percentage > 100)
      throw new IllegalArgumentException("Percentage must be greater than 0 but less than 100");

    ArrayList<StructuralVariation> variationsToRemove = new ArrayList<StructuralVariation>();
    long numObjects = schemaType.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    long coveredObjects = Math.round((numObjects * percentage) / 100);
    double currentCoverage = 0;

    ECollections.sort(schemaType.getVariations(), (var1, var2) -> Long.compare(var2.getCount(), var1.getCount()));

    for (StructuralVariation var : schemaType.getVariations())
      if (currentCoverage <= coveredObjects)
        currentCoverage += var.getCount();
      else
        variationsToRemove.add(var);

    schemaType.getVariations().removeAll(variationsToRemove);
    ECollections.sort(schemaType.getVariations(), (var1, var2) -> Long.compare(var1.getVariationId(), var2.getVariationId()));

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
