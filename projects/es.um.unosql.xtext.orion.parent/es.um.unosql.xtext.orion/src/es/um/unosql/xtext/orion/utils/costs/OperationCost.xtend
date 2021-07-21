package es.um.unosql.xtext.orion.utils.costs

enum OperationCost
{
  NO_COST_OP,            // Operation is not implemented
  CONSTANT_TIME_OP,      // Empty cheese
  SINGLE_TRAVERSAL_OP,   // 120º cheese
  MULTIPLE_TRAVERSAL_OP, // 270º cheese
  HIGH_COST_OP,          // Single cheese
  VERY_HIGH_COST_OP      // Double cheese
}
