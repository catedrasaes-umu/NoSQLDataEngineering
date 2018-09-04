package es.um.nosql.s13e;

import es.um.nosql.s13e.evolution.EvolutionAnalyzer;
import es.um.nosql.s13e.evolution.util.InferenceMode;

public class Main
{
  public static void main(String[] args)
  {
    EvolutionAnalyzer analyzer = new EvolutionAnalyzer();

    //analyzer.runHarvardExample(InferenceMode.ANALYZE_ONLY, "harvard");
    //analyzer.runFacebookExample(InferenceMode.ANALYZE_ONLY, "facebook");
    //analyzer.runPublicationsExample(InferenceMode.ANALYZE_ONLY, "publications");
    //analyzer.runSanctionsExample(InferenceMode.ANALYZE_ONLY, "opensanctions");
    //analyzer.runProteinsExample(InferenceMode.ANALYZE_ONLY, "proteins");
    analyzer.runStackOverflowExample(InferenceMode.ANALYZE_ONLY, "stackoverflow");
    //analyzer.runLinksExample(InferenceMode.ANALYZE_ONLY, "links");
    //analyzer.runWebclickExample(InferenceMode.ANALYZE_ONLY, "webclicks");
    //TODO: In the future might be possible to give instead of a simple attribute, a map-like structure
    // in which for each entity an attribute can be given, so depending on the entity a specific attribute may be used to get timestamp.
    // Example: EveryPolitician has start_date but also birth_date and creation_date.
  }
}
