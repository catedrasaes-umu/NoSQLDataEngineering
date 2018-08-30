package es.um.nosql.s13e;

import es.um.nosql.s13e.evolution.EvolutionAnalyzer;

public class Main
{
  public static void main(String[] args)
  {
    EvolutionAnalyzer analyzer = new EvolutionAnalyzer();

    analyzer.runWebclickExample("DEBUG_MapReduceTimestamp", "../es.um.nosql.orchestrator/mapreduce/mongodb/v2/");

    /* Pendiente: Hacer el timestamp como una plantilla y que el usuario pueda meter un format, una condición, un atributo como timestamp...
     * Quizá es buena idea hacer un POJO-like...
     */
  }
}
