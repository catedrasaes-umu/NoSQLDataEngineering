package es.um.unosql.xtext.orion.utils

import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.orion.orion.EntityOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.FeatureSelector
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceCardinalityOp
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.AggregateCardinalityOp
import es.um.unosql.xtext.orion.orion.OrionOperations
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import java.util.Arrays
import es.um.unosql.xtext.athena.athena.AthenaSchema

class OrionUtils
{
  static def boolean haveSameSelector(BasicOperation op1, BasicOperation op2)
  {
    if (op1 instanceof EntityOp || op2 instanceof EntityOp)
      return false

    return selectorToString(getSelector(op1)).equals(selectorToString(getSelector(op2)))
  }

  static def FeatureSelector getSelector(BasicOperation op)
  {
    var FeatureSelector selector = null
    switch (op)
    {
      case op instanceof FeatureDeleteOp:        selector = (op as FeatureDeleteOp).spec.selector
      case op instanceof FeatureRenameOp:        selector = (op as FeatureRenameOp).spec.selector
      case op instanceof FeatureCopyOp:          selector = (op as FeatureCopyOp).spec.sourceSelector
      case op instanceof FeatureMoveOp:          selector = (op as FeatureMoveOp).spec.sourceSelector
      case op instanceof FeatureNestOp:          selector = (op as FeatureNestOp).spec.selector
      case op instanceof FeatureUnnestOp:        selector = (op as FeatureUnnestOp).spec.selector
      case op instanceof AttributeAddOp:         selector = (op as AttributeAddOp).spec.selector
      case op instanceof AttributeCastOp:        selector = (op as AttributeCastOp).spec.selector
      case op instanceof ReferenceCastOp:        selector = (op as ReferenceCastOp).spec.selector
      case op instanceof ReferenceAddOp:         selector = (op as ReferenceAddOp).spec.selector
      case op instanceof ReferenceCardinalityOp: selector = (op as ReferenceCardinalityOp).spec.selector
      case op instanceof ReferenceMorphOp:       selector = (op as ReferenceMorphOp).spec.selector
      case op instanceof AggregateAddOp:         selector = (op as AggregateAddOp).spec.selector
      case op instanceof AggregateCardinalityOp: selector = (op as AggregateCardinalityOp).spec.selector
      case op instanceof AggregateMorphOp:       selector = (op as AggregateMorphOp).spec.selector
    }

    selector
  }

  static def String selectorToString(FeatureSelector selector)
  {
    selector.forAll? '*' : selector.ref
  }

  static def getEvolutionBlock(OrionOperations orion, int index)
  {
    if (!orion.evolBlocks.empty)
      return orion.evolBlocks.findFirst[b | b.name == index]

    return null
  }

  static def getEntityTypesFromSelector(AthenaSchema schema, FeatureSelector selector)
  {
    selector.forAll ? schema.entities : Arrays.asList(schema.entities.findFirst[e | e.name.equals(selector.ref)])
  }
}
