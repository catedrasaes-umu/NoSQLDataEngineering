package es.um.nosql.s13e.evolution.stats.dependencies;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Streams;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.evolution.stats.diffs.PropertyMatrix;

public class DependencyDetector
{
  private Map<Classifier, PropertyMatrix> matrixMap;

  public DependencyDetector()
  {
    matrixMap = new HashMap<Classifier, PropertyMatrix>();
  }

  public void analyzeSchema(NoSQLSchema schema)
  {
    for (Classifier classifier : Streams.concat(schema.getEntities().stream(), schema.getRefClasses().stream()).collect(Collectors.toList()))
      matrixMap.put(classifier, new PropertyMatrix(classifier));

    // TODO: Call each detector.
  }

  public void detectDependentProps()
  {
    // Two properties are dependent if, and only if, when one of them occurs, then the other occurs.
  }

  public void detectExclusionProps()
  {
    // Two properties are exclusive if, and only if, when one of them occurs, then the other do not occur.
  }

  public void detectOptionalProps()
  {
    // A property is optional if it appears and dissapears without any patron.
  }

  public void detectSchemaProps()
  {
    // A property is schema changing if:
    // - When added, once it appears, then it appears on the remaining variations
    // - When deleted, once it disappears, it does not show again on any remaining variation
    // - When renamed, once it disappears, it does not show again but another property appears 
  }

  public String getSummary()
  {
    StringBuilder result = new StringBuilder();

    for (Classifier classifier : matrixMap.keySet())
      result.append(matrixMap.get(classifier).getMatrixSummary() + "\n");

    return result.toString();
  }
}
