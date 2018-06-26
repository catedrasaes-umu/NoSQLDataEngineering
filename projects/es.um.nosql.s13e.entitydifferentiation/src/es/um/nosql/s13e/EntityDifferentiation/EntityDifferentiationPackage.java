/**
 */
package es.um.nosql.s13e.EntityDifferentiation;

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
 * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationFactory
 * @model kind="package"
 * @generated
 */
public interface EntityDifferentiationPackage extends EPackage
{
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
  EntityDifferentiationPackage eINSTANCE = es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl.init();

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationImpl <em>Entity Differentiation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationImpl
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDifferentiation()
   * @generated
   */
  int ENTITY_DIFFERENTIATION = 0;

  /**
   * The feature id for the '<em><b>Entity Diff Specs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS = 0;

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
   * The meta object id for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityDiffSpecImpl <em>Entity Diff Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDiffSpecImpl
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDiffSpec()
   * @generated
   */
  int ENTITY_DIFF_SPEC = 1;

  /**
   * The feature id for the '<em><b>Entity</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF_SPEC__ENTITY = 0;

  /**
   * The feature id for the '<em><b>Entity Version Props</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS = 1;

  /**
   * The feature id for the '<em><b>Common Props</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF_SPEC__COMMON_PROPS = 2;

  /**
   * The number of structural features of the '<em>Entity Diff Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF_SPEC_FEATURE_COUNT = 3;

  /**
   * The number of operations of the '<em>Entity Diff Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DIFF_SPEC_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.PropertySpecImpl <em>Property Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.PropertySpecImpl
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getPropertySpec()
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
   * The meta object id for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVersionPropImpl <em>Entity Version Prop</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityVersionPropImpl
   * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityVersionProp()
   * @generated
   */
  int ENTITY_VERSION_PROP = 3;

  /**
   * The feature id for the '<em><b>Property Specs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VERSION_PROP__PROPERTY_SPECS = 0;

  /**
   * The feature id for the '<em><b>Entity Version</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VERSION_PROP__ENTITY_VERSION = 1;

  /**
   * The feature id for the '<em><b>Not Props</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VERSION_PROP__NOT_PROPS = 2;

  /**
   * The number of structural features of the '<em>Entity Version Prop</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VERSION_PROP_FEATURE_COUNT = 3;

  /**
   * The number of operations of the '<em>Entity Version Prop</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_VERSION_PROP_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation <em>Entity Differentiation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Differentiation</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation
   * @generated
   */
  EClass getEntityDifferentiation();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation#getEntityDiffSpecs <em>Entity Diff Specs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Diff Specs</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation#getEntityDiffSpecs()
   * @see #getEntityDifferentiation()
   * @generated
   */
  EReference getEntityDifferentiation_EntityDiffSpecs();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation#getName()
   * @see #getEntityDifferentiation()
   * @generated
   */
  EAttribute getEntityDifferentiation_Name();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation#getSchema <em>Schema</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Schema</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation#getSchema()
   * @see #getEntityDifferentiation()
   * @generated
   */
  EReference getEntityDifferentiation_Schema();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec <em>Entity Diff Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Diff Spec</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec
   * @generated
   */
  EClass getEntityDiffSpec();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getEntity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Entity</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getEntity()
   * @see #getEntityDiffSpec()
   * @generated
   */
  EReference getEntityDiffSpec_Entity();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getEntityVersionProps <em>Entity Version Props</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entity Version Props</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getEntityVersionProps()
   * @see #getEntityDiffSpec()
   * @generated
   */
  EReference getEntityDiffSpec_EntityVersionProps();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getCommonProps <em>Common Props</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Common Props</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec#getCommonProps()
   * @see #getEntityDiffSpec()
   * @generated
   */
  EReference getEntityDiffSpec_CommonProps();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.EntityDifferentiation.PropertySpec <em>Property Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Spec</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.PropertySpec
   * @generated
   */
  EClass getPropertySpec();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.EntityDifferentiation.PropertySpec#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.PropertySpec#getProperty()
   * @see #getPropertySpec()
   * @generated
   */
  EReference getPropertySpec_Property();

