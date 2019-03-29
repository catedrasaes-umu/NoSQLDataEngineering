package es.um.nosql.s13e.evolution.analyzer;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.evolution.analyzer.dependencies.detectors.DependentPropsDetector;
import es.um.nosql.s13e.evolution.analyzer.dependencies.detectors.SchemaChangeDetector;
import es.um.nosql.s13e.evolution.analyzer.diffs.PropertyMatrix;

public class DependencyAnalyzer
{
  private SchemaType sType;
  private PropertyMatrix matrix;
  private DependentPropsDetector dPropsDetector;
  private SchemaChangeDetector sChangeDetector;

  public DependencyAnalyzer(SchemaType sType)
  {
    this.sType = sType;
    this.matrix = new PropertyMatrix(sType);
    this.dPropsDetector = new DependentPropsDetector(matrix);
//    this.sChangeDetector = new SchemaChangeDetector(matrix);
  }

  public SchemaType getSchemaType()
  {
    return sType;
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

  public void performAnalysis()
  {
    System.out.println("> " + sType.getName());
    System.out.println(dPropsDetector.detectSubtypes());
    //TODO: Print entity optionals
    // Schema changes
  }
}
