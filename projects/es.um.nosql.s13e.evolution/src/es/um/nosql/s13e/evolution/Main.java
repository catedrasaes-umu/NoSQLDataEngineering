package es.um.nosql.s13e.evolution;

import es.um.nosql.s13e.evolution.analyzer.EvolutionAnalyzer;
import es.um.nosql.s13e.evolution.util.InferenceMode;

public class Main
{
  public static void main(String[] args)
  {
    EvolutionAnalyzer analyzer = new EvolutionAnalyzer();

    analyzer.runHarvardExample(InferenceMode.INFER_ONLY, "harvard");
    analyzer.runFacebookExample(InferenceMode.INFER_ONLY, "facebook");
    analyzer.runPublicationsExample(InferenceMode.INFER_ONLY, "publications");
    analyzer.runSanctionsExample(InferenceMode.INFER_ONLY, "opensanctions");
    analyzer.runProteinsExample(InferenceMode.INFER_ONLY, "proteins");
    analyzer.runStackOverflowExample(InferenceMode.INFER_ONLY, "stackoverflow");
    analyzer.runLinksExample(InferenceMode.INFER_ONLY, "links");
    analyzer.runWebclickExample(InferenceMode.INFER_ONLY, "webclicks");
    //TODO: In the future might be possible to give instead of a simple attribute, a map-like structure
    // in which for each entity an attribute can be given, so depending on the entity a specific attribute may be used to get timestamp.
    // Example: EveryPolitician has start_date but also birth_date and creation_date.
  }
}
