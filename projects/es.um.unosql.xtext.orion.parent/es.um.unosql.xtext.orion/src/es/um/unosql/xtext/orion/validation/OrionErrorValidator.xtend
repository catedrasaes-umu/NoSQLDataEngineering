package es.um.unosql.xtext.orion.validation

import org.eclipse.xtext.validation.Check
import es.um.unosql.xtext.orion.orion.ReferenceAddSpec
import es.um.unosql.xtext.orion.orion.OrionPackage.Literals
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureNestOp
import es.um.unosql.xtext.orion.orion.FeatureUnnestOp
import es.um.unosql.xtext.orion.orion.FeatureAllocateSpec
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import java.util.Arrays

class OrionErrorValidator extends AbstractOrionValidator
{
  /**
   * @Check: A Copy is not ForAll.
   */
  @Check
  def checkCopyNotForall(FeatureCopyOp op)
  {
    if (op.spec !== null)
    {
      if (op.spec.sourceSelector !== null && op.spec.sourceSelector.forAll)
        error("A Copy operation cannot be \"for all\"", Literals.FEATURE_ALLOCATE_SPEC__SOURCE_SELECTOR)
      if (op.spec.targetSelector !== null && op.spec.targetSelector.forAll)
        error("A Copy operation cannot be \"for all\"", Literals.FEATURE_ALLOCATE_SPEC__TARGET_SELECTOR)
      if (op.spec.sourceSelector !== null && !op.spec.sourceSelector.variations.empty)
        error("A Copy operation cannot select Variations on source Entity", Literals.FEATURE_SELECTOR__VARIATIONS)
    }
  }

  /**
   * @Check: A Move is not ForAll.
   */
  @Check
  def checkMoveNotForall(FeatureMoveOp op)
  {
    if (op.spec !== null)
    {
      if (op.spec.sourceSelector !== null && op.spec.sourceSelector.forAll)
        error("A Move operation cannot be \"for all\"", Literals.FEATURE_ALLOCATE_SPEC__SOURCE_SELECTOR)
      if (op.spec.targetSelector !== null && op.spec.targetSelector.forAll)
        error("A Move operation cannot be \"for all\"", Literals.FEATURE_ALLOCATE_SPEC__TARGET_SELECTOR)
      if (op.spec.sourceSelector !== null && !op.spec.sourceSelector.variations.empty)
        error("A Move operation cannot select Variations on source Entity", Literals.FEATURE_SELECTOR__VARIATIONS)
    }
  }

  /**
   * @Check: A Nest is not ForAll.
   */
  @Check
  def checkNestNotForall(FeatureNestOp op)
  {
    if (op.spec !== null && op.spec.selector !== null)
    {
      if (op.spec.selector.forAll)
        error("A Nest operation cannot be \"for all\"", Literals.FEATURE_NEST_SPEC__SELECTOR)
      if (!op.spec.selector.variations.empty)
        error("A Nest operation cannot select Variations", Literals.FEATURE_SELECTOR__VARIATIONS)
    }
  }

  /**
   * @Check: An Unnest is not ForAll.
   */
  @Check
  def checkUnnestNotForall(FeatureUnnestOp op)
  {
    if (op.spec !== null && op.spec.selector !== null)
    {
      if (op.spec.selector.forAll)
        error("An Unnest operation cannot be \"for all\"", Literals.FEATURE_UNNEST_SPEC__SELECTOR)
      if (op.spec.selector.targets !== null && op.spec.selector.targets.exists[t | !t.contains(".")])
        error("Each unnest operand must be selected with the dot (\".\") operand", Literals.FEATURE_NEST_SPEC__SELECTOR)
      if (!op.spec.selector.variations.empty)
        error("An Unnest operation cannot select Variations", Literals.FEATURE_SELECTOR__VARIATIONS)
    }
  }

  /**
   * @Check: A condition is correctly composed.
   */
  @Check
  def checkAllocateCondition(FeatureAllocateSpec all)
  {
    if (all.condition !== null)
    {
      if (all.sourceSelector.ref !== null && all.condition.c1 !== null && all.condition.c1.contains(".") && !all.condition.c1.contains(all.sourceSelector.ref))
        error("The first member of a condition must relate to the source entity", Literals.CONDITION_DECL__C1)
      if (all.targetSelector.ref !== null && all.condition.c2 !== null && all.condition.c2.contains(".") && !all.condition.c2.contains(all.targetSelector.ref))
        error("The second member of a condition must relate to the target entity", Literals.CONDITION_DECL__C2)
    }
  }
//TODO://////////////////////////////////////////////////////////////////////
//TODO://////////////////////////////////////////////////////////////////////
//TODO://////////////////////////////////////////////////////////////////////
//TODO://////////////////////////////////////////////////////////////////////
//TODO://////////////////////////////////////////////////////////////////////
  /**
   * @Check: A ReferenceAdd type is a primitive type.
   */
  @Check
  def checkSuitableTypeInReference(ReferenceAddSpec ref)
  {
    if (ref.type !== null)
    {
      if (!(ref.type instanceof SinglePrimitiveType))
        error("A reference type can only be an Identifier, String, Integer, Number or Timestamp", Literals.REFERENCE_ADD_SPEC__TYPE)

      val refType = (ref.type as SinglePrimitiveType).typename.toLowerCase

      if (!Arrays.asList("identifier", "string", "integer", "int", "number", "timestamp").contains(refType))
        error("A reference type can only be an Identifier, String, Integer, Number or Timestamp", Literals.REFERENCE_ADD_SPEC__TYPE)
    }
  }
}
