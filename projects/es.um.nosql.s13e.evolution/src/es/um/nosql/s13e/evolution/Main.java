package es.um.nosql.s13e.evolution;

import es.um.nosql.s13e.evolution.inferrer.EvolutionInferrer;
import es.um.nosql.s13e.evolution.util.InferenceMode;

public class Main
{
  public static void main(String[] args)
  {
    EvolutionInferrer inferrer = new EvolutionInferrer();

    //inferrer.runHarvardExample(InferenceMode.INFER_ONLY, "harvard");
    //inferrer.runFacebookExample(InferenceMode.INFER_ONLY, "facebook");
    //inferrer.runPublicationsExample(InferenceMode.INFER_ONLY, "publications");
    //inferrer.runSanctionsExample(InferenceMode.INFER_ONLY, "opensanctions");
    //inferrer.runProteinsExample(InferenceMode.INFER_ONLY, "proteins");
    //inferrer.runStackOverflowExample(InferenceMode.ANALYZE_ONLY, "stackoverflow");
    inferrer.runRedditExample(InferenceMode.ANALYZE_ONLY, "reddit");
    //inferrer.runLinksExample(InferenceMode.INFER_ONLY, "links");
    //inferrer.runWebclickExample(InferenceMode.INFER_ONLY, "webclicks");
    //TODO: In the future might be possible to give instead of a simple attribute, a map-like structure
    // in which for each entity an attribute can be given, so depending on the entity a specific attribute may be used to get timestamp.
    // Example: EveryPolitician has start_date but also birth_date and creation_date.
  }
}