  /**
   * Returns the meta object for the attribute '{@link es.um.nosql.s13e.EntityDifferentiation.PropertySpec#isNeedsTypeCheck <em>Needs Type Check</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Needs Type Check</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.PropertySpec#isNeedsTypeCheck()
   * @see #getPropertySpec()
   * @generated
   */
  EAttribute getPropertySpec_NeedsTypeCheck();

  /**
   * Returns the meta object for class '{@link es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp <em>Entity Version Prop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Version Prop</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp
   * @generated
   */
  EClass getEntityVersionProp();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp#getPropertySpecs <em>Property Specs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Property Specs</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp#getPropertySpecs()
   * @see #getEntityVersionProp()
   * @generated
   */
  EReference getEntityVersionProp_PropertySpecs();

  /**
   * Returns the meta object for the reference '{@link es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp#getEntityVersion <em>Entity Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Entity Version</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp#getEntityVersion()
   * @see #getEntityVersionProp()
   * @generated
   */
  EReference getEntityVersionProp_EntityVersion();

  /**
   * Returns the meta object for the containment reference list '{@link es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp#getNotProps <em>Not Props</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Not Props</em>'.
   * @see es.um.nosql.s13e.EntityDifferentiation.EntityVersionProp#getNotProps()
   * @see #getEntityVersionProp()
   * @generated
   */
  EReference getEntityVersionProp_NotProps();

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
  interface Literals
  {
    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationImpl <em>Entity Differentiation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationImpl
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDifferentiation()
     * @generated
     */
    EClass ENTITY_DIFFERENTIATION = eINSTANCE.getEntityDifferentiation();

    /**
     * The meta object literal for the '<em><b>Entity Diff Specs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFFERENTIATION__ENTITY_DIFF_SPECS = eINSTANCE.getEntityDifferentiation_EntityDiffSpecs();

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
     * The meta object literal for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityDiffSpecImpl <em>Entity Diff Spec</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDiffSpecImpl
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityDiffSpec()
     * @generated
     */
    EClass ENTITY_DIFF_SPEC = eINSTANCE.getEntityDiffSpec();

    /**
     * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFF_SPEC__ENTITY = eINSTANCE.getEntityDiffSpec_Entity();

    /**
     * The meta object literal for the '<em><b>Entity Version Props</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFF_SPEC__ENTITY_VERSION_PROPS = eINSTANCE.getEntityDiffSpec_EntityVersionProps();

    /**
     * The meta object literal for the '<em><b>Common Props</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_DIFF_SPEC__COMMON_PROPS = eINSTANCE.getEntityDiffSpec_CommonProps();

    /**
     * The meta object literal for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.PropertySpecImpl <em>Property Spec</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.PropertySpecImpl
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getPropertySpec()
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
     * The meta object literal for the '{@link es.um.nosql.s13e.EntityDifferentiation.impl.EntityVersionPropImpl <em>Entity Version Prop</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityVersionPropImpl
     * @see es.um.nosql.s13e.EntityDifferentiation.impl.EntityDifferentiationPackageImpl#getEntityVersionProp()
     * @generated
     */
    EClass ENTITY_VERSION_PROP = eINSTANCE.getEntityVersionProp();

    /**
     * The meta object literal for the '<em><b>Property Specs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_VERSION_PROP__PROPERTY_SPECS = eINSTANCE.getEntityVersionProp_PropertySpecs();

    /**
     * The meta object literal for the '<em><b>Entity Version</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_VERSION_PROP__ENTITY_VERSION = eINSTANCE.getEntityVersionProp_EntityVersion();

    /**
     * The meta object literal for the '<em><b>Not Props</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY_VERSION_PROP__NOT_PROPS = eINSTANCE.getEntityVersionProp_NotProps();

  }

} //EntityDifferentiationPackage
