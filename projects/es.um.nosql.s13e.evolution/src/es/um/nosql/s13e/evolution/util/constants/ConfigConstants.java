package es.um.nosql.s13e.evolution.util.constants;

public class ConfigConstants
{
  public static final boolean DEBUG                   = true;

  public static final String DATABASE_IP              = "localhost";
  public static final String MONGODB_MAPREDUCE_FOLDER = "../es.um.nosql.orchestrator/mapreduce/mongodb/v2/";
  public static final String OUTPUT_FOLDER            = "output/";
  public static final String MODELS_FOLDER            = "../es.um.nosql.models/";

  public static final boolean OUTPUT_CSV              = true;
  public static final boolean OUTPUT_CONSOLE          = false;
  public static final boolean OUTPUT_CHART            = false;
  public static final boolean OUTPUT_CHART_FILE       = false;

  public final static double OUTLIER_EPSILON          = 0.0001;
  public final static double OUTLIER_COVERAGE         = 99.5;
}
