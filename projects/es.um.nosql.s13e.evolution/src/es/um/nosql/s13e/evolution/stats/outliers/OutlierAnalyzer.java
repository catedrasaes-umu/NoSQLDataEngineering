package es.um.nosql.s13e.evolution.stats.outliers;

import es.um.nosql.s13e.NoSQLSchema.Classifier;

public class OutlierAnalyzer
{
  private final static double EPSILON = 0.0001;

  public void analyzeClassifier(Classifier classifier)
  {

    long totalCount = classifier.getVariations().stream().mapToLong(var -> var.getCount()).sum();
    double countEpsilon = Math.round(totalCount * EPSILON);
    System.out.println("Entity " + classifier.getName() + ": " + totalCount + " items");
    System.out.println("Variations with less than " + countEpsilon + " should be treated as outliers");
    System.out.println("From " + classifier.getVariations().size() + " variations, " +
      classifier.getVariations().stream().filter(var -> var.getCount() > countEpsilon).count() + " would remain");
    System.out.println();
  }
}
