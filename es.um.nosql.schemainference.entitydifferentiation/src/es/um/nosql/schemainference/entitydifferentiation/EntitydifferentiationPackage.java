/**
 */
package es.um.nosql.schemainference.entitydifferentiation;

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
 * @see es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationFactory
 * @model kind="package"
 * @generated
 */
public interface EntitydifferentiationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "entitydifferentiation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modelum.es/entitydifferentiation";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "entitydifferentiation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EntitydifferentiationPackage eINSTANCE = es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl.init();

	/**
	 * The meta object id for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDifferentiationImpl <em>Entity Differentiation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntityDifferentiationImpl
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getEntityDifferentiation()
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
	 * The meta object id for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDiffSpecImpl <em>Entity Diff Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntityDiffSpecImpl
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getEntityDiffSpec()
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
	 * The meta object id for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.PropertySpecImpl <em>Property Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.PropertySpecImpl
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getPropertySpec()
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
	 * The meta object id for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityVersionPropImpl <em>Entity Version Prop</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntityVersionPropImpl
	 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getEntityVersionProp()
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
	 * The number of structural features of the '<em>Entity Version Prop</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_VERSION_PROP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Entity Version Prop</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_VERSION_PROP_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation <em>Entity Differentiation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Differentiation</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
	 * @generated
	 */
	EClass getEntityDifferentiation();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getEntityDiffSpecs <em>Entity Diff Specs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entity Diff Specs</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getEntityDiffSpecs()
	 * @see #getEntityDifferentiation()
	 * @generated
	 */
	EReference getEntityDifferentiation_EntityDiffSpecs();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getName()
	 * @see #getEntityDifferentiation()
	 * @generated
	 */
	EAttribute getEntityDifferentiation_Name();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation#getSchema()
	 * @see #getEntityDifferentiation()
	 * @generated
	 */
	EReference getEntityDifferentiation_Schema();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec <em>Entity Diff Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Diff Spec</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
	 * @generated
	 */
	EClass getEntityDiffSpec();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec#getEntity()
	 * @see #getEntityDiffSpec()
	 * @generated
	 */
	EReference getEntityDiffSpec_Entity();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec#getEntityVersionProps <em>Entity Version Props</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entity Version Props</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec#getEntityVersionProps()
	 * @see #getEntityDiffSpec()
	 * @generated
	 */
	EReference getEntityDiffSpec_EntityVersionProps();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec#getCommonProps <em>Common Props</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Common Props</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec#getCommonProps()
	 * @see #getEntityDiffSpec()
	 * @generated
	 */
	EReference getEntityDiffSpec_CommonProps();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.entitydifferentiation.PropertySpec <em>Property Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Spec</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.PropertySpec
	 * @generated
	 */
	EClass getPropertySpec();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.entitydifferentiation.PropertySpec#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Property</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.PropertySpec#getProperty()
	 * @see #getPropertySpec()
	 * @generated
	 */
	EReference getPropertySpec_Property();

	/**
	 * Returns the meta object for the attribute '{@link es.um.nosql.schemainference.entitydifferentiation.PropertySpec#isNeedsTypeCheck <em>Needs Type Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Needs Type Check</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.PropertySpec#isNeedsTypeCheck()
	 * @see #getPropertySpec()
	 * @generated
	 */
	EAttribute getPropertySpec_NeedsTypeCheck();

	/**
	 * Returns the meta object for class '{@link es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp <em>Entity Version Prop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Version Prop</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp
	 * @generated
	 */
	EClass getEntityVersionProp();

	/**
	 * Returns the meta object for the containment reference list '{@link es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp#getPropertySpecs <em>Property Specs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property Specs</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp#getPropertySpecs()
	 * @see #getEntityVersionProp()
	 * @generated
	 */
	EReference getEntityVersionProp_PropertySpecs();

	/**
	 * Returns the meta object for the reference '{@link es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp#getEntityVersion <em>Entity Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity Version</em>'.
	 * @see es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp#getEntityVersion()
	 * @see #getEntityVersionProp()
	 * @generated
	 */
	EReference getEntityVersionProp_EntityVersion();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EntitydifferentiationFactory getEntitydifferentiationFactory();

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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDifferentiationImpl <em>Entity Differentiation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntityDifferentiationImpl
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getEntityDifferentiation()
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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityDiffSpecImpl <em>Entity Diff Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntityDiffSpecImpl
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getEntityDiffSpec()
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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.PropertySpecImpl <em>Property Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.PropertySpecImpl
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getPropertySpec()
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
		 * The meta object literal for the '{@link es.um.nosql.schemainference.entitydifferentiation.impl.EntityVersionPropImpl <em>Entity Version Prop</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntityVersionPropImpl
		 * @see es.um.nosql.schemainference.entitydifferentiation.impl.EntitydifferentiationPackageImpl#getEntityVersionProp()
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

	}

} //EntitydifferentiationPackage
