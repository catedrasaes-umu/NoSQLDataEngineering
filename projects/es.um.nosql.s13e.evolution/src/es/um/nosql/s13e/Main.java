package es.um.nosql.s13e;

import es.um.nosql.s13e.evolution.EvolutionAnalyzer;

public class Main
{
  public static void main(String[] args)
  {
    EvolutionAnalyzer analyzer = new EvolutionAnalyzer();

    //TODO: I will check each example as soon as I am able to. With the results I will recreate each model with the timestamps correctly inferred.
    // Until then ignore timestamps, since all of them are new Date() results.
    //analyzer.runFacebookExample("facebook");
    //analyzer.runPublicationsExample("publications");
    //analyzer.runSanctionsExample("opensanctions");
    //analyzer.runProteinsExample("proteins");
    analyzer.runStackOverflowExample("stackoverflow");
    //analyzer.runLinksExample("links");
    //analyzer.runWebclickExample("webclicks");
    //TODO: In the future might be possible to give instead of a simple attribute, a map-like structure
    // in which for each entity an attribute can be given, so depending on the entity a specific attribute may be used to get timestamp.
    // Example: EveryPolitician has start_date but also birth_date and creation_date.
  }
}
