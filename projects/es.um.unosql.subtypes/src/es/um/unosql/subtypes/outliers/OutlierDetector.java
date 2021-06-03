package es.um.unosql.subtypes.outliers;

import java.util.List;
import java.util.Map;

import es.um.unosql.uNoSQLSchema.SchemaType;
import es.um.unosql.uNoSQLSchema.StructuralVariation;
import es.um.unosql.uNoSQLSchema.uNoSQLSchema;

public interface OutlierDetector
{
  public void setFactor(double factor);

  public double getFactor();

  public List<StructuralVariation> removeOutliers(SchemaType schemaType);

  public Map<SchemaType, List<StructuralVariation>> getOutliers();

  public void removeOutliers(uNoSQLSchema schema);
}
