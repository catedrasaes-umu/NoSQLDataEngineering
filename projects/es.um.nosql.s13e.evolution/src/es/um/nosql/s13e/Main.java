package es.um.nosql.s13e;

import es.um.nosql.s13e.evolution.EvolutionAnalyzer;

public class Main
{
  public static void main(String[] args)
  {
    EvolutionAnalyzer analyzer = new EvolutionAnalyzer();

    analyzer.runLinksExample("links");
    //analyzer.runWebclickExample("webclicks");
  }
}
