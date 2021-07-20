package es.um.unosql.xtext.athena.validation

import org.eclipse.xtext.validation.Check
import es.um.unosql.xtext.athena.athena.SchemaTypeDecl
import es.um.unosql.xtext.athena.athena.SimpleFeature
import es.um.unosql.xtext.athena.athena.AthenaPackage.Literals
import es.um.unosql.xtext.athena.athena.SimpleReferenceTarget
import es.um.unosql.xtext.athena.utils.ExpressionSimplifier
import es.um.unosql.xtext.athena.athena.ComposedReferenceTarget
import es.um.unosql.xtext.athena.athena.SinglePrimitiveType
import es.um.unosql.xtext.athena.utils.compare.ComparePrimitiveType
import es.um.unosql.xtext.athena.athena.VariationDecl
import es.um.unosql.xtext.athena.athena.RegularRelationshipDecl
import es.um.unosql.xtext.athena.athena.RegularEntityDecl
import es.um.unosql.xtext.athena.utils.FeatureUtils
import es.um.unosql.xtext.athena.athena.SQLStructure
import org.eclipse.xtext.EcoreUtil2
import es.um.unosql.xtext.athena.athena.TopLevelStructureDefiningElementDecl

class AthenaWarningValidator extends AbstractAthenaValidator
{
  val simplifier = new ExpressionSimplifier(false)
  val primitiveTypeComparer = new ComparePrimitiveType()

  /**
   * @Check: A Schema type name starts with a capital letter.
   */
  @Check
  def checkSchemaTypeStartsWithCapital(SchemaTypeDecl sType)
  {
    if (!Character.isUpperCase(sType.name.charAt(0)))
      warning("\"" + sType.name + "\" first letter should be uppercase", Literals.TOP_LEVEL_STRUCTURE_DEFINING_ELEMENT_DECL__NAME)
  }

  /**
   * @Check: A reference targets an entity type with a key.
   */
  @Check
  def checkSimpleReferenceType(SimpleReferenceTarget ref)
  {
    val entity = ref.ref

    if (entity === null)
      return

    val fSet = simplifier.simplify(entity)
    val keys = FeatureUtils.getKeys(fSet)

    if (keys.empty)
      warning("Entity \"" + entity.name + "\" is referenced but no key can be found", Literals.SIMPLE_REFERENCE_TARGET__REF)
    else if (ref.type === null && keys.forall[feat | feat.type === null])
        warning("Entity \"" + ref.ref.name + "\" is referenced but its key does not have any type", Literals.SIMPLE_REFERENCE_TARGET__REF)
    else if (ref.type !== null)
    {
      val entityKey = keys.head
      if (entityKey.type !== null && !primitiveTypeComparer.compare(ref.type, entityKey.type as SinglePrimitiveType))
        warning("Entity \"" + entity.name + "\" is referenced but reference and key types are not compatible", Literals.SIMPLE_REFERENCE_TARGET__TYPE)
    }
  }

  /**
   * @Check: A composed reference targets an entity with a key.
   */
  @Check
  def checkComposedReferenceType(ComposedReferenceTarget ref)
  {
    val entity = ref.ref

    if (entity === null)
      return

    val fSet = simplifier.simplify(entity)

    if (!fSet.features.exists[feat | feat instanceof SimpleFeature && (feat as SimpleFeature).key])
      warning("Entity \"" + entity.name + "\" is referenced but no key can be found", Literals.COMPOSED_REFERENCE_TARGET__REF)
  }

  /**
   * @Check: A regular entity (common: {}, variations: {}) does not have optional attributes on the common section.
   * Example: entity Entity1{ common: {?a: String}}
   */
  @Check
  def checkNoEntityCommonWOptionals(RegularEntityDecl entity)
  {
    if (entity.common === null || entity.common.structure === null)
      return

    val fSet = simplifier.simplify(entity)

    for (SimpleFeature sFeat : fSet.features.filter(SimpleFeature).filter[feat | feat.optional])
      warning("Entity \"" + entity.name + "\" shouldn't contain optional properties such as \"" + sFeat.name + "\" in a common section", Literals.TOP_LEVEL_STRUCTURE_DEFINING_ELEMENT_DECL__NAME)
  }

  /**
   * @Check: A regular relationship (common: {}, variations: {}) does not have optional attributes on the common section.
   * Example: relationship Rel1{ common: {?a: String}}
   */
  @Check
  def checkNoRelationshipCommonWOptionals(RegularRelationshipDecl rel)
  {
    if (rel.common === null || rel.common.structure === null)
      return

    val fSet = simplifier.simplify(rel)
    for (SimpleFeature sFeat : fSet.features.filter(SimpleFeature).filter[sFeat | sFeat.optional])
      warning("Relationship \"" + rel.name + "\" shouldn't contain optional properties such as \"" + sFeat.name + "\" in a common section", Literals.TOP_LEVEL_STRUCTURE_DEFINING_ELEMENT_DECL__NAME)
  }

  /**
   * @Check: A variation does not have optional features.
   */
  @Check
  def checkNotOptionalsInVariations(VariationDecl v)
  {
    if (v === null || v.structure === null)
      return

    val fSet = simplifier.simplify(v)
    for (SimpleFeature sFeat : fSet.features.filter(SimpleFeature).filter[sFeat | sFeat.optional])
      warning("Variation features are considered optionals by default: \"" + sFeat.name + "\"", Literals.VARIATION_DECL__NAME)
  }

  /**
   * @Check: A SQLStructure name matches its container name.
   */
  @Check
  def checkSQLStructureNameMatch(SQLStructure sqlStruct)
  {
    val container = EcoreUtil2.getContainerOfType(sqlStruct, TopLevelStructureDefiningElementDecl)

    if (container !== null && !container.name.toLowerCase.equals(sqlStruct.name.toLowerCase))
      warning("A SQLStructure name should match its container name: \"" + sqlStruct.name + "\"", Literals.SQL_STRUCTURE__NAME)
  }
}
