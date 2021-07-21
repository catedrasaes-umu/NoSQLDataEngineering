package es.um.unosql.xtext.orion.utils.costs

import java.util.List
import es.um.unosql.xtext.orion.orion.BasicOperation
import java.util.Map
import java.util.HashMap
import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.orion.orion.EntityDeleteOp
import es.um.unosql.xtext.orion.orion.EntityRenameOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.EntityExtractOp
import es.um.unosql.xtext.orion.orion.EntitySplitOp
import es.um.unosql.xtext.orion.orion.EntityMergeOp
import es.um.unosql.xtext.orion.orion.EntityDelVarOp
import es.um.unosql.xtext.orion.orion.EntityAdaptOp
import es.um.unosql.xtext.orion.orion.EntityUnionOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.AttributePromoteOp
import es.um.unosql.xtext.orion.orion.AttributeDemoteOp
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.orion.orion.ReferenceMultiplicityOp
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.AggregateMultiplicityOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp

class CassandraModelCost
{
  Map<OperationCost, Integer> costMap

  new()
  {
    this.costMap = new HashMap<OperationCost, Integer>
    this.costMap.put(OperationCost.NO_COST_OP,            0)
    this.costMap.put(OperationCost.CONSTANT_TIME_OP,      0)
    this.costMap.put(OperationCost.SINGLE_TRAVERSAL_OP,   0)
    this.costMap.put(OperationCost.MULTIPLE_TRAVERSAL_OP, 0)
    this.costMap.put(OperationCost.HIGH_COST_OP,          0)
    this.costMap.put(OperationCost.VERY_HIGH_COST_OP,     0)
  }

  def void addToCosts(List<BasicOperation> operations)
  {
    for (op : operations)
      addToCosts(op)
  }

  def void addToCosts(BasicOperation operation)
  {
    this.costMap.put(getCostFrom(operation), this.costMap.get(getCostFrom(operation)) + 1)
  }

  def Map<OperationCost, Integer> getCostMap()
  {
    return this.costMap
  }

  def String showCostMap()
  {
    val result = new StringBuilder()
    result.append("Cassandra Model cost:\n")

    for (entry : costMap.entrySet.sortBy[e | e.value])
      result.append("  " + entry.key + ": " + entry.value + "\n")

    return result.toString
  }

  private def dispatch OperationCost getCostFrom(EntityAddOp op)             { return OperationCost.CONSTANT_TIME_OP  }
  private def dispatch OperationCost getCostFrom(EntityDeleteOp op)          { return OperationCost.CONSTANT_TIME_OP  }
  private def dispatch OperationCost getCostFrom(EntityRenameOp op)          { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(EntityExtractOp op)         { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(EntitySplitOp op)           { return OperationCost.VERY_HIGH_COST_OP }
  private def dispatch OperationCost getCostFrom(EntityMergeOp op)           { return OperationCost.VERY_HIGH_COST_OP }
  private def dispatch OperationCost getCostFrom(EntityDelVarOp op)          { return OperationCost.NO_COST_OP        }
  private def dispatch OperationCost getCostFrom(EntityAdaptOp op)           { return OperationCost.NO_COST_OP        }
  private def dispatch OperationCost getCostFrom(EntityUnionOp op)           { return OperationCost.NO_COST_OP        }

  private def dispatch OperationCost getCostFrom(FeatureDeleteOp op)         { return OperationCost.CONSTANT_TIME_OP  }
  private def dispatch OperationCost getCostFrom(FeatureRenameOp op)         { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(FeatureCopyOp op)           { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(FeatureMoveOp op)           { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(FeatureNestOp op)           { return OperationCost.NO_COST_OP        }
  private def dispatch OperationCost getCostFrom(FeatureUnnestOp op)         { return OperationCost.NO_COST_OP        }

  private def dispatch OperationCost getCostFrom(AttributeAddOp op)          { return OperationCost.CONSTANT_TIME_OP  }
  private def dispatch OperationCost getCostFrom(AttributeCastOp op)         { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(AttributePromoteOp op)      { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(AttributeDemoteOp op)       { return OperationCost.HIGH_COST_OP      }

  private def dispatch OperationCost getCostFrom(ReferenceAddOp op)          { return OperationCost.CONSTANT_TIME_OP  }
  private def dispatch OperationCost getCostFrom(ReferenceCastOp op)         { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(ReferenceMultiplicityOp op) { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(ReferenceMorphOp op)        { return OperationCost.NO_COST_OP        }

  private def dispatch OperationCost getCostFrom(AggregateAddOp op)          { return OperationCost.CONSTANT_TIME_OP  }
  private def dispatch OperationCost getCostFrom(AggregateMultiplicityOp op) { return OperationCost.HIGH_COST_OP      }
  private def dispatch OperationCost getCostFrom(AggregateMorphOp op)        { return OperationCost.NO_COST_OP        }
}
