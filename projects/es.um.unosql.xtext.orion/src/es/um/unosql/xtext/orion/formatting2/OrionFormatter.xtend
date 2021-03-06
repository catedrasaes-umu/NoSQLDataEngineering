/*
 * generated by Xtext 2.20.0
 */
package es.um.unosql.xtext.orion.formatting2

import es.um.unosql.xtext.orion.orion.EntityAddOp
import es.um.unosql.xtext.orion.orion.OrionOperations
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import es.um.unosql.xtext.orion.orion.BasicOperation
import es.um.unosql.xtext.orion.orion.EntityDeleteOp
import es.um.unosql.xtext.orion.orion.EntityRenameOp
import es.um.unosql.xtext.orion.orion.EntitySplitOp
import es.um.unosql.xtext.orion.orion.EntityMergeOp
import es.um.unosql.xtext.orion.orion.FeatureDeleteOp
import es.um.unosql.xtext.orion.orion.AttributeAddOp
import es.um.unosql.xtext.orion.orion.FeatureRenameOp
import es.um.unosql.xtext.orion.orion.FeatureCopyOp
import es.um.unosql.xtext.orion.orion.FeatureMoveOp
import es.um.unosql.xtext.orion.orion.AttributeCastOp
import es.um.unosql.xtext.orion.orion.ReferenceAddOp
import es.um.unosql.xtext.orion.orion.ReferenceCastOp
import es.um.unosql.xtext.orion.orion.ReferenceCardinalityOp
import es.um.unosql.xtext.orion.orion.ReferenceMorphOp
import es.um.unosql.xtext.orion.orion.AggregateMorphOp
import es.um.unosql.xtext.orion.orion.AggregateCardinalityOp
import es.um.unosql.xtext.orion.orion.AggregateAddOp
import es.um.unosql.xtext.orion.orion.FeatureSelector
import org.eclipse.emf.common.util.EList
import es.um.unosql.xtext.athena.athena.DataType
import es.um.unosql.xtext.orion.orion.SimpleDataFeature
import java.util.HashMap
import org.eclipse.xtext.formatting2.FormatterPreferenceKeys
import org.eclipse.xtext.preferences.MapBasedPreferenceValues

class OrionFormatter extends AbstractFormatter2
{
  // Not necessary apparently
  // @Inject extension OrionGrammarAccess

  static val DEF_SEP = "  "

  def dispatch void format(OrionOperations orion, extension IFormattableDocument document)
  {
    // Set two spaces for tabs.
    val newMap = new HashMap<String, String>
    newMap.put(FormatterPreferenceKeys.indentation.id, DEF_SEP)
    this.request.preferences = new MapBasedPreferenceValues(this.getPreferences(), newMap)

    orion.regionFor.keyword("operations").append[setNewLines(2)]

    if (orion.imports !== null)
      orion.regionFor.feature(es.um.unosql.xtext.athena.athena.AthenaPackage.Literals.IMPORT__IMPORTED_NAMESPACE).append[setNewLines(2)]

    if (!orion.operations.isEmpty)
      orion.operations.format
    else
    {
      for (eBlock: orion.evolBlocks)
      {
        val firstPar = eBlock.regionFor.keywords("{").head
        val lastPar  = eBlock.regionFor.keywords("}").last

        interior(firstPar, lastPar)[indent]
        firstPar.prepend[newLine].append[newLine]
        lastPar.prepend[newLine].append[setNewLines(2)]

        eBlock.operations.format
      }
    }
  }

  def dispatch void format(EList<BasicOperation> ops, extension IFormattableDocument document)
  {
    val iter = ops.iterator
    var firstOp = iter.next

    while(iter.hasNext)
    {
      val nextOp = iter.next
      if (firstOp.class == nextOp.class)
        firstOp.append[newLine]
      else
        firstOp.append[setNewLines(2)]

      firstOp.format
      firstOp = nextOp
    }

    firstOp.format
    firstOp.append[newLine]

  }

  def dispatch void format(EntityAddOp op, extension IFormattableDocument document)
  {
    op.spec.regionFor.keywords(",").forEach[prepend[noSpace]]

    for (feat : op.spec.features)
      feat.format
  }

  def dispatch void format(EntityDeleteOp op, extension IFormattableDocument document)
  {
    op.spec.ref.format
  }

  def dispatch void format(EntityRenameOp op, extension IFormattableDocument document)
  {
    op.spec.ref.format
  }

  def dispatch void format(EntitySplitOp op, extension IFormattableDocument document)
  {
    op.spec.ref.format
    op.spec.features1.format
    op.spec.features2.format
  }

  def dispatch void format(EntityMergeOp op, extension IFormattableDocument document)
  {
    op.spec.ref1.format
    op.spec.ref2.format
    op.spec.condition.format
  }

  def dispatch void format(AttributeAddOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.regionFor.keyword(":").prepend[noSpace]
    op.spec.type.format
    op.spec.regionFor.keyword("(").prepend[noSpace].append[noSpace]
    op.spec.regionFor.keyword(")").prepend[noSpace]
  }

  def dispatch void format(FeatureDeleteOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.regionFor.keyword(":").prepend[noSpace]
  }

  def dispatch void format(FeatureRenameOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.regionFor.keyword(":").prepend[noSpace]
  }

  def dispatch void format(FeatureCopyOp op, extension IFormattableDocument document)
  {
    op.spec.sourceSelector.format
    op.spec.targetSelector.format
    op.spec.condition.format
  }

  def dispatch void format(FeatureMoveOp op, extension IFormattableDocument document)
  {
    op.spec.sourceSelector.format
    op.spec.targetSelector.format
    op.spec.condition.format
  }

  def dispatch void format(AttributeCastOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.type.format
  }

  def dispatch void format(ReferenceAddOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.regionFor.keyword(":").prepend[noSpace]
    op.spec.regionFor.keyword("*").prepend[noSpace]
    op.spec.regionFor.keyword("+").prepend[noSpace]
  }

  def dispatch void format(ReferenceCastOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.type.format
  }

  def dispatch void format(ReferenceCardinalityOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.cardinality.format
  }

  def dispatch void format(ReferenceMorphOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
    op.spec.regionFor.keyword("(").prepend[noSpace].append[noSpace]
    op.spec.regionFor.keyword(")").prepend[noSpace]
  }

  def dispatch void format(AggregateAddOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format

    op.spec.regionFor.keywords(",").forEach[prepend[noSpace]]

    for (feat : op.spec.features)
      feat.format

    op.spec.regionFor.keyword("*").prepend[noSpace]
    op.spec.regionFor.keyword("+").prepend[noSpace]
  }

  def dispatch void format(AggregateCardinalityOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
  }

  def dispatch void format(AggregateMorphOp op, extension IFormattableDocument document)
  {
    op.spec.selector.format
  }

  def dispatch void format(FeatureSelector selector, extension IFormattableDocument document)
  {
    selector.regionFor.keyword("::").prepend[noSpace].append[noSpace]
  }

  def dispatch void format(SimpleDataFeature feat, extension IFormattableDocument document)
  {
    feat.regionFor.keyword(":").prepend[noSpace]
    feat.regionFor.keyword("(").prepend[noSpace].append[noSpace]
    feat.regionFor.keyword(")").prepend[noSpace]
    feat.type.format
  }

  def dispatch void format(DataType type, extension IFormattableDocument document)
  {
    type.regionFor.keyword("<").prepend[noSpace].append[noSpace]
    type.regionFor.keyword(">").prepend[noSpace]
    type.regionFor.keywords(",").forEach[prepend[noSpace]]
  }
}
