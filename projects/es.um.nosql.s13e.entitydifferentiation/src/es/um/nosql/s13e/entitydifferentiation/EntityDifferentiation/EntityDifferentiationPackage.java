/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationFactory
 * @model kind="package"
 * @generated
 */
public interface EntityDifferentiationPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "EntityDifferentiation";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.modelum.es/EntityDifferentiation";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "EntityDifferentiation";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EntityDifferentiationPackage eINSTANCE = es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl.init();

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl <em>Entity Differentiation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDifferentiation()
   * @generated
   */
  int ENTITY_DIFFERENTIATION = 0;

  /**
   * The feature id for the '<em><b>Entity Diffs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFFERENTIATION__ENTITY_DIFFS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFFERENTIATION__NAME = 1;

  /**
   * The feature id for the '<em><b>Schema</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFFERENTIATION__SCHEMA = 2;

  /**
   * The number of structural features of the '<em>Entity Differentiation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFFERENTIATION_FEATURE_COUNT = 3;

  /**
   * The number of operations of the '<em>Entity Differentiation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFFERENTIATION_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDiffImpl <em>Entity Diff</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDiffImpl
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDiff()
   * @generated
   */
  int ENTITY_DIFF = 1;

  /**
   * The feature id for the '<em><b>Entity</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF__ENTITY = 0;

  /**
   * The feature id for the '<em><b>Variation Diffs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF__VARIATION_DIFFS = 1;

  /**
   * The feature id for the '<em><b>Common Props</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF__COMMON_PROPS = 2;

  /**
   * The number of structural features of the '<em>Entity Diff</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF_FEATURE_COUNT = 3;

  /**
   * The number of operations of the '<em>Entity Diff</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.PropertySpecImpl <em>Property Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.PropertySpecImpl
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getPropertySpec()
   * @generated
   */
  int PROPERTY_SPEC = 2;

  /**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC__PROPERTY = 0;

  /**
   * The feature id for the '<em><b>Needs Type Check</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC__NEEDS_TYPE_CHECK = 1;

  /**
   * The number of structural features of the '<em>Property Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Property Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SPEC_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.StructuralVariationDiffImpl <em>Structural Variation Diff</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.StructuralVariationDiffImpl
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getStructuralVariationDiff()
   * @generated
   */
  int STRUCTURAL_VARIATION_DIFF = 3;

  /**
   * The feature id for the '<em><b>Property Specs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS = 0;

  /**
   * The feature id for the '<em><b>Variation</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_VARIATION_DIFF__VARIATION = 1;

  /**
   * The feature id for the '<em><b>Not Props</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_VARIATION_DIFF__NOT_PROPS = 2;

  /**
   * The number of structural features of the '<em>Structural Variation Diff</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_VARIATION_DIFF_FEATURE_COUNT = 3;

  /**
   * The number of operations of the '<em>Structural Variation Diff</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_VARIATION_DIFF_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation <em>Entity Differentiation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Differentiation</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation
   * @generated
   */
  EClass getEntityDifferentiation();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getEntityDiffs <em>Entity Diffs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Diffs</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getEntityDiffs()
   * @see #getEntityDifferentiation()
   * @generated
   */
  EReference getEntityDifferentiation_EntityDiffs();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getName()
   * @see #getEntityDifferentiation()
   * @generated
   */
  EAttribute getEntityDifferentiation_Name();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getSchema <em>Schema</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Schema</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiation#getSchema()
   * @see #getEntityDifferentiation()
   * @generated
   */
  EReference getEntityDifferentiation_Schema();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff <em>Entity Diff</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Diff</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff
   * @generated
   */
  EClass getEntityDiff();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getEntity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Entity</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getEntity()
   * @see #getEntityDiff()
   * @generated
   */
  EReference getEntityDiff_Entity();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getVariationDiffs <em>Variation Diffs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variation Diffs</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getVariationDiffs()
   * @see #getEntityDiff()
   * @generated
   */
  EReference getEntityDiff_VariationDiffs();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getCommonProps <em>Common Props</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Common Props</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDiff#getCommonProps()
   * @see #getEntityDiff()
   * @generated
   */
  EReference getEntityDiff_CommonProps();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec <em>Property Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Spec</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec
   * @generated
   */
  EClass getPropertySpec();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec#getProperty()
   * @see #getPropertySpec()
   * @generated
   */
  EReference getPropertySpec_Property();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec#isNeedsTypeCheck <em>Needs Type Check</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Needs Type Check</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec#isNeedsTypeCheck()
   * @see #getPropertySpec()
   * @generated
   */
  EAttribute getPropertySpec_NeedsTypeCheck();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff <em>Structural Variation Diff</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Structural Variation Diff</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff
   * @generated
   */
  EClass getStructuralVariationDiff();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getPropertySpecs <em>Property Specs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Property Specs</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getPropertySpecs()
   * @see #getStructuralVariationDiff()
   * @generated
   */
  EReference getStructuralVariationDiff_PropertySpecs();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getVariation <em>Variation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Variation</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getVariation()
   * @see #getStructuralVariationDiff()
   * @generated
   */
  EReference getStructuralVariationDiff_Variation();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getNotProps <em>Not Props</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Not Props</em>'.
   * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff#getNotProps()
   * @see #getStructuralVariationDiff()
   * @generated
   */
  EReference getStructuralVariationDiff_NotProps();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EntityDifferentiationFactory getEntityDifferentiationFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl <em>Entity Differentiation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationImpl
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDifferentiation()
     * @generated
     */
    EClass ENTITY_DIFFERENTIATION = eINSTANCE.getEntityDifferentiation();

    /**
     * The meta object literal for the '<em><b>Entity Diffs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFFERENTIATION__ENTITY_DIFFS = eINSTANCE.getEntityDifferentiation_EntityDiffs();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_DIFFERENTIATION__NAME = eINSTANCE.getEntityDifferentiation_Name();

    /**
     * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFFERENTIATION__SCHEMA = eINSTANCE.getEntityDifferentiation_Schema();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDiffImpl <em>Entity Diff</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDiffImpl
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDiff()
     * @generated
     */
    EClass ENTITY_DIFF = eINSTANCE.getEntityDiff();

    /**
     * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFF__ENTITY = eINSTANCE.getEntityDiff_Entity();

    /**
     * The meta object literal for the '<em><b>Variation Diffs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFF__VARIATION_DIFFS = eINSTANCE.getEntityDiff_VariationDiffs();

    /**
     * The meta object literal for the '<em><b>Common Props</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFF__COMMON_PROPS = eINSTANCE.getEntityDiff_CommonProps();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.PropertySpecImpl <em>Property Spec</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.PropertySpecImpl
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getPropertySpec()
     * @generated
     */
    EClass PROPERTY_SPEC = eINSTANCE.getPropertySpec();

    /**
     * The meta object literal for the '<em><b>Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_SPEC__PROPERTY = eINSTANCE.getPropertySpec_Property();

    /**
     * The meta object literal for the '<em><b>Needs Type Check</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY_SPEC__NEEDS_TYPE_CHECK = eINSTANCE.getPropertySpec_NeedsTypeCheck();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.StructuralVariationDiffImpl <em>Structural Variation Diff</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.StructuralVariationDiffImpl
     * @see es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getStructuralVariationDiff()
     * @generated
     */
    EClass STRUCTURAL_VARIATION_DIFF = eINSTANCE.getStructuralVariationDiff();

    /**
     * The meta object literal for the '<em><b>Property Specs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS = eINSTANCE.getStructuralVariationDiff_PropertySpecs();

    /**
     * The meta object literal for the '<em><b>Variation</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRUCTURAL_VARIATION_DIFF__VARIATION = eINSTANCE.getStructuralVariationDiff_Variation();

    /**
     * The meta object literal for the '<em><b>Not Props</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRUCTURAL_VARIATION_DIFF__NOT_PROPS = eINSTANCE.getStructuralVariationDiff_NotProps();

  }

} //EntityDifferentiationPackage
