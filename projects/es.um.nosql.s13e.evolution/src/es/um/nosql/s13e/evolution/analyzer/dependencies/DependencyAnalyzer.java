package es.um.nosql.s13e.evolution.analyzer.dependencies;

import es.um.nosql.s13e.NoSQLSchema.Classifier;
import es.um.nosql.s13e.evolution.analyzer.dependencies.detectors.DependentPropsDetector;
import es.um.nosql.s13e.evolution.analyzer.dependencies.detectors.SchemaChangeDetector;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;

public class DependencyAnalyzer
{
  private Classifier classifier;
  private PropertyMatrix matrix;
  private DependentPropsDetector dPropsDetector;
  private SchemaChangeDetector sChangeDetector;

  public DependencyAnalyzer(Classifier classifier)
  {
    this.classifier = classifier;
    this.matrix = new PropertyMatrix(classifier);
    this.dPropsDetector = new DependentPropsDetector(matrix);
    this.sChangeDetector = new SchemaChangeDetector(matrix);
  }

  public Classifier getClassifier()
  {
    return classifier;
  }

  public PropertyMatrix getPropertyMatrix()
  {
    return matrix;
  }

  public DependentPropsDetector getDependentProps()
  {
    return dPropsDetector;
  }

  public SchemaChangeDetector getSchemaChanges()
  {
    return sChangeDetector;
  }
}
